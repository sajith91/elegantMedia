package com.android.elegantmedia.feature.hoteldetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.elegantmedia.R
import com.android.elegantmedia.databinding.HotelDetailFragmentBinding
import com.android.elegantmedia.databinding.HotelListFragmentBinding
import com.android.elegantmedia.feature.base.ViewModelFactory
import com.android.elegantmedia.feature.hotelList.models.Hotel
import com.android.elegantmedia.feature.hotelList.ui.HotelListFragmentArgs
import com.android.elegantmedia.feature.hotelList.ui.HotelListFragmentDirections
import com.android.elegantmedia.feature.hotelList.viewModel.HotelListViewModel
import com.android.elegantmedia.networkclass.api.ApiHelper
import com.android.elegantmedia.networkclass.api.RetrofitBuilder
import com.android.elegantmedia.util.Status
import com.android.elegantmedia.util.adapter.ItemClickListener
import com.android.elegantmedia.util.adapter.MainAdapter
import kotlinx.android.synthetic.main.hotel_detail_fragment.*
import kotlinx.android.synthetic.main.hotel_list_fragment.*

class HotelDetailFragment : Fragment() {

    private lateinit var viewModel: HotelDetailViewModel
    val args: HotelDetailFragmentArgs by navArgs()
    private lateinit var binding : HotelDetailFragmentBinding
   lateinit var hotel : Hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            HotelDetailFragmentFactory()
        ).get(HotelDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = HotelDetailFragmentBinding.inflate(inflater, container, false)

        binding.viewModel= viewModel
        viewModel.hotel = args.selectedHotel
         hotel = args.selectedHotel
        binding.setLifecycleOwner(this)

       binding. pinIcon.setOnClickListener {
//            val newAction = HotelDetailFragmentDirections.actionHotelDetailFragmentToLocationFragment(hotel?.latitude.toFloat(),
//            hotel?.longitude.toFloat())

           val newAction = HotelDetailFragmentDirections.actionHotelDetailFragmentToMapsActivity(hotel?.latitude.toFloat(),
               hotel?.longitude.toFloat())
            findNavController().navigate(newAction)
        }

        return  binding.root
    }

}