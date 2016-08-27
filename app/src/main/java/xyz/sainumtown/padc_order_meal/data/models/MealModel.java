package xyz.sainumtown.padc_order_meal.data.models;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import xyz.sainumtown.padc_order_meal.data.vos.MealVO;
import xyz.sainumtown.padc_order_meal.events.MealEvent;

/**
 * Created by asus on 8/21/2016.
 */
public class MealModel extends BaseModel {
    public static final String  BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static MealModel objInstance;

    private List<MealVO> mMealList;

    private MealModel(){
        super();
        mMealList =  new ArrayList<>();
        dataAgent.loadMeals();

    }

    public static MealModel getInstance(){
        if(objInstance == null){
            objInstance = new MealModel();
        }
        return objInstance;
    }

    public void loadMeals() {
        dataAgent.loadMeals();
    }

    public List<MealVO> getmMealList() {
        return mMealList;
    }

    public MealVO getAttractionById(String mealId) {
        for (MealVO meal : mMealList) {
            if (meal.getId().equals(mealId))
                return meal;
        }

        return null;
    }
    public void notifyAttractionsLoaded(List<MealVO> mealList) {
        //Notify that the data is ready - using LocalBroadcast
        mMealList = mealList;

        //keep the data in persistent layer.


        //broadcastAttractionLoadedWithEventBus();
        broadcastAttractionLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }


    public void notifyErrorInLoadingMeals(String message) {
    }

    private void broadcastAttractionLoadedWithEventBus() {
        EventBus.getDefault().post(new MealEvent.MealDataLoadedEvent("extra-in-broadcast", mMealList));
    }
}
