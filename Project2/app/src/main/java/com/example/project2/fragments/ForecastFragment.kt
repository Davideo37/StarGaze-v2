package com.example.project2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.R
import com.example.project2.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*


class ForecastFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        layoutManager = LinearLayoutManager(this.context)
//        recycler_view.layoutManager = layoutManager
//
//        adapter = RecyclerAdapter()
//        recycler_view.adapter = adapter
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }
}