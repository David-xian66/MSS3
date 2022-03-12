package top.mss3.mssroot.controller;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorPageController implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage error405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405.html");
        registry.addErrorPages(error404, error405);
    }
}
