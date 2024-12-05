package com.co.easy.tasks;

import com.co.easy.exceptions.ProductoNoCoincideException;
import com.co.easy.utils.DatosExcel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static com.co.easy.userInterfaces.ProductoUI.*;
import static com.co.easy.userInterfaces.ResultadoBusquedaUI.LBL_PRODUCTO;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class AgregarAlCarritoTask implements Task {

    private String correo;
    private String producto;

    public AgregarAlCarritoTask(String correo, String producto) {
        this.correo = correo;
        this.producto = producto;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        ArrayList<Map<String, String>> dataExcel;

        String departamentoExcel = "";
        String ciudadExcel = "";
        String direccionExcel = "";

        try {
            dataExcel = DatosExcel.leerDatosDeHojaDeExcel("parametros/DatosCliente.xlsx", "DatosCliente");

            departamentoExcel = dataExcel.get(0).get("departamento");
            ciudadExcel = dataExcel.get(0).get("ciudad");
            direccionExcel = dataExcel.get(0).get("direccion");

        } catch (IOException e) {
            e.printStackTrace();
        }

        String departamentoModificado = departamentoExcel.substring(0, 1).toUpperCase() + departamentoExcel.substring(1).toLowerCase();
        String direccionEntrega = String.format("%s, %s, %s", direccionExcel, ciudadExcel, departamentoModificado);

        actor.attemptsTo(
                Click.on(BTN_AGREGAR_CARRITO),
                WaitUntil.the(TXT_CORREO, isVisible()).forNoMoreThan(5).seconds(),
                Enter.keyValues(correo).into(TXT_CORREO),
                Check.whether(WebElementQuestion.stateOf(BTN_ENVIAR), WebElementStateMatchers.isNotCurrentlyEnabled())
                        .andIfSo(
                                WaitUntil.the(BTN_ENVIAR, isClickable()).forNoMoreThan(10).seconds()
                        ),
                Click.on(BTN_ENVIAR),
                Click.on(SELECT_DEPARTAMENTO),
                Click.on(OPT_DEPARTAMENTO.of(departamentoExcel)),
                Click.on(SELECT_CIUDAD),
                Click.on(OPT_CIUDAD.of(ciudadExcel)),
                Enter.keyValues(direccionExcel).into(TXT_DIRECCION),
                Click.on(BTN_CONFIRMAR),
                WaitUntil.the(LBL_DIRECCION_ENTREGA_COMPLETO.of(direccionEntrega), isVisible()).forNoMoreThan(25).seconds(),
                Click.on(BTN_NUEVO_AGREGAR_CARRITO),
                WaitUntil.the(LBL_PRODUCTO_EN_MINICARRITO, isVisible()).forNoMoreThan(10).seconds()
        );

        String validacionProductoEnMinicarrito = LBL_PRODUCTO_EN_MINICARRITO.resolveFor(actor).getText();

        if (!validacionProductoEnMinicarrito.contains(producto)) {
            throw new ProductoNoCoincideException(String.format(
                    "El producto en el minicarrito [%s] no coincide con el producto del Excel [%s]",
                    validacionProductoEnMinicarrito,
                    producto));
        }

        actor.attemptsTo(
                Check.whether(WebElementQuestion.stateOf(BTN_FINALIZAR_COMPRA), WebElementStateMatchers.isNotVisible())
                        .andIfSo(
                                WaitUntil.the(BTN_FINALIZAR_COMPRA, isVisible()).forNoMoreThan(10).seconds()
                        ),
                Click.on(BTN_FINALIZAR_COMPRA)
        );
    }

    public static AgregarAlCarritoTask conElCorreoYProducto(String correo, String producto) {
        return new AgregarAlCarritoTask(correo, producto);
    }
}
