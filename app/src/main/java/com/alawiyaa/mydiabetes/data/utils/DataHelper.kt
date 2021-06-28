package com.alawiyaa.mydiabetes.data.utils

import java.text.SimpleDateFormat
import java.util.*

object DataHelper {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}