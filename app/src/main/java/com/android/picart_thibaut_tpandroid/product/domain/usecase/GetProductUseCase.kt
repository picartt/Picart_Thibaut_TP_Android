package com.android.picart_thibaut_tpandroid.product.domain.usecase

import androidx.lifecycle.LiveData
import com.android.picart_thibaut_tpandroid.product.data.repository.ProductRepositoryImpl
import com.android.picart_thibaut_tpandroid.product.domain.model.ProductDomain
import com.android.picart_thibaut_tpandroid.product.domain.repository.ProductRepository

class GetProductUseCase {
    private val productRepository: ProductRepository by lazy { ProductRepositoryImpl() }

    fun selectAll(): LiveData<List<ProductDomain>> {
        return productRepository.selectAll()
    }
}