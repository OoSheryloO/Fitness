package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Game implements Serializable{
    private Long gameId;

    private String gameName;

    private Date gameCreatetime;

    private Date gameModifytime;

    private Integer gamePass;

    private Integer gamePasstotel;

    private Long gameLinkid;

    private String gameIconurl;

    private String gameUrl;

    private Integer gameJoinnum;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public Date getGameCreatetime() {
        return gameCreatetime;
    }

    public void setGameCreatetime(Date gameCreatetime) {
        this.gameCreatetime = gameCreatetime;
    }

    public Date getGameModifytime() {
        return gameModifytime;
    }

    public void setGameModifytime(Date gameModifytime) {
        this.gameModifytime = gameModifytime;
    }

    public Integer getGamePass() {
        return gamePass;
    }

    public void setGamePass(Integer gamePass) {
        this.gamePass = gamePass;
    }

    public Integer getGamePasstotel() {
        return gamePasstotel;
    }

    public void setGamePasstotel(Integer gamePasstotel) {
        this.gamePasstotel = gamePasstotel;
    }

    public Long getGameLinkid() {
        return gameLinkid;
    }

    public void setGameLinkid(Long gameLinkid) {
        this.gameLinkid = gameLinkid;
    }

    public String getGameIconurl() {
        return gameIconurl;
    }

    public void setGameIconurl(String gameIconurl) {
        this.gameIconurl = gameIconurl == null ? null : gameIconurl.trim();
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl == null ? null : gameUrl.trim();
    }

    public Integer getGameJoinnum() {
        return gameJoinnum;
    }

    public void setGameJoinnum(Integer gameJoinnum) {
        this.gameJoinnum = gameJoinnum;
    }
}