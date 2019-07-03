package com.boyner.news

import com.boyner.news.network.SourceRepository
import com.boyner.news.network.remote.SourceApi
import com.boyner.news.network.local.entity.Source
import com.boyner.news.network.local.dao.SourceDao
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`


class SourceRepositoryTest {

    private lateinit var sourceRepository: SourceRepository
    private lateinit var sourceApi: SourceApi
    private lateinit var sourceDao: SourceDao

    @Before
    fun setup() {
        sourceApi = mock()
        sourceDao = mock()
        `when`(sourceDao.getSourceService()).thenReturn(Single.just(emptyList()))
        sourceRepository = SourceRepository(sourceApi, sourceDao)
    }

    @Test
    fun test_emptyCache_noDataOnApi_returnsEmptyList() {
        `when`(sourceApi.getSourceService().map { it.sources }).thenReturn(Observable.just(emptyList()))

        sourceRepository.getSourceService().test()
                .assertValue { it.isEmpty() }
    }

    @Test
    fun test_emptyCache_hasDataOnApi_returnsApiData() {
        `when`(sourceApi.getSourceService().map { it.sources }).thenReturn(Observable.just(listOf(aRandomSource())))

        sourceRepository.getSourceService().test()
                .assertValueCount(1)
                .assertValue { it.size == 1 }
    }

    @Test
    fun test_hasCacheData_hasApiData_returnsBothData() {
        val cachedData = listOf(aRandomSource())
        val apiData = listOf(aRandomSource(), aRandomSource())
        `when`(sourceApi.getSourceService().map { it.sources }).thenReturn(Observable.just(apiData))

        sourceRepository.getSourceService().test()
                //Both cached & API data delivered
                .assertValueCount(2)
                //First cache data delivered
                .assertValueAt(0) { it == cachedData }
                //Secondly api data delivered
                .assertValueAt(1) { it == apiData }
    }

    @Test
    fun test_cache_updatedWithApiData() {
        val apiData = listOf(aRandomSource(), aRandomSource())
        `when`(sourceApi.getSourceService().map { it.sources }).thenReturn(Observable.just(apiData))

        sourceRepository.getSourceService().test()

    }

    private fun aRandomSource() = Source("mail@test.com", "John", "test", "www.google.com", "", "", "tr")
}