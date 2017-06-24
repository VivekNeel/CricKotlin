package cricket.`in`.cricket.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.data.remote.MatchRepository
import cricket.`in`.cricket.data.remote.Resource
import javax.inject.Inject

/**
 * Created by vivek on 24/06/17.
 */
class MatchViewModel() : ViewModel() {

    lateinit var matchLiveData: LiveData<Resource<List<Matches>>>


    @Inject constructor(repository: MatchRepository) : this() {
        matchLiveData = repository.getAllMatches()
    }

    fun getMatches() : LiveData<Resource<List<Matches>>>  = matchLiveData

}