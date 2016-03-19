package com.example.mom.datenyc.FourSquareAPI;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mom.datenyc.R;
import com.foursquare.android.nativeoauth.FoursquareCancelException;
import com.foursquare.android.nativeoauth.FoursquareDenyException;
import com.foursquare.android.nativeoauth.FoursquareInvalidRequestException;
import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.FoursquareOAuthException;
import com.foursquare.android.nativeoauth.FoursquareUnsupportedVersionException;
import com.foursquare.android.nativeoauth.model.AccessTokenResponse;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;
import com.squareup.picasso.Picasso;
import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.Region;
import com.yelp.clientlib.entities.SearchResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationPage extends AppCompatActivity {

    YelpAPI yelpAPI;
    FloatingActionButton mBx;

    private final String consumerKey = "biD2L5UtLWUB3aYjEegIyw";
    private final String consumerSecret = "RKwhAlFerfB2NwdFG9_9SAE7p3Y";
    private final String token = "I_tC-YrL1nA4QfK7NFPJrIIrqqoGodNz";
    private final String tokenSecret = "eua-2OsRU8dnbK7P7YyDdGLuKJ0";

    private static final int REQUEST_CODE_FSQ_CONNECT = 200;
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201;
    String CLIENT_ID = "I304IKBD3PKC0HMR0QAHODBI3KRWGHCYHO4G0LJOU5HROYL2";
    String CLIENT_SECRET = "A2YTKVC5HOCJ4NPXWAP5PZML2N1AEPDQX5SLG53MVLMV4HNP";

    FloatingActionButton mBk, mQu, mMa, mSi;

    private String businessId = "joju-elmhurst";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ImageView backgroundTwo = (ImageView) findViewById(R.id.backgroundTwo);

        Picasso.with(LocationPage.this).load("https://s-media-cache-ak0.pinimg.com/564x/b6/f1/34/b6f1340783278be2c97535f2674f6f49.jpg").fit().into(backgroundTwo);

        mBk = (FloatingActionButton) findViewById(R.id.brooklyn);
        mBk.setImageResource(R.drawable.bk);
        mBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchYelp();
            }
        });

        mBx = (FloatingActionButton) findViewById(R.id.fab);
        mBx.setImageResource(R.drawable.bx);

        mQu = (FloatingActionButton) findViewById(R.id.queens);
        mQu.setImageResource(R.drawable.qu);

        mMa = (FloatingActionButton) findViewById(R.id.manhattan);
        mMa.setImageResource(R.drawable.ma);

        mSi = (FloatingActionButton) findViewById(R.id.staten);
        mSi.setImageResource(R.drawable.si);

        YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
        yelpAPI = apiFactory.createAPI();
    }


    public void searchYelp() {

        Callback<Business> callback = new Callback<Business>() {
            @Override
            public void onResponse(Response<Business> response, Retrofit retrofit) {
                Business business = response.body();
                String name= business.name();
                double house= business.rating();
                TextView textView= (TextView)findViewById(R.id.tvMessage);
//                textView.setText(String.valueOf(house));
                textView.setText(name);
                // Update UI text with the Business object.
            }
            @Override
            public void onFailure(Throwable t) {
                // HTTP error happened, do something to handle it.
            }
        };

        Call<Business> call = yelpAPI.getBusiness(businessId);
        call.enqueue(callback);

//        Response<Business> response = call.execute();
//        Business business = response.body();
//
//        String businessName = business.name();  // "JapaCurry Truck"
//        Double rating = business.rating();  // 4.0
//
//        Log.d("data", businessName);

    }
}

