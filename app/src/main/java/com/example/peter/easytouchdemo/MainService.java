package com.example.peter.easytouchdemo;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainService extends Service implements View.OnClickListener, View.OnTouchListener, View.OnFocusChangeListener {
    private View touchBallView;
    private View menuBall;
    private Button touchBall;
    WindowManager windowManager;
    WindowManager.LayoutParams params;
    private PopupWindow popup;
    private float x,y;
    private boolean isMoving;
    private TextView tvApps,tvHomeScreen,tvSetting,tvLockScreen,tvFavor;
    private RelativeLayout menu_layout;


    public MainService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
        createTouchBallView();
        regListener();
    }

    private void regListener(){
        touchBall.setOnTouchListener(this);
        touchBall.setOnClickListener(this);
        tvApps.setOnClickListener(this);
        tvHomeScreen.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvLockScreen.setOnClickListener(this);
        tvFavor.setOnClickListener(this);
    }

    private void initView(){
        touchBallView = LayoutInflater.from(this).inflate(R.layout.touch_ball,null);
        touchBall = (Button) touchBallView.findViewById(R.id.touchBall);
        menuBall = LayoutInflater.from(this).inflate(R.layout.shown_menu,null);
        tvApps = (TextView) menuBall.findViewById(R.id.tv_apps);
        tvHomeScreen = (TextView) menuBall.findViewById(R.id.tv_home_screen);
        tvSetting = (TextView) menuBall.findViewById(R.id.tv_setting);
        tvLockScreen = (TextView) menuBall.findViewById(R.id.tv_lock_screen);
        tvFavor = (TextView) menuBall.findViewById(R.id.tv_favor);
        menu_layout = (RelativeLayout) touchBallView.findViewById(R.id.lay_main);
    }

    private void createTouchBallView(){
        //windowManager.removeView(touchBallView);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        //params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.x = 0;
        params.y = 0;
        //params.width = 500;
        //params.height = 500;
        Log.v("TAG", String.valueOf(WindowManager.LayoutParams.WRAP_CONTENT));
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.RGBA_8888;
        windowManager.addView(touchBallView,params);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_apps:
                Toast.makeText(this, "APPS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                //popup.dismiss();
                windowManager.removeView(menuBall);
                touchBallView.setVisibility(View.VISIBLE);
                menuBall.setVisibility(View.GONE);
                break;
            case R.id.tv_favor:
                Toast.makeText(this, "FAVOR", Toast.LENGTH_SHORT).show();
                //popup.dismiss();
                windowManager.removeView(menuBall);
                touchBallView.setVisibility(View.VISIBLE);
                menuBall.setVisibility(View.GONE);
                break;
            case R.id.tv_home_screen:
                Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
                //popup.dismiss();
                windowManager.removeView(menuBall);
                touchBallView.setVisibility(View.VISIBLE);
                menuBall.setVisibility(View.GONE);
                break;
            case R.id.tv_lock_screen:
                Toast.makeText(this, "LOCK", Toast.LENGTH_SHORT).show();
                //popup.dismiss();
                windowManager.removeView(menuBall);
                touchBallView.setVisibility(View.VISIBLE);
                menuBall.setVisibility(View.GONE);
                break;
            case R.id.tv_setting:
                Toast.makeText(this, Build.FINGERPRINT, Toast.LENGTH_LONG).show();
                //popup.dismiss();
                windowManager.removeView(menuBall);
                touchBallView.setVisibility(View.VISIBLE);
                menuBall.setVisibility(View.GONE);
                break;
            default:
                //popup.dismiss();
                windowManager.removeView(menuBall);
                touchBallView.setVisibility(View.VISIBLE);
                menuBall.setVisibility(View.GONE);
                break;
        }


    }

    private void showMenuWindows(){
        touchBallView.setVisibility(View.GONE);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        //params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.CENTER;
        //params.x = 0;
        //params.y = 0;
        //params.width = 500;
        //params.height = 500;
        Log.v("TAG", String.valueOf(WindowManager.LayoutParams.WRAP_CONTENT));
        params.width = 500;
        params.height = 500;
        params.format = PixelFormat.RGBA_8888;
        windowManager.addView(menuBall,params);
        /*
        DisplayMetrics dm  = getResources().getDisplayMetrics();
        //popup = new PopupWindow(menuBall, (int) (dm.widthPixels*0.5), (int) (dm.heightPixels*0.3));
        popup = new PopupWindow(menuBall, (int) (dm.widthPixels*0.5),(int) (dm.heightPixels*0.25));
        Drawable transpent = new ColorDrawable(Color.TRANSPARENT);
        popup.setBackgroundDrawable(transpent);
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.showAtLocation(touchBallView, Gravity.CENTER, -10, -10);
        popup.update();
        params.width = 500;
        params.height = 500;
        */
    }
    int paramX, paramY;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                isMoving = false;
                x = motionEvent.getRawX();
                y = motionEvent.getRawY();
                paramX = params.x;
                paramY = params.y;
                break;
            case MotionEvent.ACTION_MOVE:
                isMoving = true;
                int dx = (int) (motionEvent.getRawX() - x);
                int dy = (int) (motionEvent.getRawY() - y);
                params.x = paramX + dx;
                params.y = paramY + dy;
                windowManager.updateViewLayout(touchBallView,params);
                Log.v("TSE", String.valueOf(x));
                Log.v("TSE", String.valueOf(y));
                Log.v("TSE", String.valueOf(dx));
                Log.v("TSE", String.valueOf(dy));
                Log.v("TSE", String.valueOf(paramX));
                Log.v("TSE", String.valueOf(paramY));
                Log.v("TSE", String.valueOf(params.x));
                Log.v("TSE", String.valueOf(params.y));

                break;
            case MotionEvent.ACTION_UP:
                if (motionEvent.getRawX()-x==0||motionEvent.getRawY()==0){
                    showMenuWindows();
                    menuBall.setVisibility(View.VISIBLE);
                }
                break;
            default:

                break;
        }


        if (isMoving){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }
}
