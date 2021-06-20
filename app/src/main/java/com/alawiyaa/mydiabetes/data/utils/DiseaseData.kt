package com.alawiyaa.mydiabetes.data.utils

import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.ui.history.diagnosis.QuestionData

object DiseaseData {
    fun getQuestion(): ArrayList<QuestionData> {
        val questionList = ArrayList<QuestionData>()

        val gender = QuestionData(
            1,
            "Gender",
            R.drawable.ic_gender,
            "Jenis Kelamin anda?",
            "Male",
            "Female"
        )
        questionList.add(gender)


        val polyuria = QuestionData(
            2,
            "Polyuria",
            R.drawable.ic_polyuria,
            "Apakah anda sering buang air kecil?",
            "Yes",
            "No"

        )
        questionList.add(polyuria)

        val polydipsia = QuestionData(
            3,
            "Polydipsia",
            R.drawable.ic_polydipsia,
            "Apakah anda sering minum / rasa haus yang berlebih?",
            "Yes",
            "No"

        )
        questionList.add(polydipsia)

        val polyphagia = QuestionData(
            4,
            "Polyphagia",
            R.drawable.ic_polyphagia,
            "Apakah anda sering lapar / rasa lapar yang  berlebih?",
            "Yes",
            "No"

        )
        questionList.add(polyphagia)

        val swl = QuestionData(
            5,
            "Sudden Weight Loss",
            R.drawable.ic_weight_loss,
            "Apakah berat badan anda turun drastis?",
            "Yes",
            "No"

        )
        questionList.add(swl)

        val partialParesis = QuestionData(
            6,
            "Partial Paresis",
            R.drawable.ic_partial_paresis,
            "Apakah anda merasakan melemahnya otot di area tertentu seperti, kaki,tangan, wajah?",
            "Yes",
            "No"

        )
        questionList.add(partialParesis)


        val visualBlurring = QuestionData(
            7,
            "Visual Blurring",
            R.drawable.ic_visual_blurring,
            "Apakah anda pernah merasakan penglihatan kabur?",
            "Yes",
            "No"

        )
        questionList.add(visualBlurring)

        val delayedHealing = QuestionData(
            8,
            "Delayed Healing",
            R.drawable.ic_delayed_heling,
            "Apakah anda  merasakan luka yang sulit sembuh?",
            "Yes",
            "No"

        )
        questionList.add(delayedHealing)

        val weakness = QuestionData(
            9,
            "Weakness",
            R.drawable.ic_weakness,
            "Apakah anda cepat merasa lelah?",
            "Yes",
            "No"

        )
        questionList.add(weakness)

        val alopecia = QuestionData(
            10,
            "Alopecia",
            R.drawable.ic_alopecia,
            "Apakah anda merasakan kerontokan atau kebotakan pada rambut?",
            "Yes",
            "No"

        )
        questionList.add(alopecia)


        val muscleStiffness = QuestionData(
            11,
            "Muscle stiffness",
            R.drawable.ic_muscle_stiffness,
            "Apakah anda  merasakan otot kaku?",
            "Yes",
            "No"

        )
        questionList.add(muscleStiffness)

        val genitalThrush = QuestionData(
            12,
            "Genital Thrush",
            R.drawable.ic_genital_trush,
            "Apakah anda merasakan sariawan pada kulit, mulut, dan organ intim?",
            "Yes",
            "No"

        )
        questionList.add(genitalThrush)

        val itching = QuestionData(
            13,
            "Itching",
            R.drawable.ic_itaching,
            "Apakah anda sering merasakan gatal pada kulit?",
            "Yes",
            "No"

        )
        questionList.add(itching)

        val irritability = QuestionData(
            14,
            "Irritability",
            R.drawable.ic_irritability,
            "Apakah anda sering merasa gelisah atau mudah marah?",
            "Yes",
            "No"

        )
        questionList.add(irritability)

        val obesity = QuestionData(
            15,
            "Obesity",
            R.drawable.ic_obesity,
            "Apakah anda merasakan Berat badan berlebih?",
            "Yes",
            "No"

        )
        questionList.add(obesity)

        return questionList
    }
}