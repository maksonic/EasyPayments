package ru.maksonic.easypayments

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.KeyboardController
import ru.maksonic.easypayments.databinding.ActivityMainBinding
import ru.maksonic.easypayments.R.id.navGraphContainer
import ru.maksonic.easypayments.navigation.graph.R.id.paymentsScreen
import ru.maksonic.easypayments.navigation.graph.R.id.onboardingScreen
import ru.maksonic.easypayments.navigation.graph.R.navigation.nav_graph

private const val COLOR_TRANSPARENT = Color.TRANSPARENT

class MainActivity : AppCompatActivity(), KeyboardController {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()
    private val navController: NavController by lazy { initNavController() }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = COLOR_TRANSPARENT, darkScrim = COLOR_TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(COLOR_TRANSPARENT, COLOR_TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setStartDestinationByTokenStatus(
            onValid = { setStartDestination(paymentsScreen) },
            onInvalid = { setStartDestination(onboardingScreen) }
        )
    }

    override fun hideIme() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initNavController(): NavController {
        val host = supportFragmentManager.findFragmentById(navGraphContainer) as NavHostFragment
        return host.navController
    }

    private fun setStartDestination(id: Int) {
        val inflater = navController.navInflater
        val graph = inflater.inflate(nav_graph).apply { setStartDestination(id) }
        navController.graph = graph
    }
}