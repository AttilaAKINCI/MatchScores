package com.akinci.matchscores.features.splash

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.akinci.matchscores.R
import com.akinci.matchscores.common.activity.RootActivity
import com.akinci.matchscores.databinding.FragmentSplashBinding
import timber.log.Timber

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        /** Initialization of ViewBinding not need for DataBinding here **/
        binding = FragmentSplashBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner

        // hide bottom navigation view.
        (activity as RootActivity).setBottomNavigationVisibility(View.GONE)

        binding.animation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) { navigateToNewsList() }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
        })

        Timber.d("SplashFragment created..")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // when fragment starts, fire animation.
        binding.animation.playAnimation()
    }

    private fun navigateToNewsList(){
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_newsFragment)
        }, 100)
    }
}