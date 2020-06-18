package com.example.competition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    final String TAG = "List";
    Handler handler;
    java.util.List<String> liList = new ArrayList<String>();
    java.util.List<String> herfList = new ArrayList<String>();
    java.util.List<String> liList2 = new ArrayList<String>();
    java.util.List<String> herfList2 = new ArrayList<String>();
    java.util.List<String> liList3 = new ArrayList<String>();
    java.util.List<String> herfList3 = new ArrayList<String>();
    private int page = 0;
    private int page2 = 0;
    private int page3 = 0;
    private int tbNum;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        SharedPreferences sp = getSharedPreferences("TB", Activity.MODE_PRIVATE);
        tbNum = sp.getInt("TB_NUM",1);

        adapter = new ArrayAdapter<String>(List.this,android.R.layout.simple_list_item_1,liList);
    }

    //返回时自动刷新
    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: 返回刷新");
        InfoManager infoManager = new InfoManager(this);
        liList.clear();
        herfList.clear();
        for (InfoItem item:infoManager.listAll("tb_c"+tbNum)){
            liList.add(item.getInfo());
            Log.i(TAG, "run: 已从数据库中获得info:"+item.getInfo());
            herfList.add(item.getHerf());
            Log.i(TAG, "run: 已从数据库中获得herf:"+item.getHerf());
        }
        Log.i(TAG, "run: 已从数据库中获得数据");

        ListView listView = findViewById(R.id.lv_detail);
        listView.setAdapter(adapter);
    }

    //菜单操作
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.academy,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.Account){
            Intent toA = new Intent(this, AccountCom.class);
            startActivity(toA);
            Log.i(TAG, "onOptionsItemSelected: 打开会计学院");
        }else if (item.getItemId()==R.id.BA){
            Intent toB = new Intent(this, ForeignCom.class);
            startActivity(toB);
            Log.i(TAG, "onOptionsItemSelected: 打开工商管理学院");
        }
        return super.onOptionsItemSelected(item);
    }

    //打开界面
    public void openLFinance(View btn) {
        Log.i(TAG, "openOne:open ");
        Intent toF = new Intent(this, FinanceCom.class);
        startActivity(toF);
    }

    public void openMath(View btn) {
        Log.i(TAG, "openOne:open ");
        Intent tomath = new Intent(this, MathCom.class);
        startActivity(tomath);
    }

    public void openInfo(View btn) {
        Log.i(TAG, "openOne:open ");
        Intent toInfo = new Intent(this, InfoCom.class);
        startActivity(toInfo);
    }

}
