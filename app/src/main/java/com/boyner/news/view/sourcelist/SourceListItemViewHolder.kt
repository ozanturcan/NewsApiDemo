package com.boyner.news.view.sourcelist

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sourcelist_viewholder.view.*

class SourceListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewName: TextView = itemView.textView_source_name
    val textViewDescription: TextView = itemView.textView_source_description
    val linearLayoutCompat: LinearLayoutCompat = itemView.linearLayout_source_item
}