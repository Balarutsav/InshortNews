package com.utsav.inshortnews.ui.newsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utsav.inshortnews.R
import com.utsav.inshortnews.databinding.FragmentNewsListBinding
import com.utsav.inshortnews.ui.base.BaseFragment


class NewsListFragment(override val layoutResourceId: Int = R.layout.fragment_news_list) :
    BaseFragment<FragmentNewsListBinding>() {
    override fun setupViews() {

        binding?.apply {
            btnNext.setOnClickListener {
                navigateToDestination(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment())
            }
        }
    }
}