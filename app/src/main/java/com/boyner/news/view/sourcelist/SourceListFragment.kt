package com.boyner.news.view.sourcelist

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
import com.boyner.news.view.articlelist.ArticleListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.sources_fragment.*
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException


class SourceListFragment : BaseFragment() {

    private val sourceListViewModel = App.injectSourceListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sources_fragment, container, false)
    }

    @Suppress("SpellCheckingInspection")
    override fun onStart() {
        super.onStart()
        subscribe(sourceListViewModel.getSourceList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received UIModel $it sourceList.")
                    showSources(it)
                }, {
                    Timber.w("error Recevied $it from sourceList")
                }))
    }


    private fun showSources(sourceList: SourceList) {
        if (sourceList.error == null) {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = RecyclerView.VERTICAL
            source_list_recycler.layoutManager = layoutManager

            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply { this.setDrawable(resources.getDrawable(R.drawable.line_divider, null)) }
            source_list_recycler.addItemDecoration(itemDecoration)
            source_list_recycler.adapter = SourceListAdapter(sourceList.sourceList)
            (source_list_recycler.adapter as SourceListAdapter).setListener(object : ItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    if (position != -1) {
                        val bundle = Bundle()
                        bundle.putString("SOURCE_ITEM", sourceList.sourceList[position].id)
                        Toast.makeText(context, sourceList.sourceList[position].name, Toast.LENGTH_SHORT).show()
                        changeFragmentWithBundle(ArticleListFragment(), bundle)
                    }
                }
            })
        } else if (sourceList.error is ConnectException || sourceList.error is UnknownHostException) {
            Timber.d("No connection, maybe inform Source that sourceList loaded from DB.")
        } else {
            showError(sourceList.message + sourceList.error, null)
        }
    }


}
