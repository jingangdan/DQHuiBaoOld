package com.dq.huibao.update;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.allenliu.versionchecklib.core.AVersionService;

/**
 * 版本更新提示Service
 */
public class UVService extends AVersionService {
    public UVService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onResponses(AVersionService service, String response) {
        Log.e("UVService", response);
        //可以在判断版本之后在设置是否强制更新或者VersionParams
        //eg
        // versionParams.isForceUpdate=true;
        showVersionDialog("http://test-1251233192.coscd.myqcloud.com/1_1.apk", "检测到新版本", "更新内容");
//        or
//        showVersionDialog("http://down1.uc.cn/down2/zxl107821.uc/miaokun1/UCBrowser_V11.5.8.945_android_pf145_bi800_(Build170627172528).apk", "检测到新版本", getString(R.string.updatecontent));

    }
}
