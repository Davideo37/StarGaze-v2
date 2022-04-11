package com.example.project2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project2.fragments.HomeFragment
import com.example.project2.fragments.MessagesFragment
import com.example.project2.fragments.SettingsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val messagesFragment = MessagesFragment()
    private val homeFragment = HomeFragment()
    private val settingsFragment = SettingsFragment()
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
                R.id.message -> {
                    replaceFragment(messagesFragment)
                }
                R.id.settings -> {
                    replaceFragment(settingsFragment)
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