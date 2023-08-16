package org.softeer_2nd.caArt.view.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.softeer_2nd.caArt.model.data.Option
import org.softeer_2nd.caArt.databinding.FragmentCarOptionChoiceBinding
import org.softeer_2nd.caArt.databinding.ItemSituationOptionsOptionBinding
import org.softeer_2nd.caArt.model.data.typeEnum.BottomSheetMode
import org.softeer_2nd.caArt.view.dialog.OptionDetailDialog
import org.softeer_2nd.caArt.model.factory.DummyItemFactory
import org.softeer_2nd.caArt.util.dp2px
import org.softeer_2nd.caArt.view.recyclerAdapter.OptionPreviewRecyclerAdapter
import org.softeer_2nd.caArt.view.recyclerAdapter.OptionTagRecyclerAdapter

class CarOptionChoiceFragment : Fragment() {
    private var _binding: FragmentCarOptionChoiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarOptionChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bsOptionChoiceSummary.apply {
            setMode(
                BottomSheetMode.PrevAndEstimate,
                CarTrimChoiceFragmentDirections.actionCarTrimChoiceFragmentToCarTrimDescriptionFragment()
            )
        }

        binding.incOptionChoiceTopIndicator.currentIndex = 2

        binding.bsOptionChoiceSummary.setMode(
            BottomSheetMode.PrevAndEstimate,
            CarColorChoiceFragmentDirections.actionCarColorChoiceFragmentToCarOptionChoiceFragment()
        )

        val optionTagAdapter = OptionTagRecyclerAdapter(
            List(8) { "태그$it" }
        ) { _, tag ->
            binding.isSituationalTagPresent = (tag != "태그0")
            Toast.makeText(requireContext(), tag, Toast.LENGTH_SHORT).show()
            binding.incSituationalOptions.ivSituationalTagOptionsSituationImage.addOption(
                DummyItemFactory.createAdditionalSingleOptionItem()[0], 0.8f, 0.5f
            )

            binding.incSituationalOptions.ivSituationalTagOptionsSituationImage.addOption(
                DummyItemFactory.createAdditionalSingleOptionItem()[0], 0.5f, 0.5f
            )
        }

        binding.rvOptionChoiceOptionTagsContainer.initOptionChoiceOptionTagsContainer(
            optionTagAdapter
        )

        val optionPreviewAdapter = OptionPreviewRecyclerAdapter() { _, option ->
            showOptionDetailDialog(listOf(option))
        }.apply {
            addOptionList(DummyItemFactory.createAdditionalOptionGrouopItem())
        }
        binding.rvOptionPreviewContainer.initOptionPreviewContainer(optionPreviewAdapter)

        val situationalOptionViewOptionMap = mapOf<ItemSituationOptionsOptionBinding, Option?>(
            binding.incSituationalOptions.incSituationOption1 to null,
            binding.incSituationalOptions.incSituationOption2 to null,
            binding.incSituationalOptions.incSituationOption3 to null,
            binding.incSituationalOptions.incSituationOption4 to null,

            ).apply {
            keys.forEach {
                it.onClickListener = View.OnClickListener { view ->
                    //모델로 전달
                    it.isSelected = !it.isSelected
                }
            }
        }
    }

    private fun RecyclerView.initOptionPreviewContainer(adapter: OptionPreviewRecyclerAdapter) {
        this.adapter = adapter
        layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0 || position == adapter.itemCount - 1) 2
                    else 1
                }
            }
        }
        addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 4f.dp2px(requireContext())
                outRect.right = 4f.dp2px(requireContext())
                if (getChildAdapterPosition(view) != 0 && getChildAdapterPosition(view) != adapter.itemCount - 1) {
                    outRect.top = 12f.dp2px(requireContext())
                    outRect.bottom = 12f.dp2px(requireContext())
                }
            }
        })
    }

    private fun RecyclerView.initOptionChoiceOptionTagsContainer(adapter: OptionTagRecyclerAdapter) {
        val optionTagMargin = 6f.dp2px(requireContext())
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        this.adapter = adapter
        addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = optionTagMargin
                outRect.right = optionTagMargin
            }
        })
    }

    private fun showOptionDetailDialog(options: List<Option>) {
        OptionDetailDialog.Builder()
            .setOptionList(options)
            .build()
            .show(requireActivity().supportFragmentManager, "optionDetail")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}