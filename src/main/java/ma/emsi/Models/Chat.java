package ma.emsi.Models;

import java.util.Date;

public class Chat {
    private int id;
    private User sender;
    private User receiver;
    private String message;
    private Date dateOfSending;

    public Chat(int id, User sender, User receiver, String message, Date dateOfSending) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.dateOfSending = dateOfSending;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDateOfSending() {
        return dateOfSending;
    }
    public void setDateOfSending(Date dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    
}
