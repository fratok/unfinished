package com.example.myapplication2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class ItemsAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>(){

    class ViewHolder(
        itemView: View,
        private val onItemClick: (Item) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.item_list_image)
            val title: TextView = itemView.findViewById(R.id.item_list_title)
            val desc: TextView = itemView.findViewById(R.id.item_list_desc)
            val price: TextView = itemView.findViewById(R.id.item_list_price)
            val btn: Button = itemView.findViewById(R.id.item_list_button)

        fun bind(item: Item) {
                title.text = item.name
                desc.text = item.description
                price.text = "${item.price}$"

                itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int = items.count()

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].name
        holder.desc.text = items[position].description
        holder.price.text = items[position].price.toString() + "$"

        val imageUrl = items[position].imageUrl

        Glide.with(context)
            .load(imageUrl)
            .into(holder.image)

        holder.btn.setOnClickListener { view ->
            view.findNavController().navigate(
                R.id.action_itemsFragment2_to_itemFragment2,
                Bundle().apply {
                    putString("itemTitle", items[position].additionalInfo1)
                    putString("itemText", items[position].additionalInfo2)

                }
            )
        }
    }
}

