package cricket.`in`.cricket.data.remote

import android.arch.lifecycle.LiveData
import cricket.`in`.cricket.data.entity.Matches
import cricket.`in`.cricket.data.local.CricketDao
import cricket.`in`.cricket.data.remote.api.CricketService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by vivek on 24/06/17.
 */

@Singleton
class MatchRepository {

    var cricDao: CricketDao
    var service: CricketService
    var executors: AppExecutors

    @Inject constructor(appDao: CricketDao, service: CricketService, executors: AppExecutors) {
        this.cricDao = appDao
        this.service = service
        this.executors = executors
    }

    fun getAllMatches(): LiveData<Resource<List<Matches>>> {
        return object : NetworkBoundResource<List<Matches>, CricketModel>(executors) {
            override fun saveCallResult(item: CricketModel) {
                cricDao.insertItem(item.matches)
            }

            override fun shouldFetch(data: List<Matches>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Matches>> {
                return cricDao.getMatches()

            }

            override fun createCall(): LiveData<ApiResponse<CricketModel>> {
                return service.getMatches()
            }
        }.asLiveData()
    }

    private fun transform(currencies: List<Matches>): List<Matches> {
        val currencyList = ArrayList<Matches>()
        currencies.forEach {
            currencyList.add(Matches(0, it.teamTwo, it.teamOne))
        }
        return currencyList
    }


}