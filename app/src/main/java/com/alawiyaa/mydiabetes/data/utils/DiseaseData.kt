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

        val swl = QuestionData(
            4,
            "Sudden Weight Loss",
            R.drawable.ic_weight_loss,
            "Apakah berat badan anda turun drastis?",
            "Yes",
            "No"

        )
        questionList.add(swl)

        val weakness = QuestionData(
            5,
            "Weakness",
            R.drawable.ic_weakness,
            "Apakah anda cepat merasa lelah?",
            "Yes",
            "No"

        )
        questionList.add(weakness)



        val polyphagia = QuestionData(
            6,
            "Polyphagia",
            R.drawable.ic_polyphagia,
            "Apakah anda sering lapar / rasa lapar yang  berlebih?",
            "Yes",
            "No"

        )
        questionList.add(polyphagia)

        val genitalThrush = QuestionData(
            7,
            "Genital Thrush",
            R.drawable.ic_genital_trush,
            "Apakah anda merasakan sariawan pada kulit, mulut, dan organ intim?",
            "Yes",
            "No"

        )
        questionList.add(genitalThrush)

        val visualBlurring = QuestionData(
            8,
            "Visual Blurring",
            R.drawable.ic_visual_blurring,
            "Apakah anda pernah merasakan penglihatan kabur?",
            "Yes",
            "No"

        )
        questionList.add(visualBlurring)

        val itching = QuestionData(
            9,
            "Itching",
            R.drawable.ic_itaching,
            "Apakah anda sering merasakan gatal pada kulit?",
            "Yes",
            "No"

        )
        questionList.add(itching)

        val irritability = QuestionData(
            10,
            "Irritability",
            R.drawable.ic_irritability,
            "Apakah anda sering merasa gelisah atau mudah marah?",
            "Yes",
            "No"

        )
        questionList.add(irritability)

        val delayedHealing = QuestionData(
            11,
            "Delayed Healing",
            R.drawable.ic_delayed_heling,
            "Apakah anda  merasakan luka yang sulit sembuh?",
            "Yes",
            "No"

        )
        questionList.add(delayedHealing)




        val partialParesis = QuestionData(
            12,
            "Partial Paresis",
            R.drawable.ic_partial_paresis,
            "Apakah anda merasakan melemahnya otot di area tertentu seperti, kaki,tangan, wajah?",
            "Yes",
            "No"

        )
        questionList.add(partialParesis)




        val muscleStiffness = QuestionData(
            13,
            "Muscle stiffness",
            R.drawable.ic_muscle_stiffness,
            "Apakah anda  merasakan otot kaku?",
            "Yes",
            "No"

        )
        questionList.add(muscleStiffness)


        val alopecia = QuestionData(
            14,
            "Alopecia",
            R.drawable.ic_alopecia,
            "Apakah anda merasakan kerontokan atau kebotakan pada rambut?",
            "Yes",
            "No"

        )
        questionList.add(alopecia)





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