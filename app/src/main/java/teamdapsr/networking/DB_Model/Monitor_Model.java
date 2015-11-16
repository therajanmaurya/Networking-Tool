package teamdapsr.networking.DB_Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import java.io.Serializable;

/**
 * Created by rajan on 15/11/15.
 */
@Table(name = "Monitor")
public class Monitor_Model extends Model implements Serializable {

	@Column(name = "host")
	public String host;

	@Column(name = "status")
	public boolean status;

	@Column(name = "date_time" , onDelete = Column.ForeignKeyAction.CASCADE)
	public Date_Time_Model date_time_model;


	public Monitor_Model() {

		super();
	}


	/**
	 * Initializing all
	 *
	 *@param host
	 */
	public Monitor_Model(String host, boolean status ,Date_Time_Model date_time_model) {
		super();
		this.host = host;
		this.date_time_model = date_time_model;
		this.status = status;


	}


	@Override
	public String toString() {
		return " Host Name: "
				+ host
				+ "Status"
				+ status
				+ " Date Time "
				+ date_time_model;

	}


	/**
	 * @return host or IP address
	 */
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date_Time_Model getDate_time_model() {
		return date_time_model;
	}

	public void setDate_time_model(Date_Time_Model date_time_model) {
		this.date_time_model = date_time_model;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}