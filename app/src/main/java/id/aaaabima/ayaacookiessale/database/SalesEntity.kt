package id.aaaabima.ayaacookiessale.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales_table")
data class SalesEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "cake") val cake: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "quantity") val quantity: String
)