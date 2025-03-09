package com.novasparkle.spelling;

import android.text.Html;
import android.text.Spanned;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rule implements Serializable {
    private String ruleName;
    private final List<String> description, example;
    public Rule() {
        this.description = new ArrayList<>();
        this.example = new ArrayList<>();
    }
    public Spanned getRuleName() {
        return Html.fromHtml(ruleName, Html.FROM_HTML_MODE_COMPACT);
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getExample() {
        return example;
    }

    public void addMainString(String string) {
        this.description.add(string);
    }
    public void addExampleString(String string) {
        this.example.add(string);
    }
}
