package com.small.main.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.data.remote.response.MatchResponse
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter(private val matches: List<MatchResponse>,
                   private val listener: (MatchResponse) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    override fun getItemCount() = matches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))

}

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(items: MatchResponse, listener: (MatchResponse) -> Unit) {

        if (items.intHomeScore == null || items.intAwayScore == null) {
            itemView.tv_home_team_score.visibility = View.INVISIBLE
            itemView.tv_away_team_score.visibility = View.INVISIBLE
        } else {
            itemView.tv_home_team_score.text = items.intHomeScore.toString()
            itemView.tv_away_team_score.text = items.intAwayScore.toString()
        }

        val matchDate = SimpleDateFormat("dd/MM/yy HH:mm:ssX", Locale.ENGLISH)
                .parse(items.strDate + " " + items.strTime)

        val matchDateFormatted = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale("in", "ID"))

        itemView.tv_match_date.text = matchDateFormatted.format(matchDate)
        val strs = items.strEvent?.split("vs")
        var homeTeam = strs?.get(0)?.trim() ?: "Unknown Team"
        var awayTeam = strs?.get(1)?.trim() ?: "Unknown Team"

        if (homeTeam.length > 14)
            homeTeam.substring(0, 11) + "..."
        if (awayTeam.length > 14)
            awayTeam.substring(0, 11) + "..."

        itemView.tv_home_team_name.text = homeTeam
        itemView.tv_away_team_name.text = awayTeam

        itemView.setOnClickListener {
            listener(items)
        }
    }
}