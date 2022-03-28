package com.example.assignmentone;

import java.util.UUID;

public class Car {
    private String CarName;
    private int Year;
    private String Color;
    private String Rego;
    private int Price;
    private UUID mId;

    public Car() {
        this(UUID.randomUUID());
    }

    public Car(UUID id) {
        mId = id;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getRego() {
        return Rego;
    }

    public void setRego(String rego) {
        Rego = rego;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    @Override
    public String toString() {
        return Year + " " + Color + " " + Rego + "Rego $" + Price;
    }
}
