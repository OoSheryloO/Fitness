package com.huban.pojo;

import java.util.Date;
@SuppressWarnings("serial")
public class Order implements java.io.Serializable{
  private Long orderId;

    private String orderGoodsid;

    private Long orderUserid;

    private Long orderAddressid;

    private Date orderCreatetime;

    private Date orderModifytime;

    private Integer orderStatus;

    private Integer orderDelete;
    
    private Long orderMoney;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderGoodsid() {
		return orderGoodsid;
	}

	public void setOrderGoodsid(String orderGoodsid) {
		this.orderGoodsid = orderGoodsid;
	}

    public Long getOrderUserid() {
        return orderUserid;
    }

    public void setOrderUserid(Long orderUserid) {
        this.orderUserid = orderUserid;
    }

    public Long getOrderAddressid() {
        return orderAddressid;
    }

    public void setOrderAddressid(Long orderAddressid) {
        this.orderAddressid = orderAddressid;
    }

    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }

    public Date getOrderModifytime() {
        return orderModifytime;
    }

    public void setOrderModifytime(Date orderModifytime) {
        this.orderModifytime = orderModifytime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderDelete() {
        return orderDelete;
    }

    public void setOrderDelete(Integer orderDelete) {
        this.orderDelete = orderDelete;
    }

	public Long getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}
    
    
    /*
    	private String orderId;

        private String orderGoodsid;

        private String orderUserid;

        private String orderAddressid;

        private Date orderCreatetime;

        private Date orderModifytime;

        private Integer orderStatus;

        private Integer orderDelete;

        private String orderMoney;

    	public String getOrderId() {
    		return orderId;
    	}

    	public void setOrderId(String orderId) {
    		this.orderId = orderId;
    	}

    	public String getOrderGoodsid() {
    		return orderGoodsid;
    	}

    	public void setOrderGoodsid(String orderGoodsid) {
    		this.orderGoodsid = orderGoodsid;
    	}

    	public String getOrderUserid() {
    		return orderUserid;
    	}

    	public void setOrderUserid(String orderUserid) {
    		this.orderUserid = orderUserid;
    	}

    	public String getOrderAddressid() {
    		return orderAddressid;
    	}

    	public void setOrderAddressid(String orderAddressid) {
    		this.orderAddressid = orderAddressid;
    	}

    	public Date getOrderCreatetime() {
    		return orderCreatetime;
    	}

    	public void setOrderCreatetime(Date orderCreatetime) {
    		this.orderCreatetime = orderCreatetime;
    	}

    	public Date getOrderModifytime() {
    		return orderModifytime;
    	}

    	public void setOrderModifytime(Date orderModifytime) {
    		this.orderModifytime = orderModifytime;
    	}

    	public Integer getOrderStatus() {
    		return orderStatus;
    	}

    	public void setOrderStatus(Integer orderStatus) {
    		this.orderStatus = orderStatus;
    	}

    	public Integer getOrderDelete() {
    		return orderDelete;
    	}

    	public void setOrderDelete(Integer orderDelete) {
    		this.orderDelete = orderDelete;
    	}

    	public String getOrderMoney() {
    		return orderMoney;
    	}

    	public void setOrderMoney(String orderMoney) {
    		this.orderMoney = orderMoney;
    	}*/
}