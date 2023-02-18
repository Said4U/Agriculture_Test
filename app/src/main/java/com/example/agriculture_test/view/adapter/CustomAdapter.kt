package com.example.agriculture_test.view.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.agriculture_test.R
import com.example.agriculture_test.data.DrugsItem
import com.example.agriculture_test.repository.Api
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view_design.view.*


class CustomAdapter(private val itemList: List<DrugsItem>?, val itemClickListener: ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val BASE_URL = "http://shans.d2.i-partner.ru"

    interface ItemClickListener{
        fun onItemClick(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(BASE_URL + itemList?.get(position)?.image).resize(500, 800).into(holder.imageView)

        holder.itemView.itemName.text = itemList?.get(position)?.name.toString()
        var description = itemList?.get(position)?.description.toString()
        if (description.length > 70){
            description = description.subSequence(0, 70).toString() + "..."
        }
        holder.itemView.itemDescription.text = description
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }


    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        init {
            ItemView.setOnClickListener {
                itemList?.get(position)?.id?.let { itemClickListener.onItemClick(it)}
            }
        }
    }
}