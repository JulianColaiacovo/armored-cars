package com.jcolaiacovo.armored.cars.domain.converter;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julian on 09/04/2017.
 */
@Component
public abstract class AbstractConverter<T, R> {

    public abstract R convert(T value);

    public List<R> convertAll(List<T> values) {
        return values.stream().map(this::convert).collect(Collectors.toList());
    }

}
