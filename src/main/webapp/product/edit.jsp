<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            width: 500px;
            margin: 50px auto;
        }

        .container {
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        button {
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Edit Product</h1>

    <form action="${pageContext.request.contextPath}/products?action=update"
          method="post">

        <input type="hidden"
               name="id"
               value="${product.id}">

        <label>Product Name</label>

        <input type="text"
               name="name"
               value="${product.name}"
               required>

        <label>Price</label>

        <input type="number"
               name="price"
               step="0.01"
               min="0"
               value="${product.price}"
               required>

        <label>Quantity</label>

        <input type="number"
               name="quantity"
               min="0"
               value="${product.quantity}"
               required>

        <button type="submit">
            Update Product
        </button>

    </form>

    <br>

    <a href="${pageContext.request.contextPath}/products">
        Back to Product List
    </a>

</div>

</body>
</html>
