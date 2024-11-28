package com.co.easy.stepDefinitions;

import com.co.easy.tasks.AgregarAlCarritoTask;
import com.co.easy.tasks.BuscarProductoTask;
import com.co.easy.tasks.CompletarFacturacionTask;
import com.co.easy.tasks.ConfirmarCompraCarritoTask;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class AgregarProductoStepDefinition {

    @Before
    public void setStage() {
        setTheStage(new OnlineCast());
    }

    @Dado("Que me encuentro en la pagina {string}")
    public void queMeEncuentroEnLaPagina(String url) {
        WebDriverManager.chromedriver().setup();
        theActorCalled("robot").wasAbleTo(Open.url(url));

    }

    @Cuando("busco el producto {string}")
    public void buscoElProducto(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                BuscarProductoTask.conElProducto(producto)
        );
    }

    @Y("Agrego el producto {string} al carrito con el correo {string}")
    public void agregoElProductoProductoAlCarritoConElCorreoCorreo(String producto, String correo) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AgregarAlCarritoTask.conElCorreoYProducto(correo, producto)
        );
    }

    @Y("Visualizo {string} en el carrito")
    public void visualizoProductoEnElCarrito(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConfirmarCompraCarritoTask.finalizarCompraConElProducto(producto)
        );
    }

    @Entonces("Llenare los datos de facturacion con el correo {string} y producto {string}")
    public void llenareLosDatosDeFacturacionConElCorreoCorreoYProductoProducto(String correo, String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CompletarFacturacionTask.conProducto(producto, correo)
        );
    }
}
