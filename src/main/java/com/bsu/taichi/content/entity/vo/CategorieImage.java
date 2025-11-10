package com.bsu.taichi.content.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Data
public class CategorieImage {
    private int id;

    private String name;

    private List<CarouselImage> images;
}
