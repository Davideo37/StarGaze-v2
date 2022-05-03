package com.example.project2.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.MainActivity
import com.example.project2.R
import com.example.project2.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*


class ForecastFragment : Fragment() {
    private val reportFragment = ReportFragment()


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(this.context)
        recycler_view.layoutManager = layoutManager

        adapter = RecyclerAdapter { position -> onListItemClick(position) }
        recycler_view.adapter = adapter
        super.onViewCreated(view, savedInstanceState)


    }
    private fun onListItemClick(position: Int) {
        MainActivity.index = position
        (activity as MainActivity).replaceFragment(reportFragment)
    }
}