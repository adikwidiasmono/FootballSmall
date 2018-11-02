package com.small.main.ui.teams.detailteam

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.local.database.AppDatabase
import com.small.main.data.local.entity.TeamEntity
import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import ru.gildor.coroutines.retrofit.await

class TeamDetailPresenter(private val eventRepository: EventRepository,
                          private val appDatabase: AppDatabase,
                          private val contextProvider: CoroutinesContextProvider) {

    private var teamDetailView: TeamDetailView? = null

    fun attachView(teamDetailView: TeamDetailView) {
        this.teamDetailView = teamDetailView
    }

    fun detachView() {
        teamDetailView = null
    }

    fun checkFavoriteState(teamEntity: TeamEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) {
                val teamEntityResult = appDatabase.teamDao().getByIdTeam(teamEntity.idTeam)
                teamEntityResult != null
            }
            try {
                teamDetailView?.onSuccessGetFavoriteState(data)
            } catch (e: Throwable) {
                teamDetailView?.onErrorGetFavoriteState(e)
            }
        }
    }

    fun addTeamToFavorite(teamEntity: TeamEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { appDatabase.teamDao().insert(teamEntity) }
            try {
                teamDetailView?.onSuccessAddFavorite(data)
            } catch (e: Throwable) {
                teamDetailView?.onErrorAddFavorite(e)
            }
        }
    }

    fun removeTeamFromFavorite(teamEntity: TeamEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) {
                val teamEntityResult = appDatabase.teamDao().getByIdTeam(teamEntity.idTeam)
                appDatabase.teamDao().delete(teamEntityResult)
            }
            try {
                teamDetailView?.onSuccessRemoveFavorite(data)
            } catch (e: Throwable) {
                teamDetailView?.onErrorRemoveFavorite(e)
            }
        }
    }

    fun setTeamLogo(activity: Activity, teamId: Int, imageView: ImageView) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.lookupTeam(teamId) }
            try {
                Glide.with(activity)
                        .load(data.await()?.teams?.get(0)?.strTeamBadge)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.ic_image_def_128dp)
                                .error(R.drawable.ic_image_err_128dp)
                        )
                        .into(imageView)
            } catch (e: Throwable) {
                Glide.with(activity)
                        .load(R.drawable.ic_image_err_128dp)
                        .into(imageView)
                Log.e("FAILED IMG", "Home clud logo : " + e.localizedMessage)
            }
        }
    }

}