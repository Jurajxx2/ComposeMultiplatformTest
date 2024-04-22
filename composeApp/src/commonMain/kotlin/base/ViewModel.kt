package base

import ViewModel
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class ViewModelState<STATE : State, INTENT : Intent, EVENT : NavigationEvent>(
    initialState: STATE
) : ViewModel() {

    private val _navigationEvent: Channel<EVENT> = Channel()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> get() = _state

    abstract fun intent(intent: INTENT)

    protected fun setState(block: STATE.() -> STATE) = viewModelScope.launch {
        val currentState = _state.value
        val newState = block.invoke(currentState)
        _state.tryEmit(newState)
    }

    protected fun navigate(builder: () -> EVENT) {
        val effectValue = builder()
        viewModelScope.launch { _navigationEvent.send(effectValue) }
    }
}