<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Description</title>
    <title>${enterprise.name}</title>

    <link href="/resources/css/describe.css" rel="stylesheet">

    <jsp:include page="bootstrap.jsp"/>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript" src="/resources/js/json.js"> </script>
  <%--  <script type="text/javascript" src="/resources/js/jquery.form.min.js">

    </script>--%>


    <script type="text/javascript">
        var geocoder;
        var map;
        function initialize() {
            geocoder = new google.maps.Geocoder();
            var latlng = new google.maps.LatLng(${enterpriseDescription.latitude}, ${enterpriseDescription.longitude});
            var myOptions = {
                zoom: 15,
                center: latlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

            myMarker = new google.maps.Marker({ position: latlng, map: map, title: "About.com Headquarters" });
        }

        function codeAddress() {
            var address = document.getElementById('address').value;
            geocoder.geocode({ 'address': address}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location
                    });
                } else {
                    alert('Geocode was not successful for the following reason: ' + status);
                }
            });
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
    <style type="text/css">
        a.comment_reply_author {
            color: #000000;
        }
    </style>


    <script type="text/javascript">
        function replyComment(author) {
            var textArea = document.getElementById("commentContent");
            textArea.textContent = author + ", ";
            var btn = document.getElementById("addBtn");
            btn.value = "add";
            location.href = "#commentContent";
            textArea.focus();
        }

        function editComment(commentId) {
            if (!hiddenFormIsEmpty()) {
                cancelEdit();
            }
            hideCommentDisplayDiv(commentId);
            fillHiddenForm(commentId, getCommentById(commentId)["content_to_edit"].toString());
            showForm();
        }

        function getCommentById(commentId) {
            var ajaxRequest = new XMLHttpRequest();
            ajaxRequest.open("GET", "/ajax/getComment?id=" + commentId, false);
            ajaxRequest.send();
            return JSON.parse(ajaxRequest.responseText);
        }

        function deleteComment(commentId) {
            var ajaxRequest = new XMLHttpRequest();
            ajaxRequest.open("GET", "/ajax/deleteComment?id=" + commentId, false);
            ajaxRequest.send();
            if (!hiddenFormIsEmpty()) {
                cancelEdit();
            }
            removeCommentFromDocument(commentId);
        }

        function confirmEdit() {
            getHiddenForm().ajaxSubmit({url: '/ajax/updateComment', type: 'post'});

            // $("#comment_edit_form").ajaxSubmit({url: '/ajax/updateComment', type: 'post'});
            cancelEdit();
        }

        function cancelEdit() {
            var oldCommentId = getHiddenIdField().value;
            cleanHiddenForm();
            hideForm();
            showCommentDisplayDiv(oldCommentId);
        }

        function removeCommentFromDocument(commentId) {
            var commentForDelete = document.getElementById("comment" + commentId);
            commentForDelete.parentNode.removeChild(commentForDelete);
        }

        function getHiddenForm() {
            return document.getElementById("comment_edit_form");
        }

        function getHiddenIdField() {
            return document.getElementById("comment_edit_id");
        }

        function getHiddenTextArea() {
            return document.getElementById("comment_edit_input");
        }

        function fillHiddenForm(commentId, commentContent) {
            getHiddenIdField().value = commentId;
            getHiddenTextArea().textContent = commentContent;
        }

        function cleanHiddenForm() {
            if (getHiddenForm() !== 'undefined') {
                getHiddenIdField().value = "none";
                getHiddenTextArea().textContent = "";
            }
        }

        function hiddenFormIsEmpty() {
            return getHiddenIdField().value == "none";
        }

        function hideForm() {
            var hiddenForm = getHiddenForm();
            hiddenForm.style.display = "none";
            hiddenForm.parentNode.removeChild(hiddenForm);
            document.getElementById("hidden_form_container").appendChild(hiddenForm);
        }

        function showForm() {
            var hiddenForm = getHiddenForm();
            var id = getHiddenIdField().value;
            hiddenForm.parentNode.removeChild(hiddenForm);
            document.getElementById("comment_content" + id).appendChild(hiddenForm);
            hiddenForm.style.display = "inline";
        }

        function hideCommentDisplayDiv(commentId) {
            document.getElementById("comment_display_text" + commentId).style.display = "none";
        }

        function showCommentDisplayDiv(commentId) {
            document.getElementById("comment_display_text" + commentId).style.display = "inline";
        }


    </script>

</head>
<body onload="initialize()">
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 align="center">
                ${enterpriseDescription.name}
            </h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <img alt="140x140" src="/resources/images/logos/${enterpriseDescription.logoPath}" width="140"
                 height="140"/>
        </div>
        <div class="col-md-9">
            <div class="well">
                <p>
                    ${enterpriseDescription.description}
                </p>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>
                        Category name
                    </th>
                    <th>
                        Location
                    </th>
                    <th>
                        Min discount
                    </th>
                    <th>
                        Max discount
                    </th>
                    <th>
                        Contact
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr class="info">
                    <td>
                        ${enterpriseDescription.category.name}
                    </td>
                    <td>
                        ${enterpriseDescription.city.name},
                        <br>
                        ${enterpriseDescription.address}
                    </td>
                    <td>
                        ${enterpriseDescription.discountMin} %
                    </td>
                    <td>
                        ${enterpriseDescription.discountMax} %
                    </td>
                    <td>
                        mail: ${enterpriseDescription.mail}
                        <br>
                        website: <a href="/${enterpriseDescription.webSite}">${enterpriseDescription.webSite}</a>
                        <br>
                        <abbr title="Phone">P:</abbr> ${enterpriseDescription.phone}
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>

    <div id="map_canvas" style="width:1100px; height:320px;"></div>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <div id="panel" class="form">
            <div class="form-group">
                <label for="address"> Address of enterprise:</label>
                <input id="address" type="text" value="${enterpriseDescription.address}" class="form-control"
                       placeholder="Address">
            </div>
            <input type="button" value="Show on map" onclick="codeAddress()" class="btn btn-default">
        </div>
    </security:authorize>
    <div id="map-canvas"></div>

    <div id="comments" class="row">
        <jsp:useBean id="commentHelper" class="com.springapp.mvc.utils.CommentHelper" scope="request">
            <security:authorize access="isAuthenticated()">
                <jsp:setProperty name="commentHelper" property="user" value="${user}"/>
            </security:authorize>

            <div id="comments">
                <h2>Comments</h2>

                <div id="hidden_form_container">
                    <form method="POST" id="comment_edit_form" action="/ajax/updateComment" style="display: none;">
                        <div id="comment_edit_area">
                            <input type="hidden" id="comment_edit_id" name="comment_id" value="none">
                            <label for="comment_edit_input">editing comment</label>
                            <textarea name="edit_comment_content" id="comment_edit_input"
                                      class="form-control"></textarea>
                        </div>
                        <div id="comment_edit_buttons">
                            <input type="button" value="confirm"
                                   onclick="confirmEdit()"/>
                            <input type="button" value="cancel"
                                   onclick="cancelEdit()"/>
                        </div>
                    </form>
                </div>
                <c:forEach items="${comments}" var="comment">
                    <jsp:setProperty name="commentHelper" property="comment" value="${comment}"/>
                    <div id="comment${comment.id}" class="commentWell">
                            <%--COMMENT_HEADER--%>
                        <div id="commend_header${comment.id}">
                            <input type="hidden" value="${comment.id}"/>

                            <div id="comment_author">
                                <a class="author" href="<c:url value="/user/profile/${comment.user.nickname}"/> ">
                                    <jsp:getProperty name="commentHelper" property="author"/>
                                </a>
                            </div>
                            <div id=comment_user_buttons style="float: left">
                                <security:authorize access="isAuthenticated()">
                                    <c:if test="${comment.user.id == user.id}">
                                        <input type="button" value="edit"
                                               onclick="editComment('${comment.id}')"
                                               class="btn btn-link"/>
                                        <input type="button" value="delete"
                                               onclick="deleteComment('${comment.id}')"
                                               class="btn btn-link"/>
                                    </c:if>
                                </security:authorize>
                            </div>
                        </div>
                            <%--COMMENT_CONTENT--%>
                        <div id="comment_content${comment.id}" class="comment_content">
                                <%--DISPLAY_CONTENT--%>
                            <div id="comment_display_text${comment.id}">
                                <jsp:getProperty name="commentHelper" property="messageToDisplay"/>
                            </div>
                                <%--HIDDEN_EDIT_CONTENT_PLACE--%>
                        </div>
                            <%--COMMENT_FOOTER--%>
                        <div id="comment_footer${comment.id}">
                            <div id="comment_creation_date${comment.id}">
                                    ${comment.date}
                            </div>
                            <div id="comment_reply${comment.id}">
                                <security:authorize access="isAuthenticated()">
                                    <input type="button" value="reply"
                                           onclick="replyComment('${comment.user.nickname}')"/>
                                </security:authorize>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="page-header"></div>

            <security:authorize access="not isAuthenticated()">
                Only authorized users can leave a comments.
                <a href="/login">Sing in</a> or
                <a href="/registration">Register</a>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <div id="newCommentDiv" style="margin: 50px">
                    <form:form commandName="newComment" method="post" action="/describe/addComment" class="form">
                        <div class="form-group">
                            <label for="commentContent">Leave your comment, </label>
                            <label>${user.nickname}</label>
                        </div>
                        <form:hidden id="commentId" path="id"/>
                        <form:hidden path="ratio"/>
                        <div class="form-group">
                            <form:textarea id="commentContent" path="content" onfocus="this.value = this.value;"
                                           class="form-control"/>
                        </div>
                        <form:hidden path="enterprise.id"/>
                        <form:hidden path="user.id"/>
                        <div class="form-group">
                            <input type="submit" id="addBtn" value="Add" onclick="cleanId()"
                                   class="btn btn-info">
                        </div>
                    </form:form>
                </div>
            </security:authorize>
        </jsp:useBean>
    </div>


</div>


</body>
</html>