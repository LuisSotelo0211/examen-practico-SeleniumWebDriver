package com.nttdata.steps;

import com.nttdata.page.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class ProductSteps {

    private WebDriver driver;

    public ProductSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void seleccionarTalla(String talla) {

        Select select = new Select(
                driver.findElement(ProductPage.tallaSelect)
        );

        select.selectByVisibleText(talla);
    }

    public void seleccionarColor(String color) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        ProductPage.colorPorNombre(color)
                )
        ).click();

        if (color.equalsIgnoreCase("Negro")) {
            wait.until(
                    ExpectedConditions.urlContains("11-color-negro")
            );
        }

        if (color.equalsIgnoreCase("Blanco")) {
            wait.until(
                    ExpectedConditions.urlContains("8-color-blanco")
            );
        }
    }

    public void ingresarCantidad(int cantidad) {

        WebElement cantidadInput =
                driver.findElement(ProductPage.cantidadInput);

        cantidadInput.click();
        cantidadInput.sendKeys(Keys.CONTROL + "a");
        cantidadInput.sendKeys(String.valueOf(cantidad));
        cantidadInput.sendKeys(Keys.TAB);

        String cantidadActual =
                cantidadInput.getAttribute("value");

        org.junit.Assert.assertEquals(
                "La cantidad del producto no se actualizó correctamente",
                String.valueOf(cantidad),
                cantidadActual
        );
    }

    public void agregarAlCarrito() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        ProductPage.agregarCarritoButton
                )
        ).click();
    }

    public String obtenerMensajeConfirmacion() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        ProductPage.mensajeConfirmacion
                )
        ).getText();
    }

    public BigDecimal obtenerPrecioUnitarioPopup() {
        return leerImporte(ProductPage.precioUnitarioPopup);
    }

    public BigDecimal obtenerTotalPopup() {
        return leerImporte(ProductPage.totalPopup);
    }

    private BigDecimal leerImporte(By localizador) {
        String texto = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(localizador))
                .getText();

        String importe = texto.replace("S/", "").replace('\u00A0', ' ').trim();
        return new BigDecimal(importe);
    }

    public void finalizarCompra() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(ProductPage.finalizarCompraPopup))
                .click();
    }
}