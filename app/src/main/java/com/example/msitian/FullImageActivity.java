package com.example.msitian;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FullImageActivity extends AppCompatActivity {

    String imageUrl;
    PhotoView imageView;
    MaterialButton btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        imageUrl = getIntent().getStringExtra("image");
        imageView = findViewById(R.id.fullImageView);
        btnDownload = findViewById(R.id.btnDownloadImage);

        Picasso.get().load(imageUrl).into(imageView);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionForDownload();
            }
        });
    }

    private void checkPermissionForDownload() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            downloadImage();
                        } else {
                            Toast.makeText(FullImageActivity.this, "Please Allow All The Permissions ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                    }
                }).check();
    }

    private void downloadImage() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Downloading ...");
        pd.setCancelable(false);
        pd.show();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault());
            String filename = sdf.format(new Date());

            DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(imageUrl);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator + filename + ".jpg");
            dm.enqueue(request);
            Toast.makeText(this, "Downloaded Successfully", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }catch (Exception e){
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
//        ProgressDialog pd = new ProgressDialog(this);
//        pd.setMessage("Downloading ...");
//        pd.setCancelable(false);
//        pd.show();
//
//        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault());
//        String filename = sdf.format(new Date());
//
//        PRDownloader.download(imageUrl, file.getPath(), filename+".jpg")
//                .build()
//                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
//                    @Override
//                    public void onStartOrResume() {
//
//                    }
//                })
//                .setOnPauseListener(new OnPauseListener() {
//                    @Override
//                    public void onPause() {
//
//                    }
//                })
//                .setOnCancelListener(new OnCancelListener() {
//                    @Override
//                    public void onCancel() {
//
//                    }
//                })
//                .setOnProgressListener(new OnProgressListener() {
//                    @Override
//                    public void onProgress(Progress progress) {
//                        long percentage = progress.currentBytes * 100 / progress.totalBytes;
//                        pd.setMessage("Downloading : " + percentage + " %");
//                    }
//                })
//                .start(new OnDownloadListener() {
//                    @Override
//                    public void onDownloadComplete() {
//                        Toast.makeText(FullImageActivity.this, "Downloaded Successfully", Toast.LENGTH_SHORT).show();
//                        pd.dismiss();
//                    }
//
//                    @Override
//                    public void onError(Error error) {
//                        pd.dismiss();
//                        Toast.makeText(FullImageActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}