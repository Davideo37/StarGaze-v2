package com.example.stargaze.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.stargaze.R
import com.example.stargaze.MainActivity
import com.google.android.material.textview.MaterialTextView


class ReportFragment : Fragment() {

    private var name: String? = "name"
    private var temp: String? = "temp"
    private var weather: String? = "weather"
    private var image: String? = "image"
    private var date: String? = "date"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_report, container, false)
        // Inflate the layout for this fragment
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reportName: MaterialTextView = requireView().findViewById(R.id.report_name)
        val reportCond: MaterialTextView = requireView().findViewById(R.id.report_cond)
        val reportTemp: MaterialTextView = requireView().findViewById(R.id.report_temp)
        val reportDate: MaterialTextView = requireView().findViewById(R.id.report_date)
        val reportImage: ImageView = requireView().findViewById(R.id.report_image)
        val reportDesc: MaterialTextView = requireView().findViewById(R.id.report_desc)
        val i = MainActivity.index
        name = MainActivity.locName
        temp = MainActivity.forecastData[i]["temp"]
        weather = MainActivity.forecastData[i]["condition"]
        image = MainActivity.forecastData[i]["image"]
        date = MainActivity.forecastData[i]["date"]
        reportName.text = "$name"
        reportTemp.text = "$temp"
        reportCond.text = "$weather"
        reportDate.text = "$date"
        reportImage.setImageResource(image!!.toInt())

        val tp = temp?.dropLast(2)?.toDouble()

        if (tp != null) {
            if (tp > 20 && (weather == "Clear" || weather == "Sunny")) {
                reportDesc.text = getString(R.string.stargaze_positive)
            } else {
                reportDesc.text = getString(R.string.stargaze_negative)
            }
        }
    }
}