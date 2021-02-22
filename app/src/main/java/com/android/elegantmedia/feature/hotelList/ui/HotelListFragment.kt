package com.android.elegantmedia.feature.hotelList.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.elegantmedia.databinding.HotelListFragmentBinding

import com.android.elegantmedia.feature.hotelList.viewModel.ViewModelFactory
import com.android.elegantmedia.feature.hotelList.models.Hotel

import com.android.elegantmedia.feature.hotelList.viewModel.HotelListViewModel
import com.android.elegantmedia.networkclass.api.ApiHelper
import com.android.elegantmedia.networkclass.api.RetrofitBuilder
import com.android.elegantmedia.util.Status
import com.android.elegantmedia.util.adapter.ItemClickListener
import com.android.elegantmedia.util.adapter.HotelListAdapter
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.hotel_list_fragment.*

class HotelListFragment : Fragment() {

    val args: HotelListFragmentArgs by navArgs()

    private lateinit var viewModel: HotelListViewModel
    private lateinit var binding: HotelListFragmentBinding
    //private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService)
            )
        ).get(HotelListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = HotelListFragmentBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        viewModel.userName = args.name
        viewModel.userEmail = args.email
        val adapter = HotelListAdapter(ItemClickListener { hotel ->
            navigateToDetailFragment(hotel)
        });
        val manager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = manager

        binding.setLifecycleOwner(this)

        /***
         * observe for changes in hotel list
         */
        viewModel.getHotels().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                            adapter.submitList(users.data)
                        }
                        Toast.makeText(context, "SUCCESS", Toast.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        // Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        print("ERROR = > ")
                        Toast.makeText(context, "ERROR", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        print("LOADING = > ")
                        Toast.makeText(context, "LOADING", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        /**
         * logout button click handler
         */
        binding.logoutButton.setOnClickListener {
            if (AccessToken.getCurrentAccessToken() != null) {
                GraphRequest(
                    AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE,
                    GraphRequest.Callback {
                        AccessToken.setCurrentAccessToken(null)
                        LoginManager.getInstance().logOut()
                        findNavController().navigate(HotelListFragmentDirections.actionHotelListFragmentToLoginFragment())
                    }
                ).executeAsync()
            }
        }

        return binding.root
    }



    //navigate to detail fragment
    private fun navigateToDetailFragment(hotel: Hotel) {
        val newAction =
            HotelListFragmentDirections.actionHotelListFragmentToHotelDetailFragment(hotel)
        findNavController().navigate(newAction)
    }


}