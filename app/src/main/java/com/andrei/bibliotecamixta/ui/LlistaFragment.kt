package com.andrei.bibliotecamixta.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrei.bibliotecamixta.R
import com.andrei.bibliotecamixta.databinding.FragmentLlistaBinding
import com.andrei.bibliotecamixta.model.Item
import com.andrei.bibliotecamixta.model.TipusLlista
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

/**
 * Fragment principal que s'encarrega de mostrar la llista d'elements (llibres, pelis o jocs).
 */
class LlistaFragment : Fragment() {

    // Variables per fer servir el View Binding (per no usar findViewById)
    private var _binding: FragmentLlistaBinding? = null
    private val binding get() = _binding!!

    // L'adaptador que connecta les dades amb la llista visual
    private lateinit var itemAdapter: ItemAdapter

    // Carreguem el XML
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLlistaBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Quan la pantalla ja està creada, configurem els elements
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Preparem el RecyclerView
        itemAdapter = ItemAdapter(mutableListOf()) // Comencem amb la llista buida

        binding.recyclerViewLlista.apply {
            layoutManager = LinearLayoutManager(context) // Llista vertical normal
            adapter = itemAdapter
        }

    }

    /**
     * Aquesta funció la crida la MainActivity quan triem una opció al menú.
     * Serveix per canviar el títol i carregar les dades noves.
     */
    fun actualitzarLlista(tipus: TipusLlista) {

        // Canviem el títol de dalt
        // Fem servir un 'when' per triar el text correcte del fitxer strings.xml
        val titolResId = when (tipus) {
            TipusLlista.LLIBRES -> R.string.opcio_llibres
            TipusLlista.PELICULES -> R.string.opcio_pelicules
            TipusLlista.JOCS -> R.string.opcio_jocs
            else -> R.string.llista_buida
        }

        // Assignem el text traduït
        binding.textViewTitolLlista.text = getString(titolResId)

        // Carreguem els items del fitxer JSON
        val items = carregarDadesJSON(tipus)

        itemAdapter.updateData(items)
    }

    /**
     * Funció privada que llegeix els fitxers de la carpeta 'raw' i els converteix en objectes.
     */
    private fun carregarDadesJSON(tipus: TipusLlista): List<Item> {
        // Si no hem triat res, no fem res
        if (tipus == TipusLlista.CAP_SELECCIONAT) return emptyList()

        try {
            // Traiem l'extensió .json per obtenir només el nom
            val nomRecurs = tipus.jsonFile.removeSuffix(".json")

            // Busquem l'ID numèric del recurs a la carpeta res/raw
            val resourceId = resources.getIdentifier(
                nomRecurs,
                "raw",
                context?.packageName
            )

            // Si no troba el fitxer, avisem i sortim
            if (resourceId == 0) {
                Toast.makeText(context, "Error: No s'ha trobat el fitxer", Toast.LENGTH_LONG).show()
                return emptyList()
            }

            // Llegim el fitxer
            val inputStream = resources.openRawResource(resourceId)
            val reader = InputStreamReader(inputStream)

            // Convertim el text JSON a una llista d'Items
            val listType = object : TypeToken<List<Item>>() {}.type
            return Gson().fromJson(reader, listType)

        } catch (e: Exception) {
            // Si passa alguna cosa rara llegint, mostrem error
            e.printStackTrace()
            Toast.makeText(context, "Error llegint JSON", Toast.LENGTH_SHORT).show()
            return emptyList()
        }
    }

    // Neteja el binding quan tanquem el fragment per estalviar memòria
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
