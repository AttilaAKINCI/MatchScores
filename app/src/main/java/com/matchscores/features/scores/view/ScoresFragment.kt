package com.matchscores.features.scores.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.matchscores.R
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.commons.component.list.ShimmerAdapter
import com.matchscores.commons.component.list.ShimmerType
import com.matchscores.databinding.FragmentScoresBinding
import com.matchscores.features.scores.components.ScoresListAdapter
import com.matchscores.features.scores.viewmodel.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoresFragment : BaseFragment() {

    private lateinit var binding : FragmentScoresBinding
    private val scoresViewModel : ScoresViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scores, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // start with initial shimmer adapter
        binding.recyclerScoreList.adapter = ShimmerAdapter(ShimmerType.SCORE)

        // news listing adapter
        val scoreListAdapter = ScoresListAdapter()

        scoresViewModel.scores.observe(viewLifecycleOwner, {
            binding.recyclerScoreList.adapter = scoreListAdapter
            scoreListAdapter.submitList(it)
        })

        return binding.root
    }
}