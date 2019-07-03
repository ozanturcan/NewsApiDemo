package com.boyner.news.network

import com.boyner.news.network.remote.SourceApi
import com.boyner.news.network.local.entity.Source
import com.boyner.news.network.local.entity.SourceService
import com.boyner.news.network.local.dao.SourceDao
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SourceRepository(private val newsApi: SourceApi, private val sourceDao: SourceDao) {

    fun getSourceService(): Observable<List<Source>> {
        return Observable.concatArray(getSourceServiceFromDb(),
                getSourceServiceFromApi().map { it.sources })
    }


    private fun getSourceServiceFromDb(): Observable<List<Source>>? {
        return sourceDao.getSourceService().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} sourceService from DB...")
                }
    }

    private fun getSourceServiceFromApi(): Observable<SourceService> {
        return newsApi.getSourceService()
                .doOnNext {
                    Timber.d("Dispatching ${it.sources.size} sourceService from API...")
                    storeSourceServiceInDb(it.sources)
                }.doOnError {
                    Timber.d("Dispatching error : ${it.localizedMessage}  sourceService from API...")
                }
    }

    private fun storeSourceServiceInDb(sourceService: List<Source>): Disposable? {
        return Observable.fromCallable { sourceDao.insertAll(sourceService) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${sourceService.size} sourceService from API in DB...")
                }
    }

}
