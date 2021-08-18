package com.alawiyaa.mydiabetes.ui.history.result

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.remote.StatusResponse
import com.alawiyaa.mydiabetes.data.utils.DataHelper
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentResultBinding
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ResultFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding
    lateinit var userRepository: UserRepository
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var data: Array<String>
    var result: String? = null
    private var navBar: BottomNavigationView? = null


    private var user: UserDiseaseEntity? = null
    private var userLogin = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navBar = requireActivity().findViewById(R.id.nav_view)

        if (activity != null) {
            val factory = DiabetesViewModelFactory.getInstance(requireActivity())
            resultViewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]
            val sesi = SessionManager(requireContext())
            userRepository = UserRepository.getInstance(sesi)
            userRepository.getUser()?.let { userLogin = it }

            binding?.tvInformation?.visibility = View.GONE







            data = ResultFragmentArgs.fromBundle(arguments as Bundle).toResult


            user = UserDiseaseEntity()

            getData(data)
            binding?.btnProcess?.setOnClickListener(this)
            binding?.btnEdit?.setOnClickListener(this)
            binding?.btnSave?.setOnClickListener(this)

        }
    }


    private fun getData(data: Array<String>) {
        rbIsEdit(false)

        val age = data[0]
        binding?.tvResultAge?.text = age

        val gender = data[1]
        if (gender == "Pria") {
            binding?.rbPeria?.isChecked = true
        } else {
            binding?.rbWanita?.isChecked = true

        }
        val polyuria = data[2]
        if (polyuria == "Ya") {
            binding?.polyuriaYes?.isChecked = true
        } else {
            binding?.polyuriaNo?.isChecked = true

        }
        val polydipsia = data[3]
        if (polydipsia == "Ya") {
            binding?.polydipsiaYes?.isChecked = true
        } else {
            binding?.polydipsiaNo?.isChecked = true
        }
        val swl = data[4]
        if (swl == "Ya") {
            binding?.swlYes?.isChecked = true
        } else {
            binding?.swlNo?.isChecked = true
        }
        val weakness = data[5]
        if (weakness == "Ya") {
            binding?.weaknessYes?.isChecked = true
        } else {
            binding?.weaknessNo?.isChecked = true
        }
        val polyphagia = data[6]
        if (polyphagia == "Ya") {
            binding?.polyphagiaYes?.isChecked = true
        } else {
            binding?.polyphagiaNo?.isChecked = true
        }
        val gt = data[7]
        if (gt == "Ya") {
            binding?.genitalThrushYes?.isChecked = true
        } else {
            binding?.genitalThrushNo?.isChecked = true
        }
        val vb = data[8]
        if (vb == "Ya") {
            binding?.visualBlurringYes?.isChecked = true
        } else {
            binding?.visualBlurringNo?.isChecked = true
        }
        val itching = data[9]
        if (itching == "Ya") {
            binding?.itchingYes?.isChecked = true
        } else {
            binding?.itchingNo?.isChecked = true
        }
        val irritabiity = data[10]
        if (irritabiity == "Ya") {
            binding?.irritabilityYes?.isChecked = true
        } else {
            binding?.irritabilityNo?.isChecked = true
        }
        val dh = data[11]
        if (dh == "Ya") {
            binding?.delayedHealingYes?.isChecked = true
        } else {
            binding?.delayedHealingNo?.isChecked = true
        }
        val pp = data[12]
        if (pp == "Ya") {
            binding?.partialParesisYes?.isChecked = true
        } else {
            binding?.partialParesisNo?.isChecked = true
        }
        val ms = data[13]
        if (ms == "Ya") {
            binding?.muscleStiffnessYes?.isChecked = true
        } else {
            binding?.muscleStiffnessNo?.isChecked = true
        }
        val alopecia = data[14]
        if (alopecia == "Ya") {
            binding?.alopeciaYes?.isChecked = true
        } else {
            binding?.alopeciaNo?.isChecked = true
        }
        val obesity = data[15]
        if (obesity == "Ya") {
            binding?.obesityYes?.isChecked = true
        } else {
            binding?.obesityNo?.isChecked = true
        }




    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_save -> {
                val date = DataHelper.getCurrentDate()
                val age = data[0]
                val gender = data[1]
                val polyuria = data[2]
                val polydipsia = data[3]
                val swl = data[4]
                val weakness = data[5]
                val polyphagia = data[6]
                val gt = data[7]
                val vb = data[8]
                val itching = data[9]
                val irritabiity = data[10]
                val dh = data[11]
                val pp = data[12]
                val ms = data[13]
                val alopecia = data[14]
                val obesity = data[15]
                val classPrediction = binding?.tvResultClassification?.text.toString()

                user?.let { user ->
                    user.userName = userLogin
                    user.date = date
                    user.age   = age
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
                    user.classInformation = binding?.tvInformation?.text.toString()
                }
                resultViewModel.insertResult(user as UserDiseaseEntity)


                val toListDisease =
                    ResultFragmentDirections.actionResultFragmentToNavigationHistory()
                view?.findNavController()?.navigate(toListDisease)

            }
            R.id.btn_process -> {
                classificationDisease()
                binding?.btnEdit?.isEnabled = false


            }
            R.id.btn_edit -> {
                val check = binding?.btnEdit?.isChecked

                if (check == true) {
                    rbIsEdit(true)

                    binding?.btnEdit?.textOff = "Ubah"
                    binding?.btnEdit?.setBackgroundColor(Color.GREEN)

                } else {
                    rbIsEdit(false)
                    binding?.btnEdit?.setBackgroundColor(Color.YELLOW)
                    binding?.btnEdit?.textOn = "Simpan"
                }
            }


        }
    }

    override fun onResume() {
        super.onResume()
        navBar?.visibility = View.GONE
        binding?.tvInformation?.visibility  = View.GONE
    }

    override fun onStart() {
        super.onStart()
        navBar?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        navBar?.visibility = View.VISIBLE
    }



    private fun rbIsEdit(isEdit: Boolean) {

        if (isEdit) {
            binding?.rbPeria?.isEnabled = true
            binding?.rbWanita?.isEnabled = true
            binding?.polyuriaYes?.isEnabled = true
            binding?.polyuriaNo?.isEnabled = true
            binding?.polydipsiaYes?.isEnabled = true
            binding?.polydipsiaNo?.isEnabled = true
            binding?.swlYes?.isEnabled = true
            binding?.swlNo?.isEnabled = true
            binding?.weaknessYes?.isEnabled = true
            binding?.weaknessNo?.isEnabled = true
            binding?.polyphagiaYes?.isEnabled = true
            binding?.polyphagiaNo?.isEnabled = true
            binding?.genitalThrushYes?.isEnabled = true
            binding?.genitalThrushNo?.isEnabled = true
            binding?.visualBlurringYes?.isEnabled = true
            binding?.visualBlurringNo?.isEnabled = true
            binding?.itchingYes?.isEnabled = true
            binding?.itchingNo?.isEnabled = true
            binding?.irritabilityYes?.isEnabled = true
            binding?.irritabilityNo?.isEnabled = true
            binding?.delayedHealingNo?.isEnabled = true
            binding?.delayedHealingYes?.isEnabled = true
            binding?.partialParesisYes?.isEnabled = true
            binding?.partialParesisNo?.isEnabled = true
            binding?.muscleStiffnessYes?.isEnabled = true
            binding?.muscleStiffnessNo?.isEnabled = true
            binding?.alopeciaYes?.isEnabled = true
            binding?.alopeciaNo?.isEnabled = true
            binding?.obesityYes?.isEnabled = true
            binding?.obesityNo?.isEnabled = true
        } else {

            binding?.rbPeria?.isEnabled = false
            binding?.rbWanita?.isEnabled = false
            binding?.polyuriaYes?.isEnabled = false
            binding?.polyuriaNo?.isEnabled = false
            binding?.polydipsiaYes?.isEnabled = false
            binding?.polydipsiaNo?.isEnabled = false
            binding?.swlYes?.isEnabled = false
            binding?.swlNo?.isEnabled = false
            binding?.weaknessYes?.isEnabled = false
            binding?.weaknessNo?.isEnabled = false
            binding?.polyphagiaYes?.isEnabled = false
            binding?.polyphagiaNo?.isEnabled = false
            binding?.genitalThrushYes?.isEnabled = false
            binding?.genitalThrushNo?.isEnabled = false
            binding?.visualBlurringYes?.isEnabled = false
            binding?.visualBlurringNo?.isEnabled = false
            binding?.itchingYes?.isEnabled = false
            binding?.itchingNo?.isEnabled = false
            binding?.irritabilityYes?.isEnabled = false
            binding?.irritabilityNo?.isEnabled = false
            binding?.delayedHealingNo?.isEnabled = false
            binding?.delayedHealingYes?.isEnabled = false
            binding?.partialParesisYes?.isEnabled = false
            binding?.partialParesisNo?.isEnabled = false
            binding?.muscleStiffnessYes?.isEnabled = false
            binding?.muscleStiffnessNo?.isEnabled = false
            binding?.alopeciaYes?.isEnabled = false
            binding?.alopeciaNo?.isEnabled = false
            binding?.obesityYes?.isEnabled = false
            binding?.obesityNo?.isEnabled = false
        }
    }


    private fun changeGender(gender: String): String {
        if (gender == "Pria") {
            return "Male"
        } else if (gender == "Wanita") {
            return "Female"
        }

        return gender
    }

    private fun changeYesNo(text: String): String {
        if (text == "Ya") {
            return "Yes"
        } else if (text == "Tidak") {
            return "No"
        }

        return text
    }
    private fun changeAge(text: String): String {
        if (text == "Di Atas 65 Tahun") {
            return "Above 65"
        } else if (text == "20-35 Tahun") {
            return "20-35"
        }else if (text == "36-45 Tahun") {
            return "36-45"
        }else if (text == "46-55 Tahun") {
            return "46-55"
        }else if (text == "56-65 Tahun") {
            return "56-65"
        }

        return text
    }

    private fun classificationDisease() {

        var age = data[0]
        age = changeAge(binding?.tvResultAge?.text.toString().trim())
        var gender = data[1]
        when (binding?.rgGender?.checkedRadioButtonId) {
            R.id.rb_Peria -> {
                gender = changeGender(binding?.rbPeria?.text.toString())
            }
            R.id.rb_Wanita -> {
                gender = changeGender(binding?.rbWanita?.text.toString())
            }
        }
        var polyuria = data[2]
        when (binding?.rgPolyuria?.checkedRadioButtonId) {
            R.id.polyuria_yes -> {
                polyuria = changeYesNo(binding?.polyuriaYes?.text.toString())
            }
            R.id.polyuria_no -> {
                polyuria = changeYesNo(binding?.polyuriaNo?.text.toString())
            }
        }
        var polydipsia = data[3]
        when (binding?.rgPolydipsia?.checkedRadioButtonId) {
            R.id.polydipsia_yes -> {
                polydipsia = changeYesNo(binding?.polydipsiaYes?.text.toString())
            }
            R.id.polydipsia_no -> {
                polydipsia = changeYesNo(binding?.polydipsiaNo?.text.toString())
            }
        }
        var swl = data[4]
        when (binding?.rgSwl?.checkedRadioButtonId) {
            R.id.swl_yes -> {
                swl = changeYesNo(binding?.swlYes?.text.toString())
            }
            R.id.swl_no -> {
                swl = changeYesNo(binding?.swlNo?.text.toString())
            }
        }
        var weakness = data[5]
        when (binding?.rgWeakness?.checkedRadioButtonId) {
            R.id.weakness_yes -> {
                weakness = changeYesNo(binding?.weaknessYes?.text.toString())
            }
            R.id.weakness_no -> {
                weakness = changeYesNo(binding?.weaknessNo?.text.toString())
            }
        }
        var polyphagia = data[6]
        when (binding?.rgPolyphagia?.checkedRadioButtonId) {
            R.id.polyphagia_yes -> {
                polyphagia = changeYesNo(binding?.polyphagiaYes?.text.toString())
            }
            R.id.polyphagia_no -> {
                polyphagia = changeYesNo(binding?.polyphagiaNo?.text.toString())
            }
        }
        var gt = data[7]
        when (binding?.rgGenitalThrush?.checkedRadioButtonId) {
            R.id.genitalThrush_yes -> {
                gt = changeYesNo(binding?.genitalThrushYes?.text.toString())
            }
            R.id.genitalThrush_no -> {
                gt = changeYesNo(binding?.genitalThrushNo?.text.toString())
            }
        }
        var vb = data[8]
        when (binding?.rgVisualBlurring?.checkedRadioButtonId) {
            R.id.visualBlurring_yes -> {
                vb = changeYesNo(binding?.visualBlurringYes?.text.toString())
            }
            R.id.visualBlurring_no -> {
                vb = changeYesNo(binding?.visualBlurringNo?.text.toString())
            }
        }
        var itching = data[9]
        when (binding?.rgItching?.checkedRadioButtonId) {
            R.id.itching_yes -> {
                itching = changeYesNo(binding?.itchingYes?.text.toString())
            }
            R.id.itching_no -> {
                itching = changeYesNo(binding?.itchingNo?.text.toString())
            }
        }
        var irritabiity = data[10]
        when (binding?.rgIrritability?.checkedRadioButtonId) {
            R.id.irritability_yes -> {
                irritabiity = changeYesNo(binding?.irritabilityYes?.text.toString())
            }
            R.id.irritability_no -> {
                irritabiity = changeYesNo(binding?.irritabilityNo?.text.toString())
            }
        }
        var dh = data[11]
        when (binding?.rgDelayedHealing?.checkedRadioButtonId) {
            R.id.delayedHealing_yes -> {
                dh = changeYesNo(binding?.delayedHealingYes?.text.toString())
            }
            R.id.delayedHealing_no -> {
                dh = changeYesNo(binding?.delayedHealingNo?.text.toString())
            }
        }
        var pp = data[12]
        when (binding?.rgPartialParesis?.checkedRadioButtonId) {
            R.id.partialParesis_yes -> {
                pp = changeYesNo(binding?.partialParesisYes?.text.toString())
            }
            R.id.partialParesis_no -> {
                pp = changeYesNo(binding?.partialParesisNo?.text.toString())
            }
        }
        var ms = data[13]
        when (binding?.rgMuscleStiffness?.checkedRadioButtonId) {
            R.id.muscleStiffness_yes -> {
                ms = changeYesNo(binding?.muscleStiffnessYes?.text.toString())
            }
            R.id.muscleStiffness_no -> {
                ms = changeYesNo(binding?.muscleStiffnessNo?.text.toString())
            }
        }
        var alopecia = data[14]
        when (binding?.rgAlopecia?.checkedRadioButtonId) {
            R.id.alopecia_yes -> {
                alopecia = changeYesNo(binding?.alopeciaYes?.text.toString())
            }
            R.id.alopecia_no -> {
                alopecia = changeYesNo(binding?.alopeciaNo?.text.toString())
            }
        }
        var obesity = data[15]
        when (binding?.rgObesity?.checkedRadioButtonId) {
            R.id.obesity_yes -> {
                obesity = changeYesNo(binding?.obesityYes?.text.toString())
            }
            R.id.obesity_no -> {
                obesity = changeYesNo(binding?.obesityNo?.text.toString())
            }
        }

        Log.d(
            "STATUS",
             age + polyuria + polydipsia + swl + weakness + polyphagia + gt + vb + itching + irritabiity + dh + pp + ms + alopecia + obesity
        )

        resultViewModel.resultClassification(
            age,
            gender,
            polyuria,
            polydipsia,
            swl,
            weakness,
            polyphagia, gt, vb, itching, irritabiity, dh, pp, ms, alopecia, obesity
        ).observe(viewLifecycleOwner,{
            if (it != null){
               when(it.status){
                   StatusResponse.SUCCESS ->{
                       binding?.btnProcess?.visibility =View.GONE
                       binding?.btnSave?.visibility =View.VISIBLE
                       result = it.body?.hasil.toString()
                       binding?.tvResultClassification?.text = result
                       if (result.equals("Positive")){
                           binding?.tvInformation?.visibility =View.VISIBLE
                           binding?.tvInformation?.text = getString(R.string.label_information,result,getString(R.string.label_positive))
                       }else if (result.equals("Negative")){
                           binding?.tvInformation?.visibility =View.VISIBLE
                           binding?.tvInformation?.text = getString(R.string.label_information,result,getString(R.string.label_negative))
                       }
                   }
                   StatusResponse.ERROR->{
                       Toast.makeText(context, "Periksa koneksi internet anda!", Toast.LENGTH_SHORT).show()
                   }
               }
            }

        })


    }


}