package com.example.personalityapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btn_start).setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }


        findViewById<Button>(R.id.btn_sobre).setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }
    }
}
