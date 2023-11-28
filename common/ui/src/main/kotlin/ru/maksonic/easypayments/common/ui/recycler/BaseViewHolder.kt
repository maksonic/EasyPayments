package ru.maksonic.easypayments.common.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @Author maksonic on 27.11.2023
 */
abstract class BaseViewHolder<T, VB : ViewBinding> constructor(viewBinding: VB) :
    RecyclerView.ViewHolder(viewBinding.root) {

    private var item: T? = null

    var selectedPosition: Int = 0

    fun doBindings(data: T?) {
        this.item = data
    }

    abstract fun bind()

    fun getRowItem(): T? {
        return item
    }
}