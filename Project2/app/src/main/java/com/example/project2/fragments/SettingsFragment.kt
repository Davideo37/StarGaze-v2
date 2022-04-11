package com.example.project2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project2.R
import com.google.android.material.slider.Slider
import com.google.android.material.textview.MaterialTextView


class SettingsFragment : Fragment() {

    private val textField = R.id.sliderText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val slider = requireView().findViewById<Slider>(R.id.slider)
        val textView = requireView().findViewById<MaterialTextView>(R.id.sliderText)
        slider.addOnChangeListener{  slider, value, fromUser ->
            textView.text = "Slider Value: "+slider.value.toString()
        }
    }

}