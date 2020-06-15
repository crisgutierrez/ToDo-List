package com.superawesome.multiplatform.adapter

import android.graphics.Paint
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.superawesome.multiplatform.R
import com.superawesome.multiplatform.utils.inflate
import com.superawesome.sharedcode.model.TaskItem
import kotlinx.android.synthetic.main.task_item.view.*

class TaskItemsAdapter(private val onDeleteTaskClickedListener: (event: TaskItem) -> Unit) :
    RecyclerView.Adapter<TaskItemsAdapter.TaskHolder>()  {
    private var taskList = listOf<TaskItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder {
        val inflatedView = parent.inflate(R.layout.task_item, false)
        return TaskHolder(inflatedView, onDeleteTaskClickedListener)

    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val eventItem = taskList[position]
        holder.bindBalance(eventItem)

    }

    fun setData(list: List<TaskItem>) {
        taskList = list
        notifyDataSetChanged()
    }



    class TaskHolder(v: View, private val onDeleteTaskClickedListener: (event: TaskItem) -> Unit) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private lateinit var event: TaskItem

        init {
            v.deleteTask.setOnClickListener(this)
            v.taskItem.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            when {
                v is ImageButton -> {
                    onDeleteTaskClickedListener.invoke(event)
                }
                v.taskItem.isChecked -> {
                    v.taskItem.setTypeface(v.taskItem.typeface, Typeface.ITALIC)
                    v.taskItem.paintFlags = v.taskItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                else -> {
                    v.taskItem.setTypeface(null, Typeface.NORMAL)
                    v.taskItem.paintFlags = Paint.LINEAR_TEXT_FLAG
                }
            }
        }

        fun bindBalance(event: TaskItem) {
            this.event = event
            view.taskItem.text = event.title
        }
    }

}
