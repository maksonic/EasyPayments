package ru.maksonic.easypayments.feature.onboarding.ui.adapter

import com.bumptech.glide.RequestManager
import ru.maksonic.easypayments.common.ui.recycler.BaseViewHolder
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingUi
import ru.maksonic.easypayments.feature.onboarding.ui.databinding.ItemOnboardingBinding

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingViewHolder(
    private val binding: ItemOnboardingBinding,
    private val imageLoader: RequestManager
) : BaseViewHolder<OnboardingUi, ItemOnboardingBinding>(binding) {

    override fun bind() {
        getRowItem()?.let {
            with(binding) {
                title.setText(it.titleResId)
                imageLoader.load(it.imageResId).into(image)
            }
        }
    }
}