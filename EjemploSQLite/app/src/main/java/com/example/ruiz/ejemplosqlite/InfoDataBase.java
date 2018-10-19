package com.example.ruiz.ejemplosqlite;

public class InfoDataBase {
    public static final String DATABASE_NAME="prueba.db";
    public static final int DATABASE_VERSION = 1;

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
