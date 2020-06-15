package com.superawesome.sharedcode.controller

import com.netguru.kissme.Kissme
import com.superawesome.sharedcode.model.TaskItem

/**
 * This class is a controller fot the common logic of the app.
 * Here we manage the pp storage and the logic to add and remove an item from the task list.
 */
class TaskController {

    private lateinit var storage: Kissme
    private var taskList = arrayListOf<TaskItem>()

    /**
     * Initialize the viewModel, first we initialize the storage and then we fetch the stored list.
     */
    fun init() {
        storage = Kissme(name = "my_storage")
        fetchTaskList()
    }

    fun getTaskList() = taskList

    /**
     * Add a task to the task list
     * We check if the title is not empty, if it is empty we don't add that item.
     */
    fun addTask(item: TaskItem) {
        if (item.title.isNotEmpty()) {
            taskList.add(item)
        }
    }

    /**
     * Remove all the elements that have that specific [title]
     */
    fun removeTask(title: String) = taskList.removeAll { it.title == title }

    /**
     * Stored the To Do list in the phone storage
     */
    fun storedTaskList() = storage.putString(LIST_KEY, taskList.map { it.title }.joinToString())

    /**
     * Fetch the task list from the phone storage, we check if the listStored is not empty if it is
     * not empty we set the [taskList] with the values of the listStored, otherwise we return the
     * [taskList]
     */
    fun fetchTaskList(defaultValue: String = "") : List<TaskItem> {
        val listStored =  storage.getString(LIST_KEY, defaultValue) ?: ""
        if (listStored.isNotEmpty()) {
            taskList = listStored.split(",").map { TaskItem(it.trim()) } as ArrayList<TaskItem>
        }
        return taskList
    }

    companion object {
        private const val LIST_KEY = "LIST_KEY"
    }
}