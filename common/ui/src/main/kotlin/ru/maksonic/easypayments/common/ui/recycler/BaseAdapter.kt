package ru.maksonic.easypayments.common.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

/**
 * @Author maksonic on 27.11.2023
 */
abstract class BaseAdapter<T : Any, VB : ViewBinding, VH : BaseViewHolder<T, VB>>(
    callback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings((getItem(position)))
        holder.bind()
    }

    override fun submitList(items: List<T>?) {
        super.submitList(items ?: emptyList())
    }
}