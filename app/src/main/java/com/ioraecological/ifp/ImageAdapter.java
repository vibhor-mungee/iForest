package com.ioraecological.ifp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by akanksha on 3/16/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<String> f = new  ArrayList<String>();// list of file paths
    private File[] listFile;
    ImageView imageView;

    private static final String IMAGE_DIRECTORY_NAME = "ifp";

    // Keep all Images in array
    Integer[] mThumbIds = {
            R.drawable.placeholder_camera
    };


    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder {
        ImageView imageView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        imageView = new ImageView(mContext);
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

        if(f.isEmpty()){

            imageView.setImageResource(R.drawable.placeholder_camera);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(190, 190));
        }else{
            Iterator<String> i = f.iterator();
            while(i.hasNext()){
                Bitmap bitmap = BitmapFactory.decodeFile(i.next());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50 , baos);
                imageView.setImageURI(Uri.parse(i.next()));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new GridView.LayoutParams(190, 190));

            }
        }




        return imageView;
    }


    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            getFromSdcard();
            //updateImages();
            return null;
        }


        protected void onPostExecute(String s) {
            updateImages();
        }
    }

    private void updateImages() {

    }

    public void getFromSdcard()
    {
        File file= new File(Environment.getExternalStorageDirectory(), IMAGE_DIRECTORY_NAME);

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {
                f.add(listFile[i].getAbsolutePath());
            }
        } else {
            File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/ifp");
            boolean success = true;
            if (!mediaStorageDir.exists()) {
                success = mediaStorageDir.mkdir();
                Log.v("mkdir", String.valueOf(success));
            }
            if (!success) {
                // Do something on success
                //Toast.makeText(CameraRollActivity.this, "abx",Toast.LENGTH_SHORT).show();
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
            }
        }
        Log.v("file", String.valueOf(file));
    }


}
