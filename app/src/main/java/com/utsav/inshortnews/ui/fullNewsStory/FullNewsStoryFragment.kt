package com.utsav.inshortnews.ui.newsStory

import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.utsav.inshortnews.R
import com.utsav.inshortnews.databinding.FragmentFullNewsStoryBinding
import com.utsav.inshortnews.ui.base.BaseFragment


class FullNewsStoryFragment(override val layoutResourceId: Int = R.layout.fragment_full_news_story) :
    BaseFragment<FragmentFullNewsStoryBinding>() {
    private val newsArgs by navArgs<FullNewsStoryFragmentArgs>()

    override fun setupViews() {

        binding?.apply {
            webViewNews.let {
                setUpWebView(it, newsArgs.webLink)
            }

            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }

    }

    private fun setUpWebView(webView: WebView, webLink: String) {
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(webLink)
    }

}