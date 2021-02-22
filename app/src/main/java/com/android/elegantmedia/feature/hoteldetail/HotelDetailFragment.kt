package com.android.elegantmedia.feature.hoteldetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.elegantmedia.R
import com.android.elegantmedia.databinding.HotelDetailFragmentBinding
import com.android.elegantmedia.feature.hotelList.models.Hotel

class HotelDetailFragment : Fragment() {

    private lateinit var viewModel: HotelDetailViewModel
    val args: HotelDetailFragmentArgs by navArgs()
    private lateinit var binding: HotelDetailFragmentBinding
    lateinit var hotel: Hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            HotelDetailFragmentFactory()
        ).get(HotelDetailViewModel::class.java)

        //enable option menu
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = HotelDetailFragmentBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        viewModel.hotel = args.selectedHotel

        //get data from the nav args
        hotel = args.selectedHotel

        binding.setLifecycleOwner(this)

        return binding.root
    }

    /**
     *  menu Item click handling
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_location -> {
                val newAction =
                    HotelDetailFragmentDirections.actionHotelDetailFragmentToMapsActivity(
                        hotel
                    )
                findNavController().navigate(newAction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}