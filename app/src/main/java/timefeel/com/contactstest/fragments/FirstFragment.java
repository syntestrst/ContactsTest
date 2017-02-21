package timefeel.com.contactstest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timefeel.com.contactstest.R;
import timefeel.com.contactstest.adapter.ContactsAdapter;
import timefeel.com.contactstest.models.ContactsResponse;



/**
 * Created by test on 16/02/2017.
 */

public class FirstFragment extends Fragment {

    private String urlGet = "http://www.storage42.com/contacts";
    private static final String TAG = FirstFragment.class.getSimpleName();

    private final Gson gson = new Gson();
    private OkHttpClient client;
    private RecyclerView recyclerView;
    public static ContactsResponse contactsresponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.first_fragment, container, false);
        /**
         * Recycler View
         */
        recyclerView = (RecyclerView) rootView.findViewById(R.id.contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //recyclerView.setAdapter(null);
        try {
            getAStuff();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }


    /**
     *  Build request
     */
    private void getAStuff() throws IOException {
        Request request = new Request.Builder()
                .url(urlGet)
                /*.cacheControl(new CacheControl.Builder().onlyIfCached().build())*/
                .get()
                .build();
    /**
     *  Asynchrone Get with method enqueue
     *  and callBack
     */
        getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.getMessage());
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                Log.d(TAG, "Headers Response");
                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                /**
                 * Note that ResponseBody.charStream() uses the Content-Type response
                 * header to select which charset to use when decoding the response body.
                 * It defaults to UTF-8 if no charset is specified.
                 */

                // Process the data on the worker thread
                contactsresponse = gson.fromJson(response.body().charStream(), ContactsResponse.class);
                System.out.println(contactsresponse.getMe().getEmail());
                // Access deserialized pojo object here
                /**
                 * SetAdapter
                 */
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new ContactsAdapter(contactsresponse.getContacts(), R.layout.list_item_contacts, getActivity().getApplicationContext()));

                    }
                });

            }
        });

    }

    private OkHttpClient getClient() {
        if (client == null) {
            //Assigning a CacheDirectory
            File myCacheDir = new File(getActivity().getCacheDir(), "OkHttpCache");
            //you should create it...
            int cacheSize = 2024 * 2024;
            Cache cacheDir = new Cache(myCacheDir, cacheSize);
            client = new OkHttpClient.Builder()
                   // .addNetworkInterceptor(new StethoInterceptor())
                    .cache(cacheDir)
                    .build();
        }
        return client;
    }

}
