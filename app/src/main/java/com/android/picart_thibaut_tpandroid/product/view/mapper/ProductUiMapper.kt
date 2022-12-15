package com.android.picart_thibaut_tpandroid.product.view.mapper

import com.android.picart_thibaut_tpandroid.product.domain.model.ProductDomain
import com.android.picart_thibaut_tpandroid.product.view.model.ProductFooter
import com.android.picart_thibaut_tpandroid.product.view.model.ProductForRecyclerView
import com.android.picart_thibaut_tpandroid.product.view.model.ProductHeader
import com.android.picart_thibaut_tpandroid.product.view.model.ProductUi

fun List<ProductDomain>.fromDomainToUi(): List<ProductForRecyclerView> {
    val result = mutableListOf<ProductForRecyclerView>()

    groupBy {
        // Split in 2 list, modulo and not
        it.id % 2 == 0
    }.forEach { (isModulo, items) ->
        // For each mean for each list split
        // Here we have a map (key = isModulo) and each key have a list of it's items
        result.add(ProductHeader("Is Favorite : $isModulo"))

        result.addAll(items.map {
            ProductUi(
                title = it.title,
                url = it.imageUrl,
                id = it.id
            )
        })
        result.add(ProductFooter("Nombre de Pokémon:${items.size}"))
    }
    return result
}