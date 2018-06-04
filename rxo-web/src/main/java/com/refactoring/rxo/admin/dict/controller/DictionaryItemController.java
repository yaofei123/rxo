package com.refactoring.rxo.admin.dict.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.refactoring.rxo.entity.dict.DictionaryItem;
import com.refactoring.rxo.admin.dict.service.DictionaryItemService;
import com.refactoring.rxo.springmvc.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 管理数据字典项目
 */
@RestController
@RequestMapping(value = "/admin/dict/dictionaryItem")
public class DictionaryItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryItemController.class);


    @Autowired
    private DictionaryItemService dictionaryItemService;


    /**
     * 新增数据字典项目
     *
     * @param dictionaryItem
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DictionaryItem add(@RequestBody @Valid DictionaryItem dictionaryItem) {

        dictionaryItemService.insert(dictionaryItem);

        return dictionaryItem;
    }

    /**
     * 删除数据字典项目,id以逗号分隔
     *
     * @param idArray
     */
    @DeleteMapping(value = "/{idArray}")
    public void delete(@PathVariable String idArray) {

        LOGGER.debug("delete dictionaryItem :{}", idArray);

        String[] ids = idArray.split(",");
        dictionaryItemService.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 更新数据字典项目
     *
     * @param dictionaryItem
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public DictionaryItem update(@RequestBody @Valid DictionaryItem dictionaryItem, @PathVariable String id) {
        dictionaryItem.setId(id);
        dictionaryItemService.updateById(dictionaryItem);

        return dictionaryItem;
    }

    /**
     * 根据ID查询数据字典项目
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public DictionaryItem get(@PathVariable String id) {

        DictionaryItem dictionaryItem = dictionaryItemService.selectById(id);

        return dictionaryItem;
    }

    /**
     * 查询数据字典项目列表
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/list")
    public Page<DictionaryItem> list(@RequestBody PageRequest<DictionaryItem> pageRequest) {


        return dictionaryItemService.findPage(new Page<>(pageRequest.getPage(), pageRequest.getLimit()), pageRequest.getCondition());

    }

    /**
     * 查询数据字典业务列表
     *
     * @param
     * @return
     */
    @GetMapping("/listAll/{typeId}")
    public List<DictionaryItem> list(@PathVariable String typeId) {

        return dictionaryItemService.selectList(new EntityWrapper<>(new DictionaryItem(typeId)));

    }

}
