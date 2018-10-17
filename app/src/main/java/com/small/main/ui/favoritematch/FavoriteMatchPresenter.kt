package com.small.main.ui.favoritematch

import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class FavoriteMatchPresenter(private val eventRepository: EventRepository,
                             private val contextProvider: CoroutinesContextProvider) {

    private var favoriteMatchView: FavoriteMatchView? = null

    fun attachView(favoriteMatchView: FavoriteMatchView) {
        this.favoriteMatchView = favoriteMatchView
    }

    fun detachView() {
        favoriteMatchView = null
    }

    fun loadTodayMatch(leagueId: Int, date: String) {
        favoriteMatchView?.showLoading()
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadTodayMatch(leagueId, date) }
            try {
                favoriteMatchView?.showResultList(data.await())
                favoriteMatchView?.hideLoading()
            } catch (e: HttpException) {
                favoriteMatchView?.showError()
                favoriteMatchView?.hideLoading()
            } catch (e: Throwable) {
                favoriteMatchView?.showError()
                favoriteMatchView?.hideLoading()
            }
        }
    }
}