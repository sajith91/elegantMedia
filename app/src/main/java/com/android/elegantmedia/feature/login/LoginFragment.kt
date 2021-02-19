package com.android.elegantmedia.feature.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.elegantmedia.BuildConfig
import com.android.elegantmedia.R
import com.android.elegantmedia.databinding.LoginFragmentBinding
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    lateinit var callbackManager: CallbackManager
    private lateinit var bi: LoginFragmentBinding

    var name = ""
    var email = ""
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[LoginViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = LoginFragmentBinding.inflate(inflater, container, false)

        callbackManager = CallbackManager.Factory.create()

        bi.facebookLoginBtn.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile", "email"))
        }

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("TAG", "Success Login")
                    getUserProfile(loginResult?.accessToken, loginResult?.accessToken?.userId)
                }

                override fun onCancel() {
                    // Toast.makeText(this@LoginFragment, "Login Cancelled", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: FacebookException) {
                    //  Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
                }
            })


        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    @SuppressLint("LongLogTag")
    fun getUserProfile(token: AccessToken?, userId: String?) {

        val parameters = Bundle()
        parameters.putString(
            "fields",
            " name, email"
        )
        GraphRequest(token,
            "/$userId/",
            parameters,
            HttpMethod.GET,
            GraphRequest.Callback { response ->
                val jsonObject = response.jsonObject

                // Facebook Access Token
                // You can see Access Token only in Debug mode.
                // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.
                if (BuildConfig.DEBUG) {
                    FacebookSdk.setIsDebugEnabled(true)
                    FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
                }


//                // Facebook Id
//                if (jsonObject.has("id")) {
//                    val facebookId = jsonObject.getString("id")
//                    Log.i("Facebook Id: ", facebookId.toString())
//                    id = facebookId.toString()
//                } else {
//                    Log.i("Facebook Id: ", "Not exists")
//                    id = "Not exists"
//                }


//                // Facebook First Name
//                if (jsonObject.has("first_name")) {
//                    val facebookFirstName = jsonObject.getString("first_name")
//                    Log.i("Facebook First Name: ", facebookFirstName)
//                    firstName = facebookFirstName
//                } else {
//                    Log.i("Facebook First Name: ", "Not exists")
//                    firstName = "Not exists"
//                }
//
//
//                // Facebook Middle Name
//                if (jsonObject.has("middle_name")) {
//                    val facebookMiddleName = jsonObject.getString("middle_name")
//                    Log.i("Facebook Middle Name: ", facebookMiddleName)
//                    middleName = facebookMiddleName
//                } else {
//                    Log.i("Facebook Middle Name: ", "Not exists")
//                    middleName = "Not exists"
//                }
//
//
//                // Facebook Last Name
//                if (jsonObject.has("last_name")) {
//                    val facebookLastName = jsonObject.getString("last_name")
//                    Log.i("Facebook Last Name: ", facebookLastName)
//                    lastName = facebookLastName
//                } else {
//                    Log.i("Facebook Last Name: ", "Not exists")
//                    lastName = "Not exists"
//                }


                /***
                 * get Facebook name
                 */


                /***
                 * get Facebook name
                 */
                if (jsonObject.has("name")) {
                    val facebookName = jsonObject.getString("name")
                    Log.i("Facebook Name: ", facebookName)
                    name = facebookName
                } else {
                    Log.i("Facebook Name: ", "Not exists")
                    name = "Not exists"
                }


                /***
                 * get Face book email
                 */


                /***
                 * get Face book email
                 */
                if (jsonObject.has("email")) {
                    val facebookEmail = jsonObject.getString("email")
                    Log.i("Facebook Email: ", facebookEmail)
                    email = facebookEmail
                } else {
                    Log.i("Facebook Email: ", "Not exists")
                    email = "Not exists"
                }

                navigateToHotelListFragment()
            }).executeAsync()
    }

    private fun navigateToHotelListFragment() {
        val newAction = LoginFragmentDirections.actionLoginFragmentToHotelListFragment(name, email)
        findNavController().navigate(newAction)
    }

}