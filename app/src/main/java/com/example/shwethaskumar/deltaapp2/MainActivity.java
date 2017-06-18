package com.example.shwethaskumar.deltaapp2;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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
    int m=0;
    int num=0;
    int resId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mlayout=(LinearLayout) findViewById(R.id.action_settings);
        imgPicture=new ImageView(this);
        imgPicture.setImageBitmap(bitmap);
}   @Override
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

    public void add_photos(View view){
        Intent photo=new Intent(Intent.ACTION_PICK);
       File picture=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        picturePath=picture.getPath();
        Uri data= Uri.parse(picturePath);
        photo.setDataAndType(data,"image/*");
        startActivityForResult(photo, REQUEST_CODE1);
    }

@Override
protected void onActivityResult(int requestCode,int resultCode, Intent data)
{if(resultCode==RESULT_OK) {
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
        mlayout.addView(imgPicture);
        String imgId="image"+num;
        resId=getResources().getIdentifier(imgId, "id", getPackageName());
        imgPicture.setId(resId);
    }
}
m=1;
    TV=new EditText(this);
    TV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
    TV.setText("Enter caption");
    mlayout.addView(TV);
}
 /*  public void delete_photo(View view){
       Toast.makeText(this, "Click on the photo you wish to delete", Toast.LENGTH_LONG).show();
       final ImageView imgPicture1=(ImageView)findViewById(R.id.imageView);
       final TextView tv1=(TextView)findViewById(R.id.textView);
       final ImageView imgPicture2=(ImageView)findViewById(R.id.imageView2);
       final TextView tv2=(TextView)findViewById(R.id.textView2);
       final ImageView imgPicture3=(ImageView)findViewById(R.id.imageView3);
       final TextView tv3=(TextView)findViewById(R.id.textView3);
       final ImageView imgPicture4=(ImageView)findViewById(R.id.imageView4);
       final TextView tv4=(TextView)findViewById(R.id.textView4);
       final ImageView imgPicture5=new ImageView(this);
       //deleting the added image
       imgPicture5.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               int id=imgPicture5.getId();
               mlayout.removeView((ImageView)findViewById(id));

           }
       });
       //deleting the first image set
      imgPicture1.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
              mlayout.removeView(imgPicture1);
               mlayout.removeView(tv1);
   }
   });
       //deleting the second image set
       imgPicture2.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               mlayout.removeView(imgPicture2);
               mlayout.removeView(tv2);
           }
       });
       //deleting the third image set
       imgPicture3.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               mlayout.removeView(imgPicture3);
               mlayout.removeView(tv3);
           }
       });
       //deleting the fourth image set
       imgPicture4.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               mlayout.removeView(imgPicture4);
               mlayout.removeView(tv4);
           }
       });
   }*/
   public void changes_saved(View view){
       Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
       if(m==1) {
           TextView tv = new TextView(this);
           tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
           tv.setText(TV.getText().toString());
           mlayout.addView(tv);
           mlayout.removeView(TV);
           m=0;
       }
   }
}
