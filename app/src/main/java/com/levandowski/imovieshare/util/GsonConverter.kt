package com.levandowski.imovieshare.util

interface GsonConverter {

    fun unwrapElement(json: String): Any?

    fun wrap(): String
}
