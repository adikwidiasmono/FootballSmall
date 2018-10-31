package com.small.main.ui.previousmatch

import com.small.main.data.remote.repository.EventRepository
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.Call
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class PrevMatchPresenter(private val eventRepository: EventRepository,
                         private val contextProvider: CoroutinesContextProvider) {

    private var prevMatchView: PrevMatchView? = null

    fun attachView(prevMatchView: PrevMatchView) {
        this.prevMatchView = prevMatchView
    }

    fun detachView() {
        prevMatchView = null
    }

    fun loadLastMatch(leagueId: Int) {
        loadLastMatch(leagueId, true)
    }

    fun loadLastMatch(leagueId: Int, showLoading: Boolean) {
        if (showLoading)
            prevMatchView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadLastMatch(leagueId) }
            try {
                prevMatchView?.showResultList(data.await())
                if (showLoading)
                    prevMatchView?.hideLoading()
            } catch (e: HttpException) {
                prevMatchView?.showError()
                if (showLoading)
                    prevMatchView?.hideLoading()
            } catch (e: Throwable) {
                prevMatchView?.showError()
                if (showLoading)
                    prevMatchView?.hideLoading()
            }
        }
    }

}