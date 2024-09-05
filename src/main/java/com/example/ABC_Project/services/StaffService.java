package com.example.ABC_Project.services;

import com.example.ABC_Project.LocationDataRepo;
import com.example.ABC_Project.OrderRepo;
import com.example.ABC_Project.ReserveRepo;
import com.example.ABC_Project.models.LocationDataModel;
import com.example.ABC_Project.models.OrdersModel;
import com.example.ABC_Project.models.ReserveModel;
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
public class StaffService {
    @Autowired
    LocationDataRepo LocationR;
    @Autowired
    OrderRepo ordRepo;
    @Autowired
    ReserveRepo reserveR;


    public List<String> StaffLogin(LocationDataModel locM) {
        List<LocationDataModel> allData = LocationR.findAll();
        List<String> list = new ArrayList<>();

        for (LocationDataModel allDatum : allData) {
            if (locM.getLocation().equalsIgnoreCase(allDatum.getLocation()) && locM.getPassword().equalsIgnoreCase(allDatum.getPassword())) {
                list.add(allDatum.getLocation());
                list.add(String.valueOf(allDatum.get_id()));
            }
        }
        return list;
    }

    public List<OrdersModel> orders(List<String> locM) {
        List<OrdersModel> allData = ordRepo.findAll();
        List<OrdersModel> filteredData = new ArrayList<>();
        if (locM != null && !locM.isEmpty()) {
            String location = locM.getFirst();
            for (OrdersModel allDatum : allData) {
                if (location.equalsIgnoreCase(allDatum.getLocation())) {
                    filteredData.add(allDatum);
                }
            }
        }

        return filteredData;
    }

    LocalDate date = LocalDate.now();
    int month = date.getMonthValue();
    int day = date.getDayOfMonth();
    int year = date.getYear();

    public List<ReserveModel> getReserves(LocationDataModel locM) {
        List<ReserveModel> allReserves = reserveR.findAll();
        List<ReserveModel> filteredData = new ArrayList<>();
        String location = locM.getLocation();
        for (ReserveModel reserveM : allReserves) {
            String[] splitDates = reserveM.getDate().split("-");
            ArrayList<Integer> convertedData = new ArrayList<>();
            for (String data : splitDates) {
                convertedData.add(Integer.valueOf(data));
            }
            if (!(convertedData.get(0) < year) && !(convertedData.get(1) < month) && !(convertedData.get(2) < day)) {
                if (location.equalsIgnoreCase(reserveM.getLocation())) {
                    filteredData.add(reserveM);
                }

            }
        }
        return filteredData;
    }


    @Autowired
    private MongoTemplate temp;

    public void update(OrdersModel om) {
        String id = om.get_id();
        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(id));
        q.fields().include("_id");

        Update up = new Update();
        up.set("completed", true);

        boolean m = temp.updateFirst(q, up, OrdersModel.class).wasAcknowledged();
        System.out.println(m);
    }
}
