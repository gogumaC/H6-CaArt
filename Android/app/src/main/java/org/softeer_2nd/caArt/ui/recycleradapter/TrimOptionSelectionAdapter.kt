package org.softeer_2nd.caArt.ui.recycleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.softeer_2nd.caArt.databinding.ItemTrimSelectBinding
import org.softeer_2nd.caArt.model.data.Trim
import org.softeer_2nd.caArt.ui.callback.OnTrimItemClickListener

class TrimOptionSelectionAdapter(private val onTrimItemClickListener: OnTrimItemClickListener):
    RecyclerView.Adapter<TrimOptionSelectionAdapter.TrimOptionSelectionViewHolder>() {
    private var selectedPosition = 0
    private var items = mutableListOf<Trim>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrimOptionSelectionViewHolder {
        val binding =
            ItemTrimSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrimOptionSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrimOptionSelectionViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    fun updateItems(newItems: List<Trim>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    inner class TrimOptionSelectionViewHolder(val binding: ItemTrimSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val exteriorAdapter = TrimOptionMoreDetailAdapter()
        private val interiorAdapter = TrimOptionMoreDetailAdapter()
        private val defaultAdapter = TrimOptionMoreDetailAdapter(true)
        init {
            binding.ivTrimCheck.setOnClickListener {
                onTrimItemClickListener.onItemClicked(adapterPosition)

                if (selectedPosition != -1) {
                    items[selectedPosition].isChecked = false
                    notifyItemChanged(selectedPosition)
                }

                items[adapterPosition].isChecked = true
                notifyItemChanged(adapterPosition)
                selectedPosition = adapterPosition
            }

            binding.incOtherMore.rvOtherMoreExteriorDetail.apply {
                this.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = exteriorAdapter
                isNestedScrollingEnabled = false
            }

            binding.incOtherMore.rvOtherMoreInteriorDetail.apply {
                this.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = interiorAdapter
            }

            binding.incOtherMore.rvOtherMoreDefaultDetail.apply {
                this.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this.adapter = defaultAdapter
            }
        }


        fun bind(item: Trim) {
            binding.apply {
                trimItems = item
                lineVisibleGone = adapterPosition == (itemCount - 1)
                isChecked = item.isChecked
            }

            exteriorAdapter.updateItems(item.exteriorColors)
            interiorAdapter.updateItems(item.interiorColors)
            defaultAdapter.updateItems(item.mainOptions)
        }
    }
}