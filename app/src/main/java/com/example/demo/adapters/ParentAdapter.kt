package com.example.demo.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.events.OnChildItemClickListener
import com.example.demo.models.MenuItem

class ParentAdapter(private val menuItems: List<MenuItem>,private  val context: Context,private val listener: OnChildItemClickListener) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_parent, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.bind(menuItem)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.parent_title)
        private val arrowIcon: ImageView = itemView.findViewById(R.id.arrow_icon)
        private val childRecyclerView: RecyclerView = itemView.findViewById(R.id.child_recycler_view)

        fun bind(menuItem: MenuItem) {
            title.text = menuItem.title
            arrowIcon.rotation = if (menuItem.isExpanded) 180f else 0f
            childRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            val childAdapter = ChildAdapter(menuItem.children,context,listener)
            childRecyclerView.adapter = childAdapter
            childRecyclerView.visibility = if (menuItem.isExpanded) View.VISIBLE else View.GONE
            itemView.setOnClickListener {
                menuItem.isExpanded = !menuItem.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }
    }
}
