package com.mvwaran.sample.dto;

import com.mvwaran.sample.constants.AssetCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Asset {
    private Integer id;
    private String name;
    private AssetCategory category;
}
