package com.andrei.bibliotecamixta.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.andrei.bibliotecamixta.databinding.ItemLlistaBinding
import com.andrei.bibliotecamixta.model.Item

/**
 * Adaptador per connectar les dades (Llista d'Items) amb el RecyclerView
 */
class ItemAdapter(private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Guardem quina posició està seleccionada per canviar-li el color
    private var selectedPosition = RecyclerView.NO_POSITION

    /**
     * Classe interna que guarda les referències a les vistes de cada fila
     */
    inner class ItemViewHolder(private val binding: ItemLlistaBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

        init {
            // Posem els listeners (clic normal i clic llarg) a tota la fila
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        fun bind(item: Item) {
            // Posem el nom al TextView
            binding.textViewNom.text = item.nom

            // Carreguem la imatge d'Internet fent servir la llibreria Coil
            binding.imageViewItem.load(item.urlImatge) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
                transformations(RoundedCornersTransformation(16f))
            }

            // Canviem el color de fons si l'element està seleccionat
            val isSelected = adapterPosition == selectedPosition
            val color = if (isSelected) {
                Color.parseColor("#ADD8E6")
            } else {
                Color.WHITE // Blanc
            }
            binding.cardView.setCardBackgroundColor(color)
        }

        // Quan fem un clic normal: seleccionem l'element
        override fun onClick(v: View?) {
            if (adapterPosition == RecyclerView.NO_POSITION) return

            // Guardem l'anterior per despintar-lo
            val previousSelected = selectedPosition
            selectedPosition = adapterPosition

            // Avisem al adaptador que actualitzi els colors
            if (previousSelected != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousSelected)
            }
            notifyItemChanged(selectedPosition)
        }

        // Quan fem un clic llarg: esborrem l'element
        override fun onLongClick(v: View?): Boolean {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val positionToRemove = adapterPosition

                // Treiem l'item de la llista i avisem al RecyclerView
                items.removeAt(positionToRemove)
                notifyItemRemoved(positionToRemove)

                // Si hem esborrat el seleccionat, reiniciem la selecció
                if (selectedPosition == positionToRemove) {
                    selectedPosition = RecyclerView.NO_POSITION
                } else if (selectedPosition > positionToRemove) {
                    selectedPosition--
                }
                return true // Retornem true perquè sàpiga que hem gestionat el clic
            }
            return false
        }
    }

    // Crea la vista visual per a cada fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemLlistaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    // Connecta les dades amb la vista (es crida per cada element)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // Diu quants elements hi ha en total
    override fun getItemCount(): Int = items.size

    // Funció per canviar totes les dades de cop quan triem una opció del menú
    fun updateData(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        selectedPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }
}
