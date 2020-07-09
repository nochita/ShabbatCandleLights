package com.nochita.shabbatCandles.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nochita.shabbatCandles.model.ShabbatCandlesData

@Dao
abstract class ShabbatCandlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: ShabbatCandlesData?)

    @Query("DELETE FROM data")
    abstract suspend fun deleteData()

    @Query("SELECT * FROM data")
    abstract fun getData(): LiveData<ShabbatCandlesData>

}
