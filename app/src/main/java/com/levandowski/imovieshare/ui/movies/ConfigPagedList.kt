package com.levandowski.imovieshare.ui.movies

import androidx.paging.PagedList

object ConfigPagedList {

    fun getConfigToPagedList(): PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOADING_PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(MINIMUM_ORDER_SIZE)
        .build()

    private const val INITIAL_LOADING_PAGE_SIZE = 10
    private const val PAGE_SIZE = 20
    private const val MINIMUM_ORDER_SIZE = 4
}
