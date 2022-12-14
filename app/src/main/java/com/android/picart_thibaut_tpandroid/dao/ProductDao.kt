package com.android.picart_thibaut_tpandroid.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.picart_thibaut_tpandroid.view.model.LocalProduct

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_object_table ORDER BY name ASC")
    fun selectAll(): LiveData<List<LocalProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(androidVersion: LocalProduct)

    @Query("DELETE FROM product_object_table")
    fun deleteAll()
}
