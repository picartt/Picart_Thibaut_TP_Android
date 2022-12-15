package com.android.picart_thibaut_tpandroid.product.domain.usecase

import com.android.picart_thibaut_tpandroid.product.data.repository.ProductRepositoryImpl
import com.android.picart_thibaut_tpandroid.product.domain.repository.ProductRepository

class FetchNewDataProductUseCase {
    private val productRepository: ProductRepository by lazy { ProductRepositoryImpl() }

    suspend fun fetchData() {
        productRepository.fetchData()
    }
}