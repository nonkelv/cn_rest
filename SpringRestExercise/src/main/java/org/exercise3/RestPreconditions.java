package org.exercise3;

import org.exercise3.exceptions.BadRequestException;

public class RestPreconditions {

    public static <T> T checkNotNull(T resource) {
        if (resource == null)
            throw new BadRequestException();
        return resource;
    }
}
