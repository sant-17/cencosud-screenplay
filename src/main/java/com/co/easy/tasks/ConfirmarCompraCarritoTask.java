package com.co.easy.tasks;

import com.co.easy.exceptions.ProductoNoCoincideException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.co.easy.userInterfaces.CarritoUI.BTN_FINALIZAR_COMPRA;
import static com.co.easy.userInterfaces.CarritoUI.LBL_PRODUCTO_CARRITO;

public class ConfirmarCompraCarritoTask implements Task {

    private String producto;

    public ConfirmarCompraCarritoTask(String producto) {
        this.producto = producto;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String validacionProductoEnCarrito = LBL_PRODUCTO_CARRITO.resolveFor(actor).getText();

        if (!validacionProductoEnCarrito.contains(producto)) {
            throw new ProductoNoCoincideException(String.format(
                    "El producto en el carrito [%s] no coincide con el producto del Excel [%s]",
                    validacionProductoEnCarrito,
                    producto));
        }

        actor.attemptsTo(
                Click.on(BTN_FINALIZAR_COMPRA)
        );
    }

    public static ConfirmarCompraCarritoTask finalizarCompraConElProducto(String producto) {
        return new ConfirmarCompraCarritoTask(producto);
    }
}
