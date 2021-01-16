package com.matchscores

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.databinding.ActivityRootBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private lateinit var binding : ActivityRootBinding

    lateinit var fragment : BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)

        // setup navigation
        navigationController = findNavController(R.id.nav_host_fragment)
        // tell navigation controller that which fragments will be at the top of backstack
        // (hides backbutton for fragments which are placed at top)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.splashScreenFragment, R.id.newsFragment, R.id.scoresFragment)
        )

        // remove extra padding between arrow and toolbar title
        binding.toolbar.contentInsetStartWithNavigation = 10
        binding.toolbar.clickListener = View.OnClickListener {
            fragment?.rightBarIconClicked()
        }


        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navigationController, appBarConfiguration)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun resetRightBarIcons(){ binding.toolbar.resetBarItems() }
    fun addRightButton(drawableId : Int){
        val startColor = ContextCompat.getColor(this, R.color.mainBg)
        val icon = ResourcesCompat.getDrawable(resources, drawableId, theme)
        icon?.let {
            it.setTint(startColor)
            binding.toolbar.setRightBarIcon(it)
        }
    }

}