package easyfunart.easyfunart.Fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.GalleryResponse
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minha on 2018-01-12.
 */
class GalleryFragment : Fragment(), OnMapReadyCallback {
    val TAG: String = " LOG::GalleryFragment"
    var mapView: MapView? = null
    var lat: Double? = null //통신을 통해서 위도 값 받는다.
    var lon: Double? = null //통신을 통해서 경도 값 받는다.

    private var networkService: NetworkService? = null
    private var requestManager : RequestManager? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_gallery, container, false)

        Log.v(TAG, "onCreateView")

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        getGalleryList()

        mapView = v.findViewById(R.id.map)

        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)

        return v
    }

    fun getGalleryList() {
        val getGalleryList = networkService!!.getGalleryList(arguments.getInt("gallery_id"))
        getGalleryList.enqueue(object : Callback<GalleryResponse> {
            override fun onFailure(call: Call<GalleryResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.d(TAG, "통신실패")
            }

            override fun onResponse(call: Call<GalleryResponse>?, response: Response<GalleryResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d(TAG, "성공1")
                    if (response.body().status.equals("success")){
                        Log.d(TAG, "성공2")
                        lat = response.body().data.latitude.toDouble()
                        lon = response.body().data.longitude.toDouble()
                        date.text = response.body().data.runtime
                        phone.text = response.body().data.phone
                        address.text = response.body().data.address
                    }
                }
                else {
                    Log.d(TAG, "실패")
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        // mapView!!.onResume()
        Log.v(TAG, "onResume")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.v(TAG, "onAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
        Log.v(TAG, "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause")
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v(TAG, "onViewCreated")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v(TAG, "onDetach")
        // mapView!!.onLowMemory()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        //서버에서 받은 위도 경도 두개의 매개변수로 넣어주면 끝!
        if (lat == null)
            lat = 37.5809
        else if (lon == null)
            lon = 126.98
        else {
            val SEOUL = LatLng(37.58, 126.98)

            val markerOptions = MarkerOptions()
            markerOptions.position(SEOUL)
            //미술관 위치를 보여줍니다.
            var place = view!!.address.text.toString()
            markerOptions.title(place)
            //markerOptions.snippet("수도")
            googleMap.addMarker(markerOptions)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(13f))//

            view!!.copy_address.setOnClickListener {
                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val text: String
                text = view!!.address.text.toString()
                val clipData = ClipData.newPlainText("label", text)
                clipboardManager.setPrimaryClip(clipData)
                val toast = Toast.makeText(context, "주소가 복사되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}