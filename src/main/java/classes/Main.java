package classes;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<root>" +
            "    <row>" +
            "        <mesId>1</mesId>" +
            "        <status>W</status>" +
            "    </row>" +
            "    <row>" +
            "        <mesId>2</mesId>" +
            "        <status>D</status>" +
            "    </row>" +
            "    <row>" +
            "        <mesId>3</mesId>" +
            "        <status>E</status>" +
            "    </row>" +
            "    <row>" +
            "        <mesId>4</mesId>" +
            "        <status>D</status>" +
            "    </row>" +
            "    <row>" +
            "        <mesId>5</mesId>" +
            "        <status>D</status>" +
            "    </row>" +
            "</root>";
    List<Message> messages = new ArrayList<Message>();

    public static void main(String[] args) throws JAXBException, IOException {
        Main main = new Main();
        //main.initList();
        //main.jaxbObjectToXML();
        //main.marshal();
        //main.objsToXML();
        System.out.println(main.validaXML());
    }

    private void initList(){
        messages.add(new Message(1, "W"));
        messages.add(new Message(2, "D"));
        messages.add(new Message(3, "E"));
        messages.add(new Message(4, "D"));
        messages.add(new Message(5, "D"));
    }


    public void marshal() throws JAXBException, IOException {

        Message mess = messages.get(0);

            JAXBContext context = JAXBContext.newInstance(Message.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(mess, new File("F:/Test/message.xml"));

    }

    private void handleXMLString(){
        StringBuilder str = new StringBuilder();
        str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        str.append("<root>");
        for(Message mes : messages){
            str.append("<row>");
            str.append("<MesID>").append(mes.getMesId()).append("</MesID>");
            str.append("<Status>").append(mes.getStatus()).append("</Status>");
            str.append("</row>");
        }
        str.append("</root>");
    }

    /*
    private void jaxbObjectToXML(){
        Message mess = messages.get(0);
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            for(Message mes : messages) {
                jaxbMarshaller.marshal(mes.getRows(), sw);
            }

            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println( xmlContent );

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    */

    private void objsToXML(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            Root root = new Root(messages);
            jaxbMarshaller.marshal(root, sw);

            //Verify XML Content
            xml = sw.toString();
            System.out.println(xml);

        }catch (JAXBException e){
            e.printStackTrace();
        }
    }

    public String validaXML(){

        try {
            URL schemaFile = this.getClass().getClassLoader().getResource("messageIn.xsd");
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();

            Source xmlFile = new StreamSource(new StringReader(xml));

            validator.validate(xmlFile);

        } catch (SAXException ex) {
            System.out.println( ex.getMessage());
            return "Ошибка валидации";

        } catch (IOException e) {
            System.out.println( e.getMessage());
            return "Ошибка валидации";
        }
        return "Valid";
    }

}
