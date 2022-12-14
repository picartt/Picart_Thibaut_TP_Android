package com.android.picart_thibaut_tpandroid.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.databinding.ItemProductRecyclerBinding
import com.android.picart_thibaut_tpandroid.databinding.ItemProductRecyclerHeaderBinding
import com.android.picart_thibaut_tpandroid.view.model.Product
import com.android.picart_thibaut_tpandroid.view.model.ProductForRecyclerView
import com.android.picart_thibaut_tpandroid.view.model.ProductHeader

private val diffItemUtils = object : DiffUtil.ItemCallback<ProductForRecyclerView>() {

    override fun areItemsTheSame(oldItem: ProductForRecyclerView, newItem: ProductForRecyclerView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductForRecyclerView, newItem: ProductForRecyclerView): Boolean {
        return oldItem == newItem
    }
}


class ProductViewAdapter: ListAdapter<ProductForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {

            MyItemType.ROW.type -> {
                ProductViewHolder(
                    ItemProductRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            MyItemType.HEADER.type -> {
                ProductHeaderViewHolder(
                    ItemProductRecyclerHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as ProductViewHolder).bind(getItem(position) as Product)


            MyItemType.HEADER.type -> (holder as ProductHeaderViewHolder).bind(getItem(position) as ProductHeader)


            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Product -> MyItemType.ROW.type
            is ProductHeader -> MyItemType.HEADER.type
        }
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

class ProductHeaderViewHolder(
    private val binding: ItemProductRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(productHeader: ProductHeader) {
        binding.itemRecyclerViewHeader.text = productHeader.header
    }
}

enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1)
}

