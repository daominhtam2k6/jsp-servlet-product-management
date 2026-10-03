package com.productmanagement.servlet;

import com.productmanagement.dao.ProductDAO;
import com.productmanagement.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "create":
                showCreateForm(request, response);
                break;

            case "edit":
                showEditForm(request, response);
                break;

            case "delete":
                deleteProduct(request, response);
                break;

            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action =
                request.getParameter("action");

        if ("create".equals(action)) {

            insertProduct(request, response);

        } else if ("update".equals(action)) {

            updateProduct(request, response);
        }
    }

    private void listProducts(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products =
                productDAO.selectAllProducts();

        request.setAttribute(
                "products",
                products
        );

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/product/list.jsp"
                );

        dispatcher.forward(
                request,
                response
        );
    }

    private void showCreateForm(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/product/create.jsp"
                );

        dispatcher.forward(
                request,
                response
        );
    }

    private void insertProduct(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String name =
                request.getParameter("name");

        double price =
                Double.parseDouble(
                        request.getParameter("price")
                );

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity")
                );

        Product product =
                new Product(
                        name,
                        price,
                        quantity
                );

        productDAO.insertProduct(product);

        response.sendRedirect(
                request.getContextPath()
                        + "/products"
        );
    }

    private void showEditForm(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        Product product =
                productDAO.selectProduct(id);

        request.setAttribute(
                "product",
                product
        );

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/product/edit.jsp"
                );

        dispatcher.forward(
                request,
                response
        );
    }

    private void updateProduct(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        String name =
                request.getParameter("name");

        double price =
                Double.parseDouble(
                        request.getParameter("price")
                );

        int quantity =
                Integer.parseInt(
                        request.getParameter("quantity")
                );

        Product product =
                new Product(
                        id,
                        name,
                        price,
                        quantity
                );

        productDAO.updateProduct(product);

        response.sendRedirect(
                request.getContextPath()
                        + "/products"
        );
    }

    private void deleteProduct(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        productDAO.deleteProduct(id);

        response.sendRedirect(
                request.getContextPath()
                        + "/products"
        );
    }
}
