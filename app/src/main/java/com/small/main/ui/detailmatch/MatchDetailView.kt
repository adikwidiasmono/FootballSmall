package com.small.main.ui.detailmatch

interface MatchDetailView {

    fun onSuccessAddFavorite(id: Long)
    fun onErrorAddFavorite(e: Throwable)

    fun onSuccessRemoveFavorite(deleted: Int)
    fun onErrorRemoveFavorite(e: Throwable)

    fun onSuccessGetFavoriteState(isFavorite: Boolean)
    fun onErrorGetFavoriteState(e: Throwable)

}