package com.example.myapplication

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ApiCall(var title:String, var url:String) {
    private val client = OkHttpClient()

    fun callFunction() {
        val request = Request.Builder()
            .url(url)
            .build()
        for (i in 1..10){
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) =
                    println(response.body()?.string())
            })
        }
    }
}