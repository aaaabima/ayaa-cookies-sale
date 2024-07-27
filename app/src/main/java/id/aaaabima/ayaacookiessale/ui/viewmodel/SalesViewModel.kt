package id.aaaabima.ayaacookiessale.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.aaaabima.ayaacookiessale.database.SalesEntity
import id.aaaabima.ayaacookiessale.database.SalesRepository
import kotlinx.coroutines.launch

class SalesViewModel(private val repository: SalesRepository) : ViewModel() {
  val allSales = repository.allSales.asLiveData()

  fun addSales(sales: SalesEntity) = viewModelScope.launch {
    repository.addSales(sales)
  }

  fun deleteSalesById(id: Int) = viewModelScope.launch {
    repository.deleteSalesById(id)
  }

  fun deleteAllSales() = viewModelScope.launch {
    repository.deleteAllSales()
  }

  fun updateSales(id: Int, cake: String, price: String, description: String, quantity: String) = viewModelScope.launch {
    repository.updateSales(id, cake, price, description, quantity)
  }

  fun deleteSales(sales: SalesEntity) = viewModelScope.launch {
    repository.deleteSales(sales)
  }

  fun updateSales(sales: SalesEntity) = viewModelScope.launch {
    repository.updateSales(sales)
  }
}