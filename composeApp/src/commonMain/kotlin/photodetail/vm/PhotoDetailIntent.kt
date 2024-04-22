package photodetail.vm

import base.Intent

sealed class PhotoDetailIntent : Intent {
    object ShowLoading : PhotoDetailIntent()
    object ShowError : PhotoDetailIntent()
    object ShowSuccess : PhotoDetailIntent()
    object GoBack : PhotoDetailIntent()
}