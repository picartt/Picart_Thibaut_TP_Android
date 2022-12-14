package com.android.picart_thibaut_tpandroid.view.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class ProductForRecyclerView()

data class ProductHeader(
    val header: String
) : ProductForRecyclerView()

data class Product(
    val name: String,
    val remaining: Int,
    val isFavorite:  Boolean,
    val image: String
): ProductForRecyclerView()

@Entity(tableName = "product_object_table")
data class LocalProduct(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "remaining")
    val remaining: Int,

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,

    @ColumnInfo(name = "image")
    val image: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


data class ProductFooter(
    val nbItems: Int
): ProductForRecyclerView()