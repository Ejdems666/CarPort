package org.cba.controller.exception;

/**
 * Created by adam on 28/05/2017.
 */
public class NonExistentResourceException extends Exception {
    public NonExistentResourceException(String resourceName, int id) {
        super("Non existent "+resourceName+" with id "+id);
    }
}
