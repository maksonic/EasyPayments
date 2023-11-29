package ru.maksonic.easypayments

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.R.id.navGraphContainer
import ru.maksonic.easypayments.databinding.ActivityMainBinding
import ru.maksonic.easypayments.navigation.graph.R.id.onboardingScreen
import ru.maksonic.easypayments.navigation.graph.R.id.paymentsScreen
import ru.maksonic.easypayments.navigation.graph.R.navigation.nav_graph

private const val COLOR_TRANSPARENT = Color.TRANSPARENT

class MainActivity : AppCompatActivity() {

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
        applySystemBarsInsets()

        viewModel.setStartDestinationByTokenStatus(
            onValid = { setStartDestination(paymentsScreen) },
            onInvalid = { setStartDestination(onboardingScreen) }
        )
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

    private fun applySystemBarsInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val isIme = windowInsets.isVisible(WindowInsetsCompat.Type.ime())
            val imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime())
            val defaultInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            if (isIme) {
                view.updatePadding(top = defaultInsets.top, bottom = imeInsets.bottom)
            } else {
                view.updatePadding(top = defaultInsets.top, bottom = defaultInsets.bottom)
            }
            WindowInsetsCompat.CONSUMED
        }
    }
}