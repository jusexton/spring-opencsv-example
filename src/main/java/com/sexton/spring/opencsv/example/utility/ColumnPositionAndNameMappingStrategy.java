package com.sexton.spring.opencsv.example.utility;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;

/**
 * Strategy is used for mapping beans by their column name and position.
 *
 * @param <T>
 */
public class ColumnPositionAndNameMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
    @Override
    public String[] generateHeader() {
        final int numColumns = findMaxFieldIndex();
        if (!isAnnotationDriven() || numColumns == -1) {
            return super.generateHeader();
        }

        return IntStream.range(0, numColumns)
                .mapToObj(this::findField)
                .map(this::extractHeaderName)
                .toArray(String[]::new);

        // Imperative Style
        // headers = new String[numColumns + 1];
        //
        // for (int i = 0; i <= numColumns; i++) {
        //     final BeanField beanField = findField(i);
        //     String columnHeaderName = extractHeaderName(beanField);
        //     headers[i] = columnHeaderName;
        // }
        // return headers;
    }

    private String extractHeaderName(final BeanField beanField) {
        // Bean is not being bound to a column name
        if (beanField == null || beanField.getField() == null || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
            return StringUtils.EMPTY;
        }

        final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];
        return bindByNameAnnotation.column();
    }
}
