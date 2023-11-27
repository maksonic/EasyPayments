package ru.maksonic.easypayments.common.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @Author maksonic on 27.11.2023
 */
abstract class UiModel<State : UiState, Intent : UiIntent, Command : UiCommand, Effect : UiEffect>(
    initialState: State,
    initialCmd: Set<Command> = emptySet(),
    initialEff: Set<Effect> = emptySet(),
    private val subscriptions: List<CommandHandler<Intent, Command>> = emptyList(),
) : ViewModel(), IntentRunner<Intent> {

    private val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = mutableState
    private val effectsChannel: Channel<Effect> = Channel()
    val effects = effectsChannel.receiveAsFlow()

    init {
        handleCommands(initialCmd)
        handleEffects(initialEff)
    }

    protected abstract fun reduce(
        msg: Intent,
        model: State
    ): ReduceResult<State, Set<Command>, Set<Effect>>

    override fun run(intent: Intent) {
        val (newModel, commands, effects) = reduce(intent, mutableState.value)
        mutableState.update { newModel }
        handleCommands(commands)
        handleEffects(effects)
    }

    private fun handleCommands(programs: Set<Command>?) = programs?.forEach { command ->
        subscriptions.forEach { subscription ->
            viewModelScope.launch {
                subscription.handleCommand(command, this@UiModel::run)
            }
        }
    }

    private fun handleEffects(effects: Set<Effect>?) = effects?.forEach { effect ->
        viewModelScope.launch {
            effectsChannel.send(effect)
        }
    }
}