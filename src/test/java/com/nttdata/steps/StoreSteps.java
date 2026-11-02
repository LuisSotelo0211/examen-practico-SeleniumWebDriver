package com.nttdata.steps;

import com.nttdata.page.StorePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoreSteps {

    private WebDriver driver;

    public StoreSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina(String url) {
        driver.get(url);
    }

    public boolean estaVisibleIniciarSesion() {
        return driver.findElement(
                StorePage.iniciarSesionLink
        ).isDisplayed();
    }

    public void clickIniciarSesion() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        StorePage.iniciarSesionLink
                )
        ).click();
    }
}