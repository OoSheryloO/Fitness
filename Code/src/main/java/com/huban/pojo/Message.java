package com.huban.pojo;

import java.util.Date;

import com.huban.util.BaseUtil;
import com.huban.util.IdWorker;
@SuppressWarnings("serial")
public class Message implements java.io.Serializable{
    private Long messageId;

    private Long messageUserid;

    private Long messageTypeid;

    private Long messagePictureid;

    private String messageTitle;

    private String messageContent;

    private String messageUrl;

    private Date messageCreatetime;

    private Date messageModifytime;

    private Integer messageVersion;

    private Byte messageStatus;

    private Integer messageHaveread;

    private Byte messageDeleted;

    
    public static Message DefaultMessages(Long userID) {
        Message messages=new Message();
        messages.setMessageUserid(userID);
        messages.setMessageId(IdWorker.CreateNewID());
        messages.setMessageTitle("欢迎您注册登录阅读银行app");
        messages.setMessageContent("您在使用中有任何问题都可以联系我们的客服电话0571-87177872");
        messages.setMessageTypeid((long) BaseUtil.MessageType.System.getCode());
        return  messages;
    }
    
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageUserid() {
        return messageUserid;
    }

    public void setMessageUserid(Long messageUserid) {
        this.messageUserid = messageUserid;
    }

    public Long getMessageTypeid() {
        return messageTypeid;
    }

    public void setMessageTypeid(Long messageTypeid) {
        this.messageTypeid = messageTypeid;
    }

    public Long getMessagePictureid() {
        return messagePictureid;
    }

    public void setMessagePictureid(Long messagePictureid) {
        this.messagePictureid = messagePictureid;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl == null ? null : messageUrl.trim();
    }

    public Date getMessageCreatetime() {
        return messageCreatetime;
    }

    public void setMessageCreatetime(Date messageCreatetime) {
        this.messageCreatetime = messageCreatetime;
    }

    public Date getMessageModifytime() {
        return messageModifytime;
    }

    public void setMessageModifytime(Date messageModifytime) {
        this.messageModifytime = messageModifytime;
    }

    public Integer getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(Integer messageVersion) {
        this.messageVersion = messageVersion;
    }

    public Byte getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Byte messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Integer getMessageHaveread() {
        return messageHaveread;
    }

    public void setMessageHaveread(Integer messageHaveread) {
        this.messageHaveread = messageHaveread;
    }

    public Byte getMessageDeleted() {
        return messageDeleted;
    }

    public void setMessageDeleted(Byte messageDeleted) {
        this.messageDeleted = messageDeleted;
    }
}