package nz.co.vincens.service;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class TaskTest {
    public static int lastAssigneesListId;

    public static void main(String[] args) {
//        String deadline1 = "Sat Oct 07 21:15:00 NZDT 2017";
//        String deadline2 = "2017-10-02 11:24 AM UTC";
////
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
//        try {
//            Date deadline = sdf.parse(deadline2);
//            System.out.println(deadline);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        String date1 = String.valueOf(new Date());
////        String date2 = "2017-10-02 11:24 AM UTC";
////        System.out.println(date1);
        // lastAssigneesListId = getLastAssigneesListId();
        createNewAssigneesList(1, 2);
    }

    /**
     * @return
     */
    public static int getLastAssigneesListId() {
        int listId = 0;
        // Generate database query sentence
        String sql = "CALL Task_getLastAssigneesListId()";
        // Call the function of database query operation
        ResultSet rs = DatabaseHelper.databaseExecution(sql);
        try {
            // Extract data from result set
            while (rs.next()) {
                // Login query should only have one row of result
                listId = rs.getInt("MAX(listId)");
            }
        } catch (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
        } finally {
            try {
                // Clean-up environment
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listId;
    }

    /**
     * @param listId
     * @param teamMemberId
     */
    public static void createNewAssigneesList(int listId, int teamMemberId) {
        // Generate database query sentence
        String sql = "CALL Task_createNewAssigneesList(" + (listId + 1) + "," + teamMemberId + ")";

        System.out.println(sql);  // Call the function of database query operation
//        ResultSet rs = DatabaseHelper.databaseExecution(sql);
//        try {
//            // Clean-up environment
//            if (!rs.isClosed()) {
//                rs.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
