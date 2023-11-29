package ru.maksonic.easypayments.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 27.11.2023
 */
abstract class BaseScreen<VB : ViewBinding, T : Any, E : Any> : Fragment() {
    private var _binding: VB? = null
    abstract val initBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB

    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = initBinding.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render(savedInstanceState)
    }

    abstract fun render(savedInstanceState: Bundle?)

    protected fun StateFlow<T>.render() {
        lifecycleScope.launch {
            this@render.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { model ->
                renderModel(model)
            }
        }
    }

    protected fun Flow<E>.handle() {
        lifecycleScope.launch {
            this@handle.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { eff ->
                handleEffects(eff)
            }
        }
    }

    protected open fun renderModel(model: T) {}
    protected open fun handleEffects(eff: E) {}

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}