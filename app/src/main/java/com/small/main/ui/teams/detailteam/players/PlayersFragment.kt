package com.small.main.ui.teams.detailteam.players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.data.local.entity.TeamEntity
import com.small.main.data.remote.response.PlayerItem
import com.small.main.data.remote.response.PlayerListResponse
import com.small.main.ui.adapter.recycleview.PlayerAdapter
import com.small.main.ui.adapter.spinner.LeagueAdapter
import com.small.main.ui.teams.detailteam.players.detailplayer.PlayerDetailActivity
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get


class PlayersFragment : Fragment(), PlayersView {

    private lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayerAdapter
    private val listData: MutableList<PlayerItem> = mutableListOf()

    private lateinit var spAdapter: LeagueAdapter

    private lateinit var matchItem: TeamEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_players, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        presenter = get()
        presenter.attachView(this)

        matchItem = arguments?.getSerializable("TEAM_RESULT") as TeamEntity
        matchItem?.let {
            // Load all players
            presenter.loadPlayers(it.idTeam)
        }
    }

    private fun initList() {
        adapter = PlayerAdapter(listData) {
            startActivity<PlayerDetailActivity>(
                    "PLAYER_RESULT" to it
            )
        }
        rv_players.layoutManager = LinearLayoutManager(activity)
        rv_players.adapter = this.adapter


    }

    override fun showPlayerList(playerListResponse: PlayerListResponse) {
        listData.clear()
        playerListResponse.player?.let {
            for (i in playerListResponse.player.indices) {
                listData.add(playerListResponse.player[i])
            }
        }

        adapter.notifyDataSetChanged()
    }

    override fun onErrorPlayerList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        pb_players.visible()
    }

    override fun hideLoading() {
        pb_players.gone()
    }

}