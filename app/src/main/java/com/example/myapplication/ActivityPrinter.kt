package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ListItemBinding

class ActivityPrinter(): AppCompatActivity() {

    private lateinit var binding: ListItemBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = this.intent
        var title = intent.getStringExtra("title")
        var listOfActivity = intent.getSerializableExtra("list")
        setContentView(R.layout.list_item)
        binding = ListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findViewById<TextView>(R.id.pageTitle).text = title


        binding.listView.adapter=ActivityAdapter(this, listOfActivity as ArrayList<OneActivity>)
    }
}