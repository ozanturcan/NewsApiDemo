package com.boyner.news.viewmodel

import com.boyner.news.network.SourceRepository
import com.boyner.news.view.sourcelist.SourceList
import io.reactivex.Observable
import timber.log.Timber

class SourceListViewModel(private val sourceRepository: SourceRepository) {

    fun getSourceList(): Observable<SourceList> {
        //Create the data for your UI, the sourceList lists and maybe some additional data needed as well
        return sourceRepository.getSourceService()
                .map {
                    Timber.d("Mapping sourceList to UIData...")
                    SourceList(it, "Top ${it.size} Sources")
                }
                .onErrorReturn {
                    Timber.w("An error occurred $it")
                    SourceList(emptyList(), "An error occurred", it)
                }
    }
}