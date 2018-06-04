package com.refactoring.rxo.admin.resource.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.refactoring.rxo.admin.resource.dao.ResourceDao;
import com.refactoring.rxo.entity.account.Resource;
import com.refactoring.rxo.admin.resource.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

    @Override
    public void sortResourceList(List<Resource> resourceList) {
        Collections.sort(resourceList, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                o1.setOrderIndex(o1.getOrderIndex() == null ? 0 : o1.getOrderIndex());
                o2.setOrderIndex(o2.getOrderIndex() == null ? 0 : o2.getOrderIndex());

                //如果排序不相等
                if (o1.getOrderIndex() != o2.getOrderIndex()) {
                    return o1.getOrderIndex().compareTo(o2.getOrderIndex());
                }

                //如果排序相等，则比较ID
                return o1.getId().compareTo(o2.getId());
            }
        });
    }

    @Override
    public void fillResource(List<Resource> resourceList) {
        HashMap<String, Resource> resourceHashMap = new HashMap<>();
        List<Resource> result = new ArrayList<>();

        for (Resource resource : resourceList) {
            //将查询到的资源整理成map
            resourceHashMap.put(resource.getId(), resource);
            //将根目录放入结果集
            if ("-1".equals(resource.getParentId())) {
                result.add(resource);
            }
        }

        //开始拼装资源树
        String tempParentId;
        Resource tempParentResource;
        for (Resource resource : resourceList) {

            tempParentId = resource.getParentId();

            //如果是顶级节点, 则处理下一个
            if ("-1".equals(resource.getParentId())) {
                continue;
            }

            tempParentResource = resourceHashMap.get(tempParentId);
            if (tempParentResource != null) {
                tempParentResource.addChild(resource);
            }

        }
    }

    @Override
    public List<Resource> selectByName(String name) {
        return baseMapper.selectByName(name);
    }
}
