package cricket.`in`.cricket.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import cricket.`in`.cricket.AppConfig
import cricket.`in`.cricket.data.entity.Matches

/**
 * Created by vivek on 22/06/17.
 */
@Database(entities = arrayOf(Matches::class), exportSchema = false,
        version = 8)
abstract class CricketDatabase : RoomDatabase() {

    abstract fun provideScoreDao(): CricketDao

    companion object {
        const val DATABASE_NAME = AppConfig.DATABASE_NAME

        fun createPersistentDatabase(context: Context): CricketDatabase {
            return Room.databaseBuilder(context.applicationContext, CricketDatabase::class.java, DATABASE_NAME).build()
        }
    }
}