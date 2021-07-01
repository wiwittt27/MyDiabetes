package com.alawiyaa.mydiabetes.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import com.alawiyaa.mydiabetes.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {
     private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding
    private val dashboardViewModel : DashboardViewModel by viewModels()
   private var itemAdapter : AdapterNewsTypeDiabetes? = null
   private var drugAdapter : AdapterNewsDrug? = null
   private var factorGeneralAdapter : AdapterNewsFactorGeneral? = null
   private var factorRiskAdapter : AdapterNewsFactorRisk? = null



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
             itemAdapter = AdapterNewsTypeDiabetes()
            itemAdapter?.onItemClick = {typeDiabetes->
            val toDetail = DashboardFragmentDirections.actionNavigationDashboardToDetailFragmentNews(typeDiabetes)
                view.findNavController().navigate(toDetail)

            }
             drugAdapter = AdapterNewsDrug()
            drugAdapter?.onItemClick = {typeDrug->
                val toDetail = DashboardFragmentDirections.actionNavigationDashboardToDetailFragmentNews(typeDrug)
                view.findNavController().navigate(toDetail)
            }
            factorGeneralAdapter = AdapterNewsFactorGeneral()
            factorGeneralAdapter?.onItemClick = {typeGeneral->
                val toDetail = DashboardFragmentDirections.actionNavigationDashboardToDetailFragmentNews(typeGeneral)
                view.findNavController().navigate(toDetail)
            }
            factorRiskAdapter = AdapterNewsFactorRisk()
            factorRiskAdapter?.onItemClick ={toRisk->
                val toDetail = DashboardFragmentDirections.actionNavigationDashboardToDetailFragmentNews(toRisk)
                view.findNavController().navigate(toDetail)
            }

            dashboardViewModel.toastText.observe(viewLifecycleOwner, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })


            getTypeDrug()
            getTypeDatabase()
            getTypeFactorRisk()
            getTypeFactorGeneral()


        }
    }
    fun getTypeDatabase(){
        dashboardViewModel.getNewsDiabetes().observe(viewLifecycleOwner, { item ->
            itemAdapter?.setData(item)
            itemAdapter?.notifyDataSetChanged()
        })
        dashboardViewModel.getTypeDiabetes("diabetes")
        with(binding?.rvTypeDiabetes){
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
            this?.adapter = itemAdapter

        }
    }

    fun getTypeDrug(){
        dashboardViewModel.getNewsDrug().observe(viewLifecycleOwner, { item ->
            drugAdapter?.setData(item)
            drugAdapter?.notifyDataSetChanged()
        })
        dashboardViewModel.getTypeDrug("natural_medicine")
        with(binding?.rvTypeDrug){
            this?.layoutManager = GridLayoutManager(context,2)
            this?.setHasFixedSize(true)
            this?.adapter = drugAdapter

        }
    }

    fun getTypeFactorGeneral(){
        dashboardViewModel.getNewsFactor().observe(viewLifecycleOwner, { item ->
            factorGeneralAdapter?.setData(item)
            factorGeneralAdapter?.notifyDataSetChanged()
        })
        dashboardViewModel.getTypeFactor("faktor_diabetes")
        with(binding?.rvFactorDiabetes){
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
            this?.adapter = factorGeneralAdapter

        }
    }

    private fun getTypeFactorRisk(){
        dashboardViewModel.getNewsFactorRisk().observe(viewLifecycleOwner, { item ->
            factorRiskAdapter?.setData(item)
            factorRiskAdapter?.notifyDataSetChanged()
        })
        dashboardViewModel.getTypeFactorRisk("faktor_resiko")
        with(binding?.rvFactorRisk){
            this?.layoutManager = GridLayoutManager(context,2)
            this?.setHasFixedSize(true)
            this?.adapter = factorRiskAdapter

        }
    }
}