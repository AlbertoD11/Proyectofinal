package com.restauranteclick.turestauranteenunclick

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.restauranteclick.turestauranteenunclick.databinding.ItemRestauranteRecyclerViewBinding
import com.restauranteclick.turestauranteenunclick.entities.Restaurante

class RestauranteAdapter(private val restaurantes:MutableList<Restaurante>):RecyclerView.Adapter<RestauranteAdapter.ViewHolder>() {

    class ViewHolder private constructor(val binding:ItemRestauranteRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurante: Restaurante) {
binding.txtRestaurantName.text = restaurante.nombre
            binding.txtTiming.text = restaurante.telefono.toString()
            binding.txtCost.text = restaurante.direccion


        }

        companion object {
            fun newInstance(parent:ViewGroup):ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRestauranteRecyclerViewBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var restaurante = restaurantes [position]
        holder.bind (restaurante)

    }

    override fun getItemCount() = restaurantes.size
    fun addRestaurante(restaurante: Restaurante) {
        this.restaurantes.add(restaurante)
        notifyItemInserted(this.restaurantes.size)

    }

}