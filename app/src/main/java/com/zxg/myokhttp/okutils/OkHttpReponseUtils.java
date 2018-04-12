package com.zxg.myokhttp.okutils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zxg.myokhttp.base.BackBaseBean;


/**
 * Author ：zxg on 2017/4/11 11:52
 * email : remotecountry@163.com
 * 返回值filter
 */

public class OkHttpReponseUtils {


    /**
     * 检查返回值的成功与失败
     *
     * @param baseBean
     * @param mActivity
     * @return
     */
    public static boolean chkResponse(BackBaseBean baseBean, final Context mActivity) {
        if (null != baseBean) {
            Log.d("返回bean类的值=", baseBean.toString());
        }

//        if(baseBean.getStatusCode().equals(successCode)){
//            return true;
//        }else if(baseBean.getStatusCode().equals(failType)){
//            SkipActivityUtils.skipActivity(mActivity, WelcomeActivity.class);
//            Toast.makeText(mActivity, mActivity.getResources().getString(R.string.http_sessionOverTime), Toast.LENGTH_LONG).show();
//            return false;
//        }else {
//            Toast.makeText(mActivity, baseBean.getMessage(), Toast.LENGTH_LONG).show();
//            return false;
//        }
        switch (baseBean.getStatusCode()) {
            case CodeString.successCode:
                return true;
            case CodeString.successCode1:
                return true;
//            case CodeString.T01:
//                SkipActivityUtils.skipActivity(mActivity, LoginActivity.class);
//                ActivityManager.getInstance().exitAllPageButLoginActivity();
//                Toast.makeText(mActivity, CodeString.T01String, Toast.LENGTH_LONG).show();
//                return false;
            case CodeString.C01:
                Toast.makeText(mActivity, CodeString.C01String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.HBTS_U01:
                Toast.makeText(mActivity, CodeString.HBTS_U01String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.HBTS_U2:
                Toast.makeText(mActivity, CodeString.HBTS_U2String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.P01:
                Toast.makeText(mActivity, CodeString.P01String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.P02:
                Toast.makeText(mActivity, CodeString.P02String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.P03:
                Toast.makeText(mActivity, CodeString.P03String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.P04:
                Toast.makeText(mActivity, CodeString.P04String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.S01:
                Toast.makeText(mActivity, CodeString.S01String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.T03:
                Toast.makeText(mActivity, CodeString.T03String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U01:
                Toast.makeText(mActivity, CodeString.U01String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U04:
                Toast.makeText(mActivity, CodeString.U04String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U05:
                Toast.makeText(mActivity, CodeString.U05String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U08:
                Toast.makeText(mActivity, CodeString.U08String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U02:
                Toast.makeText(mActivity, CodeString.U02String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.CREDIT_002:
                Toast.makeText(mActivity, CodeString.CREDIT_002String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.CREDIT_001:
                Toast.makeText(mActivity, CodeString.CREDIT_001String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.B410:
                Toast.makeText(mActivity, CodeString.B410String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.CREDIT_005:
                Toast.makeText(mActivity, CodeString.CREDIT_005String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.CREDIT_004:
                Toast.makeText(mActivity, CodeString.CREDIT_004String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.CREDIT_006:
                Toast.makeText(mActivity, CodeString.CREDIT_006String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.CREDIT_003:
                Toast.makeText(mActivity, CodeString.CREDIT_003String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.HBTS_U3:
                Toast.makeText(mActivity, CodeString.HBTS_U3String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U03:
                Toast.makeText(mActivity, CodeString.U03String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U06:
                Toast.makeText(mActivity, CodeString.U06String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U07:
                Toast.makeText(mActivity, CodeString.U07String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U09:
                Toast.makeText(mActivity, CodeString.U09String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U10:
                Toast.makeText(mActivity, CodeString.U10String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.U11:
                Toast.makeText(mActivity, CodeString.U11String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.PAYPASS_005:
                Toast.makeText(mActivity, CodeString.PAYPASS_005String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.PAYPASS_002:
                Toast.makeText(mActivity, CodeString.PAYPASS_002String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.PAYPASS_003:
                //有特殊显示，不toast
                return false;
            case CodeString.INFO_001:
                Toast.makeText(mActivity, CodeString.INFO_001String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.INFO_002:
                Toast.makeText(mActivity, CodeString.INFO_002String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.SG01:
                Toast.makeText(mActivity, CodeString.SG01String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.SG02:
                Toast.makeText(mActivity, CodeString.SG02String, Toast.LENGTH_LONG).show();
                return false;
            case CodeString.SG03:
                Toast.makeText(mActivity, CodeString.SG03String, Toast.LENGTH_LONG).show();
                return false;
            default:
                Log.d("状态码=", baseBean.getStatusCode());
                Toast.makeText(mActivity, baseBean.getMessage(), Toast.LENGTH_LONG).show();
                return false;
        }
    }

//    /**
//     * 今日收单和历史收单统计特写
//     *
//     * @param baseBean
//     * @param mActivity
//     * @return
//     */
//    public static boolean chkResponseForBussinessType(BackBaseBean baseBean, final Context mActivity) {
//        switch (baseBean.getStatusCode()) {
//            case CodeString.successCode:
//                return true;
//            case CodeString.successCode1:
//                return true;
//            case CodeString.T01:
//                SkipActivityUtils.skipActivity(mActivity, LoginActivity.class);
//                ActivityManager.getInstance().exitAllPageButLoginActivity();
//                return false;
//            case CodeString.T02:
//                SkipActivityUtils.skipActivity(mActivity, LoginActivity.class);
//                ActivityManager.getInstance().exitAllPageButLoginActivity();
//                return false;
//            default:
//                return false;
//        }
//    }
}
