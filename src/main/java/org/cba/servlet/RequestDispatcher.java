package org.cba.servlet;

import hyggemvc.router.AppContainer;
import hyggemvc.router.Route;
import hyggemvc.router.RouteCallable;
import hyggemvc.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by adam on 02/04/2017.
 */
public class RequestDispatcher extends hyggemvc.servlet.RequestDispatcher {
    @Override
    protected void routeToController(HttpServletRequest request, HttpServletResponse response, String url) {
        Router router = new Router(
                new Route("api/(?<controller>[a-z\\-]+)(?<method>/[a-z\\-]+)?(?<int0>/\\d+)?","Default","index", "api"),
                new Route("(?<method>[a-z\\-]+)?(?<int0>/\\d+)?","Default","index"),
                new Route("(?<controller>[a-z\\-]+)(?<method>/[a-z\\-]+)?(?<int0>/\\d+)?","Default","index")
        );

        RouteCallable routeCallable = router.getRouteCallable("org.cba.controller",url);
        AppContainer app = new AppContainer(request,response);
        app.run(routeCallable);
    }
}
