package com.alawiyaa.mydiabetes.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.data.source.remote.StatusResponse
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentProfileBinding
import com.alawiyaa.mydiabetes.ui.signup.SignupActivity
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private  val binding get() = _binding
    private  lateinit var profileViewModel: ProfileViewModel
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
        if (activity != null){
            val sesi = SessionManager(requireContext())
            userRepository = UserRepository.getInstance(sesi)
            val factory = DiabetesViewModelFactory.getInstance(requireContext())
            profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

            profileViewModel.profileUser(userRepository.getUser().toString()).observe(viewLifecycleOwner,{ user->
                if (user != null){
                    when(user.status){
                        StatusResponse.SUCCESS->{
                            binding?.tvFullName?.text = user.body?.name
                            binding?.tvGender?.text = user.body?.gender
                            binding?.tvUsername?.text = user.body?.username
                        }
                        StatusResponse.ERROR ->{
                            Toast.makeText(requireContext(), "Periksa koneksi internet anda!", Toast.LENGTH_SHORT).show()                        }
                    }

                }
            })


            binding?.btnLogOut?.setOnClickListener {
                userRepository.logoutUser()
                startActivity(Intent(requireContext(), SignupActivity::class.java))
                activity?.finish()
            }

        }

    }
}