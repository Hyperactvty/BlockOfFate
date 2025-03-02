package com.hyperactvty.blockoffate;

public class LoadsIncomplete extends Exception {
    // Default constructor with a generic error message
    public LoadsIncomplete() {
        super("The required loads are incomplete.");
    }

    // Constructor that allows a custom message
    public LoadsIncomplete(String message) {
        super(message);
    }

    // Constructor that allows a custom message and a cause
    public LoadsIncomplete(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor that allows a cause
    public LoadsIncomplete(Throwable cause) {
        super(cause);
    }
}
