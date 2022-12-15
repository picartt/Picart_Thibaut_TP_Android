package com.android.picart_thibaut_tpandroid.product.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.databinding.ActivityRecyclerViewBinding
import com.android.picart_thibaut_tpandroid.product.view.ProductViewAdapter
import com.android.picart_thibaut_tpandroid.product.view.model.ProductForRecyclerView
import com.android.picart_thibaut_tpandroid.product.view.model.ProductUi
import com.android.picart_thibaut_tpandroid.product.view.viewmodel.ProductViewModel
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: ProductViewAdapter
    private lateinit var viewModel: ProductViewModel

    private val productListObserver = Observer<List<ProductForRecyclerView>>{
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        binding.addItemButton.setOnClickListener {
            viewModel.fetchNewProduct()
        }
        binding.deleteAllItemButton.setOnClickListener {
            viewModel.deleteAll()
        }
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // Create the instance of adapter
        adapter = ProductViewAdapter{
            item, view -> onItemClick(item, view)
        }

        // We define the style
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // We set the adapter to recycler view
        binding.recyclerView.adapter = adapter
    }

    private fun onItemClick(product: ProductUi, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, product.title, Toast.LENGTH_LONG).show()
    }


    override fun onStart() {
        super.onStart()
        viewModel.productLiveData.observe(this, productListObserver)
    }


    override fun onStop() {
        super.onStop()
        viewModel.productLiveData.removeObserver(productListObserver)
    }


}