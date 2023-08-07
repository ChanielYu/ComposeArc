package au.auxy.composearc.account.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.auxy.composearc.account.contract.AccountContract.AccountEligibilityIntent
import au.auxy.composearc.account.contract.AccountContract.AccountEligibilityIntent.Navigate
import au.auxy.composearc.account.contract.AccountContract.AccountEligibilityIntent.Refresh
import au.auxy.composearc.account.contract.AccountContract.AccountEligibilityViewState
import au.auxy.composearc.account.model.AccountArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class AccountEligibilityViewModel @Inject constructor(
    accountArg: AccountArg
) : ViewModel() {
    private val _viewState = MutableStateFlow(DEFAULT_VIEW_STATE)
    val viewState = _viewState.asStateFlow()

    private var refreshCounter = 0
    private val refreshState = MutableStateFlow(refreshCounter++)
    private val random = Random(accountArg.hashCode())
    private val navigationChannel = Channel<Int>(Channel.CONFLATED)

    init {
        combine(
            flowOf(accountArg).map { arg -> arg.toAccount() },
            refreshState
        ) { account, counter ->
            account.name to "Upstream counter = $counter"
        }.map { (title, upstream) ->
            AccountEligibilityViewState(title, upstream, DOWNSTREAM_EVENT_UNAVAILABLE)
        }.onEach { state ->
            _viewState.update { state }
        }.flatMapLatest { state ->
            /**
             * Only flatMapLatest can be used here.
             * flatMapMerge when each emission need to take a newly created flow
             * otherwise the previously upstream will still on subscribe of the same instance of the
             * given flow and they will suspend forever till concurrency exhausted and stop to work.
             */
            combine(
                flowOf(state),
                navigationChannel.receiveAsFlow()
            ) { st, rand ->
                st to rand
            }
        }.onEach { (state, rand) ->
            if (rand.mod(5) == 0) {
                Log.d(TAG, "Got exception")
                throw Exception()
            }
            _viewState.update { state.copy(downStream = "Random = $rand") }
        }.retry {
            Log.d(TAG, "Retry")
            true
        }.onCompletion {
            Log.d(TAG, "Flow is completed")
        }.catch { error ->
            Log.d(TAG, error.message ?: "Caught an error")
        }.launchIn(viewModelScope)
    }

    fun onIntent(intent: AccountEligibilityIntent) {
        when (intent) {
            Refresh -> refreshState.update { refreshCounter++ }
            Navigate -> viewModelScope.launch { navigationChannel.send(random.nextInt(1000)) }
        }
    }

    companion object {
        private val DEFAULT_VIEW_STATE = AccountEligibilityViewState("", "", "")
        private const val DOWNSTREAM_EVENT_UNAVAILABLE = "DOWNSTREAM EVENT UNAVAILABLE"
        private val TAG = AccountEligibilityViewModel::class.java.simpleName
    }
}
