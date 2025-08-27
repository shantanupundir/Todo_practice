package com.example.trail2

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trail2.Model.TodoViewModel
import com.example.trail2.databinding.ActivityMainBinding
import com.example.trail2.databinding.AddItemDialogBinding
import com.example.trail2.ui.CompletedFragment
import com.example.trail2.ui.TodoFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner=this
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        binding.btnTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.AddTask.setOnClickListener { showAddItemDialog() }

        if (savedInstanceState == null) {
            loadFragment(TodoFragment())
            binding.bottomNav.selectedItemId = R.id.nav_todo
        }
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_todo -> {
                    loadFragment(TodoFragment())
                    true
                }

                R.id.nav_completed -> {
                    loadFragment(CompletedFragment())
                    true
                }
                else -> false
            }
        }

    }
    private fun showAddItemDialog(){
        val dialogBinding= AddItemDialogBinding.inflate(LayoutInflater.from(this))
        val dialog= AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(true)
            .create()

        dialogBinding.btnCancel.setOnClickListener { dialog.dismiss() }
        dialogBinding.btnAdd.setOnClickListener {
            val title=dialogBinding.titleEdit.text.toString()
            val description=dialogBinding.descriptionEdit.text.toString()
            if (title.isEmpty()){
                Toast.makeText(this,"Please Enter the title", Toast.LENGTH_SHORT).show()
            }else{
                val task= TodoData(0,title,description,false)
                viewModel.insert(task)
                dialog.dismiss()
            }
        }
        dialog.show()
        dialog.window?.setDimAmount(0.8f)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}