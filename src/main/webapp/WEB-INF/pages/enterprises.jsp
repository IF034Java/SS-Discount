<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>
        <h2>Enterprise management </h2>
    </title>
    <link href="/resources/css/new-enterprise.css" rel="stylesheet">
    <jsp:include page="bootstrap.jsp"/>

    <script type='text/javascript'>
        $(document).ready(function () {

            $("#manageEnterpriseForm").validate({

                rules: {

                    name: {
                        required: true,
                        minlength: 4,
                        maxlength: 16
                    },
                    discountMin: {
                        required: true

                    },
                    discountMax: {
                        required: true

                    },

                    Description: {
                        required: true,
                        minlength: 5,
                        maxlength: 800
                    },
                    mail: {
                        required: true,
                        email: true,
                        minlength: 4,
                        maxlength: 16
                    },
                    webSite: {
                        required: true,
                        minlength: 5,
                        maxlength: 60
                    },
                    latitude: {
                        required: true

                    },
                    longitude: {
                        required: true

                    },
                    phone: {
                        required: true,
                        minlength: 5,
                        maxlength: 20
                    },
                    address: {
                        required: true,
                        minlength: 5

                    }
                },

                messages: {

                    name: {
                        required: "This field is required",
                        minlength: "Name must be at least 4 characters long",
                        maxlength: "Maximum number of characters 16"
                    },
                    discountMin: {
                        required: "This field is required"

                    },
                    discountMax: {
                        required: "This field is required"
                    },
                    Description: {
                        required: "This field is required",
                        minlength: "Description must be at least 5 characters long",
                        maxlength: "Maximum number of characters 800"
                    },
                    mail: {
                        required: "This field is required",
                        minlength: "E-mail must be at least 4 characters long",
                        maxlength: "Maximum number of characters 16"
                    },
                    webSite: {
                        required: "This field is required",
                        minlength: "Website must be at least 5 characters long",
                        maxlength: "Maximum number of characters 60"
                    },
                    latitude: {
                        required: "This field is required"
                    },
                    longitude: {
                        required: "This field is required"
                    },
                    phone: {
                        required: "This field is required",
                        minlength: "Phone must be at least 5 characters long",
                        maxlength: "Maximum number of characters 20"
                    },
                    address: {
                        required: "This field is required",
                        minlength: "Address must be at least 5 characters long"

                    }

                }

            });

        });
    </script>

</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container well">
    <div style="text-align: center">
        <form:form method="post" enctype="multipart/form-data"
                   modelAttribute="uploadedFile" action="/enterprises/uploadFile">
            <div class="container">
                <div class="row" style="margin-bottom: 15px">
                    <div class="col-md-1">
                        Upload File:
                    </div>
                    <div class="col-md-6">
                        <input type="file" name="file" class="btn btn-default"/>
                        <form:errors path="file" class="error"/>
                    </div>
                    <div class="col-md-5"></div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <input type="submit" value="Upload" class="btn btn-info"/>
                    </div>
                    <div class="col-md-9"></div>
                </div>
            </div>
        </form:form>
    </div>

    <form:form id="manageEnterpriseForm" method="POST" commandName="enterprise" class="form-horisontal">
        <h3>Management of enterprises</h3>
        <c:if test="${!empty enterprise.logoPath}">
            <h4>Your logo:</h4>
            ${enterprise.logoPath}
        </c:if>
        <div class="row">
            <div class="col-sm-4">
                <h4>Select category</h4>
                <form:select path="category.id" class="form-control">
                    <form:options items="${categoryList}" itemLabel="name" itemValue="id"/>
                </form:select>
            </div>
            <div class="col-sm-4">
                <h4>Select city</h4>
                <form:select path="city.id" class="form-control">
                    <form:options items="${cityList}" itemLabel="name" itemValue="id"/>
                </form:select>
            </div>
        </div>

        <form:hidden path="id"/>
        <div class="form-group">
            <h4>Enter name</h4>
            <form:input id="name" path="name" class="form-control"/>
            <form:errors path="name" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4>Min discount </h4>
            <form:input id="discountMin" path="discountMin" class="form-control" type="number"/>
            <form:errors path="discountMin" class="form-control alert alert-danger"/>
        </div>

        <div class="form-group">
            <h4> Max discount </h4>
            <form:input id="discountMax" path="discountMax" class="form-control" type="number"/>
            <form:errors path="discountMax" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4>Description </h4>
            <form:textarea id="Description" class="form-control" path="Description"/>
            <form:errors path="Description" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4> e-mail </h4>
            <form:input class="form-control" path="mail"/>
            <form:errors path="mail" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4> Website </h4>
            <form:input id="webSite" class="form-control" path="webSite"/>
            <form:errors path="webSite" class="form-control alert alert-danger"/>
        </div>

        <div class="form-group">
            <h4> Enter latitude </h4>
            <form:input id="latitude" class="form-control" path="latitude"/>
            <form:errors path="latitude" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4> Enter longitude </h4>
            <form:input id="longitude" class="form-control" path="longitude"/>
            <form:errors path="longitude" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4> Phone </h4>
            <form:input id="phone" class="form-control" path="phone"/>
            <form:errors path="phone" class="form-control alert alert-danger"/>
        </div>
        <div class="form-group">
            <h4> Address </h4>
            <form:input id="address" class="form-control" path="address"/>
            <form:errors path="address" class="form-control alert alert-danger"/>
        </div>
        <form:hidden path="logoPath"/>
        <input type="submit" class="btn btn-primary" value="${action}" formaction="/enterprises/${action}"/>
    </form:form>

</div>
</body>
</html>