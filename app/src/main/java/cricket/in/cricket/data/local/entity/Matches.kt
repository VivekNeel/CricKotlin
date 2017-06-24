package cricket.`in`.cricket.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import cricket.`in`.cricket.AppConfig

/**
 * Created by vivek on 24/06/17.
 */
@Entity(tableName = AppConfig.TABLE_NAME)
data class Matches(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                   @SerializedName("team-2") var teamTwo: String? = null,
                   @SerializedName("team-1") var teamOne: String? = null,
                   @SerializedName("matchStarted") var isMatchStarted: Boolean = false)
