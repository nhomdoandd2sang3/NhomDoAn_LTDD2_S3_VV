package com.example.baitapcaithien.drawingfun;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivityExe4 extends AppCompatActivity {
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;
    Button btnChon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exe4_activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        btnChon = (Button)findViewById(R.id.btnChon);
        // Tạo bộ điều khiển
        if (mediaController == null) {
            mediaController = new MediaController(MainActivityExe4.this);

            // Neo vị trí của MediaController với VideoView.
            mediaController.setAnchorView(videoView);


            // Sét đặt bộ điều khiển cho VideoView.
            videoView.setMediaController(mediaController);
        }
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

//        try {
//            // ID của file video.
//            int id = this.getRawResIdByName("videoplayback");
//            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
//
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }
//
//        videoView.requestFocus();

        // Sự kiện khi file video sẵn sàng để chơi.
//        videoView.setOnPreparedListener(new OnPreparedListener() {
//
//            public void onPrepared(MediaPlayer mediaPlayer) {
//
//
//                videoView.seekTo(position);
//                if (position == 0) {
//                    videoView.start();
//                }
//
//                // Khi màn hình Video thay đổi kích thước
//                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//                    @Override
//                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//
//                        // Neo lại vị trí của bộ điều khiển của nó vào VideoView.
//                        mediaController.setAnchorView(videoView);
//                    }
//                });
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            switch (resultCode)
            {
                case RESULT_OK:
                    Toast.makeText(MainActivityExe4.this, "Khong chon" , Toast.LENGTH_SHORT).show();
                    Uri uri = data.getData();
                    videoView.setVideoURI(uri);
                    videoView.start();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(MainActivityExe4.this, "Khong chon" , Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    // Tìm ID ứng với tên của file nguồn (Trong thư mục raw).
//    public int getRawResIdByName(String resName) {
//        String pkgName = this.getPackageName();
//
//        // Trả về 0 nếu không tìm thấy.
//        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
//        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
//        return resID;
//    }


    // Khi bạn xoay điện thoại, phương thức này sẽ được gọi
    // nó lưu trữ lại ví trí file video đang chơi.
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//
//        // Lưu lại vị trí file video đang chơi.
//        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
//        videoView.pause();
//
//    }


    // Sau khi điện thoại xoay chiều xong. Phương thức này được gọi,
    // bạn cần tái tạo lại ví trí file nhạc đang chơi.
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // Lấy lại ví trí video đã chơi.
//        position = savedInstanceState.getInt("CurrentPosition");
//        videoView.seekTo(position);
//    }
}
