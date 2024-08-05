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
            writer.write("<p>error': " + e.getMessage() + "</p>");
        }
    }
}
