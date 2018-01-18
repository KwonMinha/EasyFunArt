package easyfunart.easyfunart.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import easyfunart.easyfunart.R

class SplashActivity1: AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)

        val prefs = getSharedPreferences("savedata", Context.MODE_PRIVATE)




        /*로그인 토큰 불러오기*/
        var logincheckstring : String? = null
        logincheckstring = prefs.getString("token", "default value") //id 값 없으면 default값을 대체
        Log.e("logincheckstring", prefs.getString("token", "default value").toString() )
        /*
              /*로그인 토큰 여부 판단해서 넘어가기*/
              if (CommonData.logincheck==true){
                  //핸들러작성_백그라운드에서 돌아갈때 조정해주는 역할
                  val handler = Handler()
                  handler.postDelayed({
                      startActivity(Intent(applicationContext, SplashActivity2::class.java))
                      finish()
                  }, 3000) //3초 후에 login액티비티로 넘어감

              }

              else if (CommonData.logincheck==false){
                  //핸들러작성_백그라운드에서 돌아갈때 조정해주는 역할
                  val handler = Handler()
                  handler.postDelayed({
                      startActivity(Intent(applicationContext, ResultActivity_2::class.java))
                      finish()
                  }, 3000) //3초 후에 login액티비티로 넘어감

              }

      */
        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext,SplashActivity2::class.java))
            finish()
        }, 1000) //3초 후에 login액티비티로 넘어감


    }

}