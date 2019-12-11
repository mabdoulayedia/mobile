package com.example.asyntaskremote2019;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyntaskImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    private Activity myActivity;
    public AsyntaskImageDownloader(Activity activity){
        myActivity=activity;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Log.i("hello1", "hello1");
        publishProgress(1);
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception("Failed to connect");
            }
            InputStream is = con.getInputStream();
            publishProgress(0);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Image", "Failed to load image", e);
            Log.e("error", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {


        Log.i("ASYNTASKIMAGEDOWNLOADER", "In onPostExecute...");
        ImageView iv = (ImageView) myActivity.findViewById(R.id.remoteImage);
        Log.i("ASYNTASKIMAGEDOWNLOADER", "After findViewById...");
        if (iv != null && bitmap != null) {
            Log.i("ASYNTASKIMAGEDOWNLOADER", "Bitmap Found");
            iv.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        TextView tv = (TextView) myActivity.findViewById(R.id.);
        if (values[0] == 1) {
            tv.setText("Loading...");
        } else {
            tv.setText("");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
