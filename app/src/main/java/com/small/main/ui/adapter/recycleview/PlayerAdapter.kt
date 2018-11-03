package com.small.main.ui.adapter.recycleview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.remote.response.PlayerItem
import com.small.main.data.remote.response.TeamResponse
import com.small.main.util.CommonUtils
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerAdapter(private val teams: List<PlayerItem>,
                    private val listener: (PlayerItem) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount() = teams.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder =
            PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

}

class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(items: PlayerItem, listener: (PlayerItem) -> Unit) {

        Glide.with(itemView.context)
                .load(items.strCutout)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_teams)
                        .error(R.drawable.ic_teams)
                )
                .into(itemView.iv_player)
        itemView.tv_player.text = CommonUtils.safePresentString(items.strPlayer)
        itemView.tv_position.text = CommonUtils.safePresentString(items.strPosition)

        itemView.setOnClickListener {
            listener(items)
        }
    }
}