package com.shipu.service;

import java.util.List;

import com.shipu.model.Comment;

public interface CommentService {
	 public boolean addComment(Comment comment);
     public boolean deleteComment(Comment comment);
     public List<Comment> findCommentById(Integer recipeId);
}
