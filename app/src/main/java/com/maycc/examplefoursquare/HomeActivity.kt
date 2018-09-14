package com.maycc.examplefoursquare

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        makeRequestApi()
    }

    private fun makeRequestApi() {
        val latitude = 40.7484
        val longitude = -73.9857
        val token = Foursquare(this).getToken()
        val date = "20180912"
        val url = "Https://api.foursquare.com/v2/venues/search?ll=$latitude,$longitude&oauth_token=$token&v=$date"

        val requestQueue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url, Response.Listener {
            response ->
            Log.d("RESPONSE_HTTP", response)
            convertJsonToFrqResponse(response)

        }, Response.ErrorListener {
            error ->  Log.d("ERROR_RESPONSE", error.message)
        })

        requestQueue.add(request)
    }

    private fun convertJsonToFrqResponse(json: String) {
        val gSon = Gson()
        val frqResponse = gSon.fromJson(json, FrqResponse::class.java)
        val venues = frqResponse.response.venues

        Log.d("VENUES_COUNT", venues.size.toString())
    }
}
