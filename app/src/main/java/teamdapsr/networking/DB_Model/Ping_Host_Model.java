package teamdapsr.networking.DB_Model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import java.io.Serializable;

/**
 * Created by rajanmaurya on 20/9/15.
 */

    @Table(name = "Ping_Host")
    public class Ping_Host_Model extends Model implements Serializable {

        @Column(name = "host")
        public String host ;

        @Column(name = "host_date")
        public String host_date ;

        @Column(name = "host_time")
        public String host_time ;


        public Ping_Host_Model()
        {

            super();
        }


    /**
     * Initializing all
     * @param date
     * @param time
     * @param host
     */
        public Ping_Host_Model(String date, String time, String host)
        {
            super();
            this.host_date = date;
            this.host_time = time;
            this.host = host;


        }


    @Override
    public String toString() {
        return " Host Name: "
                + host
                + " Host_date: "
                + host_date
                + " Host_time: "
                + host_time;

    }

    /**
     *
     * @return Host Creation Time
     */
        public String getHost_date()
        {
            return host_date;
        }

        public void setHost_date(String host_add_date)
        {
            this.host_date = host_add_date;
        }


    /**
     *
     * @return host or IP address
     */
        public String getHost()
        {
            return host;
        }

        public void setHost(String host)
        {
            this.host = host;
        }

        /**
         *
         * @return host creation time
         */
        public String getHost_time() {
            return host_time;
        }

        public void setHost_time(String host_time) {
            this.host_time = host_time;
        }
    }
