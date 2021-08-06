package com.alawiyaa.mydiabetes.ui.dashboard.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.BuildConfig
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentDetailNewsBinding
import com.alawiyaa.mydiabetes.ui.dashboard.favorite.FavoriteViewModel
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetailFragmentNews : Fragment() {
    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding
    private var data: NewsEntity? = null
    private var navBar: BottomNavigationView? = null
    private var userLogin = ""
    lateinit var userRepository: UserRepository
    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sesi = SessionManager(requireContext())
        userRepository = UserRepository.getInstance(sesi)
        userRepository.getUser()?.let { userLogin = it }
        val factory = DiabetesViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        navBar = requireActivity().findViewById(R.id.nav_view)
        data = DetailFragmentNewsArgs.fromBundle(arguments as Bundle).toDetailNews
        data?.let { displayDetail(it) }
        setHasOptionsMenu(true)
    }

    private fun displayDetail(data : NewsEntity){
        val status = data.isFavorite
        binding?.tvDetailTitle?.text = data.title
        binding?.tvDesc?.text = data.description
        binding?.tvSource?.text = data.source

        binding?.imgDetail?.let {
            Glide.with(requireContext())
                .load(BuildConfig.BASE_URL +"diabetes/image/" + data.imagePath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(it)

        }
        setFavoriteState(status)
        binding?.fabFavorite?.setOnClickListener {
            setFavorite(data)
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

    private fun setFavorite(news: NewsEntity) {
        if (news.isFavorite) {
            showToast("${news.title} Dihapus dari favorite")
        } else {
            showToast("${news.title} Ditambahkan ke favorit")

        }
        favoriteViewModel.setFavoriteTvShow(news)

    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun setFavoriteState(status: Boolean) {
        if (status) {
            binding?.fabFavorite?.setImageResource(R.drawable.ic_favorite_black)
        } else {
            binding?.fabFavorite?.setImageResource(R.drawable.ic_favorite_white)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}