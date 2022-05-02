package com.example.project2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project2.databinding.MainActivityBinding
import com.example.project2.fragments.ForecastFragment
import com.example.project2.fragments.HomeFragment
import com.example.project2.fragments.ReportFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.main_activity.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class MainActivity : AppCompatActivity() {
    private val forecastFragment = ForecastFragment()
    private val homeFragment = HomeFragment()
    private val reportFragment = ReportFragment()
    private val client = OkHttpClient()
    private lateinit var binding: MainActivityBinding
    private lateinit var layout: View
    private lateinit var text: String
    private lateinit var json: JSONObject

    companion object {
        var locName = "TestName"
        var locDate = "TestTime"
        var locTemp = "TestTemp"
        var locWeath = "TestWeath"
        var locImage = "TestImage"
        var forecastWeath1 = "Weather1Test"
        var forecastWeath2 = "Weather2Test"
        var forecastWeath3 = "Weather3Test"
        var forecastDate1 = "Date1Test"
        var forecastDate2 = "Date2Test"
        var forecastDate3 = "Date3Test"
        var forecastImage1 = "Image1Test"
        var forecastImage2 = "Image2Test"
        var forecastImage3 = "Image3Test"
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    var location = "Wenham, MA"
    var requestString =
        "http://api.weatherapi.com/v1/forecast.json?key=000164ffa1bd49d48e3172911222001&q=" +
                location +
                "&days=5&hour=20&raqi=no&alerts=no"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        binding = MainActivityBinding.inflate(layoutInflater)
        layout = binding.fragmentContainer
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        fetchAPI(requestString)
        replaceFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
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

    fun errorAlert(view: View) {
        text = findViewById<TextInputEditText>(R.id.materialTextInputEditText).text.toString()
        MaterialAlertDialogBuilder(view.context)
            .setTitle("Error in location search")
            .setMessage("You entered: $text")
            .setNeutralButton("Cancel") { dialog, which -> {} }
            .setPositiveButton("Ok") { dialog, which -> {} }
            .show()
    }

    fun handleLocationSubmit(view: View) {
        location = findViewById<TextInputEditText>(R.id.materialTextInputEditText).text.toString()
        if (location.isEmpty()) {
            errorAlert(view)
        } else {
            requestString =
                "http://api.weatherapi.com/v1/forecast.json?key=000164ffa1bd49d48e3172911222001&q=" +
                        location + "&days=5&hour=20&raqi=no&alerts=no"
            fetchAPI(requestString)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    fun fetchAPI(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.i("JSON fail", "Request failed: $e")
            }

            @RequiresApi(Build.VERSION_CODES.P)
            override fun onResponse(call: okhttp3.Call, response: Response) {
                val body = response.body()!!.string()
                json = JSONObject(body)
                val name = json.getJSONObject("location").getString("name")
                val temp = json.getJSONObject("current").getDouble("temp_f").toString() + " °F"
                val weather =
                    json.getJSONObject("current").getJSONObject("condition").getString("text")
                val weather1 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getJSONArray("hour").getJSONObject(0).getJSONObject("condition")
                        .getString("text")
                val weather2 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1)
                        .getJSONArray("hour").getJSONObject(0).getJSONObject("condition")
                        .getString("text")
                val weather3 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2)
                        .getJSONArray("hour").getJSONObject(0).getJSONObject("condition")
                        .getString("text")
                val dateString = json.getJSONObject("current").getString("last_updated")
                val dateString1 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getString("date")
                val dateString2 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1)
                        .getString("date")
                val dateString3 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2)
                        .getString("date")
                val image1 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getJSONArray("hour").getJSONObject(0).getJSONObject("condition")
                        .getString("text")
                val image2 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1)
                        .getJSONArray("hour").getJSONObject(0).getJSONObject("condition")
                        .getString("text")
                val image3 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2)
                        .getJSONArray("hour").getJSONObject(0).getJSONObject("condition")
                        .getString("text")
                val image =
                    json.getJSONObject("current").getJSONObject("condition").getString("icon")
                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    location_name.text = name
                    location_temp.text = temp
                    Log.i("Success", "Text updated")
                })
                locName = name
                locTemp = temp
                locWeath = weather
                locImage = image
                locDate = formatDate(dateString)

                forecastWeath1 = weather1
                forecastWeath2 = weather2
                forecastWeath3 = weather3
                forecastDate1 = formatDate(dateString1)
                forecastDate2 = formatDate(dateString2)
                forecastDate3 = formatDate(dateString3)
                forecastImage1 = image1
                forecastImage2 = image2
                forecastImage3 = image3

                Log.i("JSON returned", json.toString())
            }
        })
    }



}


@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(date: String?): String {
    var parsedDate = LocalDate.parse(date!!.split(" ")[0])
    return parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        .substringBefore(",")
}

fun View.showSnackbar(
    view: View,
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(view, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    } else {
        snackbar.show()
    }
}