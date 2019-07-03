package com.boyner.news.view.articlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boyner.news.network.local.entity.Article
import com.boyner.news.view.ItemClickListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.DiffUtil
import com.boyner.news.R


class ArticleListAdapter(private val articleList: MutableList<Article>) : RecyclerView.Adapter<ArticleListItemViewHolder>() {

    private lateinit var listener: ItemClickListener

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.articlelist_viewholder, parent, false)
        return ArticleListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleListItemViewHolder, position: Int) {
        val articleItem = articleList[position]
        holder.textViewTitle.text = articleItem.title
        holder.textViewTime.text = articleItem.publishedAt
        holder.linearLayout.setOnClickListener { v ->
            listener.onItemClick(v, position)
        }
        Picasso.get()
                .load(articleItem.urlToImage).error(R.drawable.ic_image_blue_24dp).fetch(object : Callback {
                    override fun onSuccess() {
                        holder.imageViewTitle.alpha = 0f
                        Picasso.get().load(articleItem.urlToImage).error(R.drawable.ic_image_blue_24dp)
                                .into(holder.imageViewTitle)
                        holder.imageViewTitle.animate().setDuration(300).alpha(1f).start(); }

                    override fun onError(e: Exception?) {
                    }
                })

    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }

    fun updateEmployeeListItems(newArticleList: List<Article>) {
        val diffCallback = ArticleDiffCallback(articleList, newArticleList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        articleList.clear()
        articleList.addAll(newArticleList)
        diffResult.dispatchUpdatesTo(this)
    }
}
