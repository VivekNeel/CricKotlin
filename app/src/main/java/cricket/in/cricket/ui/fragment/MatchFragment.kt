package cricket.`in`.cricket.ui.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cricket.`in`.cricket.R
import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.data.remote.Resource
import cricket.`in`.cricket.injection.Injectable
import cricket.`in`.cricket.ui.adapter.MatchAdapter
import cricket.`in`.cricket.utils.inflateLayout
import cricket.`in`.cricket.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_score.*
import javax.inject.Inject

/**
 * Created by vivek on 24/06/17.
 */
class MatchFragment : LifecycleFragment(), Injectable {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    lateinit var matchViewModel: MatchViewModel


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflateLayout(R.layout.fragment_score)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        matchRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        initAdapter()


        matchViewModel = ViewModelProviders.of(this, viewmodelFactory).get(MatchViewModel::class.java)

        // get matches exposes live data, now view must observe it
        matchViewModel.getMatches().observe(this, Observer<Resource<List<Matches>>> {
            if (it != null && it.data != null) {
                (matchRV.adapter as MatchAdapter).updateItems(it.data)
            }
        })
    }

    private fun initAdapter() {
        if (matchRV.adapter == null
                ) {
            matchRV.adapter = MatchAdapter()
        }
    }

}