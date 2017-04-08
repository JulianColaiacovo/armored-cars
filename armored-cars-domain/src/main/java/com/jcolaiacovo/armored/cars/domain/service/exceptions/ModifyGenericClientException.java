package com.jcolaiacovo.armored.cars.domain.service.exceptions;

/**
 * Created by Julian on 27/01/2017.
 */
public class ModifyGenericClientException extends RuntimeException {

    public ModifyGenericClientException() {
        super("Can not modify generic client");
    }

}
