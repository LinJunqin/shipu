package com.shipu.service;

import java.util.List;

import com.shipu.model.Collections;

public interface CollectionService {
	    public boolean addCollection(Collections collection);
	    public boolean deleteCollection(Collections collection);
	    public List<Collections> findAllCollectionById(Integer id);
}
