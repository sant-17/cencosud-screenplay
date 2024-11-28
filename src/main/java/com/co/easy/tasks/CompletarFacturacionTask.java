package com.co.easy.tasks;

import com.co.easy.exceptions.*;
import com.co.easy.utils.DatosExcel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;

import static com.co.easy.userInterfaces.FacturacionUI.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class CompletarFacturacionTask implements Task {

    private String producto;
    private String correo;

    public CompletarFacturacionTask(String producto, String correo) {
        this.producto = producto;
        this.correo = correo;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Check.whether(WebElementQuestion.stateOf(LBL_PRODUCTO_FACTURACION), WebElementStateMatchers.isNotPresent())
                        .andIfSo(
                                WaitUntil.the(LBL_PRODUCTO_FACTURACION, isVisible()).forNoMoreThan(5).seconds()
                        )
        );

        ArrayList<Map<String, String>> dataExcel;

        String departamentoExcel = "";
        String ciudadExcel = "";
        String direccionExcel = "";
        String nombreExcel = "";
        String apellidoExcel = "";
        String tipoDocumentoExcel = "";
        String numeroDocumentoExcel = "";
        String telefonoExcel = "";

        try {
            dataExcel = DatosExcel.leerDatosDeHojaDeExcel("parametros/DatosCliente.xlsx", "DatosCliente");

            departamentoExcel = dataExcel.get(0).get("departamento");
            ciudadExcel = dataExcel.get(0).get("ciudad");
            direccionExcel = dataExcel.get(0).get("direccion");
            nombreExcel = dataExcel.get(0).get("nombre");
            apellidoExcel = dataExcel.get(0).get("apellido");
            tipoDocumentoExcel = dataExcel.get(0).get("tipoDocumento");
            numeroDocumentoExcel = dataExcel.get(0).get("numeroDocumento");
            telefonoExcel = dataExcel.get(0).get("telefono");

        } catch (IOException e) {
            e.printStackTrace();
        }

        actor.attemptsTo(
                Check.whether(WebElementQuestion.stateOf(TBL_CLIENTE), WebElementStateMatchers.isVisible())
                                .andIfSo(
                                    WaitUntil.the(SELECT_TIPO_DOCUMENTO, isVisible()).forNoMoreThan(10).seconds()
                                ),
                Click.on(SELECT_TIPO_DOCUMENTO),
                Click.on(OPT_TIPO_DOCUMENTO.of(tipoDocumentoExcel)),
                Enter.keyValues(apellidoExcel).into(TXT_APELLIDO),
                Enter.keyValues(numeroDocumentoExcel).into(TXT_NUMERO_DOCUMENTO),
                Enter.keyValues(telefonoExcel).into(TXT_TELEFONO),
                Enter.keyValues(nombreExcel).into(TXT_NOMBRE),
                Click.on(BTN_METODO_ENTREGA)
        );

        String departamentoModificado = departamentoExcel.substring(0, 1).toUpperCase() + departamentoExcel.substring(1).toLowerCase();

        String validacionCalleFacturacion = LBL_CALLE.resolveFor(actor).getText();
        String validacionCiudadFacturacion = LBL_CIUDAD.resolveFor(actor).getText();
        String validacionDepartamentoFacturacion = LBL_DEPARTAMENTO.resolveFor(actor).getText();

        if (!validacionCalleFacturacion.equals(direccionExcel)) {
            throw new DatosEntregaNoCoincidenException(String.format(
                    "La calle en facturación [%s] no coincide con la direccion del Excel [%s]",
                    validacionCalleFacturacion,
                    direccionExcel
            ));
        }

        if (!validacionCiudadFacturacion.equals(ciudadExcel)) {
            throw new DatosEntregaNoCoincidenException(String.format(
                    "La ciudad en facturación [%s] no coincide con la ciudad del Excel [%s]",
                    validacionCiudadFacturacion,
                    ciudadExcel
            ));
        }

        if (!validacionDepartamentoFacturacion.equals(departamentoModificado)) {
            throw new DatosEntregaNoCoincidenException(String.format(
                    "El departamento en facturación [%s] no coincide con el departamento del Excel [%s]",
                    validacionDepartamentoFacturacion,
                    departamentoModificado
            ));
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaEntrega = fechaActual.plusDays(5);
        int diaDeEntrega = fechaEntrega.getDayOfMonth();

        actor.attemptsTo(
                Enter.keyValues(String.format("%s %s", nombreExcel, apellidoExcel)).into(TXT_NOMBRE_RECIBE),

                Check.whether(WebElementQuestion.stateOf(BTN_FECHA_ENTREGA), WebElementStateMatchers.isNotVisible())
                        .andIfSo(

                                WaitUntil.the(BTN_FECHA_ENTREGA, isVisible()).forNoMoreThan(10).seconds(),
                                Scroll.to(BTN_FECHA_ENTREGA)
                        ),
                Click.on(BTN_FECHA_ENTREGA),
                Click.on(BTN_MES_SIGUIENTE),
                Click.on(BTN_DIA_ENTREGA.of(String.valueOf(diaDeEntrega))),
                Scroll.to(BTN_METODO_PAGO)
        );

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy");
            LocalDate fechaParseada = LocalDate.parse(LBL_FECHA_ENTREGA.resolveFor(actor).getText(), formatter);

            if (fechaParseada.getDayOfMonth() != diaDeEntrega) {
                throw new FechaEntregaIncorrectaException(String.format(
                        "La fecha de entrega en la factura [%s] es diferente a la fecha de entrega seleccionada [%s]",
                        LBL_FECHA_ENTREGA.resolveFor(actor).getText(),
                        fechaEntrega));
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        actor.attemptsTo(
                Click.on(BTN_METODO_PAGO)
        );

        String validacionProductoEnFacturacion = LBL_PRODUCTO_FACTURACION.resolveFor(actor).getText();

        if (!validacionProductoEnFacturacion.contains(producto)) {
            throw new ProductoNoCoincideException(String.format(
                    "El producto en facturación [%s] no coincide con el producto del Excel [%s]",
                    validacionProductoEnFacturacion,
                    producto));
        }

        String validacionEmailEnFacturacion = LBL_EMAIL_FACTURACION.resolveFor(actor).getText();

        if (!validacionEmailEnFacturacion.equals(correo)) {
            throw new DatosClienteNoCoincidenException(String.format(
                    "El correo de la factura [%s] no coincide con el correo ingresado [%s]",
                    validacionEmailEnFacturacion,
                    correo
            ));
        }

        String validacionNombreEnFacturacion = LBL_NOMBRE_FACTURACION.resolveFor(actor).getText();

        if (!validacionNombreEnFacturacion.equals(String.format("%s %s", nombreExcel, apellidoExcel))) {
            throw new DatosClienteNoCoincidenException(String.format(
                    "El nombre de la factura [%s] no coincide con el nombre ingresado [%s]",
                    validacionNombreEnFacturacion,
                    String.format("%s %s", nombreExcel, apellidoExcel)
            ));
        }

        String validacionTelefonoEnFacturacion = LBL_TELEFONO_FACTURACION.resolveFor(actor).getText();

        if (!(telefonoExcel == null || telefonoExcel.length() != 10)) {
            String telefonoFormateado = telefonoExcel.substring(0, 3) + " " + telefonoExcel.substring(3, 6) + " " + telefonoExcel.substring(6);

            if (!validacionTelefonoEnFacturacion.equals(telefonoFormateado)) {
                throw new DatosClienteNoCoincidenException(String.format(
                        "El telefono de la factura [%s] no coincide con el telefono ingresado [%s]",
                        validacionTelefonoEnFacturacion,
                        telefonoFormateado
                ));
            }
        }
    }

    public static CompletarFacturacionTask conProducto(String producto, String correo) {
        return new CompletarFacturacionTask(producto, correo);
    }
}
