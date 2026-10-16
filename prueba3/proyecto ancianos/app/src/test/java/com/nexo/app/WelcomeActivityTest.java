package com.nexo.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

/**
 * Pruebas de la T1: Pantalla de Bienvenida (Frame 1).
 * Requisitos cubiertos: RF-01, RF-02 (navegación), RNF-01 y RNF-02.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 34)
public class WelcomeActivityTest {

    private static final double CONTRASTE_MINIMO = 7.0;   // Nivel AAA (RNF-01)
    private static final float TEXTO_MINIMO_SP = 24f;     // Tipografía grande (RNF-01)
    private static final float TOQUE_MINIMO_DP = 72f;     // Área de toque amplia (RNF-01)

    private WelcomeActivity crearActividad() {
        return Robolectric.buildActivity(WelcomeActivity.class).setup().get();
    }

    private float aPx(WelcomeActivity actividad, int unidad, float valor) {
        DisplayMetrics metricas = actividad.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(unidad, valor, metricas);
    }

    // ---------- RF-01 ----------

    @Test
    public void alIniciarLaApp_laPantallaLanzadoraEsLaBienvenida() {
        Context contexto = ApplicationProvider.getApplicationContext();
        Intent inicio = new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .setPackage(contexto.getPackageName());

        ResolveInfo resultado = contexto.getPackageManager().resolveActivity(inicio, 0);

        assertNotNull(resultado);
        assertEquals(WelcomeActivity.class.getName(), resultado.activityInfo.name);
    }

    @Test
    public void muestraMensajeDeBienvenidaCalido() {
        WelcomeActivity actividad = crearActividad();
        TextView titulo = actividad.findViewById(R.id.textoBienvenida);

        assertEquals(View.VISIBLE, titulo.getVisibility());
        assertEquals("¡Hola! Qué alegría verte por aquí", titulo.getText().toString());
    }

    @Test
    public void muestraBotonPrincipalConTextoQuieroHablar() {
        WelcomeActivity actividad = crearActividad();
        Button boton = actividad.findViewById(R.id.botonQuieroHablar);

        assertEquals(View.VISIBLE, boton.getVisibility());
        assertTrue(boton.isEnabled());
        assertEquals("Quiero hablar", boton.getText().toString());
    }

    // ---------- RF-02 ----------

    @Test
    public void alPulsarQuieroHablar_navegaALaPantallaDeTema() {
        WelcomeActivity actividad = crearActividad();
        Button boton = actividad.findViewById(R.id.botonQuieroHablar);

        boton.performClick();

        Intent siguiente = Shadows.shadowOf(actividad).getNextStartedActivity();
        assertNotNull(siguiente);
        assertEquals(TopicActivity.class.getName(), siguiente.getComponent().getClassName());
    }

    // ---------- RNF-01: accesibilidad ----------

    @Test
    public void lasTipografiasSonGrandes() {
        WelcomeActivity actividad = crearActividad();
        float minimoPx = aPx(actividad, TypedValue.COMPLEX_UNIT_SP, TEXTO_MINIMO_SP);

        TextView titulo = actividad.findViewById(R.id.textoBienvenida);
        TextView lema = actividad.findViewById(R.id.textoLema);
        Button boton = actividad.findViewById(R.id.botonQuieroHablar);

        assertTrue("Título demasiado pequeño", titulo.getTextSize() >= minimoPx);
        assertTrue("Lema demasiado pequeño", lema.getTextSize() >= minimoPx);
        assertTrue("Botón con texto demasiado pequeño", boton.getTextSize() >= minimoPx);
    }

    @Test
    public void elBotonTieneAreaDeToqueAmplia() {
        WelcomeActivity actividad = crearActividad();
        Button boton = actividad.findViewById(R.id.botonQuieroHablar);
        float minimoPx = aPx(actividad, TypedValue.COMPLEX_UNIT_DIP, TOQUE_MINIMO_DP);

        assertTrue("Botón demasiado bajo", boton.getMinimumHeight() >= minimoPx);
    }

    @Test
    public void losColoresTienenAltoContraste() {
        WelcomeActivity actividad = crearActividad();
        int fondoPantalla = ContextCompat.getColor(actividad, R.color.fondo_pantalla);
        int textoPrincipal = ContextCompat.getColor(actividad, R.color.texto_principal);
        int botonFondo = ContextCompat.getColor(actividad, R.color.boton_fondo);
        int botonTexto = ContextCompat.getColor(actividad, R.color.boton_texto);

        assertTrue("Contraste insuficiente texto/fondo",
                ColorUtils.calculateContrast(textoPrincipal, fondoPantalla) >= CONTRASTE_MINIMO);
        assertTrue("Contraste insuficiente texto/botón",
                ColorUtils.calculateContrast(botonTexto, botonFondo) >= CONTRASTE_MINIMO);

        // Y la pantalla realmente usa esa paleta.
        Button boton = actividad.findViewById(R.id.botonQuieroHablar);
        assertEquals(botonTexto, boton.getTextColors().getDefaultColor());
    }

    @Test
    public void elBotonTieneDescripcionParaLectoresDeEscritura() {
        WelcomeActivity actividad = crearActividad();
        Button boton = actividad.findViewById(R.id.botonQuieroHablar);

        assertNotNull(boton.getContentDescription());
        assertTrue(boton.getContentDescription().toString().startsWith("Quiero hablar"));
    }

    // ---------- RNF-02: tono y lenguaje ----------

    @Test
    public void mantieneElTonoCalidoSinPrisas() {
        WelcomeActivity actividad = crearActividad();
        TextView lema = actividad.findViewById(R.id.textoLema);

        assertEquals("Sin prisas. A tu ritmo.", lema.getText().toString());
    }
}
