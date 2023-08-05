package au.auxy.composearc.playground.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.Companion.INIT_VIEW_STATE
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeColumnViewState.HomeColumn
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeItem
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewAction
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewAction.ActionNavBack
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewAction.ActionNavDetail
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewAction.ActionNone
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewEvent
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewEvent.NavBack
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewEvent.NavDetail
import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeViewState
import au.auxy.composearc.playground.navigation.PlaygroundParaKey
import au.auxy.composearc.playground.repository.ItemType.EXIT_ITEM
import au.auxy.composearc.playground.repository.ItemType.HOME_ITEM
import au.auxy.composearc.playground.repository.PlaygroundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaygroundHomeViewModel @Inject constructor(
    private val repository: PlaygroundRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _viewState = MutableStateFlow(INIT_VIEW_STATE)
    val viewState: StateFlow<HomeViewState> = _viewState

    private val viewActionChannel = Channel<HomeViewAction>(Channel.CONFLATED).apply {
        trySend(ActionNone)
    }

    private fun playgroundHomeFlow() = combine(
        flow { emit(repository.getHomeItems()) },
        viewActionChannel.receiveAsFlow()
    ) { homeItemMap, viewAction ->
        if (viewAction != ActionNone) throw Exception("Whatever exception")
        HomeViewState(HomeColumn(homeItemMap.map { entry ->
            HomeItem(
                entry.key, when (entry.value) {
                    HOME_ITEM -> NavDetail(entry.key)
                    EXIT_ITEM -> NavBack
                }
            )
        }))
    }.onEach { state ->
        _viewState.update { state }
    }.catch {
        //Log.e(TAG, "Swallowed inner exception")
        throw Exception("Inner exception")
    }

    init {
        flow {
            emitAll(playgroundHomeFlow())
        }.onCompletion {
            Log.e(TAG, "Outer onCompletion")
        }.catch { error ->
            Log.e(TAG, "Outer catch: ${error.message}")
        }.launchIn(viewModelScope)
        flow {
            emitAll(
                flow {
                    emit(1)
                    delay(500)
                    emit(2)
                    delay(500)
                    emit(3)
                }
            )
            emitAll(
                flow {
                    emit(4)
                    delay(500)
                    emit(5)
                    delay(500)
                    emit(6)
                }
            )
        }.onEach { num ->
            Log.d(TAG, num.toString())
        }.launchIn(viewModelScope)
        val para: String? = savedStateHandle[PlaygroundParaKey]
        para?.length
    }

    fun onEvent(event: HomeViewEvent): Any = when (event) {
        is NavDetail -> updateViewAction(ActionNavDetail())
        NavBack -> updateViewAction(ActionNavBack)
    }

    private fun updateViewAction(action: HomeViewAction) = viewModelScope.launch {
        viewActionChannel.send(action)
    }

    companion object {
        private val TAG = PlaygroundHomeViewModel::class.simpleName
    }
}
