package com.matchscores.features.scores.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import timber.log.Timber

@AndroidEntryPoint
class ScoresFragment : BaseFragment() {

    private lateinit var binding : FragmentScoresBinding
    private val scoresViewModel : ScoresViewModel by viewModels()

    override fun hasDropDownMenu() = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scores, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // start with initial shimmer adapter
        binding.recyclerScoreList.adapter = ShimmerAdapter(ShimmerType.SCORE)
        binding.vm = scoresViewModel

        // news listing adapter
        val scoreListAdapter = ScoresListAdapter()

        scoresViewModel.scores.observe(viewLifecycleOwner, {
            binding.recyclerScoreList.adapter = scoreListAdapter
            scoreListAdapter.submitList(it)

            Toast.makeText(context, "Skorlar güncellendi", Toast.LENGTH_SHORT).show()
        })

        Timber.d("ScoresFragment created..")
        return binding.root
    }
}