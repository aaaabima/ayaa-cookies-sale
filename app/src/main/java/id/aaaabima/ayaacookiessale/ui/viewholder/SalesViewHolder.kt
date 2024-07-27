package id.aaaabima.ayaacookiessale.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import id.aaaabima.ayaacookiessale.databinding.SalesItemLayoutBinding
import id.aaaabima.ayaacookiessale.ui.model.SalesModel

class SalesViewHolder(
    private val binding: SalesItemLayoutBinding,
    private val onItemClicked: (SalesModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(salesModel: SalesModel) {
        binding.root.setOnClickListener { onItemClicked }
        binding.tvCake.text = salesModel.cake
        binding.tvQuantity.text = salesModel.quantity
        binding.tvPrice.text = salesModel.price
        binding.tvDescription.text = salesModel.description
    }
}