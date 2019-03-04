package com.huban.pojo;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Gamepart implements Serializable{
	
	private String gameIconurl;
	
	private Long gameLinkid;

    private Long gameId;

    private Long gameUserid;

    private String gameName;

    private Date gameCreatetime;

    private Date gameModifytime;

    private Integer gamePass;

    private Integer gamePasstotel;
    
    /**
   	 * @return the gameIconurl
   	 */
   	public String getGameIconurl() {
   		return gameIconurl;
   	}

   	/**
   	 * @param gameIconurl the gameIconurl to set
   	 */
   	public void setGameIconurl(String gameIconurl) {
   		this.gameIconurl = gameIconurl;
   	}

    public Long getGameLinkid() {
        return gameLinkid;
    }

    public void setGameLinkid(Long gameLinkid) {
        this.gameLinkid = gameLinkid;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameUserid() {
        return gameUserid;
    }

    public void setGameUserid(Long gameUserid) {
        this.gameUserid = gameUserid;
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
}