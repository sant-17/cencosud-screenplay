package com.co.easy.userInterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class InicioUI {

    public static final Target TXT_BUSCADOR = Target
            .the("Buscador Inicio")
            .locatedBy("//input[@placeholder='¿Qué estás buscando?']");

    public InicioUI() {
        throw new UnsupportedOperationException("Utility class");
    }
}
