package com.sexton.example.service;

import com.sexton.example.exception.CsvSerializationException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class CsvBeanWriterService {
    public <T> void writeBeansToResponse(final HttpServletResponse httpServletResponse,
                                         final MappingStrategy<T> mappingStrategy,
                                         final List<T> beans)
            throws CsvSerializationException {
        // TODO: Can be moved to a filter or interceptor.
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=data.csv";
        httpServletResponse.setContentType("text/csv");
        httpServletResponse.setHeader(headerKey, headerValue);

        try {
            final StatefulBeanToCsv<T> statefulBeanToCsv = new StatefulBeanToCsvBuilder<T>(httpServletResponse.getWriter())
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(',')
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            statefulBeanToCsv.write(beans);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
            throw new CsvSerializationException();
        }
    }
}
