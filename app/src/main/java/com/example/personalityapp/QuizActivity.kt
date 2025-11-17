package com.example.personalityapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.personalityapp.model.QuizRepository

class QuizActivity : AppCompatActivity(), QuestionFragment.OnAnswerSelectedListener {

    private val questions = QuizRepository.getQuestions()
    private var currentIndex = 0
    private val answers = mutableMapOf<Int, Int>()  // questionIndex -> selectedOptionIndex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // ⬅ 左上角 Voltar 按钮：返回主界面
        findViewById<Button>(R.id.btn_back_quiz).setOnClickListener {
            finish()   // 关闭 QuizActivity，回到 MainActivity
        }

        showQuestion()

        findViewById<Button>(R.id.btn_prev).setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                showQuestion()
            }
        }

        findViewById<Button>(R.id.btn_next).setOnClickListener {
            if (currentIndex < questions.lastIndex) {
                currentIndex++
                showQuestion()
            } else {
                finishQuiz()
            }
        }
    }

    private fun showQuestion() {
        val question = questions[currentIndex]

        val fragment = QuestionFragment.newInstance(
            question.text,
            question.options.toTypedArray()
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onAnswerSelected(optionIndex: Int) {
        answers[currentIndex] = optionIndex
    }

    private fun finishQuiz() {
        var total = 0

        answers.forEach { (questionIndex, optionIndex) ->
            val q = questions[questionIndex]
            total += q.weights[optionIndex]
        }

        ResultActivity.start(this, total)
        finish()
    }
}
