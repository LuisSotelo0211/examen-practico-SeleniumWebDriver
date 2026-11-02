package com.nttdata.steps;

import com.nttdata.page.AccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountSteps {

    private WebDriver driver;

    public AccountSteps(WebDriver driver) {
        this.driver = driver;
    }

    public boolean estaVisibleCerrarSesion() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AccountPage.cerrarSesionLink
                )
        ).isDisplayed();
    }
}