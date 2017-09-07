package com.shipu.mapper;

import java.util.List;

import com.shipu.model.Collections;

public interface CollectionsMapper {
    public boolean addCollection(Collections collection);
    public boolean deleteCollection(Collections collection);
    public List<Collections> findAllCollectionById(Integer id);
}
