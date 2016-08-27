package xyz.sainumtown.padc_order_meal.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import xyz.sainumtown.padc_order_meal.R;
import xyz.sainumtown.padc_order_meal.adapters.MealAdapter;
import xyz.sainumtown.padc_order_meal.data.models.MealModel;
import xyz.sainumtown.padc_order_meal.data.vos.MealVO;
import xyz.sainumtown.padc_order_meal.events.MealEvent;
import xyz.sainumtown.padc_order_meal.views.holder.MealViewHolder;


public class MealListFragment extends BaseFragment {
    private static final String BARG_COLUMN_COUNT = "column_count";
    @BindView(R.id.rv_meals)
    RecyclerView rvMeals;

    private MealAdapter mMealAdpater;
    private MealViewHolder.ControllerMealItem controllerMealItem;

    public MealListFragment() {
        // Required empty public constructor
    }

    public static MealListFragment newInstance(int columnCount) {
        MealListFragment fragment = new MealListFragment();
        Bundle args = new Bundle();

        args.putInt(BARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        ButterKnife.bind(this, view);

        /*MealModel.getInstance().loadMeals();*/
        List<MealVO> newMealList = MealModel.getInstance().getmMealList();

        mMealAdpater = new MealAdapter(newMealList,controllerMealItem);
        rvMeals.setAdapter(mMealAdpater);

        Bundle bundle = getArguments();
        int gridColumnSpanCount = 1;
        if (bundle != null) {
            gridColumnSpanCount = bundle.getInt(BARG_COLUMN_COUNT);
        }
        rvMeals.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerMealItem = (MealViewHolder.ControllerMealItem )context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();


        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(MealEvent.MealDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<MealVO> newMealList = event.getAttractionList();
        mMealAdpater.setNewData(newMealList);
        mMealAdpater.notifyDataSetChanged();

    }

}
