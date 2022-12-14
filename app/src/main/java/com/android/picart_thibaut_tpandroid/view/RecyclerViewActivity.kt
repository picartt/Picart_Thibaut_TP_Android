package com.android.picart_thibaut_tpandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.R
import com.android.picart_thibaut_tpandroid.databinding.ActivityRecyclerViewBinding
import com.android.picart_thibaut_tpandroid.view.model.Product

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

    private fun generateFakeData(): ArrayList<Product> {
        return arrayListOf(
            Product("Tomate", 5, false),
            Product("Courgette", 6, false),
            Product("Oignon", 7, false),
            Product("Carotte", 8, false),
            Product("Haricot", 9, true),
            Product("Pomme de terre", 10, true),
            Product("Chou", 11, false),
            Product("Endive", 12, true)
        )
    }

}