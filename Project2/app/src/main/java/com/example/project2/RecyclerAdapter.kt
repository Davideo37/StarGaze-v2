package com.example.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dates = arrayOf(
        MainActivity.forecastData[0]["date"],
        MainActivity.forecastData[1]["date"],
        MainActivity.forecastData[2]["date"]
    )

    private var descs = arrayOf(
        MainActivity.forecastData[0]["condition"],
        MainActivity.forecastData[1]["condition"],
        MainActivity.forecastData[2]["condition"]
    )

    private var images = intArrayOf(
        MainActivity.forecastData[0]["image"]!!.toInt(),
        MainActivity.forecastData[1]["image"]!!.toInt(),
        MainActivity.forecastData[2]["image"]!!.toInt()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(v, onItemClicked)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemDate.text = dates[position]
        holder.itemDesc.text = descs[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    inner class ViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var itemImage: ImageView = itemView.findViewById(R.id.image_view)
        var itemDate: TextView = itemView.findViewById(R.id.text_view1)
        var itemDesc: TextView = itemView.findViewById(R.id.text_view2)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = absoluteAdapterPosition
            onItemClicked(position)
        }

    }
}