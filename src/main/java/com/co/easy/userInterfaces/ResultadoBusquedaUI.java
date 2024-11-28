package com.co.easy.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class ResultadoBusquedaUI {

    public static final Target LBL_PRODUCTO = Target
            .the("Label Producto Resultado Busqueda")
            .locatedBy("//h2/span[contains(@class,'brandName') and contains(text(), '{0}')]");

    public ResultadoBusquedaUI() {
        throw new UnsupportedOperationException("Utility class");
    }
}
