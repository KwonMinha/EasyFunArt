package easyfunart.easyfunart.Fragment

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Activity.TrackActivity
import easyfunart.easyfunart.Adapter.SurroundingAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.GET.Gallery2Data
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.Gallery2Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by minha on 2018-01-12.
 */
class SurroundingFragment: Fragment(), View.OnClickListener{

    private var networkService : NetworkService? = null
    private var surroundingRecyclerView : RecyclerView?=null
    private var surroundingData : ArrayList<Gallery2Data>?=null
    private var surroundingAdapter : SurroundingAdapter? = null
    private var requestManager : RequestManager? = null
    private var locationManager: LocationManager? = null
    val TAG : String = "BookMarkFragment"
    //    private var latiitude : Float? = null
//    private var longtitude : Float? = null
    var lastKnownLocation: Location? = null
    var lm : LocationManager? = null
    var latitude : Double? =null
    var longitude : Double? =null
    var network_btn : Button? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.activity_bookmark, container, false)

        networkService = ApplicationController.instance!!.networkService
        surroundingRecyclerView = v.findViewById(R.id.bookmark_recyclerView) as RecyclerView
        requestManager = Glide.with(this)



//        network_btn = v.findViewById(R.id.network_btn) as Button


        surroundingRecyclerView!!.layoutManager = LinearLayoutManager(context)
        surroundingRecyclerView!!.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        surroundingRecyclerView!!.layoutManager = LinearLayoutManager(context)


        lm  = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        lm!!.removeUpdates(locationListener) // Stop the update if it is in progress.
        lm!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f,locationListener)
        lm!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10f,locationListener)




        surroundingData = ArrayList<Gallery2Data>()
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "정원희", "1전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "임종완", "2전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "김다혜", "3전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "홍주영", "4전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "김은영", "5전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "신예지", "6전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "정승후", "7전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "권민하", "8전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "박지윤", "9전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "이동수", "10전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "이혜진", "11전시 제목입니당"))
//        surroundingData!!.add(GalleryData(0,R.raw.jwh, "김지혜", "12전시 제목입니당"))

//        surroundingAdapter = SurroundingAdapter(surroundingData!!,requestManager!!)
//        surroundingAdapter!!.setOnItemClickListener(this)
//        surroundingRecyclerView!!.adapter = surroundingAdapter

//        network_btn!!.setOnClickListener{
//            getSurroundData()
//        }


        return v
    }

    var locationListener = object: LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("onLocationChanged","완료")

            lastKnownLocation = location

            latitude = lastKnownLocation!!.getLatitude()
            longitude = lastKnownLocation!!.getLongitude()
//            editTextLatitude.setText(""+lastKnownLocation!!.getLatitude())
//            editTextLongitude.setText(""+lastKnownLocation!!.getLongitude())



            Log.d("위도1",lastKnownLocation!!.getLatitude().toString())
            Log.d("경도1",lastKnownLocation!!.getLongitude().toString())
            Log.d("위도2",latitude.toString())
            Log.d("경도2",longitude.toString())

            lm!!.removeUpdates(this)
            getSurroundData()
        }

        override fun onProviderDisabled(provider:String) {
            Log.d("onProviderDisabled","완료")
        }
        override fun onProviderEnabled(provider:String) {
            Log.d("onProviderEnabled","완료")
        }
        override fun onStatusChanged(provider:String, status:Int, extras:Bundle) {
            Log.d("onStatusChanged","완료")
        }
    }

    fun getSurroundData(){

        Log.d("위도_통신",latitude.toString())
        Log.d("경도_통신",longitude.toString())

        val getSurroundData = networkService!!.getSurroundTab(latitude,longitude)

        getSurroundData.enqueue(object : Callback<Gallery2Response> {
            override fun onResponse(call: Call<Gallery2Response>?, response: Response<Gallery2Response>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){
                        Log.d(TAG,"통신 성공")

                        Log.d("asd", response.body().data.toString())
                        surroundingData = response.body().data
                        surroundingAdapter = SurroundingAdapter(surroundingData!!,requestManager!!)
                        surroundingAdapter!!.setOnItemClickListener(this@SurroundingFragment)
                        surroundingRecyclerView!!.adapter = surroundingAdapter

                    }else{
                        Log.d(TAG,"데이터 이상")
                    }
                }else{
                    Log.d(TAG,"데이터 이상")
                    ApplicationController.instance!!.makeToast("데이터 이상")

                }
            }

            override fun onFailure(call: Call<Gallery2Response>?, t: Throwable?) {
                Log.d(TAG,"통신 이상")
            }
        })
    }

    override fun onClick(v: View?) {
        val idx : Int = surroundingRecyclerView!!.getChildAdapterPosition(v)
        val intent = Intent(context, TrackActivity::class.java)
        intent.putExtra("ex_id", surroundingData!!.get(idx).ex_id)
        intent.putExtra("ex_id", surroundingData!!.get(idx).ex_image)
        startActivity(intent)
    }
}