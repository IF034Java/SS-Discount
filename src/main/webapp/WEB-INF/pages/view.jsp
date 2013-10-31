<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>View</title>

    <jsp:include page="bootstrap.jsp"/>

    <script type="text/javascript">
        function getNames()
        {
            var nameOfCategories = new Array();
            <c:forEach items="${enterprisesCount}" var="pairCount">
                nameOfCategories.push(${pairCount.key.name});
            </c:forEach>
            alert('Value => '+nameOfCategories[0])
           return nameOfCategories.join(',');
        }
        function getCountOfEnterprises(){
          var numberOfEnterprises = new Array();
          <c:forEach items="${enterprisesCount}" var="pairCount">
            numberOfEnterprises.push(${pairCount.value});
          </c:forEach>
          alert('Value => '+numberOfEnterprises[0]);
        return numberOfEnterprises.join(',');
        }
    </script>

    <script type='text/javascript'>

        var options = {
            chart: {
                renderTo: 'chart',
                type: 'bar'
            },
            title: {
                text: 'Fruit Consumption'
            },
            xAxis: {
                categories: [
                        document.write(getNames())
                ]
            },
            yAxis: {
                title: {
                    text: 'Fruit eaten'
                }
            },
            series: [{
                data: [
                    document.write(getCountOfEnterprises())
                ]
            }]
        };

        $(document).ready(function(){

            var chart1 = new Highcharts.Chart(options);


            $("#addCategoryForm").validate({

                rules:{

                    name:{
                        required: true,
                        minlength: 4,
                        maxlength: 16
                    }

                },

                messages:{

                    name:{
                        required: "This field is required",
                        minlength: "Category name must be at least 4 characters long",
                        maxlength: "Maximum number of characters 16"
                    }

                }

            });

            document.body.appendChild(document.createTextNode(getNames()));
        });
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <ul class="nav nav-tabs">
        <li class="active"><a href="/view">Enterprises</a></li>
        <li><a href="/category">Categories</a></li>
        <li><a href="/city">Cities</a></li>
        <li><a href="/user">Users</a></li>
    </ul>
</div>

<div class="container">
    <div class="container">
        <table class="table table-striped">
            <tr><thead style="text-align: center"><h3>Enterprises</h3></thead></tr>
            <tr>
                <th>Enterprise</th>
                <th>City</th>
                <th>Category</th>
                <th>Minimum discount</th>
                <th>Maximum discount</th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach items="${enterpriseList}" var="enterprise">
                <tr>
                    <td> <a href="describe/${enterprise.id}" class="btn btn-default btn-block"> ${enterprise.name} </a></td>
                    <td>${enterprise.city.name}</td>
                    <td>${enterprise.category.name}</td>
                    <td>${enterprise.discountMin}</td>
                    <td>${enterprise.discountMax}</td>
                    <td><a class="btn btn-info" href="/enterprises/edit/${enterprise.id}">Edit</a></td>
                    <td><a class="btn btn-danger" href="/view/deleteEnterprise/${enterprise.id}">Delete</a></td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <a class="btn btn-success" href="/enterprises">Add new</a>

    <div id="chart" style="width:100%; height:400px;"></div>
</div>

</body>
</html>