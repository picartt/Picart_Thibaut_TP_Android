package com.android.picart_thibaut_tpandroid.product.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.picart_thibaut_tpandroid.R
import com.android.picart_thibaut_tpandroid.databinding.ItemProductRecyclerBinding
import com.android.picart_thibaut_tpandroid.databinding.ItemProductRecyclerFooterBinding
import com.android.picart_thibaut_tpandroid.databinding.ItemProductRecyclerHeaderBinding
import com.android.picart_thibaut_tpandroid.product.view.model.*
import com.bumptech.glide.Glide

private val diffItemUtils = object : DiffUtil.ItemCallback<ProductForRecyclerView>() {

    override fun areItemsTheSame(oldItem: ProductForRecyclerView, newItem: ProductForRecyclerView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductForRecyclerView, newItem: ProductForRecyclerView): Boolean {
        return oldItem == newItem
    }
}


class ProductViewAdapter(
    private val onItemClick: (product: ProductUi, view: View) -> Unit
): ListAdapter<ProductForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {

            MyItemType.ROW.type -> {
                ProductViewHolder(
                    ItemProductRecyclerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick
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

            MyItemType.FOOTER.type -> {
                ProductFooterViewHolder(
                    ItemProductRecyclerFooterBinding.inflate(
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
            MyItemType.ROW.type -> (holder as ProductViewHolder).bind(getItem(position) as ProductUi)

            MyItemType.HEADER.type -> (holder as ProductHeaderViewHolder).bind(getItem(position) as ProductHeader)

            MyItemType.FOOTER.type -> (holder as ProductFooterViewHolder).bind(getItem(position) as ProductFooter)

            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ProductUi -> MyItemType.ROW.type
            is ProductHeader -> MyItemType.HEADER.type
            is ProductFooter -> MyItemType.FOOTER.type
            else -> throw RuntimeException("Wrong type received ${getItem(position)}")
        }
    }


}

class ProductViewHolder(
    private val binding: ItemProductRecyclerBinding,
    onItemClick:(product: ProductUi, view: View) -> Unit
): RecyclerView.ViewHolder(binding.root){
    private lateinit var ui: ProductUi

    init {
        binding.root.setOnClickListener{
            onItemClick(ui, itemView)
        }
    }

    fun bind(product: ProductUi){
        ui = product
        binding.itemRecyclerViewName.text = product.title
        Glide.with(itemView.context)
            .load(product.url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.itemRecyclerViewImage)
    }
}

class ProductHeaderViewHolder(
    private val binding: ItemProductRecyclerHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(productHeader: ProductHeader) {
        binding.itemRecyclerViewHeader.text = productHeader.header
    }
}

class ProductFooterViewHolder(
    private val binding: ItemProductRecyclerFooterBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(productFooter: ProductFooter) {
        binding.itemRecyclerViewFooter.text = productFooter.footer
    }
}

enum class MyItemType(val type: Int) {
    ROW(0),
    HEADER(1),
    FOOTER(2)
}

