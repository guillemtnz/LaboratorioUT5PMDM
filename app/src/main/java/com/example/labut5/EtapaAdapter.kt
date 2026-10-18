package com.example.labut5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class EtapaAdapter(
    private val context: Context,
    private val listaEtapas: List<Etapa>
) : BaseAdapter() {

    //ViewHolder
    private class ViewHolder(view: View) {
        val ivTerreno: ImageView = view.findViewById(R.id.ivTerreno)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvPeligro: TextView = view.findViewById(R.id.tvPeligro)
    }

    override fun getCount(): Int = listaEtapas.size

    override fun getItem(position: Int): Any = listaEtapas[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            // Inflar y guardar holder
            view = LayoutInflater.from(context).inflate(R.layout.item_etapa, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            // Reutilizar holder
            view = convertView
            holder = view.tag as ViewHolder
        }

        val etapa = listaEtapas[position]

        holder.ivTerreno.setImageResource(etapa.imagenResId)
        holder.tvNombre.text = etapa.nombre
        holder.tvPeligro.text = context.getString(R.string.peligro_label, etapa.peligro)

        return view
    }
}
