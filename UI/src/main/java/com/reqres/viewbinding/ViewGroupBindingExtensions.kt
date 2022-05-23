package com.reqres.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> ViewGroup.viewBinding(
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T
) = viewBindingFactory(LayoutInflater.from(context), this, false)