package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.CollectionsMapper;
import com.shipu.model.Collections;
import com.shipu.service.CollectionService;
@Service
@Transactional
public class CollectionServiceImpl implements CollectionService{
	@Autowired
    private CollectionsMapper collectionMapper;
	public boolean addCollection(Collections collection) {
		return collectionMapper.addCollection(collection);
	}

	public boolean deleteCollection(Collections collection) {
		return collectionMapper.deleteCollection(collection);
	}

	public List<Collections> findAllCollectionById(Integer id) {
		return collectionMapper.findAllCollectionById(id);
	}

}
