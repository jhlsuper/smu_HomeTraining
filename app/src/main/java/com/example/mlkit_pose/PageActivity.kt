package com.example.mlkit_pose


import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.adapter.MainViewModel
import com.example.mlkit_pose.adapter.UserRkAdapter
import com.example.mlkit_pose.dao.SharedManager
import com.example.mlkit_pose.dao.User
import com.example.mlkit_pose.fragment.*
import com.example.mlkit_pose.fragment.expre.RoutineFragment
import com.example.mlkit_pose.kotlin.SettingLivePreviewActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main_page_part.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_ranking_main.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*
import kotlinx.android.synthetic.main.main_drawer_header.*
import kotlinx.android.synthetic.main.popup_settime.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class PageActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var userRankAdapter: UserRkAdapter
    lateinit var exname: String
    lateinit var year: String
    lateinit var month: String
    lateinit var day: String
    private var minute by Delegates.notNull<Int>()
    private var second by Delegates.notNull<Int>()
    val datas = mutableListOf<User>()

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    private val sharedManager: SharedManager by lazy { SharedManager(this) }

    //    private var belong: String? = sharedManager.getCurrentUser().belong
    lateinit var viewModel: MainViewModel
    val list: MutableList<Model> by lazy {
        mutableListOf<Model>()
    }
    var number = 0
    lateinit var alertDialog: AlertDialog
    lateinit var builder: AlertDialog.Builder

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context_main = this

        val currentUser = sharedManager.getCurrentUser()
        setUserRank()
        val transaction = supportFragmentManager.beginTransaction()
        setContentView(R.layout.fragment_main_page_part)
        transaction.add(R.id.frameLayout, HomeFragment().apply {
            arguments = Bundle().apply {
                putString("name", "${currentUser.name}")
                putString("points", "${currentUser.points}")
            }
        })
        transaction.commit()

        setSupportActionBar(main_layout_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //드로어를 꺼낼 홈버튼 활상화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.btn_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)  //타이틀 안보이게
        btn_home.setOnClickListener(this)
        btn_guide.setOnClickListener(this)
        btn_mypage.setOnClickListener(this)
        btn_routine.setOnClickListener(this)
        btn_ranking.setOnClickListener(this)
        btn_drawer.setOnClickListener(this)

//        btn_add_routine.setOnClickListener(this)
        main_navigationView.setNavigationItemSelectedListener(this)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                Log.d("resultLauncher", it.resultCode.toString())
                if (it.resultCode == RESULT_OK) {
                    showExerciseDonePopup(exname, minute, second, this)
                }
            }
//        Log.d("userinfo", "${currentUser.weight},${currentUser.height}")

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.belong.observe(this, {
            info_user_belong?.text = "소속: $it"
        })
        viewModel.point.observe(this, {
//            Log.d("userPoint", it)
            txt_ranking_my_points?.text = it.toString()
            et_mypage_point?.text = it.toString()
            info_user_point?.text = "포인트: $it"
            btn_home_ranking.text = "유저포인트\n $it"
            rankingRefresh()
        })
        viewModel.recentDay.observe(this, {
            if (it.length != 8) {
                year = "없음"
                month = ""
                day = ""
            } else {
                year = it.substring(0 until 4) + "년"
                month = it.substring(4 until 6) + "월"
                day = it.substring(6 until 8) + "일"
                et_mypage_recent_day?.text = "$year $month $day"
            }
        })
        viewModel.countDays.observe(this, {
            et_mypage_exercisedays?.text = it.toString() + "일"
        })
        viewModel.init()
//        Log.d("userinfo", "${currentUser.countDays},${currentUser.recentDay}")

    }

    //    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(requestCode == 1000){
//            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this@PageActivity,"거부하실 경우 카메라 사용에 문제가 될 수 있습니다.",Toast.LENGTH_LONG).show()
//            }
//        }
//    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //툴바 버튼 처리
        when (item.itemId) {
            android.R.id.home -> {
                main_drawer_layout.openDrawer(GravityCompat.START)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        return super.onCreateOptionsMenu(menu)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.btn_drawer_logout -> {
//                logOut()
                getAlertShow("로그아웃", "정말 로그아웃 하시겠습니까?") { logOut() }
            }
            R.id.btn_opensource -> {
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
                OssLicensesMenuActivity.setActivityTitle("오픈소스 라이브러리")
            }
        }
        return false
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        //뒤로가기 버튼 처리
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawers()
        } else if (count == 0) {
            getAlertShow("종료하기", "정말로 종료하시겠습니까?") { super.onBackPressed() }
//            super.onBackPressed()
        } else {
            super.onBackPressed()
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View) {
//        val datas = mutableListOf<User>()
        val currentUser = sharedManager.getCurrentUser()

//        val transaction = supportFragmentManager.beginTransaction()
        when (v.id) {
            R.id.btn_home -> {
                setDataAtFragment(HomeFragment(), TAG_HOME_FRAGMENT)
            }
            R.id.btn_guide -> {
                setDataAtFragment(GuideMainFragment(), TAG_GUIDE_FRAGMENT)

            }
            R.id.btn_mypage -> {
                setDataAtFragment(MyPageFragment(), TAG_MYPAGE_FRAGMENT)
            }
            R.id.btn_ranking -> {
//                setRankData()
                setDataAtFragment(RankingMainFragment(), TAG_RANK_FRAGMENT)
            }
            R.id.btn_routine -> {
                setDataAtFragment(RoutineFragment(), TAG_ROUTINE_FRAGMENT)
            }
            R.id.btn_drawer -> {
                info_user_id.text = "${currentUser.id}"
                info_user_belong.text = "소속: ${currentUser.belong}"
                info_user_point.text = "포인트: ${currentUser.points}"
                main_drawer_layout.openDrawer(GravityCompat.START)
            }
        }
    }


    fun logOut() {

        val currentUser = sharedManager.getCurrentUser()
        sharedManager.DeleteCurrentUser(currentUser)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun setDataAtFragment(fragment: Fragment, tag: String) {
        val currentUser = sharedManager.getCurrentUser()
        val bundle = Bundle()
        bundle.putString("id", currentUser.id)
        bundle.putString("name", currentUser.name)
        bundle.putString("gender", currentUser.gender)
        bundle.putString("belong", currentUser.belong)
        bundle.putString("points", currentUser.points)
        bundle.putString("height", currentUser.height)
        bundle.putString("weight", currentUser.weight)
        bundle.putString("recentDay", currentUser.recentDay)
        bundle.putString("countDays", currentUser.countDays.toString())
        fragment.arguments = bundle
        setFragment(fragment, tag)
    }

    private fun setFragment(fragment: Fragment, tag: String) {

        val transaction = supportFragmentManager.beginTransaction()
        val manager = supportFragmentManager

        if (manager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.frameLayout, fragment, tag)
            transaction.show(fragment)
        }
        val home = manager.findFragmentByTag(TAG_HOME_FRAGMENT)
        val rank = manager.findFragmentByTag(TAG_RANK_FRAGMENT)
        val routine = manager.findFragmentByTag(TAG_ROUTINE_FRAGMENT)
        val guide = manager.findFragmentByTag(TAG_GUIDE_FRAGMENT)
        val mypage = manager.findFragmentByTag(TAG_MYPAGE_FRAGMENT)
        val guide_click = manager.findFragmentByTag(TAG_GUIDE_CLICK_FRAGMENT)
        val guide_sport = manager.findFragmentByTag(TAG_GUIDE_SPORT_FRAGMENT)
        val routine_detail = manager.findFragmentByTag(TAG_ROUTINE_DETAIL_FRAGMENT)

        if (home != null) {
//            transaction.remove(home)
            transaction.hide(home)
            Log.d("fragment", "home hide")
        }
        if (rank != null) {
            transaction.hide(rank)
//            transaction.detach(rank)
        }
        if (routine != null) {
            transaction.remove(routine)
//            transaction.remove(routine)
        }
        if (guide != null) {
            transaction.hide(guide)
//            transaction.remove(guide)
        }
        if (mypage != null) {

            transaction.hide(mypage)
        }
        if (guide_click != null) {
            transaction.remove(guide_click)
            Log.d("fragment", "gudie click removed")
        }
        if (guide_sport != null) {
            transaction.remove(guide_sport)
            Log.d("fragment", "guide sport removed")
        }
        if (routine_detail != null) {
            transaction.remove(routine_detail)
            Log.d("fragment", "routine Detail removed")
        }
        when (tag) {
            TAG_HOME_FRAGMENT -> {

                if (home != null) {
                    transaction.show(home)
                }
            }
            TAG_RANK_FRAGMENT -> {

                if (rank != null) {
                    transaction.show(rank)
                }
            }
            TAG_ROUTINE_FRAGMENT -> {

                if (routine != null) {
                    if (routine_detail != null) {
                        transaction.remove(routine_detail)
                    }
                    transaction.remove(routine)
                    transaction.add(R.id.frameLayout, routine, TAG_ROUTINE_FRAGMENT)
                    transaction.show(routine)
                }
            }
            TAG_GUIDE_FRAGMENT -> {

                if (guide != null) {
                    if (guide_click != null) {
                        transaction.remove(guide_click)
                    }
                    if (guide_sport != null) {
                        transaction.remove(guide_sport)
                    }
                    transaction.show(guide)
                }
            }

            TAG_MYPAGE_FRAGMENT -> {
                if (mypage != null) {
                    transaction.show(mypage)
                }
            }
        }

        transaction.commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initRecycler() {
        userRankAdapter = UserRkAdapter(this)
        rankingRecyclerView?.adapter = userRankAdapter
        userRankAdapter.datas = datas
        userRankAdapter.notifyDataSetChanged()
    }

    fun emptyRecycler() {
        userRankAdapter = UserRkAdapter(this)
        rankingRecyclerView?.adapter = null
    }

    private fun setUserRank() {
        val queue = Volley.newRequestQueue(this)
        val currentUser = sharedManager.getCurrentUser()

        val url_getUserRank = JSP.getUserRank(currentUser.id.toString())
//        Log.d("currentID", currentUser.id.toString())
        val StringRequest2 = StringRequest(
            Request.Method.GET, url_getUserRank, { response ->
                response.trim { it <= ' ' }

                val details2 = (response.trim().split(",")).toTypedArray()
//                Log.d("rankingresponse", details2[0])
//                val userPoint: Int

//                Toast.makeText(this, "유저의 운동 points:${details2[2]}", Toast.LENGTH_SHORT).show()
                sharedManager.setUserPoint(currentUser, details2[2])
            }, {
                Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show()
            })
        queue.add(StringRequest2)

    }

    //소속별 랭크 받아오고 랭크 어댑터에 data init
    fun setRankData() {
        val queue = Volley.newRequestQueue(this)
        val currentUser = sharedManager.getCurrentUser()
        val url_getEveryRank = JSP.getEveryRank(currentUser.belong.toString())

        val StringRequest3 = StringRequest(
            Request.Method.GET, url_getEveryRank, { response ->
                response.trim { it <= ' ' }

                val details3 = (response.trim().split(",")).toTypedArray()
                datas.clear()
                for (i in 0 until (details3.size) - 3 step 3) {

                    datas.apply {
                        add(
                            User(
                                img = R.drawable.penguin,
                                id = details3[i + 1],
                                points = details3[i + 2]
                            )
                        )

                    }
                    Log.d("ranklist", "${details3[i + 1]} ${details3[i + 2]}")
                }
                initRecycler()

            }, {
                Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show()
            })
        queue.add(StringRequest3)
    }

    @SuppressLint("SetTextI18n")
    fun showExerciseDonePopup(name: String?, minute: Int, second: Int, context: Context) {
        val dialog = android.app.AlertDialog.Builder(context).create()
        val currentUser = sharedManager.getCurrentUser()
        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_exercise_done, null)
        val point = 120 * minute + 2 * second
        val endComment: TextView = mView.findViewById(R.id.txt_popup_exercise_time)
        val time: TextView = mView.findViewById(R.id.txt_popup_point)

        val exit: Button = mView.findViewById<Button>(R.id.btn_exercise_exit)
        val redo: Button = mView.findViewById<Button>(R.id.btn_exercise_redo)
        val newPoint = point + (currentUser.points)!!.toInt()

        endComment.text = "${name} ${minute}분 ${second}초 했습니다!!!"
        time.text = "획득한 Point \n $point point !!!"
        viewModel.editPoint(newPoint.toString())
//        Log.d("userPoint",currentUser.points.toString())
//        sharedManager.setUserPoint(currentUser,newPoint.toString())
        setUserDBPoints(currentUser, newPoint.toString())

//        Log.d("userinfo", currentUser.countDays.toString())
        Toast.makeText(this, "${currentUser.points}", Toast.LENGTH_SHORT).show()
        exit.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
//            finish()
        }
        redo.setOnClickListener {
            Toast.makeText(this, "운동 다시 하기 ", Toast.LENGTH_SHORT).show()
        }


        dialog.setView(mView)
        dialog.create()
        dialog.show()
    }

    fun startExcercise(exname: String?, minute: Int, second: Int) {
        // 점수 계산을 위한 운동 시간 설정 -> GuideSportsFragment.showTimeSettingPopup()
        // 안내 & 카메라 사용 시작
//        settingStart()

        // Initialize EXNAME,MINUTE,SECOND received from GuideSportsFragment
        if (exname != null) {
            this.exname = exname
        }
        this.minute = minute
        this.second = second



        val intent = Intent(this, SettingLivePreviewActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        intent.putExtra("ExcerciseName", exname) //English Name
        intent.putExtra("minute", minute)
        intent.putExtra("second", second)
        activityResultLauncher.launch(intent)
    }

    fun setUserDBPoints(user: User, points: String) {
        val longNow = System.currentTimeMillis()

        val currentUser: User = sharedManager.getCurrentUser()
        val queue = Volley.newRequestQueue(this)
//        Log.d("userinfo", currentUser.countDays.toString())

        val url_setUserPoints = JSP.getUserPointSet(
            user.id.toString(),
            points,
            currentUser.recentDay.toString(),
            currentUser.countDays.toString()
        )
//        Log.d("userinfo", "$url_setUserPoints 앱네 countdays ${currentUser.countDays}")

        val StringRequest = StringRequest(
            Request.Method.GET, url_setUserPoints, { response ->
                response.trim { it <= ' ' }
//                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                val details3 = (response.trim().split(",")).toTypedArray()
//                Log.d("userinfo", "details ${details3[0]}, ${details3[1]}")
                //details3[0] 은 운동 count details[1] 이 최근 날짜 response
//                Log.d("Point", "유저의 Point가 $points 로 업데이트되었습니다.")
                viewModel.editExerciseDay(details3[1], details3[0].toInt())
//                Log.d("userinfo", "앱네 count days ${currentUser.countDays}")

            }, {
                Toast.makeText(this, "server error here", Toast.LENGTH_SHORT).show()
            })
        queue.add(StringRequest)
    }

    fun rankingRefresh() {
        emptyRecycler()
        setRankData()
        initRecycler()
    }

    fun showTimeSettingPopup(exEname: String?,exname_k : String?, context: Context) {

        val dialog = android.app.AlertDialog.Builder(context).create()

        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_settime, null)

        val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min)
        val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec)
        val selected_name: TextView = mView.findViewById(R.id.time_exercise_name)
        val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no)
        val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok)

        selected_name.text = exname_k
        // editText 설정해제
        minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        second.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        //최소값 설정
        minute.minValue = 0
        second.minValue = 0

        //최대값 설정
        minute.maxValue = 30
        second.maxValue = 59
        //기본값 설정
        minute.value = 1
        second.value = 0

        //보여질 값 설정


        //취소버튼
        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        start.setOnClickListener {
            Toast.makeText(context, "${minute.value}분 ${second.value}초", Toast.LENGTH_SHORT).show()
            startExcercise(exEname, minute.value, second.value)
            dialog.dismiss()
        }
        dialog.setView(mView)
        dialog.create()
        dialog.show()
        dialog.window!!.setLayout(750, WindowManager.LayoutParams.WRAP_CONTENT)

    }

    fun findUserId(): String {
        val currentUser = sharedManager.getCurrentUser()
        return currentUser.id.toString()
    }

    fun getAlertShow(title: String, message: String, function: () -> Unit) {

        val buttonOk = "확인"
        val buttonNo = "취소"

        builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton(buttonOk, DialogInterface.OnClickListener { dialog, which ->
            function()
        })
        builder.setNegativeButton(buttonNo, DialogInterface.OnClickListener { dialog, which ->
            alertDialog.dismiss()
        })
        alertDialog = builder.create()
        alertDialog.show()
    }


    companion object {
        lateinit var context_main: Any
        const val TAG_HOME_FRAGMENT = "home"
        const val TAG_RANK_FRAGMENT = "rank"
        const val TAG_ROUTINE_FRAGMENT = "routine"
        const val TAG_GUIDE_FRAGMENT = "guide"
        const val TAG_MYPAGE_FRAGMENT = "mypage"
        const val TAG_GUIDE_CLICK_FRAGMENT = "guide_click"
        const val TAG_GUIDE_SPORT_FRAGMENT = "guide_sport"
        const val TAG_ROUTINE_DETAIL_FRAGMENT = "routine_detail"
        private const val SETTING_OK = 2
    }


}


