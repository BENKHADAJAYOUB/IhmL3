package contactnatation;

import java.util.Optional;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ayoub benkhadaj
 */
public class ContactNatation extends Application {

    TextField mail = new TextField();
    TextField object = new TextField();
    TextArea messagearea = new TextArea();
    ProgressIndicator progressIndicator = new ProgressIndicator();
    private static final String HOVERED_BUTTON_STYLE_ORANGE = "-fx-background-color:orange";
    private static final String HOVERED_BUTTON_STYLE_BLUE = "-fx-background-color:#0D79D6";
   

    // Color hexadicimal 
    Color bl = Color.web("#0D79D6");

//   Alerte mail envoyé 
    private void showAlertSendMail() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mail envoyer");
        alert.setHeaderText("Nous avons bien reçu vos informations");
        alert.setContentText("Merci !");
        //Optional c'est à vous de choisir
        Optional<ButtonType> option = alert.showAndWait();
        //si on clique sur le button ok de l'alerte 
        if (option.get() == ButtonType.OK) {
            mail.setText("");
            messagearea.setText("");
            object.setText("");
            progressIndicator.setVisible(false);

        }
    }
    // Alerte Mail incorrect 
    private void showAlertMailIncorect() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Vore mail est incorect");
        alert.setHeaderText("Mail incorect ");
        alert.setContentText("Mail n'est bien saisi");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            progressIndicator.setVisible(false);

        }

    }

    // Alerte case vide 
    private void showAlertCasesVides() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cases vides");
        alert.setHeaderText("Voue avez pas saisi toute vous informations ");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {

            progressIndicator.setVisible(false);

        }

    }

    @Override
    public void start(Stage primaryStage) {
        //Caché le progressIndicator
        progressIndicator.setVisible(false);
        // Un conteneur de type Pane
        Pane root = new Pane();
        // Un conteneur de type ScrollPane
        ScrollPane scroll = new ScrollPane();
        //Le pane est dedans le scrollPane
        scroll.setContent(root);
        //color  background pane
        root.setStyle("-fx-background-color:WHITE");
        //Page de taille fixe (sans agrandir et réduir)
        //primaryStage.initStyle(UTILITY);
        //primaryStage.setResizable(false);

        //Image Logo 
        Image im = new Image(getClass().getResourceAsStream("img2.jpg"));
        ImageView iv = new ImageView(im);
        iv.setLayoutX(28);
        iv.setLayoutY(33);
        iv.setFitHeight(75.0);//regler height photo 
        iv.setFitWidth(94.0);//regler width photo
        //Image nageur 
        Image im1 = new Image(getClass().getResourceAsStream("r1.jpg"));
        ImageView iv1 = new ImageView(im1);
        iv1.setFitHeight(367);//regler height photo 
        iv1.setFitWidth(1000);//regler width photo 
        iv1.setLayoutX(0);
        iv1.setLayoutY(124);

        //Image contact 
        Image im3 = new Image(getClass().getResourceAsStream("ok.JPG"));
        ImageView iv3 = new ImageView(im3);
        iv3.setLayoutX(450);
        iv3.setLayoutY(506);

        //Hyperlink ACCUEIL
        scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        Hyperlink ACCUEIL = new Hyperlink("ACCUEIL");
        ACCUEIL.setLayoutX(129);
        ACCUEIL.setLayoutY(67);

        // Hyperlink COMPETITION
        Hyperlink COMPETITION = new Hyperlink("COMPETITION");
        COMPETITION.setLayoutX(198);
        COMPETITION.setLayoutY(67);
        //Hyperlink CRENAUX
        Hyperlink CRENAUX = new Hyperlink("CRENAUX");
        CRENAUX.setLayoutX(295);
        CRENAUX.setLayoutY(67);
        //Hyperlink CONTACT
        Hyperlink CONTACT = new Hyperlink("CONTACT");
        //color page courant
        CONTACT.setTextFill(Color.ORANGE);
        CONTACT.setLayoutX(370);
        CONTACT.setLayoutY(67);
        //Hyperlink GALERIES
        Hyperlink GALERIES = new Hyperlink("GALERIES");
        GALERIES.setLayoutX(452);
        GALERIES.setLayoutY(67);
        //Hyperlink LIENS
        Hyperlink LIENS = new Hyperlink("LIENS");
        LIENS.setLayoutX(535);
        LIENS.setLayoutY(67);
        //Hyperlink DOCUMENTS
        Hyperlink DOCUMENTS = new Hyperlink("DOCUMENTS");
        DOCUMENTS.setLayoutX(599);
        DOCUMENTS.setLayoutY(67);
        //Button S'INSCRIRE
        Button SINSC = new Button("S'INSCRIRE");
        SINSC.setLayoutX(707);
        SINSC.setLayoutY(63);
        SINSC.setId("sins");
        SINSC.getStyleClass().add("btn-sins");
        SINSC.setTextFill(Color.WHITE);
        //Button ESPACE COMPETITEUR
        Button espComp = new Button("ESPACE COMPETITEUR");
        espComp.setLayoutX(804);
        espComp.setLayoutY(63);
        espComp.setId("esp");
        espComp.setTextFill(Color.WHITE);
       //Label Courriel
        Label mailLab = new Label("Votre Courriel  * :");
        mailLab.setLayoutX(319);
        mailLab.setLayoutY(594);
        mailLab.setTextFill(bl);
        //Label Object email 
        Label objLab = new Label("Object email * :");
        objLab.setLayoutX(330);
        objLab.setLayoutY(638);
        objLab.setTextFill(bl);
        //Label("Message
        Label msg = new Label("Message * : ");
        msg.setLayoutX(258);
        msg.setLayoutY(709);
        msg.setTextFill(bl);
        //TEXTAREA MESSAGE
        messagearea.setPromptText("Saisir votre message");
        messagearea.setLayoutX(246);
        messagearea.setLayoutY(734);
        //TextField mail 
        mail.setPromptText("Saisir votre mail");//plac holder
        mail.setLayoutX(438);
        mail.setLayoutY(589);
        //TextField objet
        object.setPromptText("Objet mail ");
        object.setLayoutX(439);
        object.setLayoutY(634);//679949
        Button env = new Button("Envoyer");
        env.setLayoutX(662);
        env.setLayoutY(949);
        env.setStyle("-fx-background-radius:30;-fx-background-color:orange");
        //Donne important 
        Label doneimrt = new Label(" * Données obligatoires.");
        doneimrt.setLayoutX(248);
        doneimrt.setLayoutY(951);
        doneimrt.setTextFill(bl);
        //ProgressIndicator
        progressIndicator.setLayoutX(604);
        progressIndicator.setLayoutY(940);

        //Evenements d'envoie du mail 
        EventHandler<ActionEvent> a3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                progressIndicator.setVisible(true);
                //Compte gmail de l'émetteur
                final String username = "benkhadajmiaga2020@gmail.com";
                final String password = "l3miage2020";
                //JavaMail utilise des propriétés d'environnement pour recevoir certains paramètres de configuration.
                // Ils sont stockés dans un objet de type Properties.
                Properties prop = new Properties();
                prop.put("mail.smtp.auth", "true");//Il permet à un client SMTP (un expéditeur d’emails) de se connecter à un serveur SMTP
                prop.put("mail.smtp.starttls.enable", "true");
                prop.put("mail.smtp.host", "smtp.gmail.com");//Le serveur SMTP auquel se connecter.
                prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                prop.put("mail.smtp.port", "587");//Port du serveur SMTP auquel se connecte

//             La classe Session encapsule pour un client donné sa connexion avec le serveur de mails. 
//             Cette classe encapsule les données liées à la connexion (options de configuration et données d'authentification). 
//             C'est à partir de cet objet que toutes les actions concernant les mails sont réalisées
                Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                username, password);
                    }
                });

                if (mail.getText().isEmpty() || object.getText().isEmpty() || messagearea.getText().isEmpty()) {
                    showAlertCasesVides();//afichage d'une alerte dans le cas  ou une donnée n'est pas saisi

                } else if (mail.getText().contains("@") == false) {
                    showAlertMailIncorect();//afichage d'une alerte dans le cas ou le mail incorrect 

                } else {

                    try {
//-------L'envoi d'un email se passe sur trois étapes :----------
//
//       1- La création d'une Session.
//
//       2- La création d'un objet Message qui contient le destinataire, l’expéditeur, le sujet et le texte à envoyer.
//
//       3- La création d'un objet Transport, qui va transporter le message.

//                      La classe MimeMessage 
//                      Elle possède de nombreuses méthodes pour initialiser les données du message :setSubject,setFrom, setRecipients...
                        Message message = new MimeMessage(session);
//                      Mettre à jour l'émetteur
                        message.setFrom(new InternetAddress("benkhadajmiaga2020@gmail.com"));
//                      Mettre à jour les destinataires 
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getText()));
//                      Mettre à jour les objets des messages                  
                        message.setSubject(object.getText());
//                      Mettre à jour le contenu du message 
                        message.setText(messagearea.getText());
                        //La classe Transport se charge de réaliser l'envoi du message avec le protocole smtp. 
                        //C'est une classe abstraite qui contient la méthode send() pour envoyer un mail.

                        Transport.send(message);
                        //Loading for sent  mail

//                      Affichage d'une fenétre de mesage pour dire que le mail est envoyé 
                        showAlertSendMail();

                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        };

        env.addEventHandler(ActionEvent.ACTION, a3);
        //Add to container pane
        root.getChildren().add(iv);
        root.getChildren().add(iv1);
        root.getChildren().add(iv3);
        root.getChildren().add(ACCUEIL);
        root.getChildren().add(COMPETITION);
        root.getChildren().add(CRENAUX);
        root.getChildren().add(CONTACT);
        root.getChildren().add(LIENS);
        root.getChildren().add(DOCUMENTS);
        root.getChildren().add(espComp);
        root.getChildren().add(GALERIES);
        root.getChildren().add(SINSC);
        root.getChildren().add(mail);
        root.getChildren().add(object);
        root.getChildren().add(mailLab);
        root.getChildren().add(objLab);
        root.getChildren().add(msg);
        root.getChildren().add(messagearea);
        root.getChildren().add(env);
        root.getChildren().add(doneimrt);
        root.getChildren().add(progressIndicator);
        Scene scene = new Scene(scroll, 1000, 800);
        //Change the Cursor When Entering a Button Area (button envoyer)
        env.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND); //Change cursor to hand
                env.setStyle(HOVERED_BUTTON_STYLE_BLUE);

            }
        });
        //Change the Cursor When Exiting a Button Area (button envoyer)
        env.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
                env.setStyle(HOVERED_BUTTON_STYLE_ORANGE);
            }
        });
        //Change the Cursor When Entering a Button Area(Button s'inscrire)
        SINSC.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND); //Change cursor to hand
                SINSC.setStyle(HOVERED_BUTTON_STYLE_ORANGE);
            }
        });
        //Change the Cursor When Exiting a Button Area(Button s'inscrire)
        SINSC.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
                SINSC.setStyle(HOVERED_BUTTON_STYLE_BLUE);
         
            }
        });
        //Change the Cursor When Entering a Button Area(button espace compétiteur )
        espComp.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND); //Change cursor to hand
                //HOVER
                espComp.setStyle(HOVERED_BUTTON_STYLE_ORANGE);

            }
        });
        //Change the Cursor When Exiting a Button Area(button espace compétiteur )
        espComp.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
                //HOVER
                espComp.setStyle(HOVERED_BUTTON_STYLE_BLUE);
            }
        });
        //évenement Hyperlink ACCEUIL 
        ACCUEIL.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                //hover
                ACCUEIL.setTextFill(Color.ORANGE);
            }
        });
        ACCUEIL.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                //HOVER
                ACCUEIL.setTextFill(bl);
            }
        });
        //évenement Hyperlink Compétition  
        COMPETITION.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                //hover
                COMPETITION.setTextFill(Color.ORANGE);
            }
        });
        COMPETITION.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                //HOVER
                COMPETITION.setTextFill(bl);
            }
        });
        //évenement Hyperlink Créneaux
        CRENAUX.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                //hover
                CRENAUX.setTextFill(Color.ORANGE);
            }
        });
        CRENAUX.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                //HOVER
                CRENAUX.setTextFill(bl);
            }
        });
        //évenement Hyperlink GALERIES
        GALERIES.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                //hover
                GALERIES.setTextFill(Color.ORANGE);
            }
        });
        GALERIES.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                //HOVER
                GALERIES.setTextFill(bl);
            }
        });
        //évenement Hyperlink LIENS
        LIENS.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                //hover
                LIENS.setTextFill(Color.ORANGE);
            }
        });
        LIENS.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                //HOVER
                LIENS.setTextFill(bl);
            }
        });
        //évenement Hyperlink Documents 
        DOCUMENTS.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                //hover
                DOCUMENTS.setTextFill(Color.ORANGE);
            }
        });
        DOCUMENTS.setOnMouseExited(new EventHandler() {

            @Override
            public void handle(Event event) {
                //HOVER
                DOCUMENTS.setTextFill(bl);
            }
        });
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Page CONTACT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
