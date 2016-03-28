import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.apache.commons.lang3.StringEscapeUtils.*;

/**
 * This class sends an e-mail to the BFH e-mail server
 * (hermes.bfh.ch).
 * <p>
 * Author: jean-paul.dubois@bfh.ch
 */
public class BFH_EmailSender {

    //
  private static final String SERVER = "147.87.244.55";
    //private static final String SERVER = "imap-mail.outlook.com";
    //protected static Folder inbox;
    protected static Store store;

  /**
   * Sends an e-mail.
   *
   * @param user      the user name of the e-mail sender (e.g. doj1)
   * @param passwd    its password
   * @param recipient the address of the e-mail recipient (e.g. doj1@bfh.ch)
   * @param subject   the e-mail subject
   * @param filename   the e-mail text
   * @throws AddressException   when a wrongly formatted address is encountered
   * @throws MessagingException when the message could not be sent
   */
  public static void send(String user, char[] passwd, String recipient,
                          String subject, List<String> filename)
          throws AddressException, MessagingException {

      System.out.println("filename: " + filename);

    // Get system properties
      Properties properties = System.getProperties();

      // Account authenticator
      Authenticator authenticator = new Authenticator(user, passwd);

      // Setup mail server
      properties.setProperty("mail.smtp.host", SERVER);
      properties.setProperty("mail.smtp.auth", "true");
      properties.setProperty("mail.smtp.submitter",
            authenticator.getPasswordAuthentication().getUserName());

      // Get the default Session object.
      Session session = Session.getInstance(properties, authenticator);

      // Create a default MimeMessage object.
      Message message = new MimeMessage(session);

      // Set the RFC 822 "From" header field using the
      // value of the InternetAddress.getLocalAddress method.
      message.setFrom(new InternetAddress(UserVariables.emailbfh));

      // Set the "Subject" header field.
      message.setSubject(subject);

      // Set the "To" header field.
      message.setRecipient(javax.mail.Message.RecipientType.TO,
            new InternetAddress(recipient));

      if(oop2.ccAdress.getText().isEmpty() == false) {
        message = setXtraRecipients("CC", message);
      }

      if(oop2.bccAdress.getText().isEmpty() == false) {
         message = setXtraRecipients("BCC", message);
      }


       // byte[] bytechar = oop2.editor.getHtmlText().getBytes(Charset.forName("utf-8"));
      //  System.out.println(new String(bytechar, Charset.forName("utf-8")));
      //  String escutf = new String(bytechar, Charset.forName("utf-8"));
      String escjava = escapeJava(oop2.editor.getHtmlText());
      String eschtml = escapeHtml4(new String(escjava));
     // String esc3 = escjava.replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll(">", "&amp;");
      String linesc = escjava.replaceAll("\\n", "<br>");
    // Set the given String as this part's content,
    // with a MIME type of "text/plain".
      MimeMultipart mp = new MimeMultipart();
      MimeBodyPart mbp1= new MimeBodyPart();
      String font = "Calibri";
      String fontsize = "3";
      String fontcolor = "#000";
      String htmlText = /*"<pre>" +*/ unescapeJava(linesc);
      mbp1.setContent(htmlText, "text/html; charset=UTF-8");
      mp.addBodyPart(mbp1);

      if(filename.isEmpty() == false) {
          for(String singlefile : filename) {
              MimeBodyPart mbp2 = new MimeBodyPart();
              DataSource source = new FileDataSource(singlefile);
              mbp2.setDataHandler(new DataHandler(source));
              int filenr = singlefile.lastIndexOf("\\");
              mbp2.setFileName(singlefile.substring(filenr));
              mp.addBodyPart(mbp2);
          }
      }

      message.setContent(mp, "text/html; charset=UTF-8");

    // Send message
      Transport.send(message);

  }


  public static void receive(String username, char[] passwd) throws MessagingException, IOException {

      Properties properties = System.getProperties();

      // Account authenticator
      Authenticator authenticator = new Authenticator(username, passwd);
      java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
      // Setup mail server
      properties.setProperty("mail.store.protocol", "imaps");
      properties.setProperty("mail.imap.host", SERVER);
      //properties.setProperty("mail.imap.ssl.enable", "true");

/*
      properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      // don't fallback to normal IMAP connections on failure.
      properties.setProperty("mail.imap.socketFactory.fallback", "false");
      // use the simap port for imap/ssl connections.
   */   properties.setProperty("mail.imap.socketFactory.port", "993");

      properties.setProperty("mail.imap.auth", "true");
      properties.setProperty("mail.imap.user", authenticator.getPasswordAuthentication().getUserName());

      // Get the default Session object.
      Session session = Session.getInstance(properties, authenticator);

      store = session.getStore("imaps");
      store.connect(SERVER, UserVariables.emailbfh, passwd.toString());

  }

    public static Message getEmail(int i, Folder inbox) throws MessagingException, IOException {

        Message msg = inbox.getMessage(i);
   //   Address[] in = msg.getFrom();

     // String mp =  msg.getContent().toString();


    //  oop2.mailAdress.setText(in.toString());
    //  oop2.mailSubject.setText(msg.getSubject());
     // oop2.editor.setHtmlText(mp);

        //System.out.println(foldername);
return msg;
  }

    public static int getEmailCount(String foldername) throws MessagingException {
        Folder inbox = store.getFolder(foldername);
        System.out.println(foldername);

        int i = inbox.getMessageCount();
        if(i > 99) {i = 99;}
        return i;
    }



    public static int getUnreadNr() throws MessagingException {
        Folder inbox = store.getFolder("INBOX");


        int i = inbox.getUnreadMessageCount();
        return i;
    }


    public static List<String> getFolderList() throws MessagingException {

        Folder[] f = store.getDefaultFolder().list();
        List<String> folderlist = new ArrayList<>();
        for(Folder fd : f) {
            if ((fd.getType() & javax.mail.Folder.HOLDS_MESSAGES) != 0) {
                folderlist.add(fd.getFullName());
            System.out.println(fd.getFullName());}
        }

        return folderlist;
    }

    public static Message setXtraRecipients(String ccorbcc, Message message) throws MessagingException {
        if(ccorbcc.matches("CC") == true) {
            message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(
                    oop2.ccAdress.getText()));
            return message;
        }
        if(ccorbcc.matches("BCC") == true) {
            message.addRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(
                    oop2.bccAdress.getText()));
            return message;
        }
        return message;
    }




  private static class Authenticator extends javax.mail.Authenticator {

    private PasswordAuthentication authentication;

    public Authenticator(String user, char[] passwd) {
      authentication = new PasswordAuthentication(user, new String(passwd));
    }

    protected PasswordAuthentication getPasswordAuthentication() {
      return authentication;
    }
  }
} 
