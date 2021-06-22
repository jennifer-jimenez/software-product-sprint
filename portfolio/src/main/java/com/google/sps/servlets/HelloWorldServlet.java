package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.Random;

import scorex.util.ArrayList;

/** Handles requests sent to the /song URL. Try running a server and navigating to /hello! */
@WebServlet("/songs")
public class HelloWorldServlet extends HttpServlet {

  private ArrayList<String> songs = new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    songs.add("\"Romantic Lover\" - Eyedress");
    songs.add("\"88\" - Inner Wave");
    songs.add("\"Trying Your Luck\" - The Strokes");

    Random rand = new Random();
    int upperBound = songs.size() - 1;
    int randIndex = rand.nextInt(upperBound);

    String json = convertToJsonUsingGson(songs.get(randIndex));

    response.setContentType("application/json;");
    response.getWriter().println(json);

  }

  private String convertToJsonUsingGson(String song) {
    Gson gson = new Gson();
    String json = gson.toJson(song);
    return json;
  }  
}
