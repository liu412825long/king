package cn.bj.king.util;

import java.io.File;

/**
 * Create by 刘海龙 2019年12月26日
 * Copyright (c) 2019 神工众志科技有限公司
 * {在这里补充类的功能说明}
 *
 * @author 刘海龙 hailong.liu@0071515.com
 */
public class MailAttachment {
    private File attachment;
    private String fileName;

    public MailAttachment() {
    }

    public MailAttachment(File attachment) {
        this.attachment = attachment;
    }

    public MailAttachment(File attachment, String fileName) {
        this.attachment = attachment;
        this.fileName = fileName;
    }

    public File getAttachment() {
        return this.attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
