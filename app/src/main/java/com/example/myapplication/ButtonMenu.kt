package com.example.myapplication

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
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

        val urlList = arrayOf(
            "http://www.boredapi.com/api/activity?type=education",
            "http://www.boredapi.com/api/activity?type=recreational",
            "http://www.boredapi.com/api/activity?type=social",
            "http://www.boredapi.com/api/activity?type=charity",
            "http://www.boredapi.com/api/activity?type=cooking",
            "http://www.boredapi.com/api/activity?type=music",
            "http://www.boredapi.com/api/activity/"
        )

        val titleList = arrayOf(
            "Education",
            "Recreational",
            "Social",
            "Charity",
            "Cooking",
            "Music",
            "Random"
        )

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

        for(i in buttons.indices){
            buttons[i].setOnClickListener {
                var ps = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()
                handler.postDelayed(Runnable { ps.dismiss() }, 1000) // 3000 milliseconds delay

                var listActiviti = ApiCall(urlList[i]).callFunction()

            }

        }


    }
}