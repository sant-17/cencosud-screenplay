package com.co.easy.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ValidarProducto implements Question<Boolean> {
    protected Target element;
    protected String texto;

    public ValidarProducto(Target element, String texto) {
        this.element = element;
        this.texto = texto;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return element.resolveFor(actor).getText().contains(texto);
    }

    public static Question<Boolean> value(Target element, String texto) {
        return new ValidarProducto(element, texto);
    }
}
