package com.owlalarm.addprodit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NomDeProduct extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nom_de_product);
        TextView txtV=(TextView)findViewById(R.id.textView3);


         Bundle b =getIntent().getExtras();
         String img=b.getString("img");
        String Name=b.getString("Name");
        String p_Name=b.getString("p_Name");
        String address=b.getString("address");
        String available=b.getString("available");
        String price=b.getString("price");
        txtV.setText("img:"+img+",Name:"+Name+",p_Name:"+p_Name+",address:"+address+",available:"+available+",price:"+price);





    }
}
