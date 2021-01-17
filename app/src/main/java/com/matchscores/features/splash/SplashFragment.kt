package com.matchscores.features.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.matchscores.R
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.databinding.FragmentSplashBinding
import timber.log.Timber

class SplashFragment : BaseFragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        navigateToNewsList()

        Timber.d("SplashFragment created..")
        return binding.root
    }

    private fun navigateToNewsList(){
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_splashScreenFragment_to_newsFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashScreenFragment,
                            true).build()
                )
        }, 1000)
    }
}