package cricket.`in`.cricket.utils

import android.support.v4.app.FragmentManager
import cricket.`in`.cricket.R
import cricket.`in`.cricket.ui.activity.MainActivity
import cricket.`in`.cricket.ui.fragment.MatchFragment
import javax.inject.Inject

/**
 * Created by vivek on 24/06/17.
 */

class NavigationController() {

    var container: Int = 0
    lateinit var fm: FragmentManager

    @Inject constructor(activity: MainActivity) : this() {
        this.container = R.id.container
        fm = activity.supportFragmentManager
    }

    fun navigateToScore() {
        var scoreFragement: MatchFragment = MatchFragment()
        fm.beginTransaction().replace(container, scoreFragement, "scoreTag")
                .commitAllowingStateLoss()
    }
}
