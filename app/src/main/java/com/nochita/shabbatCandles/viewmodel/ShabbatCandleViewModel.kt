package com.nochita.shabbatCandles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nochita.shabbatCandles.model.DataResponse
import com.nochita.shabbatCandles.model.ItemCategory
import com.nochita.shabbatCandles.model.ShabbatCandlesData
import com.nochita.shabbatCandles.network.ShabbatCandlesRepository
import kotlinx.coroutines.Dispatchers

class ShabbatCandleViewModel : ViewModel(){

    val repository: ShabbatCandlesRepository = ShabbatCandlesRepository()

    fun getData() : LiveData<ShabbatCandlesData>  = liveData(Dispatchers.IO) {
        val hebCalResponse = repository.getData()
        hebCalResponse?.items.let {
            emit(obtainData(hebCalResponse!!.items))
        }
    }

    private fun obtainData(items : List<DataResponse>) : ShabbatCandlesData {
        var dateOfLighting : String = ""
        var parashat : String = ""
        items.forEach{
            when(ItemCategory.getItemCategory(it.category)){
                is ItemCategory.Candles -> dateOfLighting = it.date
                is ItemCategory.Parashat -> parashat = it.title
            }
        }
        Log.d("shabbat", "dateOfLighting ${dateOfLighting} - parashat ${parashat}")
        return ShabbatCandlesData(dateOfLighting, parashat)
    }
}