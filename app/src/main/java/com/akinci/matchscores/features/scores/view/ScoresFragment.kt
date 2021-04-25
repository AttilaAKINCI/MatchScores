package com.akinci.matchscores.features.scores.view

import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.akinci.matchscores.R
import com.akinci.matchscores.common.component.SnackBar
import com.akinci.matchscores.common.component.TileDrawable
import com.akinci.matchscores.common.component.listview.ShimmerAdapter
import com.akinci.matchscores.common.component.listview.viewholder.ShimmerViewHolderTypeFactory.Companion.SCORE_SHIMMER_VIEW
import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.databinding.FragmentScoresBinding
import com.akinci.matchscores.features.scores.adapter.ScoresListAdapter
import com.akinci.matchscores.features.scores.viewmodel.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import timber.log.Timber

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ScoresFragment : Fragment() {

    private lateinit var binding : FragmentScoresBinding
    private val scoresViewModel : ScoresViewModel by activityViewModels()

    private val shimmerAdapter = ShimmerAdapter(SCORE_SHIMMER_VIEW)
    private var scoreListAdapter = ScoresListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        /** Initialization of ViewBinding not need for DataBinding here **/
        binding = FragmentScoresBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        // set tile background
        val backgroundDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_pattern)
        binding.tileBackground.setImageDrawable(TileDrawable(backgroundDrawable!!, Shader.TileMode.REPEAT))

        Timber.d("ScoresFragment created..")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // fetch score table content
        scoresViewModel.initiateTicker()
    }

    override fun onStop() {
        super.onStop()
        scoresViewModel.stopTicker()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scoresViewModel.scores.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Loading -> {
                    Timber.d("Initial List Shimmer activated")
                    binding.recyclerScoreList.adapter = shimmerAdapter
                }
                is Resource.Success -> {
                    // send data to scores adapter
                    it.data?.let { data ->
                        Timber.d("Scores are updated and displayed")
                        binding.recyclerScoreList.adapter = scoreListAdapter
                        scoreListAdapter.submitList(data)
                    }
                }
                is Resource.Info -> {
                    // show info message on snackBar
                    SnackBar.make(binding.root, it.message, SnackBar.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    // show error message on snackBar
                    SnackBar.makeLarge(binding.root, it.message, SnackBar.LENGTH_LONG).show()
                }
            }

        })
    }

}