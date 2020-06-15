package com.superawesome.multiplatform.view

import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.superawesome.multiplatform.R
import com.superawesome.multiplatform.databinding.ActivityCreateTaskBinding
import com.superawesome.multiplatform.viewModel.CreateTaskViewModel

class CreateTaskActivity : BaseActivity() {
    private lateinit var activityCreateEventBinding : ActivityCreateTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCreateEventBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_task)
        viewModel = ViewModelProviders.of(this)[CreateTaskViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribe()
    }

    override fun onResume() {
        super.onResume()
        activityCreateEventBinding.viewmodel = viewModel as CreateTaskViewModel
        activityCreateEventBinding.lifecycleOwner = this
        activityCreateEventBinding.executePendingBindings()
    }

    /**
     * If the user press on back button from the appBar we finish the activity.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}