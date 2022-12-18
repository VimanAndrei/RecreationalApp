package com.example.myapplication

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ApiCall(var url:String) {
    private val client = OkHttpClient()
    var listActivity: ArrayList<OneActivity> = ArrayList()

    fun callFunction(){
        val request = Request.Builder()
            .url(url)
            .build()
        for (i in 1..10){
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    val json = JSONObject(body)
                    val activity = OneActivity(
                        json.getString("activity"),
                        json.getString("type"),
                        json.getString("participants"),
                        json.getString("price"),
                        json.getString("link"),
                        json.getString("key"),
                        json.getString("accessibility"))
                    listActivity.add(activity)
                }


            })
        }
    }

    @JvmName("getListActivity1")
    fun getListActivity(): ArrayList<OneActivity> {
        return listActivity
    }


}