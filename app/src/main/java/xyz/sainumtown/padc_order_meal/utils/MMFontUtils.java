package xyz.sainumtown.padc_order_meal.utils;

import android.content.Context;
import android.graphics.Typeface;

import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import xyz.sainumtown.padc_order_meal.MealApp;

/**
 * Created by aung on 6/25/16.
 */
public class MMFontUtils {

    private static Typeface mmTypeFace;

    static {
        Context context = MealApp.getContext();
        mmTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Zawgyi.ttf");
    }

    public static void setMMFont(TextView view) {
        view.setTypeface(mmTypeFace);
    }


}
