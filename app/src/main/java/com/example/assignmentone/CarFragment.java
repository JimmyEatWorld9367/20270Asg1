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

public class CarFragment extends Fragment {
    //this class is to configure the car detail page which allows user to enter the data of a existing car.
    private static final String ARG_CAR_NAME = "car_name";
    private EditText mModelField;
    private EditText mYear;
    private EditText mRego;
    private EditText mColor;
    private EditText mPrice;
    private Button mButton;
    private Car mCar;

    public static CarFragment newInstance(UUID carId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAR_NAME, carId);
        CarFragment fragment = new CarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID carID = (UUID) getArguments().getSerializable(ARG_CAR_NAME);
        mCar = CarLab.get(getActivity()).getCar(carID);
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
        mModelField.setText(mCar.getCarName());
        mModelField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mCar.setCarName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });
        mColor = (EditText) v.findViewById(R.id.color);
        mColor.setText(mCar.getColor());
        mColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mCar.setColor(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mYear = (EditText) v.findViewById(R.id.year_of_manufacture);
        mYear.setText(String.valueOf(mCar.getYear()));
        mYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                try {
                    mCar.setYear(Integer.valueOf(s.toString()));
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getContext(), "Year of manufacturing can't be null", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mRego = (EditText) v.findViewById(R.id.rego);
        mRego.setText(mCar.getRego());
        mRego.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mCar.setRego(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mPrice = (EditText) v.findViewById(R.id.price);
        mPrice.setText(String.valueOf(mCar.getPrice()));
        mPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                try {
                    mCar.setPrice(Integer.valueOf(s.toString()));
                } catch (java.lang.NumberFormatException e) {
                    Toast.makeText(getContext(), "Price can't be null", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });
        mButton = (Button) v.findViewById(R.id.save);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if any of the columns is empty, make a toast to remind user
                if (mModelField.length() == 0 || mColor.length() == 0 || mRego.length() == 0 || mYear.length() == 0 || mPrice.length() == 0) {
                    Toast.makeText(getContext(), "values can'e be null", Toast.LENGTH_LONG).show();
                    return;
                }
                CarLab.get(getActivity())
                        .updateCar(mCar);
                getActivity().finish();
            }
        });
        return v;
    }
}
