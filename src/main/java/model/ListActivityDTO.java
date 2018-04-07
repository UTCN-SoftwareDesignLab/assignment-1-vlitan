package model;

import java.sql.Date;

public class ListActivityDTO {
    private int UserId;
    private Date startDate;
    private Date endDate;

    public ListActivityDTO(int userId, Date startDate, Date endDate) {

        UserId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



}
