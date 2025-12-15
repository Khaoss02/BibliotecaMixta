package com.andrei.bibliotecamixta.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.andrei.bibliotecamixta.MenuSelectionListener
import com.andrei.bibliotecamixta.databinding.FragmentMenuBinding
import com.andrei.bibliotecamixta.model.TipusLlista

/**
 * Fragment que es mostra com una finestra flotant (Dialog) per triar què volem veure
 */
class MenuFragment : DialogFragment() {

    // Binding per accedir als botons del menú
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    // Interface per avisar a la MainActivity de què hem triat
    private lateinit var listener: MenuSelectionListener

    // Aquest mètode s'executa quan el fragment s'enganxa a l'Activity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MenuSelectionListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context ha d'implementar MenuSelectionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurem què passa quan cliquem cada botó del menú

        binding.btnLlibres.setOnClickListener {
            listener.onMenuOptionSelected(TipusLlista.LLIBRES)
            dismiss() // Tanquem el menú
        }

        binding.btnPelicules.setOnClickListener {
            listener.onMenuOptionSelected(TipusLlista.PELICULES)
            dismiss()
        }

        binding.btnJocs.setOnClickListener {
            listener.onMenuOptionSelected(TipusLlista.JOCS)
            dismiss()
        }

        // Botó per tancar
        binding.btnCancelar.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
