package com.catnip.retrofitexample.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bitohur.foodapp.core.ViewHolderBinder
import com.catnip.retrofitexample.data.model.Product
import com.catnip.retrofitexample.databinding.ItemProductBinding

class ProductListAdapter() : RecyclerView.Adapter<ProductItemListViewHolder>() {
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })

    fun setData(data: List<Product>) {
        differ.submitList(data)
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemListViewHolder {
        return ProductItemListViewHolder(
            binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ProductItemListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}

class ProductItemListViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root),
    ViewHolderBinder<Product> {
    override fun bind(item: Product) {
        binding.ivProductPic.load(item.images)
        binding.tvPriceProduct.text = item.price.toString()
        binding.tvTitleProduct.text = item.title
        binding.tvDescriptionProduct.text = item.desc
    }
}