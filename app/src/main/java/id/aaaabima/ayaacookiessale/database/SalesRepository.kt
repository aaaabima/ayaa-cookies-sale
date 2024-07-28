package id.aaaabima.ayaacookiessale.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class SalesRepository(private val salesDao: SalesDao) {

    val allSales: Flow<List<SalesEntity>> = salesDao.getSales()

    @WorkerThread
    suspend fun addSales(sales: SalesEntity) {
        salesDao.addSales(sales)
    }

    @WorkerThread
    suspend fun deleteAllSales() {
        salesDao.deleteAllSales()
    }

    @WorkerThread
    suspend fun updateSales(
        id: Int,
        cake: String,
        price: String,
        description: String,
        quantity: String
    ) {
        salesDao.updateSales(id, cake, price, description, quantity)
    }

    @WorkerThread
    suspend fun deleteSales(sales: SalesEntity) {
        salesDao.deleteSales(sales)
    }

    @WorkerThread
    suspend fun updateSales(sales: SalesEntity) {
        salesDao.updateSales(sales)
    }
}