package xyz.sainumtown.padc_order_meal.data.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import xyz.sainumtown.padc_order_meal.data.vos.MealVO;

/**
 * Created by asus on 8/21/2016.
 */
public class MealListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("meal_list")
    private ArrayList<MealVO> mealList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<MealVO> getMealList() {
        return mealList;
    }
}
