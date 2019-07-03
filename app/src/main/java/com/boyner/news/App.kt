package com.boyner.news

import android.app.Application
import androidx.room.Room
import com.boyner.news.network.ArticlesRepository
import com.boyner.news.network.SourceRepository
import com.boyner.news.network.remote.ArticleApi
import com.boyner.news.network.remote.SourceApi
import com.boyner.news.network.local.AppDatabase
import com.boyner.news.provider.retrofitBuilderProvider
import com.boyner.news.viewmodel.ArticleListViewModel
import com.boyner.news.viewmodel.SourceListViewModel
import timber.log.Timber


class App : Application() {

    companion object {

        private lateinit var sourceListViewModel: SourceListViewModel
        private lateinit var articleListViewModel: ArticleListViewModel
        private lateinit var appDatabase: AppDatabase
        private lateinit var sourceApi: SourceApi
        private lateinit var articlesApi: ArticleApi
        private lateinit var sourceRepository: SourceRepository
        private lateinit var articleRepository: ArticlesRepository


        fun injectSourceListViewModel() = sourceListViewModel
        fun injectArticleListViewModel() = articleListViewModel
    }

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())


        val retrofit = retrofitBuilderProvider().build()


        sourceApi = retrofit.create(SourceApi::class.java)
        articlesApi = retrofit.create(ArticleApi::class.java)
        appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, BuildConfig.DB_FILEPATH).fallbackToDestructiveMigration().build()

        sourceRepository = SourceRepository(sourceApi, appDatabase.sourceDao())
        articleRepository = ArticlesRepository(articlesApi, appDatabase.articleDao())
        sourceListViewModel = SourceListViewModel(sourceRepository)
        articleListViewModel = ArticleListViewModel(articleRepository)
    }
}