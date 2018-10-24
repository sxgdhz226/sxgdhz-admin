package com.ruoyi.project.servlet;

import com.ruoyi.project.servlet.bean.UserBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 扫描登录
 * 二维码图片里包含的内容是一个uuid，手机端扫描后拿到uuid，将这个uuid作为参数向这个约定好的action发起请求
 * uuid，手机端登录用户的信息，服务端拿到后找到uuid这个key对应的value，将用户信息放进去。
 * 即http://www.yuorfei.com/scan_login?uuid=123456&user=yuorfei
 * @author sxx
 *
 */

@WebServlet("/scan_login")
public class ScanLogin extends HttpServlet{

    private static final long serialVersionUID = -8937026850389999735L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //手机端传来uuid和user信息
        String uuid = request.getParameter("uuid");
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        UserBean userBean = new UserBean();
        userBean.setId(Integer.valueOf(id));
        userBean.setName(name);
        this.getServletContext().setAttribute(uuid, userBean);
        PrintWriter pw = response.getWriter();
        pw.print("success");
        pw.flush();
        pw.close();
    }
}
