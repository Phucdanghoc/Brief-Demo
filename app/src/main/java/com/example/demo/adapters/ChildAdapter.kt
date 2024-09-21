package com.example.demo.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.events.OnChildItemClickListener
import com.example.demo.models.ChildItem
import com.example.demo.utils.VideoPlayer

class ChildAdapter(private val children: List<ChildItem>,private val context: Context,private val listener: OnChildItemClickListener) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_child, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val childItem = children[position]
        holder.bind(childItem)
    }

    override fun getItemCount(): Int {
        return children.size
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val childTitle: TextView = itemView.findViewById(R.id.child_title)

        fun bind(childItem: ChildItem) {
            childTitle.text = childItem.title
            itemView.setOnClickListener {
                listener.onChildItemClick(childItem.linkVideo)
            }
        }
    }
}
