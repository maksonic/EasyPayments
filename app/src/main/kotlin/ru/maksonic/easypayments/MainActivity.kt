package ru.maksonic.easypayments

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.maksonic.easypayments.databinding.ActivityMainBinding

private const val COLOR_TRANSPARENT = Color.TRANSPARENT

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = COLOR_TRANSPARENT, darkScrim = COLOR_TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(COLOR_TRANSPARENT, COLOR_TRANSPARENT)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}