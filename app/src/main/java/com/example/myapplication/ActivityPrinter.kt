package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.myapplication.databinding.ListItemBinding

class ActivityPrinter(): AppCompatActivity() {

    private lateinit var binding: ListItemBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = this.intent
        var title = intent.getStringExtra("title")
        var listOfActivity = intent.getSerializableExtra("list") as ArrayList<OneActivity>
        setContentView(R.layout.list_item)
        binding = ListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        findViewById<TextView>(R.id.pageTitle).text = title

        binding.listView.adapter=ActivityAdapter(this, listOfActivity)

        binding.listView.setOnItemLongClickListener { adapterView, view, i, l ->
            Toast.makeText(applicationContext, "Long clicked on ${binding.listView[i]}", Toast.LENGTH_LONG).show()
            PopupMenu(this,binding.listView)
            true
        }
    }
}