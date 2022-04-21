package com.example.project2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project2.fragments.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val forecastFragment = ForecastFragment()
    private val homeFragment = HomeFragment()
    private val reportFragment = ReportFragment()
    private lateinit var text : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        replaceFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.forecast -> {
                    replaceFragment(forecastFragment)
                }
                R.id.report -> {
                    replaceFragment(reportFragment)
                }
                R.id.home -> {
                    replaceFragment(homeFragment)
                }
            }
            true
        }
    }
    fun basicAlert(view: View) {
        text = findViewById<TextInputEditText>(R.id.materialTextInputEditText).text.toString()
        MaterialAlertDialogBuilder(view.context)
            .setTitle("This is a dialog")
            .setMessage("You typed: $text")
            .setNeutralButton("Cancel") { dialog, which -> {} }
            .setPositiveButton("Ok") { dialog, which -> {} }
            .show()
    }


    private fun replaceFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
    }
}