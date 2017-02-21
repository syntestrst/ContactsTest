package timefeel.com.contactstest.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import timefeel.com.contactstest.R;

/**
 * Created by test on 18/02/2017.
 */
public class DetailActivity extends AppCompatActivity{
    private Toolbar mToolbar;
    private TextView name;
    private TextView email;
    private TextView phone;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout item
        setContentView(R.layout.detail_item);

        // Set a Toolbar to replace the ActionBar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Toast.makeText(getApplicationContext(), "detail activity",Toast.LENGTH_SHORT).show();

        // set object view in layout
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        //TODO: downloading image with okhttp from json url and load it with Glide or Volley
        // use inputstream outputstream or okIO
        //image = (ImageView) findViewById(R.id.thumbnail);

         //Get all Extras from intent
        name.setText(getIntent().getExtras().getString("name"));
        email.setText(getIntent().getExtras().getString("email"));
        phone.setText(getIntent().getExtras().getString("phone"));



    }
}
