Biblioteca Mixta – Aplicación Android (Fragments, RecyclerView y JSON)

Biblioteca Mixta es una aplicación móvil desarrollada con Android Studio y Kotlin que permite mostrar y gestionar diferentes tipos de listas, como películas, juegos y libros, utilizando Fragments, RecyclerView y datos cargados desde archivos JSON.

El proyecto está orientado a practicar la arquitectura básica de aplicaciones Android, la comunicación entre fragments y la carga dinámica de datos desde ficheros estructurados.

Características principales

La aplicación permite visualizar tres listas diferentes: películas, juegos y libros. Cada una de estas listas se carga desde su propio archivo JSON, lo que facilita la separación de datos y una estructura más clara del proyecto.

Arquitectura basada en Fragments

La aplicación está compuesta por una Activity principal y dos fragments. La Activity principal es la encargada de gestionar el estado de la aplicación y la comunicación entre fragments. Uno de los fragments se utiliza para mostrar la lista mediante un RecyclerView, mientras que el otro actúa como menú para seleccionar el tipo de lista que se desea visualizar.

El fragmento del menú se muestra de forma superpuesta al fragmento de la lista, permitiendo una navegación clara y sencilla.

RecyclerView interactivo

Dentro del fragmento de la lista se utiliza un RecyclerView para mostrar los elementos correspondientes a la lista seleccionada. Cada elemento dispone de una imagen y un nombre, definidos en un layout propio en XML.

El RecyclerView permite seleccionar elementos, mostrando un cambio visual en el fondo, y eliminar elementos mediante una pulsación larga.

Comunicación entre Fragments

La comunicación entre el fragmento del menú y el fragmento de la lista se gestiona a través de la Activity principal. Cuando el usuario selecciona una opción en el menú, esta información se envía a la Activity, que guarda el identificador de la lista seleccionada y lo transmite al fragmento de la lista para que cargue los datos correspondientes.

Este enfoque permite mantener los fragments desacoplados y mejora la mantenibilidad del código.

Carga de datos desde JSON

Los datos de cada lista se leen desde archivos JSON locales. Cada archivo contiene las entradas correspondientes a una lista concreta, incluyendo al menos el nombre y la imagen asociada de cada elemento.

La lectura y el procesamiento de los datos se realizan dentro del fragmento encargado de mostrar la lista, desde donde se pasan al adapter del RecyclerView.

Gestión de la navegación

Al iniciar la aplicación se muestra el fragmento de la lista, inicialmente vacío. Al pulsar el botón de menú se muestra el fragmento del menú sobre el fragmento de la lista. Al volver a pulsar, el menú se oculta y se actualiza el contenido mostrado según la opción seleccionada.

Soporte multi-idioma

La aplicación incluye soporte para múltiples idiomas mediante el uso de archivos strings.xml, permitiendo adaptar los textos visibles de la interfaz al idioma seleccionado.

Descargar la APK

La aplicación puede descargarse e instalarse en un dispositivo Android desde el siguiente enlace:

https://github.com/Khaoss02/BibliotecaMixta/releases/tag/v1.0.0
