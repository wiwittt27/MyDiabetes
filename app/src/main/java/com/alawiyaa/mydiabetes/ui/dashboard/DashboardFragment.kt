package com.alawiyaa.mydiabetes.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alawiyaa.mydiabetes.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {
     private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ( activity != null ){


        setupViewPager(requireContext())

        }
    }

    private fun setupViewPager(context: Context) {
        val sectionsPagerAdapter = SectionsPagerAdapter(context, childFragmentManager)
        binding?.mainViewPager?.adapter = sectionsPagerAdapter
        binding?.mainTablayout?.setupWithViewPager(binding?.mainViewPager)
    }

}