package com.co.easy.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class FacturacionUI {

    public static final Target TBL_CLIENTE = Target
            .the("Tabla Datos Cliente")
            .locatedBy("//div[@id='client-profile-data']");

    public static final Target LBL_PRODUCTO_FACTURACION = Target
            .the("Producto Facturacion")
            .locatedBy("//span[@class='fn product-name']");

    public static final Target TXT_NOMBRE = Target
            .the("Nombre Cliente Facturacion")
            .locatedBy("//input[@id='client-first-name']");

    public static final Target TXT_APELLIDO = Target
            .the("Apellidos Cliente Facturacion")
            .locatedBy("//input[@id='client-last-name']");

    public static final Target SELECT_TIPO_DOCUMENTO = Target
            .the("Tipo Documento Cliente Facturacion")
            .locatedBy("//select[@id='select-document']");

    public static final Target OPT_TIPO_DOCUMENTO = Target
            .the("Tipo Documento Seleccionado Cliente Facturacion")
            .locatedBy("//option[text()='{0}']");

    public static final Target TXT_NUMERO_DOCUMENTO = Target
            .the("Numero Documento Cliente Facturacion")
            .locatedBy("//input[@id='client-new-document']");

    public static final Target TXT_TELEFONO = Target
            .the("Telefono Cliente Facturacion")
            .locatedBy("//input[@id='client-phone']");

    public static final Target BTN_METODO_ENTREGA = Target
            .the("Boton Metodo De Entrega")
            .locatedBy("//button[@id='go-to-shipping']");

    public static final Target LBL_CALLE = Target
            .the("Texto Calle Entrega")
            .locatedBy("//span[@class='street']");

    public static final Target LBL_CIUDAD = Target
            .the("Texto Ciudad Entrega")
            .locatedBy("//span[@class='city']");

    public static final Target LBL_DEPARTAMENTO = Target
            .the("Texto Departamento Entrega")
            .locatedBy("//span[@class='state']");

    public static final Target TXT_NOMBRE_RECIBE = Target
            .the("Nombre Quien Recibe Facturacion")
            .locatedBy("//input[@id='ship-receiverName']");

    public static final Target BTN_FECHA_ENTREGA = Target
            .the("Boton Fecha De Entrega")
            .locatedBy("//button[text()='Elija una fecha de entrega']");

    public static final Target BTN_MES_SIGUIENTE = Target
            .the("Boton Mes Siguiente Fecha De Entrega")
            .locatedBy("//button[text()='Next month']");

    public static final Target BTN_DIA_ENTREGA = Target
            .the("Dia Fecha De Entrega")
            .locatedBy("//div[contains(@class, 'day--keyboard-selected')]");

    public static final Target LBL_FECHA_ENTREGA = Target
            .the("Fecha de entrega")
            .locatedBy("//span[contains(@class,'shp-selected-date')]");

    public static final Target BTN_METODO_PAGO = Target
            .the("Boton Metodo Pago Factura")
            .locatedBy("//button[@id='btn-go-to-payment']");

    public static final Target LBL_EMAIL_FACTURACION = Target
            .the("Texto Email Cliente Facturacion")
            .locatedBy("//span[@class='email']");

    public static final Target LBL_NOMBRE_FACTURACION = Target
            .the("Texto Nombre Cliente Facturacion")
            .locatedBy("//span[@class='name']");

    public static final Target LBL_TELEFONO_FACTURACION = Target
            .the("Texto Telefono Cliente Facturacion")
            .locatedBy("//span[@class='tel']");

    public FacturacionUI() {
        throw new UnsupportedOperationException("Utility class");
    }
}
