package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductPage {

    public static By tallaSelect =
            By.id("group_1");

    public static By colorPorNombre(String color) {
        return By.cssSelector(
                "label[aria-label='" + color + "']"
        );
    }

    public static By cantidadInput =
            By.id("quantity_wanted");

    public static By agregarCarritoButton =
            By.cssSelector(
                    "button[data-button-action='add-to-cart']"
            );

    public static By mensajeConfirmacion =
            By.id("myModalLabel");

    public static final By precioUnitarioPopup =
            By.cssSelector("#blockcart-modal .product-price");

    public static final By totalPopup =
            By.cssSelector("#blockcart-modal .product-total .value");

    public static final By finalizarCompraPopup =
            By.cssSelector("#blockcart-modal a[href*='/carrito?action=show']");
}