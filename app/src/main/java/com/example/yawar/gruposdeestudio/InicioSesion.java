package com.example.yawar.gruposdeestudio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class InicioSesion extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etContrasena;
    private Button btIngresar;

    private VolleyRP volley;
    private RequestQueue mRequest;

    private static String IP = "https://nanitaandroid.000webhostapp.com/archivosphp/login_GETID.php?id=";

    private String USER = "";
    private String PASSWORD = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContrasena = (EditText) findViewById(R.id.etContrasena);

        btIngresar = (Button) findViewById(R.id.btIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificarLogin(etUsuario.getText().toString().toLowerCase(),etContrasena.getText().toString().toLowerCase());
                Toast.makeText(InicioSesion.this,"Ingresando...",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void VerificarLogin(String user,String password){
        USER=user;
        PASSWORD=password;
        SolicitudJSON(IP+user);
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                VerificarPassword(datos);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InicioSesion.this,"Ocurrio un error ",Toast.LENGTH_SHORT).show();

            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volley);
    }

    public void VerificarPassword(JSONObject datos){
        try {
            String estado = datos.getString("resultado");
            if (estado.equals("CC")){
                JSONObject Jsondatos= new JSONObject(datos.getString("datos")) ;
                String usuario = Jsondatos.getString("id");
                String contraseña = Jsondatos.getString("password");
                if(usuario.equals(USER) && contraseña.equals(PASSWORD)){
                    Toast.makeText(this,"Usted se logeo correctamente",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                }else
                    Toast.makeText(this,"El usuario y/o contraseña son incorrectos",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
        }
    }
}
