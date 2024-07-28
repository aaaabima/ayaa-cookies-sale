package id.aaaabima.ayaacookiessale.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.aaaabima.ayaacookiessale.R
import id.aaaabima.ayaacookiessale.databinding.ActivityAddNewSalesBinding
import id.aaaabima.ayaacookiessale.ui.model.SalesModel

class AddNewSalesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewSalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNewSalesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            val replyIntent = Intent()
            if (validateData()) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val sales = SalesModel(
                    binding.edtName.text.toString(),
                    binding.edtPrice.text.toString(),
                    binding.edtKuantitas.text.toString(),
                    binding.edtDescription.text.toString()
                )
                replyIntent.putExtra(EXTRA_REPLY, sales)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    private fun validateData(): Boolean {
        return binding.edtName.text?.isNotEmpty() == true &&
                binding.edtPrice.text?.isNotEmpty() == true &&
                binding.edtKuantitas.text?.isNotEmpty() == true &&
                binding.edtDescription.text?.isNotEmpty() == true
    }

    companion object {
        const val EXTRA_REPLY = "REPLY"
    }
}