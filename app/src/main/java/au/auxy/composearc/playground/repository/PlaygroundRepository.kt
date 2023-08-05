package au.auxy.composearc.playground.repository

import au.auxy.composearc.playground.repository.ItemType.HOME_ITEM
import kotlinx.coroutines.delay
import javax.inject.Inject

class PlaygroundRepository @Inject constructor(){
    suspend fun getHomeItems() = delay(1000).run {
        List(20) { index -> "Home item $index" }.associateWith { HOME_ITEM }
    }
}
