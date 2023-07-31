package org.denischernous.homeworks.lesson3.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.denischernous.homeworks.lesson3.model.Shop;
import org.denischernous.homeworks.lesson3.service.ShopService;
import org.denischernous.homeworks.lesson3.service.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shops")
public class ShopServlet extends HttpServlet {
    private final ShopService shopService;
    ObjectMapper objectMapper = new ObjectMapper();

    public ShopServlet() {
        super();
        shopService = new ShopServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<Shop> shops = shopService.getAll();
        resp.getWriter().write(objectMapper.writeValueAsString(shops));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Shop shop = objectMapper.readValue(req.getInputStream(), Shop.class);

            if (shop == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Shop data");
                return;
            }

            Shop createdShop = shopService.save(shop);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), createdShop);

        } catch (JsonProcessingException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to parse Shop data");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Shop shop = objectMapper.readValue(req.getReader(), Shop.class);
            if (shop == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Shop data");
                return;
            }

            Shop updatedShop = shopService.update(shop);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), updatedShop);
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to parse Shop data");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("shop_id");
        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Shop id");
            return;
        }
        int shopId;
        try {
            shopId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Shop id");
            return;
        }
        shopService.deleteById(shopId);
    }
}
