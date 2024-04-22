package home.vm

import base.ViewModelState

class HomeViewModel: ViewModelState<HomeState, HomeIntent, HomeNavigationEvents>(HomeState.DEFAULT) {

    override fun intent(intent: HomeIntent) {
        when(intent) {
            HomeIntent.GoToDetail -> {
                navigate { HomeNavigationEvents.GoToPhotoDetail }
            }
            HomeIntent.ShowError -> setState {
                copy(
                    state = HomeState.HomeScreenState.Error
                )
            }
            HomeIntent.ShowLoading -> setState {
                copy(
                    state = HomeState.HomeScreenState.Loading
                )
            }
            HomeIntent.ShowSuccess -> setState {
                copy(
                    state = HomeState.HomeScreenState.Success
                )
            }
        }
    }
}