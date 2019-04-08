package com.example.wan_android.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

open class BaseFragment : Fragment() {
    protected lateinit var mActivity: AppCompatActivity
    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as AppCompatActivity
    }

    fun navigate(actionId: Int) {
        this.navigate(actionId, null)
    }

    fun navigate(actionId: Int, bundle: Bundle?) {
        kotlin.runCatching {
            NavHostFragment.findNavController(this).navigate(actionId, bundle)
        }
    }

    fun findNavController(): NavController {
        return NavHostFragment.findNavController(this)
    }
}