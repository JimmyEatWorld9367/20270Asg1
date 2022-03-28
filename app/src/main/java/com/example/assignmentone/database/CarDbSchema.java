package com.example.assignmentone.database;

public class CarDbSchema {
    public static final class CarTable {
        public static final String NAME = "cars";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String MODEL = "model";
            public static final String COLOR = "color";
            public static final String YEAR = "year";
            public static final String REGO = "rego";
            public static final String PRICE = "price";
        }
    }
}
