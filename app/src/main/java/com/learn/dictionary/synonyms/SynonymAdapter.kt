package com.learn.dictionary.synonyms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learn.dictionary.R
import com.learn.dictionary.model.list
import kotlinx.android.synthetic.main.synonym_list_item.view.*

class SynonymAdapter: RecyclerView.Adapter<SynonymAdapter.ViewHolder>() {

    var dataSet: List<list>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.synonym_list_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int = dataSet?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet?.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item:list){
            with(itemView){
                definitionTV.text = item.definition
            }
        }
    }
}