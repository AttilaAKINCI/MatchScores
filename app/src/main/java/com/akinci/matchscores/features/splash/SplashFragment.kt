package com.akinci.matchscores.features.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.akinci.matchscores.R
import com.akinci.matchscores.databinding.FragmentSplashBinding
import timber.log.Timber

class SplashFragment : Fragment() {

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
                .navigate(R.id.action_splashFragment_to_newsFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment,
                            true).build()
                )
        }, 1000)
    }
}