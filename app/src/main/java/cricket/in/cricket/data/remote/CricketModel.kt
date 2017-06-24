package cricket.`in`.cricket.data.remote

import com.google.gson.annotations.SerializedName
import cricket.`in`.cricket.data.entity.Matches

/**
 * Created by vivek on 24/06/17.
 *
 */
data class CricketModel(
        @SerializedName("matches") var matches: List<Matches> = emptyList())