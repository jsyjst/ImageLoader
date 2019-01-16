package com.example.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button mMemoryLoad,mDiskLoad,mDoubleLoad;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageLoader = new ImageLoader();
        imageView = findViewById(R.id.iv_image);
        mMemoryLoad = findViewById(R.id.btn_memory_load);
        mDiskLoad = findViewById(R.id.btn_disk_load);
        mDoubleLoad = findViewById(R.id.btn_double_load);
        onClick();
    }
    private void onClick(){
        mDoubleLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageLoader.setImageCache(new DoubleCache());
                mImageLoader.displayImage("http://p2.music.126.net/ql3nSwy0XKow_HAoZzRZgw==/109951163111196186.jpg",
                        imageView);
            }
        });

        mMemoryLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageLoader.setImageCache(new MemoryCache());
                mImageLoader.displayImage("http://p1.music.126.net/o2qzE0Is5Qh1pfSKQiVGkA==/18646617697368402.jpg",
                        imageView);
            }
        });

        mDiskLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageLoader.setImageCache(new DiskCache());
                mImageLoader.displayImage("http://p1.music.126.net/W__FCWFiyq0JdPtuLJoZVQ==/109951163765026271.jpg",
                        imageView);
            }
        });
    }
}
