package com.android.picart_thibaut_tpandroid.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.picart_thibaut_tpandroid.dao.ProductDao
import com.android.picart_thibaut_tpandroid.view.model.LocalProduct

@Database(
    entities = [
        LocalProduct::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun mProductDao() : ProductDao
}
