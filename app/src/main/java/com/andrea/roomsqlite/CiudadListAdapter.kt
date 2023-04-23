package com.andrea.roomsqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class CiudadListAdapter :
    ListAdapter<Ciudad, CiudadListAdapter.CiudadViewHolder>(CIUDAD_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder {
        return CiudadViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CiudadViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.nombre)
    }

    class CiudadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ciudadItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            ciudadItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): CiudadViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CiudadViewHolder(view)
            }
        }
    }

    companion object {
        private val CIUDAD_COMPARATOR = object : DiffUtil.ItemCallback<Ciudad>() {
            override fun areItemsTheSame(oldItem: Ciudad, newItem: Ciudad): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Ciudad, newItem: Ciudad): Boolean {
                return oldItem.nombre == newItem.nombre
            }
        }

    }

}