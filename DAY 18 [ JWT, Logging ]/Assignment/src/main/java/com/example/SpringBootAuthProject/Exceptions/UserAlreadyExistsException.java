package com.example.SpringBootAuthProject.Exceptions;

import java.util.Set;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message, Set<String> suggestions) {
        super(message);
        this.suggestions = suggestions;
    }

    private final Set<String> suggestions;

    public Set<String> getSuggestions() {
        return suggestions;
    }
}

