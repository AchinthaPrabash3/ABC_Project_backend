package com.example.ABC_Project.services;

import com.example.ABC_Project.LocationDataRepo;
import com.example.ABC_Project.ReserveRepo;
import com.example.ABC_Project.models.LocationDataModel;
import com.example.ABC_Project.models.ReserveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReserveService {
    @Autowired
    ReserveRepo repo;

    @Autowired
    LocationDataRepo Lrepo;

    LocalDate date = LocalDate.now();
    int month = date.getMonthValue();
    int day = date.getDayOfMonth();
    int year = date.getYear();
    int seats = 0;

    public Integer manageSeats(ReserveModel rmod, int reserveDate) {

        List<ReserveModel> allData = repo.findAll();
        List<ReserveModel> filteredDataByLocation = new ArrayList<>();
        for (ReserveModel allDatum : allData) {
            if (rmod.getLocation().equals(allDatum.getLocation())) {
                filteredDataByLocation.add(allDatum);
            }
        }
        for (ReserveModel reserveModel : filteredDataByLocation) {
            String date = reserveModel.getDate();

            String[] splitDate = date.split("-");
            ArrayList<Integer> convertToInt = new ArrayList<>();
            for (String s : splitDate) {
                convertToInt.add(Integer.parseInt(s));
            }
            if (reserveDate == convertToInt.getLast()) {
                seats += reserveModel.getPeopleCount();
            }
        }
        List<LocationDataModel> allLocationData = Lrepo.findAll();
        int remaining = 0;
        for (LocationDataModel allLocationDatum : allLocationData) {

            if (rmod.getLocation().equalsIgnoreCase(allLocationDatum.getLocation())) {
                remaining = allLocationDatum.getSeatCount() - seats;
            }
        }
        return remaining;
    }


    public ArrayList<String> Reserve(ReserveModel resM) {
        String reserveDate = resM.getDate();
        ArrayList<String> message = new ArrayList<>();
        String[] reserveDateSplit = reserveDate.split("-");
        ArrayList<Integer> reserveDataInt = new ArrayList<>();
        for (String s : reserveDateSplit) {
            reserveDataInt.add(Integer.valueOf(s));
        }
        if (!(reserveDataInt.get(0) < year) && !(reserveDataInt.get(1) < month) && !(reserveDataInt.get(2) < day)) {
            int remaining = manageSeats(resM, reserveDataInt.getLast());
            if (!(resM.getPeopleCount() > remaining)) {
                repo.save(resM);
                seats = 0;
                message.add("you have booked " + resM.getPeopleCount() + "seats");
                return message;
            }else if (remaining <= 0 ){
                message.add("This date is fully booked");
                seats = 0;
                return message;
            }else{
                message.add("we only " + remaining + " seats are remaining");
                seats = 0;
                return message;
            }
        } else {
            message.add("please select correct data");
        }
        return message;
    }
}
