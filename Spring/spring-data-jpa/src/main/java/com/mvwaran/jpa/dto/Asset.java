package com.mvwaran.jpa.dto;

import com.mvwaran.jpa.constants.AssetCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Asset {
    private Integer id;
    private String name;
    private AssetCategory category;
}
