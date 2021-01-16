package com.matchscores.features.news.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.matchscores.R
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.commons.component.list.ShimmerAdapter
import com.matchscores.commons.component.list.ShimmerType
import com.matchscores.databinding.FragmentNewsBinding
import com.matchscores.features.news.list.components.NewsListAdapter
import com.matchscores.features.news.list.view.NewsFragmentDirections.Companion.actionNewsFragmentToNewsDetailFragment
import com.matchscores.features.news.list.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    private lateinit var binding : FragmentNewsBinding
    private val newsViewModel : NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = newsViewModel

        // start with initial shimmer adapter
        binding.recyclerNewsList.adapter = ShimmerAdapter(ShimmerType.CARD)

        // news listing adapter
        val newsListAdapter = NewsListAdapter(clickListener = { newsLink ->
            // catch news row clicks and navigate to news Detail fragment
            Timber.i("Navigation to news detail fragment")

            NavHostFragment.findNavController(this).navigate(
                actionNewsFragmentToNewsDetailFragment(newsLink),
                null
            )
        })

        // when news fetched, load to recyclerViewList
        newsViewModel.news.observe(viewLifecycleOwner, {
            binding.recyclerNewsList.adapter = newsListAdapter
            newsListAdapter.submitList(it.news)
        })

        return binding.root
    }

}