package com.small.main.ui.matches

import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class MatchesPresenter(private val eventRepository: EventRepository,
                       private val contextProvider: CoroutinesContextProvider) {

    private var matchesView: MatchesView? = null

    fun attachView(matchView: MatchesView) {
        this.matchesView = matchView
    }

    fun detachView() {
        matchesView = null
    }

    fun searchMatch(queryTeamName: String) {
        matchesView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadMatchesByTeamName(queryTeamName) }
            try {
                matchesView?.showSearchMatchList(data.await())
                matchesView?.hideLoading()
            } catch (e: HttpException) {
                matchesView?.onErrorSearchMatchList()
                matchesView?.hideLoading()
            } catch (e: Throwable) {
                matchesView?.onErrorSearchMatchList()
                matchesView?.hideLoading()
            }
        }
    }

}