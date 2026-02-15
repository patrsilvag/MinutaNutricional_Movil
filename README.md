# App Minuta Nutricional - Semana 6

Este proyecto corresponde a la implementación del Front End para la aplicación de Minuta Nutricional, 
desarrollada íntegramente en **Jetpack Compose** bajo el lenguaje **Kotlin**.

## 🚀 Cumplimiento de Requerimientos (Equivalencias Compose)

Siguiendo las instrucciones de la sesión sincrónica y el mensaje del docente, el desarrollo se realizó de forma 100% declarativa, 
evitando el uso de XML y layouts tradicionales. 
A continuación, se detallan las equivalencias de los componentes solicitados en la guía de la Semana 6:

### 1. Estructura y Organización (Grupo 1)
* **Activity**: Se utiliza una única actividad principal (`MainActivity.kt`) que hereda de `ComponentActivity`, actuando como el contenedor de la aplicación.
* **Fragments**: Reemplazados por funciones **Composables modulares** (ej. `PantallaLogin`, `PantallaMinuta`). Esto permite una interfaz dinámica y adaptable sin la complejidad del ciclo de vida de los Fragments clásicos.
* **ViewGroups**: Se implementó la jerarquía de vistas utilizando contenedores modernos como `Column`, `Row` y `Box` en lugar de LinearLayout o FrameLayout.

### 2. Componentes Visuales y Eventos (Grupo 2)
* **Views & Widgets**: Implementación de componentes de **Material3** como `Text`, `OutlinedTextField`, `Icon` y `Card`.
* **Buttons**: Uso de `Button` y `TextButton` con estilos personalizados definidos en el tema.
* **Events**: Gestión de eventos mediante el estado de Compose (`remember`, `mutableStateOf`) y lambdas para la interacción del usuario (clics, cambios de texto y navegación).

### 3. Paletas y Gestión de Datos (Grupo 3)
* **Palettes**: Aplicación de un sistema de diseño consistente mediante `MinutaNutricionalTheme`, utilizando la paleta de colores oficial de Material Design 3.
* **Content Navigation**: El intercambio y flujo de datos entre pantallas se gestiona a través de un sistema de navegación basado en estados en `AppNavigation.kt`, permitiendo pasar objetos complejos (como `Receta`) de forma segura.
* **Widgets Avanzados**: Implementación de un `ComboBoxDia` personalizado utilizando `ExposedDropdownMenuBox` para una selección fluida de filtros.

## 🛠️ Tecnologías Utilizadas
* **Kotlin**
* **Jetpack Compose** (UI moderna declarativa)
* **Material Design 3**
* **Single Activity Architecture**

## 📂 Estructura del Proyecto
* `/ui/theme`: Configuración de colores, tipografía y tema global.
* `/screens`: Pantallas principales de la aplicación (Login, Registro, Minuta, Detalle).
* `/components`: Componentes reutilizables (Tarjetas, Tablas, Fondo Curvo).
* `/navigation`: Lógica de navegación entre pantallas.