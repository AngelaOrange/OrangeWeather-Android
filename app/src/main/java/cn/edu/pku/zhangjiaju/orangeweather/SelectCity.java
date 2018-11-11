package cn.edu.pku.zhangjiaju.orangeweather;

import net.sourceforge.pinyin4j.PinyinHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import cn.edu.pku.zhangjiaju.app.MyApplication;

import cn.edu.pku.zhangjiaju.cn.edu.pku.zhangjiaju.bean.City;

public class SelectCity extends Activity implements View.OnClickListener {

    private ImageView mBackBtn;
    private SearchView searchView;

    private ArrayList<String> mSearchResult = new ArrayList<>(); //搜索结果，只放城市名
    private Map<String,String> nameToCode = new HashMap<>(); //城市名到编码
    private Map<String,String> nameToPinyin = new HashMap<>(); //城市名到拼音

    private ArrayAdapter<String> adapter;
    private String returnCode = "101010100"; //默认值为北京的代码


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

        cityselected = (TextView) findViewById(R.id.title_name);
        Log.i("City",listcity.get(1).getCity());
        for(int i = 0; i<listSize; i++){
            city[i] = listcity.get(i).getCity();
            Log.d("City",city[i]);
        }
        listView = (ListView) findViewById(R.id.city_list);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SelectCity.this,"您已选择"+city[position],Toast.LENGTH_SHORT).show();
//                cityselected.setText("当前城市："+city[position]);
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String returnCityName = mSearchResult.get(position);
                Toast.makeText(SelectCity.this,"您已选择"+returnCityName,Toast.LENGTH_SHORT).show();
                returnCode = nameToCode.get(returnCityName);
                cityselected.setText("当前城市："+returnCityName);
            }
        });
//        initListView();


        searchView = (SearchView) findViewById(R.id.search);
        searchView.setIconified(true); //需要点击搜索图标，才展开搜索框
        searchView.setQueryHint("请输入城市名称或拼音");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){
                    if(mSearchResult != null)
                        mSearchResult.clear();

                    for(String str : nameToPinyin.keySet()){
                        if(str.contains(newText)||nameToPinyin.get(str).contains(newText)){
                            mSearchResult.add(str);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String newText) {
                Toast.makeText(SelectCity.this,"检索中", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        initSearchView();

    }

    protected void initSearchView(){
        MyApplication myApplication_2 = MyApplication.getInstannce();
        ArrayList<City> mCityList = (ArrayList<City>) myApplication_2.getCityList();
        String strName;
        String strNamePinyin;
        String strCode;
        for(City city: mCityList){
            strCode = city.getNumber();
            strName = city.getCity();
//            strNamePinyin = PinYin.converterToSpell(strName);
            nameToCode.put(strName,strCode);
//            nameToPinyin.put(strName,strNamePinyin);
            mSearchResult.add(strName);
        }
        adapter = new ArrayAdapter<>(SelectCity.this,android.R.layout.simple_list_item_single_choice,mSearchResult);
        listView.setAdapter(adapter);

    }


    public void initListView() {

//        ArrayAdapter<String>adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_single_choice,city);
        adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_single_choice,city);

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
//            case R.id.title_back:
//                int position = listView.getCheckedItemPosition();
//                Log.d("position",Integer.toString(position));
//                Intent i = new Intent();
//                String select_citycode;;
//                String city;
//                // -1是用于判断用户是否点选了城市，如果没点选，则position值为-1，默认显示兰州的天气
//                if (position != -1){
//                    select_citycode = listcity.get(position).getNumber();
//                    city = listcity.get(position).getCity();
//                } else{
//                    select_citycode = "101160101";
//                    city = "兰州";
//                }
//                i.putExtra("cityCode",select_citycode);
//                setResult(RESULT_OK,i);
//                Log.d("citycode",select_citycode);
//                Log.d("citycode",city);
//                finish();
//                break;
            case R.id.title_back:
//                int position = listView.getCheckedItemPosition();
//                Log.d("position",Integer.toString(position));
                Intent i = new Intent();

                i.putExtra("cityCode",returnCode);
                setResult(RESULT_OK,i);
//                Log.d("citycode",select_citycode);
//                Log.d("citycode",city);
                finish();
                break;

            default:
                break;
        }
    }

}
