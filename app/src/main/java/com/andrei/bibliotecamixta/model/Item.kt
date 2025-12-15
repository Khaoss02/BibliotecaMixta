package com.andrei.bibliotecamixta.model

data class Item(
    val id: Int,
    val nom: String,
    val descripcio: String,
    val urlImatge: String, // La URL de la imatge
    val tipus: String // "llibre", "pelicula", "joc"
)