package es.alberrogu.prueba;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import es.alberrogu.prueba.model.Product;
import es.alberrogu.prueba.model.Size;
import es.alberrogu.prueba.model.Stock;
import es.alberrogu.prueba.util.CsvLoader;
import lombok.Data;

@Component
public class FindProducts {

	@Value("classpath:data/product.csv")
	private Resource resourceProduct;

	@Value("classpath:data/size.csv")
	private Resource resourceSize;

	@Value("classpath:data/stock.csv")
	private Resource resourceStock;

	public List<Long> getAllOrdered() {
		var mapProductSizes = getMapProductSizes();
		var products = new TreeSet<>(Comparator.comparing(Product::getSequence));
		CsvLoader.loadCsv(resourceProduct, Product.class, product -> {
			if (mapProductSizes.containsKey(product.getId())
					&& isProductVisible(mapProductSizes.get(product.getId()))) {
				products.add(product);
			}
		});
		return products.stream().map(Product::getId).toList();
	}

	private Boolean isProductVisible(ProductSizes productSizes) {
		if (Boolean.TRUE.equals(productSizes.getSpecialSizes())) {
			return productSizes.getNormalStock() && productSizes.getSpecialSizesStock();
		} else {
			return productSizes.getNormalStock();
		}

	}

	private Map<Long, ProductSizes> getMapProductSizes() {
		var sizesWithStock = getAllSizeWithStock();
		Map<Long, ProductSizes> mapProductSizes = new HashMap<>();
		CsvLoader.loadCsv(resourceSize, Size.class, size -> {
			final ProductSizes productSize;
			if (mapProductSizes.containsKey(size.getProductId())) {
				productSize = mapProductSizes.get(size.getProductId());
			} else {
				productSize = new ProductSizes();
				mapProductSizes.put(size.getProductId(), productSize);
			}
			Consumer<ProductSizes> setTrueValueToStock;
			if (Boolean.TRUE.equals(size.getSpecial())) {
				productSize.setSpecialSizes(Boolean.TRUE);
				setTrueValueToStock = product -> product.setSpecialSizesStock(true);
			} else {
				setTrueValueToStock = product -> product.setNormalStock(true);

			}
			if (Boolean.TRUE.equals(size.getBackSoon()) || sizesWithStock.contains(size.getId())) {
				setTrueValueToStock.accept(productSize);
			}
		});
		return mapProductSizes;
	}

	private Set<Long> getAllSizeWithStock() {
		var sizesWithStock = new HashSet<Long>();
		CsvLoader.loadCsv(resourceStock, Stock.class, stock -> {
			if (stock.getQuantity() > 0)
				sizesWithStock.add(stock.getSizeId());
		});
		return sizesWithStock;
	}

}

@Data
class ProductSizes {
	private Boolean normalStock = Boolean.FALSE;
	private Boolean specialSizes = Boolean.FALSE;
	private Boolean specialSizesStock = Boolean.FALSE;

}
