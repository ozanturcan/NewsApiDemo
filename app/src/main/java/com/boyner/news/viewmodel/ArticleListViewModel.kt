package com.boyner.news.viewmodel

import com.boyner.news.network.ArticlesRepository
import com.boyner.news.network.local.entity.Article
import com.boyner.news.view.articlelist.ArticleList
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class ArticleListViewModel(private val articlesRepository: ArticlesRepository) {

    fun getArticleList(page: Int, source: String): Observable<ArticleList> {
        return articlesRepository.getArticleService(page, source)
                .map {
                    Timber.d("Mapping newsList to UIData...")
                    ArticleList(it, "Top ${it.size} Article")
                }
                .onErrorReturn {
                    Timber.d("An error occurred $it")
                    ArticleList(emptyList(), "An error occurred", it)
                }
    }

    fun updateArticleItem(article: Article): Disposable? {
        return articlesRepository.updateArticleItemInDb(article)
    }
}