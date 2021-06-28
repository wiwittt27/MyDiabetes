package com.alawiyaa.mydiabetes.ui.history.result

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.utils.DataHelper
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.ActivityResultBinding

import com.alawiyaa.mydiabetes.viewmodel.ViewModelFactory
import java.util.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    private  var _binding : ActivityResultBinding? = null
    private val binding get() = _binding
    lateinit var userRepository: UserRepository
    private lateinit var  resultViewModel: ResultViewModel
    private var data : ArrayList<String>? = null
    var result :String? = null

    private var user : UserDiseaseEntity? = null
    private var userLogin = ""
    companion object{
        const val DATA_EXTRA = "extra_data"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val sesi = SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)
        userRepository.getUser()?.let { userLogin = it }


        resultViewModel = obtainViewModel(this@ResultActivity)
        resultViewModel.resultText.observe(this,{
            binding?.tvResult?.visibility =View.VISIBLE
            result = it.toString()
            binding?.tvResult?.text = result
        })


                data = intent.getStringArrayListExtra(DATA_EXTRA)


        user = UserDiseaseEntity()

        data?.let { getData(it) }
        binding?.btnSubmit?.setOnClickListener(this)


    }

    private fun showUser(user: UserDiseaseEntity) {
        binding?.tvGender?.text = user.gender
        binding?.tvPolyuria?.text = user.polyuria
        binding?.tvPolydipsia?.text = user.polydipsia
        binding?.tvPolyphagia?.text = user.polyphagia
        binding?.tvSwl?.text = user.swl
        binding?.tvPartialParesis?.text = user.partialParesis
        binding?.tvVisualBlurring?.text =user.visualBlurring
        binding?.tvDelayedHealing?.text = user.delayedHealing
        binding?.tvWeakness?.text = user.weakness
        binding?.tvAlopecia?.text = user.alopecia
        binding?.tvMuscleStiffness?.text = user.muscleStiffness
        binding?.tvGenitalThrush?.text = user.genitalThrush
        binding?.tvItching?.text = user.itching
        binding?.tvIrritability?.text =user.irritability
        binding?.tvObesity?.text = user.obesity
        binding?.tvResult?.text = user.classPrediction
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
        binding?.tvPolyphagia?.text = polyphagia
        binding?.tvSwl?.text = swl
        binding?.tvPartialParesis?.text = pp
        binding?.tvVisualBlurring?.text =vb
        binding?.tvDelayedHealing?.text = dh
        binding?.tvWeakness?.text = weakness
        binding?.tvAlopecia?.text = alopecia
        binding?.tvMuscleStiffness?.text = ms
        binding?.tvGenitalThrush?.text = gt
        binding?.tvItching?.text = itching
        binding?.tvIrritability?.text =irritabiity
        binding?.tvObesity?.text = obesity


      }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_submit){

                val date = DataHelper.getCurrentDate()
                val gender = data?.get(0)
                val polyuria = data?.get(1)
                val polydipsia = data?.get(2)
                val swl = data?.get(3)
                val weakness = data?.get(4)
                val polyphagia = data?.get(5)
                val gt = data?.get(6)
                val vb = data?.get(7)
                val itching = data?.get(8)
                val irritabiity = data?.get(9)
                val dh = data?.get(10)
                val pp = data?.get(11)
                val ms = data?.get(12)
                val alopecia = data?.get(13)
                val obesity = data?.get(14)
                val classPrediction = binding?.tvResult?.text.toString()

                user?.let { user ->
                    user.userName = userLogin
                    user.date = date
                    user.gender = gender
                    user.polyuria = polyuria
                    user.polydipsia = polydipsia
                    user.swl = swl
                    user.weakness = weakness
                    user.polyphagia = polyphagia
                    user.genitalThrush = gt
                    user.visualBlurring = vb
                    user.itching = itching
                    user.irritability = irritabiity
                    user.delayedHealing = dh
                    user.partialParesis = pp
                    user.muscleStiffness = ms
                    user.alopecia = alopecia
                    user.obesity = obesity
                    user.classPrediction = classPrediction
                }
              resultViewModel.insert(user as UserDiseaseEntity)
    finish()




        }
    }

    private fun obtainViewModel(activity: ResultActivity): ResultViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(ResultViewModel::class.java)
    }
}