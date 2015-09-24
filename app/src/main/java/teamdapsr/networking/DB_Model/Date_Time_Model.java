package teamdapsr.networking.DB_Model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.Model;
import java.io.Serializable;

/**
 * Created by rajanmaurya on 24/9/15.
 */
@Table(name = "Date_Time")
public class Date_Time_Model extends Model implements Serializable {


    @Column(name = "host_date")
    public String host_date ;

    @Column(name = "host_time")
    public String host_time ;


    public Date_Time_Model()
    {
        super();
    }

    public Date_Time_Model(String date , String time)
    {
        this.host_date = date;
        this.host_time = time;
    }



    public String getHost_time() {
        return host_time;
    }

    public void setHost_time(String host_time) {
        this.host_time = host_time;
    }

    public String getHost_date() {
        return host_date;
    }

    public void setHost_date(String host_date) {
        this.host_date = host_date;
    }



}
