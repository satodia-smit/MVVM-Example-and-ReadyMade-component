package com.hyperelement.mvvmdemo.ui.demobasicfragment.fragmenttwo

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.hyperelement.mvvmdemo.data.repository.FragmentOneRepository
import com.hyperelement.mvvmdemo.utilities.RootViewModel

private const val TAG = "FragmentTwoViewModel"

class FragmentTwoViewModel(
    private val fragmentARepository: FragmentOneRepository
) : RootViewModel() {
    val Str = "Printed"

    @SuppressLint("LogNotTimber")
    fun navigateToFragmentC(view: View) {
        Log.d(TAG, "navigateToFragmentB() called with: view = $view")
        Navigation.findNavController(view)
            .navigate(
                FragmentTwoDirections.actionFragmentTwoToFragmentThree()
            )
    }
}