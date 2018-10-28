package cn.edu.pku.zhangjiaju.orangeweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.List;

import cn.edu.pku.zhangjiaju.app.MyApplication;
import cn.edu.pku.zhangjiaju.cn.edu.pku.zhangjiaju.bean.City;

public class SelectCity extends Activity implements View.OnClickListener {

    private ImageView mBackBtn;
    private ListView mListView;
    private String[] data={"第1组","第2组","第3组","第4组","第5组","第6组", "第7组","第8组","第9组","第10组","第11组","第12组","第13组", "第14组","第15组","第16组","第17组","第18组","第19组","第20组",
            "第21组","第22组"};

    List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        initViews();
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
    }

    public void initViews() {
        mListView = (ListView) findViewById(R.id.city_list);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_expandable_list_item_1,data);
        mListView.setAdapter(adapter);

        MyApplication myApplication = (MyApplication) getApplication();
        cityList = myApplication.getCityList();
//        for(City city: cityList) {
//            filterDateList.add(city);
//        }


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                Intent i = new Intent();
                i.putExtra("cityCode","101160101");
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }

}
