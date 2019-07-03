package com.boyner.news.network.local

import androidx.room.TypeConverter
import com.boyner.news.network.local.entity.Article
import com.boyner.news.network.local.entity.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converters {
    @TypeConverter
    fun toStringFromSources(value: String): List<Source> {
        val listType = object : TypeToken<List<Source>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toSourcesFromString(list: List<Source>): String {
        val gsonObject = Gson()
        return gsonObject.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun toStringFromSource(value: String): Source {
        val type = object : TypeToken<Source>() {

        }.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toSourceFromString(source: Source): String {
        val gsonObject = Gson()
        return gsonObject.toJson(source)
    }

    @TypeConverter
    fun toStringFromArticle(value: String): List<Article> {
        val listType = object : TypeToken<List<Article>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toArticleFromString(list: List<Article>): String {
        val gsonObject = Gson()
        return gsonObject.toJson(list)
    }
}