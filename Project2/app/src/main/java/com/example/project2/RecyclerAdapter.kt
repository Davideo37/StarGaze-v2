package com.example.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dates = arrayOf("4/24", "4/25", "4/26")

    private var descs = arrayOf("Clear", "Partly Cloudy", "Rainy")

    private var images = intArrayOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemDate.text = dates[position]
        holder.itemDesc.text = descs[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemDate: TextView
        var itemDesc: TextView

        init {
            itemImage = itemView.findViewById(R.id.image_view)
            itemDate = itemView.findViewById(R.id.text_view1)
            itemDesc = itemView.findViewById(R.id.text_view2)
        }

    }
}