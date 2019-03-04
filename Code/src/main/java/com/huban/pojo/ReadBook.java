package com.huban.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class ReadBook {
	public static final String sReadBookClass = "ReadBook";
	
    private Long bookId;
    @JSONField(serialize=false)
    private Long doubanId;

    private String publisher;

    private Float ratingAverage;

    private Long ratingNumraters;

    private String pubdate;

    private String imagesS;

    private String imagesL;

    private String imagesM;

    private String alt;

    private String price;

    private String isbn10;

    private String isbn13;

    private String tags;

    private String binding;

    private Integer type;
    @JSONField(serialize=false)
    private Integer isDelete;

    private String title;

    private String subtitle;

    private String author;

    private String translator;

    private String authorIntro;

    private String summary;

    private String catalog;
    
    private boolean paybill;

	/**
	 * @return bookId
	 */
	
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return doubanId
	 */
	
	public Long getDoubanId() {
		return doubanId;
	}

	/**
	 * @param doubanId the doubanId to set
	 */
	
	public void setDoubanId(Long doubanId) {
		this.doubanId = doubanId;
	}

	/**
	 * @return publisher
	 */
	
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return ratingAverage
	 */
	
	public Float getRatingAverage() {
		return ratingAverage;
	}

	/**
	 * @param ratingAverage the ratingAverage to set
	 */
	
	public void setRatingAverage(Float ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	/**
	 * @return ratingNumraters
	 */
	
	public Long getRatingNumraters() {
		return ratingNumraters;
	}

	/**
	 * @param ratingNumraters the ratingNumraters to set
	 */
	
	public void setRatingNumraters(Long ratingNumraters) {
		this.ratingNumraters = ratingNumraters;
	}

	/**
	 * @return pubdate
	 */
	
	public String getPubdate() {
		return pubdate;
	}

	/**
	 * @param pubdate the pubdate to set
	 */
	
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	/**
	 * @return imagesS
	 */
	
	public String getImagesS() {
		return imagesS;
	}

	/**
	 * @param imagesS the imagesS to set
	 */
	
	public void setImagesS(String imagesS) {
		this.imagesS = imagesS;
	}

	/**
	 * @return imagesL
	 */
	
	public String getImagesL() {
		return imagesL;
	}

	/**
	 * @param imagesL the imagesL to set
	 */
	
	public void setImagesL(String imagesL) {
		this.imagesL = imagesL;
	}

	/**
	 * @return imagesM
	 */
	
	public String getImagesM() {
		return imagesM;
	}

	/**
	 * @param imagesM the imagesM to set
	 */
	
	public void setImagesM(String imagesM) {
		this.imagesM = imagesM;
	}

	/**
	 * @return alt
	 */
	
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt the alt to set
	 */
	
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return price
	 */
	
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return isbn10
	 */
	
	public String getIsbn10() {
		return isbn10;
	}

	/**
	 * @param isbn10 the isbn10 to set
	 */
	
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	/**
	 * @return isbn13
	 */
	
	public String getIsbn13() {
		return isbn13;
	}

	/**
	 * @param isbn13 the isbn13 to set
	 */
	
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	/**
	 * @return tags
	 */
	
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return binding
	 */
	
	public String getBinding() {
		return binding;
	}

	/**
	 * @param binding the binding to set
	 */
	
	public void setBinding(String binding) {
		this.binding = binding;
	}

	/**
	 * @return type
	 */
	
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return isDelete
	 */
	
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return title
	 */
	
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return subtitle
	 */
	
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle the subtitle to set
	 */
	
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return author
	 */
	
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return translator
	 */
	
	public String getTranslator() {
		return translator;
	}

	/**
	 * @param translator the translator to set
	 */
	
	public void setTranslator(String translator) {
		this.translator = translator;
	}

	/**
	 * @return authorIntro
	 */
	
	public String getAuthorIntro() {
		return authorIntro;
	}

	/**
	 * @param authorIntro the authorIntro to set
	 */
	
	public void setAuthorIntro(String authorIntro) {
		this.authorIntro = authorIntro;
	}

	/**
	 * @return summary
	 */
	
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return catalog
	 */
	
	public String getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return paybill
	 */
	
	public boolean isPaybill() {
		return paybill;
	}

	/**
	 * @param paybill the paybill to set
	 */
	
	public void setPaybill(Integer paybill) {
		boolean flag = false;
		if (paybill != null && paybill > 0) {
			flag = true;
		}
		this.paybill = flag;
	}
	
	
	
}