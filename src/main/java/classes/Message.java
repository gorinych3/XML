package classes;


public class Message {

    private long mesId;
    private String status;

    public Message() {
    }


    public Message(long mesId, String status) {
        super();
        this.mesId = mesId;
        this.status = status;
    }

    public long getMesId() {
        return mesId;
    }

    public void setMesId(long mesId) {
        this.mesId = mesId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
