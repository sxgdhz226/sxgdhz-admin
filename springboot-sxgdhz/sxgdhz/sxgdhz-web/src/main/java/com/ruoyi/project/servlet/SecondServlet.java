package com.ruoyi.project.servlet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "firstServlet", urlPatterns = "/firstServlet")  //标记为servlet，以便启动器扫描。
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("firstServlet");
    }
}
