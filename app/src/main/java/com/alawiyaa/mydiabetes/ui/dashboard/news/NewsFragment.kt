package com.alawiyaa.mydiabetes.ui.dashboard.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alawiyaa.mydiabetes.databinding.FragmentNewsBinding
import com.alawiyaa.mydiabetes.ui.dashboard.adapter.AdapterNewsDrug
import com.alawiyaa.mydiabetes.ui.dashboard.adapter.AdapterNewsFactorGeneral
import com.alawiyaa.mydiabetes.ui.dashboard.adapter.AdapterNewsFactorRisk
import com.alawiyaa.mydiabetes.ui.dashboard.adapter.AdapterNewsTypeDiabetes
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory
import com.alawiyaa.mydiabetes.vo.Status


class NewsFragment : Fragment() {
  private var _binding : FragmentNewsBinding? = null
    private val binding get() = _binding

    private lateinit var  newsViewModel : NewsViewModel
    private var itemAdapter : AdapterNewsTypeDiabetes? = null
    private var drugAdapter : AdapterNewsDrug? = null
    private var factorGeneralAdapter : AdapterNewsFactorGeneral? = null
    private var factorRiskAdapter : AdapterNewsFactorRisk? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ( activity != null ){
            val factory = DiabetesViewModelFactory.getInstance(requireActivity())
            newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]


            itemAdapter = AdapterNewsTypeDiabetes()

            drugAdapter = AdapterNewsDrug()

            factorGeneralAdapter = AdapterNewsFactorGeneral()

            factorRiskAdapter = AdapterNewsFactorRisk()



            getTypeDrug()
            getTypeDatabase()
            getTypeFactorRisk()
            getTypeFactorGeneral()


        }


    }

    private fun getTypeDatabase(){
        newsViewModel.getTypeDiabetes("diabetes").observe(viewLifecycleOwner, { item ->
            if (item != null){
                when(item.status){
                    Status.SUCCESS ->{
                        itemAdapter?.submitList(item.data)
                        itemAdapter?.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }

                }

            }
        })
        with(binding?.rvTypeDiabetes){
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
            this?.adapter = itemAdapter

        }
    }

    private fun getTypeDrug(){
        newsViewModel.getTypeDrug("natural_medicine").observe(viewLifecycleOwner, { item ->
            if (item != null){
                when(item.status){
                    Status.SUCCESS ->{
                        drugAdapter?.submitList(item.data)
                        drugAdapter?.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }

                }

            }
        })
        with(binding?.rvTypeDrug){
            this?.layoutManager = GridLayoutManager(context,2)
            this?.setHasFixedSize(true)
            this?.adapter = drugAdapter

        }
    }

    private fun getTypeFactorGeneral(){
        newsViewModel.getTypeFactorGeneral("faktor_diabetes").observe(viewLifecycleOwner, { item ->
            if (item != null){
                when(item.status){
                    Status.SUCCESS ->{
                        factorGeneralAdapter?.submitList(item.data)
                        factorGeneralAdapter?.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }

                }

            }
        })
        with(binding?.rvFactorDiabetes){
            this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
            this?.adapter = factorGeneralAdapter

        }
    }

    private fun getTypeFactorRisk(){
        newsViewModel.getTypeFactorRisk("faktor_resiko").observe(viewLifecycleOwner, { item ->
            if (item != null){
                when(item.status){
                    Status.SUCCESS ->{
                        factorRiskAdapter?.submitList(item.data)
                        factorRiskAdapter?.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(requireContext(), "Periksa koneksi internet anda!", Toast.LENGTH_SHORT).show()                    }

                }

            }
        })
        with(binding?.rvFactorRisk){
            this?.layoutManager = GridLayoutManager(context,2)
            this?.setHasFixedSize(true)
            this?.adapter = factorRiskAdapter
            this?.recycledViewPool?.clear()
            this?.setRecycledViewPool(RecyclerView.RecycledViewPool())

        }
    }
}