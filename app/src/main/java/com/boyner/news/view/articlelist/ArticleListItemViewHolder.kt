package com.boyner.news.view.articlelist

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.articlelist_viewholder.view.*

class ArticleListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewTime: AppCompatTextView = itemView.textView_time
    val textViewTitle: AppCompatTextView = itemView.textView_title
    val imageViewTitle: AppCompatImageView = itemView.imageView_Uri
    val linearLayout: LinearLayoutCompat = itemView.linearLayout_article_item
}