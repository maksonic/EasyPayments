package ru.maksonic.easypayments.common.core.elm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 24.06.2023
 */
abstract class Sandbox<T : ElmModel, M : ElmMessage, C : ElmCommand, E : ElmEffect>(
    initialModel: T,
    initialCmd: Set<C> = emptySet(),
    initialEff: Set<E> = emptySet(),
    private val subscriptions: List<ElmProgram<M, C>> = emptyList(),
) : ViewModel(), MessageSender<M> {

    private val mutableModel = MutableStateFlow(initialModel)
    val model: StateFlow<T>
        get() = mutableModel
    private val effectsChannel: Channel<E> = Channel()
    val effects = effectsChannel.receiveAsFlow()

    init {
        executePrograms(initialCmd)
        executeEffects(initialEff)
    }

    protected abstract fun update(msg: M, model: T): ElmUpdate<T, Set<C>, Set<E>>

    override fun send(msg: M) {
        val (newModel, commands, effects) = update(msg, mutableModel.value)
        mutableModel.update { newModel }
        executePrograms(commands)
        executeEffects(effects)
    }

    private fun executePrograms(programs: Set<C>?) = programs?.forEach { command ->
        subscriptions.forEach { subscription ->
            viewModelScope.launch {
                subscription.executeProgram(command, ::send)
            }
        }
    }

    private fun executeEffects(effects: Set<E>?) = effects?.forEach { effect ->
        viewModelScope.launch {
            effectsChannel.send(effect)
        }
    }
}
