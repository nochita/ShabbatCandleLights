package com.nochita.shabbatCandles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nochita.shabbatCandles.network.ShabbatCandlesRepository

class ShabbatCandleViewModel : ViewModel() {

    private val repository: ShabbatCandlesRepository = ShabbatCandlesRepository()

    val shabbatCandlesData = liveData {
        val data = repository.getData()
        emitSource(data)
    }
}