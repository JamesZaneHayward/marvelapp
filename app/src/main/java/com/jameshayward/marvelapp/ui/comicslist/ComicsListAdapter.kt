package com.jameshayward.marvelapp.ui.comicslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jameshayward.marvelapp.R
import com.jameshayward.marvelapp.domain.comic.Comic
import com.squareup.picasso.Picasso


class ComicListAdapter(items: List<Comic>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var items: List<Comic> = items
    set(value) {
        field = value
        comicListFiltered = value
    }

    var comicListFiltered = items

    override fun getItemCount(): Int {
        return comicListFiltered.size
    }

    override fun getItemId(position: Int): Long {
        return comicListFiltered[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cell_comic,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bindTo(comicListFiltered[position])
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                val filteredList: MutableList<Comic> = ArrayList()

                filteredList.clear()
                if (charString.isEmpty()) {
                    filteredList.addAll(items)
                } else {
                    for (row in items) {
                        if (row.title.toLowerCase().contains(charString.toLowerCase().trim())) {
                            filteredList.add(row)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                comicListFiltered = filterResults.values as List<Comic>
                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }
}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val primaryText = itemView.findViewById<TextView>(R.id.cell_comic_primary_text)
    private val secondaryText = itemView.findViewById<TextView>(R.id.cell_comic_secondary_text)
    private val icon = itemView.findViewById<ImageView>(R.id.cell_comic_image)

    fun bindTo(item: Comic) {
        primaryText.text = item.title
        secondaryText.text =  item.description
        if (item.thumbnail.path.isNotEmpty()) {
            Picasso.with(itemView.context).load(item.thumbnail.path + "/standard_xlarge.jpg").into(icon)
        }
    }
}
