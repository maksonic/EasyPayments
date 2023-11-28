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
import ru.maksonic.easypayments.feature.onboarding.ui.core.OnboardingSandbox
import ru.maksonic.easypayments.feature.onboarding.ui.databinding.ScreenOnboardingBinding

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingScreen : BaseScreen<ScreenOnboardingBinding>() {
    override val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> ScreenOnboardingBinding
        get() = ScreenOnboardingBinding::inflate

    private val sandbox: OnboardingSandbox by viewModel()
    private val imageLoader: RequestManager by inject()
    private val onboardingAdapter: OnboardingAdapter by lazy(::initOnboardingAdapter)

    override fun render(savedInstanceState: Bundle?) {
        initViewPager()
        binding.btnNext.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
        }

        sandbox.model.render { model ->
            onboardingAdapter.submitList(model.onboardings)
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
}