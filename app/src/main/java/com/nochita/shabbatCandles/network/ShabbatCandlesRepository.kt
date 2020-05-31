package com.nochita.shabbatCandles.network

class ShabbatCandlesRepository() {

    var client = HebCalApiService.getRetrofit().create(HebCalInterface::class.java)

    suspend fun getData() = client.shabbatTime()
}