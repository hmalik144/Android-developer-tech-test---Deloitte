package com.example.h_mal.shopapicasestudy.api

import android.os.AsyncTask
import android.util.Log
import com.example.h_mal.shopapicasestudy.ui.ResponseListener
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


class AsyncApiCall(
    val urlString : String,
    val callBack : ResponseListener?
): AsyncTask<String, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        callBack?.onStarted()
    }

    override fun doInBackground(vararg params: String?): String {


        return try {
            val url = URL(urlString)
            val con = url.openConnection() as HttpURLConnection

            if (urlString ==
                "https://private-anon-21a48b3d0e-ddshop.apiary-mock.com/cart/1"){
                con.requestMethod = "DELETE"

            }

            val code = con.responseCode

            Log.i("reponse", "code = ${code}")

            if (code == 204){
                return "successful delete"
            }

            URL(urlString).readText()
        }catch (e : Exception){
            Log.e("error", e.message)
            ""
        }


    }

    override fun onPostExecute(result: String) {
        if (result.isEmpty()){
            callBack?.onFailure("Failed to retrieve")
        }else{
            callBack?.onSuccess()
        }

    }
}