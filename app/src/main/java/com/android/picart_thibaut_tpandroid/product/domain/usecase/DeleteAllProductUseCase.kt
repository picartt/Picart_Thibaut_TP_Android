package com.android.picart_thibaut_tpandroid.product.domain.usecase

import com.android.picart_thibaut_tpandroid.product.data.repository.ProductRepositoryImpl
import com.android.picart_thibaut_tpandroid.product.domain.repository.ProductRepository

class DeleteAllProductUseCase {
    private val productRepository: ProductRepository by lazy { ProductRepositoryImpl() }

    suspend fun deleteAll() {
        productRepository.deleteAll()
    }
}