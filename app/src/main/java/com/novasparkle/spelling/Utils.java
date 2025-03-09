package com.novasparkle.spelling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

public class Utils {
    public static void switchActivity(Context context, Class<?> clazz, Serializable serializable) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(serializable.getClass().getSimpleName(), serializable);
        context.startActivity(intent);
    }
    public static int toDIP(Context context, int DIP) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DIP, resources.getDisplayMetrics());
    }
    @SuppressLint("ResourceAsColor")
    public static TextView divide(Context context, int widthView) {
        TextView divider = new TextView(context);
        ViewGroup.LayoutParams dividerParams = new ViewGroup.LayoutParams(widthView, toDIP(context, 3));
        divider.setLayoutParams(dividerParams);
        divider.setBackgroundColor(R.color.black);
        return divider;
    }
}
