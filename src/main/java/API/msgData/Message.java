package API.msgData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String msg;
    private String fromEmail;
    private String toEmail;

    public long getID() { return id; }

    public void setID(long id) { this.id = id; }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public String getFromEmail() { return fromEmail; }

    public void setFromEmail(String fromEmail) { this.fromEmail = fromEmail; }

    public String getTomEmail() { return toEmail; }

    public void setToEmail(String toEmail) { this.toEmail = toEmail; }

}
