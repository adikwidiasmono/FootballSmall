package com.small.main.ui.favorites.favoriteteam

import com.small.main.data.local.database.AppDatabase
import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException

class FavoriteTeamPresenter(private val eventRepository: EventRepository,
                            private val appDatabase: AppDatabase,
                            private val contextProvider: CoroutinesContextProvider) {

    private var favoriteTeamView: FavoriteTeamView? = null

    fun attachView(favoriteTeamView: FavoriteTeamView) {
        this.favoriteTeamView = favoriteTeamView
    }

    fun detachView() {
        favoriteTeamView = null
    }

    fun loadFavoriteMatch() {
        loadFavoriteMatch(true)
    }

    fun loadFavoriteMatch(showLoading: Boolean) {
        if (showLoading)
            favoriteTeamView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { appDatabase.teamDao().getAll() }
            try {
                favoriteTeamView?.showResultList(data)
                if (showLoading)
                    favoriteTeamView?.hideLoading()
            } catch (e: HttpException) {
                favoriteTeamView?.showError()
                if (showLoading)
                    favoriteTeamView?.hideLoading()
            } catch (e: Throwable) {
                favoriteTeamView?.showError()
                if (showLoading)
                    favoriteTeamView?.hideLoading()
            }
        }
    }
}