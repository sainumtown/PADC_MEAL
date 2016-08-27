package xyz.sainumtown.padc_order_meal.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 8/21/2016.
 */
public class MealVO {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("img_url")
    private String img_url;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private int price;

    @SerializedName("ingredients")
    private String[] ingredients;

    public String getName() {
        return name;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getId() {

        return id;
    }
}
