package com.catalogapptest.ui.listeners

import android.view.View

interface ItemListener {

    fun onClick(model: Any?, view: View, position: Int)
}