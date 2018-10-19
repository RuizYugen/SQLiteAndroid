package com.example.ruiz.ejemplosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdaptadorContacto extends SQLiteOpenHelper {
    private InfoDataBase info=new InfoDataBase();
    public static final String CONTACTOSTABLE="contactos";
    public static final String [] CONTACTOSCOLUMNS = {"_id", "nombre", "telefono", "correo","fecha" };

    private String SCRIPT_DB  = "create table "+CONTACTOSTABLE+" ("+CONTACTOSCOLUMNS[0]+" integer primary key autoincrement, " +
            CONTACTOSCOLUMNS[1]+" text not null, "+CONTACTOSCOLUMNS[2]+" text, "+CONTACTOSCOLUMNS[3]+" text ,"+CONTACTOSCOLUMNS[4]+" text);";

    public AdaptadorContacto(Context context) {
        super(context,"prueba.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
