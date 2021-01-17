package com.matchscores.features.scores.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.matchscores.R
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.databinding.FragmentScoreWithComponentBinding
import com.matchscores.features.scores.viewmodel.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragmentWithComponent : BaseFragment() {

    private lateinit var binding : FragmentScoreWithComponentBinding
    private val scoresViewModel : ScoresViewModel by viewModels()

    override fun hasDropDownMenu() = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score_with_component, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = scoresViewModel

        scoresViewModel.scores.observe(viewLifecycleOwner, {
            if(it.isNotEmpty()){ binding.scoresProgressBar.visibility = View.GONE }
            Toast.makeText(context, "Skorlar güncellendi", Toast.LENGTH_SHORT).show()
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}