package com.ckq.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ItemDTO {
	/**
     * @ExcelProperty(String)是 EasyExcel 库中的注解，
     * 用于指定 Java 类字段与 Excel 表格中的列之间的映射关系。
     */
	@ExcelProperty("物料编码")
    private String code;
    @ExcelProperty("物料名称")
    private String name;
    // ... 其他属性
}
