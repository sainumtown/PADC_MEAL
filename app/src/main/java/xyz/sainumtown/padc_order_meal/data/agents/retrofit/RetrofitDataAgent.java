package xyz.sainumtown.padc_order_meal.data.agents.retrofit;


import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.sainumtown.padc_order_meal.data.agents.MealDataAgent;
import xyz.sainumtown.padc_order_meal.data.models.Meal;
import xyz.sainumtown.padc_order_meal.data.models.MealModel;
import xyz.sainumtown.padc_order_meal.data.responses.MealListResponse;
import xyz.sainumtown.padc_order_meal.utils.CommonInstances;
import xyz.sainumtown.padc_order_meal.utils.MealConstants;

/**
 * Created by aung on 7/9/16.
 */
public class RetrofitDataAgent implements MealDataAgent {

    private static RetrofitDataAgent objInstance;

    private final MealApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MealConstants.MEAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(MealApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadMeals() {
        Call<MealListResponse> loadMealsCall = theApi.loadMeals(MealConstants.ACCESS_TOKEN);
        loadMealsCall.enqueue(new Callback<MealListResponse>() {
            @Override
            public void onResponse(Call<MealListResponse> call, Response<MealListResponse> response) {
                MealListResponse mealListResponse = response.body();
                if (mealListResponse == null) {
                    MealModel.getInstance().notifyErrorInLoadingMeals(response.message());
                } else {
                    MealModel.getInstance().notifyAttractionsLoaded(mealListResponse.getMealList());


                }
            }

            @Override
            public void onFailure(Call<MealListResponse> call, Throwable t) {
                MealModel.getInstance().notifyErrorInLoadingMeals(t.getMessage());
            }
        });
    }
}
