<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            width: 900px;
            margin: 40px auto;
            background-color: #f5f5f5;
        }

        h1 {
            text-align: center;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #333;
            color: white;
        }

        .add-button {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 15px;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .edit {
            color: #007bff;
        }

        .delete {
            color: red;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Product Management</h1>

    <a class="add-button"
       href="${pageContext.request.contextPath}/products?action=create">
        Add Product
    </a>

    <table>

        <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>

        <c:forEach var="product" items="${products}">

            <tr>

                <td>${product.id}</td>

                <td>${product.name}</td>

                <td>${product.price}</td>

                <td>${product.quantity}</td>

                <td>

                    <a class="edit"
                       href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">
                        Edit
                    </a>

                    |

                    <a class="delete"
                       href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}"
                       onclick="return confirm('Are you sure you want to delete this product?')">
                        Delete
                    </a>

                </td>

            </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>
