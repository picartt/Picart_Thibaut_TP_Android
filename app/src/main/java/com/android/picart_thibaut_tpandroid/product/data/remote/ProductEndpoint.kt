package com.android.picart_thibaut_tpandroid.product.data.remote

import com.android.picart_thibaut_tpandroid.product.data.model.ProductRetrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductEndpoint {

    @GET("pokemon/{id}")
    suspend fun getRandomProduct(@Path("id") id: Int): ProductRetrofit
}