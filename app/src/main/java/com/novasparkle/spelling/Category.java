package com.novasparkle.spelling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    private Chapter chapter;
    private final String categoryName;
    private final List<Rule> rules;
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.rules = new ArrayList<>();
    }
    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Chapter getChapter() {
        return chapter;
    }
}
