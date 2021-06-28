package com.alawiyaa.mydiabetes.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import com.alawiyaa.mydiabetes.databinding.ItemTypeDiabetesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AdapterNewsFactorGeneral : RecyclerView.Adapter<AdapterNewsFactorGeneral.MyAdapter>() {

    var onItemClick: ((ResponseNewsItem) -> Unit)? = null
    private val item = ArrayList<ResponseNewsItem>()
    fun setData(items: ArrayList<ResponseNewsItem>) {
        item.clear()
        item.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter =
        MyAdapter(
            ItemTypeDiabetesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )


    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int  =item.size

    inner class MyAdapter(private val binding : ItemTypeDiabetesBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseNewsItem){
           with(binding){
               tvDetailTitle.text = item.title
               Glide.with(itemView.context)
                   .load(item.imagePath)
                   .apply(
                       RequestOptions.placeholderOf(R.drawable.ic_loading)
                           .error(R.drawable.ic_error))
                   .into(imgDetail)
           }
        }


        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(item[adapterPosition])
            }
        }
    }
}