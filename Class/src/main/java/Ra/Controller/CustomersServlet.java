package Ra.Controller;

import Ra.Model.Customers;
import Ra.Service.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomersServlet", value = "/CustomersServlet")
public class CustomersServlet extends HttpServlet {
    protected CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        customerService.save(new Customers(1, "Hoang Hieu", "HoangHieu@gmail.com", "CM"));
        customerService.save(new Customers(2, "GiangCaCa", "GiangCaCa@gmail.com", "HN"));
        customerService.save(new Customers(3, "LongCoCo", "LongCoCo@gmail.com", "HN"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "GETALL":
                    break;
                case "DELETE":
                    int del = Integer.parseInt(request.getParameter("id"));
                    customerService.deleteById(del);
                    break;
                case "EDIT":
                    int id = Integer.parseInt((request.getParameter("id")));
                    Customers customer = customerService.findById(id);
                    request.setAttribute("customer", customer);
                    request.getRequestDispatcher("/WEB-INF/Customers/edit.jsp").forward(request, response);
                    break;
                case "CREATE":
                    request.getRequestDispatcher("/WEB-INF/Customers/create.jsp").forward(request, response);
                case "SEARCH":
                    String searchName = request.getParameter("search");
                    String sort = request.getParameter("sort");
                    String By = request.getParameter("By");
                    List<Customers> listSearch = searchCustomer(searchName, sort, By);
                    request.setAttribute("searchName", searchName);
                    request.setAttribute("sort", sort);
                    request.setAttribute("By", By);
                    showCustomer(listSearch, request, response);
                    break;

                default:
                    break;
            }
            showCustomer(customerService.findall(), request, response);
        }
    }

    protected List<Customers> searchCustomer(String name, String sort, String by) {
        List<Customers> searchList = new ArrayList<>();
        for (Customers c : customerService.findall()) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                searchList.add(c);
            }
        }
        switch (sort) {
            case "name":
                if (by.equalsIgnoreCase("ASC")) {
                    searchList.sort(Comparator.comparing(Customers::getName));
                } else {
                    searchList.sort(Comparator.comparing(Customers::getName).reversed());
                }
                break;
            case "email":
                if (by.equalsIgnoreCase("ASC")) {
                    searchList.sort(Comparator.comparing(Customers::getEmail));
                } else {
                    searchList.sort(Comparator.comparing(Customers::getEmail).reversed());
                }
                break;
            case "address":
                if (by.equalsIgnoreCase("ASC")) {
                    searchList.sort(Comparator.comparing(Customers::getAddress));
                } else {
                    searchList.sort(Comparator.comparing(Customers::getAddress).reversed());
                }
                break;
            default:
                break;
        }
        return searchList;
    }

    protected void showCustomer(List<Customers> customersList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", customersList);
        request.getRequestDispatcher("/WEB-INF/Customers/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String address = request.getParameter("address");
                    customerService.save(new Customers(id, name, email, address));
                    break;
                case "ADD":
                    int idNew = customerService.newId();
                    String nameNew = request.getParameter("name");
                    String emailNew = request.getParameter("email");
                    String addressNew = request.getParameter("address");
                    customerService.save(new Customers(idNew, nameNew, emailNew, addressNew));
                default:
                    break;
            }
            showCustomer(customerService.findall(), request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}