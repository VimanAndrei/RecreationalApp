package com.example.myapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var loginBtn = findViewById<Button>(R.id.button)

        loginBtn.setOnClickListener {
            var ps = ProgressDialog.show(this, "Loading", "Wait while loading...")
            val handler = Handler()
            handler.postDelayed(Runnable { ps.dismiss() }, 1000) // 3000 milliseconds delay

            if (password.text.length < 6) {
                password.error = "Parola are mai putin de 6 caractere."
                return@setOnClickListener
            } else {
                if (username.text.toString().equals("admin") &&
                    password.text.toString().equals("password")
                ) {
                    Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ButtonMenu::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}