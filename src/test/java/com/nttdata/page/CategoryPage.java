package com.nttdata.page;

import org.openqa.selenium.By;

public class CategoryPage {

    public static final By categorias = By.cssSelector("li[id^='category-'] > a");
    public static final By subcategorias = By.cssSelector(".block-categories a");
}