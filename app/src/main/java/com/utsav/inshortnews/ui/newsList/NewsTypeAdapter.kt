package com.utsav.inshortnews.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utsav.inshortnews.data.model.NewsTypeData
import com.utsav.inshortnews.databinding.ItemNewsTypeBinding

class NewsTypeAdapter(private val onItemClick: (NewsTypeData) -> Unit) :
    RecyclerView.Adapter<NewsTypeAdapter.NewsTypeViewHolder>() {

    private var itemList = listOf<NewsTypeData>()

    inner class NewsTypeViewHolder(private val binding: ItemNewsTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsType: NewsTypeData,position: Int) {
            binding.newsType = newsType
            binding.executePendingBindings()

            itemView.setOnClickListener {
                onItemClick(newsType)
                updateList(position = position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTypeViewHolder {
        val binding =
            ItemNewsTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsTypeViewHolder, position: Int) {
        val currentNewsType = itemList[position]
        holder.bind(currentNewsType,position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    fun submitList(newList: List<NewsTypeData>) {
        itemList = newList
        notifyDataSetChanged()
    }

    fun updateList(position:Int){
        itemList.forEachIndexed { index, item ->
            item.isSelected = (index == position)
        }
        notifyDataSetChanged()

    }

}
