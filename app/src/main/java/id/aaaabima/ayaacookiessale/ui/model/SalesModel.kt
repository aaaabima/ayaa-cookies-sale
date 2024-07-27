package id.aaaabima.ayaacookiessale.ui.model

import android.os.Parcel
import android.os.Parcelable
import id.aaaabima.ayaacookiessale.database.SalesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
class SalesModel(
    val id: Int,
    val cake: String,
    val price: String,
    val description: String,
    val quantity: String
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        return if (other is SalesModel) {
            other.id == id
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + cake.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + quantity.hashCode()
        return result
    }
}

fun SalesEntity.toSalesModel() = SalesModel(
    id = id,
    cake = cake,
    price = price,
    description = description,
    quantity = quantity
)

fun SalesModel.toSalesEntity() = SalesEntity(
    id = id,
    cake = cake,
    price = price,
    description = description,
    quantity = quantity
)