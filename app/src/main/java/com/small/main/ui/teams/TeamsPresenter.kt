package com.small.main.ui.teams

import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class TeamsPresenter(private val eventRepository: EventRepository,
                     private val contextProvider: CoroutinesContextProvider) {

    private var teamsView: TeamsView? = null

    fun attachView(matchView: TeamsView) {
        this.teamsView = matchView
    }

    fun detachView() {
        teamsView = null
    }

    fun loadTeams(idLeague: Int) {
        loadTeams(idLeague, true)
    }

    fun loadTeams(idLeague: Int, showLoading: Boolean) {
        if (showLoading)
            teamsView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadTeamsByLeagueId(idLeague) }
            try {
                teamsView?.showTeamList(data.await())
                if (showLoading)
                    teamsView?.hideLoading()
            } catch (e: HttpException) {
                e.printStackTrace()
                teamsView?.onErrorTeamList()
                if (showLoading)
                    teamsView?.hideLoading()
            } catch (e: Throwable) {
                e.printStackTrace()
                teamsView?.onErrorTeamList()
                if (showLoading)
                    teamsView?.hideLoading()
            }
        }
    }

    fun loadLeagues() {
        teamsView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadAllSoccerLeague() }
            try {
                teamsView?.showLeagueList(data.await())
                teamsView?.hideLoading()
            } catch (e: HttpException) {
                teamsView?.onErrorLeagueList()
                teamsView?.hideLoading()
            } catch (e: Throwable) {
                teamsView?.onErrorLeagueList()
                teamsView?.hideLoading()
            }
        }
    }

    fun searchTeam(queryTeamName: String) {
        teamsView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadTeamsByTeamName(queryTeamName) }
            try {
                teamsView?.showSearchTeamList(data.await())
                teamsView?.hideLoading()
            } catch (e: HttpException) {
                teamsView?.onErrorSearchTeamList()
                teamsView?.hideLoading()
            } catch (e: Throwable) {
                teamsView?.onErrorSearchTeamList()
                teamsView?.hideLoading()
            }
        }
    }

}