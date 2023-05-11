package at.fhv.mme.bt_dementia_app.viewmodel

sealed class AddActivityResult {
    object Success : AddActivityResult()
    data class Error(val message: String) : AddActivityResult()
}

sealed class DeleteActivityResult {
    object Success : DeleteActivityResult()
    data class Error(val message: String) : DeleteActivityResult()
}