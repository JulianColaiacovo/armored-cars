package com.jcolaiacovo.armored.cars.domain.transformer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julian on 09/04/2017.
 */
@Component
public abstract class AbstractApiTransformer<T, R> {

    public abstract T transform(R value);

    public abstract R transformToDTO(T value);

    public List<T> transformAll(List<R> values) {
        return values.stream().map(this::transform).collect(Collectors.toList());
    }

    public List<R> transformToDTOAll(List<T> values) {
        return values.stream().map(this::transformToDTO).collect(Collectors.toList());
    }

}
