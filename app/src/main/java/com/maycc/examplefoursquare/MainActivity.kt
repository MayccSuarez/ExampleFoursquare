package com.maycc.examplefoursquare

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val foursquare = Foursquare(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(foursquare.thereIsToken()) {
            startHomeActivity(this)
        }

        setListenerLoginBtn()
    }

    private fun setListenerLoginBtn() {
        btnLogin.setOnClickListener {
            foursquare.startAuthentication()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        foursquare.validActivityResult(requestCode, resultCode, data)
    }
}
