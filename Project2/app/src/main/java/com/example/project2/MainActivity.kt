package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import com.example.project2.databinding.MainActivityBinding
import com.example.project2.fragments.HomeFragment
import com.example.project2.fragments.MessagesFragment
import com.example.project2.fragments.SettingsFragment
import com.example.project2.ui.main.MainFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val messagesFragment = MessagesFragment()
    private val homeFragment = HomeFragment()
    private val settingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        replaceFragment(homeFragment)
    }
    fun basicAlert(view: View){
        MaterialAlertDialogBuilder(view.context)
            .setTitle("This is a test")
            .setMessage("Here is some text")
            .setNeutralButton("Cancel") {dialog, which ->{}}
            .setPositiveButton("Ok") {dialog, which->{}}
            .show()        

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
    private fun replaceFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
    }
}