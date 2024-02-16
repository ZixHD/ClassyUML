package raf.dsw.classycraft.app.messages;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class Message {

    private String text;
    private String type;
    private Timestamp timestamp;
    private SimpleDateFormat date;

    public Message(String text, String type, Timestamp timestamp) {
        this.text = text;
        this.type = type;
        this.timestamp = timestamp;
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String toString() {
        return "[" + type + "][" + timestamp + "] " + text;
    }

}
