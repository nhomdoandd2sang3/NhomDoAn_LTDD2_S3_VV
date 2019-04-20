package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.Random;


class SnakeEngine extends SurfaceView implements Runnable {


    private Thread thread = null;


    private Context context;


    private SoundPool soundPool;
    private int eat = -1;
    private int snake_crash = -1;


    public enum Heading {UP, RIGHT, DOWN, LEFT}

    private Heading heading = Heading.RIGHT;

    private int screenX;
    private int screenY;


    private int snakeLength;

    private int bobX;
    private int bobY;

    private int blockSize;

    private final int NUM_BLOCKS_WIDE = 40;
    private int numBlocksHigh;

    private long nextFrameTime;

    private final long FPS = 8;

    private final long MILLIS_PER_SECOND = 1000;


    private int score;


    private int[] snakeXs;
    private int[] snakeYs;

    private volatile boolean isPlaying;

    private Canvas canvas;

    private SurfaceHolder surfaceHolder;

    private Paint paint;

    public SnakeEngine(Context context, Point size) {
        super(context);
        this.context = context;
        screenX = size.x;
        screenY = size.y;
        blockSize = screenX / NUM_BLOCKS_WIDE;
        numBlocksHigh = screenY / blockSize;
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("eat_bob.ogg");
            eat = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("snake_crash.ogg");
            snake_crash = soundPool.load(descriptor, 0);

        } catch (IOException e) {
            // Error
        }
        surfaceHolder = getHolder();
        paint = new Paint();

        snakeXs = new int[200];
        snakeYs = new int[200];

        newGame();
    }

    @Override
    public void run() {

        while (isPlaying) {

            if(updateRequired()) {
                update();
                draw();
            }

        }
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void newGame() {

        // Bắt đầu game với chiều dài rắn = 1
        snakeLength = 1;
        // Vị trí rắn xuất hiện giữa màn hình
        snakeXs[0] = NUM_BLOCKS_WIDE / 2;
        snakeYs[0] = numBlocksHigh / 2;

        //Tạo khối cho rắn ăn
        spawn();

        //Set điểm trở về 0
        score = 0;

        //Cài đặt thời gian cho update game
        nextFrameTime = System.currentTimeMillis();
    }

    public void spawn() {
        Random random = new Random();
        bobX = random.nextInt(NUM_BLOCKS_WIDE - 3) + 1;
        bobY = random.nextInt(numBlocksHigh - 3) + 1;
    }

    private void eat(){
        //tăng chiều dài cho rắn
        snakeLength++;
        //tạo khối khác
        spawn();
        //tăng điểm
        score = score + 1;
        //âm thanh rắn ăn
        soundPool.play(eat, 1, 1, 0, 0, 1);
    }

    private void moveSnake(){
        for (int i = snakeLength; i > 0; i--) {
            snakeXs[i] = snakeXs[i - 1];
            snakeYs[i] = snakeYs[i - 1];

        }

        switch (heading) {
            case UP:
                snakeYs[0]--;
                break;

            case RIGHT:
                snakeXs[0]++;
                break;

            case DOWN:
                snakeYs[0]++;
                break;

            case LEFT:
                snakeXs[0]--;
                break;
        }
    }

    private boolean detectDeath(){
        //tạo biến dead
        boolean dead = false;

        //trường hợp đụng vào tường 4 bên màn hình
        if (snakeXs[0] == -1) dead = true;
        if (snakeXs[0] >= NUM_BLOCKS_WIDE) dead = true;
        if (snakeYs[0] == -1) dead = true;
        if (snakeYs[0] == numBlocksHigh) dead = true;

        //trường hợp tự ăn bản thân
        for (int i = snakeLength - 1; i > 0; i--) {
            if ((i > 4) && (snakeXs[0] == snakeXs[i]) && (snakeYs[0] == snakeYs[i])) {
                dead = true;
            }
        }

        return dead;
    }

    public void update() {
        //Rắn ăn thành công
        if (snakeXs[0] == bobX && snakeYs[0] == bobY) {
            eat();
        }

        //Di chuyển liên tục
        moveSnake();

        //Phát hiện chết
        if (detectDeath()) {
            soundPool.play(snake_crash, 1, 1, 0, 0, 1);

            Intent intent  = new Intent (context, LoseActivity.class);
            intent.putExtra("score", String.valueOf(score));
            context.startActivity(intent );

        }
    }

    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            // Set màu cho màn hình chơi
            canvas.drawColor(Color.argb(255, 26, 128, 182));
            // Set màu cho rắn
            paint.setColor(Color.argb(255, 23, 255, 0));
            //Vẽ rắn
            for (int i = 0; i < snakeLength; i++) {
                canvas.drawRect(snakeXs[i] * blockSize,
                        (snakeYs[i] * blockSize),
                        (snakeXs[i] * blockSize) + blockSize,
                        (snakeYs[i] * blockSize) + blockSize,
                        paint);
            }
            //Set màu cho khối để cho rắn ăn
            paint.setColor(Color.argb(255, 255, 0, 0));
            //Vẽ khối để cho rắn ăn
            canvas.drawRect(bobX * blockSize,
                    (bobY * blockSize),
                    (bobX * blockSize) + blockSize,
                    (bobY * blockSize) + blockSize,
                    paint);
            //Vẽ điểm số
            paint.setColor(Color.argb(255, 255, 255, 255));
            paint.setTextSize(70);
            canvas.drawText("Điểm " + score, 10, 70, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean updateRequired() {

        if(nextFrameTime <= System.currentTimeMillis()){

            nextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;

            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (motionEvent.getX() >= screenX / 2) {
                    switch(heading){
                        case UP:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.UP;
                            break;
                    }
                } else {
                    switch(heading){
                        case UP:
                            heading = Heading.LEFT;
                            break;
                        case LEFT:
                            heading = Heading.DOWN;
                            break;
                        case DOWN:
                            heading = Heading.RIGHT;
                            break;
                        case RIGHT:
                            heading = Heading.UP;
                            break;
                    }
                }
        }
        return true;
    }
}
