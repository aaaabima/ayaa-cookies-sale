package id.aaaabima.ayaacookiessale.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.aaaabima.ayaacookiessale.databinding.ActivitySalesDetailBinding
import id.aaaabima.ayaacookiessale.ui.model.SalesModel

class SalesDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySalesDetailBinding
    private var salesModel: SalesModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySalesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initView()
        initListener()
    }

    private fun initComponent() {
        salesModel = intent.getParcelableExtra(SalesActivity.EXTRA_SALES_DATA)
    }

    private fun initView() {
        binding.apply {
            edtName.setText(salesModel?.cake)
            edtPrice.setText(salesModel?.price)
            edtKuantitas.setText(salesModel?.quantity)
            edtDescription.setText(salesModel?.description)
        }
    }

    private fun initListener() {
        binding.btnUpdate.setOnClickListener {
            val replyIntent = Intent()
            val newSalesModel = SalesModel(
                id = salesModel!!.id,
                cake = binding.edtName.text.toString(),
                price = binding.edtPrice.text.toString(),
                quantity = binding.edtKuantitas.text.toString(),
                description = binding.edtDescription.text.toString()
            )
            replyIntent.putExtra(SalesActivity.EXTRA_SALES_DATA, newSalesModel)
            setResult(SalesActivity.RESULT_CODE_UPDATE, replyIntent)
            finish()
        }
        binding.btnDelete.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(SalesActivity.EXTRA_SALES_DATA, salesModel)
            setResult(SalesActivity.RESULT_CODE_DELETE, replyIntent)
            finish()
        }
    }
}