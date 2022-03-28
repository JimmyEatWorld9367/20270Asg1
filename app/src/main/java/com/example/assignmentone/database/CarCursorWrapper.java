package com.example.assignmentone.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.assignmentone.Car;
import com.example.assignmentone.database.CarDbSchema.CarTable;

import java.util.UUID;

public class CarCursorWrapper extends CursorWrapper {
    public CarCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Car getCar() {
        String uuidString = getString(getColumnIndex(CarTable.Cols.UUID));
        String model = getString(getColumnIndex(CarTable.Cols.MODEL));
        String color = getString(getColumnIndex(CarTable.Cols.COLOR));
        int year = getInt(getColumnIndex(CarTable.Cols.YEAR));
        String rego = getString(getColumnIndex(CarTable.Cols.REGO));
        int price = getInt(getColumnIndex(CarTable.Cols.PRICE));

        Car car = new Car(UUID.fromString(uuidString));
        car.setCarName(model);
        car.setColor(color);
        car.setYear(year);
        car.setRego(rego);
        car.setPrice(price);

        return car;
    }
}
