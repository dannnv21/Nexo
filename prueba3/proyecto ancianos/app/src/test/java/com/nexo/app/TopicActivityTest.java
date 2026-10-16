package com.nexo.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Pruebas de la T2: Frame 2 (tema de conversación).
 * Requisitos cubiertos: RF-02, RF-03, RF-04, LC-01 y RNF-01.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class TopicActivityTest {

    private TopicActivity crearActividad() {
        return Robolectric.buildActivity(TopicActivity.class).setup().get();
    }

    @Test
    public void muestraCampoDeTextoYBotonEnviar() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        Button boton = actividad.findViewById(R.id.botonEnviar);

        assertEquals(View.VISIBLE, campo.getVisibility());
        assertEquals(View.VISIBLE, boton.getVisibility());
        assertEquals("Enviar", boton.getText().toString());
    }

    @Test
    public void botonEmpiezaDeshabilitado() {
        Button boton = crearActividad().findViewById(R.id.botonEnviar);
        assertFalse(boton.isEnabled());
    }

    @Test
    public void conSoloEspacios_elBotonSigueDeshabilitado() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        Button boton = actividad.findViewById(R.id.botonEnviar);

        campo.setText("     ");

        assertFalse(boton.isEnabled());
    }

    @Test
    public void conTextoValido_elBotonSeHabilita() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        Button boton = actividad.findViewById(R.id.botonEnviar);

        campo.setText("Mis plantas");

        assertTrue(boton.isEnabled());
    }

    @Test
    public void siSeBorraElTexto_elBotonSeVuelveADeshabilitar() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        Button boton = actividad.findViewById(R.id.botonEnviar);

        campo.setText("Mi día");
        campo.setText("");

        assertFalse(boton.isEnabled());
    }

    @Test
    public void elCampoLimitaElTextoACienCaracteres() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);

        campo.setText("a".repeat(150));

        assertEquals(100, campo.getText().length());
    }

    @Test
    public void elContadorMuestraLosCaracteresEscritos() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        TextView contador = actividad.findViewById(R.id.contadorCaracteres);

        campo.setText("Hola");

        assertEquals("4/100", contador.getText().toString());
    }

    @Test
    public void alPulsarEnviar_seBloqueaElBotonYElCampo() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        Button boton = actividad.findViewById(R.id.botonEnviar);
        campo.setText("Mis plantas");

        boton.performClick();

        assertFalse(boton.isEnabled());
        assertFalse(campo.isEnabled());
        assertEquals(View.VISIBLE, actividad.findViewById(R.id.textoEnviando).getVisibility());
    }

    @Test
    public void trasEnviar_noSeVuelveAHabilitarAunqueCambieElTexto() {
        TopicActivity actividad = crearActividad();
        EditText campo = actividad.findViewById(R.id.campoTema);
        Button boton = actividad.findViewById(R.id.botonEnviar);
        campo.setText("Mis plantas");
        boton.performClick();

        campo.setText("Otro tema");

        assertFalse(boton.isEnabled());
    }

    @Test
    public void validacionDelTema() {
        assertFalse(TopicActivity.esTemaValido(null));
        assertFalse(TopicActivity.esTemaValido(""));
        assertFalse(TopicActivity.esTemaValido("   "));
        assertTrue(TopicActivity.esTemaValido("Mi día"));
        assertTrue(TopicActivity.esTemaValido("a".repeat(100)));
        assertFalse(TopicActivity.esTemaValido("a".repeat(101)));
    }
}
