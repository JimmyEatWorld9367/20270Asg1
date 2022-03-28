package com.example.assignmentone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignmentone.database.CarCursorWrapper;
import com.example.assignmentone.database.CarDbSchema.CarTable;
import com.example.assignmentone.database.CarBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarLab {

    private static CarLab sCarLab;
    private List<Car> mCars;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CarLab get(Context context) {
        if (sCarLab == null) {
            sCarLab = new CarLab(context);
        }
        return sCarLab;
    }

    private CarLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CarBaseHelper(mContext)
                .getWritableDatabase();

    }

    public List<Car> getCars() {
        mCars = new ArrayList<>();
        CarCursorWrapper cursor = queryCars(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mCars.add(cursor.getCar());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return mCars;
    }

    public Car getCar(UUID carId) {
        CarCursorWrapper cursor = queryCars(
                CarTable.Cols.UUID + " = ?",
                new String[]{carId.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCar();
        } finally {
            cursor.close();
        }
    }

    public void updateCar(Car car) {
        String uuidString = car.getId().toString();
        ContentValues values = getContentValues(car);
        mDatabase.update(CarTable.NAME, values,
                CarTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private CarCursorWrapper queryCars(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CarTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CarCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Car car) {
        ContentValues values = new ContentValues();
        values.put(CarTable.Cols.UUID, car.getId().toString());
        values.put(CarTable.Cols.COLOR, car.getColor());
        values.put(CarTable.Cols.MODEL, car.getCarName());
        values.put(CarTable.Cols.YEAR, car.getYear());
        values.put(CarTable.Cols.REGO, car.getRego());
        values.put(CarTable.Cols.PRICE, car.getPrice());
        return values;
    }

    public void addCar(Car car) {
        ContentValues values = getContentValues(car);
        mDatabase.insert(CarTable.NAME, null, values);
    }
}
