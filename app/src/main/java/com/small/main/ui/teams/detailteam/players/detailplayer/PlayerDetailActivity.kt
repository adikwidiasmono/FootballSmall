package com.small.main.ui.teams.detailteam.players.detailplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.remote.response.PlayerItem
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var playerItem: PlayerItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        playerItem = intent.getSerializableExtra("PLAYER_RESULT") as PlayerItem
        playerItem?.let {
            Glide.with(this)
                    .load(it.strFanart1)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_image_def_128dp)
                            .error(R.drawable.ic_image_err_128dp)
                    )
                    .into(iv_player_icon)

            toolbar_player_detail.title = it.strPlayer
            setSupportActionBar(toolbar_player_detail)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            tv_player_weight.text = it.strWeight
            tv_player_height.text = it.strHeight
            tv_player_position.text = it.strPosition
            tv_player_desc.text = it.strDescriptionEN
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
