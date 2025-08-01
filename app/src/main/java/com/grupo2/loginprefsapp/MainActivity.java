package com.grupo2.loginprefsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario, etContrasena;
    private CheckBox chkRecordarme;
    private Button btnLogin;
    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        chkRecordarme = findViewById(R.id.chkRecordarme);
        btnLogin = findViewById(R.id.btnLogin);

        // Inicializar SharedPreferences
        preferencias = getSharedPreferences("datos_login", MODE_PRIVATE);

        // Cargar usuario guardado (si existe)
        String usuarioGuardado = preferencias.getString("usuario", "");
        if (!usuarioGuardado.isEmpty()) {
            etUsuario.setText(usuarioGuardado);
            chkRecordarme.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simulación de login exitoso
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);


            // Guardar usuario si está activado "Recordarme"
            if (chkRecordarme.isChecked()) {
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("usuario", usuario);
                editor.apply();
            } else {
                // Borrar si no está marcada la opción
                preferencias.edit().remove("usuario").apply();
            }
        });
    }
}
