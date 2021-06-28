package com.alawiyaa.mydiabetes.ui.history.diagnosis

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.DiseaseData
import com.alawiyaa.mydiabetes.databinding.ActivityDiagnosisBinding
import com.alawiyaa.mydiabetes.ui.history.result.ResultActivity
import com.alawiyaa.mydiabetes.ui.history.result.ResultViewModel

class DiagnosisActivity : AppCompatActivity(), View.OnClickListener {

    private  var _binding : ActivityDiagnosisBinding? = null
    private val binding get() = _binding
    private  val diagnosisViewModel: DiagnosisViewModel by viewModels()

    private var resultDiagnosis : String? = null
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<QuestionData>? = null
    private var mSelectedOptionPosition: Int = 0
    private var optionSelect = ""
    private var answerList : ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDiagnosisBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        mQuestionsList = DiseaseData.getQuestion()
        setQuestion()

        answerList = ArrayList()

        binding?.tvOptionOne?.setOnClickListener(this)
        binding?.tvOptionTwo?.setOnClickListener(this)
        binding?.btnNext?.setOnClickListener(this)
    }

    private fun setQuestion() {
        disable(false)
        val question = mQuestionsList?.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList?.size) {
            binding?.btnNext?.text = "FINISH"

        } else {
            binding?.btnNext?.text = "SUBMIT"
            binding?.btnNext?.setBackgroundColor(Color.GREEN)
        }



        binding?.progressBar?.progress = mCurrentPosition
        binding?.tvProgress?.text = getString(R.string.progress_text,mCurrentPosition, binding?.progressBar?.max)

        binding?.tvTitle?.text = question?.title
        binding?.tvQuestion?.text = question?.question
        question?.image?.let { binding?.imgQuestion?.setImageResource(it) }
        binding?.tvOptionOne?.text = question?.optionYes
        binding?.tvOptionTwo?.text = question?.optionNo
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                binding?.tvOptionOne?.let { selectedOptionView(it,1) }
                optionSelect = binding?.tvOptionOne?.text.toString()
            }
            R.id.tv_option_two ->{
                binding?.tvOptionTwo?.let { selectedOptionView(it, 2) }
                optionSelect = binding?.tvOptionTwo?.text.toString()
            }
            R.id.btn_next ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()

                        }
                        else ->{
                            val intent =
                                Intent(this@DiagnosisActivity, ResultActivity::class.java)
                            intent.putStringArrayListExtra(ResultActivity.DATA_EXTRA, answerList)
                            startActivity(intent)
                                                        finish()




                        }
                    }
                }else{
                    if (mCurrentPosition == mQuestionsList?.size) {
                        binding?.btnNext?.text = "Finish"

                    } else {
                        binding?.btnNext?.setBackgroundColor(Color.YELLOW)
                        binding?.btnNext?.text = "NEXT"

                    }
                    disable(true)
                    mSelectedOptionPosition = 0
                    answerList?.add(optionSelect)

                    Log.d("DIAGNOSIS", answerList!!.toString())


                }
            }
        }
    }

    private fun disable(select : Boolean){
        if(select){
            binding?.tvOptionOne?.isEnabled = false
            binding?.tvOptionTwo?.isEnabled = false
        }else{
            binding?.tvOptionOne?.isEnabled = true
            binding?.tvOptionTwo?.isEnabled = true
        }
    }

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@DiagnosisActivity,
            R.drawable.selected_option_border_bg
        )
    }

    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        binding?.tvOptionOne?.let { options.add(0, it) }
        binding?.tvOptionTwo?.let { options.add(1, it) }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@DiagnosisActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun getData(data: ArrayList<String>) {
        val gender = data[0]
        val polyuria  = data[1]
        val polydipsia  = data[2]
        val swl = data[3]
        val weakness  = data[4]
        val polyphagia = data[5]
        val gt  = data[6]
        val vb  = data[7]
        val itching  = data[8]
        val irritabiity  = data[9]
        val dh  = data[10]
        val pp  = data[11]
        val ms  = data[12]
        val alopecia  = data[13]
        val obesity = data[14]

        diagnosisViewModel.userClassification(gender,polyuria,polydipsia,swl,weakness,polyphagia,gt,vb,itching,irritabiity,dh,pp,ms,alopecia,obesity)
    }

}