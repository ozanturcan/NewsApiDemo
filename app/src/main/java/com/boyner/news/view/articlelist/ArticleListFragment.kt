package com.boyner.news.view.articlelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boyner.news.App
import com.boyner.news.R
import com.boyner.news.view.BaseFragment
import com.boyner.news.view.ItemClickListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.articles_fragment.*
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit


class ArticleListFragment : BaseFragment() {

    private val articleListViewModel = App.injectArticleListViewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.articles_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val sourceItem = arguments?.getString("SOURCE_ITEM").orEmpty()

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply { this.setDrawable(resources.getDrawable(R.drawable.line_divider, null)) }
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        article_list_recycler.layoutManager = layoutManager
        article_list_recycler.addItemDecoration(itemDecoration)

        subscribe(articleListViewModel.getArticleList(1, sourceItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    subscribe(Observable.interval(1000L, TimeUnit.MILLISECONDS)
                            .timeInterval()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                articleListViewModel.getArticleList(1, sourceItem)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe({
                                            Timber.d("interval ${it.articleList.size} articleList.")
                                            val articleListAdapter = article_list_recycler.adapter as ArticleListAdapter
                                            articleListAdapter.updateEmployeeListItems(it.articleList)
                                        }, {
                                            showError("onStart", it)
                                        })
                            })
                }
                .subscribe({
                    Timber.d("Received UIModel $it articleList.")
                    showArticles(it)
                }, {
                    showError("onStart", it)
                }))


    }


    private fun showArticles(articleList: ArticleList) {
        if (articleList.error == null) {
            article_list_recycler.adapter = ArticleListAdapter(articleList.articleList.toMutableList())
                    .also {
                        it.setListener(object : ItemClickListener {
                            override fun onItemClick(v: View, position: Int) {
                                if (position != -1) {
                                    Toast.makeText(context, articleList.articleList[position].description, Toast.LENGTH_SHORT).show()
                                    onBrowseClick(articleList.articleList[position].url.orEmpty())
                                } else Toast.makeText(context, "position :$position", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
        } else if (articleList.error is ConnectException || articleList.error is UnknownHostException) {
            Timber.d("No connection, maybe inform News that articleList loaded from DB.")
        } else {
            showError("ShowArticle")
        }
    }

    fun onBrowseClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Note the Chooser below. If no applications match,
        // Android displays a system message.So here there is no need for try-catch.
        startActivity(Intent.createChooser(intent, "Browse with"))

    }


}
