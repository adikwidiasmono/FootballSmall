package com.small.main.ui.previousmatch

import com.small.main.data.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class PrevMatchPresenter(private val eventRepository: EventRepository,
                         private val contextProvider: CoroutinesContextProvider) {

    private var prevMatchView: PrevMatchView? = null

    fun attachView(prevMatchMatchView: PrevMatchView) {
        this.prevMatchView = prevMatchMatchView
    }

    fun detachView() {
        prevMatchView = null
    }

    fun loadLastMatch(leagueId: Int) {
        prevMatchView?.showLoading()
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadLastMatch(leagueId) }
            try {
                prevMatchView?.showResultList(data.await())
                prevMatchView?.hideLoading()
            } catch (e: HttpException) {
                prevMatchView?.showError()
                prevMatchView?.hideLoading()
            } catch (e: Throwable) {
                prevMatchView?.showError()
                prevMatchView?.hideLoading()
            }
        }
    }

}