package com.jcolaiacovo.armored.cars.domain.parser;

import org.apache.poi.ss.usermodel.Row;

/***
 *
 * @param <Row>
 * @param <T> target type
 */
public abstract class RowParser<T> {

    public abstract T parse(Row row);

    public abstract boolean isEmpty(Row row);

    public boolean isNotEmpty(Row row) {
        return !this.isEmpty(row);
    }

}
