package cricket.`in`.cricket.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import cricket.`in`.cricket.data.entity.Matches

/**
 * Created by vivek on 22/06/17.
 */
@Dao
abstract class CricketDao {

    @Query("SELECT * FROM cric")
    abstract fun getMatches(): LiveData<List<Matches>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItem(matches: List<Matches>)

}