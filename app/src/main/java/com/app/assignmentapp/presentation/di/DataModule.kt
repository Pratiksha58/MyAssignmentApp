package com.app.assignmentapp.presentation.di

import com.app.assignmentapp.data.Mapper.UserDataMapper
import com.app.assignmentapp.presentation.utils.Navigator
import com.app.assignmentapp.presentation.view.viewmodel.DataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PostModule = module {
    viewModel { DataViewModel(get()) }
    factory { UserDataMapper() }

    single { Navigator }
}