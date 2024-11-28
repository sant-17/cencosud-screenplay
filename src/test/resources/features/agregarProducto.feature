#language: es

  Característica: Automarizar e-commerce Cencosud

    @AgregarCarrito

    Esquema del escenario: Agregar producto al carrito

      Dado  Que me encuentro en la pagina '<url>'
      Cuando  busco el producto '<producto>'
      Y Agrego el producto '<producto>' al carrito con el correo '<correo>'
      Y Visualizo '<producto>' en el carrito
      Entonces Llenare los datos de facturacion con el correo '<correo>' y producto '<producto>'

      Ejemplos:
        | url   | producto | correo |
##@externaldata@parametros/Datos.xlsx@AgregarProducto
   |https://www.easy.com.co/   |Nevecon no frost samsung 778 Litros   |santiago17@email.com|
   |https://www.easy.com.co/   |Lavadora Ivy 18kg digital gris Haceb   |santiago17@email.com|
   |https://www.easy.com.co/   |Ahumador Mediano Basik   |santiago17@email.com|
   |https://www.easy.com.co/   |Biblioteca Wick Roma 110x30x24Cm   |santiago17@email.com|
