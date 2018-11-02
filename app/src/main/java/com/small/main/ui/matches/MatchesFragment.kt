package com.small.main.ui.matches

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.haikotlin.main.detail.MatchDetailActivity
import com.small.main.R
import com.small.main.data.remote.response.MatchBySearchListResponse
import com.small.main.data.remote.response.MatchResponse
import com.small.main.ui.adapter.recycleview.EventAdapter
import com.small.main.ui.matches.nextmatch.NextMatchFragment
import com.small.main.ui.matches.previousmatch.PrevMatchFragment
import com.small.main.util.ParseUtils
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_matches.*
import kotlinx.android.synthetic.main.fragment_matches.view.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class MatchesFragment : Fragment(), MatchesView {

    private lateinit var presenter: MatchesPresenter
    private lateinit var adapter: EventAdapter
    private val listData: MutableList<MatchResponse> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.fragment_matches, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)

        setHasOptionsMenu(true)
        view.toolbar_matches.title = "Football Match Schedule"
        val act = activity as AppCompatActivity
        act.setSupportActionBar(toolbar_matches)

        setupViewPager(view.vp_matches)
        view.tl_matches.setupWithViewPager(view.vp_matches)
    }

    private fun initList() {
        adapter = EventAdapter(listData) {
            startActivity<MatchDetailActivity>(
                    "MATCH_RESULT" to ParseUtils.matchResponseToEntity(it)
            )
        }
        rv_matches.layoutManager = LinearLayoutManager(activity)
        rv_matches.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(PrevMatchFragment(), getString(R.string.prev_match))
        adapter.addFragment(NextMatchFragment(), getString(R.string.next_match))
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater?.inflate(R.menu.dashboard_menu, menu)

        val searchItem = menu?.findItem(R.id.it_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView? = null

        searchItem.let {
            searchView = it?.actionView as SearchView
        }
        searchView.let {
            it?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            it?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(query: String): Boolean {
//                    Log.e("onQueryTextChange", "->" + query)
                    onSearchMatch(query)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
//                    Log.e("onQueryTextSubmit", "->" + query)
                    return false
                }
            })
        }
    }

    class ViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        private val listFragment: MutableList<Fragment> = mutableListOf()
        private val listFragmentTitle: MutableList<String> = mutableListOf()

        override fun getItem(position: Int): Fragment {
            return listFragment.get(position)
        }

        override fun getCount(): Int {
            return listFragment.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return listFragmentTitle.get(position)
        }

        fun addFragment(fragment: Fragment, title: String) {
            listFragment.add(fragment)
            listFragmentTitle.add(title)
        }
    }

    fun onSearchMatch(queryTeamName: String) {
        Log.e("onSearchMatch", "->" + queryTeamName)
        if (queryTeamName.length < 1) {
            tl_matches.visible()
            vp_matches.visible()
            rv_matches.gone()
        } else {
            tl_matches.gone()
            vp_matches.gone()
            rv_matches.visible()
            presenter.searchMatch(queryTeamName)
        }
    }

    override fun showSearchMatchList(matchBySearchListResponse: MatchBySearchListResponse) {
        listData.clear()
        matchBySearchListResponse.event?.let {
            for (i in matchBySearchListResponse.event.indices) {
                listData.add(matchBySearchListResponse.event[i])
            }
        }

        adapter.notifyDataSetChanged()
    }

    override fun onErrorSearchMatchList() {
        val snbar = Snackbar.make(
                rl_matches,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_LONG
        )
        snbar.show()
    }

    override fun showLoading() {
        pb_matches.visible()
    }

    override fun hideLoading() {
        pb_matches.gone()
    }

}