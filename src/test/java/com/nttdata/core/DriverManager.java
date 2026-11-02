package com.nttdata.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static WebDriver driver;

    public static void iniciarDriver() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void cerrarDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}