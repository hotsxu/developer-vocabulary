package com.hotsx.vocabulary

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.hotsx.app.log
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application)
                .create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializeView()
        initializeSubscribe()
        initializePermissions()
    }

    private fun initializeView() {
        button.setOnClickListener {
            viewModel.translation("google")
        }
    }

    private fun initializeSubscribe() {
        viewModel.getTranslation().observeForever {

        }
    }

    private fun initializePermissions() {
        /**权限检查*/
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            /**权限申请*/
            EasyPermissions.requestPermissions(this, "软件需要存储音频文件~", 1, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        log("Denied:$perms")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        log("Granted:$perms")
    }
}
