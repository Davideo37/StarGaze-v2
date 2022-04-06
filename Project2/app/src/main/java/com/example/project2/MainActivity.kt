package com.example.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.project2.ui.main.MainFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }
    fun basicAlert(view: View){
        MaterialAlertDialogBuilder(view.context)
            .setTitle("This is a test")
            .setMessage("Here is some text")
            .setNeutralButton("Cancel") {dialog, which ->{}}
            .setPositiveButton("Ok") {dialog, which->{}}
            .show()
    }
}