package com.android.picart_thibaut_tpandroid.product.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.android.picart_thibaut_tpandroid.architecture.CustomApplication
import com.android.picart_thibaut_tpandroid.architecture.RetrofitBuilder
import com.android.picart_thibaut_tpandroid.product.data.mapper.fromRoomToDomain
import com.android.picart_thibaut_tpandroid.product.data.mapper.toRoom
import com.android.picart_thibaut_tpandroid.product.data.model.ProductRoom
import com.android.picart_thibaut_tpandroid.product.domain.model.ProductDomain
import com.android.picart_thibaut_tpandroid.product.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ProductRepositoryImpl : ProductRepository {
    private val productDao = CustomApplication.instance.mApplicationDatabase.mProductDao()

    override fun selectAll(): LiveData<List<ProductDomain>> {
        return productDao.selectAll().map {
            it.fromRoomToDomain()
        }
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        productDao.deleteAll()
    }

    override suspend fun fetchData() {
        val random = Random.nextInt(0, 901)
        insertOnce(RetrofitBuilder.getProduct().getRandomProduct(random).toRoom())
    }

    private suspend fun insertOnce(product: ProductRoom) =
        withContext(Dispatchers.IO) {
            productDao.insert(product)
        }
}