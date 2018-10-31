package com.small.main.ui.adapter.spinner

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.remote.response.CountrysItem
import com.small.main.util.CommonUtils
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(context: Context?, @LayoutRes val layoutResource: Int, val listLeague: List<CountrysItem?>?)
    : ArrayAdapter<CountrysItem>(context, layoutResource, listLeague) {

    override fun getCount(): Int {
        return listLeague?.size ?: 0
    }

    override fun getItem(position: Int): CountrysItem? {
        return listLeague?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(layoutResource, parent, false)
        val league = listLeague?.get(position)

        Glide.with(context)
                .load(league?.strBadge)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_teams)
                        .error(R.drawable.ic_teams)
                )
                .into(view.iv_league)
        view.tv_league.text = CommonUtils.safePresentString(league?.strLeague)

        return view
    }

}