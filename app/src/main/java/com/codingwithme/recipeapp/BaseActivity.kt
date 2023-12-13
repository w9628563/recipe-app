package com.codingwithme.recipeapp

import android.app.job.JobInfo
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import pub.devrel.easypermissions.EasyPermissions
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(),CoroutineScope, EasyPermissions.PermissionCallbacks  {
    private lateinit var job: Job
    override val coroutineContext:CoroutineContext
    get() = job +Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        handlePermissionsResult(requestCode, grantResults)
    }

    open fun handlePermissionsResult(requestCode: Int, grantResults: IntArray) {
        // Handle permissions result in the base class
    }

}