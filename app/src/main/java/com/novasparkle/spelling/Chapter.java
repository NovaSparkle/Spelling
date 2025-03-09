package com.novasparkle.spelling;

import java.io.Serializable;

public enum Chapter implements Serializable {
    SPELLING("Орфография"),
    PUNCTUATION("Пунктуация");
    private final String typeName;
    Chapter(String typeName) {
        this.typeName = typeName;
    }
    public String getName() {
        return this.typeName;
    }
}
