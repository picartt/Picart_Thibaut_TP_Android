package com.android.picart_thibaut_tpandroid.architecture

import com.android.picart_thibaut_tpandroid.product.data.remote.ProductEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun getProduct(): ProductEndpoint =
        retrofit.create(ProductEndpoint::class.java)
}