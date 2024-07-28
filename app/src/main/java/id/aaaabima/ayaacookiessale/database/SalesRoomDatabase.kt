package id.aaaabima.ayaacookiessale.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [SalesEntity::class], version = 1, exportSchema = false)
abstract class SalesRoomDatabase : RoomDatabase() {

    abstract fun salesDao(): SalesDao

    private class SalesDatabaseCallback(
        private val coroutineScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { salesRoomDatabase ->
                coroutineScope.launch {
                    var salesDao = salesRoomDatabase.salesDao()

                    salesDao.deleteAllSales()

                    var sales = SalesEntity(1, "Kue Salju", "Rp 80.000", "Kue kacang mete yang dilapisi gula halus.", "1kg")
                    salesDao.addSales(sales)
                    sales = SalesEntity(2, "Kue Keju", "Rp 75.000", "Kue dengan base keju dan topping keju yang renyah.", "1kg")
                    salesDao.addSales(sales)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SalesRoomDatabase? = null

        fun getSalesDatabase(
            context: Context,
            coroutineScope: CoroutineScope
        ): SalesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SalesRoomDatabase::class.java,
                    "sales_database"
                ).addCallback(SalesDatabaseCallback(coroutineScope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}