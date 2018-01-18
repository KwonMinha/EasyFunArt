package easyfunart.easyfunart.Activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.NetworkService
import easyfunart.easyfunart.POST.FacebookPost
import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.SendResponse
import kotlinx.android.synthetic.main.activity_splash2.*
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class SplashActivity2 :AppCompatActivity() {

    var mDiscoveringImage : GifImageView? = null
    var mDiscoveringGif : GifDrawable? = null

    var callbackManager: CallbackManager? =null
    var mSession : PackageInstaller.Session?=null

    //서버 통신용 변수 선언
    private var data: Uri? = null
    private var networkService: NetworkService? = null
    private var v : View.OnClickListener?=null


    override fun onCreate(savedInstanceState: Bundle?) {

        FacebookSdk.sdkInitialize(this.applicationContext)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash2)

        mDiscoveringImage = findViewById(R.id.splash)
        mDiscoveringImage!!.setImageResource(R.drawable.splash)
        mDiscoveringGif = mDiscoveringImage!!.getDrawable() as GifDrawable
        mDiscoveringGif!!.loopCount = 1
        mDiscoveringGif!!.setSpeed(3.0f)

        val animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
        animation.startOffset = 1000
        login_btn.startAnimation(animation)


        try {
            val info = packageManager.getPackageInfo("easyfunart.easyfunart", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        networkService = ApplicationController.instance!!.networkService

//        //페이스북 버튼 등장 애니메이션
//        var anim : Animation?=null
//        anim = AnimationUtils.loadAnimation(applicationContext, R.anim.alpha_anim)
//        login_btn.startAnimation(anim)

        login_btn.setOnClickListener( {
            onclick()
            Log.e("여기 onclick함수 진입", "asd")
//            startActivity(Intent(applicationContext, HomeActivity::class.java))
        })
    }

    private fun onclick() {
        callbackManager =CallbackManager.Factory.create()
        Log.v("여기GET callbackMtostirng", callbackManager.toString())
        Log.e("get", "여기onclick")

      // login_btn.setReadPermissions(Arrays.asList("public_profile", "email"));

        LoginManager.getInstance().logInWithReadPermissions(this@SplashActivity2, Arrays.asList("public_profile", "email"))
        Log.e("여기Get", "여기 loginPermission")
        //

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.e("여기 success", "여기까지잘됨onsuccess")
                var request : GraphRequest?=null
                Log.e("여기 result.accesstoken완성", result!!.accessToken.token.toString())
                Log.e("여기 loginresult", result.accessToken.userId.toString())

                /*로그인 시 로그인 userId commonData에 저장*/
                CommonData.loginStr=result.accessToken.userId.toString()

                /*로그인 시 레벨 10 sharedPreference에 저장*/
                val sharedLevel = getSharedPreferences("level", Context.MODE_PRIVATE)
                val sharedEditor = sharedLevel.edit()
                sharedEditor.putInt("level", 10)

                val sharedusertoken = getSharedPreferences("usertoken", Context.MODE_PRIVATE)
                val sharedEditortoken = sharedusertoken.edit()

                Log.e("여기 commmonData.string", CommonData.loginStr)
                sharedEditortoken.putString("usertokenstring",CommonData.loginStr)
                sharedEditortoken.apply()
                Log.e("jysharedusertoken", sharedusertoken.toString())
                /*서버에 전송*/
                sendFacebook()

                /*shared preferences 에 정보 저장*/
                val prefs= getSharedPreferences("savedata", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("token", result!!.accessToken.userId)
                editor.apply()

                /*CommonData의 로그인 체크 변수  true 로 변경*/
                CommonData.logincheck=true
                Log.e("여기 logincheck", CommonData.logincheck.toString())



                /*화면 전환*/
                val intent= Intent(applicationContext, ResultActivity_2::class.java)
                startActivity(intent)
            }

            override fun onCancel() {
                Log.e("여기", "cancel error")
                //사용자가 로그인 취소 누르면 정보 입력화면으로 이동
                val intent= Intent(applicationContext, ResultActivity_2::class.java)
                startActivity(intent)
            }

            override fun onError(error: FacebookException?) {
                Log.e("facebookerror", "error")

            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("GET", "여기까지 잘됨onActivityResult")
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        Log.e("여기 callbackRESULT" , callbackManager!!.onActivityResult(requestCode, resultCode, data).toString())
    }

    fun sendFacebook(){

        Log.e("jySendFacebook", CommonData.loginStr)
        val token : String =   CommonData.loginStr!!

        Log.e("여기 로그인정보",CommonData.loginStr.toString())

        val sendinfo = FacebookPost(CommonData.loginStr.toString())

        val facebookResponse = networkService!!.sendFacebook(token,sendinfo)

        facebookResponse?.enqueue(object : Callback<SendResponse>{
            override fun onResponse(call: Call<SendResponse>?, response: Response<SendResponse>?) {
                if(response!!.isSuccessful){
                    if(response.body().status.equals("success")){

                        //CommonData에 저장
                        CommonData.loginStr=response.body().data.token
                        Log.e("여기 loginstr", CommonData.loginStr)


                    }
                    else {
                        Log.d("e", "정보가 정확")
                    }
                }

            }

            override fun onFailure(call: Call<SendResponse>?, t: Throwable?) {
                Log.d("e", "연결에 실패했습니다")}

        })
    }

}