package xyz.sainumtown.padc_order_meal.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.sainumtown.padc_order_meal.MealApp;
import xyz.sainumtown.padc_order_meal.R;
import xyz.sainumtown.padc_order_meal.data.models.MealModel;
import xyz.sainumtown.padc_order_meal.data.vos.MealVO;

public class MealDetailAcitivity extends AppCompatActivity {

    private static final String IE_MEAL_ID = "meal_id";
    private CollapsingToolbarLayout collapsingToolbar;
    MealVO meal;

    @BindView(R.id.tv_Price)
    TextView tvPrice;

    @BindView(R.id.tv_ingridents_data)
    TextView tvIngridents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        TextView tvDesc = (TextView) findViewById(R.id.tv_meal_desc);
        ImageView ivImage = (ImageView) findViewById(R.id.iv_meal);

        String mealId = getIntent().getStringExtra(IE_MEAL_ID);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            String tsName = getResources().getString(R.string.share_element_transistion_attractioin);
            ivImage.setTransitionName(tsName);
        }

        meal = MealModel.getInstance().getAttractionById(mealId);
        if (meal == null) {
            throw new RuntimeException("Can't find Attraction obj with the id : " + mealId);
        } else {
            collapsingToolbar.setTitle(meal.getName());
            tvPrice.setText(String.valueOf(meal.getPrice()));

            String ingridents = "";
            for (int i = 0; i < meal.getIngredients().length; i++) {
                if (i == 0) {
                    ingridents += meal.getIngredients()[i];
                } else {
                    ingridents += "\n\n" + meal.getIngredients()[i];
                }
            }

            tvIngridents.setText(ingridents);

            tvDesc.setText(meal.getDescription() + "\n\n");

            Glide.with(ivImage.getContext())
                    .load(meal.getImg_url().toString())
                    .centerCrop()
                    .placeholder(R.drawable.stock_photo_placeholder)
                    .into(ivImage);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static Intent newIntent(String id) {
        Intent intent = new Intent(MealApp.getContext(), MealDetailAcitivity.class);
        intent.putExtra(IE_MEAL_ID, id);
        return intent;
    }


}
