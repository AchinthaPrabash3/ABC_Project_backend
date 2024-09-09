package com.example.ABC_Project.services;

import com.example.ABC_Project.LoginRepo;
import com.example.ABC_Project.OrderRepo;
import com.example.ABC_Project.SignupRepo;
import com.example.ABC_Project.models.LoginModel;
import com.example.ABC_Project.models.OrdersModel;
import com.example.ABC_Project.models.SignupModel;
import com.example.ABC_Project.models.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class LoginService {
    @Autowired
    private LoginRepo repo;
    @Autowired
    private SignupRepo repo1;
    @Autowired
    OrderRepo orderRepo;

    public List<UserData> verify(LoginModel sModel) {
        List<SignupModel> allData = repo1.findAll();
        List<UserData> UserData = repo.findAll();
        String email = sModel.getEmail();
        String password = sModel.getPassword();
        List<UserData> verifiedData = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            if (email.equals(allData.get(i).getEmail()) & password.equals(allData.get(i).getPassword())) {
                verifiedData.add(UserData.get(i));
            }
        }
        return verifiedData;
    }

    @Autowired
    MongoTemplate template;

    public boolean updateUserDetails(List<String> data) {
        Optional<SignupModel> user = repo1.findById(data.getFirst());
        if (user.isPresent()) {
            List<String> userOrders = user.get().getOrders();
            String newProdId = data.getLast();
            if (!newProdId.isBlank()) {
                userOrders.add(newProdId);
                Query q = new Query();
                q.addCriteria(Criteria.where("_id").is(user.get().get_id()));
                q.fields().include("_id");

                Update up = new Update();
                up.set("orders", userOrders);
                boolean m = template.updateFirst(q, up, SignupModel.class).wasAcknowledged();
                return m;
            } else {
                return false;
            }

        }
        return false;
    }

    public boolean updateUserReservations(List<String> data) {
        Optional<SignupModel> user = repo1.findById(data.getFirst());
        if (user.isPresent()) {
            List<String> userRes = user.get().getReservations();
            String newProdId = data.getLast();
            if (!newProdId.isBlank()) {
                userRes.add(newProdId);
                Query q = new Query();
                q.addCriteria(Criteria.where("_id").is(user.get().get_id()));
                q.fields().include("_id");

                Update up = new Update();
                up.set("reservations", userRes);
                boolean m = template.updateFirst(q, up, SignupModel.class).wasAcknowledged();
                return m;
            } else {
                return false;
            }

        }
        return false;
    }


}
