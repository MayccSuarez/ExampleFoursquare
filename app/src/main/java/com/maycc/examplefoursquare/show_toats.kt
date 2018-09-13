package com.maycc.examplefoursquare

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, msj: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msj, duration).show()
}