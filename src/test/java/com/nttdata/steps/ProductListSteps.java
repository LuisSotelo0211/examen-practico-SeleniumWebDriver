package com.nttdata.steps;

import com.nttdata.page.ProductListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductListSteps {

    private WebDriver driver;

    public ProductListSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void seleccionarProducto(String nombreProducto) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        ProductListPage.productoPorNombre(nombreProducto)
                )
        ).click();
    }
}