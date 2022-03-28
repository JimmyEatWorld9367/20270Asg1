package com.example.assignmentone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarListFragment extends Fragment {
    private RecyclerView mList;
    private CarAdapter mAdapter;
    Button mButton;
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (CarLab.get(getActivity()).getCars().size() == 0) {
            Car car = new Car();
            car.setCarName("BMW320");
            car.setColor("white");
            car.setRego("5 month");
            car.setYear(2015);
            car.setPrice(90000);
            CarLab.get(getActivity()).addCar(car);
        }

        if (root == null) {
            root = inflater.inflate(R.layout.fragment_car_list, container, false);
        }
        mList = (RecyclerView) root.findViewById(R.id.recycler);
        mButton = (Button) root.findViewById(R.id.NewCarButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the "new car" button is clicked, go to the new car page, to allow user input data.
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewCarPagerActivity.class);
                startActivity(intent);
            }
        });
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        CarLab carLab = CarLab.get(getActivity());
        List<Car> cars = carLab.getCars();
        if (mAdapter == null) {
            mAdapter = new CarAdapter(cars);
            mList.setAdapter(mAdapter);
        } else {
            mAdapter.setCars(cars);
            mAdapter.notifyDataSetChanged();
        }
    }

    public class CarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Car mCar;
        private TextView mCarNameTextView;
        private TextView mCarDetailTextView;

        public CarHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list_view, parent, false));
            itemView.setOnClickListener(this);
            mCarNameTextView = itemView.findViewById(R.id.CarName);
            mCarDetailTextView = itemView.findViewById(R.id.CarDetail);
        }

        public void setData(Car car) {
            mCar = car;
            mCarNameTextView.setText(car.getCarName());
            mCarDetailTextView.setText(car.toString());
        }

        @Override
        public void onClick(View v) {
            //if any of the car information is clicked, open the corresponding page.
            Intent intent = CarPagerActivity.newIntent(getActivity(), mCar.getId());
            startActivity(intent);
        }
    }

    public class CarAdapter extends RecyclerView.Adapter<CarHolder> {
        private List<Car> mCars;

        public CarAdapter(List<Car> data) {
            this.mCars = data;
        }

        @Override
        public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CarHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CarHolder holder, int position) {
            Car car = mCars.get(position);
            holder.setData(car);
        }

        @Override
        public int getItemCount() {
            if (mCars != null) {
                return mCars.size();
            }
            return 0;
        }

        public void setCars(List<Car> cars) {
            mCars = cars;
        }
    }
}
