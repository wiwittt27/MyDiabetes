package com.alawiyaa.mydiabetes.ui.dashboard.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.databinding.FragmentDashboardBinding
import com.alawiyaa.mydiabetes.databinding.FragmentFavoriteBinding
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory


class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = DiabetesViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            val adapter = AdapterFavorite()
            if (adapter.itemCount == 0){
                binding?.viewData?.root?.visibility = View.VISIBLE
            }

            favoriteViewModel.getListFavoriteMovie().observe(viewLifecycleOwner, { listNews ->
                if (listNews != null && listNews.size > 0) {
                    adapter.submitList(listNews)
                    binding?.viewData?.root?.visibility = View.GONE
                }
            })

            with(binding?.rvFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }
}