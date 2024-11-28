package com.co.easy.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ProductoUI {

    public static final Target BTN_AGREGAR_CARRITO = Target
            .the("Boton Agregar Producto A Carrito")
            .locatedBy("(//div[contains(@class,'add-to-card')]/div/div[@role='button']) [1]");

    public static final Target TXT_CORREO = Target
            .the("Input Para Asociar Correo")
            .locatedBy("//input[@placeholder='Ingresa aquí tu correo']");

    public static final Target BTN_ENVIAR = Target
            .the("Boton Enviar Correo")
            .locatedBy("//div[text()='Enviar']");

    public static final Target SELECT_DEPARTAMENTO = Target
            .the("Seleccionar Departamento")
            .locatedBy("//div[text()='Departamento']");

    public static final Target OPT_DEPARTAMENTO = Target
            .the("Departamento seleccionado")
            .locatedBy("//div[contains(text(), '{0}')]");

    public static final Target SELECT_CIUDAD = Target
            .the("Seleccionar Ciudad")
            .locatedBy("//div[text()='Ciudad']");

    public static final Target OPT_CIUDAD = Target
            .the("Ciudad seleccionada")
            .locatedBy("//div[contains(text(), '{0}')]");

    public static final Target TXT_DIRECCION = Target
            .the("Input De Direccion De Entrega")
            .locatedBy("//input[@placeholder='Ingresa tu dirección']");

    public static final Target BTN_CONFIRMAR = Target
            .the("Boton Confirmar Metodo De Entrega")
            .locatedBy("//button/div[text()='Confirmar']");

    public static final Target LBL_DIRECCION_ENTREGA_COMPLETO = Target
            .the("Texto De Direccion Entrega")
            .locatedBy("//p[contains(@class, 'delivery-modal') and text()='{0}']");

    public static final Target BTN_NUEVO_AGREGAR_CARRITO = Target
            .the("Boton Nuevo Agregar Producto A Carrito")
            .locatedBy("(//span[text()='Añadir al carrito']) [1]");

    public static final Target LBL_PRODUCTO_EN_MINICARRITO = Target
            .the("Texto De Producto En MiniCarrito")
            .locatedBy("//a[contains(@class, 'minicartLink')]/div");

    public static final Target BTN_FINALIZAR_COMPRA = Target
            .the("Boton En MiniCarrito Finalizar Compra")
            .locatedBy("//span[text()='Finalizar compra']");

    public ProductoUI() {
        throw new UnsupportedOperationException("Utility class");
    }
}
