# Algoritmo de visibilidad

## Estructura de datos
- Stocks: Se ha utilizado un hashSet para almacenar el codigo de la talla que tenga una cantidad mayor que 0. Se ha usado hashset porque contains es O(1).
- Sizes: Para sizes se ha creado un HashMap con un objeto personalizado con la información mínima para saber si se cumplen las condiciones para que se muestre la talla. La key es el id del producto.
- Products: Se almacena en un TreeSet con la ordenación de la secuencia y solo se añanden al treeset los que cumplen con la visibilidad

## Complejidad temporal del algoritmo
- El método getAllSizeWithStock(): Tiene complejidad O(M)
- El método getMapProductSizes(): O(N + M), teniendo en cuenta M como la complejidad del método anterior
- El método getAllOrdered(): O(N + M + P + K log K). Hay que obtener el método previo y además añadirlo a un TreeSet con complejidad O(K log K) y luego hay que obtener el valor del id del producto.
