package com.alawiyaa.mydiabetes.ui.history.result

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alawiyaa.mydiabetes.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    private  var _binding : ActivityResultBinding? = null
    private val binding get() = _binding

    private  val resultViewModel: ResultViewModel by viewModels()
    lateinit var data : ArrayList<String>

    companion object{
        const val DATA_EXTRA = "extra_data"
        const val DATA_RESULT = "extra_result"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        resultViewModel.resultText.observe(this,{
            binding?.tvResult?.visibility =View.VISIBLE
            binding?.tvResult?.text = it.toString()
        })

         data = intent.getStringArrayListExtra(DATA_EXTRA) as ArrayList<String>


        getData(data)
        binding?.btnSubmit?.setOnClickListener(this)

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

        resultViewModel.userClassification(gender,polyuria,polydipsia,swl,weakness,polyphagia,gt,vb,itching,irritabiity,dh,pp,ms,alopecia,obesity)



        binding?.tvGender?.text =gender
        binding?.tvPolyuria?.text = polyuria
        binding?.tvPolydipsia?.text = polydipsia
        binding?.tvPolyphagia?.text = data?.get(3) ?: "null"
        binding?.tvSwl?.text = data?.get(4) ?: "null"
        binding?.tvPartialParesis?.text = data?.get(5) ?: "null"
        binding?.tvVisualBlurring?.text = data?.get(6) ?: "null"
        binding?.tvDelayedHealing?.text = data?.get(7) ?: "null"
        binding?.tvWeakness?.text = data?.get(8) ?: "null"
        binding?.tvAlopecia?.text = data?.get(9) ?: "null"
        binding?.tvMuscleStiffness?.text = data?.get(10) ?: "null"
        binding?.tvGenitalThrush?.text = data?.get(11) ?: "null"
        binding?.tvItching?.text = data?.get(12) ?: "null"
        binding?.tvIrritability?.text = data?.get(13) ?: "null"
        binding?.tvObesity?.text = data?.get(14) ?: "null"


      }

    override fun onClick(v: View) {

    }
}