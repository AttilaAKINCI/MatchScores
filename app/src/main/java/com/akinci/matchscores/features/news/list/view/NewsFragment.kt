package com.akinci.matchscores.features.news.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.akinci.matchscores.R
import com.akinci.matchscores.common.component.SnackBar
import com.akinci.matchscores.common.component.adapter.ShimmerAdapter
import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.databinding.FragmentNewsBinding
import com.akinci.matchscores.features.news.list.adapter.NewsListAdapter
import com.akinci.matchscores.features.news.list.view.NewsFragmentDirections.Companion.actionNewsFragmentToNewsDetailFragment
import com.akinci.matchscores.features.news.list.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var binding : FragmentNewsBinding
    private val newsViewModel : NewsViewModel by activityViewModels()

    private val shimmerAdapter = ShimmerAdapter()
    lateinit var newsListAdapter : NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = newsViewModel

        // show action bar
        (activity as AppCompatActivity).supportActionBar?.show()

        // news listing adapter
        newsListAdapter = NewsListAdapter(clickListener = { newsLink ->
            // catch news row clicks and navigate to news Detail fragment
            Timber.d("Navigation to news detail fragment")

            NavHostFragment.findNavController(this).navigate(
                actionNewsFragmentToNewsDetailFragment(newsLink),
                null
            )
        })

        Timber.d("NewsFragment created..")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // fetch initial news
        newsViewModel.fetchNews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.news.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Loading -> {
                    Timber.d("Shimmer activated")
                    binding.recyclerNewsList.adapter = shimmerAdapter
                }
                is Resource.Success -> {
                    // send data to news adapter
                    it.data?.let { data ->
                        Timber.d("news is displayed")
                        binding.recyclerNewsList.adapter = newsListAdapter
                        newsListAdapter.submitList(data.news)
                    }
                }
                is Resource.Error -> {
                    // show error message on snackBar
                    SnackBar.makeLarge(binding.root, it.message, SnackBar.LENGTH_LONG).show()
                }
            }
        })
    }

}