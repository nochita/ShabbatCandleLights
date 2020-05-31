package com.nochita.shabbatCandles.network

import com.nochita.shabbatCandles.model.HebCalResponse
import retrofit2.http.GET

interface HebCalInterface{

    @GET("/shabbat/?cfg=json&geonameid=3435910&m=0&leyning=off")
    suspend fun shabbatTime() : HebCalResponse
}
