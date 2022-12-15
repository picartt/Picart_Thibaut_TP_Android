package com.android.picart_thibaut_tpandroid.product.view.viewmodel

import androidx.lifecycle.*
import com.android.picart_thibaut_tpandroid.product.domain.usecase.DeleteAllProductUseCase
import com.android.picart_thibaut_tpandroid.product.domain.usecase.FetchNewDataProductUseCase
import com.android.picart_thibaut_tpandroid.product.domain.usecase.GetProductUseCase
import com.android.picart_thibaut_tpandroid.product.view.mapper.fromDomainToUi
import com.android.picart_thibaut_tpandroid.product.view.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val getProductUseCase: GetProductUseCase by lazy { GetProductUseCase() }
    private val fetchNewDataProductUseCase: FetchNewDataProductUseCase by lazy { FetchNewDataProductUseCase() }
    private val deleteAllProductUseCase: DeleteAllProductUseCase by lazy { DeleteAllProductUseCase() }

    var productLiveData: LiveData<List<ProductForRecyclerView>> =
        getProductUseCase.selectAll().map {
            it.fromDomainToUi()
        }

    fun fetchNewProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchNewDataProductUseCase.fetchData()
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllProductUseCase.deleteAll()
        }
    }

}