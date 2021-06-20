package com.alawiyaa.mydiabetes.ui.history.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.databinding.ActivityDiagnosisBinding
import com.alawiyaa.mydiabetes.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private  var _binding : ActivityResultBinding? = null
    private val binding get() = _binding

    companion object{
        const val DATA_EXTRA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val data = intent.getStringArrayListExtra(DATA_EXTRA)

        getData(data)
    }

    private fun getData(data: ArrayList<String>?) {
        binding?.tvGender?.text = data?.get(0) ?: "null"
        binding?.tvPolyuria?.text = data?.get(1) ?: "null"
        binding?.tvPolydipsia?.text = data?.get(2) ?: "null"
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
}