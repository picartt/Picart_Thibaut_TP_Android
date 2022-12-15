package com.android.picart_thibaut_tpandroid.product.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.android.picart_thibaut_tpandroid.architecture.CustomApplication
import com.android.picart_thibaut_tpandroid.product.domain.model.ProductDomain
import com.android.picart_thibaut_tpandroid.product.view.model.*

interface ProductRepository {

    // Get all product from database
    fun selectAll(): LiveData<List<ProductDomain>>

    // Delete all content in the table
    suspend fun deleteAll()

    // Fetch a new product randomly and add it inside database
    suspend fun fetchData()
}