package org.bany.exception;

public class NotAllowed extends RuntimeException {
    public NotAllowed(String message) {
        super(message);
    }

    public NotAllowed(){
        super("You're not allowed to do this action");
    }
}
