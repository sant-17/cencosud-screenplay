package com.co.easy.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CarritoUI {

    public static final Target LBL_PRODUCTO_CARRITO = Target
            .the("Texto De Producto en Carrito")
            .locatedBy("//div[contains (@class, 'itemInfoName')]/a");

    public static final Target BTN_FINALIZAR_COMPRA = Target
            .the("Boton Finalizar Compra")
            .locatedBy("//div/span[text()='Finalizar compra']");

    public CarritoUI() {
        throw new UnsupportedOperationException("Utility class");
    }
}
