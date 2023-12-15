package com.utsav.inshortnews.ui.newsDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.utsav.inshortnews.R
import com.utsav.inshortnews.data.model.NewsData
import com.utsav.inshortnews.databinding.FragmentNewsDetailsBinding
import com.utsav.inshortnews.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailsFragment(override val layoutResourceId: Int = R.layout.fragment_news_details) :
    BaseFragment<FragmentNewsDetailsBinding>() {
    val arguments: NewsDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setupViews() {

        binding?.apply {
            val data:NewsData=arguments.newsData
            newsData = arguments.newsData
            ivImage.transitionName = arguments.transitoinName
            ivBack.setOnClickListener {
                findNavController().navigateUp()


            }
            ivShare.setOnClickListener {
                val shareIntent = `Intent`(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT,                         arguments.newsData.url
                    )
                }

                val chooserIntent = Intent.createChooser(shareIntent, "Share with")

                if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(chooserIntent)
                }
            }
            tvViewRead.setOnClickListener {

                val action =
                    NewsDetailsFragmentDirections.actionNewsDetailsFragmentToFullNewsStoryFragment(
                        arguments.newsData.url
                    )
                navigateToDestination(action)

            }
        }
    }

}