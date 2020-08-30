package com.eaglesoft.carousel.framework.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.eaglesoft.carousel.R
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.state.DataState
import com.eaglesoft.carousel.framework.presentation.adapter.UserItemAdapter
import com.eaglesoft.carousel.framework.presentation.viewmodel.MainStateEvent.GetUsersEvent
import com.eaglesoft.carousel.framework.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import swipeable.com.layoutmanager.OnItemSwiped
import swipeable.com.layoutmanager.SwipeableLayoutManager
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
constructor(
    private val someString: String
) : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment"

    private val viewModel: MainViewModel by viewModels()
    private var adapter: UserItemAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        viewModel.setStateEvent(GetUsersEvent)
        setSwappableItem()
        Log.d(TAG, "someString: ${someString}")
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<User>> -> {
                    displayProgressBar(false)
                    setUserItem(dataState.data as MutableList<User>)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })

        viewModel.favorite.observe(viewLifecycleOwner, { favorite ->
            when (favorite) {
                is DataState.Success<User> -> {
                    Toast.makeText(
                        context,
                        getString(R.string.message_success_favorite, favorite.data.username),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {

                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) text.text = message else text.text = "Unknown error."
    }

    private fun setUserItem(users: MutableList<User>) {
        adapter?.setData(users)
    }

    private fun setSwappableItem() {
        val swappableTouchHelperCallback: SwipeableTouchHelperCallback =
            object : SwipeableTouchHelperCallback(object : OnItemSwiped {
                override fun onItemSwiped() {
                    adapter?.removeTopItem()
                    viewModel.setStateEvent(GetUsersEvent)
                }

                override fun onItemSwipedLeft() {
                    Log.e("SWIPE", "LEFT")
                }

                override fun onItemSwipedRight() {
                    viewModel.addFavorite(GetUsersEvent, adapter?.getFavoriteItem())
                    Log.e("SWIPE", "RIGHT")
                }

                override fun onItemSwipedUp() {
                    Log.e("SWIPE", "UP")
                }

                override fun onItemSwipedDown() {
                    Log.e("SWIPE", "DOWN")
                }
            }) {
                override fun getAllowedSwipeDirectionsMovementFlags(viewHolder: ViewHolder): Int {
                    return ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                }
            }

        val itemTouchHelper = ItemTouchHelper(swappableTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rv_user)
        rv_user.layoutManager = SwipeableLayoutManager().setAngle(10)
            .setAnimationDuratuion(450)
            .setMaxShowCount(3)
            .setScaleGap(0.1f)
            .setTransYGap(0)
        adapter = UserItemAdapter(context)
        rv_user.adapter = adapter
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }
}