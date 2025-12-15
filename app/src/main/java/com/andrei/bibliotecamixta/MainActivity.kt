package com.andrei.bibliotecamixta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andrei.bibliotecamixta.databinding.ActivityMainBinding
import com.andrei.bibliotecamixta.model.TipusLlista
import com.andrei.bibliotecamixta.ui.LlistaFragment
import com.andrei.bibliotecamixta.ui.MenuFragment

/**
 * Interfície que fem servir per connectar el menú amb l'Activity principal
 */
interface MenuSelectionListener {
    fun onMenuOptionSelected(tipus: TipusLlista)
}

/**
 * L'Activity principal que controla tota l'app i escolta el menú
 */
class MainActivity : AppCompatActivity(), MenuSelectionListener {

    private lateinit var binding: ActivityMainBinding
    private val menuFragment = MenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Activem el View Binding
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_llista, LlistaFragment())
                .commit()
        }

        // Quan toquem el botó flotant gran, obrim el menú
        binding.fabMenu.setOnClickListener {
            mostrarMenu()
        }
    }

    // Funció per ensenyar el menú flotant
    private fun mostrarMenu() {
        if (!menuFragment.isAdded) {
            menuFragment.show(supportFragmentManager, "MenuFragmentTag")
        }
    }

    // Aquesta funció salta quan hem triat una opció al menú
    override fun onMenuOptionSelected(tipus: TipusLlista) {
        // Tanquem el menú
        if (menuFragment.isVisible) {
            menuFragment.dismiss()
        }

        // Busquem el fragment de la llista que tenim a la pantalla
        val llistaFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_llista)

        // Si el trobem, li diem que actualitzi les dades amb el que hem triat
        if (llistaFragment is LlistaFragment) {
            llistaFragment.actualitzarLlista(tipus)
        }
    }
}
