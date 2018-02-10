package com.gzyct.m.api.busi.bean.ext;

public class Advertisement {
  private long type;
  private String title;
  private String content;
  private String pic_url;
  private long pic_url_type;
  private String web_url;
  private long place;


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPic_url() {
    return pic_url;
  }

  public void setPic_url(String pic_url) {
    this.pic_url = pic_url;
  }

  public long getPic_url_type() {
    return pic_url_type;
  }

  public void setPic_url_type(long pic_url_type) {
    this.pic_url_type = pic_url_type;
  }

  public String getWeb_url() {
    return web_url;
  }

  public void setWeb_url(String web_url) {
    this.web_url = web_url;
  }

  public long getPlace() {
    return place;
  }

  public void setPlace(long place) {
    this.place = place;
  }
}
