package com.example.ABC_Project.services;

import com.example.ABC_Project.LocationDataRepo;
import com.example.ABC_Project.OrderRepo;
import com.example.ABC_Project.ProductsRepo;
import com.example.ABC_Project.ReserveRepo;
import com.example.ABC_Project.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminService {
    @Autowired
    LocationDataRepo locationR;

    @Autowired
    OrderRepo ordersR;

    @Autowired
    ReserveRepo reserveR;

    @Autowired
    ProductsRepo productR;


    LocalDate date = LocalDate.now();
    int month = date.getMonthValue();
    int day = date.getDayOfMonth();
    int year = date.getYear();


    public Boolean adminLogon(LoginModel logM) {
        String password = "Admin2003";
        return password.equals(logM.getPassword());
    }

    public List<LocationDataModel> allLocationData() {
        return locationR.findAll();
    }

    public List<OrdersModel> allOrderData() {
        return ordersR.findAll();
    }

    public List<ReserveModel> allReservations() {
        return reserveR.findAll();
    }

    public List<ProductsModel> getAllProd() {
        return productR.findAll();
    }


    public boolean updateProd(List<String> items) {
        String id = items.getFirst();
        String updateProp = items.get(1);
        String newValue = items.getLast();

        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        q.fields().include("_id");

        Update up = new Update();
        if ("price".equalsIgnoreCase(updateProp)) {
            int intValue = Integer.parseInt(newValue);
            up.set(updateProp, intValue);
        } else {
            up.set(updateProp, newValue);
        }

        boolean m = template.updateFirst(q, up, ProductsModel.class).wasAcknowledged();
        return m;
    }

    public List<ReserveModel> newReservations() {
        List<ReserveModel> allReservations = reserveR.findAll();
        List<ReserveModel> filteredReservations = new ArrayList<>();
        for (ReserveModel resM : allReservations) {
            String[] DateSplit = resM.getDate().split("-");
            ArrayList<Integer> convertedDates = new ArrayList<>();
            for (String dates : DateSplit) {
                convertedDates.add(Integer.valueOf(dates));
            }
            if (!(convertedDates.get(0) < year) && !(convertedDates.get(1) < month) && !(convertedDates.get(2) < day)) {
                filteredReservations.add(resM);
            }
        }
        return filteredReservations;
    }

    public List<ReserveModel> oldReservations() {
        List<ReserveModel> allReservations = reserveR.findAll();
        List<ReserveModel> filteredReservations = new ArrayList<>();
        for (ReserveModel resM : allReservations) {
            String[] DateSplit = resM.getDate().split("-");
            ArrayList<Integer> convertedDates = new ArrayList<>();
            for (String dates : DateSplit) {
                convertedDates.add(Integer.valueOf(dates));
            }
            if (!(convertedDates.get(0) > year) && !(convertedDates.get(1) > month) && !(convertedDates.get(2) > day)) {
                filteredReservations.add(resM);
            }
        }
        return filteredReservations;
    }

    public boolean addLocation(LocationDataModel locM) {
        List<LocationDataModel> allLocations = locationR.findAll();
        String location = locM.getLocation();


        boolean locationExists = allLocations.stream()
                .anyMatch(existingLoc -> existingLoc.getLocation().equalsIgnoreCase(location));

        if (!locationExists) {
            LocationDataModel savedLoc = locationR.save(locM);
            return locationR.findById(String.valueOf(savedLoc.get_id())).isPresent();
        }


        return false;
    }

    public boolean deleteLocation(LocationDataModel locM) {
        locationR.deleteById(String.valueOf(locM.get_id()));
        return false;
    }

    @Autowired
    MongoTemplate template;

    public boolean updateLocationName(LocationDataModel locM) {
        String id = locM.get_id();
        String location = locM.getLocation();

        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        q.fields().include("_id");

        Update up = new Update();
        up.set("location", location);

        boolean m = template.updateFirst(q, up, LocationDataModel.class).wasAcknowledged();
        return m;
    }

    public boolean updateSeatCount(LocationDataModel locM) {
        String id = locM.get_id();
        int seatCount = locM.getSeatCount();

        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        q.fields().include("_id");

        Update up = new Update();
        up.set("seatCount", seatCount);

        boolean m = template.updateFirst(q, up, LocationDataModel.class).wasAcknowledged();
        return m;
    }

    public boolean updatePass(LocationDataModel locM) {
        String id = locM.get_id();
        String password = locM.getPassword();

        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        q.fields().include("_id");

        Update up = new Update();
        up.set("password", password);

        boolean m = template.updateFirst(q, up, LocationDataModel.class).wasAcknowledged();
        return m;
    }

    public boolean addNewProduct(ProductsModel podM) {
        ProductsModel prod = productR.save(podM);
        return prod.get_id() != null;
    }

    public boolean deleteProduct(ProductsModel prodM) {
        try {
            productR.deleteById(prodM.get_id());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
