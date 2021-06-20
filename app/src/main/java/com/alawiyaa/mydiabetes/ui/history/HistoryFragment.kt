package com.alawiyaa.mydiabetes.ui.history

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.databinding.FragmentHistoryBinding
import com.alawiyaa.mydiabetes.ui.history.diagnosis.DiagnosisActivity


class HistoryFragment : Fragment() {

    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fabDiagnosis?.setOnClickListener {
            startActivity(Intent(requireContext(), DiagnosisActivity::class.java))
        }
    }
}