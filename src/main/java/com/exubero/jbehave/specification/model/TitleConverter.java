package com.exubero.jbehave.specification.model;

public final class TitleConverter {
    public static String convertToTitle(String pathComponent) {
        return pathComponent.replace("_", " ").replace(".story", "");
    }
}
