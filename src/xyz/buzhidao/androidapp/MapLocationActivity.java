package xyz.buzhidao.androidapp;

import android.content.Context;
import android.util.Log;
import com.baidu.location.*;

import java.util.List;

public class MapLocationActivity implements BDLocationListener {
    public MapActivity MapActivityObj; // ���Ϊ�Լ�д��һ���ӿڣ������ص����ⲿ����
    public LocationClient mLocationClient = null;
    public Context context;

    public MapLocationActivity(Context context, MapActivity MapActivityObj) {
        super();
        this.MapActivityObj = MapActivityObj;
        this.context = context;

        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(MapLocationActivity.this);

        initmMapLocation();

        mLocationClient.start();  // ��ʼ��λ
//        mLocationClient.stop();   // ֹͣ��λ
    }

    private void initmMapLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//��ѡ��Ĭ�ϸ߾��ȣ����ö�λģʽ���߾��ȣ��͹��ģ����豸
        option.setCoorType("bd09ll");//��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ
        int span=1000;
        option.setScanSpan(span);//��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��
        option.setIsNeedAddress(true);//��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
        option.setOpenGps(true);//��ѡ��Ĭ��false,�����Ƿ�ʹ��gps
        option.setLocationNotify(true);//��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���
        option.setIsNeedLocationDescribe(true);//��ѡ��Ĭ��false�������Ƿ���Ҫλ�����廯�����������BDLocation.getLocationDescribe��õ�����������ڡ��ڱ����찲�Ÿ�����
        option.setIsNeedLocationPoiList(true);//��ѡ��Ĭ��false�������Ƿ���ҪPOI�����������BDLocation.getPoiList��õ�
        option.setIgnoreKillProcess(false);//��ѡ��Ĭ��true����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϲ�ɱ��
        option.SetIgnoreCacheException(false);//��ѡ��Ĭ��false�������Ƿ��ռ�CRASH��Ϣ��Ĭ���ռ�
        option.setEnableSimulateGps(false);//��ѡ��Ĭ��false�������Ƿ���Ҫ����gps��������Ĭ����Ҫ
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        //ע�����һ��Ҫ�ж�BdLocation�ķ���ֵ��ֻ����getLocType����==61����161������²ű�ʾ��λ�ɹ�
        //���巵�صĴ�����ɲο�http://lbsyun.baidu.com/index.php?title=android-locsdk/guide/ermsg
        if (location.getLocType()==61 || location.getLocType()==161 && location.getLatitude()!=0.0) {
            MapActivityObj.setmMapLocation(mLocationClient, location);
        }
    }

    private void logLocation(BDLocation location) {
        //Receive Location
        StringBuffer sb = new StringBuffer(256);
        sb.append("time : ");
        sb.append(location.getTime());
        sb.append("\nerror code : ");
        sb.append(location.getLocType());
        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());
        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());
        sb.append("\nradius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS��λ���
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());// ��λ������ÿСʱ
            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\nheight : ");
            sb.append(location.getAltitude());// ��λ����
            sb.append("\ndirection : ");
            sb.append(location.getDirection());// ��λ��
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            sb.append("\ndescribe : ");
            sb.append("gps��λ�ɹ�");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// ���綨λ���
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());
            //��Ӫ����Ϣ
            sb.append("\noperationers : ");
            sb.append(location.getOperators());
            sb.append("\ndescribe : ");
            sb.append("���綨λ�ɹ�");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// ���߶�λ���
            sb.append("\ndescribe : ");
            sb.append("���߶�λ�ɹ������߶�λ���Ҳ����Ч��");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append("\ndescribe : ");
            sb.append("��������綨λʧ�ܣ����Է���IMEI�źʹ��嶨λʱ�䵽loc-bugs@baidu.com��������׷��ԭ��");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append("\ndescribe : ");
            sb.append("���粻ͬ���¶�λʧ�ܣ����������Ƿ�ͨ��");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append("\ndescribe : ");
            sb.append("�޷���ȡ��Ч��λ���ݵ��¶�λʧ�ܣ�һ���������ֻ���ԭ�򣬴��ڷ���ģʽ��һ���������ֽ�����������������ֻ�");
        }
        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());// λ�����廯��Ϣ
        List<Poi> list = location.getPoiList();// POI����
        if (list != null) {
            sb.append("\npoilist size = : ");
            sb.append(list.size());
            for (Poi p : list) {
                sb.append("\npoi= : ");
                sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
            }
        }
        Log.i("BaiduLocationApiDem", sb.toString());
    }
}
