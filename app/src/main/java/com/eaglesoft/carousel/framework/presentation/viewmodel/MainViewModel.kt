package com.eaglesoft.carousel.framework.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.eaglesoft.carousel.business.domain.models.User
import com.eaglesoft.carousel.business.domain.state.DataState
import com.eaglesoft.carousel.business.interactors.GetUsers
import com.eaglesoft.carousel.framework.presentation.viewmodel.MainStateEvent.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val getUsers: GetUsers
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<User>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<User>>>
        get() = _dataState

    private val _favorite: MutableLiveData<DataState<User>> = MutableLiveData()
    val favorite: LiveData<DataState<User>>
        get() = _favorite


    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is GetUsersEvent -> {
                    getUsers.execute()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    fun addFavorite(mainStateEvent: MainStateEvent, user: User?) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is GetUsersEvent -> {
                    getUsers.addFavorite(user)
                        .onEach {
                            _favorite.value = it
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}


sealed class MainStateEvent {

    object GetUsersEvent : MainStateEvent()

    object None : MainStateEvent()
}


















