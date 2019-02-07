package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);

        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();

            return;

        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);

        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(contacts);

        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);

        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }

    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {

        try (Writer writer = new FileWriter(file)) {

            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstname(),
                        contact.getMiddlename(),
                        contact.getLastname(),
                        contact.getNickname(),
                        contact.getTitle(),
                        contact.getCompany(),
                        contact.getAddress(),
                        contact.getHomephone(),
                        contact.getMobile(),
                        contact.getWorkphone(),
                        contact.getBday(),
                        contact.getBmonth(),
                        contact.getByear(),
                        contact.getEmail(),
                        contact.getEmail1(),
                        contact.getEmail2()));
            }
        }
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        File photo = new File("src/test/resources/Screenshot_1.png");

        for (int i = 0; i < count; i++) {
            contacts.add(
                new ContactData()
                    .withFirstname(String.format("first name %s", i))
                    .withMiddlename(String.format("middle name %s", i))
                    .withLastname(String.format("last name %s", i))
                    .withNickname(String.format("nick name %s", i))
                    .withTitle(String.format("title %s", i))
                    .withCompany(String.format("company %s", i))
                    .withAddress(String.format("address %s", i))
                    .withHomephone(String.format("home phone %s", i))
                    .withMobile(String.format("mobile %s", i))
                    .withWorkphone(String.format("workphone %s", i))
                    .withBday(String.format("b-day %s", i))
                    .withBmonth(String.format("b-month %s", i))
                    .withByear(String.format("b-year %s", i))
                    .withEmail(String.format("email %s", i))
                    .withEmail1(String.format("email1 %s", i))
                    .withEmail2(String.format("email2 %s", i))
                    .withPhoto(photo)
            );
        }

        return contacts;
    }
}
