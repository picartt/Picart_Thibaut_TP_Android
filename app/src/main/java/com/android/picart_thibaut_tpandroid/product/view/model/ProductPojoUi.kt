package com.android.picart_thibaut_tpandroid.product.view.model

sealed class ProductForRecyclerView()

/** Object use for Ui */
data class ProductUi(
    val title: String,
    val url: String,
    val id: Int
): ProductForRecyclerView()

/** Object use for Ui */
data class ProductHeader(
    val header: String
): ProductForRecyclerView()

/** Object use for Ui */
data class ProductFooter(
    val footer: String
): ProductForRecyclerView()