# Regresión web de la tienda QALab

Pruebas de interfaz con Java 21, Selenium WebDriver, Cucumber y JUnit 4. El proyecto usa Page Object Model para validar el inicio de sesión, la navegación por categorías y el cálculo de precios en la [tienda QALab](https://qalab.bensg.com/store).

## Ejecutar

1. Instala JDK 21, Maven y Google Chrome. Selenium gestiona el driver.
2. Configura las variables de entorno `STORE_USUARIO` y `STORE_CLAVE` con las credenciales de una cuenta de prueba. Empieza con el carrito vacío.
3. Desde la raíz del proyecto, ejecuta:

```sh
mvn test
```

En IntelliJ también puedes ejecutar `RunnerTest`. Para grabar el video, añade `-DmodoVideo=true` a sus opciones de la JVM. Esto agrega una pausa de dos segundos después de cada paso ejecutado.

## Resultados esperados

| Escenario | Comportamiento esperado |
| --- | --- |
| Compra válida | Agrega dos unidades, valida el total del popup y comprueba los precios en el carrito. |
| Credenciales inválidas | Falla en el login y omite los pasos posteriores. |
| Categoría inexistente | Inicia sesión, falla al buscar `Autos` y omite los pasos posteriores. |

La consigna espera **un escenario aprobado y dos fallidos**. Maven indicará un fallo de pruebas cuando se produzcan esos dos fallos previstos. El detalle de los pasos queda en `target/cucumber-report.json`.
