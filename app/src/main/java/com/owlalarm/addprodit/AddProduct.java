package com.owlalarm.addprodit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.jar.Attributes;

public class AddProduct extends AppCompatActivity {


          ImageView img;
          Button upload;



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        Button upload = (Button)findViewById(R.id.upload);
        img=(ImageView)findViewById(R.id.gallery);

       upload.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent
                ( Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Select Your Photo"), 1);
            }
        });
            }




        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // if(resultCode == RESULT_OK) تعني ان كان قد تم الحصول على البيانات بدون مشاكل
            if (requestCode == 1 && resultCode == RESULT_OK) {
                Uri photo_uri = data.getData();
                Bitmap selected_photo = null;
                try {
                    InputStream imagestream = getContentResolver().openInputStream(photo_uri);
                    selected_photo = BitmapFactory.decodeStream(imagestream);
                    img.setImageBitmap(selected_photo);
                } catch (FileNotFoundException FNFE) {
                    Toast.makeText(AddProduct.this, FNFE.getMessage(), Toast.LENGTH_LONG).show();
                }

                //للحفاظ على مقاسات الصوؤة
                selected_photo = Bitmap.createScaledBitmap
                        (selected_photo, 200, 200, true);
                img.setImageBitmap(selected_photo);

                //لعدم دوران الصورة
                Matrix matrix =new Matrix();
                matrix.postRotate(0);
                Bitmap rotated_photo = Bitmap.createBitmap(selected_photo,0,0,
                        selected_photo.getWidth(),selected_photo.getHeight(),matrix,true);

            }
        }


    public void btn_add(View view) {
        ImageView img =(ImageView)findViewById(R.id.gallery);
        EditText Name =(EditText)findViewById(R.id.Name);
        EditText p_Name =(EditText)findViewById(R.id.p_Name);
        EditText address =(EditText)findViewById(R.id.address);
        EditText available =(EditText)findViewById(R.id.available);
        EditText price =(EditText)findViewById(R.id.price);


        Intent send= new Intent(this,NomDeProduct.class);
        Bundle b=new Bundle();
        //لكي تظهر الصورة لابد من تغير get ImageMatrix
        b .putString("img",img.getImageMatrix().toString());
        b.putString("Name",Name.getText().toString());
        b.putString("p_name",p_Name.getText().toString());
        b.putString("address",address.getText().toString());
        b.putString("available",available.getText().toString());
        b.putString("price",price.getText().toString());
          send.putExtras(b);


        startActivity(send);


    }
}






