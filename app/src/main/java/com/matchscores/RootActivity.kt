package com.matchscores

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.matchscores.commons.component.base.BaseFragment
import com.matchscores.commons.component.widget.DropDownClickListener
import com.matchscores.commons.component.widget.DropDownMenu
import com.matchscores.databinding.ActivityRootBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var navigationController: NavController
    private lateinit var binding : ActivityRootBinding

    lateinit var fragment : BaseFragment
    private var selectedMenuItem = DropDownMenu.DropDownItems.NEWS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)

        // setup navigation
        navigationController = findNavController(R.id.nav_host_fragment)
        // tell navigation controller that which fragments will be at the top of backstack
        // (hides backbutton for fragments which are placed at top)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.splashScreenFragment, R.id.newsFragment, R.id.scoresFragment, R.id.standingsFragment)
        )

        // remove extra padding between arrow and toolbar title
        binding.toolbar.contentInsetStartWithNavigation = 10
        binding.toolbar.addDropDownMenuIcon()
        binding.toolbar.clickListener = View.OnClickListener {
            if(binding.toolbar.dropDownActive){
                binding.dropDownMenu.visibility = View.VISIBLE
            } else {
                binding.dropDownMenu.visibility = View.GONE
            }
        }

        binding.dropDownMenu.dropDownClickListener = (object : DropDownClickListener {
            override fun dropDownClicked(item: DropDownMenu.DropDownItems) {
                    closeDropDown()
                    if(selectedMenuItem != item){
                        when(item){
                            DropDownMenu.DropDownItems.NEWS -> {
                                navigationController.navigate(R.id.newsFragment)
                            }
                            DropDownMenu.DropDownItems.SCORES -> {
                                navigationController.navigate(R.id.scoresFragment)
                            }
                            DropDownMenu.DropDownItems.STANDINGS ->
                                navigationController.navigate(R.id.standingsFragment)
                        }
                    }
                    selectedMenuItem = item
                }
            })

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navigationController, appBarConfiguration)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun showDropDownMenu(isShown : Int){ binding.toolbar.showDropDownMenuIcon(isShown) }
    private fun closeDropDown(){
        binding.dropDownMenu.visibility = View.GONE
        binding.toolbar.closeDropDownIcon()
    }
}