package es.alberrogu.prueba.model;

import es.alberrogu.prueba.annotations.CsvOrder;
import lombok.Data;

@Data
public class Stock {

	@CsvOrder(position = 0)
	private Long sizeId;

	@CsvOrder(position = 1)
	private Long quantity;

}
