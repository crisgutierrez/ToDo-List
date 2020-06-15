package com.superawesome.multiplatform

import com.superawesome.sharedcode.model.TaskItem
import com.superawesome.sharedcode.controller.TaskController
import org.junit.Test

import org.junit.Assert.*

/**
 * Here we test each method of the class that contains all the login of the app, in this case is
 * TaskController class that is in the common section of the app.
 */
class TaskControllerUnitTest {
    private val controller = TaskController()

    @Test
    fun getTaskListTest() {
        val testItem = TaskItem("Test")
        controller.addTask(testItem)
        val list = controller.getTaskList()
        assertEquals(list.size, 1)
        val testItem2 = TaskItem("Test2")
        controller.addTask(testItem2)
        val list2 = controller.getTaskList()
        assertEquals(list2.size, 2)
    }

    @Test
    fun addTaskTest() {
        val testItem = TaskItem("Test")
        controller.addTask(testItem)
        val list = controller.getTaskList()
        assertEquals(list.size, 1)
        assertEquals(list[0].title, testItem.title)
    }

    @Test
    fun removeTaskTest() {
        val testItem = TaskItem("Test")
        val testItem2 = TaskItem("Test2")
        controller.addTask(testItem)
        controller.addTask(testItem2)
        controller.removeTask(testItem.title)
        val list = controller.getTaskList()
        assertEquals(list.size, 1)
        assertEquals(list[0].title, testItem2.title)
    }
}
