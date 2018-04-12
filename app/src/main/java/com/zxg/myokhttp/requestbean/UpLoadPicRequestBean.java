package com.zxg.myokhttp.requestbean;

import java.io.File;

/**
 * Author ：zxg on 2017/3/2 17:10
 * email : remotecountry@163.com
 */

public class UpLoadPicRequestBean {
    /**
     * 上传文件
     */
    private File pictureFile;

    public UpLoadPicRequestBean() {
    }

    public UpLoadPicRequestBean(File pictureFile) {
        this.pictureFile = pictureFile;
    }

    public File getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(File pictureFile) {
        this.pictureFile = pictureFile;
    }

    @Override
    public String toString() {
        return "UpLoadPicRequestBean{" +
                "pictureFile=" + pictureFile +
                '}';
    }
}
