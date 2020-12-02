package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView imageview;
    public void downloadImage(View view){
        ImageDownloader imagDownloader = new ImageDownloader();
        Bitmap bitmapImage;

        try {
            bitmapImage = imagDownloader.execute("https://img.pngio.com/aaron-mcgruder-huey-freeman-the-boondock-518086-png-images-pngio-boondocks-png-huey-and-riley-900_500.png").get();

            imageview.setImageBitmap(bitmapImage);

            Log.i("Task", "......clicked......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = findViewById(R.id.imageView);
        System.out.println("Is it working!");
        Log.i("Info: ", "LOGGING TEST");
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
