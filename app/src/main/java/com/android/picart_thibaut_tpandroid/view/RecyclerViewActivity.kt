package com.android.picart_thibaut_tpandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.R
import com.android.picart_thibaut_tpandroid.databinding.ActivityRecyclerViewBinding
import com.android.picart_thibaut_tpandroid.view.model.Product
import com.android.picart_thibaut_tpandroid.view.model.ProductForRecyclerView
import com.android.picart_thibaut_tpandroid.view.model.ProductHeader
import com.android.picart_thibaut_tpandroid.viewmodel.ProductViewModel
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
        binding.addItemButton.setOnClickListener { addRandomAndroidVersion() }
        binding.deleteAllItemButton.setOnClickListener { deleteAndroidVersion() }
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

    private fun onItemClick(product: Product, view : View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, product.name, Toast.LENGTH_LONG).show()
    }


    override fun onStart() {
        super.onStart()
        viewModel.productList.observe(this, productListObserver)
    }


    override fun onStop() {
        super.onStop()
        viewModel.productList.removeObserver(productListObserver)
    }

    private fun addRandomAndroidVersion() {
        val isFavIcon = "https://icons.iconarchive.com/icons/paomedia/small-n-flat/512/heart-icon.png"
        val noFavIcon = "https://www.iconpacks.net/icons/2/free-heart-icon-3510-thumb.png"
        val random = Random.nextInt(0, 1000)
        var isFavorite = false
        var url = ""
        if (random%4==0){
            isFavorite = true
            url = isFavIcon
        } else {
            isFavorite = false
            url = noFavIcon
        }
        viewModel.insertProduct("Product $random", random, isFavorite, url)
    }


    private fun deleteAndroidVersion() {
        viewModel.deleteAllProduct()
    }


}