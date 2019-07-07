package com.boyner.news.view.sourcelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boyner.news.R
import com.boyner.news.network.local.entity.Source
import com.boyner.news.view.ItemClickListener


class SourceListAdapter(private val sourceList: List<Source>) : RecyclerView.Adapter<SourceListItemViewHolder>() {

    private lateinit var listener: ItemClickListener

    init {

    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return sourceList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.source_list_item_viewholder, parent, false)
        return SourceListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourceListItemViewHolder, position: Int) {
        holder.textViewName.text = sourceList[position].name
        holder.textViewDescription.text = sourceList[position].description

        holder.linearLayoutCompat.setOnClickListener { v ->
            listener.onItemClick(v, position)
        }
    }
}
