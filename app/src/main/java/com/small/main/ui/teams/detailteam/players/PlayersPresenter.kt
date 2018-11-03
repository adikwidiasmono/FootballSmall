package com.small.main.ui.teams.detailteam.players

import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class PlayersPresenter(private val eventRepository: EventRepository,
                       private val contextProvider: CoroutinesContextProvider) {

    private var playersView: PlayersView? = null

    fun attachView(playersView: PlayersView) {
        this.playersView = playersView
    }

    fun detachView() {
        playersView = null
    }

    fun loadPlayers(idTeam: Int) {
        playersView?.showLoading()

        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.loadPlayersByTeamId(idTeam) }
            try {
                playersView?.showPlayerList(data.await())
                playersView?.hideLoading()
            } catch (e: HttpException) {
                e.printStackTrace()
                playersView?.onErrorPlayerList()
                playersView?.hideLoading()
            } catch (e: Throwable) {
                e.printStackTrace()
                playersView?.onErrorPlayerList()
                playersView?.hideLoading()
            }
        }
    }

}