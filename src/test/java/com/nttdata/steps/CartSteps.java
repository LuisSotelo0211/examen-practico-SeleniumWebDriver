package com.nttdata.steps;

import com.nttdata.page.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class CartSteps {

    private final WebDriverWait wait;

    public CartSteps(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int obtenerCantidad() {
        String valor = visible(CartPage.cantidad).getDomProperty("value");
        return Integer.parseInt(valor);
    }

    public BigDecimal obtenerPrecioUnitario() {
        return leerImporte(CartPage.precioUnitario);
    }

    public BigDecimal obtenerImporteLinea() {
        return leerImporte(CartPage.importeLinea);
    }

    public BigDecimal obtenerTotalConImpuestos() {
        return leerImporte(CartPage.totalConImpuestos);
    }

    private WebElement visible(By localizador) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
    }

    private BigDecimal leerImporte(By localizador) {
        String texto = visible(localizador).getText();
        String importe = texto.replace("S/", "").replace('\u00A0', ' ').trim();
        return new BigDecimal(importe);
    }
}