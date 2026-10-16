# Tareas: MVP de Solicitud de Acompañamiento ("¿Hablamos?")

- [x] **T1: Implementación de la Pantalla de Bienvenida (Frame 1)**
  - **RF cubierto:** RF-01, RNF-01, RNF-02
  - **Descripción:** Crear la vista inicial móvil con el mensaje de bienvenida y el botón grande de "Quiero hablar".
  - **Hecho cuando:** La pantalla se renderiza correctamente con tipografía grande y el botón navega hacia la pantalla de introducción del tema.

- [x] **T2: Implementación de la Validación y Bloqueo del Tema (Frame 2)**
  - **RF cubierto:** RF-02, RF-03, RF-04, RNF-01
  - **Descripción:** Desarrollar el campo de texto con límite de 100 caracteres y la lógica para deshabilitar el botón si está vacío o solo tiene espacios.
  - **Hecho cuando:** El botón de enviar permanece deshabilitado si el texto está vacío, se bloquea inmediatamente al primer clic para evitar duplicados y lanza la petición si es válido.

- [ ] **T3: Lógica del Servicio de Llamadas y Manejo de Errores de Red**
  - **RF cubierto:** RF-04, RF-05, LC-02
  - **Descripción:** Programar `CallService` para gestionar la comunicación, el envío del número registrado y la cancelación automática si la red falla en el momento del envío.
  - **Hecho cuando:** Una pérdida de red al pulsar enviar cancela la operación de forma segura y devuelve al usuario el control de la pantalla.

- [ ] **T4: Implementación de la Pantalla de Espera, Timeout y Cancelación (Frame 3)**
  - **RF cubierto:** RF-05, RF-06, RF-07
  - **Descripción:** Crear la vista de "Preparando tu conversación", configurar el temporizador de cuenta atrás de 2 minutos y asegurar que cerrar la pantalla cancela la petición.
  - **Hecho cuando:** Si pasan 120 segundos redirige al Frame 4, si el usuario sale se cancela la petición, y permite recibir la llamada telefónica convencional simulada en segundo plano.

- [ ] **T5: Implementación de la Pantalla de Sin Voluntarios (Frame 4)**
  - **RF cubierto:** RF-06, RNF-01
  - **Descripción:** Diseñar la vista "Ahora no hay voluntarios disponibles" informando que se llamará al número registrado, con botón de retorno al inicio.
  - **Hecho cuando:** La pantalla muestra el mensaje claro de que se llamará al número registrado y permite volver a la pantalla de bienvenida.