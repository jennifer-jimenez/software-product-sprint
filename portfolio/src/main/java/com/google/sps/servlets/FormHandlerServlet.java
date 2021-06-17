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

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String name = request.getParameter("name");
    String company = request.getParameter("company");
    String email = request.getParameter("email");
    String message = request.getParameter("text-input");
    
    // Store contact info in datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("company", company)
            .set("email", email)
            .set("message", message)
            .build();
    datastore.put(taskEntity);

     // Print the value so you can see it in the server logs.
    System.out.println("Name: " + name + "\nCompany: " + company + "\nEmail: " + email + "\nMessage: " + message);

    // Redirect user back to homepage after submit
    response.sendRedirect("/index.html");
  }
}