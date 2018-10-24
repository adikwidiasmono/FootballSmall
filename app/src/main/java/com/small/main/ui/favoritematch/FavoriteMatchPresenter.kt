package com.small.main.ui.favoritematch

import com.small.main.data.local.database.AppDatabase
import com.small.main.data.remote.repository.EventRepository
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.MatchResponse
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class FavoriteMatchPresenter(private val eventRepository: EventRepository,
                             private val appDatabase: AppDatabase,
                             private val contextProvider: CoroutinesContextProvider) {

    private var favoriteMatchView: FavoriteMatchView? = null

    fun attachView(favoriteMatchView: FavoriteMatchView) {
        this.favoriteMatchView = favoriteMatchView
    }

    fun detachView() {
        favoriteMatchView = null
    }

    fun loadFavoriteMatch() {
        loadFavoriteMatch(true)
    }

    fun loadFavoriteMatch(showLoading: Boolean) {
        if (showLoading)
            favoriteMatchView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { appDatabase.matchDao().getAll() }
            try {
                favoriteMatchView?.showResultList(data)
                if (showLoading)
                    favoriteMatchView?.hideLoading()
            } catch (e: HttpException) {
                favoriteMatchView?.showError()
                if (showLoading)
                    favoriteMatchView?.hideLoading()
            } catch (e: Throwable) {
                favoriteMatchView?.showError()
                if (showLoading)
                    favoriteMatchView?.hideLoading()
            }
        }
    }
}