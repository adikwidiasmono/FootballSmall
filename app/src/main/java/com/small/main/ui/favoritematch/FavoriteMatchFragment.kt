package com.small.main.ui.favoritematch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haikotlin.main.detail.MatchDetailActivity
import com.small.main.R
import com.small.main.data.local.entity.MatchEntity
import com.small.main.data.remote.response.MatchResponse
import com.small.main.ui.adapter.recycleview.EventAdapter
import com.small.main.util.ParseUtils
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class FavoriteMatchFragment : Fragment(), FavoriteMatchView {

    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var adapter: EventAdapter
    private val listData: MutableList<MatchResponse> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite_match, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)
        presenter.loadFavoriteMatch()

        srl_favorite_match.onRefresh {
            presenter.loadFavoriteMatch(false)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFavoriteMatch()
    }

    private fun initList() {
        adapter = EventAdapter(listData) {
            startActivity<MatchDetailActivity>(
                    "MATCH_RESULT" to ParseUtils.matchResponseToEntity(it)
            )
        }
        rv_favorite_match.layoutManager = LinearLayoutManager(activity)
        rv_favorite_match.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        pb_favorite_match.visible()
    }

    override fun hideLoading() {
        pb_favorite_match.gone()
    }

    override fun showResultList(listMatchEntity: List<MatchEntity>) {
        listData.clear()
        for (i in listMatchEntity.indices) {
            listData.add(ParseUtils.matchEntityToResponse(listMatchEntity[i]))
        }
        adapter.notifyDataSetChanged()

        srl_favorite_match.isRefreshing = false
    }

    override fun showError() {
        val snbar = Snackbar.make(
                rl_favorite_match,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_LONG
        );
        snbar.setAction("RELOAD",
                {
                    presenter.loadFavoriteMatch()
                    snbar.dismiss()
                }
        )
        snbar.show()
    }

}