package com.superawesome.multiplatform.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.superawesome.multiplatform.R
import com.superawesome.multiplatform.adapter.TaskItemsAdapter
import com.superawesome.multiplatform.viewModel.CreateTaskViewModel
import com.superawesome.sharedcode.model.TaskItem
import com.superawesome.sharedcode.controller.TaskController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val controller = TaskController()

    // Click listener to delete a task when delete button is clicked
    private val onDeleteTaskClicked: (item: TaskItem) -> Unit = { event ->
        controller.removeTask(event.title)
        adapter.setData(controller.getTaskList())
    }
    private val adapter = TaskItemsAdapter(onDeleteTaskClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.init()
    }

    override fun onResume() {
        super.onResume()
        linearLayoutManager = LinearLayoutManager(this)
        initViews()
    }

    override fun onPause() {
        super.onPause()
        controller.storedTaskList()
    }

    fun initViews() {
        fab.setOnClickListener {
            val intent = Intent(this, CreateTaskActivity::class.java)
            startActivityForResult(intent, CREATE_TASK_REQUEST_CODE)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        taskList.layoutManager = linearLayoutManager
        taskList.adapter = adapter
        adapter.setData(controller.getTaskList())

        val dividerItemDecoration = DividerItemDecoration(
            taskList.context,
            LinearLayout.HORIZONTAL
        )
        taskList.addItemDecoration(dividerItemDecoration)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra(CreateTaskViewModel.TASK_RESULT)
            if (result != null && result.isNotEmpty()) {
                controller.addTask(TaskItem(result))
                adapter.setData(controller.getTaskList())
            }

        }
    }

    companion object {
        private const val CREATE_TASK_REQUEST_CODE = 123
    }
}