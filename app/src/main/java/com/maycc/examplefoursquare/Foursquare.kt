package com.maycc.examplefoursquare

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.foursquare.android.nativeoauth.FoursquareOAuth

class Foursquare(val activity: AppCompatActivity) {

    private val CLIENT_ID     = "RXC15OOKII40LRLH4IXDXQ4ECKRE5RIBZ2ABJNDSSPG0D3OJ"
    private val CLIENT_SECRET = "NNLZOPFCEYURZC3GCDAMHX3ZGQKCHK4DRL1XJCJKIJBITQUK"

    private val REQUEST_CODE_FRS_CONNECT = 200
    private val REQUEST_CODE_FRS_TOKEN   = 201

    private val PREFERENCE_TOKEN = "preference_token"
    private val ACCESS_TOKEN     = "access_token"

    fun startAuthentication() {
        val intent = FoursquareOAuth.getConnectIntent(activity.applicationContext, CLIENT_ID)

        // app no esta instalada
        if (FoursquareOAuth.isPlayStoreIntent(intent)) {
            showToast(activity.applicationContext, "No tienes instalada la app de Foursquare")
            activity.startActivity(intent)

        } else {
            activity.startActivityForResult(intent, REQUEST_CODE_FRS_CONNECT)
        }
    }

    fun validActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_FRS_CONNECT -> checkAuthenticationComplete(resultCode, data)

            REQUEST_CODE_FRS_TOKEN -> getAccessToken(resultCode, data)
        }
    }

    private fun checkAuthenticationComplete(resultCode: Int, data: Intent?) {
        val codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data)
        val exception = codeResponse.exception

        if (exception == null) {
            getExchangeToken(codeResponse.code)
        } else {
            showToast(activity.applicationContext, "No se ha podido realizar la autenticación, " +
                    "intentelo más tarde")
        }
    }

    private fun getExchangeToken(authCode: String) {
        val intent = FoursquareOAuth.getTokenExchangeIntent(activity.applicationContext, CLIENT_ID, CLIENT_SECRET, authCode)

        activity.startActivityForResult(intent, REQUEST_CODE_FRS_TOKEN)
    }

    private fun getAccessToken(resultCode: Int, data: Intent?) {
        val tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode,data)
        val exception = tokenResponse.exception

        if (exception == null) {
            val accessToken = tokenResponse.accessToken
            saveToken(accessToken)
            startHomeActivity(activity)

        } else {
            showToast(activity.applicationContext, "No se pudo obtener el token...")
        }
    }

    private fun saveToken(token: String) {
        val sharedPreferences = activity.getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(ACCESS_TOKEN, token).commit()
    }

    fun getToken(): String {
        val sharedPreferences = activity.getSharedPreferences(PREFERENCE_TOKEN, Context.MODE_PRIVATE)

        return sharedPreferences.getString(ACCESS_TOKEN, "")
    }

    fun isSaveToken(): Boolean {
        val token = getToken()

        if (token.isNotEmpty()) return true

        return false
    }

}