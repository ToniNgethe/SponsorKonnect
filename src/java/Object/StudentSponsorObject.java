/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Model.StudentPersonalModel;
import Model.StudentSponsorModel;
import java.util.List;

/**
 *
 * @author toni
 */
public class StudentSponsorObject {
     int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;

    List<StudentSponsorModel> aaData;

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public List<StudentSponsorModel> getAaData() {
        return aaData;
    }

    public void setAaData(List<StudentSponsorModel> aaData) {
        this.aaData = aaData;
    }
}
