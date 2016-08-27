package xyz.sainumtown.padc_order_meal.data.agents.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.sainumtown.padc_order_meal.data.responses.MealListResponse;
import xyz.sainumtown.padc_order_meal.utils.MealConstants;


/**
 * Created by aung on 7/9/16.
 */
public interface MealApi {
    @FormUrlEncoded
    @POST(MealConstants.API_GET_MEAL_LIST)
    Call<MealListResponse> loadMeals(
            @Field(MealConstants.PARAM_ACCESS_TOKEN) String param);

}
