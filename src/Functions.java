import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.*;


/**
 * Created by [fbiliboh] on 06.03.2016.
 * Created by [::Tyler the human Compiler::  on 06.03.2016.
 */
public class Functions extends Thread {


    private static List<String> htmlcodesnips =  new ArrayList<>();


    public static boolean checkEmailAddress(String emailadress) {
        int eal = emailadress.length();
        String emailescape = emailadress;

        for(int i = new Integer("A".charAt(1)); i <= 130; i++) {

            String emailescape2 = new String();
            String letter = new String(new char[i]);
            emailescape2 = emailescape.replaceAll(letter, "");
            emailescape = new String(emailescape2);
        }

        if(emailadress.substring(eal - 1, eal).contains(".") == true ||
                emailadress.substring(eal - 2, eal).contains(".") == true ||
                emailadress.startsWith("@") == true ||
                emailadress.contains("@") == false ||
                emailadress.substring(emailadress.lastIndexOf("@")).contains(".") == false ||
                emailadress.substring(1, emailadress.lastIndexOf("@")).contains("@") == true ||
                emailescape.isEmpty() == false) {return false;}
        else {return true;}



    }




    public static void makeTextGreatAgain(int p1, int p2, String htmltag) {
        String otag = new String("<" + htmltag + ">");
        String ctag = new String("</" + htmltag + ">");


        String t1;
        String t2;

        String prestring = oop2.editor.getHtmlText().substring(0, p1);
        String boldstr = oop2.editor.getHtmlText().substring(p1, p2);
        String poststr = oop2.editor.getHtmlText().substring(p2);
        String replacestr = new String();

        if(prestring.lastIndexOf(otag) < prestring.lastIndexOf(ctag) == true) {t1 = otag;} else {t1 = ctag;}
        if(boldstr.contains(otag) == false || (boldstr.contains(otag) == true && boldstr.lastIndexOf(otag) < boldstr.lastIndexOf(ctag) == true) || boldstr.contains(ctag) == false) {t2 = ctag;} else {t2 = otag;}
        if(boldstr.contains(otag) == true || boldstr.contains(ctag) == true) {String rep1 = boldstr.replaceAll(otag, "<htmltag2>"); String rep2 = rep1.replaceAll(ctag, otag); replacestr = rep2.replaceAll("<htmltag2>", ctag);}
        if(replacestr.isEmpty() == false) {boldstr = replacestr;}

        String boldagain = new String(prestring + t1 + boldstr + t2 + poststr);

        oop2.editor.setHtmlText(boldagain);
    }


    public static void makeTextColorAgain(int p1, int p2, String hexcol, String seltext) {

        String otag = new String("<color=\"#" + hexcol + "\">");
        String ctag = new String("</color>");

        String t1;
        String t2;

        String htmlstr = oop2.editor.getHtmlText();
/*        int a = htmlstr.indexOf("<font ");
        int b = htmlstr.substring(htmlstr.indexOf("<font ")).indexOf(">");
        int c = htmlstr.indexOf("</font>");
        String htmltxt = htmlstr.substring(a + b + 1, c);
        */
        System.out.println(htmlstr);

        String prestring = htmlstr.substring(0, p1);
        String markstr = seltext;
        String poststr = htmlstr.substring(p2);
        System.out.println(markstr);
        String replacestr = new String();

        if ((prestring.lastIndexOf("<color=\"#") > prestring.lastIndexOf(ctag) == true) ||
                (prestring.contains("<color=\"#") == true && prestring.contains(ctag) == false)) {
            t1 = ctag + otag;} else {t1 = otag;}

        if ((poststr.indexOf(ctag) < prestring.indexOf("<color=\"#") == true) ||
                (prestring.contains(ctag) == true && prestring.contains("<color=\"#") == false)) {
            String searchcol = prestring + markstr;
            int lastcoltag = searchcol.lastIndexOf("<color=\"#") + 9;
            String excol = new String("<color=\"#" + searchcol.substring(lastcoltag, (lastcoltag + 6)) + "\">");
            t2 = ctag + excol;} else {t2 = ctag;}

            if (markstr.contains("<color=\"#") == true || markstr.contains(ctag) == true) {
                replacestr = markstr.replaceAll("<color=\"#(.)(.)(.)(.)(.)(.)\">", "").replaceAll(ctag, "");
                markstr = replacestr;}
        if (hexcol.matches("xxxxxx") == false && hexcol.matches("XXXXXX") == false) {
            String coloragain = new String(prestring + t1 + markstr + t2 + poststr);
            oop2.editor.setHtmlText(coloragain);

            //return coloragain;

        } else {
            String fontface;
            double fontsize;
            System.out.println(markstr);

         /*   if (fontcheck.contains("<font ") == true) {
                fontface = fontcheck.substring((fontcheck.lastIndexOf("<font face=\"") + 12), fontcheck.lastIndexOf(" size=\""));
                fontsize = Double.parseDouble(fontcheck.substring((fontcheck.lastIndexOf(" size=\"") + 7), (fontcheck.lastIndexOf(" size=\"") + 9)));
            } else {*/fontface = "fantasy"; fontsize = 12
            ;//}
                List<String> txtlines = textSlicer(markstr, "\n");
                int listln = txtlines.size();
                int maxlnindex = 0;
                if (listln > 1) {
                    int entrylength = 0;
                    for (int i = 0; i < listln; i++) {
                        if (txtlines.get(i).length() > entrylength) {
                            entrylength = txtlines.get(i).length();
                            maxlnindex = i;
                        }
                    }
                }

                double maxwidth = getTextWidth(txtlines.get(maxlnindex), fontface, fontsize);
            System.out.println("MAXINDEX:       " + Double.toString(maxwidth));
            System.out.println("MAXWIDTH:       " + Double.toString(maxwidth));





                String rainbowtext = new String();
                for(int i = 0; i < txtlines.size(); i++) {
                    String rainbowline = new String(lineToRainbow(txtlines.get(i), fontface, fontsize, maxwidth) + "<br>");
                    System.out.println("\nRAINBOWLINE:     " + rainbowline + "\n");
                    rainbowtext += rainbowline;
                }
            String rainbow = prestring + rainbowtext + poststr;

            System.out.println("\nPRESTRING:       " + prestring + "\n");
            System.out.println("\nRAINBOWTEXT:     " + rainbowtext + "\n");
            System.out.println("\nRAINBOW:         " + rainbow + "\n");
            System.out.println("\nRAINBOW OUT:     " + rainbow + "\n");
            oop2.editor.setHtmlText(rainbow);
            //return rainbow;
        }

    }
    public static List<String> textSlicer(String textstr, String slinp) {
        List<String> linelist = new ArrayList<>();
        //if(textstr.contains(slinp) == false) {linelist.add(textstr);return linelist;}
        int lbi = textstr.indexOf(slinp) + 1;
        linelist.add(textstr.substring(0, lbi));
        if(textstr.substring(lbi).contains(slinp) == true) {
            linelist.addAll(textSlicer(textstr.substring(lbi), slinp));
            return linelist;} else {linelist.add(textstr.substring(lbi)); return linelist;}
    }



    public static char[] getRainbowHex (double pos, double maxwidth) {
        double rad = (StrictMath.PI * 5/3) * (pos / maxwidth);
        double redamp = (cos(rad - (0.5 * sin(rad))) * 248);
        double grnamp = (sin(rad - (PI * 1/6) + (0.5 * cos(rad - (PI * 1/6)))) * 248);
        double bluamp = ((sin(rad + (PI * 7/6) + (0.5 + cos(rad + (PI * 7/6))))) * 248);

        char[] rainbowhex = new char[3];
        if(redamp >= 0) {rainbowhex[0] = ((char) round(redamp));} else {rainbowhex[0] = ((char) 0);}
        if(grnamp >= 0) {rainbowhex[1] = ((char) round(grnamp));} else {rainbowhex[1] = ((char) 0);}
        if(bluamp >= 0) {rainbowhex[2] = ((char) round(bluamp));} else {rainbowhex[2] = ((char) 0);}
        System.out.println("REDVALUE        :       " + Integer.toString(rainbowhex[0]) + "         GRNVALUE:       " + Integer.toString(rainbowhex[1]) + "         BLUVALUE:       " + Integer.toString(rainbowhex[2]));
        return rainbowhex;
    }

    public static double getTextWidth(String strwid, String fontface, double fontsize) {
        if(strwid.isEmpty() == true ) {return 0;}
        Text txtmsr = new Text();
        txtmsr.setText(strwid);
        txtmsr.setFont(Font.font(fontface, fontsize));
        new Scene(new Group(txtmsr));
        txtmsr.applyCss();
        return txtmsr.getLayoutBounds().getWidth();
    }


    public static String lineToRainbow(String linestr, String fontface, double fontsize, double maxwidth) {
        List<String> rbx = new ArrayList<>();

        int strln = linestr.length();

                for(int i = 0; i < strln; i++) {
            char c = linestr.charAt(i);
            double pos = getTextWidth(linestr.substring(0, i), fontface, fontsize);
            String htmlrbxchar = chaRBX(c, pos, maxwidth);
            rbx.add(htmlrbxchar);
        }
        String out = new String();
        for(String s : rbx) {
            out += s;
        }

        return  out;
    }


    public static String chaRBX(char c, double pos, double maxwidth) {
        char[] rainbowhexval = getRainbowHex(pos, maxwidth);
        String redval = Integer.toHexString(((int) rainbowhexval[0]));
        String grnval = Integer.toHexString(((int) rainbowhexval[1]));
        String bluval = Integer.toHexString(((int) rainbowhexval[2]));
        String r, g ,b;
        if(redval.length() == 1) {r = "0" + redval;} else {r = redval;}
        if(grnval.length() == 1) {g = "0" + grnval;} else {g = grnval;}
        if(bluval.length() == 1) {b = "0" + bluval;} else {b = bluval;}
        String rgb = new String(r + g + b);
        String charbx = new String("<span style=\"text-shadow: 1px 1px 1px #000; color:#" + rgb + "; line-height: 0.7;\">" + c + "</span>");
        return charbx;

    }



    protected static HBox createEmailEntry(int i, Folder inbox) throws Exception { //not used, as html editor was added

        HBox msgbox1 = new HBox();
        HBox msgbox2 = new HBox();
        HBox msgbox3 = new HBox();
        Text sender = new Text();
        Text subject = new Text();
        Text daterec = new Text();
        String font = "fantasy";
        double fsize = 12;




        Message msg = BFH_EmailSender.getEmail(i, inbox);
        Address[] adis = msg.getFrom();
        String addresstr = new String();
        if(inbox.getFullName().matches("Drafts") == false) {
        for (Address address : adis) {
            addresstr += address.toString();
        }}
        String recipient = addresstr.toString();
        //System.out.println(recipient);
        int ltIndex = recipient.indexOf('<');
        int atIndex = recipient.indexOf('@');
        int gtIndex = recipient.indexOf('>');
        String sendername = new String();
        String senderaddress = new String();
        if ( (ltIndex!=-1)
                && (atIndex!=-1)
                && (gtIndex!=-1)
                && (ltIndex<atIndex)
                && (atIndex<gtIndex) ) {
            sendername = recipient.substring(0, ltIndex-1);
            senderaddress = recipient.substring(ltIndex+1, gtIndex);
        } else {sendername = senderaddress = recipient;}
        try {subject.setText(new String(msg.getSubject()));} catch (NullPointerException n) {System.out.println("\nERROR: " + n + "\n"); subject.setText("");}
        System.out.println(sendername + "   " + subject.getText());
        subject.setTextAlignment(TextAlignment.LEFT);
        sender.setText(sendername);
        final TextField senfld = new TextField();
        final TextField subfld = new TextField();
        TextField datfld = new TextField();
        sender.setTextAlignment(TextAlignment.LEFT);
        daterec.setText(msg.getSentDate().toString());

        sender.prefWidth(127);
        sender.maxWidth(127);

        daterec.prefWidth(194);

        senfld.setStyle("-fx-font-family: fantasy; -fx-font-size: 12; -fx-border-style: hidden; -fx-font-weight: bold;");
        senfld.setText(sendername);
        senfld.setAlignment(Pos.CENTER_LEFT);
        senfld.setPrefWidth(127);
        senfld.setMaxWidth(127);
        senfld.setMaxHeight(13);

        final String finalSenderaddress = senderaddress;
        senfld.setCursor(Cursor.HAND);
        senfld.setOnMouseClicked(event -> {
                oop2.webStack.getChildren().clear();
                oop2.mailSubject.clear();
                oop2.view.getEngine().loadContent(oop2.content);
                oop2.webStack.getChildren().addAll(oop2.editor);
                oop2.webStack.setStyle("-fx-border-color: transparent");
                oop2.mailAdress.setText(finalSenderaddress);


        });


        int pln = 561;
        subfld.setStyle("-fx-font-family: fantasy; -fx-font-size: 12; -fx-border-style: hidden;");
        if(getTextWidth(subject.getText(), font, fsize) < pln) {subfld.setText(subject.getText());}
        else {subfld.setText(trimStringtoPixelLength(subject.getText(), font, fsize, pln));}

        subfld.setAlignment(Pos.CENTER_LEFT);
        subfld.setPrefWidth(561);
        subfld.setMaxWidth(561);
        subfld.setMaxHeight(13);
        subfld.setCursor(Cursor.HAND);
        subfld.setOnMouseClicked(event -> {
                oop2.webStack.getChildren().clear();


                String content = new String();
            try {
                inbox.open(Folder.READ_ONLY);
                if((msg.getContent() instanceof Multipart) == true) {
                    Multipart multipart = (Multipart) msg.getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {
                        BodyPart bodyPart = multipart.getBodyPart(j);
                        String disposition = bodyPart.getDisposition();

                        if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                            System.out.println("Mail have some attachment");

                            DataHandler handler = bodyPart.getDataHandler();
                            System.out.println("file name : " + handler.getName());
                        } else {
                            content = bodyPart.toString();
                        }
                    }
                } else {
                    content = msg.getContent().toString();
                }

                    oop2.view.getEngine().loadContent(content);
                    inbox.close(true);
                } catch (IOException e) {
                    e.printStackTrace();
                    oop2.view.getEngine().loadContent("IO ERROR");

                } catch (MessagingException e) {
                    e.printStackTrace();
                    oop2.view.getEngine().loadContent("MESSAGING ERROR");
                }
                oop2.webStack.getChildren().addAll(oop2.view);
                oop2.webStack.setStyle("-fx-border-color: darkgrey; -fx-border-style: solid hidden hidden hidden");
                oop2.mailSubject.setText(subject.getText());
                oop2.mailAdress.setText(recipient);

        });


        datfld.setStyle("-fx-font-family: fantasy; -fx-font-size: 12; -fx-border-style: hidden;");
        datfld.setText(daterec.getText());
        datfld.setAlignment(Pos.CENTER_RIGHT);
        datfld.setPrefWidth(194);
        datfld.setMaxWidth(194);


        msgbox1.getChildren().addAll(senfld);
        msgbox1.setMaxWidth(133);
        msgbox1.setPrefWidth(133);
        msgbox1.setMaxHeight(13);
        msgbox1.setPadding(new Insets(0,3,0,3));


        msgbox2.getChildren().addAll(subfld);
        msgbox2.setMaxWidth(567);
        msgbox2.setPrefWidth(567);
        msgbox2.setMaxHeight(23);
        msgbox2.setPadding(new Insets(0,3,0,3));

        HBox hoxbox = new HBox();
        Image icon = new Image("file:src/bilder/sent4.png");
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(29);
        imageView.setFitWidth(29);

        hoxbox.getChildren().addAll(imageView);
        hoxbox.setPadding(new Insets(7,0,0,7));
        VBox boxxy = new VBox();


boxxy.setSpacing(-7);
        boxxy.getChildren().addAll(msgbox1, msgbox2);



        //daterec.setFont(Font.font(font, fsize));
        msgbox3.getChildren().addAll(datfld);
        msgbox3.setMaxWidth(200);
        msgbox3.setPrefWidth(200);        msgbox3.setAlignment(Pos.CENTER_RIGHT);
        msgbox3.setPadding(new Insets(0,3,0,3));


        HBox msgbox = new HBox();
        msgbox.getChildren().addAll(hoxbox, boxxy, msgbox3);
        msgbox.setHgrow(msgbox1, Priority.NEVER);
        msgbox.setPrefWidth(800);
        msgbox.setMaxWidth(800);
        msgbox.setStyle("-fx-control-inner-background: white; -fx-text-fill: black; -fx-border-color: #e0e0e0; -fx-border-width: 0.5px;");


        /*
        int ln;
        int subln = msg.getSubject().toString().length();
        if(subln > 30 ) {ln = 30;} else {ln = subln - 1;}
        String filename = recipient + " - " + msg.getSubject().toString().substring(0, ln).replace(":", "").replace("\\", "").replace("|", "").replace("*", "").replace("\"", "").replace(">", "").replace("<", "").replace("/", "").replace("?", "");
        try {
            Functions.exportEmail(msg.getContent().toString(), filename, inbox.getFullName());

        } catch (MessagingException m) {}
*/
        return msgbox;
    }


    public static String trimStringtoPixelLength(String subject, String font, double fsize, int pln) {
        if(getTextWidth(subject + "...", font, fsize) < pln) {return subject + "...";}
        else {return trimStringtoPixelLength(subject.substring(0, (subject.length() - 1)), font, fsize, pln);}
    }



    protected static void exportEmail(String email, String filename, String foldername) throws Exception {
        File dir = new File("C:\\Users\\Skynet\\Documents\\BFH\\emails\\" + foldername);
        if(!dir.exists()) {
            dir.mkdir();
        }
        byte[] pub = email.getBytes();
        FileOutputStream os = new FileOutputStream("C:\\Users\\Skynet\\Documents\\BFH\\emails\\" + foldername + "\\" +filename + ".html");
        os.write(pub);
        os.flush();
        os.close();
    }




}


