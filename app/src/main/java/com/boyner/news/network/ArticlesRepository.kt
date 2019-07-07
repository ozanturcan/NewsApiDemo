package com.boyner.news.network

import com.boyner.news.network.local.dao.ArticlesDao
import com.boyner.news.network.local.entity.Article
import com.boyner.news.network.local.entity.ArticlesService
import com.boyner.news.network.remote.ArticleApi
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class ArticlesRepository(private val articlesApi: ArticleApi, private val articlesDao: ArticlesDao) {

    fun getArticleService(page: Int, source: String): Observable<List<Article>> {
        return Observable.concatArray(getArticleServiceFromDb(source),
                getArticleServiceFromApi(page, source).map { it.articles })
    }


    private fun getArticleServiceFromDb(source: String): Observable<List<Article>>? {
        return articlesDao.getArticlesService(source).filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} articleService from DB...")
                }
    }

    private fun getArticleServiceFromApi(page: Int, source: String): Observable<ArticlesService> {
        return articlesApi.getArticleService(page, source)
                .doOnNext {
                    Timber.d("Dispatching ${it.articles.size} articleService from API...")
                    storeArticlesServiceInDb(it.articles)
                }.doOnError {
                    Timber.d("Dispatching error : ${it.localizedMessage}  articleService from API...")
                }
    }

    private fun storeArticlesServiceInDb(articleList: List<Article>): Disposable? {
        return Observable.fromCallable { articlesDao.insertAll(articleList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${articleList.size} articleService from API in DB...")
                }
    }

    fun updateArticleItemInDb(article: Article): Disposable? {
        return Observable.fromCallable { articlesDao.updateArticle(article) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Updated ${article.isInReadList} in article from API in DB...")
                }
    }

}
