package au.auxy.composearc.playground.contract

import au.auxy.composearc.playground.contract.PlaygroundHomeContract.HomeColumnViewState.Shimmer
import java.util.concurrent.atomic.AtomicBoolean

interface PlaygroundHomeContract {
    sealed class HomeViewAction {
        object ActionNone : HomeViewAction()
        data class ActionNavDetail(
            val isConsumed: AtomicBoolean = AtomicBoolean()
        ) : HomeViewAction()

        object ActionNavBack : HomeViewAction()
    }

    sealed class HomeViewEvent {
        data class NavDetail(val title: String) : HomeViewEvent()
        object NavBack : HomeViewEvent()
    }

    data class HomeItem(val body: String, val event: HomeViewEvent)

    sealed class HomeColumnViewState {
        object Shimmer : HomeColumnViewState()
        data class HomeColumn(val homeItems: List<HomeItem>) : HomeColumnViewState()
    }

    data class HomeViewState(val columnState: HomeColumnViewState)

    companion object {
        val INIT_VIEW_STATE = HomeViewState(Shimmer)
    }
}
