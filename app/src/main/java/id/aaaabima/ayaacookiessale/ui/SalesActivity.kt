package id.aaaabima.ayaacookiessale.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.aaaabima.ayaacookiessale.SalesApplication
import id.aaaabima.ayaacookiessale.databinding.ActivitySalesBinding
import id.aaaabima.ayaacookiessale.ui.adapter.SalesListAdapter
import id.aaaabima.ayaacookiessale.ui.model.SalesModel
import id.aaaabima.ayaacookiessale.ui.model.toListSalesModel
import id.aaaabima.ayaacookiessale.ui.model.toSalesEntity
import id.aaaabima.ayaacookiessale.ui.viewmodel.SalesViewModel
import id.aaaabima.ayaacookiessale.ui.viewmodel.SalesViewModelFactory

class SalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySalesBinding
    private var salesAdapter: SalesListAdapter? = null
    private val salesViewModel: SalesViewModel by viewModels {
        SalesViewModelFactory((application as SalesApplication).salesRepository)
    }

    private lateinit var getResult: ActivityResultLauncher<Intent>

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

        initGetActivityResult()
        initAdapter()
        initObserver()
        initListener()
    }

    private fun initObserver() {
        salesViewModel.allSales.observe(this) { sales ->
            sales?.let { salesAdapter?.submitList(it.toListSalesModel()) }
        }
    }

    private fun initAdapter() {
        salesAdapter = SalesListAdapter(onItemClicked)
        binding.rvSales.apply {
            adapter = salesAdapter
            layoutManager = LinearLayoutManager(this@SalesActivity)
        }
    }

    private fun initListener() {
        binding.fabAddSales.setOnClickListener {
            val intent = Intent(this@SalesActivity, AddNewSalesActivity::class.java)
            getResult.launch(intent)
        }
    }

    private fun initGetActivityResult() {
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> result.data?.getParcelableExtra<SalesModel>(AddNewSalesActivity.EXTRA_REPLY)?.let {
                    salesViewModel.addSales(it.toSalesEntity())
                }

                RESULT_CODE_DELETE -> result.data?.getParcelableExtra<SalesModel>(EXTRA_SALES_DATA)?.let {
                    salesViewModel.deleteSales(it.toSalesEntity())
                }

                RESULT_CODE_UPDATE -> result.data?.getParcelableExtra<SalesModel>(EXTRA_SALES_DATA)?.let {
                    salesViewModel.updateSales(it.toSalesEntity())
                }

                RESULT_CODE_FAIL_INSERT -> Toast.makeText(
                    applicationContext,
                    "Kue tidak ditambahkan karena data tidak lengkap.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private val onItemClicked : (SalesModel) -> Unit = { salesModel: SalesModel ->
        val intent = Intent(this@SalesActivity, SalesDetailActivity::class.java)
        intent.putExtra(EXTRA_SALES_DATA, salesModel)
        getResult.launch(intent)
    }

    companion object {
        const val EXTRA_SALES_DATA = "SALES_DATA"
        const val RESULT_CODE_FAIL_INSERT = 2
        const val RESULT_CODE_DELETE = 3
        const val RESULT_CODE_UPDATE = 4
    }
}