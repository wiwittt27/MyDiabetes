package com.alawiyaa.mydiabetes.ui.signup

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.ui.signup.login.LoginFragment
import com.alawiyaa.mydiabetes.ui.signup.register.RegisterFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.login, R.string.signup)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> LoginFragment()
            1 -> RegisterFragment()
            else -> Fragment()


    }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])
}