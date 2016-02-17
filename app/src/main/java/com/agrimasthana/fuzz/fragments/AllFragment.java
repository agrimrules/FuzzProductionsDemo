package com.agrimasthana.fuzz.fragments;

//import android.support.v4.app.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.agrimasthana.fuzz.Model.Data;
import com.agrimasthana.fuzz.R;
import com.agrimasthana.fuzz.Rest.ApiAccess;
import com.agrimasthana.fuzz.Utils.AllContentAdapter;
import com.agrimasthana.fuzz.Utils.SharedprefAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by agrimasthana on 2/12/16.
 */
public class AllFragment extends Fragment {
    ApiAccess api = new ApiAccess();
    SharedprefAdapter shp = new SharedprefAdapter();
    ArrayList<String> st = new ArrayList<>();
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sample_list_detail_list, container, false);
        lv = (ListView) view.findViewById(R.id.list);
        Call<List<Data>> dt = api.loadAPI();
        dt.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Response<List<Data>> response, Retrofit retrofit) {
                List<Data> fin = response.body();
                for (Data dt : fin) {
                    if (dt.getData() != null && !dt.getData().equals("")){
                        st.add(dt.getData());
                    }
                }
                shp.setStringArrayPref(getContext(), "json", st);
                AllContentAdapter adapter = new AllContentAdapter(getContext(), st);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle bundle = new Bundle();
                        String resource = st.get(position);
                        if(resource.matches("(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*\\.(?:jpg|gif|png|jpeg))(?:\\?([^#]*))?(?:#(.*))?"))
                        {
                            String url = resource;
                            bundle.putString("urlString", url);
                        }
                        else
                        {
                        String url = "https://fuzzproductions.com/";
                        bundle.putString("urlString", url);
                        }
                        Intent intent = new Intent(getContext(), FuzzWebView.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Log.w("Failure", "");
            }

        });
        return view;
    }

}
