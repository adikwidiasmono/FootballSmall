package com.small.main.ui.adapter.recycleview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.remote.response.TeamResponse
import com.small.main.util.CommonUtils
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val teams: List<TeamResponse>,
                  private val listener: (TeamResponse) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount() = teams.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
            TeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

}

class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(items: TeamResponse, listener: (TeamResponse) -> Unit) {

        Glide.with(itemView.context)
                .load(items.strTeamBadge)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_teams)
                        .error(R.drawable.ic_teams)
                )
                .into(itemView.iv_team)
        itemView.tv_team.text = CommonUtils.safePresentString(items.strTeam)

        itemView.setOnClickListener {
            listener(items)
        }
    }
}