package com.android.picart_thibaut_tpandroid.viewmodel

import androidx.lifecycle.*
import com.android.picart_thibaut_tpandroid.repository.ProductRepository
import com.android.picart_thibaut_tpandroid.view.model.Product
import com.android.picart_thibaut_tpandroid.view.model.ProductFooter
import com.android.picart_thibaut_tpandroid.view.model.ProductForRecyclerView
import com.android.picart_thibaut_tpandroid.view.model.ProductHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val productRepository: ProductRepository by lazy { ProductRepository() }
    private val _androidVersionList = MutableLiveData<List<ProductForRecyclerView>>()
    val productList: LiveData<List<ProductForRecyclerView>> = productRepository.selectAllProduct().map { list ->
        list.toProductForRecyclerView()
    }

    fun insertProduct(productName: String, productRemaining: Int, isFavorite: Boolean, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertProduct(
                Product(productName, productRemaining, isFavorite, url)
            )
        }
    }


    fun deleteAllProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteAllProduct()
        }
    }

    private fun List<Product>.toProductForRecyclerView(): List<ProductForRecyclerView> {
        val result = mutableListOf<ProductForRecyclerView>()


        groupBy {
            // Split in 2 list, modulo and not
            it.isFavorite
        }.forEach { (isFavorite, items) ->
            // For each mean for each list split
            // Here we have a map (key = isModulo) and each key have a list of it's items
            result.add(ProductHeader("Is Favorite : $isFavorite"))
            result.addAll(items)
            result.add(ProductFooter(items.size))
        }
        return result
    }



}