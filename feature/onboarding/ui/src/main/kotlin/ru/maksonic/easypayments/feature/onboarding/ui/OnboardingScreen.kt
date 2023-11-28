package ru.maksonic.easypayments.feature.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.RequestManager
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.maksonic.easypayments.common.ui.BaseScreen
import ru.maksonic.easypayments.feature.onboarding.ui.adapter.OnboardingAdapter
import ru.maksonic.easypayments.feature.onboarding.ui.core.Eff
import ru.maksonic.easypayments.feature.onboarding.ui.core.Model
import ru.maksonic.easypayments.feature.onboarding.ui.core.Msg
import ru.maksonic.easypayments.feature.onboarding.ui.core.OnboardingSandbox
import ru.maksonic.easypayments.feature.onboarding.ui.databinding.ScreenOnboardingBinding
import ru.maksonic.easypayments.navigation.router.Router

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingScreen : BaseScreen<ScreenOnboardingBinding, Model, Eff>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenOnboardingBinding
        get() = ScreenOnboardingBinding::inflate

    private val sandbox: OnboardingSandbox by viewModel()
    private val imageLoader: RequestManager by inject()
    private val router: Router by inject()
    private val onboardingAdapter: OnboardingAdapter by lazy(::initOnboardingAdapter)

    override fun render(savedInstanceState: Bundle?) {
        initViewPager()
        listenCurrentPageChange()

        binding.btnNext.setOnClickListener {
            sandbox.send(Msg.Ui.OnBtnNextClicked)
        }

        sandbox.model.render()
        sandbox.effects.handle()
    }

    override fun renderModel(model: Model) {
        onboardingAdapter.submitList(model.onboardings)
    }

    override fun handleEffects(eff: Eff) = when (eff) {
        is Eff.ScrollToNextPage -> {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
        }
        is Eff.NavigateToAuth -> {
            router.navigateFromOnboardingToAuth(this@OnboardingScreen)
        }
    }

    private fun initOnboardingAdapter() = OnboardingAdapter(imageLoader)

    private fun initViewPager() = with(binding) {
        viewPager.apply {
            adapter = onboardingAdapter
            overScrollMode = ViewPager2.OVER_SCROLL_NEVER
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }

    private fun listenCurrentPageChange() = binding.viewPager.registerOnPageChangeCallback(
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                sandbox.send(Msg.Inner.UpdatedCurrentPage(position))
                super.onPageSelected(position)
            }
        }
    )
}