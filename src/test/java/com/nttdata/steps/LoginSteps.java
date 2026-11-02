package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void escribirCorreo(String correo) {
        driver.findElement(LoginPage.correoInput).sendKeys(correo);
    }

    public void escribirClave(String clave) {
        driver.findElement(LoginPage.claveInput).sendKeys(clave);
    }

    public void iniciarSesion() {
        driver.findElement(LoginPage.iniciarSesionButton).click();
    }
}