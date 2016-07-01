package xyz.buzhidao.androidapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;

public class MapActivity extends BaseActivity {
    MapView mMapView = null;
    BaiduMap mBaiduMap = null;

    //默认定位坐标经纬度
    double defaultLat = 31.307158;
    double defaultLng = 120.59141;
    LatLng mMapLocation = new LatLng(defaultLat, defaultLng);
    //默认缩放级别
    int defaultZoom = 12;
    //定位缩放级别
    int locationZoom = 17;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.map_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.customtitle_activity);

        //设置标题栏
        GSHeadTitle();

        //初始化地图参数
        setmMapView();

        //初始定位
        getmMapLocation();
    }

    //设置标题栏
    public void GSHeadTitle() {
        //设置中间标题
        setHeadTitle(R.string.wordMapTitle);
    }

    //设置地图初始化参数
    private void setmMapView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        /**
         * 地图类型
         * 普通：MAP_TYPE_NORMAL
         * 卫星：MAP_TYPE_NORMAL
         */
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //开启交通图
//        mBaiduMap.setTrafficEnabled(true);

        //更新地图状态
        setmMapStatus(mMapLocation, defaultZoom);

        //自定义工具
        mMapTools();
    }

    //定位
    public void getmMapLocation() {
        MapActivity MapActivityObj = MapActivity.this;
        new MapLocationActivity(getApplicationContext(), MapActivityObj);
    }

    //定位 - 回调
    public void setmMapLocation(LocationClient mLocationClient, BDLocation location) {
        //定位坐标
        LatLng mMapLocation = new LatLng(location.getLatitude(), location.getLongitude());

        //停止定位 节省资源
        mLocationClient.stop();

        //更新地图状态
        setmMapStatus(mMapLocation, locationZoom);

        //清除标点
        mBaiduMap.clear();

        //标点
        addMarker(mMapLocation);
    }

    //更新地图状态
    public void setmMapStatus(LatLng mMapLocation, int zoom) {
        //地图状态
        MapStatus mMapStatus = new MapStatus.Builder().target(mMapLocation).zoom(zoom).build();

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    //添加标注
    public void addMarker(LatLng mMapLocation) {
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_marker);
        //定义覆盖物
        OverlayOptions overlayOptions = new MarkerOptions().position(mMapLocation)
                .icon(descriptor)
                .zIndex(0)
                .period(10)
                .animateType(MarkerOptions.MarkerAnimateType.grow);

        //将覆盖物添加到地图上
        Marker marker = (Marker) mBaiduMap.addOverlay(overlayOptions);

        Bundle bundle = new Bundle();
        bundle.putString("info", "当前位置");
        marker.setExtraInfo(bundle);
    }

    //自定义地图工具
    public void mMapTools() {
        //定位按钮 - 监听事件
        ImageButton btnMapLocation = (ImageButton) findViewById(R.id.btnMapLocation);
        btnMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getmMapLocation();
            }
        });
    }
}
