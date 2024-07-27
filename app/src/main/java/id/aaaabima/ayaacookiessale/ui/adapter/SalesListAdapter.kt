package id.aaaabima.ayaacookiessale.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import id.aaaabima.ayaacookiessale.databinding.SalesItemLayoutBinding
import id.aaaabima.ayaacookiessale.ui.model.SalesModel
import id.aaaabima.ayaacookiessale.ui.viewholder.SalesViewHolder

class SalesListAdapter(
    private val onItemClicked: (SalesModel) -> Unit
): ListAdapter<SalesModel, SalesViewHolder>(SalesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        return SalesViewHolder(
            SalesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked
        )
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SalesDiffCallback: DiffUtil.ItemCallback<SalesModel>() {
        override fun areItemsTheSame(oldItem: SalesModel, newItem: SalesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SalesModel, newItem: SalesModel): Boolean {
            return oldItem == newItem
        }
    }
}