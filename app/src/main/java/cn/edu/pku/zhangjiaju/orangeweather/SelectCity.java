package cn.edu.pku.zhangjiaju.orangeweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.edu.pku.zhangjiaju.app.MyApplication;

import cn.edu.pku.zhangjiaju.cn.edu.pku.zhangjiaju.bean.City;

public class SelectCity extends Activity implements View.OnClickListener {

    private ImageView mBackBtn;

    private ListView listView = null;
    private TextView cityselected = null;

    private List<City> listcity = MyApplication.getInstannce().getCityList();
    private int listSize = listcity.size();
    private String[] city = new String[listSize];


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);

        initListView();

    }

    public void initListView() {
        cityselected = (TextView) findViewById(R.id.title_name);
        Log.i("City",listcity.get(1).getCity());
        for(int i = 0; i<listSize; i++){
            city[i] = listcity.get(i).getCity();
            Log.d("City",city[i]);
        }
        listView = (ListView) findViewById(R.id.city_list);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_single_choice,city);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SelectCity.this,"您已选择"+city[position],Toast.LENGTH_SHORT).show();
                cityselected.setText("当前城市："+city[position]);
            }
        });

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                int position = listView.getCheckedItemPosition();
                Intent i = new Intent();
                String select_citycode = listcity.get(position).getNumber();
                String city = listcity.get(position).getCity();
//                i.putExtra("cityCode","101160101");
                i.putExtra("cityCode",select_citycode);
                setResult(RESULT_OK,i);
                Log.d("citycode",select_citycode);
                Log.d("citycode",city);
                finish();
                break;
            default:
                break;
        }
    }

}
