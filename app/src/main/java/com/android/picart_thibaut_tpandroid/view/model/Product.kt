package com.android.picart_thibaut_tpandroid.view.model

sealed class ProductForRecyclerView()

data class ProductHeader(
    val header: String
) : ProductForRecyclerView()

data class Product(
    val name: String,
    val remaining: Int,
    val isFavorite:  Boolean
): ProductForRecyclerView()