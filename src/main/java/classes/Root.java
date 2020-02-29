package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "root")
public class Root {

    private List<Message> messages;

    public Root() {
    }

    public Root(List<Message> messages) {
        this.messages = messages;
    }

    @XmlElement(name = "row")
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
