package com.c201901090124.nftmarket.entity;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author 小小怪
 * @title Commodity
 * @create 2022/9/11 13:54
 **/
@Data
public class Commodity {
    private int id;
    private String name;
    private String owner;
    private String sort;
    private String saletime;
    //长度为10，小数位为2
    @Column(length = 10,scale = 2)
    private BigDecimal price;
    private String introduction;
    private String information;
}
