package com.exubero.jbehave.model;

public final class StringConverter {
    public static String convertToTitle(String pathComponent) {
        return pathComponent.replace("_", " ").replace(".story", "");
    }
}
