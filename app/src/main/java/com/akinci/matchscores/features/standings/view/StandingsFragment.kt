package com.akinci.matchscores.features.standings.view

import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.akinci.matchscores.R
import com.akinci.matchscores.common.component.TileDrawable
import com.akinci.matchscores.databinding.FragmentStandingsBinding
import timber.log.Timber

class StandingsFragment : Fragment() {

    lateinit var binding : FragmentStandingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        /** Initialization of ViewBinding not need for DataBinding here **/
        binding = FragmentStandingsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        // set tile background
        val backgroundDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_pattern)
        binding.tileBackground.setImageDrawable(TileDrawable(backgroundDrawable!!, Shader.TileMode.REPEAT))

        Timber.d("StandingsFragment created..")
        return binding.root
    }
}