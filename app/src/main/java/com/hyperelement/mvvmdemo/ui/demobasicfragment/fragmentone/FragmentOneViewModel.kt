package com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmentone

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.mvvmdemo.utilities.RootViewModel

private const val TAG = "FragmentOneViewModel"

class FragmentOneViewModel(
    private val fragmentOneRepository: FragmentOneRepository
) : RootViewModel() {
    val Str = "Printed"

    @SuppressLint("LogNotTimber")
    fun navigateToFragmentB(view: View) {
        Log.d(TAG, "navigateToFragmentB() called with: view = $view")
        Navigation.findNavController(view)
            .navigate(
                FragmentOneDirections.actionFragmentOneToFragmentTwo2()
            )
    }
}