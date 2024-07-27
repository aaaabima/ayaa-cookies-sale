package id.aaaabima.ayaacookiessale.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SalesEntity::class], version = 1, exportSchema = false)
public abstract class SalesRoomDatabase : RoomDatabase() {

  abstract fun salesDao(): SalesDao

  companion object {
    @Volatile
    private var INSTANCE: SalesRoomDatabase? = null

    fun getSalesDatabase(context: Context): SalesRoomDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          SalesRoomDatabase::class.java,
          "sales_database"
        ).build()
        INSTANCE = instance
        instance
      }
    }
  }
}