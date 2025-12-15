package com.andrei.bibliotecamixta.model

enum class TipusLlista(val nom: String, val jsonFile: String) {

    // Categories amb el nom i el fitxer a res/raw
    LLIBRES("Llibres", "llibres.json"),
    PELICULES("Pel·lícules", "peliculas.json"),
    JOCS("Jocs", "jocs.json"),
    CAP_SELECCIONAT("Cap", "")
}