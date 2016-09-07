package com.exubero.jbehave.specification.model;

final class TitleConverter {
    static String convertToTitle(String pathComponent) {
        return pathComponent.replace("_", " ").replace(".story", "");
    }
}
