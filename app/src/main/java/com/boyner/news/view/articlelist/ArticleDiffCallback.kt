package com.boyner.news.view.articlelist

import androidx.recyclerview.widget.DiffUtil
import com.boyner.news.network.local.entity.Article

class ArticleDiffCallback(private val mOldArticleList: List<Article>, private val mNewArticleList: List<Article>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldArticleList.size
    }

    override fun getNewListSize(): Int {
        return mNewArticleList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mNewArticleList[newItemPosition].url == mOldArticleList[oldItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldArticleList[oldItemPosition] == mNewArticleList[newItemPosition]
    }
}
