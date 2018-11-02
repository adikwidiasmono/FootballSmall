package com.small.main.ui.matches.nextmatch

import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class NextMatchPresenter(private val eventRepository: EventRepository,
                         private val contextProvider: CoroutinesContextProvider) {

    private var nextMatchView: NextMatchView? = null

    fun attachView(nextMatchView: NextMatchView) {
        this.nextMatchView = nextMatchView
    }

    fun detachView() {
        nextMatchView = null
    }

    fun loadNextMatch(leagueId: Int) {
        loadNextMatch(leagueId, false)
    }

    fun loadNextMatch(leagueId: Int, showLoading: Boolean) {
        if (showLoading)
            nextMatchView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadNextMatch(leagueId) }
            try {
                nextMatchView?.showResultList(data.await())
                if (showLoading)
                    nextMatchView?.hideLoading()
            } catch (e: HttpException) {
                nextMatchView?.showError()
                if (showLoading)
                    nextMatchView?.hideLoading()
            } catch (e: Throwable) {
                nextMatchView?.showError()
                if (showLoading)
                    nextMatchView?.hideLoading()
            }
        }
    }

}