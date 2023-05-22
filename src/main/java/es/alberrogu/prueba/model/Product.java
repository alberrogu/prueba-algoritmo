package es.alberrogu.prueba.model;

import es.alberrogu.prueba.annotations.CsvOrder;
import lombok.Data;

@Data
public class Product {
	@CsvOrder(position = 0)
	private Long id;
	@CsvOrder(position = 1)
	private Long sequence;

}
