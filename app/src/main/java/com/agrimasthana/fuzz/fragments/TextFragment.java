package com.agrimasthana.fuzz.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.agrimasthana.fuzz.Model.Data;
import com.agrimasthana.fuzz.R;
import com.agrimasthana.fuzz.Rest.ApiAccess;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

//import android.support.v4.app.ListFragment;

/**
 * Created by agrimasthana on 2/12/16.
 */
public class TextFragment extends ListFragment {
    ApiAccess api = new ApiAccess();
    ArrayList<String>text=new ArrayList<>();
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle bundle = new Bundle();
        String url = "https://fuzzproductions.com/";
        bundle.putString("urlString",url);
        Intent intent = new Intent(getContext(),FuzzWebView.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Call<List<Data>> dt = api.loadAPI();
        dt.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Response<List<Data>> response, Retrofit retrofit) {
                List<Data> fin = response.body();
                for(Data dt : fin){
                    if(dt.getData() !=null && dt.getData().matches(".*\\w.*") && dt.getType().equals("text") && !dt.getData().matches("(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*\\.(?:jpg|gif|png|jpeg))(?:\\?([^#]*))?(?:#(.*))?")) {
                        text.add(dt.getData());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,text);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        return inflater.inflate(R.layout.content_text, container, false);
    }
}
