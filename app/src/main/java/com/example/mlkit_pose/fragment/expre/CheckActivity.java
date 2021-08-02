//package com.example.mlkit_pose.fragment.expre;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.example.mlkit_pose.R;
//
//public class CheckActivity implements OnClickListener {
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);// 액티비티 생성
//        setContentView(R.layout.check);// check.xml
//        //버튼 선언
//        Button button=(Button)findViewById(R.id.button_result);
//        button.setOnClickListener(this);
//
//        // option1 체크박스가 눌렸을 때
//        findViewById(R.id.option1).setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Checked(v); // 체크되었을 때 동작코드
//            }
//        });
//
//        // option2 체크박스가 눌렸을 때
//        findViewById(R.id.option2).setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Checked(v); // 체크되었을 때 동작코드
//            }
//        });
//
//    }
//
//    public String Checked(View v) { // 체크되었을 때 동작할 메소드 구현
//        // TODO Auto-generated method stub
//        CheckBox option1 = (CheckBox) findViewById(R.id.option1); // option1체크박스
//        // 선언
//        CheckBox option2 = (CheckBox) findViewById(R.id.option2); // option1체크박스
//        // 선언
//
//        String resultText = ""; // 체크되었을 때 값을 저장할 스트링 값
//        if (option1.isChecked()) { // option1 이 체크되었다면
//            resultText = "option1";
//        }
//        if (option2.isChecked()) {
//            resultText = "option2"; // option2 이 체크되었다면
//        }
//
//        return resultText; // 체크된 값 리턴
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        if (v.getId() == R.id.button_result) { //button_result 이라는 버튼이 생성되었다고 가정.
//            Intent it = new Intent(this, MainActivity.class); // MainActivity.java로  보내기 위한  인텐트 선언
//
//            it.putExtra("it_check", Checked(v)); // it_check 라는 이름하에 체크된 값을 전달
//            startActivity(it); // MainActivity.java를 실행하면서 값을 전달
//        }
//    }
//}
