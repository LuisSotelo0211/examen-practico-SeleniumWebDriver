package com.nttdata.steps;

import com.nttdata.page.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategorySteps {

    private WebDriver driver;

    public CategorySteps(WebDriver driver) {
        this.driver = driver;
    }

    public void ingresarCategoria(String nombre) {
        hacerClicEnEnlace(CategoryPage.categorias, nombre, "categoría");
    }

    public void ingresarSubcategoria(String nombre) {
        hacerClicEnEnlace(CategoryPage.subcategorias, nombre, "subcategoría");
    }

    private void hacerClicEnEnlace(By localizador, String nombre, String tipo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.withMessage("No se encontró una " + tipo + " disponible con el nombre: " + nombre);

        WebElement enlace = wait.until(navegador ->
                navegador.findElements(localizador).stream()
                        .filter(elemento -> elemento.isDisplayed() && elemento.isEnabled())
                        .filter(elemento -> elemento.getText().trim().equalsIgnoreCase(nombre))
                        .findFirst()
                        .orElse(null)
        );

        enlace.click();
    }
}