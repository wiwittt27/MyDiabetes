package com.alawiyaa.mydiabetes.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentHistoryBinding
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class HistoryFragment : Fragment() {


    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: HistoryPagedAdapter
    private lateinit var mainViewModel :HistoryViewModel
    private var userLogin = ""
    lateinit var userRepository: UserRepository
    private var navBar: BottomNavigationView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      if (activity!= null){
          adapter = HistoryPagedAdapter(requireActivity())
          val factory = DiabetesViewModelFactory.getInstance(requireActivity())
          mainViewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]
          navBar = requireActivity().findViewById(R.id.nav_view)

          val sesi = SessionManager(requireContext())
          userRepository = UserRepository.getInstance(sesi)
          userRepository.getUser()?.let { userLogin = it }



          mainViewModel.getAllResultUsername(userLogin).observe(viewLifecycleOwner, {listUser->
              if (listUser != null && listUser.size > 0) {
                  adapter.submitList(listUser)
                  binding?.viewData?.root?.visibility = View.GONE
              }
          })
      }
        with(binding?.rvResult){
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = adapter
        }
        if (adapter.itemCount == 0){
            binding?.viewData?.root?.visibility = View.VISIBLE
        }
        binding?.fabDiagnosis?.setOnClickListener {
         val toDiagnosis =  HistoryFragmentDirections.actionNavigationHistoryToDiagnosisFragment()
            view.findNavController().navigate(toDiagnosis)
        }
    }

    override fun onPause() {
        super.onPause()
        showBookmark()
    }

    override fun onStop() {
        super.onStop()
        showBookmark()
    }

    override fun onStart() {
        super.onStart()
        showBookmark()
        navBar?.visibility = View.VISIBLE

    }

    private fun showBookmark(){
        mainViewModel.getAllResultUsername(userLogin).observe(viewLifecycleOwner, {listUser->
            if (listUser != null && listUser.size > 0) {
                adapter.submitList(listUser)
                binding?.viewData?.root?.visibility = View.GONE
            }
        })
    }


    override fun onResume() {
        super.onResume()
        showBookmark()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}