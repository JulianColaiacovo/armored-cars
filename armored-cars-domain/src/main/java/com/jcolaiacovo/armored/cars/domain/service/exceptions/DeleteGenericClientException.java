package com.jcolaiacovo.armored.cars.domain.service.exceptions;

/**
 * Created by Julian on 27/01/2017.
 */
public class DeleteGenericClientException extends RuntimeException {

    public DeleteGenericClientException() {
        super("Can not delete generic client");
    }

}
