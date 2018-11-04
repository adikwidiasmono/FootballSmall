package com.small.main.ui.adapter.recycleview

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.data.remote.response.MatchResponse
import com.small.main.util.CommonUtils
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent


class EventAdapter(private val matches: List<MatchResponse>,
                   private val listener: (MatchResponse) -> Unit)
    : RecyclerView.Adapter<MatchViewHolder>() {

    var context: Context? = null

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    override fun getItemCount() = matches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
            MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false), parent.context)

}

class MatchViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(items: MatchResponse, listener: (MatchResponse) -> Unit) {
        if (items.strSport != null && items.strSport.equals("Soccer", true)) {
            if (items.intHomeScore == null || items.intAwayScore == null) {
                itemView.tv_home_team_score.visibility = View.INVISIBLE
                itemView.tv_away_team_score.visibility = View.INVISIBLE
            } else {
                itemView.tv_home_team_score.text = items.intHomeScore.toString()
                itemView.tv_away_team_score.text = items.intAwayScore.toString()
            }

            var matchDate: Date

            if (items.strTime == null) {
                matchDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                        .parse(items.dateEvent)
            } else if (items.strTime?.contains("\\+")!!) {
                matchDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH)
                        .parse(items.dateEvent + " " + items.strTime)
            } else {
                matchDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                        .parse(items.dateEvent + " " + items.strTime)
            }

            itemView.tv_match_date.text = CommonUtils.getStringLocalDate(matchDate)
            val strs = items.strEvent?.split("vs")
            var homeTeam = "-"
            var awayTeam = "-"
            if (strs != null && strs?.size > 1) {
                homeTeam = strs?.get(0)?.trim()
                awayTeam = strs?.get(1)?.trim()
            }

            if (homeTeam.length > 14)
                homeTeam.substring(0, 11) + "..."
            if (awayTeam.length > 14)
                awayTeam.substring(0, 11) + "..."

            itemView.tv_home_team_name.text = homeTeam
            itemView.tv_away_team_name.text = awayTeam

            itemView.setOnClickListener {
                listener(items)
            }

            itemView.iv_add_reminder.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val cal = Calendar.getInstance()
                    cal.time = CommonUtils.parseDateToLocalDate(matchDate)
                    val intent = Intent(Intent.ACTION_INSERT).apply {
                        data = CalendarContract.Events.CONTENT_URI
                        putExtra(CalendarContract.Events.TITLE, items.strEvent)
                        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
                        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.timeInMillis + (1.5 * 60 * 60 * 1000)) // 1.5 Hours
                    }
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                    }
                }
            })
        }
    }
}