package com.utsav.inshortnews.ui.newsList

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.utsav.inshortnews.R
import com.utsav.inshortnews.data.model.NewsData
import com.utsav.inshortnews.data.model.NewsTypeData
import com.utsav.inshortnews.data.remote.ApiResources
import com.utsav.inshortnews.databinding.FragmentNewsListBinding
import com.utsav.inshortnews.ui.base.BaseFragment
import com.utsav.inshortnews.utils.NewsType
import com.utsav.inshortnews.utils.extension.showInternetDialog
import com.utsav.inshortnews.utils.extension.showMessageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsListFragment(override val layoutResourceId: Int = R.layout.fragment_news_list) :
    BaseFragment<FragmentNewsListBinding>() {
    val viewModel: NewListViewModel by viewModels()
    override fun setupViews() {
        viewModel.getNewsList(NewsType.Sports.fullName)
        binding?.apply {
            val newsTypeAdapter = NewsTypeAdapter { selectedNewsType ->
                NewsType.getFullNameState(selectedNewsType.type)
                    ?.let { viewModel.getNewsList(it) }
            }
            newsTypeAdapter.submitList(getListOfNewsType())
            rvType.adapter = newsTypeAdapter
        }
        addObserver()

    }

    fun addObserver() {
        lifecycleScope.launch {
            viewModel.stateNewsResponse.collect { state ->
                when (state.status) {
                    ApiResources.Status.SUCCESS -> {
                        binding?.run {
                            shimmerLayout.visibility = View.GONE
                            rvNews.visibility=View.VISIBLE

                        }
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
                        binding?.run {
                            shimmerLayout.visibility = View.GONE
                            rvNews.visibility=View.VISIBLE

                        }

                    }

                    ApiResources.Status.LOADING -> {
                        Log.e(TAG, "addObserver: loading")
                        binding?.run {
                            shimmerLayout.visibility = View.VISIBLE
                            rvNews.visibility=View.GONE

                        }
                    }

                    ApiResources.Status.NO_INTERNET_CONNECTION -> {
                        Log.e(TAG, "addObserver: loading")
                        showInternetDialog()
                        binding?.run {
                            shimmerLayout.visibility = View.GONE
                            rvNews.visibility=View.VISIBLE

                        }
                    }

                    ApiResources.Status.UNKNOWN -> {
                        Log.e(TAG, "addObserver: unknown")
                        binding?.run {
                            shimmerLayout.visibility = View.GONE
                            rvNews.visibility=View.VISIBLE

                        }
                    }

                    ApiResources.Status.SHIMMER_EFFECT -> {

                        binding?.run {
                            shimmerLayout.visibility = View.GONE
                            rvNews.visibility=View.VISIBLE

                        }
                    }
                }
            }
        }

    }

    private fun setNewsList(articles: List<NewsData>) {

        val adapter = NewsAdapter { data, binding ->
            run {

                val transitionNameImage = binding.ivImage.transitionName
                val extras = FragmentNavigatorExtras(
                    binding.ivImage to transitionNameImage,
                )
                findNavController().navigate(
                    NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(
                        data, transitionNameImage
                    ), navigatorExtras = extras
                )

            }

        }
        adapter.submitList(articles)

        binding?.run {
            rvNews.adapter = adapter
        }
    }

    fun getListOfNewsType(): List<NewsTypeData> {
        return mutableListOf<NewsTypeData>().apply {
            add(NewsTypeData(NewsType.Sports.name, R.drawable.ic_sports, true))
            add(NewsTypeData(NewsType.Business.name, R.drawable.ic_business))
            add(NewsTypeData(NewsType.Tech.name, R.drawable.cpu_charge))
        }
    }
}