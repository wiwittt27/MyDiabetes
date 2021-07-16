package com.alawiyaa.mydiabetes.ui.dashboard.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alawiyaa.mydiabetes.BuildConfig
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.databinding.ItemFavoriteBinding
import com.alawiyaa.mydiabetes.databinding.ItemNewsDiabetesBinding
import com.alawiyaa.mydiabetes.ui.dashboard.DashboardFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AdapterFavorite : PagedListAdapter<NewsEntity, AdapterFavorite.MyAdapter>(DIFF_CALLBACK) {

    companion  object  {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsEntity>() {
            override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem.newsId == newItem.newsId
            }
            override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter =
        MyAdapter(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )


    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news)
        }
    }



    inner class MyAdapter(private val binding : ItemFavoriteBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : NewsEntity){
            with(binding){
                tvDetailTitle.text = item.title
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_URL +"diabetes/image/" + item.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgDetail)

                cardType.setOnClickListener {
                    val detail =
                        DashboardFragmentDirections.actionNavigationDashboardToDetailFragmentNews(
                            item
                        )
                    cardType.findNavController().navigate(detail)
                }
            }
        }

    }
}