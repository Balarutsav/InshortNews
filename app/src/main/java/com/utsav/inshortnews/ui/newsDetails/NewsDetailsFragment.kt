package com.utsav.inshortnews.ui.newsDetails

import androidx.navigation.fragment.findNavController
import com.utsav.inshortnews.R
import com.utsav.inshortnews.databinding.FragmentNewsDetailsBinding
import com.utsav.inshortnews.ui.base.BaseFragment
import com.utsav.inshortnews.ui.newsList.NewsListFragmentDirections


class NewsDetailsFragment(override val layoutResourceId: Int = R.layout.fragment_news_details) :
    BaseFragment<FragmentNewsDetailsBinding>() {
    override fun setupViews() {

        binding?.apply {
            ivBack.setOnClickListener {
                findNavController().navigateUp()


            }
        }
    }

}