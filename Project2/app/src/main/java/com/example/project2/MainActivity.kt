package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project2.fragments.HomeFragment
import com.example.project2.fragments.MessagesFragment
import com.example.project2.fragments.SettingsFragment
import com.example.project2.ui.main.MainFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val settingsFragment = SettingsFragment()
    private val messagesFragment = MessagesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        replaceFragment(homeFragment)
    }
    fun basicAlert(view: View){
        MaterialAlertDialogBuilder(view.context)
            .setTitle("This is a test")
            .setMessage("Here is some text")
            .setNeutralButton("Cancel") {dialog, which ->{}}
            .setPositiveButton("Ok") {dialog, which->{}}
            .show()        
    }
    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}