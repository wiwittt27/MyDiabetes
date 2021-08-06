package com.alawiyaa.mydiabetes.ui.history.diagnosis

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.DiseaseData
import com.alawiyaa.mydiabetes.databinding.FragmentDiagnosisBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DiagnosisFragment : Fragment(), View.OnClickListener {

    private  var _binding : FragmentDiagnosisBinding? = null
    private val binding get() = _binding
    private var navBar: BottomNavigationView? = null
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<QuestionData>? = null
    private var mSelectedOptionPosition: Int = 0
    private var optionSelect = ""
    private var dialog : Dialog? = null
    private var question :QuestionData? = null

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
            dialog = activity?.let { Dialog(it) }
            navBar = requireActivity().findViewById(R.id.nav_view)
            mQuestionsList = DiseaseData.getQuestion()
            setQuestion()

            answerList = ArrayList()

            ArrayAdapter.createFromResource(requireContext(),R.array.age_array, android.R.layout.simple_spinner_item).also { adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding?.spinAge?.adapter = adapter

            }


            binding?.btnNext?.setOnClickListener(this)
            binding?.btnPrevious?.setOnClickListener(this)
            binding?.btnInfo?.setOnClickListener(this)

        }
    }

    private fun setQuestion() {

         question = mQuestionsList?.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.


            if (mCurrentPosition == mQuestionsList?.count()) {
                binding?.btnNext?.text = "Selesai"
                binding?.btnNext?.setBackgroundColor(Color.GREEN)


            } else {
                binding?.btnNext?.text = "Selanjutnya"
                binding?.rgGender?.clearCheck()
                binding?.btnNext?.setBackgroundColor(Color.BLUE)


            }





        binding?.progressBar?.progress = mCurrentPosition
        binding?.tvProgress?.text = getString(R.string.progress_text,mCurrentPosition -1, binding?.progressBar?.max)

        binding?.tvTitle?.text = question?.title
        binding?.tvQuestion?.text = question?.question
        question?.image?.let { binding?.imgQuestion?.setImageResource(it) }
        binding?.rbOption1?.text = question?.optionYes
        binding?.rbOption2?.text = question?.optionNo


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_next -> {
                binding?.rgGender?.visibility = View.VISIBLE
                binding?.spinAge?.visibility = View.GONE
                    val id = binding?.rgGender?.checkedRadioButtonId
                optionSelect = binding?.spinAge?.selectedItem.toString()
                    when (id) {
                        R.id.rb_option1 -> {
                            optionSelect = binding?.rbOption1?.text.toString()

                        }
                        R.id.rb_option2 -> {
                            optionSelect = binding?.rbOption2?.text.toString()

                        }R.id.spin_age ->{

                        }

                    }
                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition++
                        when {
                            mCurrentPosition <= mQuestionsList!!.size -> {
                                setQuestion()
                                answerList?.add(optionSelect)

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
            R.id.btn_info -> {
                openDialog()
            }
            R.id.btn_previous -> {


                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition--

                        if (mCurrentPosition == 1) {

                            binding?.rgGender?.visibility = View.INVISIBLE
                            binding?.spinAge?.visibility = View.VISIBLE
                        }else if (mCurrentPosition == 0){
                            binding?.rgGender?.visibility = View.INVISIBLE
                            binding?.spinAge?.visibility = View.VISIBLE
                            Log.d("DIAGNOSIS", "Chakzzz")
                            activity?.onBackPressed()
                    } else{
                          setQuestion()
                          answerList?.removeAt(mCurrentPosition - 1)
                          Log.d("DIAGNOSIS", answerList!!.toString())

                      }


                        }



            }
        }
    }



    private fun openDialog(){

        dialog?.setContentView(R.layout.dialog_information)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(600,500)

       val img: ImageView? = dialog?.findViewById(R.id.img_close)
        val title = dialog?.findViewById<TextView>(R.id.tv_title_info)
        val info = dialog?.findViewById<TextView>(R.id.tv_text_info)

        title?.text = question?.title
        info?.text = question?.detail
        img?.setOnClickListener {
            dialog?.dismiss()
        }


        dialog?.show()
    }



    override fun onStart() {
        super.onStart()
        navBar?.visibility = View.GONE
    }


}