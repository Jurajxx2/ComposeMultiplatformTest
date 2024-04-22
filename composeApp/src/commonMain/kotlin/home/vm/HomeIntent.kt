package home.vm

import base.Intent

sealed class HomeIntent: Intent {
    object ShowLoading: HomeIntent()
    object ShowError: HomeIntent()
    object ShowSuccess: HomeIntent()
    object GoToDetail: HomeIntent()
}

