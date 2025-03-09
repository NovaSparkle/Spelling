package com.novasparkle.spelling;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RuleParser {
    private final List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public RuleParser() {
        categories = new ArrayList<>();
    }

    public boolean parse(XmlPullParser xpp) {
        Rule currentRule = null;
        Category currentCategory = null;
        boolean inRule = false;
        String textValue = "";

        try {
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();

                switch (eventType) {

                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("rule")) {
                            inRule = true;
                            currentRule = new Rule();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        if (textValue.equalsIgnoreCase("null")) textValue = "";
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("categoryName")) {
                            currentCategory = new Category(textValue);

                        } else if (tagName.equalsIgnoreCase("chapter")) {
                            assert currentCategory != null;
                            currentCategory.setChapter(Chapter.valueOf(textValue));

                        } else if (tagName.equalsIgnoreCase("category")) {
                            this.categories.add(currentCategory);

                        } else if (inRule) {

                            if (tagName.equalsIgnoreCase("rule")) {
                                assert currentCategory != null;
                                currentCategory.addRule(currentRule);
                                inRule = false;

                            } else if (tagName.equalsIgnoreCase("ruleName")) {
                                currentRule.setRuleName(textValue);

                            } else if (tagName.equalsIgnoreCase("mainString")) {
                                currentRule.addMainString(textValue);

                            } else if (tagName.equalsIgnoreCase("exampleString")) {
                                currentRule.addExampleString(textValue);
                            }
                            break;

                        }
                    default:
                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
