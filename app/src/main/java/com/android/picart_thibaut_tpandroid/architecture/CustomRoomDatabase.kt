package com.android.picart_thibaut_tpandroid.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.picart_thibaut_tpandroid.product.data.local.ProductDao
import com.android.picart_thibaut_tpandroid.product.data.model.ProductRoom

@Database(
    entities = [
        ProductRoom::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun mProductDao() : ProductDao
}
