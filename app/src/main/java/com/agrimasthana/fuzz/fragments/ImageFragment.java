package com.agrimasthana.fuzz.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.agrimasthana.fuzz.R;
import com.agrimasthana.fuzz.Utils.AllContentAdapter;
import com.agrimasthana.fuzz.Utils.SharedprefAdapter;

import java.util.ArrayList;

/**
 * Created by agrimasthana on 2/12/16.
 */
public class ImageFragment extends Fragment {
    SharedprefAdapter shp = new SharedprefAdapter();
    ArrayList<String> images = new ArrayList<>();
    ListView lv;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_list_detail_list, container, false);
        lv = (ListView) view.findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                String url = images.get(position);
                bundle.putString("urlString",url);
                Intent intent = new Intent(getContext(),FuzzWebView.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        ArrayList<String> fromsp = shp.getStringArrayPref(getContext(),"json");
        for(String str: fromsp)
        {
            if(str.matches("(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*\\.(?:jpg|gif|png|jpeg))(?:\\?([^#]*))?(?:#(.*))?"))
            {
                images.add(str);
            }
        }
        AllContentAdapter adapter = new AllContentAdapter(getContext(), images);
        lv.setAdapter(adapter);
        return view;
    }



}
