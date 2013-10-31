package com.springapp.mvc.web;// Created with IntelliJ IDEA by Yaroslav Kovbas (Xardas)

import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.service.ICommentService;
import com.springapp.mvc.utils.CommentHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    ICommentService commentService;

    @Autowired
    CommentHelper commentHelper;


    @RequestMapping(value = "/getComment", method = RequestMethod.GET)
    public @ResponseBody String getCommentToEdit(@RequestParam MultiValueMap<String, String> params){
        Comment comment = commentService.get(Integer.parseInt(params.getFirst("id")));
        if(comment == null) {
            System.out.println("comment not found");
        } else {
            JSONObject response = new JSONObject();
            try {
                commentHelper.setComment(comment);
                response.put("content_to_edit", commentHelper.getMessageToEdit());
                response.put("content_to_display", commentHelper.getMessageToDisplay());
                response.put("date", comment.getDate());
                response.put("edit_date", comment.getEditDate());
            } catch (JSONException e) {
                System.out.println("Json exception ,message:" + e.getLocalizedMessage());
            }
            return response.toString();
        }

        return null;
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public @ResponseBody String deleteComment(@RequestParam MultiValueMap<String, String> params){
        commentService.delete(Integer.parseInt(params.getFirst("id")));
        return "true";
    }

    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public @ResponseBody String updateComment(@RequestParam("comment_id") Integer commentId,
                                              @RequestParam("edit_comment_content") String commentContent ){
        System.out.println("AjaxHandled-Update");
        Comment commentToUpdate = commentService.get(commentId);
        commentToUpdate.setContent(commentContent);
        return "true";
    }
}
