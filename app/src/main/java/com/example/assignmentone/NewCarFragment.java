package com.example.assignmentone;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class NewCarFragment extends Fragment {
    //this class is to configure the car detail page which allows user to enter the data of a new car.
    private EditText mModelField;
    private EditText mYear;
    private EditText mRego;
    private EditText mColor;
    private EditText mPrice;
    private Button mButton;

    /*public static NewCarFragment newInstance(UUID carId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAR_NAME, carId);

        NewCarFragment fragment = new NewCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.car_detail, container, false);
        mModelField = (EditText) v.findViewById(R.id.make_model);
        mColor = (EditText) v.findViewById(R.id.color);
        mYear = (EditText) v.findViewById(R.id.year_of_manufacture);
        mRego = (EditText) v.findViewById(R.id.rego);
        mPrice = (EditText) v.findViewById(R.id.price);
        mButton = (Button) v.findViewById(R.id.save);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if any of the columns is empty, make a toast to remind user.
                if (mModelField.length() == 0 || mColor.length() == 0 || mRego.length() == 0 || mYear.length() == 0 || mPrice.length() == 0) {
                    Toast.makeText(getContext(), "not null", Toast.LENGTH_LONG).show();
                    return;
                }

                //collect the information from edit text, to create a new car
                Car car = new Car();
                car.setCarName(mModelField.getText().toString());
                car.setColor(mColor.getText().toString());
                car.setRego(mRego.getText().toString());
                car.setYear(Integer.valueOf(mYear.getText().toString()));
                car.setPrice(Integer.valueOf(mPrice.getText().toString()));
                CarLab.get(getActivity()).addCar(car);
                getActivity().finish();
            }
        });
        return v;
    }
}
