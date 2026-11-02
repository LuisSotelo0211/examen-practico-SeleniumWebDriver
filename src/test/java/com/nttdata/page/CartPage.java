package com.nttdata.page;

import org.openqa.selenium.By;

public class CartPage {

    public static final By precioUnitario =
            By.cssSelector("#cart .current-price .price");

    public static final By cantidad =
            By.cssSelector("#cart input.js-cart-line-product-quantity");

    public static final By importeLinea =
            By.cssSelector("#cart .product-line-actions .product-price strong");

    public static final By totalConImpuestos =
            By.cssSelector("#cart .cart-summary-totals .cart-total .value");
}