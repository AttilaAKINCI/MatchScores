package com.akinci.matchscores.features.scores.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akinci.matchscores.R
import com.akinci.matchscores.common.component.adapter.ShimmerAdapter
import com.akinci.matchscores.databinding.FragmentScoresBinding
import com.akinci.matchscores.features.scores.adapter.ScoresListAdapter
import com.akinci.matchscores.features.scores.viewmodel.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi
import timber.log.Timber

@ObsoleteCoroutinesApi
@AndroidEntryPoint
class ScoresFragment : Fragment() {

    private lateinit var binding : FragmentScoresBinding
    private val scoresViewModel : ScoresViewModel by viewModels()

    private val shimmerAdapter = ShimmerAdapter()
    lateinit var scoreListAdapter : ScoresListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scores, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = scoresViewModel

        // news listing adapter
        scoreListAdapter = ScoresListAdapter()

        Timber.d("ScoresFragment created..")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // fetch score table content
        scoresViewModel.fetchScores()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scoresViewModel.scores.observe(viewLifecycleOwner, {
            binding.recyclerScoreList.adapter = scoreListAdapter
            scoreListAdapter.submitList(it)

            Toast.makeText(context, "Skorlar güncellendi", Toast.LENGTH_SHORT).show()
        })
    }

}