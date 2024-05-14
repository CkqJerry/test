package com.ckq.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.ckq.entity.ItemDTO;
import com.ckq.service.ItemService;

import java.util.List;

// 有个很重要的点 ItemExcelListener 不能被spring管理，然后里面用到spring可以构造方法传进去
public class ItemExcelListener extends AnalysisEventListener<ItemDTO> {
	//对数据进行操作和处理的对象，由于无法注入，所以需要用构造函数传进来
    private final ItemService itemService;

    /**
     * 定义100条数据存储一次，然后清理list，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    
    /**
     * 存储缓存的数据
     */
    private List<ItemDTO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

	//构造函数
    public ItemExcelListener(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 每一条数据解析都会调用该方法
     */
    @Override
    public void invoke(ItemDTO itemDTO, AnalysisContext analysisContext) {
        cachedDataList.add(itemDTO);
        // 达到BATCH_COUNT了，需要存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
        	//执行数据存储操作，需要自己来写相关逻辑
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成后调用该方法
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        //*******************************************************************
        //这里一定要确保cachedDataList不为空再调用saveData
        //否则当导入数据记录数等于BATCH_COUNT时可能会导致批量插入空数据数据报错
        //*******************************************************************  
        if (!cachedDataList.isEmpty()){
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    private void saveData(){
        // ... 批量保存数据的逻辑
    }

}
