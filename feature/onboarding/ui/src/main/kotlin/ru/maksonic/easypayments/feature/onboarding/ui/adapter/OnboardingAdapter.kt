package ru.maksonic.easypayments.feature.onboarding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import ru.maksonic.easypayments.common.ui.recycler.BaseAdapter
import ru.maksonic.easypayments.feature.onboarding.domain.OnboardingUi
import ru.maksonic.easypayments.feature.onboarding.ui.databinding.ItemOnboardingBinding

/**
 * @Author maksonic on 27.11.2023
 */
class OnboardingAdapter(
    private val imageLoader: RequestManager
) : BaseAdapter<OnboardingUi, ItemOnboardingBinding, OnboardingViewHolder>(
    OnboardingItemDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder =
        OnboardingViewHolder(
            binding = ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            imageLoader = imageLoader
        )
}

private class OnboardingItemDiffUtil : DiffUtil.ItemCallback<OnboardingUi>() {
    override fun areItemsTheSame(oldItem: OnboardingUi, newItem: OnboardingUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OnboardingUi, newItem: OnboardingUi): Boolean {
        return oldItem == newItem
    }
}
