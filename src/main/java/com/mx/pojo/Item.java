package com.mx.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 *
 * 商品表实体类
 *
 */

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    //商品id
    private Integer itemId;
    //商品名称
    @NotEmpty(message = "商品名不能为空")
    @Pattern(regexp = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$", message = "只能是中文，英文字母和数字及下划线")
    @Size(min = 1, max = 20)
    private String name;

    //商品类型id
//    @NotNull(message = "商品类型id不能为空")
    private Category CateId;
    //商品价格
    @NotNull(message = "商品价格不能为空")
    private Float price;
    //库存
    @NotNull(message = "商品库存不能为空")
    private Integer count;
    //作者
    @NotEmpty(message = "作者姓名不能为空")
    private String author;
    //ISBN
    @NotEmpty(message = "ISBN不能为空")
    @Pattern(regexp = "^[0-9]{10,13}$",message = "只能输入10-13位数字")
    private String isbn;
    //新旧程度
    @NotEmpty(message = "新旧程度不能为空")
    private String oldLevel;
    //新书比价
    @NotNull(message = "新书比不能为空")
    private Float compare;
    //商品描述
    @NotEmpty(message = "商品描述不能为空")
    private String describe;
    //出版日期
    @NotEmpty(message = "商品出版日期不能为空")
    private String publishTime;
    //出版社名称
    @NotEmpty(message = "出版社不能为空")
    private String publish;
    //发货地点
    @NotEmpty(message = "发货地点不能为空")
    private String place;

    //商品的标签（0：无，1：价格低，等等，到时候再具体想想有哪些）可以为空
    private String label;

    //商品图片路径
//    @NotEmpty(message = "商品图片路径不能为空")
    private Item_Pic item_pic;


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCateId() {
        return CateId.getcId();
    }

    public void setCateId(Category cateId) {
        CateId = cateId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(String oldLevel) {
        this.oldLevel = oldLevel == null ? null : oldLevel.trim();
    }

    public Float getCompare() {
        return compare;
    }

    public void setCompare(Float compare) {
        this.compare = compare;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish == null ? null : publish.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Item_Pic getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(Item_Pic item_pic) {
        this.item_pic = item_pic;
    }



    public String toJson(){
        return
                "{"+"\""+"item_id"+"\":"+"\""+ itemId +"\","+
                        "\""+"name"+"\":"+"\""+name +"\","+
                        "\""+"cateName"+"\":"+"\""+ CateId.getCateName()+"\","+
                        "\""+"price"+"\":"+"\""+price +"\","+
                        "\""+"count"+"\":"+"\""+count +"\","+
                        "\""+"author"+"\":"+"\""+author +"\","+
                        "\""+"ISBN"+"\":"+"\""+isbn +"\","+
                        "\""+"old_level"+"\":"+"\""+ oldLevel +"\","+
                        "\""+"compare"+"\":"+"\""+compare +"\","+
                        "\""+"describe"+"\":"+"\""+describe +"\","+
                        "\""+"publish_time"+"\":"+"\""+publishTime +"\","+
                        "\""+"publish"+"\":"+"\""+publish +"\","+
                        "\""+"place"+"\":"+"\""+place +"\","+
                        "\""+"label"+"\":"+"\""+label +"\","+
                        "\""+"item_pic"+"\":"+"\""+item_pic.getPicPath()+"\""+"}";
    }
    public String UpdateToJson() {
        return
                        "\""+"name"+"\":"+"\""+name +"\","+
                        "\""+"cateName"+"\":"+"\""+ CateId.getCateName()+"\","+
                        "\""+"price"+"\":"+"\""+price +"\","+
                        "\""+"count"+"\":"+"\""+count +"\","+
                        "\""+"author"+"\":"+"\""+author +"\","+
                        "\""+"ISBN"+"\":"+"\""+isbn +"\","+
                        "\""+"old_level"+"\":"+"\""+ oldLevel +"\","+
                        "\""+"compare"+"\":"+"\""+compare +"\","+
                        "\""+"describe"+"\":"+"\""+describe +"\","+
                        "\""+"publish_time"+"\":"+"\""+publishTime +"\","+
                        "\""+"publish"+"\":"+"\""+publish +"\","+
                        "\""+"place"+"\":"+"\""+place +"\","+
                        "\""+"label"+"\":"+"\""+label +"\",";
    }
    public String FrontToJson() {
        return
                        "\""+"name"+"\":"+"\""+name +"\","+
                        "\""+"price"+"\":"+"\""+price +"\","+
                        "\""+"count"+"\":"+"\""+count +"\","+
                        "\""+"author"+"\":"+"\""+author +"\","+
                        "\""+"ISBN"+"\":"+"\""+isbn +"\","+
                        "\""+"old_level"+"\":"+"\""+ oldLevel +"\","+
                        "\""+"compare"+"\":"+"\""+compare +"\","+
                        "\""+"desc"+"\":"+"\""+describe +"\","+
                        "\""+"publish_time"+"\":"+"\""+publishTime +"\","+
                        "\""+"publish"+"\":"+"\""+publish +"\","+
                        "\""+"place"+"\":"+"\""+place +"\",";
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", CateId=" + CateId +
                ", price=" + price +
                ", count=" + count +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", oldLevel='" + oldLevel + '\'' +
                ", compare=" + compare +
                ", describe='" + describe + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", publish='" + publish + '\'' +
                ", place='" + place + '\'' +
                ", label='" + label + '\'' +
                ", item_pic=" + item_pic +
                '}';
    }
}