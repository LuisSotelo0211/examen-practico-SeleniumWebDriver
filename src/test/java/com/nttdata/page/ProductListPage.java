package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductListPage {

    public static By productoPorNombre(String nombreProducto) {
        return By.cssSelector(
                ".product-title a[href*='hummingbird-printed-t-shirt']"
        );
    }
}