package com.example.assignmentone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CarPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<Car> mCars;
    private static final String EXTRA_CAR_ID =
            "com.example.assignmentone.car_id";

    @Override
    public void onBackPressed() {
        //if back button is pressed, go back to car list, not to the last page
        this.finish();
    }


    public static Intent newIntent(Context packageContext, UUID carId) {
        Intent intent = new Intent(packageContext, CarPagerActivity.class);
        intent.putExtra(EXTRA_CAR_ID, carId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_detail_pager);
        UUID carId = (UUID) getIntent().getSerializableExtra(EXTRA_CAR_ID);
        mViewPager = (ViewPager) findViewById(R.id.car_view_pager);
        mCars = CarLab.get(this).getCars();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Car car = mCars.get(position);
                return CarFragment.newInstance(car.getId());
            }

            @Override
            public int getCount() {
                return mCars.size();
            }
        });

        for (int i = 0; i < mCars.size(); i++) {
            if (mCars.get(i).getId().equals(carId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
