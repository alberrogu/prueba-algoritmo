## Algoritmo de visibilidad

# Estructura de datos
- Stocks: Se ha utilizado un hashSet para almacenar el codigo de la talla que tenga una cantidad mayor que 0. Se ha usado hashset porque contains es O(1).
- Sizes: Para sizes se ha creado un HashMap con un objeto personalizado con la información mínima para saber si se cumplen las condiciones para que se muestre la talla. La key es el id del producto.
- Products: Se almacena en un TreeSet con la ordenación de la secuencia y solo se añanden al treeset los que cumplen con la visibilidad

# Complejidad temporal del algoritmo
