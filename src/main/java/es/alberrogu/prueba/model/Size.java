package es.alberrogu.prueba.model;

import es.alberrogu.prueba.annotations.CsvOrder;
import lombok.Data;

@Data
public class Size {

	@CsvOrder(position = 0)
	private Long id;

	@CsvOrder(position = 1)
	private Long productId;

	@CsvOrder(position = 2)
	private Boolean backSoon;

	@CsvOrder(position = 3)
	private Boolean special;
}
