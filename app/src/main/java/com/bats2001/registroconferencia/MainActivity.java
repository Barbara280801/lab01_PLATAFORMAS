package com.bats2001.registroconferencia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    // Variables para los campos de entrada
    private EditText etNombre, etApellido, etCorreo, etTelefono, etGrupoSanguineo;
    private Button btnRegistrar, btnLeer;
    private final String FILE_NAME = "registro_conferencia.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos de la interfaz
        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellido);
        etCorreo = findViewById(R.id.et_correo);
        etTelefono = findViewById(R.id.et_telefono);
        etGrupoSanguineo = findViewById(R.id.et_grupo_sanguineo);
        btnRegistrar = findViewById(R.id.btn_registrar);
        btnLeer = findViewById(R.id.btn_leer);

        // Botón para registrar datos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        // Botón para leer datos
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerDatos();
            }
        });
    }

    // Método para guardar los datos en un archivo de texto
    private void guardarDatos() {
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String correo = etCorreo.getText().toString();
        String telefono = etTelefono.getText().toString();
        String grupoSanguineo = etGrupoSanguineo.getText().toString();

        // Concatenar todos los datos
        String datos = "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Correo: " + correo + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Grupo Sanguíneo: " + grupoSanguineo + "\n\n";

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(datos.getBytes());
            Log.i("Registro", "Datos guardados correctamente");
        } catch (Exception e) {
            Log.e("Registro", "Error al guardar los datos", e);
        }
    }

    // Método para leer los datos del archivo de texto y mostrarlos en la consola
    private void leerDatos() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            Log.i("Registro", sb.toString());
        } catch (Exception e) {
            Log.e("Registro", "Error al leer los datos", e);
        }
    }
}
