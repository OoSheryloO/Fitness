package com.huban.pojo;

import java.util.Date;

public class Book {
	
	public static final String sBookClass = "Book";
    private Long bookId;

    private String bookHeadicon;

    private String bookNum;

    private String bookName;

    private String bookAuthor;

    private String bookPress;

    private Float bookCount;

    private Long bookCommentcount;

    private Integer bookReadnumber;

    private Date bookCreatetime;

    private Date bookModifytime;

    private Integer bookStatus;

    private Integer bookDelete;

    private String bookComment;

    private String bookUrl;

    private Integer bookReadnum;

    private Integer bookHot;

    private Integer bookWeight;

    private String bookMoney;

    private Integer bookFamous;

    private Integer bookChild;
    
    private long bookPublisher;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookHeadicon() {
        return bookHeadicon;
    }

    public void setBookHeadicon(String bookHeadicon) {
        this.bookHeadicon = bookHeadicon == null ? null : bookHeadicon.trim();
    }

    public String getBookNum() {
        return bookNum;
    }

    public void setBookNum(String bookNum) {
        this.bookNum = bookNum == null ? null : bookNum.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress == null ? null : bookPress.trim();
    }

    public Float getBookCount() {
        return bookCount;
    }

    public void setBookCount(Float bookCount) {
        this.bookCount = bookCount;
    }

    public Long getBookCommentcount() {
        return bookCommentcount;
    }

    public void setBookCommentcount(Long bookCommentcount) {
        this.bookCommentcount = bookCommentcount;
    }

    public Integer getBookReadnumber() {
		return bookReadnumber;
	}

	public void setBookReadnumber(Integer bookReadnumber) {
		this.bookReadnumber = bookReadnumber;
	}

	public Date getBookCreatetime() {
        return bookCreatetime;
    }

    public void setBookCreatetime(Date bookCreatetime) {
        this.bookCreatetime = bookCreatetime;
    }

    public Date getBookModifytime() {
        return bookModifytime;
    }

    public void setBookModifytime(Date bookModifytime) {
        this.bookModifytime = bookModifytime;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getBookDelete() {
        return bookDelete;
    }

    public void setBookDelete(Integer bookDelete) {
        this.bookDelete = bookDelete;
    }

    public String getBookComment() {
        return bookComment;
    }

    public void setBookComment(String bookComment) {
        this.bookComment = bookComment == null ? null : bookComment.trim();
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl == null ? null : bookUrl.trim();
    }

    public Integer getBookReadnum() {
        return bookReadnum;
    }

    public void setBookReadnum(Integer bookReadnum) {
        this.bookReadnum = bookReadnum;
    }

    public Integer getBookHot() {
        return bookHot;
    }

    public void setBookHot(Integer bookHot) {
        this.bookHot = bookHot;
    }

    public Integer getBookWeight() {
        return bookWeight;
    }

    public void setBookWeight(Integer bookWeight) {
        this.bookWeight = bookWeight;
    }

    public String getBookMoney() {
        return bookMoney;
    }

    public void setBookMoney(String bookMoney) {
        this.bookMoney = bookMoney == null ? null : bookMoney.trim();
    }

    public Integer getBookFamous() {
        return bookFamous;
    }

    public void setBookFamous(Integer bookFamous) {
        this.bookFamous = bookFamous;
    }

    public Integer getBookChild() {
        return bookChild;
    }

    public void setBookChild(Integer bookChild) {
        this.bookChild = bookChild;
    }

	public long getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(long bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
    
}