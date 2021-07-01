package com.alawiyaa.mydiabetes.ui.history.diagnosis

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.DiseaseData
import com.alawiyaa.mydiabetes.databinding.FragmentDiagnosisBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DiagnosisFragment : Fragment(), View.OnClickListener {

    private  var _binding : FragmentDiagnosisBinding? = null
    private val binding get() = _binding
    private  val diagnosisViewModel: DiagnosisViewModel by viewModels()
    private var navBar: BottomNavigationView? = null
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<QuestionData>? = null
    private var mSelectedOptionPosition: Int = 0
    private var optionSelect = ""

    private var answerList : ArrayList<String>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiagnosisBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            navBar = requireActivity().findViewById(R.id.nav_view)
            mQuestionsList = DiseaseData.getQuestion()
            setQuestion()

            answerList = ArrayList()


            binding?.btnNext?.setOnClickListener(this)
            binding?.tglDetail?.setOnClickListener(this)
        }
    }

    private fun setQuestion() {
        val question = mQuestionsList?.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.


        if (mCurrentPosition == mQuestionsList?.count()) {
            binding?.btnNext?.text = "FINISH"
            binding?.btnNext?.setBackgroundColor(Color.GREEN)

        } else {
            binding?.btnNext?.text = "NEXT"
            binding?.rgGender?.clearCheck()
            binding?.btnNext?.setBackgroundColor(Color.BLUE)

        }



        binding?.progressBar?.progress = mCurrentPosition
        binding?.tvProgress?.text = getString(R.string.progress_text,mCurrentPosition -1, binding?.progressBar?.max)

        binding?.tvTitle?.text = question?.title
        binding?.tvQuestion?.text = question?.question
        binding?.tvDetail?.text = question?.detail
        question?.image?.let { binding?.imgQuestion?.setImageResource(it) }
        binding?.rbOption1?.text = question?.optionYes
        binding?.rbOption2?.text = question?.optionNo
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_next -> {
                if (binding?.rbOption1?.isChecked == false && binding?.rbOption2?.isChecked == false) {
                    Toast.makeText(requireContext(), "Anda Belum memilih!", Toast.LENGTH_SHORT).show()
                } else {
                    val id = binding?.rgGender?.checkedRadioButtonId
                    when (id) {
                        R.id.rb_option1 -> {
                            optionSelect = binding?.rbOption1?.text.toString()

                        }
                        R.id.rb_option2 -> {
                            optionSelect = binding?.rbOption2?.text.toString()


                        }

                    }
                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition++
                        when {
                            mCurrentPosition <= mQuestionsList!!.size -> {
                                setQuestion()
                                answerList?.add(optionSelect)
                                binding?.tvDetail?.visibility = View.GONE
                                binding?.tglDetail?.setBackgroundResource(R.drawable.ic_hide)
                                Log.d("DIAGNOSIS", answerList!!.toString())
                            }
                            else -> {
                            val toResult = answerList?.toTypedArray()?.let {
                                DiagnosisFragmentDirections.actionDiagnosisFragmentToResultFragment(
                                    it
                                )
                            }
                                if (toResult != null) {
                                    view?.findNavController()?.navigate(toResult)
                                }

                            }


                        }

                    }

                }

            }
            R.id.tgl_detail ->{
                val check = binding?.tglDetail?.isChecked

                if (check == true){
                    binding?.tvDetail?.visibility = View.VISIBLE
                    binding?.tglDetail?.setBackgroundResource(R.drawable.ic_show)

                }else{
                    binding?.tvDetail?.visibility = View.GONE
                    binding?.tglDetail?.setBackgroundResource(R.drawable.ic_hide)
                }

            }

        }
    }




    override fun onStart() {
        super.onStart()
        navBar?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        navBar?.visibility = View.VISIBLE
    }


}