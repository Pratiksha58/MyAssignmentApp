package com.app.assignmentapp.presentation.utils

import android.content.Context
import com.app.assignmentapp.presentation.view.activities.MainActivity

object Navigator {

        fun navigateToMainActivity(
            context: Context
        ) {
            context.startActivity(MainActivity.getCallingIntent(context))
        }
}