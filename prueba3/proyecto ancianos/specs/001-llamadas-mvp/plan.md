# Plan Técnico: MVP de Solicitud de Acompañamiento ("¿Hablamos?")

## 1. Estructura de Módulos (Android Java)
- `src/main/java/com/nexo/app/WelcomeActivity.java` -> Gestiona el **RF-01** (Frame 1).
- `src/main/java/com/nexo/app/TopicActivity.java` -> Gestiona el **RF-02**, **RF-03** y el bloqueo de doble clic (**RF-04**).
- `src/main/java/com/nexo/app/WaitingActivity.java` -> Gestiona el **RF-04**, **RF-05**, **RF-06** y **RF-07**.
- `src/main/java/com/nexo/app/NoVolunteersActivity.java` -> Gestiona el **RF-06** (Frame 4).
- `src/main/java/com/nexo/app/CallService.java` -> Lógica de negocio para el registro, control de red, temporizador y disparo de llamada telefónica.

## 2. Modelo de Datos (JSON)
```json
{
  "request_id": "req_987654321",
  "user_phone": "+34600111222",
  "topic": "Me gustaría charlar sobre mi día y mis plantas",
  "status": "searching",
  "created_at": "2026-06-06T10:00:00Z",
  "timeout_seconds": 120
}
```
