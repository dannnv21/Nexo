# Especificación: MVP de Solicitud de Acompañamiento ("¿Hablamos?")

## 1. Contexto y Objetivo
El objetivo de este MVP es permitir que una persona mayor solicite de manera sencilla, rápida y accesible una conversación telefónica con un voluntario. La interfaz debe priorizar la claridad, el uso de textos grandes y un flujo guiado sin fricciones, basándose en los diseños de la aplicación móvil.

## 2. Usuarios
- **Persona Mayor (Usuario Principal):** Persona que busca compañía o conversación, requiriendo una interfaz sumamente clara, botones grandes y un tono empático ("Sin prisas. A tu ritmo").
- **Voluntario:** Persona disponible para recibir el aviso y realizar la llamada de acompañamiento.

## 3. Historias de Usuario
- **HU-01 (Solicitud de conversación):** Como persona mayor, quiero pulsar un botón de bienvenida para iniciar el proceso de hablar con alguien cuando lo necesite.
- **HU-02 (Aporte de tema):** Como persona mayor, quiero indicar brevemente (máximo 100 caracteres) sobre qué me apetece hablar para que el voluntario tenga un contexto inicial.
- **HU-03 (Espera, bloqueo y cancelación):** Como persona mayor, quiero saber que el sistema está buscando un voluntario de forma segura (bloqueando el botón para evitar duplicados), con la opción de cancelar si cierro la pantalla o si falla la red, o recibir una llamada normal en mi móvil cuando se encuentre a alguien.

## 4. Requisitos Funcionales (Notación EARS)

- **RF-01 (Pantalla de Bienvenida):** CUANDO la aplicación móvil se inicie, ENTONCES el sistema mostrará el Frame 1 con un mensaje de bienvenida cálido y un botón principal de tamaño grande con el texto "Quiero hablar".
- **RF-02 (Navegación al Tema):** CUANDO el usuario pulse el botón "Quiero hablar", ENTONCES el sistema mostrará el Frame 2 permitiendo introducir un tema de conversación.
- **RF-03 (Obligatoriedad y Límite del Tema):** MIENTRAS el usuario redacte el tema de conversación, EL SISTEMA DEBE exigir que el campo no esté vacío (ni solo espacios) y limitar la longitud máxima a 100 caracteres antes de habilitar el botón "Enviar".
- **RF-04 (Inicio de Búsqueda y Bloqueo de Botón):** CUANDO el usuario pulse el botón "Enviar" con un tema válido, ENTONCES el sistema bloqueará inmediatamente el botón para evitar dobles envíos, registrará la petición utilizando el número de teléfono guardado y mostrará el Frame 3 de "Preparando tu conversación".
- **RF-05 (Cancelación por Cierre o Fallo de Red):** MIENTRAS el usuario esté en la pantalla de espera (Frame 3), SI el usuario cierra la pantalla o se interrumpe la red en el momento del envío, ENTONCES el sistema cancelará automáticamente la petición de acompañamiento.
- **RF-06 (Timeout de Búsqueda):** SI transcurren 2 minutos en la pantalla de espera (Frame 3) sin que ningún voluntario acepte la solicitud, ENTONCES el sistema redirigirá automáticamente al usuario al Frame 4 ("Ahora no hay voluntarios disponibles").
- **RF-07 (Aviso por Llamada Telefónica Normal):** CUANDO un voluntario esté disponible y acepte la solicitud, ENTONCES el sistema mantendrá visualmente al usuario en el Frame 3 mientras emite una llamada telefónica convencional al número registrado del dispositivo.

## 5. Requisitos No Funcionales
- **RNF-01 (Accesibilidad):** La interfaz móvil debe utilizar tipografías grandes, contrastes elevados y áreas de toque amplias en todos los botones (Frames 1 al 4).
- **RNF-02 (Tono y Lenguaje):** Todos los textos de la interfaz deben estar en español y mantener un tono paciente, empático y respetuoso.

## 6. Casos Límite
- **LC-01:** ¿Qué ocurre si el usuario intenta enviar el tema con 0 caracteres o solo espacios en blanco? El botón de "Enviar" permanecerá deshabilitado.
- **LC-02:** ¿Qué pasa si la conexión del móvil falla justo en el momento de pulsar "Enviar" en el Frame 2? La petición se cancelará de forma segura y se informará al usuario (cumpliendo con RF-05).

## 7. Fuera de Alcance (Fuera del MVP)
- Chat de texto interno dentro de la aplicación.
- Registro o creación de cuentas de usuario desde la propia app (se asume número precargado/registrado).
- Historial de llamadas anteriores para el usuario mayor.

## 8. Criterios de Finalización
- El flujo completo desde el Frame 1 hasta el Frame 4 (incluyendo el bloqueo anti-duplicados del botón, la validación de 100 caracteres y el temporizador de 2 minutos) funciona de manera fluida en entorno móvil.
- Se pasan todas las pruebas automatizadas asociadas a estos requisitos.

## 9. Dudas Abiertas
- *[NECESITA ACLARACIÓN]*: ¿Se requiere algún sonido específico o locución por voz dentro de la app para personas con dificultades visuales severas en futuras versiones? (Fuera de este MVP).