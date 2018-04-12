package com.zxg.myokhttp.backbean;


import com.zxg.myokhttp.base.BackBaseBean;

/**
 * Author ：zxg on 2017/3/2 17:11
 * email : remotecountry@163.com
 */

public class UpLoadPicBackBean extends BackBaseBean {


    /**
     * msHeadUrl  : http://192.168.1.118:8888/file-provider/upload/pos/D0B0588B44A14AAA852A6D71FA0E619B.png
     */

    private DataBean data;

    @Override
    public String toString() {
        return "UpLoadPicBackBean{" +
                "data=" + data +
                "} " + super.toString();
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String msHeadUrl;

        @Override
        public String toString() {
            return "DataBean{" +
                    "msHeadUrl='" + msHeadUrl + '\'' +
                    '}';
        }

        public String getMsHeadUrl() {
            return msHeadUrl;
        }

        public void setMsHeadUrl(String msHeadUrl) {
            this.msHeadUrl = msHeadUrl;
        }
    }
}
