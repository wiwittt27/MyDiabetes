package com.alawiyaa.mydiabetes.ui.dashboard.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.databinding.FragmentDetailBookmarkBinding
import com.alawiyaa.mydiabetes.ui.history.result.ResultActivity
import com.alawiyaa.mydiabetes.ui.history.result.ResultViewModel
import com.alawiyaa.mydiabetes.viewmodel.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetailFragmentBookmark : Fragment() {
    private  var _binding : FragmentDetailBookmarkBinding? = null
    private val binding get() = _binding
    private var data: UserDiseaseEntity? = null
        private var navBar: BottomNavigationView? = null
    private lateinit var  resultViewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBookmarkBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navBar = requireActivity().findViewById(R.id.nav_view)
        resultViewModel = obtainViewModel()
       data = DetailFragmentBookmarkArgs.fromBundle(arguments as Bundle).toBookmarkDetail

        binding?.tvGender?.text = data?.gender
        binding?.tvPolyuria?.text = data?.polyuria
        binding?.tvPolydipsia?.text = data?.polydipsia
        binding?.tvPolyphagia?.text = data?.polyphagia
        binding?.tvSwl?.text = data?.swl
        binding?.tvPartialParesis?.text = data?.partialParesis
        binding?.tvVisualBlurring?.text =data?.visualBlurring
        binding?.tvDelayedHealing?.text = data?.delayedHealing
        binding?.tvWeakness?.text = data?.weakness
        binding?.tvAlopecia?.text = data?.alopecia
        binding?.tvMuscleStiffness?.text = data?.muscleStiffness
        binding?.tvGenitalThrush?.text = data?.genitalThrush
        binding?.tvItching?.text = data?.itching
        binding?.tvIrritability?.text =data?.irritability
        binding?.tvObesity?.text = data?.obesity
        binding?.tvResult?.text = data?.classPrediction

        binding?.btnSubmit?.setOnClickListener { data?.let {  resultViewModel.delete(it)}
        activity?.onBackPressed()
        }

        
    }

    private fun obtainViewModel(): ResultViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(requireActivity(), factory).get(ResultViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        navBar?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        navBar?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}