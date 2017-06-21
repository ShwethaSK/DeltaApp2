package com.example.shwethaskumar.deltaapp2;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE1 = 20;
    private ImageView imgPicture;
    LinearLayout mlayout;
    Bitmap bitmap;
    String picturePath;
    Uri imageUri;
    EditText TV;
    int m = 0;
    int k = 0;
    int l=0;
    Intent photo;
    File picture;
    Uri data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mlayout = (LinearLayout) findViewById(R.id.action_settings);
        imgPicture = new ImageView(this);
        imgPicture.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void add_photos(View view) {
        photo = new Intent(Intent.ACTION_PICK);
        picture = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        picturePath = picture.getPath();
        data = Uri.parse(picturePath);
        photo.setDataAndType(data, "image/*");
        startActivityForResult(photo, REQUEST_CODE1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE1) {
                imageUri = data.getData();
                InputStream inputStream;
                final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                final ImageView imgPicture = new ImageView(this);
                imgPicture.setLayoutParams(lparams);
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imgPicture.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
                m = 1;
                mlayout.addView(imgPicture);
                imgPicture.setId(View.generateViewId());
                imgPicture.setClickable(true);
                imgPicture.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        delete_image(imgPicture);
                    }
                });
             }
        }
        TV = new EditText(this);
        TV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TV.setText("Enter caption");
        TV.setTextSize(30);
        TV.setTextColor(Color.MAGENTA);
        TV.setTypeface(Typeface.DEFAULT_BOLD);
        mlayout.addView(TV);
    }

    public void delete_image(View view) {
        if (k == 1) {
            int x = view.getId();
            final ImageView iv = (ImageView) findViewById(x);
             mlayout.removeView(iv);
        }
    }

   public void delete_photo(View view) {
       Toast.makeText(this, "Click on the images you wish to delete", Toast.LENGTH_LONG).show();
       k = 1;
   }
     public void delete_cap(View view){
    if(l==1)
    {
        int x=view.getId();
        final TextView tV=(TextView)findViewById(x);
        mlayout.removeView(tV);
    }
}



   public void changes_saved(View view){
       Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
       k=0;
       l=0;
       if(m==1) {
           final TextView tv = new TextView(this);
           tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
           tv.setText(TV.getText().toString());
           tv.setTextSize(30);
           tv.setTypeface(Typeface.DEFAULT_BOLD);
           tv.setTextColor(Color.MAGENTA);
           tv.setClickable(true);
           tv.setId(View.generateViewId());
           mlayout.addView(tv);
           mlayout.removeView(TV);
           m=0;
           tv.setOnClickListener(new View.OnClickListener(){
               public void onClick(View view){
                   delete_cap(tv);
               }
           });
       }
   }

    public void delete_caption(View view) {
        Toast.makeText(this, "Click on captions you wish to delete", Toast.LENGTH_SHORT).show();
        l=1;
    }
 }
