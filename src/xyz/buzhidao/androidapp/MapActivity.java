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

    //Ĭ�϶�λ���꾭γ��
    double defaultLat = 31.307158;
    double defaultLng = 120.59141;
    LatLng mMapLocation = new LatLng(defaultLat, defaultLng);
    //Ĭ�����ż���
    int defaultZoom = 12;
    //��λ���ż���
    int locationZoom = 17;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        //��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
        //ע��÷���Ҫ��setContentView����֮ǰʵ��
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.map_activity);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.customtitle_activity);

        //���ñ�����
        GSHeadTitle();

        //��ʼ����ͼ����
        setmMapView();

        //��ʼ��λ
        getmMapLocation();
    }

    //���ñ�����
    public void GSHeadTitle() {
        //�����м����
        setHeadTitle(R.string.wordMapTitle);
    }

    //���õ�ͼ��ʼ������
    private void setmMapView() {
        //��ȡ��ͼ�ؼ�����
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        /**
         * ��ͼ����
         * ��ͨ��MAP_TYPE_NORMAL
         * ���ǣ�MAP_TYPE_NORMAL
         */
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //������ͨͼ
//        mBaiduMap.setTrafficEnabled(true);

        //���µ�ͼ״̬
        setmMapStatus(mMapLocation, defaultZoom);

        //�Զ��幤��
        mMapTools();
    }

    //��λ
    public void getmMapLocation() {
        MapActivity MapActivityObj = MapActivity.this;
        new MapLocationActivity(getApplicationContext(), MapActivityObj);
    }

    //��λ - �ص�
    public void setmMapLocation(LocationClient mLocationClient, BDLocation location) {
        //��λ����
        LatLng mMapLocation = new LatLng(location.getLatitude(), location.getLongitude());

        //ֹͣ��λ ��ʡ��Դ
        mLocationClient.stop();

        //���µ�ͼ״̬
        setmMapStatus(mMapLocation, locationZoom);

        //������
        mBaiduMap.clear();

        //���
        addMarker(mMapLocation);
    }

    //���µ�ͼ״̬
    public void setmMapStatus(LatLng mMapLocation, int zoom) {
        //��ͼ״̬
        MapStatus mMapStatus = new MapStatus.Builder().target(mMapLocation).zoom(zoom).build();

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //�ı��ͼ״̬
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
        mMapView.onPause();
    }

    //��ӱ�ע
    public void addMarker(LatLng mMapLocation) {
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_map_marker);
        //���帲����
        OverlayOptions overlayOptions = new MarkerOptions().position(mMapLocation)
                .icon(descriptor)
                .zIndex(0)
                .period(10)
                .animateType(MarkerOptions.MarkerAnimateType.grow);

        //����������ӵ���ͼ��
        Marker marker = (Marker) mBaiduMap.addOverlay(overlayOptions);

        Bundle bundle = new Bundle();
        bundle.putString("info", "��ǰλ��");
        marker.setExtraInfo(bundle);
    }

    //�Զ����ͼ����
    public void mMapTools() {
        //��λ��ť - �����¼�
        ImageButton btnMapLocation = (ImageButton) findViewById(R.id.btnMapLocation);
        btnMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getmMapLocation();
            }
        });
    }
}
