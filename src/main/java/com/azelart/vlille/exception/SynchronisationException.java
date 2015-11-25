package com.azelart.vlille.exception;

/**
 * Synchronisation exception.
 * @author Corentin Azelart
 */
public class SynchronisationException extends Exception {

    /**
     * Default constructor.
     */
    public SynchronisationException() {
        super();
    }

    /**
     * Message constructor.
     * @param message is the message.
     */
    public SynchronisationException(final String message) {
        super(message);
    }
}
