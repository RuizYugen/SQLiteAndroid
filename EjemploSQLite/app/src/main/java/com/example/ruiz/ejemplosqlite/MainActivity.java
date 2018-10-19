package com.example.ruiz.ejemplosqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lstLista;
    int posicion=-1;
    EditText txtBuscar;
    ArrayList<Contacto> lista=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstLista=findViewById(R.id.lstLista);
        txtBuscar=findViewById(R.id.txtBuscar);
        actualizarTabla();
    }
    public void actualizarTabla(){
        DaoContacto dao=new DaoContacto(this);
        Cursor c=dao.SeleccionarTodosCursor();
        lista=dao.SeleccionarTodos();
        SimpleCursorAdapter adp=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,AdaptadorContacto.CONTACTOSCOLUMNS,
                new int[]{android.R.id.text1,android.R.id.text2},SimpleCursorAdapter.NO_SELECTION);
        lstLista.setAdapter(adp);
        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });
    }
    public void ClickNuevo(View v){
        Intent intent=new Intent(v.getContext(),NuevoContacto.class);
        startActivity(intent);
    }

    public void btnEliminarClick(View v){
        if(posicion>=0){
            DaoContacto dao=new DaoContacto(this);
            int result=dao.Eliminar(lista.get(posicion));
            if(result>0){
                Toast.makeText(this,"Se elimino",Toast.LENGTH_LONG).show();
                actualizarTabla();
            }
        }else{
            Toast.makeText(this,"Seleccione uno primero",Toast.LENGTH_LONG).show();
        }

    }

    public void btnActualizarClick(View v){
        if(posicion>=0){
            Intent intent=new Intent(getApplicationContext(),actualizar_contacto.class);
            intent.putExtra("_id",lista.get(posicion).getId());
            startActivity(intent);
        }else{
            Toast.makeText(this,"Seleccione uno primero",Toast.LENGTH_LONG).show();
        }
    }

    public void btnBuscarClick(View v){
        String desc=txtBuscar.getText().toString();
        if(!desc.equals("")){
            actualizarTabla(desc);
        }
    }

    public void actualizarTabla(String desc){
        DaoContacto dao=new DaoContacto(this);
        Cursor c=dao.SeleccionarTodosCursor(desc);
        lista=dao.SeleccionarTodos(desc);
        SimpleCursorAdapter adp=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,c,AdaptadorContacto.CONTACTOSCOLUMNS,
                new int[]{android.R.id.text1,android.R.id.text2},SimpleCursorAdapter.NO_SELECTION);
        lstLista.setAdapter(adp);
        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicion=position;
            }
        });
    }
}
