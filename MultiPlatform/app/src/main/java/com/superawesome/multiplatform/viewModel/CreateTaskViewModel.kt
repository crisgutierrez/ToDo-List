package com.superawesome.multiplatform.viewModel

import android.content.Intent
import androidx.databinding.ObservableField


class CreateTaskViewModel : BaseViewModel() {

    var task = ObservableField<String>()

    /**
     * Open CreateEventActivity.
     */
    fun onCreateNewTaskCreated() {
        val returnIntent = Intent()
        returnIntent.putExtra(TASK_RESULT, task.get())
        closeActivity(returnIntent)
    }

    companion object {
        const val TAG = "CreateTaskViewModel"
        const val TASK_RESULT = "TaskResult"
    }
}