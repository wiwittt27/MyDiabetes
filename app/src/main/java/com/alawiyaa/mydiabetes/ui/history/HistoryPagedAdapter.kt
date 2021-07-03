package com.alawiyaa.mydiabetes.ui.history

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.databinding.ItemUserHistoryBinding

class HistoryPagedAdapter (private val activity: Activity) : PagedListAdapter<UserDiseaseEntity, HistoryPagedAdapter.UserViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<UserDiseaseEntity> = object : DiffUtil.ItemCallback<UserDiseaseEntity>() {
            override fun areItemsTheSame(oldNote: UserDiseaseEntity, newNote: UserDiseaseEntity): Boolean {
                return oldNote.userName == newNote.userName
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: UserDiseaseEntity, newNote: UserDiseaseEntity): Boolean {
                return oldNote == newNote
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position) as UserDiseaseEntity)
    }



    inner class UserViewHolder(private val binding: ItemUserHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserDiseaseEntity) {
            with(binding) {
                tvResultName.text = user.userName
                tvDate.text = user.date
                tvResultClass.text = user.classPrediction
                tvResultGender.text = user.gender

                if (tvResultClass.text.equals("Positive")){
                    tvResultClass.setBackgroundColor(Color.RED)
                }else if (tvResultClass.equals("Negative")){
                    tvResultClass.setBackgroundColor(Color.BLUE)
                }
                cvUser.setOnClickListener {
                    val detail = HistoryFragmentDirections.actionNavigationHistoryToDetailFragmentBookmark(user)
                   cvUser.findNavController().navigate(detail)
                }




            }


        }

    }


}