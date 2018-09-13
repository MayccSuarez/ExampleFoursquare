package com.maycc.examplefoursquare

import android.content.Intent
import android.support.v7.app.AppCompatActivity

fun startHomeActivity(activity: AppCompatActivity) {
    activity.startActivity(Intent(activity, HomeActivity::class.java))
    activity.finish()
}