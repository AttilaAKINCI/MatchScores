package com.matchscores.features.standings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.matchscores.R
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.databinding.FragmentStandingsBinding

class StandingsFragment : BaseFragment() {

    private lateinit var binding : FragmentStandingsBinding

    override fun hasDropDownMenu() = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_standings, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}