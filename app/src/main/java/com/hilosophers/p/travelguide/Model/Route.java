package com.hilosophers.p.travelguide.Model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<List<Sight>> routes = new ArrayList<>();

    public Route(List<List<Sight>> routes) {
        this.routes = routes;
    }

    public List<List<Sight>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<List<Sight>> routes) {
        this.routes = routes;
    }
}
