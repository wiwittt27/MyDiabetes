package com.alawiyaa.mydiabetes.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentProfileBinding
import com.alawiyaa.mydiabetes.ui.history.result.ResultViewModel
import com.alawiyaa.mydiabetes.ui.login.LoginActivity


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private  val binding get() = _binding
    private  val profileViewModel: ProfileViewModel by viewModels()
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sesi = SessionManager(requireContext())
        userRepository = UserRepository.getInstance(sesi)

        userRepository.getUser()?.let { profileViewModel.getUserProfile(it) }
        profileViewModel.isName.observe(viewLifecycleOwner,{
            binding?.tvFullName?.text = it
        })
        profileViewModel.isUsername.observe(viewLifecycleOwner,{
            binding?.tvUsername?.text = it
        })
        profileViewModel.isGender.observe(viewLifecycleOwner,{
            binding?.tvGender?.text = it
        })

        binding?.btnLogOut?.setOnClickListener {
            userRepository.logoutUser()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()
        }


    }
}