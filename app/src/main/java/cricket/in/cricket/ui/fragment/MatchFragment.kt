package cricket.`in`.cricket.ui.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cricket.`in`.cricket.R
import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.data.remote.Resource
import cricket.`in`.cricket.injection.Injectable
import cricket.`in`.cricket.utils.inflateLayout
import cricket.`in`.cricket.viewmodel.MatchViewModel
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchViewModel = ViewModelProviders.of(this, viewmodelFactory).get(MatchViewModel::class.java)

        // get matches exposes live data, now view must observe it
        matchViewModel.getMatches().observe(this, Observer<Resource<List<Matches>>> {
            if (it != null) {
                it.data?.forEach { Log.d("scorefragment", "team" + it.teamOne) }
            }
        })

    }

}