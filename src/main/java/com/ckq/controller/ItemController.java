package com.ckq.controller;

import com.ckq.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/item")
@Api("表格导入")
public class ItemController {
    @Resource
    private ItemService itemService;

	@PostMapping("/import")
    @ApiOperation(value = "导入物料信息接口")
    public String importExcel(@RequestParam("file") MultipartFile file){
        try {
            itemService.importExcel(file.getInputStream());
            return "文件导入成功";
        } catch (Exception e) {
            return "文件导入失败: " + e.getMessage();
        }
    }

	// ... 其他方法

}
