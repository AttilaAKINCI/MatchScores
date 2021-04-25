package com.akinci.matchscores.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.akinci.matchscores.databinding.ActivityRootBinding

abstract class RootActivity : AppCompatActivity()  {

    private lateinit var navigationController: NavController
    lateinit var binding : ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Initialization of ViewBinding not need for DataBinding here **/
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** navHostFragment is placed in <fragment></fragment> tags and
         * findViewById not works with this tags so findViewByTag is used in order to acquire
         * navHostFragment
        **/
        // setup navigation
        val navHostFragment = supportFragmentManager.findFragmentByTag("navHostFragment") as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(getNavigationGraph()) // gets navigation graph from each root activity
        navigationController = navHostFragment.navController
        navigationController.graph = navGraph

        NavigationUI.setupWithNavController(binding.bottomNav, navigationController)
    }

    abstract fun getNavigationGraph() : Int
    fun setBottomNavigationVisibility(isVisible : Int) { binding.bottomNav.visibility = isVisible }
}