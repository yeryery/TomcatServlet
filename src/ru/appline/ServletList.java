package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    private Model model = Model.getInstance();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        if (id == 0) {
            pw.print("<html>"+
                    "<h3>Доступные пользователи<h3><br/>" +
                    "ID пользователя: " +
                    "<ul>");
            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
                pw.println("<li>" + entry.getKey() + "</li>"+
                        "<ul>"+
                        "<li>Имя: " + entry.getValue().getName() +"</li>" +
                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
                        "</ul>");
            }
            pw.println("</ul>"+
                    "<a href=\"index.jsp\">Домой</a>" +
                    "</html>");
        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                pw.println("<html>"+
                        "<h3>Такого пользователя нет ;(<h3><br/>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            } else {
                pw.println("<html>"+
                        "<h3>Запрошенный пользователь<h3><br/>"+
                        "Имя: " + model.getFromList().get(id).getName() + "<br/>" +
                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>" +
                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>" +
                        "<a href=\"index.jsp\">Домой</a>"+
                        "</html>");

            }
        } else {
            pw.println("<html>"+
                    "<h3>ID должен быть больше нуля!<h3><br/>"+
                    "<a href=\"index.jsp\">Домой</a>"+
                    "</html>");
        }
    }
    */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        User user =  model.getFromList().get(id);

        if (id == 0) {
            pw.println(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            if (user == null) {
                pw.println("Error! Такого пользователя нет! ((((");
            } else {
                pw.println(gson.toJson(model.getFromList().get(id)));
            }
        } else {
            pw.println("Error! ID должен быть больше нуля!");
        }
    }
}
