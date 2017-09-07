package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.NewsMapper;
import com.shipu.model.News;
import com.shipu.service.NewsService;
@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	@Autowired
    private NewsMapper newsMapper;
	public boolean addNews(News news) {
		return newsMapper.addNews(news);
	}

	public boolean deleteNews(Integer newsId) {
		return newsMapper.deleteNews(newsId);
	}

	public List<News> findNewsByType(News news) {
		return newsMapper.findNewsByType(news);
	}

	public List<News> findNewsByTypeAndNew(News news) {
		return newsMapper.findNewsByTypeAndNew(news);
	}

	public boolean modifyNewsStatus(Integer newsId) {
		return newsMapper.modifyNewsStatus(newsId);
	}

}
