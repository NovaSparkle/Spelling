package com.novasparkle.spelling;

import static com.novasparkle.spelling.Utils.toDIP;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RuleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rule);

        Bundle extras = this.getIntent().getExtras();
        assert extras != null;
        Rule rule = (Rule) extras.getSerializable(Rule.class.getSimpleName());

        LinearLayout ruleCardLayout = this.findViewById(R.id.ruleLinear);

        Typeface font = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            font = this.getResources().getFont(R.font.timesnewromanpsmt);
        }

        int width = (int) (ruleCardLayout.getLayoutParams().width * 0.88);

        ruleCardLayout.addView(getHeader(R.string.formul, width, font));
        ruleCardLayout.addView(Utils.divide(this, width));

        assert rule != null;
        for (String ruleDescription : rule.getDescription()) {
            TextView ruleString = new TextView(this);
            ruleString.setTypeface(font);
            ruleString.setText(Html.fromHtml(ruleDescription, Html.FROM_HTML_MODE_COMPACT));
            ruleString.setTextSize(14);
            ruleString.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams ruleTextParams =
                    new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            int px = toDIP(this, 10);

            if (!ruleDescription.isEmpty()) ruleTextParams.setMargins(px, px, px, px);

            ruleString.setLayoutParams(ruleTextParams);
            ruleString.setTextColor(Color.argb(175, 255, 255, 255));
            ruleCardLayout.addView(ruleString);
        }

        LinearLayout exampleCardLayout = this.findViewById(R.id.exampleLinear);

        exampleCardLayout.addView(getHeader(R.string.example, width, font));
        exampleCardLayout.addView(Utils.divide(this, width));

        for (String exampleString : rule.getExample()) {
            TextView ruleString = new TextView(this);
            ruleString.setTypeface(font);
            ruleString.setText(Html.fromHtml(exampleString, Html.FROM_HTML_MODE_COMPACT));
            ruleString.setTextSize(12);
            ruleString.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams ruleTextParams =
                    new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            int px = toDIP(this, 10);

            if (!exampleString.isEmpty()) ruleTextParams.setMargins(px, px, px, px);

            ruleString.setLayoutParams(ruleTextParams);
            ruleString.setTextColor(Color.argb(175, 255, 255, 255));
            exampleCardLayout.addView(ruleString);
        }
    }
    private TextView getHeader(int resource, int width, Typeface font) {
        TextView header = new TextView(this);
        header.setText(resource);
        header.setTextColor(Color.argb(175, 255, 255, 255));
        header.setGravity(Gravity.CENTER);
        header.setTextSize(18);
        header.setTypeface(font);
        LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerParams.setMargins(0, toDIP(this, 10), 0, toDIP(this, 10));
        header.setLayoutParams(headerParams);
        return header;
    }
}