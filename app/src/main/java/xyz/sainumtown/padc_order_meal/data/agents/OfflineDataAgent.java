package xyz.sainumtown.padc_order_meal.data.agents;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import xyz.sainumtown.padc_order_meal.data.models.MealModel;
import xyz.sainumtown.padc_order_meal.data.vos.MealVO;
import xyz.sainumtown.padc_order_meal.utils.CommonInstances;
import xyz.sainumtown.padc_order_meal.utils.JsonUtils;


/**
 * Created by aung on 7/9/16.
 */
public class OfflineDataAgent implements MealDataAgent {

    private static final String OFFLINE_ATTRACTION_LIST = "meals.json";

    private static OfflineDataAgent objInstance;

    private OfflineDataAgent() {

    }

    public static OfflineDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new OfflineDataAgent();
        }

        return objInstance;
    }

    @Override
    public void loadMeals() {
        try {
            String attractions = JsonUtils.getInstance().loadDummyData(OFFLINE_ATTRACTION_LIST);
            Type listType = new TypeToken<List<MealVO>>() {
            }.getType();
            List<MealVO> mealList = CommonInstances.getGsonInstance().fromJson(attractions, listType);

            MealModel.getInstance().notifyAttractionsLoaded(mealList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
