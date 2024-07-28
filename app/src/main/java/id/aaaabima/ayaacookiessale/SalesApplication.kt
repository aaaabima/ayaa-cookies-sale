package id.aaaabima.ayaacookiessale

import android.app.Application
import id.aaaabima.ayaacookiessale.database.SalesRepository
import id.aaaabima.ayaacookiessale.database.SalesRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SalesApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val salesDatabase by lazy { SalesRoomDatabase.getSalesDatabase(this, applicationScope) }
    val salesRepository by lazy { SalesRepository(salesDatabase.salesDao()) }
}