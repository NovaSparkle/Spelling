package com.novasparkle.spelling;

import static com.novasparkle.spelling.Utils.toDIP;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.linear);


        XmlPullParser xpp = this.getResources().getXml(R.xml.rules);
        RuleParser ruleParser = new RuleParser();

        if (ruleParser.parse(xpp)) ruleParser.getCategories().forEach(r -> Log.d("Rule", r.toString()));
        Log.d("INFO", ruleParser.getCategories().toString());
        Typeface font = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = this.getResources().getFont(R.font.timesnewromanpsmt);
        }


        for (Chapter chapter : Chapter.values()) {
            CardView chapterCard = new CardView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(toDIP(this, 350),
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, toDIP(this, 20), 0, toDIP(this, 30));

            chapterCard.setLayoutParams(params);
            chapterCard.setElevation(toDIP(this, 24));
            chapterCard.setRadius(toDIP(this, 14));

            LinearLayout categoryButtonsContainer = new LinearLayout(this);
            categoryButtonsContainer.setLayoutParams(new LinearLayout.LayoutParams(chapterCard.getLayoutParams().width,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            categoryButtonsContainer.setGravity(Gravity.CENTER_HORIZONTAL);
            categoryButtonsContainer.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams((int) (chapterCard.getLayoutParams().width * 0.85),
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonParams.setMargins(0, toDIP(this, 10), 0, toDIP(this, 10));

            TextView cardHeader = new TextView(this);

            cardHeader.setText(chapter.getName());
            cardHeader.setTypeface(font);
            cardHeader.setGravity(Gravity.CENTER);
            cardHeader.setTextSize(18);
            cardHeader.setLayoutParams(buttonParams);

            TextView divider = new TextView(this);
            ViewGroup.LayoutParams dividerParams =
                    new ViewGroup.LayoutParams(cardHeader.getLayoutParams().width, toDIP(this, 3));
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(R.color.black);

            categoryButtonsContainer.addView(cardHeader);
            categoryButtonsContainer.addView(divider);

            for (Category category : ruleParser.getCategories()) {
                if (category.getChapter().equals(chapter)) {
                    Button categoryButton = this.createButton(category, category.getCategoryName(), font);

                    categoryButton.setLayoutParams(buttonParams);
                    categoryButtonsContainer.addView(categoryButton);
                }
            }
            chapterCard.addView(categoryButtonsContainer);
            layout.addView(chapterCard);
        }

    }
    private Button createButton(Serializable serializable, String text, Typeface font) {
        Button categoryButton = new Button(this);
        categoryButton.setText(text);
        categoryButton.setTypeface(font);

        categoryButton.setTextColor(Color.argb(200, 255, 255, 255));
        categoryButton.setTextSize(14);
        categoryButton.setOnClickListener(v -> Utils.switchActivity(this, CategoryActivity.class, serializable));
        return categoryButton;
    }
}