package easyfunart.easyfunart

import android.media.MediaPlayer

/**
 * Created by JWLIM on 2018-01-02.
 */
object CommonData {
    //var loginData : LoginData? = null
    //var user_token : String? = null
    var user_token : String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjUsInVzZXJMZXZlbCI6NTAsImlhdCI6MTUxNTc5MTIwNywiZXhwIjoxNTI0NDMxMjA3LCJpc3MiOiJFYXN5RnVuQXJ0In0.tzfB4rv2RXo914-1Dif25wnc0rRSO6rCFimP60aVX14"
    var mp : MediaPlayer? = null
    var track_array :ArrayList<Int>? = null

    //지윤
    /*서버에 보내주려고 저장할 int 배열*/
    var genreIntlist : ArrayList<Int>?= arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0)
    var placeIntlist : ArrayList<Int>? = arrayListOf(0,0,0,0,0,0,0,0,0)
    var moodIntlist : ArrayList<Int>?=arrayListOf(0,0,0,0,0,0,0,0,0,0,0)
    var titleIntlist : ArrayList<Int>?= arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0)

    /*서버에 보내줄 String 배열*/
    var sendgenre : String?=null
    var sendplace : String?=null
    var sendmood : String?=null
    var sendtitle : String?=null


    var str_sex : Int?=null
    var str_age : Int?=null

    var str_genre : String? = null
    var str_place : String? = null
    var str_mood : String? = null
    var str_title : String? = null

    /*토큰목록*/
    var logincheck : Boolean? = false
    var loginStr : String? = null
    var infoStr : String? = null

    var nickname : String? =null
    var level : Int?=null

    var checkflag : Int? = null
}