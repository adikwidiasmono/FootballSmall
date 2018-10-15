package com.small.main.ui.nextmatch

import com.small.main.data.repository.EventRepository
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
        nextMatchView?.showLoading()
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadNextMatch(leagueId) }
            try {
                nextMatchView?.showResultList(data.await())
                nextMatchView?.hideLoading()
            } catch (e: HttpException) {
                nextMatchView?.showError()
                nextMatchView?.hideLoading()
            } catch (e: Throwable) {
                nextMatchView?.showError()
                nextMatchView?.hideLoading()
            }
        }
    }

}