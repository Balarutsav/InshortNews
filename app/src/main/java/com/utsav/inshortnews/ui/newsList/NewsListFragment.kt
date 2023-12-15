package com.utsav.inshortnews.ui.newsList

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.utsav.inshortnews.R
import com.utsav.inshortnews.data.model.NewsData
import com.utsav.inshortnews.data.model.NewsTypeData
import com.utsav.inshortnews.data.remote.ApiResources
import com.utsav.inshortnews.databinding.FragmentNewsListBinding
import com.utsav.inshortnews.ui.base.BaseFragment
import com.utsav.inshortnews.utils.extension.hideLoader
import com.utsav.inshortnews.utils.extension.showInternetDialog
import com.utsav.inshortnews.utils.extension.showLoader
import com.utsav.inshortnews.utils.extension.showMessageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsListFragment(override val layoutResourceId: Int = R.layout.fragment_news_list) :
    BaseFragment<FragmentNewsListBinding>() {
    val viewModel: NewListViewModel by viewModels()
    override fun setupViews() {

        binding?.apply {
            val newsTypeAdapter = NewsTypeAdapter { selectedNewsType ->

            }
            newsTypeAdapter.submitList(getListOfNewsType())
            rvType.adapter = newsTypeAdapter
        }
        addObserver()
        viewModel.getHealthNews()

    }

    fun addObserver() {
        lifecycleScope.launch {
            viewModel.stateNewsResponse.collect { state ->
                when (state.status) {
                    ApiResources.Status.SUCCESS -> {
                        hideLoader()

                        Log.e(TAG, "Response :  ${state.data}")

                        state.data?.articles?.let { setNewsList(it) }

                    }

                    ApiResources.Status.ERROR -> {
                        state.message?.let {
                            showMessageDialog(
                                message = it,
                                title = getString(R.string.app_name)
                            ) { }
                        }
                        Log.e(TAG, "addObserver: error")
                        hideLoader()
                    }

                    ApiResources.Status.LOADING -> {
                        Log.e(TAG, "addObserver: loading")
                        showLoader()
                    }

                    ApiResources.Status.NO_INTERNET_CONNECTION -> {
                        Log.e(TAG, "addObserver: loading")
                        showInternetDialog()
                        hideLoader()
                    }

                    ApiResources.Status.UNKNOWN -> {
                        Log.e(TAG, "addObserver: unknown")
                        hideLoader()
                    }

                    ApiResources.Status.SHIMMER_EFFECT -> {

                        hideLoader()
                    }
                }
            }
        }

    }

    private fun setNewsList(articles: List<NewsData>) {

        val adapter = NewsAdapter {

        }
        adapter.submitList(articles)

        binding?.run {
            rvNews.adapter = adapter
        }
    }

    fun getListOfNewsType(): List<NewsTypeData> {
        return mutableListOf<NewsTypeData>().apply {
            add(NewsTypeData("All", R.drawable.document_text, true))
            add(NewsTypeData("World", R.drawable.global))
            add(NewsTypeData("StartUps", R.drawable.flash))
            add(NewsTypeData("Tech", R.drawable.cpu_charge))
        }
    }
}