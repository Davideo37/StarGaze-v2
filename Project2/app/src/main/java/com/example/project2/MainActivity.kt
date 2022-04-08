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
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val messagesFragment = MessagesFragment()
    private val homeFragment = HomeFragment()
    private val settingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
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
    private fun replaceFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
    }
}