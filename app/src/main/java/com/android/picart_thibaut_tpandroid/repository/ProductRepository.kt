package com.android.picart_thibaut_tpandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.android.picart_thibaut_tpandroid.architecture.CustomApplication
import com.android.picart_thibaut_tpandroid.view.model.*

class ProductRepository {

    private val mProductDao=
        CustomApplication.instance.mApplicationDatabase.mProductDao()

    fun selectAllProduct(): LiveData<List<Product>> {
        return mProductDao.selectAll().map { list ->
            list.toProduct()
        }
    }

    fun insertProduct(product: Product) {
        mProductDao.insert(product.toRoomObject())
    }

    fun deleteAllProduct() {
        mProductDao.deleteAll()
    }
}

private fun Product.toRoomObject(): LocalProduct {
    return LocalProduct(
        name = name,
        remaining = remaining,
        isFavorite = isFavorite,
        image = image
    )
}


private fun List<LocalProduct>.toProduct(): List<Product> {
    return map { eachItem ->
        Product(
            name = eachItem.name,
            remaining = eachItem.remaining,
            isFavorite = eachItem.isFavorite,
            image = eachItem.image
        )
    }
}