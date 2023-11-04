package net.azarquiel.recyclerviewPokemons.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.pokemon.R
import net.azarquiel.pokemon.model.Pokemon


class AdapterPokemon(val context: Context,
                     val layout: Int
                    ) : RecyclerView.Adapter<AdapterPokemon.ViewHolder>() {

    private var dataList: List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setPokemons(pokemons: List<Pokemon>) {
        this.dataList = pokemons
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Pokemon){
            // itemview es el row del diseño
            // al que hay que poner los datos del objeto dataItem
            val ivrowpokemon = itemView.findViewById(R.id.ivrowpokemon) as ImageView
            val tvrowpokemon = itemView.findViewById(R.id.tvrowpokemon) as TextView

            tvrowpokemon.text = dataItem.name

            // foto de internet a traves de Picasso
            Picasso.get().load("${dataItem.foto}").into(ivrowpokemon)

            itemView.tag = dataItem

        }

    }
}