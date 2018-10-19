package com.example.ruiz.ejemplosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DaoContacto {
    private SQLiteDatabase _ad ;

    public DaoContacto(Context ctx) {
        this._ad = new AdaptadorContacto(ctx).getWritableDatabase();
    }

    public long Insert(Contacto contacto){
        ContentValues cnt=new ContentValues();
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[1],contacto.getNombre());
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[2],contacto.getTelefono());
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[3],contacto.getCorreo());
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[4],contacto.getFecha());

        return  _ad.insert(AdaptadorContacto.CONTACTOSTABLE,null,cnt);
    }

    public ArrayList<Contacto> SeleccionarTodos(){
        ArrayList<Contacto>lista=new ArrayList<Contacto>();
        Cursor c=_ad.query(AdaptadorContacto.CONTACTOSTABLE,AdaptadorContacto.CONTACTOSCOLUMNS,null,null,null,null,null);
        while(c.moveToNext()){
            lista.add(new Contacto(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
        }
        return lista;

    }
    public Cursor SeleccionarTodosCursor(){
        Cursor c=_ad.query(AdaptadorContacto.CONTACTOSTABLE,AdaptadorContacto.CONTACTOSCOLUMNS,null,null,null,null,null);
        return c;

    }

    public ArrayList<Contacto> SeleccionarTodos(String desc){
        Cursor c=_ad.query(AdaptadorContacto.CONTACTOSTABLE,AdaptadorContacto.CONTACTOSCOLUMNS,"nombre like  '%"+desc+"%'",null,null,null,null);
        ArrayList<Contacto> lista=new ArrayList<>();
        while(c.moveToNext()){
            lista.add(new Contacto(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
        }
        return lista;
    }

    public Cursor SeleccionarTodosCursor(String desc){
        Cursor c=_ad.query(AdaptadorContacto.CONTACTOSTABLE,AdaptadorContacto.CONTACTOSCOLUMNS,"nombre like '%"+desc+"%'",null,null,null,null);
        return c;
    }

    public Contacto SeleccionarUno(int id){
        Cursor c=_ad.query(AdaptadorContacto.CONTACTOSTABLE,AdaptadorContacto.CONTACTOSCOLUMNS,"_id=?",new String[]{id+""},null,null,null);
        Contacto obj=null;
        while(c.moveToNext()){
            obj=new Contacto(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
        }
        return obj;

    }



    public int Eliminar(Contacto contacto){
        return _ad.delete(AdaptadorContacto.CONTACTOSTABLE,"_id=?",new String[]{contacto.getId()+""});
    }

    public long Actualizar(Contacto contacto){
        ContentValues cnt=new ContentValues();
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[1],contacto.getNombre());
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[2],contacto.getTelefono());
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[3],contacto.getCorreo());
        cnt.put(AdaptadorContacto.CONTACTOSCOLUMNS[4],contacto.getFecha());
        return _ad.update(AdaptadorContacto.CONTACTOSTABLE,cnt,"_id=?",new String[]{contacto.getId()+""});

    }


}
