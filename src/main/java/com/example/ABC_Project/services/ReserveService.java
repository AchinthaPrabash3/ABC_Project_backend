package com.example.ABC_Project.services;

import com.example.ABC_Project.LocationDataRepo;
import com.example.ABC_Project.ReserveRepo;
import com.example.ABC_Project.SignupRepo;
import com.example.ABC_Project.models.LocationDataModel;
import com.example.ABC_Project.models.ReserveModel;
import com.example.ABC_Project.models.UserData;
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

    @Autowired
    private SignupRepo signupRepo;


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
                ReserveModel res = repo.save(resM);
                seats = 0;
                message.add("you have booked " + resM.getPeopleCount() + " seats");
                message.add(res.get_id());
                return message;
            } else if (remaining <= 0) {
                message.add("This date is fully booked");
                message.add("false");
                seats = 0;
                return message;
            } else {
                message.add("we only " + remaining + " seats are remaining");
                message.add("false");
                seats = 0;
                return message;
            }
        } else {
            message.add("please select correct data");
        }
        return message;
    }

    public void cancelReserve(ReserveModel om) {
        String id = om.get_id();
        System.out.println(id);
        if (id != null) {
            try {
                repo.deleteById(id);
                boolean m = repo.findById(id).isPresent();
                System.out.println(m);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<ReserveModel> getUserRes(UserData data) {
        String id = data.get_id();

        List<ReserveModel> reserves = new ArrayList<>();
        if (id != null) {
            List<String> reserveArray = signupRepo.findById(id).get().getReservations();
            List<ReserveModel> allReserves = repo.findAll();
            for (String ReserveID : reserveArray) {
                for (ReserveModel res : allReserves) {
                    if (ReserveID.equals(res.get_id())) {
                        String[] spliteDates = res.getDate().split("-");
                        List<Integer> convertedData = new ArrayList<>();
                        for (String dates : spliteDates) {
                            convertedData.add(Integer.parseInt(dates));
                        }
                        if (!(convertedData.get(0) < year) && !(convertedData.get(1) < month) && !(convertedData.get(2) < day)) {
                            reserves.add(res);
                        }
                    }
                }
            }

        }
        return reserves;
    }
}
