package com.small.main.ui.detailmatch

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.local.database.AppDatabase
import com.small.main.data.local.entity.MatchEntity
import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import ru.gildor.coroutines.retrofit.await

class MatchDetailPresenter(private val eventRepository: EventRepository,
                           private val appDatabase: AppDatabase,
                           private val contextProvider: CoroutinesContextProvider) {

    private var matchDetailView: MatchDetailView? = null

    fun attachView(matchDetailView: MatchDetailView) {
        this.matchDetailView = matchDetailView
    }

    fun detachView() {
        matchDetailView = null
    }

    fun checkFavoriteState(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) {
                val matchEntityResult = appDatabase.matchDao().getByIdEvent(matchEntity.idEvent)
                matchEntityResult != null
            }
            try {
                matchDetailView?.onSuccessGetFavoriteState(data)
            } catch (e: Throwable) {
                matchDetailView?.onErrorGetFavoriteState(e)
            }
        }
    }

    fun addMatchToFavorite(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { appDatabase.matchDao().insert(matchEntity) }
            try {
                matchDetailView?.onSuccessAddFavorite(data)
            } catch (e: Throwable) {
                matchDetailView?.onErrorAddFavorite(e)
            }
        }
    }

    fun removeMatchToFavorite(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) {
                val matchEntityResult = appDatabase.matchDao().getByIdEvent(matchEntity.idEvent)
                appDatabase.matchDao().delete(matchEntityResult)
            }
            try {
                matchDetailView?.onSuccessRemoveFavorite(data)
            } catch (e: Throwable) {
                matchDetailView?.onErrorRemoveFavorite(e)
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