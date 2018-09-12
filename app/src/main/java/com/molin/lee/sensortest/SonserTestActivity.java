package com.molin.lee.sensortest;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.molin.lee.R;

import java.net.URL;
import java.util.List;



/**
 * Created by LEEP_COMPUTER on 2018/9/11.
 * 传感器测试
 */

public class SonserTestActivity extends Activity implements SensorEventListener {

    private static final String TAG=SonserTestActivity.class.getSimpleName();

    private TextView tvSensor;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonser);
        initView();
    }

    private void initView() {
        tvSensor = (TextView) findViewById(R.id.sonser_type_item);
    }

    public void getSorsens(View view) {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取手机里所有传感器
//        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
//        StringBuffer sb = new StringBuffer();
//        for (Sensor sensor :
//                sensorList) {
//            sb.append(sensor.toString() + "\n");
//        }
//        tvSensor.setText(sb.toString());
        //获取光线传感器
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        tvSensor.setText(lightSensor.toString());

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        StringBuilder sb = null;
        int type = event.sensor.getType();
        switch (type) {
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("\n光传感器返回数据：");
                sb.append("\n当前光的强度为：");
                sb.append(values[0]);
                Log.e(TAG,sb.toString());
                tvSensor.setText(sb.toString());
                if(values[0]<20){
//                    mIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:13714780207"));
                    //动态的请求权限
                    ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA
                    }, 0x12);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG,"请求码=="+requestCode+"--权限数组的长度"+grantResults.length);
        if(requestCode==0x12){
            if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Log.e(TAG,"拨打电话权限被允许");
                if(grantResults.length>1&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    Log.e(TAG,"camera权限被允许");
                }
//                startActivity(mIntent);
            }else{
                Log.e(TAG,"权限被拒绝"+grantResults[0]+"--"+grantResults[1]);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
