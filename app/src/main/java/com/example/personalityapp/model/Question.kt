package com.example.personalityapp.model

// 一道题的数据结构
data class Question(
    val text: String,
    val options: List<String>,
    val weights: List<Int>
)
