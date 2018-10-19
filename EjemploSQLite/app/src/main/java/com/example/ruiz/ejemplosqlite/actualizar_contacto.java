package com.example.ruiz.ejemplosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class actualizar_contacto extends AppCompatActivity {
    EditText txtNombre,txtTelefono,txtCorreo;
    DatePicker dtpFecha;
    Contacto contacto=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contacto);
        txtNombre=findViewById(R.id.txtNombre1);
        txtTelefono=findViewById(R.id.txtTelefono1);
        txtCorreo=findViewById(R.id.txtCorreo1);
        dtpFecha=findViewById(R.id.dtpFecha);
        Bundle bundle=getIntent().getExtras();
        DaoContacto dao=new DaoContacto(this);
         contacto=dao.SeleccionarUno(bundle.getInt("_id"));
        txtNombre.setText(contacto.getNombre());
        txtTelefono.setText((contacto.getTelefono()));
        txtCorreo.setText(contacto.getCorreo());
        String[] arr=contacto.getFecha().split("-");
        dtpFecha.updateDate(Integer.parseInt(arr[2]),Integer.parseInt(arr[1]),Integer.parseInt(arr[0]));
    }

    public void btnGuardarClick1(View v){

        DaoContacto dao=new DaoContacto(this);
        String fecha=dtpFecha.getDayOfMonth()+"-"+dtpFecha.getMonth()+"-"+dtpFecha.getYear();
        Contacto obj=new Contacto(contacto.getId(),txtNombre.getText().toString(), txtTelefono.getText().toString(),txtCorreo.getText().toString(),fecha);
        long result=dao.Actualizar(obj);
        if(result>0){
            Toast.makeText(this,"Se modifico",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}
