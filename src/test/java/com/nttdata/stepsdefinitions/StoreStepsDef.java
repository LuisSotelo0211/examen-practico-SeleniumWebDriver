package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.steps.*;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import java.math.BigDecimal;
import java.time.Duration;

public class StoreStepsDef {

    private StoreSteps storeSteps;
    private LoginSteps loginSteps;
    private AccountSteps accountSteps;
    private CategorySteps categorySteps;
    private ProductListSteps productListSteps;
    private ProductSteps productSteps;
    private int cantidadSolicitada;

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_página_de_la_tienda() {

        DriverManager.iniciarDriver();

        storeSteps = new StoreSteps(
                DriverManager.getDriver()
        );

        storeSteps.abrirPagina(
                "https://qalab.bensg.com/store"
        );
    }

    @Dado("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String usuario, String clave) {
        storeSteps.clickIniciarSesion();

        loginSteps = new LoginSteps(DriverManager.getDriver());
        loginSteps.escribirCorreo(resolverDato(usuario));
        loginSteps.escribirClave(resolverDato(clave));
        loginSteps.iniciarSesion();

        accountSteps = new AccountSteps(DriverManager.getDriver());
        boolean autenticado = accountSteps.estaVisibleCerrarSesion();

        org.junit.Assert.assertTrue(
                "El usuario no pudo autenticarse correctamente",
                autenticado
        );
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {

        categorySteps = new CategorySteps(
                DriverManager.getDriver()
        );

        categorySteps.ingresarCategoria(categoria);
        categorySteps.ingresarSubcategoria(subcategoria);
    }

    @Cuando("selecciono el producto {string}")
    public void selecciono_el_producto(String nombreProducto) {

        productListSteps = new ProductListSteps(
                DriverManager.getDriver()
        );

        productListSteps.seleccionarProducto(nombreProducto);
    }

    @Cuando("selecciono la talla {string}")
    public void selecciono_la_talla(String talla) {

        productSteps = new ProductSteps(
                DriverManager.getDriver()
        );

        productSteps.seleccionarTalla(talla);
    }

    @Cuando("selecciono el color {string}")
    public void selecciono_el_color(String color) {

        productSteps.seleccionarColor(color);
    }

    @Cuando("ingreso {int} unidades del producto")
    public void ingreso_unidades_del_producto(int cantidad) {
        this.cantidadSolicitada = cantidad;
        productSteps.ingresarCantidad(cantidad);
    }

    @Cuando("doy clic en el botón {string}")
    public void doy_clic_en_el_boton(String boton) {
        productSteps.agregarAlCarrito();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {

        String mensaje =
                productSteps.obtenerMensajeConfirmacion();

        org.junit.Assert.assertTrue(
                "No se mostró el mensaje esperado de confirmación",
                mensaje.contains(
                        "Producto añadido correctamente a su carrito de compra"
                )
        );
    }

    @Entonces("valido en el popup que el monto total sea calculado correctamente")
    public void validoTotalPopup() {
        BigDecimal precioUnitario = productSteps.obtenerPrecioUnitarioPopup();
        BigDecimal esperado = precioUnitario.multiply(BigDecimal.valueOf(cantidadSolicitada));
        BigDecimal mostrado = productSteps.obtenerTotalPopup();

        org.junit.Assert.assertTrue(
                "Para " + cantidadSolicitada + " unidades a S/ " + precioUnitario
                        + ", se esperaba S/ " + esperado + " y el popup mostró S/ " + mostrado,
                esperado.compareTo(mostrado) == 0
        );
    }

    @Cuando("finalizo la compra")
    public void finalizoLaCompra() {
        productSteps.finalizarCompra();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                .withMessage("Se esperaba que la página tuviera el título 'Carrito'")
                .until(ExpectedConditions.titleIs("Carrito"));
    }

    @Entonces("vuelvo a validar el calculo de precios en el carrito")
    public void validoPreciosEnElCarrito() {
        CartSteps carrito = new CartSteps(DriverManager.getDriver());

        Assert.assertEquals("Cantidad de unidades en el carrito",
                cantidadSolicitada, carrito.obtenerCantidad());

        BigDecimal precio = carrito.obtenerPrecioUnitario();
        BigDecimal esperado = precio.multiply(BigDecimal.valueOf(cantidadSolicitada));
        BigDecimal importeLinea = carrito.obtenerImporteLinea();
        BigDecimal totalCarrito = carrito.obtenerTotalConImpuestos();

        Assert.assertTrue(
                "Importe de la línea: se esperaba S/ " + esperado
                        + " y se mostró S/ " + importeLinea,
                esperado.compareTo(importeLinea) == 0
        );

        Assert.assertTrue(
                "Total del carrito: se esperaba S/ " + esperado
                        + " y se mostró S/ " + totalCarrito,
                esperado.compareTo(totalCarrito) == 0
        );
    }

    private String resolverDato(String dato) {
        if (!dato.equals("STORE_USUARIO") && !dato.equals("STORE_CLAVE")) {
            return dato;
        }

        String valor = System.getenv(dato);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Falta configurar la variable " + dato);
        }
        return valor;
    }

    @After
    public void cerrarNavegador() {
        DriverManager.cerrarDriver();
    }

}