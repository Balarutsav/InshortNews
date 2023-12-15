package com.utsav.inshortnews.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utsav.inshortnews.data.model.NewsData
import com.utsav.inshortnews.databinding.ItemNewsBinding

class NewsAdapter(private val onItemClick: (NewsData) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsTypeViewHolder>() {

    private var itemList = listOf<NewsData>()

    inner class NewsTypeViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsType: NewsData, position: Int) {
            binding.newsData = newsType
            binding.executePendingBindings()

            itemView.setOnClickListener {
                onItemClick(newsType)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTypeViewHolder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsTypeViewHolder, position: Int) {
        val currentNewsType = itemList[position]
        holder.bind(currentNewsType, position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun submitList(newList: List<NewsData>) {
        itemList = newList
        notifyDataSetChanged()
    }


}
