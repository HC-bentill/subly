package com.subly.domain.shared;

/** Thrown when an entity is asked to move to a status its state machine does not allow. */
public class InvalidStateTransitionException extends RuntimeException {

    public InvalidStateTransitionException(Enum<?> from, Enum<?> to) {
        super("Cannot transition " + from.getDeclaringClass().getSimpleName()
                + " from " + from + " to " + to);
    }
}
