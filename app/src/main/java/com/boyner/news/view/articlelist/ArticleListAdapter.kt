package com.boyner.news.view.articlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boyner.news.network.local.entity.Article
import com.boyner.news.view.ItemClickListener
import androidx.recyclerview.widget.DiffUtil
import com.boyner.news.R


class ArticleListAdapter : RecyclerView.Adapter<ArticleListItemViewHolder>() {
    private var articleList: MutableList<Article> = emptyList<Article>().toMutableList()
    private lateinit var articleListItemlistener: ItemClickListener
    private lateinit var readListTextViewListener: ItemClickListener

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item_viewholder, parent, false)
        return ArticleListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleListItemViewHolder, position: Int) {
        holder.bindView(articleList[position]).setArticleItemListener(articleListItemlistener)
        holder.bindView(articleList[position]).setReadListTextViewListener(readListTextViewListener)
    }

    fun setArticleItemListener(listener: ItemClickListener) {
        this.articleListItemlistener = listener
    }

    fun setReadListTextViewListener(listener: ItemClickListener) {
        this.readListTextViewListener = listener
    }

    fun updateEmployeeListItems(newArticleList: List<Article>) {
        val diffCallback = ArticleDiffCallback(articleList, newArticleList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        articleList.clear()
        articleList.addAll(newArticleList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setList(list: List<Article>) {
        this.articleList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun addData(listOfSigns: List<Article>) {
        updateEmployeeListItems(listOfSigns)
        notifyDataSetChanged()
    }
}
