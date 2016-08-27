package xyz.sainumtown.padc_order_meal.events;

import java.util.List;

import xyz.sainumtown.padc_order_meal.data.vos.MealVO;

/**
 * Created by asus on 8/21/2016.
 */
public class MealEvent {
    public static class MealDataLoadedEvent {

        private String extraMessage;
        private List<MealVO> mealList;

        public MealDataLoadedEvent(String extraMessage, List<MealVO> mealList) {
            this.extraMessage = extraMessage;
            this.mealList = mealList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<MealVO> getAttractionList() {
            return mealList;
        }
    }
}
