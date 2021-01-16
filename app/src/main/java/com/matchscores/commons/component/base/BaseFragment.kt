package com.matchscores.commons.component.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matchscores.RootActivity

open class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(activity is RootActivity){
            (activity as RootActivity).fragment = this
            (activity as RootActivity).resetRightBarIcons()
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun rightBarIconClicked() { /** used for overrides. **/ }
}