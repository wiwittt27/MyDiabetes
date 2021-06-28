package com.alawiyaa.mydiabetes.ui.dashboard.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import com.alawiyaa.mydiabetes.databinding.FragmentDetailNewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetailFragmentNews : Fragment() {
    private  var _binding : FragmentDetailNewsBinding? = null
    private val binding get() = _binding
    private var data: ResponseNewsItem? = null
    private var navBar: BottomNavigationView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailNewsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            navBar = requireActivity().findViewById(R.id.nav_view)
        data = DetailFragmentNewsArgs.fromBundle(arguments as Bundle).toDetailNews

        binding?.tvDetailTitle?.text = data?.title
        binding?.tvDesc?.text = data?.description
        binding?.tvSource?.text = data?.source
        binding?.imgDetail?.let {
            Glide.with(requireContext())
                .load(data?.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(it)
        }
    }

    override fun onStart() {
        super.onStart()
        navBar?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        navBar?.visibility = View.VISIBLE
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}