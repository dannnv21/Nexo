package com.nexo.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Frame 1: Pantalla de bienvenida.
 *
 * Cubre RF-01 (mensaje cálido + botón grande "Quiero hablar"), RF-02 (al pulsar,
 * navega al Frame 2), RNF-01 (accesibilidad) y RNF-02 (tono y lenguaje).
 *
 * Esta vista no contiene lógica de negocio (Constitución, principio 3): solo
 * muestra la interfaz y delega la navegación.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle estadoGuardado) {
        super.onCreate(estadoGuardado);
        setContentView(R.layout.activity_welcome);

        Button botonQuieroHablar = findViewById(R.id.botonQuieroHablar);
        botonQuieroHablar.setOnClickListener(vista -> irAPantallaDeTema());
    }

    private void irAPantallaDeTema() {
        startActivity(new Intent(this, TopicActivity.class));
    }
}
