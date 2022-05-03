package com.example.project2.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.project2.MainActivity
import com.example.project2.R
import com.google.android.material.slider.Slider
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_report.*
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class ReportFragment : Fragment() {

    private var name : String? = "name"
    private var temp : String? = "temp"
    private var weather : String? = "weather"
    private var image : String? = "image"
    private var date : String? = "date"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_report, container, false)
        // Inflate the layout for this fragment
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reportName : MaterialTextView  = requireView().findViewById(R.id.report_name)
        val reportCond : MaterialTextView  = requireView().findViewById(R.id.report_cond)
        val reportTemp : MaterialTextView  = requireView().findViewById(R.id.report_temp)
        val reportDate : MaterialTextView  = requireView().findViewById(R.id.report_date)
        name = MainActivity.locName
        temp = MainActivity.locTemp
        weather = MainActivity.locWeath.toString()
        image = MainActivity.locImage
        date = MainActivity.locDate
        reportName.text = "$name"
        reportTemp.text = "$temp"
        reportCond.text = "$weather"
        reportDate.text = "$date"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}