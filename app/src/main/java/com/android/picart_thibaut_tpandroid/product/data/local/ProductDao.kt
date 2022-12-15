package com.android.picart_thibaut_tpandroid.product.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.picart_thibaut_tpandroid.product.data.model.ProductRoom

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun selectAll(): LiveData<List<ProductRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chuckNorrisRoom: ProductRoom)

    @Query("DELETE FROM product")
    fun deleteAll()
}