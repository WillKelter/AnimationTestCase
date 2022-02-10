package com.willkelter.animationtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.willkelter.animationtest.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

private var data:Array<ButtonEntity> = arrayOf(
    ButtonEntity(R.drawable.ic_baseline_flight_24, "Flight",{}),
    ButtonEntity(R.drawable.ic_baseline_bluetooth_24, "Bluetooth") {
        binding.mainLayout.transitionToState(
            R.id.bluetooth
        )
    },
    ButtonEntity(R.drawable.ic_baseline_brightness_3_24, "Silent Mode",{}),
    ButtonEntity(R.drawable.ic_baseline_wifi_24, "Wi-fi",{}),
    ButtonEntity(R.drawable.ic_baseline_notifications_24, "Notifications",{}),
    ButtonEntity(R.drawable.ic_baseline_wifi_tethering_24, "Share",{})
)


class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener  {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val viewModel:MainActivityViewModel by viewModels()
        var decorator = SpacingItemDecoration(15)
        val adapter = RecyclerAdapter(data, viewModel, this)
        binding.folderRecycler.adapter = adapter
        binding.folderRecycler.layoutManager =object:GridLayoutManager(this, 2){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        binding.folderRecycler.addItemDecoration(decorator)
        binding.folderLayout.setOnClickListener {
            if(!viewModel.isOpened){
                viewModel.isOpened = true
                binding.mainLayout.transitionToEnd()
                binding.folderRecycler.startLayoutAnimation()

            } else {
                viewModel.isOpened = false
                binding.mainLayout.transitionToStart()
                binding.folderRecycler.startLayoutAnimation()

            }
        }
    }

    override fun onClick(position: Int) {
        data[position].onClick()

    }

}