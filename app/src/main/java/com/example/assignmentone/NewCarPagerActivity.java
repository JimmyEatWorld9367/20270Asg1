package com.example.assignmentone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewCarPagerActivity extends AppCompatActivity {
    //this is the activity when user pressed "new car" button.
    @Override
    public void onBackPressed() {
        this.finish();
        System.out.println("按下了back键   onBackPressed()");
    }

    /*public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, NewCarPagerActivity.class);
       // intent.putExtra(EXTRA_CAR_ID);
        return intent;
    }

     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_car_activity);
    }
}

