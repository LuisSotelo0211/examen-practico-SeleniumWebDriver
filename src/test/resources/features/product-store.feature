# language: es

Característica: Product - Store

  Esquema del escenario: Validación del precio de un producto - <caso>
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando navego a la categoria "<categoria>" y subcategoria "Men"
    Y selecciono el producto "Hummingbird Printed T-Shirt"
    Y selecciono la talla "XL"
    Y selecciono el color "Negro"
    Y ingreso 2 unidades del producto
    Y doy clic en el botón "Añadir al carrito"
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | caso                   | usuario                      | clave             | categoria |
      | Compra válida          | STORE_USUARIO | STORE_CLAVE | Clothes   |
      | Credenciales inválidas | qa-invalido@example.test     | claveincorrecta   | Clothes   |
      | Categoría inexistente  | STORE_USUARIO | STORE_CLAVE | Autos     |
