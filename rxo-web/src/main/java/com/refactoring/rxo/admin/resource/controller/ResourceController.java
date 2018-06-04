package com.refactoring.rxo.admin.resource.controller;

import com.refactoring.rxo.entity.account.Resource;
import com.refactoring.rxo.admin.resource.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 管理资源
 */
@RestController
@RequestMapping(value = "/admin/resource")
public class ResourceController {

    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource add(@RequestBody Resource resource) {

        resourceService.insert(resource);

        return resource;
    }

    @DeleteMapping(value = "/{idArray}")
    public void delete(@PathVariable String idArray) {
        resourceService.deleteBatchIds(Arrays.asList(idArray, String.class));
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    Resource update(
            @RequestBody Resource resource, @PathVariable String id) {
        resource.setId(id);
        resourceService.updateById(resource);

        return resource;
    }

    @GetMapping(value = "/{id}")
    public Resource get(@PathVariable String id) {

        return resourceService.selectById(id);
    }
}
