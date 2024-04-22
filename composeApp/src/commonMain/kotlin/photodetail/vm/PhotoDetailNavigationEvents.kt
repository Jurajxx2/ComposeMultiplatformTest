package photodetail.vm

import base.NavigationEvent

sealed class PhotoDetailNavigationEvents : NavigationEvent {
    object GoBack : PhotoDetailNavigationEvents()
}
