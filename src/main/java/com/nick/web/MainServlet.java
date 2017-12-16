package com.nick.web;

import com.nick.Factory;
import com.nick.controller.Controller;
import com.nick.controller.CreateUserController;
import com.nick.controller.LoginUserController;
import com.nick.controller.ProfileController;
import com.nick.dao.UserDaoImpl;
import com.nick.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private static final Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put(new Request("/servlet/login", "POST"),
                Factory.getSomething(LoginUserController::new)
                        .compose(UserServiceImpl::new)
                        .compose(UserDaoImpl::new)
                        .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/login", "GET"),
                Factory.getSomething(LoginUserController::new)
                        .compose(UserServiceImpl::new)
                        .compose(UserDaoImpl::new)
                        .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/signup", "POST"),
                Factory.getSomething(CreateUserController::new)
                        .compose(UserServiceImpl::new)
                        .compose(UserDaoImpl::new)
                        .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/signup", "GET"),
                Factory.getSomething(CreateUserController::new)
                        .compose(UserServiceImpl::new)
                        .compose(UserDaoImpl::new)
                        .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/profile", "GET"), new ProfileController());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    /*    if (req.getRequestURI().equals("/servlet/home")) {
           req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
       } else {
           req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
       }*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request request = new Request(req.getRequestURI(), req.getMethod());
        Controller controller = controllerMap.get(request);
        if (controller == null) {
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
        ViewModel vm = controller.process(req, resp);
        forward(req, resp, vm);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        processAttributes(req, vm);
        req.getRequestDispatcher(vm.getView()).forward(req, resp);
    }

    private void processAttributes(HttpServletRequest req, ViewModel vm) {
        if (vm.getArgumentsMap().isEmpty()) {
            return;
        }
        /*vm.getArgumentsMap().entrySet().stream()*/
    }
}
