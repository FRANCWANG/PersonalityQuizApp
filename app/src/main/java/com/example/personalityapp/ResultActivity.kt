package com.example.personalityapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)

        val txtResult = findViewById<TextView>(R.id.txt_result)
        val btnEmail = findViewById<Button>(R.id.btn_email)
        val edtEmail = findViewById<EditText>(R.id.edt_email)

        val summary = when {
            score >= 12 -> "Perfil: Organizado e consistente"
            score >= 8 -> "Perfil: Flexível e equilibrado"
            else -> "Perfil: Espontâneo — pode melhorar rotina"
        }

        // 只显示结果，不显示 pontuação
        txtResult.text = summary

        btnEmail.setOnClickListener {
            val email = edtEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Digite um e-mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!email.contains("@")) {
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                putExtra(Intent.EXTRA_SUBJECT, "Meu resultado no Quiz")
                putExtra(Intent.EXTRA_TEXT, summary)
            }

            // 🌟👉 你要的：只要点按钮就立刻显示 “Enviado com sucesso!”
            Toast.makeText(this, "Enviado com sucesso!", Toast.LENGTH_SHORT).show()

            // 然后正常跳到邮箱 app
            startActivity(intent)
        }
    }

    companion object {
        fun start(activity: AppCompatActivity, score: Int) {
            val intent = Intent(activity, ResultActivity::class.java)
            intent.putExtra("score", score)
            activity.startActivity(intent)
        }
    }
}
