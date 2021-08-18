package com.alawiyaa.mydiabetes.data.utils

import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.ui.history.diagnosis.QuestionData

object DiseaseData {
    fun getQuestion(): ArrayList<QuestionData> {
        val questionList = ArrayList<QuestionData>()



        val age = QuestionData(
            1,
            "Age",
            R.drawable.ic_growing_up,
            "Rentang usia anda?",
            "Berapa rentang usia anda",
            "20-35",
            null
        )
        questionList.add(age)


        val gender = QuestionData(
            2,
            "Gender",
            R.drawable.ic_gender,
            "Apa jenis Kelamin anda?",
            "Jenis Kelamin anda",
            "Pria",
            "Wanita"
        )
        questionList.add(gender)


        val polyuria = QuestionData(
            3,
            "Polyuria",
            R.drawable.ic_polyuria,
            "Apakah anda sering buang air kecil?",
            "kondisi ketika tubuh menghasilkan urine (air kencing) secara berlebihan. Penderita poliuria dapat buang air kecil hingga belasan kali dalam satu hari, terutama pada malam hari.",
            "Ya",
            "Tidak"

        )
        questionList.add(polyuria)

        val polydipsia = QuestionData(
            4,
            "Polydipsia",
            R.drawable.ic_polydipsia,
            "Apakah anda sering minum / rasa haus yang berlebih?",
            "kondisi haus luar biasa yang mewalahkan. Anda akan terus minum, minum, dan minum, tapi tetap merasa sering haus.Kecenderungan sering haus terus juga dapat disebabkan oleh penurunan kadar hormon antidiuretik dalam tubuh yang menyebabkan Anda jadi buang air kecil berlebihan (poliuria).",
            "Ya",
            "Tidak"

        )
        questionList.add(polydipsia)

        val swl = QuestionData(
            5,
            "Sudden Weight Loss",
            R.drawable.ic_weight_loss,
            "Apakah berat badan anda turun drastis?",
            "Penurunan berat badan bisa saja terjadi melalui usaha, seperti diet atau olahraga. Namun jika berat badan turun drastis tanpa ada usaha tertentu untuk menurunkannya, kemungkinan terdapat suatu penyakit.",
            "Ya",
            "Tidak"

        )
        questionList.add(swl)

        val weakness = QuestionData(
            6,
            "Weakness",
            R.drawable.ic_weakness,
            "Apakah anda merasakan cepat lelah?",
            "Kelemahan dapat disebabkan oleh hal-hal di luar penyakit. Contohnya termasuk kondisi fisik yang buruk, pemulihan dari latihan kekuatan, atau kelelahan ekstrem.",
            "Ya",
            "Tidak"

        )
        questionList.add(weakness)



        val polyphagia = QuestionData(
            7,
            "Polyphagia",
            R.drawable.ic_polyphagia,
            "Apakah anda sering lapar / rasa lapar yang  berlebih?",
            "Tanda dan gejala utama dari polifagia adalah meningkatnya nafsu makan sehingga membuat Anda makan lebih sering dari biasanya. Hiperfagia juga bisa diartikan Anda jadi sangat cepat lapar.",
            "Ya",
            "Tidak"

        )
        questionList.add(polyphagia)

        val genitalThrush = QuestionData(
            8,
            "Genital Thrush",
            R.drawable.ic_genital_trush,
            "Apakah anda merasakan sariawan pada kulit, mulut, dan organ intim?",
            "Sariawan adalah infeksi jamur (candida albicans) yang cenderung menyerang area tubuh yang hangat dan lembab seperti vagina, penis, mulut, dan area kulit tertentu. Sariawan lebih sering terjadi pada penderita diabetes karena kadar gula yang tinggi menyebabkan kondisi yang lebih baik bagi ragi untuk tumbuh.",
            "Ya",
            "Tidak"

        )
        questionList.add(genitalThrush)

        val visualBlurring = QuestionData(
            9,
            "Visual Blurring",
            R.drawable.ic_visual_blurring,
            "Apakah anda pernah merasakan penglihatan kabur?",
            "Kadar gula darah yang tinggi membuat lensa mata membengkak hingga mengganggu kemampuan mata untuk melihat. Dalam jangka pendek, kadar gula darah yang tinggi dapat menyebabkan perubahan bentuk lensa mata. Hal ini dapat membuat penglihatan menjadi kabur.",
            "Ya",
            "Tidak"

        )
        questionList.add(visualBlurring)

        val itching = QuestionData(
            10,
            "Itching",
            R.drawable.ic_itaching,
            "Apakah anda sering merasakan gatal pada kulit?",
            "Orang dengan diabetes cenderung mengalami kulit gatal lebih sering daripada mereka yang tidak memiliki kondisi tersebut. Gatal yang terus-menerus bisa membuat tidak nyaman dan dapat menyebabkan garukan berlebihan, yang dapat menyebabkan infeksi, ketidaknyamanan, dan nyeri. Seringkali, penyebab gatal terkait diabetes adalah polineuropati diabetik atau neuropati perifer. Ini adalah komplikasi diabetes yang berkembang ketika kadar glukosa darah tinggi menyebabkan kerusakan pada serabut saraf, terutama di kaki dan tangan.",
            "Ya",
            "Tidak"

        )
        questionList.add(itching)

        val irritability = QuestionData(
            11,
            "Irritability",
            R.drawable.ic_irritability,
            "Apakah anda sering merasa gelisah atau mudah marah?",
            "Diabetes dapat mempengaruhi suasana hati seseorang, menyebabkan perubahan yang cepat dan parah. Gejala kadar gula darah rendah yang mungkin berkontribusi terhadap perubahan suasana hati meliputi: kebingungan,  kelaparan,  kesulitan koordinasi,  dan pengambilan keputusan agresi dan lekas marah perubahan kepribadian atau perilaku kesulitan konsentrasi.",
            "Ya",
            "Tidak"

        )
        questionList.add(irritability)

        val delayedHealing = QuestionData(
            12,
            "Delayed Healing",
            R.drawable.ic_delayed_heling,
            "Apakah anda  merasakan luka yang sulit sembuh?",
            "Luka lama sembuh terjadi saat proses penyembuhan luka terhambat. Beberapa hal yang turut berkontribusi terhadap lamanya penyembuhan luka, mulai dari riwayat penyakit yang diderita seperti diabetes, hingga penerapan pola hidup yang tidak sehat seperti kurangnya asupan makanan bernutrisi dan kebiasaan merokok.",
            "Ya",
            "Tidak"

        )
        questionList.add(delayedHealing)




        val partialParesis = QuestionData(
            13,
            "Partial Paresis",
            R.drawable.ic_partial_paresis,
            "Apakah anda merasakan melemahnya otot di area tertentu seperti, kaki,tangan, wajah?",
            ". Paralisis adalah kondisi lumpuh karena gangguan pada saraf yang berperan dalam mengatur gerakan otot tubuh. Paralisis membuat anggota tubuh tidak bisa digerakkan. Kondisi ini paling sering dialami oleh penderita stroke atau orang yang mengalami cedera saraf tulang belakang.",
            "Ya",
            "Tidak"

        )
        questionList.add(partialParesis)




        val muscleStiffness = QuestionData(
            14,
            "Muscle stiffness",
            R.drawable.ic_muscle_stiffness,
            "Apakah anda  merasakan otot kaku?",
            "Tendon adalah sekumpulan jaringan yang menghubungkan otot dengan tulang. Tendon terdapat di seluruh tubuh. Mereka meletakkan kekuatan dari otot ke tulang sehingga Anda bisa bergerak . Ciri signifikan saat tendon Anda rusak akibat diabetes, antara lain telapak tangan serta jari yang kaku atau berubah menuju arah tak semestinya. Kondisi lain seperti susah bergerak, mati rasa, dan kesemutan terus-menerus juga dapat dialami tubuh Anda.",
            "Ya",
            "Tidak"

        )
        questionList.add(muscleStiffness)


        val alopecia = QuestionData(
            15,
            "Alopecia",
            R.drawable.ic_alopecia,
            "Apakah anda merasakan kerontokan atau kebotakan pada rambut?",
            "Diabetes dapat menyebabkan berbagai gejala dan masalah kesehatan, termasuk rambut rontok. Seperti dilansir dari Medical News Today, diabetes dapat menyebabkan penipisan rambut dan kerontokan rambut pada beberapa orang.",
            "Ya",
            "Tidak"

        )
        questionList.add(alopecia)





        val obesity = QuestionData(
            16,
            "Obesity",
            R.drawable.ic_obesity,
            "Apakah anda merasakan Berat badan berlebih?",
            "Obesitas biasanya disebabkan oleh patologi yang mendasari akumulasi kelebihan lemak tubuh. Ini merusak kesehatan untuk sebagian besar terutama dengan meningkatkan risiko penyakit kronis lainnya seperti penyakit jantung dan diabetes. Obesitas didefinisikan sebagai Indeks Massa Tubuh (IMT) melebihi 30 kg/m2.",
            "Ya",
            "Tidak"

        )
        questionList.add(obesity)
        val obesityy = QuestionData(
            17,
            "Obesity",
            R.drawable.ic_obesity,
            "Apakah anda merasakan Berat badan berlebih?",
            "Obesitas biasanya disebabkan oleh patologi yang mendasari akumulasi kelebihan lemak tubuh. Ini merusak kesehatan untuk sebagian besar terutama dengan meningkatkan risiko penyakit kronis lainnya seperti penyakit jantung dan diabetes. Obesitas didefinisikan sebagai Indeks Massa Tubuh (IMT) melebihi 30 kg/m2.",
            "Ya",
            "Tidak"

        )
        questionList.add(obesityy)

        return questionList
    }
}