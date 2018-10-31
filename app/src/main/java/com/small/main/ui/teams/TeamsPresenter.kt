package com.small.main.ui.teams

import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class TeamsPresenter(private val eventRepository: EventRepository,
                     private val contextProvider: CoroutinesContextProvider) {

    private var matchView: TeamsView? = null

    fun attachView(matchView: TeamsView) {
        this.matchView = matchView
    }

    fun detachView() {
        matchView = null
    }

    fun loadTeams(idLeague: Int) {
        loadTeams(idLeague, true)
    }

    fun loadTeams(idLeague: Int, showLoading: Boolean) {
        if (showLoading)
            matchView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadTeamsByLeagueId(idLeague) }
            try {
                matchView?.showTeamList(data.await())
                if (showLoading)
                    matchView?.hideLoading()
            } catch (e: HttpException) {
                e.printStackTrace()
                matchView?.onErrorTeamList()
                if (showLoading)
                    matchView?.hideLoading()
            } catch (e: Throwable) {
                e.printStackTrace()
                matchView?.onErrorTeamList()
                if (showLoading)
                    matchView?.hideLoading()
            }
        }
    }

    fun loadLeagues() {
        matchView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadAllSoccerLeague() }
            try {
                matchView?.showLeagueList(data.await())
                matchView?.hideLoading()
            } catch (e: HttpException) {
                matchView?.onErrorLeagueList()
                matchView?.hideLoading()
            } catch (e: Throwable) {
                matchView?.onErrorLeagueList()
                matchView?.hideLoading()
            }
        }
    }

}