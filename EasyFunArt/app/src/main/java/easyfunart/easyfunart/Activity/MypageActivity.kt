package easyfunart.easyfunart.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import de.hdodenhof.circleimageview.CircleImageView
import easyfunart.easyfunart.Adapter.MyinfoAdapter
import easyfunart.easyfunart.Adapter.ReviewAdapter2
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.Dialog.PopupDialog
import easyfunart.easyfunart.GET.MyPageUserLikeData
import easyfunart.easyfunart.GET.MyPageUserReviewData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.PUT.MyPageNickModiPut
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.MyPageNickModiResponse
import easyfunart.easyfunart.Response.MyPageProfileModiResponse
import easyfunart.easyfunart.Response.MyPageResponse
import kotlinx.android.synthetic.main.activity_mypage.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MypageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    override fun attachBaseContext(newBase : Context){
        //super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

   // var token : String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjkwLCJ1c2VyTGV2ZWwiOjUwLCJpYXQiOjE1MTU2NjQ4MjEsImV4cCI6MTUyNDMwNDgyMSwiaXNzIjoiRWFzeUZ1bkFydCJ9.BLcg7erablQSztZPV4PfOyyJyQYC3zzJfUHur6qVkQg"
    var getText: TextView? = null
    var getLikeText : TextView? = null
    var getReviewText : TextView? = null
    var getStarText : TextView? = null

    var imageView4_1 : ImageView? = null

    var getImage : CircleImageView? = null

    var lessTagButton : ImageButton? = null
    var moreTagButton : ImageButton? = null
    var moreView : LinearLayout? = null

    var settingButton: ImageButton? = null
    var analyzeButton: ImageButton? = null
    var picchooseButton: ImageButton? = null
    var changenameButton: ImageButton? = null

    private var myinfoList: RecyclerView? = null
    private var myinfoDatas: ArrayList<MyPageUserLikeData>? = null
    private var adapter: MyinfoAdapter? = null
    private var popDialog: PopupDialog? = null


    private var iv_UserPhoto: ImageView? = null
    private var absoultePath: String? = null

    companion object {
        private val PICK_FROM_ALBUM = 1
        private val CROP_FROM_iMAGE = 2
    }

    private var mImageCaptureUri: Uri? = null
    private var networkService: NetworkService? = null
    private var requestManager : RequestManager? = null

    private var editProfile : MultipartBody.Part? = null

    private var imageFile : File? = null

    fun doTakeAlbumAction() // 앨범에서 이미지 가져오기
    {
        // 앨범 호출
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    private fun storeCropImage(bitmap: Bitmap, filePath: String) {
        // SmartWheel 폴더를 생성하여 이미지를 저장하는 방식이다.
        val dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel"
        val directory_SmartWheel = File(dirPath)
        if (!directory_SmartWheel.exists())
        // SmartWheel 디렉터리에 폴더가 없다면 (새로 이미지를 저장할 경우에 속한다.)
            directory_SmartWheel.mkdir()
        val copyFile = File(filePath)
        var out: BufferedOutputStream? = null
        var baos : ByteArrayOutputStream? = null
        try {
            copyFile.createNewFile()
            out = BufferedOutputStream(FileOutputStream(copyFile))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            // sendBroadcast를 통해 Crop된 사진을 앨범에 보이도록 갱신한다.
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(copyFile)))
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos!!.toByteArray())

        editProfile = MultipartBody.Part.createFormData("image", "name", photoBody)

        // 프로필사진 바꾸기
        val putMyPageProfileResponse = networkService!!.putMyPageProfileModi(CommonData.user_token!!,
                editProfile!!)


        putMyPageProfileResponse.enqueue(object : Callback<MyPageProfileModiResponse> {
            override fun onResponse(call: Call<MyPageProfileModiResponse>?, response: Response<MyPageProfileModiResponse>?) {
                if (response!!.isSuccessful) {
                    if (response!!.body().status == "success") {
                        ApplicationController.instance!!.makeToast("수정 완료")
                        Log.v("tongsin","success")
                    }
                }
                Log.v("tongsin", "not successful")
            }
            override fun onFailure(call: Call<MyPageProfileModiResponse>?, t: Throwable?) {
                //응답 실패
                ApplicationController.instance!!.makeToast("통신 확인")
                Log.v("tongsin","fail")
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK)
            return
        when (requestCode) {
            PICK_FROM_ALBUM -> {
                if(data == null){
                    Log.v("main", "inRWhen")
                    return
                }

                run({
                    // 이후의 처리가 카메라와 같으므로 일단 break없이 진행합니다.
                    // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.
                    mImageCaptureUri = data.getData()
                    Log.d("SmartWheel", mImageCaptureUri!!.getPath().toString())
                })
                run({
                    // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                    // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.
                    val intent = Intent("com.android.camera.action.CROP")
                    intent.setDataAndType(mImageCaptureUri, "image/*")
                    // CROP할 이미지를 200*200 크기로 저장
                    intent.putExtra("outputX", 200) // CROP한 이미지의 x축 크기
                    intent.putExtra("outputY", 200) // CROP한 이미지의 y축 크기
                    intent.putExtra("aspectX", 1) // CROP 박스의 X축 비율
                    intent.putExtra("aspectY", 1) // CROP 박스의 Y축 비율
                    intent.putExtra("scale", true)
                    intent.putExtra("return-data", true)

                    startActivityForResult(intent, CROP_FROM_iMAGE) // CROP_FROM_CAMERA case문 이동
                })
            }
            CROP_FROM_iMAGE -> {

                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                if (resultCode != RESULT_OK) {
                    return
                }

                val extras = data!!.getExtras()
                // CROP된 이미지를 저장하기 위한 FILE 경로
                val filePath = (Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/SmartWheel/" + System.currentTimeMillis() + ".jpg")
                if (extras != null) {
                    val photo = extras.getParcelable("data") as Bitmap // CROP된 BITMAP
                    iv_UserPhoto!!.setImageBitmap(photo) // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                    storeCropImage(photo, filePath) // CROP된 이미지를 외부저장소, 앨범에 저장한다.
                    absoultePath = filePath
                }
                // 임시 파일 삭제
                val f = File(mImageCaptureUri!!.getPath())
                if (f.exists()) {
                    f.delete()
                }
            }
        }
        if(data == null){
            Log.v("main", "outWhen")

            return
        }
//        imageFile = File(mImageCaptureUri!!.path)
//        Log.d("데이터",data.toString())
    }

    private var reviewList: RecyclerView? = null
    private var reviewDatas: ArrayList<MyPageUserReviewData>? = null
    private var reviewAdapter: ReviewAdapter2? = null
    private var preFerImg : HashMap<Int, Int> = HashMap()
    private var imageArray : ArrayList<ImageView> = ArrayList()
    private var imageIndex = 0

    fun createHash(){
        preFerImg.put(0, R.drawable.mypage_preference_place_1)
        preFerImg.put(1, R.drawable.mypage_preference_place_2)
        preFerImg.put(2, R.drawable.mypage_preference_place_3)
        preFerImg.put(3, R.drawable.mypage_preference_place_4)
        preFerImg.put(4, R.drawable.mypage_preference_place_5)
        preFerImg.put(5, R.drawable.mypage_preference_place_6)
        preFerImg.put(6, R.drawable.mypage_preference_place_7)
        preFerImg.put(7, R.drawable.mypage_preference_place_8)
        preFerImg.put(8, R.drawable.mypage_preference_place_9)


        preFerImg.put(9, R.drawable.mypage_preference_genre_1)
        preFerImg.put(10, R.drawable.mypage_preference_genre_2)
        preFerImg.put(11, R.drawable.mypage_preference_genre_3)
        preFerImg.put(12, R.drawable.mypage_preference_genre_4)
        preFerImg.put(13, R.drawable.mypage_preference_genre_5)
        preFerImg.put(14, R.drawable.mypage_preference_genre_6)
        preFerImg.put(15, R.drawable.mypage_preference_genre_7)
        preFerImg.put(16, R.drawable.mypage_preference_genre_8)
        preFerImg.put(17, R.drawable.mypage_preference_genre_9)
        preFerImg.put(18, R.drawable.mypage_preference_genre_10)
        preFerImg.put(19, R.drawable.mypage_preference_genre_11)
        preFerImg.put(20, R.drawable.mypage_preference_genre_12)

        preFerImg.put(21, R.drawable.mypage_preference_love_1)
        preFerImg.put(22, R.drawable.mypage_preference_love_2)
        preFerImg.put(23, R.drawable.mypage_preference_love_3)
        preFerImg.put(24, R.drawable.mypage_preference_love_4)
        preFerImg.put(25, R.drawable.mypage_preference_love_5)
        preFerImg.put(26, R.drawable.mypage_preference_love_6)
        preFerImg.put(27, R.drawable.mypage_preference_love_7)
        preFerImg.put(28, R.drawable.mypage_preference_love_8)
        preFerImg.put(29, R.drawable.mypage_preference_love_9)
        preFerImg.put(30, R.drawable.mypage_preference_love_10)
        preFerImg.put(31, R.drawable.mypage_preference_love_11)

        preFerImg.put(32, R.drawable.mypage_preference_subject_1)
        preFerImg.put(33, R.drawable.mypage_preference_subject_2)
        preFerImg.put(34, R.drawable.mypage_preference_subject_3)
        preFerImg.put(35, R.drawable.mypage_preference_subject_4)
        preFerImg.put(36, R.drawable.mypage_preference_subject_5)
        preFerImg.put(37, R.drawable.mypage_preference_subject_6)
        preFerImg.put(38, R.drawable.mypage_preference_subject_7)
        preFerImg.put(39, R.drawable.mypage_preference_subject_8)
        preFerImg.put(40, R.drawable.mypage_preference_subject_9)
        preFerImg.put(41, R.drawable.mypage_preference_subject_10)
        preFerImg.put(42, R.drawable.mypage_preference_subject_11)
        preFerImg.put(43, R.drawable.mypage_preference_subject_12)



        imageArray.add(line1_1)
        imageArray.add(line1_2)
        imageArray.add(line1_3)
        imageArray.add(line1_4)
        imageArray.add(line2_1)
        imageArray.add(line2_2)
        imageArray.add(line2_3)
        imageArray.add(line2_4)
        imageArray.add(line3_1)
        imageArray.add(line3_2)
        imageArray.add(line3_3)
        imageArray.add(line3_4)

        imageArray.add(line4_1)
        imageArray.add(line4_2)
        imageArray.add(line4_3)
        imageArray.add(line4_4)

        imageArray.add(line5_1)
        imageArray.add(line5_2)
        imageArray.add(line5_3)
        imageArray.add(line5_4)

        imageArray.add(line6_1)
        imageArray.add(line6_2)
        imageArray.add(line6_3)
        imageArray.add(line6_4)

        imageArray.add(line7_1)
        imageArray.add(line7_2)
        imageArray.add(line7_3)
        imageArray.add(line7_4)

        imageArray.add(line8_1)
        imageArray.add(line8_2)
        imageArray.add(line8_3)
        imageArray.add(line8_4)

        imageArray.add(line9_1)
        imageArray.add(line9_2)
        imageArray.add(line9_3)
        imageArray.add(line9_4)

        imageArray.add(line10_1)
        imageArray.add(line10_2)
        imageArray.add(line10_3)
        imageArray.add(line10_4)

        imageArray.add(line11_1)
        imageArray.add(line11_2)
        imageArray.add(line11_3)
        imageArray.add(line11_4)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        createHash()
        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)


        iv_UserPhoto = findViewById(R.id.user_picture) as ImageView

        myinfoList = findViewById(R.id.myinfo_list) as RecyclerView
        myinfoList!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)




        reviewList = findViewById(R.id.review_list) as RecyclerView
        reviewList!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        reviewList!!.layoutManager = LinearLayoutManager(this)


        imageView4_1 = findViewById(R.id.line4_1) as ImageView

        moreTagButton = findViewById(R.id.more_tag) as ImageButton
        moreView = findViewById(R.id.more_view) as LinearLayout

        lessTagButton = findViewById(R.id.less_tag) as ImageButton


        moreTagButton!!.setOnClickListener{
            moreView!!.visibility=View.VISIBLE
            moreTagButton!!.visibility=View.GONE
            lessTagButton!!.visibility = View.VISIBLE
        }
        lessTagButton!!.setOnClickListener{
            moreView!!.visibility=View.GONE
            moreTagButton!!.visibility=View.VISIBLE
            lessTagButton!!.visibility=View.GONE
        }

        settingButton = findViewById(R.id.setting_btn) as ImageButton
        analyzeButton = findViewById(R.id.analyze_btn) as ImageButton
        picchooseButton = findViewById(R.id.picchoose_btn) as ImageButton
        changenameButton = findViewById(R.id.changeName_btn) as ImageButton

        Log.v("main", "onCreate")
        settingButton!!.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        analyzeButton!!.setOnClickListener {
            var intent = Intent(this, AnalyzeActivity::class.java)
            startActivity(intent)
        }

        picchooseButton!!.setOnClickListener {
            doTakeAlbumAction()
        }

        changenameButton!!.setOnClickListener {
            floatDialog()
        }


        // 마이페이지 불러오기
        val getMyPageResponse = networkService!!.getMyPage(CommonData.user_token!!)
        getMyPageResponse.enqueue(object : Callback<MyPageResponse> {
            override fun onResponse(call: Call<MyPageResponse>?, response: Response<MyPageResponse>?) {
                if (response!!.isSuccessful) {
                    if (response!!.body().status == "success") {
                        //데이터를 성공적으로 받았을 때 할 일
                        //받은 데이터를 가지고 뷰에 붙이기.
                        Log.d("받아오기","완료")
                        //Log.v("데이터", response!!.body().data.user_Grade_Count.toString())

                        getText = findViewById(R.id.user_name) as TextView
                        getText!!.text = response!!.body().data.user_data.user_nickname

                        getLikeText = findViewById(R.id.like_count) as TextView
                        getLikeText!!.text = response!!.body().data.user_Like_Count.toString()

                        getReviewText = findViewById(R.id.review_count) as TextView
                        getReviewText!!.text = response!!.body().data.user_Review_Count.toString()

                        getStarText = findViewById(R.id.star_count) as TextView
                        getStarText!!.text = response!!.body().data.user_Grade_Count.toString()

                        getImage = findViewById(R.id.user_picture) as CircleImageView

//                     Log.v("main", response!!.body().data.user_data.user_profile)

                        if(response!!.body().data.user_data.user_profile == null){
                            requestManager!!.load(R.drawable.img_profie_basic).into(getImage)
                        }
                        else
                            requestManager!!.load(response!!.body().data.user_data.user_profile).into(getImage)



                        var myPlace : List<Int>? = null
                        myPlace = response!!.body().data.user_data.user_place

                        var myGenre : List<Int>? = null
                        myGenre = response!!.body().data.user_data.user_genre

                        var myMood : List<Int>? = null
                        myMood = response!!.body().data.user_data.user_mood

                        var mySubject : List<Int>? = null
                        mySubject = response!!.body().data.user_data.user_subject

                        var SumPref : List<Int>? = null
                        SumPref = myPlace+myGenre+myMood+mySubject

                        Log.v("size", SumPref!!.size.toString())

                        for(i in 0..(SumPref!!.size-1)){
                            if(SumPref[i] == 1){
                                Log.v("null1", imageArray[imageIndex].toString())
                                Log.v("null2", preFerImg[i]!!.toString())
                                Log.v("null2", i.toString())
                                imageArray[imageIndex].setBackgroundResource(preFerImg.get(i)!!)
                                imageIndex+=1
                            }else{
                                continue
                            }
                        }


                        if(imageIndex<12)
                            moreTagButton!!.visibility=View.GONE


                        adapter = MyinfoAdapter(response.body().data.user_Like_List, requestManager!!)
                        myinfoDatas = response.body().data.user_Like_List
                        adapter!!.setOnItemClickListener(this@MypageActivity)
                        myinfoList!!.adapter = adapter


                        reviewAdapter = ReviewAdapter2(response!!.body().data.user_Review_List, requestManager!!)
                        reviewDatas = response.body().data.user_Review_List
                        reviewList!!.adapter = reviewAdapter


                    }
                }
            }
            override fun onFailure(call: Call<MyPageResponse>?, t: Throwable?) {
                //응답 실패
                ApplicationController.instance!!.makeToast("통신 확인")
            }

        })



    }

    fun floatDialog() {
        popDialog = PopupDialog(this, "닉네임 변경", cancelListener, okLeftListener, "확인")
        popDialog!!.show()
    }

    private val okLeftListener: View.OnClickListener? = View.OnClickListener {
        //확인 버튼 눌렀을 때
        Log.v("main", "ok")
        var edit = popDialog!!.findViewById(R.id.change_edit) as EditText
        var text = edit.text.toString()

        var error = popDialog!!.findViewById(R.id.error_msg) as TextView
        if (text.contains(" ")) {
            error.text = "띄어쓰기는 피해주세요:("
        } else {
            user_name.text = text


            // 닉네임 바꾸기
            val putMyPageModiResponse = networkService!!.putMyPageNickMode(CommonData.user_token!!,
                    MyPageNickModiPut(text))

            putMyPageModiResponse.enqueue(object : Callback<MyPageNickModiResponse> {
                override fun onResponse(call: Call<MyPageNickModiResponse>?, response: Response<MyPageNickModiResponse>?) {
                    if (response!!.isSuccessful) {
                        if (response!!.body().status == "success") {
                            ApplicationController.instance!!.makeToast("수정 완료")
                            Log.d("수정완료","완료")
                        }
                    }else{
                        Log.d("수정완료","실패")
                    }
                }
                override fun onFailure(call: Call<MyPageNickModiResponse>?, t: Throwable?) {
                    //응답 실패
                    ApplicationController.instance!!.makeToast("통신 확인")
                    Log.d("수정완료","통신실패")

                }
            })

            popDialog!!.dismiss()
        }
    }

    private val cancelListener: View.OnClickListener? = View.OnClickListener {
        //취소 버튼 눌렀을 때
        //Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show()
        Log.v("main", "cancel")
        popDialog!!.dismiss()
    }
}
