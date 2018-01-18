package easyfunart.easyfunart.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import easyfunart.easyfunart.Activity.TrackActivity
import easyfunart.easyfunart.Adapter.BookMarkAdapter
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
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
class BookMarkFragment: Fragment(), View.OnClickListener{


    private var networkService : NetworkService? = null
    private var bookMarkRecyclerView : RecyclerView?=null
    private var bookMarkData : ArrayList<Gallery2Data>?=null
    private var bookAdapter : BookMarkAdapter? = null
    private var requestManager : RequestManager? = null
    val TAG : String = "BookMarkFragment"


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.activity_bookmark, container, false)

        networkService = ApplicationController.instance!!.networkService

        bookMarkRecyclerView = v.findViewById(R.id.bookmark_recyclerView) as RecyclerView
        requestManager = Glide.with(this)

        bookMarkRecyclerView!!.layoutManager = LinearLayoutManager(context)

        bookMarkRecyclerView!!.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

        bookMarkData = ArrayList<Gallery2Data>()
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "1전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "2전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "3전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "4전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "5전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "6전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "7전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "8전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "9전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "10전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "11전시 제목입니당"))
//        bookMarkData!!.add(GalleryData(0,R.raw.jwh, "촤랄라 : 랄라살롱", "12전시 제목입니당"))
//        bookAdapter = BookMarkAdapter(bookMarkData!!,requestManager!!)
//        bookAdapter!!.setOnItemClickListener(this@BookMarkFragment)
//        bookMarkRecyclerView!!.adapter = bookAdapter

        getBookMark()

        return v
    }

    fun getBookMark(){

        val getBookMark = networkService!!.getBookMarkTab(CommonData.user_token!!)

        getBookMark.enqueue(object : Callback<Gallery2Response>{
            override fun onResponse(call: Call<Gallery2Response>?, response: Response<Gallery2Response>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){
                        Log.d(TAG,"통신 성공")
                        bookMarkData = response.body().data
                        bookAdapter = BookMarkAdapter(bookMarkData!!,requestManager!!)
                        bookAdapter!!.setOnItemClickListener(this@BookMarkFragment)
                        bookMarkRecyclerView!!.adapter = bookAdapter

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
        val idx : Int = bookMarkRecyclerView!!.getChildAdapterPosition(v)
        val intent = Intent(context, TrackActivity::class.java)
        intent.putExtra("exId", bookMarkData!!.get(idx).ex_id)
        intent.putExtra("exImage", bookMarkData!!.get(idx).ex_image)
        intent.putExtra("exTitle", bookMarkData!!.get(idx).ex_title)
        Log.d("exId",bookMarkData!!.get(idx).ex_id.toString())
        startActivity(intent)
//        activity.finish()
    }

}