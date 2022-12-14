package com.android.picart_thibaut_tpandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.R
import com.android.picart_thibaut_tpandroid.databinding.ActivityRecyclerViewBinding
import com.android.picart_thibaut_tpandroid.view.model.Product
import com.android.picart_thibaut_tpandroid.view.model.ProductForRecyclerView
import com.android.picart_thibaut_tpandroid.view.model.ProductHeader

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: ProductViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the instance of adapter
        adapter = ProductViewAdapter()

        // We define the style
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter

        // Generate data and give it to adapter
        adapter.submitList(generateFakeData())
    }

    private fun generateFakeData(): MutableList<ProductForRecyclerView> {
        val result = mutableListOf<ProductForRecyclerView>()
        // Create data raw
        mutableListOf(
            Product("Tomate", 5, false),
            Product("Courgette", 6, false),
            Product("Oignon", 7, false),
            Product("Carotte", 8, false),
            Product("Haricot", 9, true),
            Product("Pomme de terre", 10, true),
            Product("Chou", 11, false),
            Product("Endive", 12, true)
        ).groupBy {
            // Split in 2 list, modulo and not
            it.isFavorite
        }.forEach { (isFavorite, items) ->
            // For each mean for each list split
            // Here we have a map (key = isModulo) and each key have a list of it's items
            result.add(ProductHeader("Is Favorite : $isFavorite"))
            result.addAll(items)
            // Here we can add footer, just after our items
        }
        return result
    }


}