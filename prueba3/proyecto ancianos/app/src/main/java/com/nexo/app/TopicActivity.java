package com.nexo.app;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Frame 2: Pantalla para introducir el tema de conversación.
 *
 * Cubre RF-02 (se muestra al pulsar "Quiero hablar"), RF-03 (tema obligatorio, sin
 * solo espacios, máximo 100 caracteres) y RF-04 (el botón se bloquea al primer clic
 * para evitar envíos duplicados).
 *
 * Pendiente (T3/T4): crear la petición real con CallService y navegar al Frame 3.
 * Mientras tanto, al enviar se bloquea la pantalla y se muestra "Preparando tu conversación…".
 */
public class TopicActivity extends AppCompatActivity {

    /** Longitud máxima del tema (RF-03). */
    public static final int LIMITE_CARACTERES = 100;

    private EditText campoTema;
    private Button botonEnviar;
    private TextView contadorCaracteres;
    private TextView textoEnviando;

    /** Pasa a true en el primer clic válido y ya no vuelve a false (anti doble envío). */
    private boolean peticionEnviada = false;

    @Override
    protected void onCreate(Bundle estadoGuardado) {
        super.onCreate(estadoGuardado);
        setContentView(R.layout.activity_topic);

        campoTema = findViewById(R.id.campoTema);
        botonEnviar = findViewById(R.id.botonEnviar);
        contadorCaracteres = findViewById(R.id.contadorCaracteres);
        textoEnviando = findViewById(R.id.textoEnviando);

        campoTema.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                actualizarEstado();
            }
        });

        botonEnviar.setOnClickListener(vista -> enviar());

        actualizarEstado();
    }

    /** Un tema es válido si no está vacío, no son solo espacios y no pasa de 100 caracteres. */
    static boolean esTemaValido(CharSequence tema) {
        if (tema == null) {
            return false;
        }
        String texto = tema.toString();
        return !texto.trim().isEmpty() && texto.length() <= LIMITE_CARACTERES;
    }

    private void actualizarEstado() {
        int longitud = campoTema.getText().length();
        contadorCaracteres.setText(getString(R.string.tema_contador, longitud, LIMITE_CARACTERES));

        // Una vez enviado, el botón se queda bloqueado pase lo que pase con el texto.
        botonEnviar.setEnabled(!peticionEnviada && esTemaValido(campoTema.getText()));
    }

    private void enviar() {
        // Doble protección: aunque el clic llegara dos veces seguidas, solo el primero cuenta.
        if (peticionEnviada || !esTemaValido(campoTema.getText())) {
            return;
        }
        peticionEnviada = true;

        // RF-04: bloqueo inmediato.
        botonEnviar.setEnabled(false);
        campoTema.setEnabled(false);
        ocultarTeclado();
        textoEnviando.setVisibility(View.VISIBLE);

        String tema = campoTema.getText().toString().trim();
        // TODO (T3/T4): CallService.registrarPeticion(tema) y startActivity(WaitingActivity).
    }

    private void ocultarTeclado() {
        InputMethodManager teclado =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (teclado != null) {
            teclado.hideSoftInputFromWindow(campoTema.getWindowToken(), 0);
        }
    }
}
