package com.android.picart_thibaut_tpandroid.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.databinding.ItemProductRecyclerBinding
import com.android.picart_thibaut_tpandroid.view.model.Product

private val diffItemUtils = object : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}


class ProductViewAdapter: ListAdapter<Product, ProductViewHolder>(diffItemUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ProductViewHolder(
    private val binding: ItemProductRecyclerBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(product: Product){
        binding.itemRecyclerViewName.text = product.name
        binding.itemRecyclerViewRemaining.text = "${product.remaining}"
    }
}