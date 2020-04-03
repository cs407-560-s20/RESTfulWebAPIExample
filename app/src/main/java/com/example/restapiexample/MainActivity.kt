package com.example.restapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun downloadJokeData(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
            .load("https://api.chucknorris.io/jokes/random")
            .asString()
            .setCallback { e, result ->
                // Write code to process the result
                // e parameter stores the exception if any
                Log.d(TAG, "The received data : $result")

                // Helper function to parse/process data
                parseChuckData(result)
            }
    }


    private fun parseChuckData(result: String){
        // Extract the information from JSON data

        // Received data is in JSON format as shown below
        // {
        //    "icon_url" : "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
        //    "id" : "yM8nV5p5RmSgllUkZIgRfQ",
        //    "url" : ""
        //    "value" : "Superman has posters of Chuck Norris"
        // }

        val data = JSONObject(result)
        val joke = data.getString("value")
        Log.d(TAG, "The received data : $joke")

        // Display the joke
        tv_joke.text = joke
    }


    fun downloadFunnyImage(view: View) {
        // Download the data from the specified URL
        Ion.with(this)
            .load("https://yesno.wtf/api")
            .asString()
            .setCallback { e, result ->
                // Write code to process the result
                // e parameter stores the exception if any
                Log.d(TAG, "The received data : $result")

                // Helper function to parse/process data
                parsefunnyImageData(result)
            }
    }


    private fun parsefunnyImageData(result: String){
        // Extract the information from JSON data
        //{"answer":"yes","forced":false,"image":"https://yesno.wtf/assets/yes/13-c3082a998e7758be8e582276f35d1336.gif"}
        val data = JSONObject(result)
        val img = data.getString("image")
        val yesOrNo = data.getString("answer")
        Log.d(TAG, "The received data : $img")

        // Display the image using Ion library, alternatively picasso library can be used
        Ion.with(image)
            .load(img)
        // Display the yes/no text
        tv_yes_no.text = yesOrNo
    }

}
