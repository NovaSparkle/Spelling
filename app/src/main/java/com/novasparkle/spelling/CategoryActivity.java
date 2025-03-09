package com.novasparkle.spelling;

import static com.novasparkle.spelling.Utils.toDIP;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Category category = (Category) Objects.requireNonNull(this.getIntent().getExtras()).getSerializable(Category.class.getSimpleName());
        assert category != null;

        LinearLayout mainLinear = this.findViewById(R.id.linear);

        CardView card = new CardView(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(toDIP(this, 350),
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, toDIP(this, 20), 0, toDIP(this, 30));

        card.setLayoutParams(params);
        card.setElevation(toDIP(this, 24));
        card.setRadius(toDIP(this, 14));

        LinearLayout categoryButtonsContainer = new LinearLayout(this);
        categoryButtonsContainer.setLayoutParams(new LinearLayout.LayoutParams(card.getLayoutParams().width,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        categoryButtonsContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        categoryButtonsContainer.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams((int) (card.getLayoutParams().width * 0.85),
                LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, toDIP(this, 10), 0, toDIP(this, 10));
        Typeface font = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = this.getResources().getFont(R.font.timesnewromanpsmt);
        }
        TextView cardHeader = new TextView(this);
        cardHeader.setText(category.getCategoryName());
        cardHeader.setGravity(Gravity.CENTER);
        cardHeader.setTypeface(font);
        cardHeader.setTextSize(18);
        cardHeader.setLayoutParams(buttonParams);

        categoryButtonsContainer.addView(cardHeader);
        categoryButtonsContainer.addView(Utils.divide(this, cardHeader.getLayoutParams().width));

        for (Rule rule : category.getRules()) {
            Button categoryButton = new Button(this);
            categoryButton.setText(rule.getRuleName());
            categoryButton.setTypeface(font);
            categoryButton.setTextColor(Color.argb(200, 255, 255, 255));
            categoryButton.setTextSize(14);
            categoryButton.setOnClickListener(v -> Utils.switchActivity(this, RuleActivity.class, rule));

            categoryButton.setLayoutParams(buttonParams);
            categoryButtonsContainer.addView(categoryButton);
        }
        card.addView(categoryButtonsContainer);
        mainLinear.addView(card);
    }
}