package cricket.`in`.cricket.data.remote.api

import android.arch.lifecycle.LiveData
import cricket.`in`.cricket.BuildConfig
import cricket.`in`.cricket.data.remote.ApiResponse
import cricket.`in`.cricket.data.remote.CricketModel
import retrofit2.http.GET

/**
 * Created by vivek on 24/06/17.
 */

interface CricketService {

    @GET("matches?apikey=" + BuildConfig.API_KEY)
    fun getMatches(): LiveData<ApiResponse<CricketModel>>
}