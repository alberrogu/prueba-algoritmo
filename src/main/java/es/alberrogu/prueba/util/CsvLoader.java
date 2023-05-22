package es.alberrogu.prueba.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

import es.alberrogu.prueba.annotations.CsvOrder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class CsvLoader {

	public static <T> void loadCsv(Resource resource, Class<T> entityClass, Consumer<T> consumer) {
		try (var br = new BufferedReader(new FileReader(resource.getFile()))) {
			var fields = entityClass.getDeclaredFields();
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = StringUtils.remove(line, " ").split(",");
				var entity = entityClass.getDeclaredConstructor().newInstance();
				for (Field field : fields) {
					if (field.isAnnotationPresent(CsvOrder.class)) {
						int position = field.getAnnotation(CsvOrder.class).position();
						BeanUtilsBean.getInstance().setProperty(entity, field.getName(), values[position]);
					}
				}
				consumer.accept(entity);
			}
		} catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			log.error("Error leyendo fichero", e);
			throw new RuntimeException("No se puede leer el fichero");
		}

	}

}
