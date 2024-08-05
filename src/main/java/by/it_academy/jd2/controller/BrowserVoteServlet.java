package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.service.VoteService;
import by.it_academy.jd2.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/browser/vote")
public class BrowserVoteServlet extends HttpServlet {

    private final static IVoteService voteService = VoteService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                "   <title>Проголосуй</title>\n" +
                "   <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body>\n" +
                "\t<form action=\"" + req.getContextPath() + "/browser/vote\" method=\"POST\">\n" +
                "\t\t<p>Выбери артиста: </p>\n" +
                "\t\t<select name=\"artist\">\n" +
                "\t\t  <option>Пугачёва</option>\n" +
                "\t\t  <option>Леонтьев</option>\n" +
                "\t\t  <option>Tokyo Hotel</option>\n" +
                "\t\t</select>\n" +
                "\t\t</br>\n" +
                "\t\t<p>Выбери жанры: </p>\n" +
                "\t\t  <input type=\"checkbox\" name=\"genre\" value=\"Хип-хоп\"><span>Хип-хоп</span><br>\n" +
                "\t\t  <input type=\"checkbox\" name=\"genre\" value=\"Инструментал\"><span>Инструментал</span><br>\n" +
                "\t\t  <input type=\"checkbox\" name=\"genre\" value=\"РЕП\"><span>РЕП</span><br> \n" +
                "\t\t  </br>\n" +
                "\t\t<p>О себе:</p>\n" +
                "\t\t<textarea name=\"about\"></textarea>\n" +
                "\t\t</br>\n" +
                "\t\t<input type=\"submit\" value=\"Отправить\">\n" +
                "\t</form>\n" +
                " </body> \n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genre = req.getParameterValues("genre");
        String about = req.getParameter("about");

        PrintWriter writer = resp.getWriter();
        try{
            voteService.create(new VoteDTO(artist, genre, about));
        } catch (IllegalArgumentException e){
            writer.write("<p>error: " + e.getMessage() + "</p>");
        }
    }
}
