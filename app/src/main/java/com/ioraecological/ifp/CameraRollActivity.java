package com.ioraecological.ifp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraRollActivity extends Activity {

    AsyncTaskLoadFiles myAsyncTaskLoadFiles;
    private Uri fileUri;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "ifp";
    Bitmap nBitmap;
    String nPath;

    public class AsyncTaskLoadFiles extends AsyncTask<Void, String, Void> {

        File targetDirector;
        ImageAdapter myTaskAdapter;

        public AsyncTaskLoadFiles(ImageAdapter adapter) {
            myTaskAdapter = adapter;
        }

        @Override
        protected void onPreExecute() {
            String ExternalStorageDirectoryPath = Environment
                    .getExternalStorageDirectory().getAbsolutePath();

            String targetPath = ExternalStorageDirectoryPath + "/ifp/";
            targetDirector = new File(targetPath);
            myTaskAdapter.clear();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            File[] files = targetDirector.listFiles();
            Arrays.sort(files);
            for (File file : files) {
                publishProgress(file.getAbsolutePath());
                if (isCancelled()) break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            myTaskAdapter.add(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            myTaskAdapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }

    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;
        ArrayList<String> itemList = new ArrayList<String>();

        public ImageAdapter(Context c) {
            mContext = c;
        }

        void add(String path) {
            itemList.add(path);
        }

        void clear() {
            itemList.clear();
        }

        void remove(int index) {
            itemList.remove(index);
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }


        //getView load bitmap in AsyncTask
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            ImageView imageView;
            if (convertView == null) { // if it's not recycled, initialize some
                // attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);

                convertView = imageView;

                holder = new ViewHolder();
                holder.image = imageView;
                holder.position = position;
                convertView.setTag(holder);
            } else {
                //imageView = (ImageView) convertView;
                holder = (ViewHolder) convertView.getTag();
                ((ImageView) convertView).setImageBitmap(null);
            }

            //Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);
            // Using an AsyncTask to load the slow images in a background thread
            new AsyncTask<ViewHolder, Void, Bitmap>() {
                private ViewHolder v;

                @Override
                protected Bitmap doInBackground(ViewHolder... params) {
                    v = params[0];
                    Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220, 220);
                    return bm;
                }

                @Override
                protected void onPostExecute(Bitmap result) {
                    super.onPostExecute(result);
                    v.image.setImageBitmap(result);

                }
            }.execute(holder);

            //imageView.setImageBitmap(bm);
            //return imageView;
            return convertView;
        }

        public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
                                                 int reqHeight) {

            Bitmap bm = null;
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(path, options);

            return bm;
        }

        public int calculateInSampleSize(

                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                if (width > height) {
                    inSampleSize = Math.round((float) height
                            / (float) reqHeight);
                } else {
                    inSampleSize = Math.round((float) width / (float) reqWidth);
                }
            }

            return inSampleSize;
        }

        class ViewHolder {
            ImageView image;
            int position;
        }

    }

    ImageAdapter myImageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_roll);

        final GridView gridview = (GridView) findViewById(R.id.grid_view);
        myImageAdapter = new ImageAdapter(this);
        gridview.setAdapter(myImageAdapter);

        gridview.setOnItemClickListener(myOnItemClickListener);

        FloatingActionButton buttonReload = (FloatingActionButton) findViewById(R.id.camera_fab);
        buttonReload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(i,1);
                //Cancel the previous running task, if exist.
                myAsyncTaskLoadFiles.cancel(true);

                //new another ImageAdapter, to prevent the adapter have
                //mixed files
                myImageAdapter = new ImageAdapter(CameraRollActivity.this);
                gridview.setAdapter(myImageAdapter);
                myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
                myAsyncTaskLoadFiles.execute();
            }
        });

    }

    OnItemClickListener myOnItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            //String prompt = "remove " + (String) parent.getItemAtPosition(position);
            //Toast.makeText(getApplicationContext(), prompt, Toast.LENGTH_SHORT)
                    //.show();

            //myImageAdapter.remove(position);
            //myImageAdapter.notifyDataSetChanged();
            //Intent i = new Intent(getApplicationContext(), SingleImageActivity.class);

            // Pass image index
            //i.putExtra("id", position);
            //startActivity(i);

        }
    };

    /*
     * returning image
     */
    private File getOutputMediaFile(int type) {

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

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "mForest" + timeStamp + ".jpg");
        } else {
            return null;
        }
        Log.v("mediaFile", String.valueOf(mediaFile));
        return mediaFile;
    }

    /*
   * Creating file uri to store image
   */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            // successfully captured the image
            // display it in image view
            previewCapturedImage();
        } else if (resultCode == RESULT_CANCELED) {
            // user cancelled Image capture
            Toast.makeText(CameraRollActivity.this,
                    "User cancelled image capture", Toast.LENGTH_LONG)
                    .show();

        } else {
            // failed to capture image
            Toast.makeText(CameraRollActivity.this,
                    "Sorry! Failed to capture image", Toast.LENGTH_LONG)
                    .show();

        }
    }
    //}

    /*
    * Display image from a path to ImageView
    */
    private void previewCapturedImage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // downsizing image as it throws OutOfMemory Exception for larger
            options.inSampleSize = 8;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            nBitmap = bitmap;
            nPath = fileUri.getPath().toString();
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
        myAsyncTaskLoadFiles.execute();
    }
}