package easyfunart.easyfunart.Activity

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import easyfunart.easyfunart.ApplicationController
import easyfunart.easyfunart.CommonData
import easyfunart.easyfunart.NetworkService

import easyfunart.easyfunart.R
import easyfunart.easyfunart.Response.RecordResponse
import kotlinx.android.synthetic.main.activity_playback_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaybackScreen : AppCompatActivity() {

    private var networkService : NetworkService? = null
    var pos : Int? = null
    var sb : SeekBar? = null
    var isPlaying : Boolean = false
    var start_pause : Int = 0
    var i : Int? = 0
    var j : Int? = 0
    var divided : Int? = null
    var current_quotient : Int? = null
    var current_remainder : Int? = null
    var complete_quotient : Int? = null
    var complete_remainder : Int? = null
    var flag : Int? = null
    var exId : Int? = null
    var exImage : String? = null
    var exTitle : String? = null
    var docentTitle : String? = null
    var docentTrack : Int = 0
    var docent : String? = null
    var trackSize : Int = 0


    internal inner class MyThread : Thread() {
        override fun run() { // 쓰레드가 시작되면 콜백되는 메서드
            // 씨크바 막대기 조금씩 움직이기 (노래 끝날 때까지 반복)

            while (isPlaying) {
                sb!!.setProgress(CommonData.mp!!.getCurrentPosition())//매순간 포지션을 받아서 프로그레스바를 이동시킨다.

//                Log.d("쓰레드_테스트2", CommonData.test.toString())
//                Log.d("MyThread1", "실행")
//                Log.d("MyThread1", CommonData.mp!!.duration.toString())
//                Log.d("MyThread2",(CommonData.mp!!.getCurrentPosition()).toString())


                if (CommonData.mp!!.getCurrentPosition() + 500 >= CommonData.mp!!.duration) {
                    isPlaying = false
//                        Log.d("MyThread3", CommonData.mp!!.getCurrentPosition().toString())
//                        ApplicationController.instance!!.makeToast("asdfghjkl")

//                        if (flag.equals("1")) {
//                            // 전시상세보기 팝업
//                            var builder: AlertDialog.Builder = AlertDialog.Builder(this@PlaybackScreen)
//                            var inflater: LayoutInflater = LayoutInflater.from(this@PlaybackScreen)
//                            var view: View = inflater.inflate(R.layout.evaluation_question_alarm, null)
//                            builder.setView(view)
//                            var dialog: Dialog = builder.create()
//                            dialog.show()
//                            startActivity(Intent(applicationContext, TrackActivity::class.java))
//                        }
                    Log.d("트랙현재 트랙",docentTrack.toString())
                    Log.d("트랙사이즈",trackSize.toString())
                    if(docentTrack!! <= trackSize ){//트랙의 아이템 사이즈 만큼 음악이 재생될수 있도록 한다.
                        GetNextRecord()
                        playbackScreen_playPause_btn.setBackgroundResource(R.drawable.docentplaying_stop)
                        Log.d("인덱스","하이")
                    }
//                        sb!!.setProgress(0)
//                        current_minute!!.setText("00")
//
//                        current_second!!.setText("00")
//                        Log.d("현재분",current_minute.toString())
//                        Log.d("현재초",current_second.toString())

                } else if (CommonData.mp!!.getCurrentPosition() + 500 < CommonData.mp!!.duration) {
//                Log.d("MyThread","실행")
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            // 해당 작업을 처리함
//                                Log.v("nulling", CommonData.mp!!.toString())
                            pos = CommonData.mp!!.getCurrentPosition()
                            divided = pos!! / 1000
                            current_quotient = divided!! / 60
                            current_remainder = divided!! % 60

                            if (current_quotient.toString().length.equals(1)) {
                                current_minute.setText("0" + current_quotient.toString())
                            } else {
                                current_minute.setText(current_quotient.toString())
                            }
                            if (current_remainder.toString().length.equals(1)) {
                                current_second.setText("0" + current_remainder.toString())
                            } else {
                                current_second.setText(current_remainder.toString())
                            }
                        }
                    })
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback_screen)

        networkService = ApplicationController.instance!!.networkService
        sb = findViewById(R.id.seekBar1) as SeekBar



        sb!!.getProgressDrawable().setColorFilter( Color.parseColor("#fcdc09"), PorterDuff.Mode.SRC_IN );

        //어떤 트랙 선택했는지 exId로 받음.
        exId = intent.getIntExtra("exId",0)
        exImage = intent.getStringExtra("exImage")
        exTitle = intent.getStringExtra("exTitle")
        docentTrack = intent.getIntExtra("docentTrack",0)
        docentTitle= intent.getStringExtra("docentTitle")
        trackSize = intent.getIntExtra("trackSize",0)
        Log.d("TrackList_exId",exId.toString())
        Log.d("TrackList_docentTrack",docentTrack.toString())
        Log.d("1트랙현재 트랙",docentTrack.toString())
        Log.d("1트랙사이즈",trackSize.toString())

        //intent로 전시회 이미지,전시회 타이틀,작품 타이틀을 받아옴.
        Glide.with(this@PlaybackScreen)//전시관 이미지
                .load(exImage)
                .into(playbackScreen_work_img)
        playbackScreen_galleryname_text.setText(exTitle)
//        playbackScreen_workname_text.setText(docentTitle)
//        playbackScreen_script_Gallerytitle_text.setText(exTitle)
        playbackScreen_script_worktitle_text.setText(docentTitle)

        Log.d("playbackScreen_exid",exId.toString())
        Log.d("playbackScreen_docentid",docentTrack.toString())


        //초기 재생 데이터 받음.
        init()

        //다음 버튼 클릭시.
        playbackScreen_next_btn.setOnClickListener{
            Log.d("다음버튼","클릭")
            GetNextRecord()
        }
        //이전 버튼 클릭시.
        playbackScreen_prev_btn.setOnClickListener{

            Log.d("이전버튼","클릭")
            GetPrevRecord()
        }
        //취소 버튼 클릭시
        playbackScreen_cancel_btn!!.setOnClickListener {
            val intent = Intent(applicationContext, TrackActivity::class.java)
            intent.putExtra("exId", exId!!)
            intent.putExtra("exTitle", exTitle)
            intent.putExtra("exImage",exImage)
            startActivity(intent)

            isPlaying = false // 쓰레드 정지
            //MyThread().interrupt()

            Log.d("백버튼","백버튼")
            CommonData.mp!!.pause()
            finish()
        }
        //스크립트 버튼 클릭시
        playbackScreen_script_button.setOnClickListener{
            ScriptCreation()
//            Toast.makeText(getApplication(),"getListdata2 error1",Toast.LENGTH_SHORT).show();
        }
        //맵 버튼 클릭시
        playbackScreen_map_button.setOnClickListener{
            MapCreation()
        }
        playbackScreen_playingMaintain_btn.setOnClickListener{
            val intent = Intent(applicationContext, TrackActivity::class.java)
            intent.putExtra("exId", exId!!)
            intent.putExtra("exTitle", exTitle)
            intent.putExtra("exImage",exImage)
            startActivity(intent)
            finish()
        }


//        Log.d("전체",CommonData.mp!!.duration.toString())




        sb!!.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar:SeekBar){//seekBar 이동하시키다 멈출때 발생하는 이벤트

                isPlaying = true
                val ttt = seekBar.getProgress()
                CommonData.mp!!.seekTo(ttt)
                CommonData.mp!!.start()
                MyThread().start()
                playbackScreen_playPause_btn.setBackgroundResource(R.drawable.docentplaying_stop)


                if ((CommonData.mp!!.duration) == 5832704){//프로그레스바 끝까지 이동시키면 5832704값이 반환됨.
                    isPlaying = false
                    if(docentTrack!! <= trackSize ){
                        GetNextRecord()
                        playbackScreen_playPause_btn.setBackgroundResource(R.drawable.docentplaying_stop)
                    }
                    //재생이 끝날을 시 현재 시간을 0으로 세팅
                    current_minute.setText("00")
                    current_second.setText("00")
                    Log.d("현재분",current_minute.toString())
                    Log.d("현재초",current_second.toString())
                }


                Log.d("아나다",CommonData.mp!!.duration.toString())
                Log.d("아나다",CommonData.mp!!.getCurrentPosition().toString())

                pos = CommonData.mp!!.getCurrentPosition()

                divided = pos!!/1000
                current_quotient = divided!!/60
                current_remainder =divided!!%60

                if(current_quotient.toString().length.equals(1)){
                    current_minute.setText("0" + current_quotient.toString())
                }else{
                    current_minute.setText(current_quotient.toString())
                }
                if(current_remainder.toString().length.equals(1)){
                    current_second.setText("0" + current_remainder)
                }else{
                    current_second.setText(current_remainder.toString())
                }
            }
            override fun onStartTrackingTouch(seekBar:SeekBar){//seekBar 이동 시작할때 이벤트
                Log.d("onStartTrackingTouch","실행")
                isPlaying = false
                CommonData.mp!!.pause()

            }
            override fun onProgressChanged(seekBar:SeekBar, progress:Int, fromUser:Boolean) {//seekBar 이동하는 중간의 이벤트
                if (seekBar.getMax() == progress){
                    Log.d("onProgressChanged","실행")
                    isPlaying = false
                    CommonData.mp!!.stop()
//                    startActivity(Intent(applicationContext,TrackActivity::class.java))
                }
            }
        })

        playbackScreen_playPause_btn!!.setOnClickListener(object:View.OnClickListener {

            override fun onClick(v:View) {

                if(start_pause.equals(0)) {
                    pos = CommonData.mp!!.getCurrentPosition()
//                    Log.d("현재재생",pos.toString())
                    CommonData.mp!!.pause() // 일시중지
                    isPlaying = false // 쓰레드 정지
                    start_pause = 1
                    Log.d("pause",start_pause.toString())
                    playbackScreen_playPause_btn.setBackgroundResource(R.drawable.btn_docent_play)
                }
                else if(start_pause.equals(1)){
                    // 멈춘 지점부터 재시작
                    pos = CommonData.mp!!.getCurrentPosition()
                    CommonData.mp!!.seekTo(pos!!) // 일시정지 시점으로 이동
                    CommonData.mp!!.start() // 시작
                    isPlaying = true // 재생하도록 flag 변경
                    MyThread().start() // 쓰레드 시작
                    start_pause = 0
                    playbackScreen_playPause_btn.setBackgroundResource(R.drawable.docentplaying_stop)
                }
            }
        })

//        val loginParams: WindowManager.LayoutParams
//        dialog = CustomDialog(this@PlaybackScreen)
//
//        loginParams = dialog!!.getWindow().getAttributes()
//        // Dialog 사이즈 조절 하기
//
//        loginParams.width = WindowManager.LayoutParams.MATCH_PARENT
//        loginParams.height = WindowManager.LayoutParams.MATCH_PARENT
//        dialog!!.getWindow().setAttributes(loginParams)
//
//        dialog!!.show()


    }
    fun init(){

        val getInit = networkService!!.getInitData(exId!!,docentTrack!!)

        getInit.enqueue(object : Callback<RecordResponse>{
            override fun onResponse(call: Call<RecordResponse>?, response: Response<RecordResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().status.equals("success")){
                        Log.d("playbackScreen_init","성공")

                        Glide.with(this@PlaybackScreen)//전시관 이미지
                                .load(exImage)
                                .into(playbackScreen_work_img)

                        Glide.with(this@PlaybackScreen)//맵 이미지
                                .load(response.body().data.docent_place)
                                .into(playbackScreen_map_img)


                        playbackScreen_script_content_text.setText(response.body().data.docent_text)//대본 연결
                        playbackScreen_workname_text.setText(response.body().data.docent_title)

                        Log.d("스크립트",response.body().data.docent_text)

                        isPlaying = false // 쓰레드 정지
                        play(response.body().data.docent_audio)//초기 음악 실행.

                        totalTime()


                    }else{
                        Log.d("playbackScreen_init","다음곡 받기 실패1")
                    }
                }else{
                    Log.d("playbackScreen_init","다음곡 받기 실패2")
                }
            }
            override fun onFailure(call: Call<RecordResponse>?, t: Throwable?) {
                Log.d("playbackScreen_init","통신 실패") }
        })

    }
    fun GetNextRecord(){

        val NextRecordResponse = networkService!!.getNextRecord(exId!!,docentTrack!!)

        NextRecordResponse.enqueue(object : Callback<RecordResponse>{

            override fun onResponse(call: Call<RecordResponse>?, response: Response<RecordResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().status.equals("success")){
                        Log.d("playbackScreen_next","성공")

                        Glide.with(this@PlaybackScreen)//전시관 이미지
                                .load(exImage)
                                .into(playbackScreen_work_img)

                        Glide.with(this@PlaybackScreen)//맵 이미지
                                .load(response.body().data.docent_place)
                                .into(playbackScreen_map_img)

                        playbackScreen_script_content_text.setText(response.body().data.docent_text)//대본 연결


                        playbackScreen_workname_text.setText(response.body().data.docent_title)

                        docentTrack = response.body().data.docent_track




//                        CommonData.track_array!![response.body().data.docent_track-1] = 1


                        isPlaying = false // 쓰레드 정지
//                        pause()
                        play(response.body().data.docent_audio)//새로운 음악 실행.
                        totalTime()

                    }else{
                        Log.d("playbackScreen_next","다음곡 받기 실패1")
                    }
                }else{
                    Log.d("playbackScreen_next","다음곡 받기 실패2")
                }
            }
            override fun onFailure(call: Call<RecordResponse>?, t: Throwable?) {
                Log.d("playbackScreen_next","init통신실패")

            }

        })
    }

    fun GetPrevRecord(){

        val PrevRecordResponse = networkService!!.getPrevRecord(exId!!,docentTrack!!)
        PrevRecordResponse.enqueue(object : Callback<RecordResponse>{
            override fun onResponse(call: Call<RecordResponse>?, response: Response<RecordResponse>?) {
                if(response!!.isSuccessful){
                    if(response!!.body().status.equals("success")){
                        Log.d("playbackScreen_prev","성공")


                        Glide.with(this@PlaybackScreen)//전시관 이미지
                                .load(exImage)
                                .into(playbackScreen_work_img)

                        Glide.with(this@PlaybackScreen)//맵 이미지
                                .load(response.body().data.docent_place)
                                .into(playbackScreen_map_img)
                        playbackScreen_script_content_text.setText(response.body().data.docent_text)//대본 연결
                        playbackScreen_workname_text.setText(response.body().data.docent_title)

//                        CommonData.track_array!![response.body().data.docent_track-1] = 1

                        docentTrack = response.body().data.docent_track

                        isPlaying = false // 쓰레드 정지
                        play(response.body().data.docent_audio)//새로운 음악 실행.
                        totalTime()

                    }else{
                        Log.d("playbackScreen_prev","이전곡 받기 실패")
                    }
                }else{
                    Log.d("playbackScreen_prev","이전곡 받기 실패2")
                }

            }
            override fun onFailure(call: Call<RecordResponse>?, t: Throwable?) {
                Log.d("playbackScreen_prev","이전곡통신실패")

            }

        })
    }
    fun totalTime(){

        divided = CommonData.mp!!.duration / 1000
        complete_quotient = divided!! / 60
        complete_remainder =divided!! % 60


        Log.d("몫",complete_quotient.toString())
        Log.d("나머지",complete_remainder.toString())

        if(complete_quotient.toString().length.equals(1)){
            Log.d("몫",complete_quotient.toString())
            complete_minute.setText("0" + complete_quotient.toString())
        }else{
            complete_minute.setText(complete_quotient.toString())
        }
        if(complete_remainder.toString().length.equals(1)){
            Log.d("몫",complete_quotient.toString())
            complete_second.setText("0" + complete_remainder.toString())
        }else{
            complete_second.setText(complete_remainder.toString())
        }
    }

    fun ScriptCreation(){

        if(i!!.equals(0)){

            //if,스크립트를 키는데 지도가 미리켜져있을경우 지도를 없애기 위해.
            playbackScreen_map_img!!.alpha=0f//투명도 100%
            playbackScreen_scroll_view!!.setBackgroundColor(Color.parseColor("#00ffffff"))
            j=0
            //////////////////////////////////////////////////////////////////////////////////////////
            playbackScreen_script_layout!!.setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
            playbackScreen_scroll_view!!.setBackgroundColor(Color.parseColor("#77000000"))//배경화면 어둡게.

            //작품 이름
            playbackScreen_script_worktitle_text!!.setTextColor(Color.parseColor("#ffffff"))
            playbackScreen_script_worktitle_text!!.setTextSize(20f)
//            playbackScreen_script_worktitle_text!!.setText("아이유-밤편지\n")

            //작품 내용
            playbackScreen_script_content_text!!.setTextColor(Color.parseColor("#ffffff"))
            playbackScreen_script_content_text!!.setTextSize(15f)
            i = 1
            Log.d("로그1",i.toString())


        }else if(i!!.equals(1)){

            playbackScreen_galleryname_text!!.setTextColor(Color.parseColor("#000000"))//배경 갤러리 이름 나타내기.
            playbackScreen_workname_text!!.setTextColor(Color.parseColor("#000000"))//배경 작품 이름 나타내기


            //텍스트 컬러 투명하게...
//            playbackScreen_script_Gallerytitle_text!!.setTextColor(Color.parseColor("#00ffffff"))
            playbackScreen_script_worktitle_text!!.setTextColor(Color.parseColor("#00ffffff"))
            playbackScreen_script_content_text!!.setTextColor(Color.parseColor("#00ffffff"))
            playbackScreen_scroll_view!!.setBackgroundColor(Color.parseColor("#00ffffff"))
            i = 0


            Log.d("로그2",i.toString())

        }
    }


    fun MapCreation(){

        if(j!!.equals(0)){
//
//            //if,맵을 키는데 스크립트가 켜져있는 경우, 스크립트를 없앤다.
//            playbackScreen_galleryname_text!!.setTextColor(Color.parseColor("#00000000"))
//            playbackScreen_galleryname_text!!.setTypeface(Typeface.DEFAULT)

//            playbackScreen_script_Gallerytitle_text!!.setTextColor(Color.parseColor("#00ffffff"))
            playbackScreen_script_worktitle_text!!.setTextColor(Color.parseColor("#00ffffff"))//스크립트의 작품 제목을 투명하게만듦
            playbackScreen_script_content_text!!.setTextColor(Color.parseColor("#00ffffff"))//스크립트의 내용을 투명하게 만듦.
            playbackScreen_scroll_view!!.setBackgroundColor(Color.parseColor("#00ffffff"))//스크립트의 배경색을 투명으로 만듦.
            i = 0
            /////////////////////////////////////////////////////////////////////////////////////////

//            playbackScreen_map_img!!.setImageResource(R.drawable.pic1)
            playbackScreen_map_img!!.alpha = 255f//투명도 0%//맵 투명도를 0%로 만듦.
//            playbackScreen_script_layout!!.setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
            playbackScreen_scroll_view!!.setBackgroundColor(Color.parseColor("#77000000"))//배경색을 까맣게 만듦.

            j = 1
            Log.d("로그1",j.toString())

        }else if(j!!.equals(1)){

            playbackScreen_map_img!!.alpha=0f//맵이미지을 투명하게 만듦.
            playbackScreen_scroll_view!!.setBackgroundColor(Color.parseColor("#00ffffff"))//배경을 투명하게 만듦.
            j = 0

            Log.d("로그2",j.toString())
        }
    }

    fun pause(){
        isPlaying = false // 쓰레드 종료
        CommonData.mp!!.stop() // 멈춤
        CommonData.mp!!.release() // 자원 해제
        sb!!.setProgress(0) // 씨크바 초기화
    }

    fun play(record : String){

        if(CommonData.mp!=null){
            Log.d("미디어플레이어","null")
            CommonData.mp!!.pause()
        }

        CommonData.mp = MediaPlayer.create(
                getApplicationContext(), // 현재 화면의 제어권자
                Uri.parse(record)) // 음악파일
        CommonData.mp!!.setLooping(false) // true:무한반복
        CommonData.mp!!.start() // 노래 재생 시작d
        val a = CommonData.mp!!.getDuration() // 노래의 재생시간(miliSecond)
        sb!!.setMax(a)// 씨크바의 최대 범위를 노래의 재생시간으로 설정
        MyThread().start() // 씨크바 그려줄 쓰레드 시작
        isPlaying = true // 씨크바 쓰레드 반복 하도록
        Log.d("play","실행")
    }


    override fun onStop(){
        super.onStop()
        //isPlaying = false // 쓰레드 정지
//        MyThread().interrupt()
//        if (CommonData.mp != null)
//        {
////            CommonData.mp!!.release() // 자원해제
//            sb!!.setProgress(0) // 씨크바 초기화
        //CommonData.mp!!.
    }

    override fun onBackPressed() {

        val intent = Intent(applicationContext, TrackActivity::class.java)
        intent.putExtra("exId", exId)
        intent.putExtra("exTitle", exTitle)
        intent.putExtra("exImage",exImage)
        intent.putExtra("docentTrack",docentTrack)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }


}