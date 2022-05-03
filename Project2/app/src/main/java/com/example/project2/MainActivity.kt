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
        var day1 = mutableMapOf(
            "date" to "Date1",
            "condition" to "Condition1",
            "image" to "0",
            "temp" to "Temp1"
        )
        var day2 = mutableMapOf(
            "date" to "Date2",
            "condition" to "Condition2",
            "image" to "0",
            "temp" to "Temp2"
        )
        var day3 = mutableMapOf(
            "date" to "Date3",
            "condition" to "Condition3",
            "image" to "0",
            "temp" to "Temp3"
        )
        val forecastData = arrayOf(day1, day2, day3)
        var index = 0
        var locName = "TestName"
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

    fun replaceFragment(fragment: Fragment) {
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

                val weather =
                    json.getJSONObject("current").getJSONObject("condition").getString("text")
                val temp1 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getJSONArray("hour").getJSONObject(0).getDouble("temp_f")
                        .toString() + " °F"
                val temp2 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1)
                        .getJSONArray("hour").getJSONObject(0).getDouble("temp_f")
                        .toString() + " °F"
                val temp3 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2)
                        .getJSONArray("hour").getJSONObject(0).getDouble("temp_f")
                        .toString() + " °F"
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
                val dateString1 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                        .getString("date")
                val dateString2 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1)
                        .getString("date")
                val dateString3 =
                    json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2)
                        .getString("date")
                val image1 = chooseWeatherImage(weather1)
                val image2 = chooseWeatherImage(weather2)
                val image3 = chooseWeatherImage(weather3)

                locName = name

                forecastData[0]["temp"] = temp1
                forecastData[1]["temp"] = temp2
                forecastData[2]["temp"] = temp3
                forecastData[0]["condition"] = weather1
                forecastData[1]["condition"] = weather2
                forecastData[2]["condition"] = weather3
                forecastData[0]["date"] = formatDate(dateString1)
                forecastData[1]["date"] = formatDate(dateString2)
                forecastData[2]["date"] = formatDate(dateString3)
                forecastData[0]["image"] = image1.toString()
                forecastData[1]["image"] = image2.toString()
                forecastData[2]["image"] = image3.toString()

                Log.i("JSON returned", json.toString())
                Log.i("Map Data", forecastData[0]["temp"]!!)
            }
        })
    }


}

/* Function to determine which weather image to display based on the conditions
 *
 */
fun chooseWeatherImage(weather: String): Int {
    if (weather == "Clear") {
        return R.drawable.clear
    } else if (weather == "Partly Cloudy") {
        return R.drawable.partlycloudy
    } else if (weather == "Overcast") {
        return R.drawable.overcast
    } else if (weather.contains("thunder", true)) {
        if (weather.contains("rain", true) || weather.contains("snow", true)) {
            return R.drawable.thunderstorm_rain
        } else {
            return R.drawable.thunderstorm
        }
    } else if (weather.contains("rain", true)) {
        return R.drawable.rainy
    } else if (weather == "Sunny") {
        return R.drawable.sunny
    } else if (weather.contains("snow", true)) {
        return R.drawable.snow
    } else if (weather.contains("fog", true)) {
        return R.drawable.overcast // Replace with fog
    } else if (weather.contains("sleet", true)) {
        return R.drawable.sleet
    } else if (weather.contains("hail", true)) {
        return R.drawable.hail
    }
    return R.drawable.partlycloudy // Default return
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(date: String?): String {
    var parsedDate = LocalDate.parse(date!!.split(" ")[0])
    return parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        .substringBefore(",")
}

