package com.android.picart_thibaut_tpandroid.product.data.mapper

import com.android.picart_thibaut_tpandroid.product.data.model.ProductRetrofit
import com.android.picart_thibaut_tpandroid.product.data.model.ProductRoom
import com.android.picart_thibaut_tpandroid.product.domain.model.ProductDomain

fun ProductRetrofit.toRoom(): ProductRoom {
    return ProductRoom(
        title = title,
        iconUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png",
        _id = id.toInt()
    )
}

fun List<ProductRoom>.fromRoomToDomain(): List<ProductDomain> {
    return map {
        ProductDomain(
            title = it.title,
            imageUrl = it.iconUrl,
            id = it._id
        )
    }
}