package com.springapp.mvc.web;

import com.springapp.mvc.entity.Comment;
import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.service.IEnterpriseService;
import com.springapp.mvc.service.IUserService;
import com.springapp.mvc.utils.CommentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DescriptionController {
    @Autowired
    private IEnterpriseService enterpriseService;
    @Autowired
    private IUserService userService;

    private static DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    }

    @RequestMapping(value = "/describe/{firmId}", method = RequestMethod.GET)
    public String describeFirm(@PathVariable("firmId") int firmId, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Enterprise enterprise = enterpriseService.get(firmId);
        boolean isAuthorized = false;
        if (auth.isAuthenticated()) {
            User user = userService.getUser(auth.getName());
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("newComment", enterpriseService.getEmptyComment(user.getId(), enterprise.getId()));
                isAuthorized = true;
            }
        }
        model.addAttribute("authorized", isAuthorized);
        model.addAttribute("enterpriseDescription", enterprise);
        model.addAttribute("comments", enterpriseService.getComments(firmId));
        return "describe";
    }

    @RequestMapping(value = "/describe/addComment", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("newComment") Comment comment) {
        comment.setDate(dateFormat.format(new Date()));
        if (comment.getId() != null && comment.getId() != 0) {
            Comment commentToUpdate = enterpriseService.getComment(comment.getId());
            commentToUpdate.setContent(getContentWithHref(comment.getContent()));
            enterpriseService.updateComment(commentToUpdate);
            return "redirect:/describe/" + comment.getEnterprise().getId();
        }
        comment.setContent(getContentWithHref(comment.getContent()));
        enterpriseService.addComment(comment);
        return "redirect:/describe/" + comment.getEnterprise().getId();
    }

    private String getContentWithHref(String content) {
        if (content.contains(",")) {
            String[] messagePart = content.split(",");
            if (userService.getUser(messagePart[0]) == null) {
                return content;
            }
            String message = mergeMessageParts(messagePart);
            return String.format("%s%s,%s", messagePart[0]
                                         , CommentHelper.getServiceSymbols()
                                         , message);
        } else {
            return content;
        }
    }

    private String mergeMessageParts(String[] messageParts) {
        StringBuilder resultMessage = new StringBuilder();
        for (int iterator = 1; iterator < messageParts.length; iterator++) {
            resultMessage.append(messageParts[iterator]);
            if(messageParts.length - 1 != iterator){
                resultMessage.append(",");
            }
        }
        return resultMessage.toString();
    }
}
