package photodetail.vm

import base.ViewModelState

class PhotoDetailViewModel: ViewModelState<PhotoDetailState, PhotoDetailIntent, PhotoDetailNavigationEvents>(PhotoDetailState.DEFAULT) {

    override fun intent(intent: PhotoDetailIntent) {
        when(intent) {
            PhotoDetailIntent.GoBack -> {
                navigate { PhotoDetailNavigationEvents.GoBack }
            }
            PhotoDetailIntent.ShowError -> setState {
                copy(
                    state = PhotoDetailState.PhotoDetailScreenState.Error
                )
            }
            PhotoDetailIntent.ShowLoading -> setState {
                copy(
                    state = PhotoDetailState.PhotoDetailScreenState.Loading
                )
            }
            PhotoDetailIntent.ShowSuccess -> setState {
                copy(
                    state = PhotoDetailState.PhotoDetailScreenState.Success
                )
            }
        }
    }
}