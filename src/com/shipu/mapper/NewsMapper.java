package com.shipu.mapper;

import java.util.List;

import com.shipu.model.News;

public interface NewsMapper {
     public boolean addNews(News news);
     public boolean deleteNews(Integer newsId);
     public List<News> findNewsByType(News news);//消息列表，按类型区分
     public List<News> findNewsByTypeAndNew(News news); //新消息列表，按类型区分
     public boolean modifyNewsStatus(Integer newsId);
}
