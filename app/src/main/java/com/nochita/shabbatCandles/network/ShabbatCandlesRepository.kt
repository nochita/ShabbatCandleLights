package com.nochita.shabbatCandles.network

import android.util.Log
import androidx.lifecycle.LiveData
import com.nochita.ShabbatCandlesApplication
import com.nochita.shabbatCandles.model.DataResponse
import com.nochita.shabbatCandles.model.ItemCategory
import com.nochita.shabbatCandles.model.ShabbatCandlesData

class ShabbatCandlesRepository {

    private val client = HebCalApiService.getRetrofit().create(HebCalInterface::class.java)
    private val dao = ShabbatCandlesApplication.db.dataDao()

    suspend fun getData() : LiveData<ShabbatCandlesData> {
        fetchData()
        return dao.getData()
    }

    private suspend fun fetchData()  {
        val response  =  client.shabbatTime()
        response?.items.let {
            dao.deleteData()
            val data = obtainData(response.items)
            dao.insert(data)
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