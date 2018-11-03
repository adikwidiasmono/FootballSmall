package com.small.main.ui.favorites.favoriteteam

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.data.local.entity.TeamEntity
import com.small.main.data.remote.response.TeamResponse
import com.small.main.ui.adapter.recycleview.TeamAdapter
import com.small.main.ui.teams.detailteam.TeamDetailActivity
import com.small.main.util.ParseUtils
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class FavoriteTeamFragment : Fragment(), FavoriteTeamView {

    private lateinit var presenter: FavoriteTeamPresenter
    private lateinit var adapter: TeamAdapter
    private val listData: MutableList<TeamResponse> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite_team, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)
        presenter.loadFavoriteMatch()

        srl_favorite_team.onRefresh {
            presenter.loadFavoriteMatch(false)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFavoriteMatch()
    }

    private fun initList() {
        adapter = TeamAdapter(listData) {
            startActivity<TeamDetailActivity>(
                    "TEAM_RESULT" to ParseUtils.teamResponseToEntity(it)
            )
        }
        rv_favorite_team.layoutManager = LinearLayoutManager(activity)
        rv_favorite_team.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        pb_favorite_team.visible()
    }

    override fun hideLoading() {
        pb_favorite_team.gone()
    }

    override fun showResultList(listTeamEntity: List<TeamEntity>) {
        listData.clear()
        for (i in listTeamEntity.indices) {
            listData.add(ParseUtils.teamEntityToResponse(listTeamEntity[i]))
        }
        adapter.notifyDataSetChanged()

        srl_favorite_team.isRefreshing = false
    }

    override fun showError() {
        val snbar = Snackbar.make(
                rl_favorite_team,
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