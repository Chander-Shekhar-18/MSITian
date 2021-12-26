package com.example.msitian.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.msitian.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;


public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;
    String url;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdfViewer);
        progressBar = findViewById(R.id.pdfViewerProgressBar);
        url = getIntent().getStringExtra("pdfUrl");

        loadFile(url);

    }

    private void loadFile(String url) {
        FileLoader.with(this)
                .load(url)
                .fromDirectory("test4",FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        File loadedFile = response.getBody();

                        progressBar.setVisibility(View.GONE);

                        pdfView.fromFile(loadedFile)
                                .pages(0, 2, 1, 3, 3, 3)
                                .password(null)
                                .defaultPage(0)
                                .enableAntialiasing(true)
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(false)
                                .spacing(5)
                                .nightMode(false)
                                .load();
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Toast.makeText(PdfViewerActivity.this, "Some Error Occurred !", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}