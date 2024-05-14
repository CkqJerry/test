package com.ckq.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ckq.entity.ItemDTO;
import com.ckq.listener.ItemExcelListener;
import com.ckq.service.ItemService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ItemServiceImpl implements ItemService {
	//根据自己使用的框架注入数据库访问对象，用于与数据库交互并操作数据库中的数据
	/*@Resource
    private ItemMapper itemMapper;*/

    @Override
    public void importExcel(InputStream inputStream) {
        ItemExcelListener itemExcelListener = new ItemExcelListener(this);
        EasyExcel.read(inputStream, ItemDTO.class, itemExcelListener).sheet().doRead();
    }
    
	// ... 其他方法

}
