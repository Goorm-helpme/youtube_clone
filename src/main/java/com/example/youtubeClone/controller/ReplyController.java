package com.example.youtubeClone.controller;

import com.example.youtubeClone.dto.Reply;
import com.example.youtubeClone.service.CommentService;
import com.example.youtubeClone.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;
    private final CommentService commentService;

    @PostMapping("/save")
    public String replySave(@ModelAttribute ReplyForm replyForm) {
        Reply reply = new Reply();
        reply.setContent(replyForm.getContent());
        reply.setUsername(replyForm.getUsername());
        reply.setBoardId(replyForm.getBoardId());
        reply.setDateTime(LocalDateTime.now().toString());
        replyService.addReply(replyForm.getParentId(), reply);
        String videoId = commentService.findCommentOne(replyForm.getParentId()).getVideoId();

        return "redirect:/video?id=" + videoId;
    }

    @PostMapping("/update")
    public String replyUpdate(@RequestBody ReplyForm replyForm) {
        Reply reply = replyService.findOne(replyForm.getId());

        reply.setContent(replyForm.getContent());
        reply.setDateTime(LocalDateTime.now().toString());
        replyService.updateReply(reply.getId(), reply);
        String videoId = commentService.findCommentOne(replyForm.getParentId()).getVideoId();

        return "redirect:/video?id=" + videoId;
    }

    @DeleteMapping("/delete")
    public String replyDelete(@RequestBody Map<String, Long> data) {
        Long id = data.get("id");
        Long parentId = data.get("parentId");
        replyService.deleteReply(parentId, id);
        return "redirect:/";
    }
}
