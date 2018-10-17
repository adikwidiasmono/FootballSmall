package com.small.main.ui.previousmatch.detail

import com.small.main.data.local.database.AppDatabase
import com.small.main.data.local.entity.MatchEntity
import com.small.main.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import ru.gildor.coroutines.retrofit.await

class PrevMatchDetailPresenter(private val appDatabase: AppDatabase,
                               private val contextProvider: CoroutinesContextProvider) {

    private var prevMatchDetailView: PrevMatchDetailView? = null

    fun attachView(prevMatchDetailView: PrevMatchDetailView) {
        this.prevMatchDetailView = prevMatchDetailView
    }

    fun detachView() {
        prevMatchDetailView = null
    }

    fun addMatchToFavorite(matchEntity: MatchEntity) {
        launch(contextProvider.main) {
            val data = withContext(contextProvider.io) { appDatabase.matchDao().insert(matchEntity) }
            try {
                prevMatchDetailView?.showSuccessInsert(data)
            } catch (e: Throwable) {
                prevMatchDetailView?.showErrorInsert()
            }
        }
    }
}