package com.ckq.controller;

import com.alibaba.fastjson.JSONArray;
import com.ckq.util.excel.ExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/import")
    public JSONArray importUser(@RequestPart("file")MultipartFile file) throws Exception{
        JSONArray array = ExcelUtils.readMultipartFile(file);
        System.out.println("array = " + array);
        return array;
    }

}
