package nz.co.vincens.service;

import nz.co.vincens.model.*;

public class TaskTest {
    public static void main(String[] args) {
        Manager actualManager;
        actualManager = UserHelper.getManagerById(1);
        System.out.println(actualManager.getClass());


//        User actualUser;
//        actualUser = UserHelper.getUserDetails(1);
//        System.out.println(actualUser.getId());
//        System.out.println(actualUser.getName());
//        System.out.println(actualUser.getUsername());
//        System.out.println(actualUser.getEmail());
//        System.out.println(actualUser.getPassword());
//        System.out.println(actualUser.getRole());

    }
}
