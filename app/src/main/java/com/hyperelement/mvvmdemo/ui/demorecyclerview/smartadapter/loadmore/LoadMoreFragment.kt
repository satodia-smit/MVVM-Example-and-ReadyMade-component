package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.loadmore

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.databinding.FragmentLoadMoreBinding
import com.hyperelement.mvvmdemo.utilities.ext.runDelayed
import com.hyperelement.mvvmdemo.utilities.ext.showToast
import kotlinx.android.synthetic.main.fragment_load_more.*
import smartadapter.SmartEndlessScrollRecyclerAdapter
import smartadapter.extension.SmartViewHolderBinder
import smartadapter.viewevent.listener.OnClickEventListener
import smartadapter.viewevent.listener.OnLongClickEventListener
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.swipe.BasicSwipeEventBinder
import smartadapter.viewevent.swipe.SwipeFlags
import timber.log.Timber

class LoadMoreFragment :
    BaseFragment<LoadMoreVM>(
        R.layout.fragment_load_more,
        LoadMoreVM::class
    ) {

    private var adapter: SmartEndlessScrollRecyclerAdapter? = null
    private var currentPage: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSpecificBinding<FragmentLoadMoreBinding>()?.viewModel = viewModel

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter = SmartEndlessScrollRecyclerAdapter.empty()
            .setAutoLoadMoreEnabled(true)
            .setLoadMoreLayoutResource(R.layout.custom_load_more_view)
            .setOnLoadMoreListener { adapter, loadMoreViewHolder ->
                loadMore()
            }
            .map(EmployeeEntity::class, LoadMoreVH::class)
            .setLayoutManager(layoutManager)
            .add(OnClickEventListener {
                val mEntity = adapter?.getItem(it.position) as? EmployeeEntity
                Timber.d("CLICKED ITEM ${mEntity.toString()}")
                context?.showToast("onItemClick ${it.position}")
            })
            .add(OnLongClickEventListener {
                context?.showToast("onItemLongClick ${it.position}")
            })
            .add(SwipeRemoveItemBinder(ItemTouchHelper.LEFT) {
                // Remove item from SmartRecyclerAdapter data set
                adapter?.removeItem(it.viewHolder.adapterPosition)
            })
            .into(rvEmployee)

        viewModel.mEmployeeList.observe(viewLifecycleOwner, Observer {
            adapter?.addItems(it)
        })
    }

    private fun loadMore() {
        runDelayed {
            viewModel.loadEmployeesFromStorage(currentPage)
            currentPage++
        }
    }

    class SwipeRemoveItemBinder(
        override var swipeFlags: SwipeFlags,
        override var eventListener: (ViewEvent.OnItemSwiped) -> Unit
    ) : BasicSwipeEventBinder(
        eventListener = eventListener
    ), SmartViewHolderBinder {
        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val icon = ContextCompat.getDrawable(
                viewHolder.itemView.context,
                R.drawable.ic_delete
            )
            val background = ColorDrawable(Color.RED)

            val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
            val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
            val iconBottom = iconTop + icon.intrinsicHeight

            val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
            val iconRight = itemView.right - iconMargin

            icon.setBounds(
                iconLeft,
                iconTop,
                iconRight,
                iconBottom
            )

            background.setBounds(
                (itemView.right + dX).toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )

            if (dX.toInt() == 0) { // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }

            background.draw(canvas)

            if (-dX > (icon.intrinsicWidth + iconMargin)) // Draw icon only on full visibility
                icon.draw(canvas)
        }
    }
}