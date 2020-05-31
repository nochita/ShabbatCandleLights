package com.nochita.shabbatCandles.model

sealed class ItemCategory (val category: String) {
    class Candles : ItemCategory("candles")
    class Parashat : ItemCategory("parashat")

    companion object {
        val map = hashMapOf(
            "candles" to Candles(),
            "parashat" to Parashat()
        )

        fun getItemCategory(category : String) : ItemCategory {
            return map.getOrDefault(category, Parashat())!!
        }
    }
}
