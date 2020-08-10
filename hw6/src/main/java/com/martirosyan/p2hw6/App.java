package com.martirosyan.p2hw6;

import com.martirosyan.p2hw6.service.ProductService;
import com.martirosyan.p2hw6.service.UserService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;


public class App {
    private static UserService userService;
    private static ProductService productService;

//    public App(UserService userService, ProductService productService) {
//        this.userService = userService;
//        this.productService = productService;
//    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8189);

        ProtectionDomain domain = App.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app");
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);
        server.start();
        server.join();

    }
}
