package com.example.youtubeClone.repository.comment;

import com.example.youtubeClone.dto.Board;
import com.example.youtubeClone.dto.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryCommentRepositoryTest {

    MemoryCommentRepository memoryCommentRepository = new MemoryCommentRepository();

    @AfterEach
    public void afterEach(){
        memoryCommentRepository.clear();
    }

    @Test
    void save() {
        Comment comment = new Comment();
        comment.setCommentName("배진환");
        comment.setCommentContent("안녕하세요");
        Board board = new Board();
        board.setId(123L);
;
        memoryCommentRepository.save(comment, board.getId());

        Comment result = memoryCommentRepository.findById(comment.getCommentId());
        assertThat(comment).isEqualTo(result);
    }

    @Test
    void update() {
        Comment comment = new Comment();
        comment.setCommentContent("안녕하세요");
        Board board = new Board();
        board.setId(123L);
        memoryCommentRepository.save(comment, board.getId());
        Comment comment1 = new Comment();
        comment1.setCommentContent("안녕");
        memoryCommentRepository.update(comment.getCommentId(), comment1);

        assertThat(comment1.getCommentContent()).isEqualTo(comment.getCommentContent());
    }

    @Test
    void delete() {
        Comment comment = new Comment();
        comment.setCommentName("배진환");
        comment.setCommentContent("안녕하세요");
        Board board = new Board();
        board.setId(123L);
        memoryCommentRepository.save(comment, board.getId());

        memoryCommentRepository.delete(comment.getCommentId());

        assertThat(memoryCommentRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void findById() {
        Comment comment = new Comment();
        comment.setCommentName("배진환");
        Board board = new Board();
        board.setId(123L);
        memoryCommentRepository.save(comment, board.getId());

        Comment result = memoryCommentRepository.findById(comment.getCommentId());

        assertThat(comment).isEqualTo(result);
    }

    @Test
    void findAll() {
        Comment comment = new Comment();
        Comment comment1 = new Comment();
        comment.setCommentName("배진환");
        comment1.setCommentName("김경규");

        Board board = new Board();
        board.setId(123L);
        memoryCommentRepository.save(comment, board.getId());
        memoryCommentRepository.save(comment1, board.getId());

        List<Comment> result = memoryCommentRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}