package com.shipu.mapper;

import java.util.List;

import com.shipu.model.Comment;

public interface CommentMapper {
     public boolean addComment(Comment comment);
     public boolean deleteComment(Comment comment);
     public List<Comment> findCommentById(Integer recipeId);
}
