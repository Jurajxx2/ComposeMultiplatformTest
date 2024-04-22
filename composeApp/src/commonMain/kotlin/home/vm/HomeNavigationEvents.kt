package home.vm

import base.NavigationEvent

sealed class HomeNavigationEvents : NavigationEvent {
    data object GoToPhotoDetail : HomeNavigationEvents()
}
