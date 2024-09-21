package com.example.demo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.models.Product
import com.example.demo.R
import com.example.demo.activities.ProductDetailActivity

class ProductAdapter(private val items: List<Product>,private  val context: Context) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleProductItem: TextView = itemView.findViewById(R.id.tv_titleProductItem)
        val imageProductItem: ImageView = itemView.findViewById(R.id.image_productItem)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = items[position]
                    val intent = Intent(context, ProductDetailActivity::class.java).apply {
                        putExtra("PRODUCT_ID", product.id)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitleProductItem.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.icon)
            .into(holder.imageProductItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
