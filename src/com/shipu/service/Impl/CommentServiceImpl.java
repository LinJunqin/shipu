package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.CommentMapper;
import com.shipu.model.Comment;
import com.shipu.service.CommentService;
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
    private CommentMapper commentMapper;
	public boolean addComment(Comment comment) {
		
		return commentMapper.addComment(comment);
	}

	public boolean deleteComment(Comment comment) {
		
		return commentMapper.deleteComment(comment);
	}

	public List<Comment> findCommentById(Integer recipeId) {
		
		return commentMapper.findCommentById(recipeId);
	}
    
}
