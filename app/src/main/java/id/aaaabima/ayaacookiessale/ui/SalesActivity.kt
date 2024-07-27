package id.aaaabima.ayaacookiessale.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.aaaabima.ayaacookiessale.databinding.ActivitySalesBinding
import id.aaaabima.ayaacookiessale.ui.adapter.SalesListAdapter
import id.aaaabima.ayaacookiessale.ui.model.SalesModel

class SalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySalesBinding
    private var salesAdapter: SalesListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySalesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initAdapter()
    }

    private fun initAdapter() {
        salesAdapter = SalesListAdapter(onItemClicked)
        binding.rvSales.apply {
            adapter = salesAdapter
            layoutManager = LinearLayoutManager(this@SalesActivity)
        }
    }

    private val onItemClicked : (SalesModel) -> Unit = { salesModel: SalesModel ->
        Toast.makeText(this, salesModel.cake, Toast.LENGTH_SHORT).show()
    }
}