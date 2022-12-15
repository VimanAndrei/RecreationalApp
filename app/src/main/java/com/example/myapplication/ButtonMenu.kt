package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityButtonsBinding

private lateinit var binding: ActivityButtonsBinding

class ButtonMenu : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons: ArrayList<Button> = ArrayList()


        var educationBtn = findViewById<Button>(R.id.education)
        var recreationalBtn = findViewById<Button>(R.id.recreational)
        var socialBtn = findViewById<Button>(R.id.social)
        var charityBtn = findViewById<Button>(R.id.charity)
        var cookingBtn = findViewById<Button>(R.id.cooking)
        var musicBtn = findViewById<Button>(R.id.music)
        var randomBtn = findViewById<Button>(R.id.random)

        buttons.add(educationBtn)
        buttons.add(recreationalBtn)
        buttons.add(socialBtn)
        buttons.add(charityBtn)
        buttons.add(cookingBtn)
        buttons.add(musicBtn)
        buttons.add(randomBtn)


    }
}