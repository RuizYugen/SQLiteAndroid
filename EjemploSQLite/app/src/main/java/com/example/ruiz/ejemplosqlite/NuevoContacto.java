package com.example.ruiz.ejemplosqlite;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoContacto extends AppCompatActivity {
    EditText txtNombre,txtTelefono,txtCorreo;
    DatePicker dtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_contacto);
        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtCorreo=findViewById(R.id.txtCorreo);
        dtp=findViewById(R.id.dtpFecha);
    }

    public void btnGuardarClick(View v){
        DaoContacto dao=new DaoContacto(getApplicationContext());
        String fecha=dtp.getDayOfMonth()+"-"+dtp.getMonth()+"-"+dtp.getYear();
        Contacto obj=new Contacto(0,txtNombre.getText().toString(), txtTelefono.getText().toString(),txtCorreo.getText().toString(),fecha);
        long result=dao.Insert(obj);
        if(result>0){
            Toast.makeText(this,"Se inserto",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
      //Toast.makeText(this,dtp.getDayOfMonth()+"",Toast.LENGTH_LONG).show();
    }
}
