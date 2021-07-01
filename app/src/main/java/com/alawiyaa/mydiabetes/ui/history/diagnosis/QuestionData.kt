package com.alawiyaa.mydiabetes.ui.history.diagnosis

data class QuestionData (
    val id: Int,
    val title :String,
    val image: Int,
    val question: String,
    val detail: String? = null,
    val optionYes: String,
    val optionNo: String
)