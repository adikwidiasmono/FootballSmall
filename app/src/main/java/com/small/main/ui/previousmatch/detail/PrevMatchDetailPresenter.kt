package com.small.main.ui.previousmatch.detail

import android.arch.lifecycle.LiveData
import android.widget.ImageView
import com.small.main.data.local.database.AppDatabase
import com.small.main.data.local.entity.MatchEntity
import com.small.main.data.remote.repository.EventRepository
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

class PrevMatchDetailPresenter(private val eventRepository: EventRepository,
                               private val appDatabase: AppDatabase,
                               private val contextProvider: CoroutinesContextProvider) {

    private var prevMatchDetailView: PrevMatchDetailView? = null

    fun attachView(prevMatchDetailView: PrevMatchDetailView) {
        this.prevMatchDetailView = prevMatchDetailView
    }

    fun detachView() {
        prevMatchDetailView = null
    }

    fun checkFavoriteState(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) {
                lateinit var liveData: LiveData<MatchEntity>
                if (matchEntity.id != null) {
                    liveData = appDatabase.matchDao().get(matchEntity.id)
                } else {
                    liveData = appDatabase.matchDao().getByIdEvent(matchEntity.idEvent ?: 0)
                }
                liveData.value != null
            }
            try {
                prevMatchDetailView?.onSuccessGetFavoriteState(data)
            } catch (e: Throwable) {
                prevMatchDetailView?.onErrorGetFavoriteState(e)
            }
        }
    }

    fun addMatchToFavorite(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { appDatabase.matchDao().insert(matchEntity) }
            try {
                prevMatchDetailView?.onSuccessAddFavorite(data)
            } catch (e: Throwable) {
                prevMatchDetailView?.onErrorAddFavorite(e)
            }
        }
    }

    fun removeMatchToFavorite(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) {
                if (matchEntity.id == null) {
                    val entity = appDatabase.matchDao().getByIdEvent(matchEntity.idEvent).value
                    if (entity != null)
                        appDatabase.matchDao().delete(entity)
                    else
                        0
                } else {
                    appDatabase.matchDao().delete(matchEntity)
                }
            }
            try {
                prevMatchDetailView?.onSuccessRemoveFavorite(data)
            } catch (e: Throwable) {
                prevMatchDetailView?.onErrorRemoveFavorite(e)
            }
        }
    }

    private fun setHomeLogo(teamId: Int, imageView: ImageView) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { eventRepository.lookupTeam(teamId) }
            try {
                prevMatchView?.showResultList(data.await())

                Glide.with(this)
                        .load(result?.teams?.get(0)?.strTeamBadge)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.ic_image_def_128dp)
                                .error(R.drawable.ic_image_err_128dp)
                        )
                        .into(iv_home_logo)
            } catch (e: Throwable) {
                prevMatchView?.showError()
                prevMatchView?.hideLoading()
            }
        }


        disposableHome =
                footballApiService.lookupTeam(idTeam)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    run {
                                        Glide.with(this)
                                                .load(result?.teams?.get(0)?.strTeamBadge)
                                                .apply(RequestOptions()
                                                        .placeholder(R.drawable.ic_image_def_128dp)
                                                        .error(R.drawable.ic_image_err_128dp)
                                                )
                                                .into(iv_home_logo)
                                    }
                                },
                                { error ->
                                    run {
                                        Glide.with(this)
                                                .load(R.drawable.ic_image_err_128dp)
                                                .into(iv_home_logo)
                                        Log.e("FAILED IMG", "Home clud logo : " + error.localizedMessage)
                                    }
                                }
                        )
    }

}