package ru.sumin.coroutinestart

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.sumin.coroutinestart.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        loadCity {
            binding.tvLocation.text = it
            loadTemperature(it) {
                binding.tvTemperature.text = it.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
        }
    }

    private fun loadCity(callback: (String) -> Unit) {
        thread{
            Thread.sleep(5000)
                //1-й вариант
//            handler.post {
//                callback("Moscow")
//            }
//            Handler(Looper.getMainLooper()).post (Runnable {
//                callback("Moscow")
//            })
            //2-й вариант
//            Handler(Looper.getMainLooper()).post {
//                callback("Moscow")
//            }
            //3-й вариант
            runOnUiThread {
                callback("Moscow")
            }
        }
    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
        thread {
            //1-й вариант
//            handler.post {
//                Toast.makeText(
//                    this,
//                    getString(R.string.loading_temperature_toast, city),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
            //2-й вариант
//            Handler(Looper.getMainLooper()).post {
//                Toast.makeText(
//                    this,
//                    getString(R.string.loading_temperature_toast, city),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
            //3-й вариант
            runOnUiThread {
                Toast.makeText(
                    this,
                    getString(R.string.loading_temperature_toast, city),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Thread.sleep(5000)

//            handler.post {
//                callback(17)
//            }

//            Handler(Looper.getMainLooper()).post {
//                callback(17)
//            }

            runOnUiThread {
                callback(17)
            }
        }
    }
}
