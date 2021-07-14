package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String company = Jsoup.clean(request.getParameter("company"), Whitelist.none());
    String email = Jsoup.clean(request.getParameter("email"), Whitelist.none());
    String message = Jsoup.clean(request.getParameter("text-input"), Whitelist.none());
    
    //Get time submitted
    long timestamp = System.currentTimeMillis();
    
    // Store contact info in datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("ContactInfo");
    FullEntity contactEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("company", company)
            .set("email", email)
            .set("message", message)
            .set("timestamp", timestamp)
            .build();
    datastore.put(contactEntity);

    // Print the value so you can see it in the server logs.
    System.out.println("Name: " + name + "\nCompany: " + company + "\nEmail: " + email + "\nMessage: " + message);

    // Redirect user back to homepage after submit
    response.sendRedirect("/index.html");
  }
}