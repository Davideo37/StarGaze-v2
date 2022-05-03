package com.example.project2.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.MainActivity
import com.example.project2.R
import com.example.project2.RecyclerAdapter
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.fragment_forecast.*


class ForecastFragment : Fragment() {
    private val reportFragment = ReportFragment()
    private var weather1 : String? = "weather"
    private var image1 : String? = "image"
    private var date1 : String? = "date"
    private var weather2: String? = "weather"
    private var image2 : String? = "image"
    private var date2 : String? = "date"
    private var weather3 : String? = "weather"
    private var image3 : String? = "image"
    private var date3 : String? = "date"

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

        adapter = RecyclerAdapter()
        recycler_view.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
        //val reportDate : MaterialTextView = requireView().findViewById(R.id.text_view1)
        //val reportCond : MaterialTextView = requireView().findViewById(R.id.text_view2)
        //val reportImage : MaterialTextView = requireView().findViewById(R.id.image_view)
        weather1 = MainActivity.forecastWeath1
        image1 = MainActivity.forecastImage1
        date1 = MainActivity.forecastDate1
        weather2 = MainActivity.forecastWeath2
        image2 = MainActivity.forecastImage2
        date2 = MainActivity.forecastDate2
        weather3 = MainActivity.forecastWeath3
        image3 = MainActivity.forecastImage3
        date3 = MainActivity.forecastDate3
        //reportCond.text = "$weather1"
        //reportDate.text = "$date1"

    }

}