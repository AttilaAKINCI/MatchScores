package com.akinci.matchscores.features.scores.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.akinci.matchscores.R
import com.akinci.matchscores.common.component.base.BaseFragment
import com.akinci.matchscores.databinding.FragmentScoreWithComponentBinding
import com.akinci.matchscores.features.scores.viewmodel.ScoresViewModel
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