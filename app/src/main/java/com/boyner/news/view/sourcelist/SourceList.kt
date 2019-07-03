package com.boyner.news.view.sourcelist

import com.boyner.news.network.local.entity.Source

data class SourceList(val sourceList: List<Source>, val message: String, val error: Throwable? = null)
