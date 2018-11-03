package com.small.main.ui.teams.detailteam.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.data.local.entity.TeamEntity
import kotlinx.android.synthetic.main.fragment_team_overview.*

class OverviewFragment : Fragment() {

    private lateinit var matchItem: TeamEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.fragment_team_overview, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchItem = arguments?.getSerializable("TEAM_RESULT") as TeamEntity
        matchItem?.let {
            tv_team_overview.text = it.strDescriptionEN
        }
    }

}