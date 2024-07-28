package id.aaaabima.ayaacookiessale.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SalesDao {

    @Query("SELECT * FROM sales_table ORDER BY id ASC")
    fun getSales(): Flow<List<SalesEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSales(sales: SalesEntity)

    @Query("DELETE FROM sales_table")
    suspend fun deleteAllSales()

    @Query("UPDATE sales_table SET cake = :cake, price = :price, description = :description, quantity = :quantity WHERE id = :id")
    suspend fun updateSales(
        id: Int,
        cake: String,
        price: String,
        description: String,
        quantity: String
    )

    @Delete
    suspend fun deleteSales(sales: SalesEntity)

    @Update(entity = SalesEntity::class)
    suspend fun updateSales(sales: SalesEntity)
}