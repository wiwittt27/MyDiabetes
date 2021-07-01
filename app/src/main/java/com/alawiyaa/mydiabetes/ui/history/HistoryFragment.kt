package com.alawiyaa.mydiabetes.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.SortUtils
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentHistoryBinding
import com.alawiyaa.mydiabetes.ui.history.diagnosis.DiagnosisActivity
import com.alawiyaa.mydiabetes.ui.profile.ProfileViewModel
import com.alawiyaa.mydiabetes.viewmodel.ViewModelFactory


class HistoryFragment : Fragment() {


    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: HistoryPagedAdapter
    private lateinit var mainViewModel :HistoryViewModel
    private var userLogin = ""
    lateinit var userRepository: UserRepository

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


          val sesi = SessionManager(requireContext())
          userRepository = UserRepository.getInstance(sesi)
          userRepository.getUser()?.let { userLogin = it }

          mainViewModel = obtainViewModel()

          mainViewModel.getAllResultUsername(userLogin).observe(viewLifecycleOwner, userObserver)
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
    }

    private fun showBookmark(){
        mainViewModel.getAllResultUsername(userLogin).observe(viewLifecycleOwner, userObserver)
    }


    override fun onResume() {
        super.onResume()
        showBookmark()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun obtainViewModel(): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(requireActivity(),factory).get(HistoryViewModel::class.java)
    }
    private val userObserver = Observer<PagedList<UserDiseaseEntity>> { userList ->
        if (userList != null && userList.size > 0) {
            adapter.submitList(userList)
            binding?.viewData?.root?.visibility = View.GONE
        }
    }

}