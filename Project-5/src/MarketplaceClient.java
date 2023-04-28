import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * Client GUI's
 *
 * @author Jai Nanda, lab sec 07
 *
 * @version 24/05/2023
 *
 */

public class MarketplaceClient {
    public static void main(String[] args) {
        //User user;
        try {
            MarketplaceClient marketplaceClient = new MarketplaceClient();
            Socket socket = new Socket("localhost", 4242);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            JFrame frame = new JFrame("Online Marketplace"); // creating new frame
            JPanel panel = new JPanel(); // creating new panel
            frame.getContentPane();

            JLabel welcomeMessage = new JLabel("Welcome to Marketplace!"); // new jlabel welcome message
            welcomeMessage.setForeground(Color.cyan); // welcome message color
            welcomeMessage.setBounds(122, 10, 500, 30); // welcome message position in frame

            JButton loginButton = new JButton("Log In");// new jlabel login button
            loginButton.setBounds(60, 120, 100, 30); // login button position in frame

            JButton signUpButton = new JButton("Sign Up");// new jlabel sign up button
            signUpButton.setBounds(250, 120, 100, 30); // sign up button position in frame

            signUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    marketplaceClient.Two(reader, writer, ois,oos);
                }
            });


            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    marketplaceClient.Four(reader, writer,ois,oos);
                }
            });

            panel.setLayout(null);
            panel.add(welcomeMessage);
            panel.add(loginButton);
            panel.add(signUpButton);
            //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


            panel.setBackground(Color.darkGray);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    public void Two(BufferedReader reader, PrintWriter writer, ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame2 = new JFrame("Online Marketplace"); // creating new frame
        JPanel panel2 = new JPanel(); // creating new panel
        frame2.getContentPane();

        JLabel logInMenu = new JLabel("Create New Account"); // new jlabel welcome message
        logInMenu.setForeground(Color.cyan); // welcome message color
        Dimension size = logInMenu.getPreferredSize();
        logInMenu.setBounds(150, 10, size.width, size.height); // welcome message position in frame

        JLabel username = new JLabel("Username :"); // new jlabel welcome message
        username.setForeground(Color.cyan); // welcome message color
        username.setBounds(122, 50, size.width, size.height); // welcome message position in frame

        JTextField usernameText = new JTextField();
        usernameText.setBounds(202, 50, size.width, 20); // welcome message position in frame

        JLabel password = new JLabel("Password :"); // new jlabel welcome message
        password.setForeground(Color.cyan); // welcome message color
        password.setBounds(122, 80, size.width, size.height); // welcome message position in frame

        JTextField passwordText = new JTextField();
        passwordText.setBounds(202, 80, size.width, 20); // welcome message position in frame

        JLabel email = new JLabel("Email :"); // new jlabel welcome message
        email.setForeground(Color.cyan); // welcome message color
        email.setBounds(122, 110, size.width, size.height); // welcome message position in frame

        JTextField emailText = new JTextField();
        emailText.setBounds(202, 110, size.width, 20); // welcome message position in frame

        //String[] options={"Buyer","Seller"};
        JLabel user = new JLabel("Buyer/Seller :"); // new jlabel welcome message
        user.setForeground(Color.cyan); // welcome message color
        user.setBounds(122, 140, size.width, size.height); // welcome message position in frame
        JComboBox<String> options = new JComboBox();
        options.addItem("Buyer");
        options.addItem("Seller");
        options.setBounds(205, 135, size.width, 30);


        JButton logIn = new JButton("Create Account");// new jlabel login button
        logIn.setBounds(210, 180, 125, 30); // login button position in frame

        panel2.setLayout(null);
        panel2.add(logInMenu);
        panel2.add(username);
        panel2.add(usernameText);
        panel2.add(password);
        panel2.add(passwordText);
        panel2.add(email);
        panel2.add(emailText);
        panel2.add(logIn);
        panel2.add(user);
        panel2.add(options);
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel2.setBackground(Color.darkGray);
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.add(panel2);
        frame2.setSize(400, 250);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);


        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String uName = usernameText.getText();
                String password = passwordText.getText();
                String email = emailText.getText();
                if (uName.equals("")||password.equals("")||email.equals("")){
                    marketplaceClient.Twenty(reader,writer,ois,oos);
                }
                else {
                    int buyer = options.getSelectedIndex();
                    boolean type = true;
                    if (buyer == 0) {
                        type = true;
                    } else {
                        type = false;
                    }
                    User user2=new User(uName,password,email,type,null);
                    System.out.println(uName);
                    System.out.println(password);
                    System.out.println(email);
                    System.out.println(buyer);
                    try {
                        //User user2 = new User(uName, password, email, type);
                        //user2.writeUser();
                        writer.println("2");
                        oos.writeObject(user2);
                        //writer.println(uName);
                        //writer.println(password);
                        //writer.println(email);
                        //writer.flush();
                        marketplaceClient.Three(reader, writer, ois,oos);
                    } catch (Exception ex) {
                        marketplaceClient.Five(reader, writer,ois,oos);
                    }
                }

                //marketplaceServer.Three();
            }
        });


    }

    public void Twenty(BufferedReader reader,PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos){
        JFrame frame20 = new JFrame("Online Marketplace"); // creating new frame20
        JPanel panel20 = new JPanel(); // creating new panel20
        frame20.getContentPane();

        JLabel errorLabel = new JLabel("Please enter all the fields!"); // new jlabel welcome message
        errorLabel.setForeground(Color.cyan); // welcome message color
        Dimension size = errorLabel.getPreferredSize();
        errorLabel.setBounds(80, 50, size.width, size.height); // welcome message position in frame20


        JButton errorButton = new JButton("Try Again");// new jlabel login button
        errorButton.setBounds(250, 120, 100, 30); // login button position in frame20

        panel20.setLayout(null);
        panel20.add(errorLabel);
        panel20.add(errorButton);
        //panel20.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel20.setBackground(Color.darkGray);
        frame20.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame20.add(panel20);
        frame20.setSize(400, 250);
        frame20.setLocationRelativeTo(null);
        frame20.setVisible(true);

        errorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MarketplaceClient marketplaceClient=new MarketplaceClient();
                marketplaceClient.Two(reader,writer,ois,oos);
            }
        });
    }

    public void Four(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame4 = new JFrame("Online Marketplace"); // creating new frame
        JPanel panel4 = new JPanel(); // creating new panel
        frame4.getContentPane();

        JLabel logInMenu = new JLabel("Log In Menu"); // new jlabel welcome message
        logInMenu.setForeground(Color.cyan); // welcome message color
        Dimension size = logInMenu.getPreferredSize();
        logInMenu.setBounds(150, 10, size.width, size.height); // welcome message position in frame

        JLabel username = new JLabel("Username :"); // new jlabel welcome message
        username.setForeground(Color.cyan); // welcome message color
        username.setBounds(122, 50, size.width, size.height); // welcome message position in frame

        JTextField usernameText = new JTextField();
        usernameText.setBounds(202, 50, size.width, 20); // welcome message position in frame

        JLabel password = new JLabel("Password :"); // new jlabel welcome message
        password.setForeground(Color.cyan); // welcome message color
        password.setBounds(122, 80, size.width, size.height); // welcome message position in frame

        JTextField passwordText = new JTextField();
        passwordText.setBounds(202, 80, size.width, 20); // welcome message position in frame


        JButton logIn = new JButton("Log In");// new jlabel login button
        logIn.setBounds(250, 120, 100, 30); // login button position in frame


        panel4.setLayout(null);
        panel4.add(logInMenu);
        panel4.add(username);
        panel4.add(usernameText);
        panel4.add(password);
        panel4.add(passwordText);
        panel4.add(logIn);
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel4.setBackground(Color.darkGray);
        frame4.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame4.add(panel4);
        frame4.setSize(400, 250);
        frame4.setLocationRelativeTo(null);
        frame4.setVisible(true);

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame4.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String uName = usernameText.getText();
                String uPassword=passwordText.getText();
                System.out.println(uName);
                System.out.println(uPassword);
                try {
                    writer.println("4");
                    writer.println(uName);
                    writer.println(uPassword);
                    writer.flush();
                    String result=reader.readLine();
                    System.out.println("hello");
                    String type=reader.readLine();
                    if (result.equals("success")){
                        if (type.equals("b")){
                            marketplaceClient.Seven(reader,writer,ois,oos);
                        }
                        else{
                            marketplaceClient.Six(reader, writer,ois,oos);
                        }
                    }
                    else {
                        marketplaceClient.Five(reader,writer,ois,oos);
                    }
                    //marketplaceClient.Seven(reader, writer,ois,oos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void Three(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame3 = new JFrame("Online Marketplace"); // creating new frame3
        JPanel panel3 = new JPanel(); // creating new panel3
        frame3.getContentPane();

        JLabel storeRegistration = new JLabel("Store Registration"); // new jlabel welcome message
        storeRegistration.setForeground(Color.cyan); // welcome message color
        Dimension size = storeRegistration.getPreferredSize();
        storeRegistration.setBounds(150, 10, size.width, size.height); // welcome message position in frame3

        JLabel sName = new JLabel("Store Name :"); // new jlabel welcome message
        sName.setForeground(Color.cyan); // welcome message color
        sName.setBounds(122, 80, size.width, size.height); // welcome message position in frame3

        JTextField sNameText = new JTextField();
        sNameText.setBounds(202, 80, size.width, 20); // welcome message position in frame3


        JButton Ok3 = new JButton("Ok");// new jlabel login button
        Ok3.setBounds(250, 120, 100, 30); // login button position in frame3

        panel3.setLayout(null);
        panel3.add(storeRegistration);
        panel3.add(sName);
        panel3.add(sNameText);
        panel3.add(Ok3);
        //panel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel3.setBackground(Color.darkGray);
        frame3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame3.add(panel3);
        frame3.setSize(400, 250);
        frame3.setLocationRelativeTo(null);
        frame3.setVisible(true);

        Ok3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }


    public void Five(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame5 = new JFrame("Online Marketplace"); // creating new frame5
        JPanel panel5 = new JPanel(); // creating new panel5
        frame5.getContentPane();

        JLabel exceptionLabel = new JLabel("The username or password is incorrect!"); // new jlabel welcome message
        exceptionLabel.setForeground(Color.cyan); // welcome message color
        Dimension size = exceptionLabel.getPreferredSize();
        exceptionLabel.setBounds(80, 50, size.width, size.height); // welcome message position in frame5


        JButton exceptionButton = new JButton("Try Again");// new jlabel login button
        exceptionButton.setBounds(250, 120, 100, 30); // login button position in frame5

        panel5.setLayout(null);
        panel5.add(exceptionLabel);
        panel5.add(exceptionButton);
        //panel5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel5.setBackground(Color.darkGray);
        frame5.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame5.add(panel5);
        frame5.setSize(400, 250);
        frame5.setLocationRelativeTo(null);
        frame5.setVisible(true);

        exceptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame5.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Four(reader, writer,ois,oos);
            }
        });
    }

    public void Six(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame6 = new JFrame("Online Marketplace"); // creating new frame6
        JPanel panel6 = new JPanel(); // creating new panel6
        frame6.getContentPane();

        JLabel menuLabel = new JLabel("Seller Menu"); // new jlabel welcome message
        menuLabel.setForeground(Color.cyan); // welcome message color
        Dimension size = menuLabel.getPreferredSize();
        menuLabel.setBounds(170, 10, size.width, size.height); // welcome message position in frame6


        JButton create = new JButton("Create");// new jlabel login button
        create.setBounds(50, 50, 100, 60); // login button position in frame6

        JButton edit = new JButton("Edit");// new jlabel login button
        edit.setBounds(160, 50, 100, 60); // login button position in frame6

        JButton delete = new JButton("Delete");// new jlabel login button
        delete.setBounds(270, 50, 100, 60); // login button position in frame6

        JButton statistics = new JButton("Statistics");// new jlabel login button
        statistics.setBounds(100, 120, 100, 60); // login button position in frame6

        JButton exit = new JButton("Exit");// new jlabel login button
        exit.setBounds(210, 120, 100, 60); // login button position in frame6

        panel6.setLayout(null);
        panel6.add(menuLabel);
        panel6.add(create);
        panel6.add(edit);
        panel6.add(delete);
        panel6.add(statistics);
        panel6.add(exit);
        //panel6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel6.setBackground(Color.darkGray);
        frame6.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame6.add(panel6);
        frame6.setSize(400, 250);
        frame6.setLocationRelativeTo(null);
        frame6.setVisible(true);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Eight(reader, writer,ois,oos);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Nine(reader, writer,ois,oos);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Ten(reader, writer,ois,oos);
            }
        });

        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Eleven(reader, writer,ois,oos);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Twelve(reader, writer,ois,oos);
            }
        });
    }

    public void Seven(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame7 = new JFrame("Online Marketplace"); // creating new frame7
        JPanel panel7 = new JPanel(); // creating new panel7
        frame7.getContentPane();

        JLabel buyerMenu = new JLabel("Buyer Menu"); // new jlabel welcome message
        buyerMenu.setForeground(Color.cyan); // welcome message color
        Dimension size = buyerMenu.getPreferredSize();
        buyerMenu.setBounds(170, 10, size.width, size.height); // welcome message position in frame7


        JButton search7 = new JButton("Search");// new jlabel login button
        search7.setBounds(50, 50, 100, 60); // login button position in frame7

        JButton cart7 = new JButton("View Cart");// new jlabel login button
        cart7.setBounds(160, 50, 100, 60); // login button position in frame7

        JButton statistics7 = new JButton("Statistics");// new jlabel login button
        statistics7.setBounds(270, 50, 100, 60); // login button position in frame7

        JButton phistory7 = new JButton("Purchase History");// new jlabel login button
        phistory7.setBounds(90, 120, 120, 60); // login button position in frame7

        JButton exit7 = new JButton("Exit");// new jlabel login button
        exit7.setBounds(220, 120, 100, 60); // login button position in frame7

        panel7.setLayout(null);
        panel7.add(buyerMenu);
        panel7.add(search7);
        panel7.add(cart7);
        panel7.add(statistics7);
        panel7.add(phistory7);
        panel7.add(exit7);
        //panel7.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel7.setBackground(Color.darkGray);
        frame7.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame7.add(panel7);
        frame7.setSize(400, 250);
        frame7.setLocationRelativeTo(null);
        frame7.setVisible(true);
        MarketplaceClient marketplaceClient = new MarketplaceClient();

        search7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispose();
                marketplaceClient.TwentyTwo(reader, writer,ois,oos);
            }
        });

        cart7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispose();
                marketplaceClient.ThirtyOne(reader, writer,ois,oos);
            }
        });

        statistics7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispose();
                marketplaceClient.TwentyEight(reader, writer,ois,oos);
            }
        });

        phistory7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispose();
                marketplaceClient.TwentyNine(reader, writer,ois,oos);
            }
        });

        exit7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame7.dispose();
                marketplaceClient.Twelve(reader, writer,ois,oos);
            }
        });
    }

    public void Eight(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame8 = new JFrame("Online Marketplace"); // creating new frame8
        JPanel panel8 = new JPanel(); // creating new panel8
        frame8.getContentPane();

        JLabel newProduct = new JLabel("Create New Product"); // new jlabel welcome message
        newProduct.setForeground(Color.cyan); // welcome message color
        Dimension size = newProduct.getPreferredSize();
        newProduct.setBounds(150, 10, size.width, size.height); // welcome message position in frame8

        JLabel productName8 = new JLabel("Name :"); // new jlabel welcome message
        productName8.setForeground(Color.cyan); // welcome message color
        productName8.setBounds(122, 50, size.width, size.height); // welcome message position in frame8

        JTextField productNameText8 = new JTextField();
        productNameText8.setBounds(202, 50, size.width, 20); // welcome message position in frame8

        JLabel price8 = new JLabel("Price :"); // new jlabel welcome message
        price8.setForeground(Color.cyan); // welcome message color
        price8.setBounds(122, 80, size.width, size.height); // welcome message position in frame8

        JTextField priceText8 = new JTextField();
        priceText8.setBounds(202, 80, size.width, 20); // welcome message position in frame8

        JLabel qty8 = new JLabel("Quantity :"); // new jlabel welcome message
        qty8.setForeground(Color.cyan); // welcome message color
        qty8.setBounds(122, 110, size.width, size.height); // welcome message position in frame8

        JTextField qtyText8 = new JTextField();
        qtyText8.setBounds(202, 110, size.width, 20); // welcome message position in frame8

        //String[] options={"Buyer","Seller"};
        JLabel description8 = new JLabel("Description :"); // new jlabel welcome message
        description8.setForeground(Color.cyan); // welcome message color
        description8.setBounds(122, 140, size.width, size.height); // welcome message position in frame8

        JTextField descriptionText8 = new JTextField();
        descriptionText8.setBounds(202, 140, size.width, 20); // welcome message position in frame8


        JButton ok8 = new JButton("Ok");// new jlabel login button
        ok8.setBounds(210, 180, 125, 30); // login button position in frame8

        JButton back8 = new JButton("Back");// new jlabel login button
        back8.setBounds(50, 180, 125, 30); // login button position in frame8

        panel8.setLayout(null);
        panel8.add(newProduct);
        panel8.add(productName8);
        panel8.add(productNameText8);
        panel8.add(price8);
        panel8.add(priceText8);
        panel8.add(qty8);
        panel8.add(qtyText8);
        panel8.add(ok8);
        panel8.add(back8);
        panel8.add(description8);
        panel8.add(descriptionText8);
        //panel8.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel8.setBackground(Color.darkGray);
        frame8.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame8.add(panel8);
        frame8.setSize(400, 250);
        frame8.setLocationRelativeTo(null);
        frame8.setVisible(true);


        ok8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame8.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String pName = productNameText8.getText();
                String pPrice = priceText8.getText();
                String pQty = qtyText8.getText();
                String pDescription = descriptionText8.getText();
                System.out.println(pName);
                System.out.println(pPrice);
                System.out.println(pQty);
                System.out.println(pDescription);
                try {
                    System.out.println("Server Connected");
                    writer.println("8");
                    System.out.println("Hello");
                    writer.println(pName);
                    writer.println(pPrice);
                    writer.println(pQty);
                    writer.println(pDescription);
                    writer.flush();
                    String result = reader.readLine();
                    if (result.equals("success")) {
                        System.out.println("success");
                        marketplaceClient.Thirteen(reader, writer,ois,oos);
                    } else {
                        System.out.println("unsuccessful");
                        marketplaceClient.Seventeen(reader, writer,ois,oos);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //writer.println("8");
                //marketplaceServer.Thirteen();
            }
        });


        back8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame8.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }

    public void Nine(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame9 = new JFrame("Online Marketplace"); // creating new frame9
        JPanel panel9 = new JPanel(); // creating new panel9
        frame9.getContentPane();

        JLabel editProduct = new JLabel("Edit Product"); // new jlabel welcome message
        editProduct.setForeground(Color.cyan); // welcome message color
        Dimension size = editProduct.getPreferredSize();
        editProduct.setBounds(150, 10, size.width, size.height); // welcome message position in frame9

        JLabel editName = new JLabel("Product Name to edit :"); // new jlabel welcome message
        editName.setForeground(Color.cyan); // welcome message color
        editName.setBounds(50, 80, 200, size.height); // welcome message position in frame9

        JTextField editNameText = new JTextField();
        editNameText.setBounds(202, 78, 100, 20); // welcome message position in frame9


        JButton ok9 = new JButton("Ok");// new jlabel login button
        ok9.setBounds(250, 150, 100, 30); // login button position in frame9

        JButton back9 = new JButton("Back");// new jlabel login button
        back9.setBounds(50, 150, 100, 30); // login button position in frame9

        panel9.setLayout(null);
        panel9.add(editProduct);
        panel9.add(editName);
        panel9.add(editNameText);
        panel9.add(ok9);
        panel9.add(back9);
        //panel9.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel9.setBackground(Color.darkGray);
        frame9.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame9.add(panel9);
        frame9.setSize(400, 250);
        frame9.setLocationRelativeTo(null);
        frame9.setVisible(true);

        ok9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame9.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String pName = editNameText.getText();
                try {
                    writer.println("9");
                    writer.println(pName);
                    writer.flush();
                    String result = reader.readLine();
                    if (result.equals("success")) {
                        marketplaceClient.Fourteen(reader, writer,ois,oos);
                    } else {
                        marketplaceClient.Eighteen(reader, writer,ois,oos);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        back9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame9.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }

    public void Ten(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame10 = new JFrame("Online Marketplace"); // creating new frame10
        JPanel panel10 = new JPanel(); // creating new panel10
        frame10.getContentPane();

        JLabel deleteProduct = new JLabel("Delete Product"); // new jlabel welcome message
        deleteProduct.setForeground(Color.cyan); // welcome message color
        Dimension size = deleteProduct.getPreferredSize();
        deleteProduct.setBounds(150, 10, size.width, size.height); // welcome message position in frame10

        JLabel deleteName = new JLabel("Product Name to delete :"); // new jlabel welcome message
        deleteName.setForeground(Color.cyan); // welcome message color
        deleteName.setBounds(50, 80, 200, size.height); // welcome message position in frame10

        JTextField deleteNameText = new JTextField();
        deleteNameText.setBounds(212, 78, 100, 20); // welcome message position in frame10


        JButton ok10 = new JButton("Ok");// new jlabel login button
        ok10.setBounds(250, 150, 100, 30); // login button position in frame9

        JButton back10 = new JButton("Back");// new jlabel login button
        back10.setBounds(50, 150, 100, 30); // login button position in frame9

        panel10.setLayout(null);
        panel10.add(deleteProduct);
        panel10.add(deleteName);
        panel10.add(deleteNameText);
        panel10.add(ok10);
        panel10.add(back10);
        //panel10.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel10.setBackground(Color.darkGray);
        frame10.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame10.add(panel10);
        frame10.setSize(400, 250);
        frame10.setLocationRelativeTo(null);
        frame10.setVisible(true);

        ok10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame10.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String pName = deleteNameText.getText();
                try {
                    writer.println("10");
                    writer.println(pName);
                    writer.flush();
                    String result = reader.readLine();
                    if (result.equals("success")) {
                        marketplaceClient.Sixteen(reader, writer,ois,oos);
                    } else {
                        marketplaceClient.Nineteen(reader, writer,ois,oos);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        back10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame10.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }

    public void Eleven(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame11 = new JFrame("Online Marketplace"); // creating new frame11
        JPanel panel11 = new JPanel(); // creating new panel11
        frame11.getContentPane();

        JLabel statisticsInfo = new JLabel("Statistics info"); // new jlabel welcome message
        statisticsInfo.setForeground(Color.cyan); // welcome message color
        Dimension size = statisticsInfo.getPreferredSize();
        statisticsInfo.setBounds(150, 10, size.width, size.height); // welcome message position in frame11


        JButton ok11 = new JButton("Ok");// new jlabel login button
        ok11.setBounds(250, 120, 100, 30); // login button position in frame11

        panel11.setLayout(null);
        panel11.add(statisticsInfo);
        panel11.add(ok11);
        //panel11.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel11.setBackground(Color.darkGray);
        frame11.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame11.add(panel11);
        frame11.setSize(400, 250);
        frame11.setLocationRelativeTo(null);
        frame11.setVisible(true);

        ok11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame11.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }

    public void Twelve(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame12 = new JFrame("Online Marketplace"); // creating new frame12
        JPanel panel12 = new JPanel(); // creating new panel12
        frame12.getContentPane();

        JLabel label12 = new JLabel(" Thank you for using Marketplace, have a good day!"); // new jlabel welcome message
        label12.setForeground(Color.cyan); // welcome message color
        Dimension size = label12.getPreferredSize();
        label12.setBounds(40, 50, size.width, size.height); // welcome message position in frame12


        JButton ok12 = new JButton("Ok");// new jlabel login button
        ok12.setBounds(250, 120, 100, 30); // login button position in frame12

        panel12.setLayout(null);
        panel12.add(label12);
        panel12.add(ok12);
        //panel12.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel12.setBackground(Color.darkGray);
        frame12.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame12.add(panel12);
        frame12.setSize(400, 250);
        frame12.setLocationRelativeTo(null);
        frame12.setVisible(true);
        System.out.println("First");


        ok12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                try {
                    writer.println("0");
                    writer.flush();
                    writer.close();
                    reader.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                frame12.dispose();
            }
        });
    }

    public void Thirteen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame13 = new JFrame("Online Marketplace"); // creating new frame13
        JPanel panel13 = new JPanel(); // creating new panel13
        frame13.getContentPane();

        JLabel label13 = new JLabel("Product created"); // new jlabel welcome message
        label13.setForeground(Color.cyan); // welcome message color
        Dimension size = label13.getPreferredSize();
        label13.setBounds(150, 50, size.width, size.height); // welcome message position in frame13


        JButton ok13 = new JButton("Ok");// new jlabel login button
        ok13.setBounds(250, 120, 100, 30); // login button position in frame13

        panel13.setLayout(null);
        panel13.add(label13);
        panel13.add(ok13);
        //panel13.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel13.setBackground(Color.darkGray);
        frame13.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame13.add(panel13);
        frame13.setSize(400, 250);
        frame13.setLocationRelativeTo(null);
        frame13.setVisible(true);

        ok13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame13.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }


    public void Fourteen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame14 = new JFrame("Online Marketplace"); // creating new frame14
        JPanel panel14 = new JPanel(); // creating new panel14
        frame14.getContentPane();

        JLabel editLabel = new JLabel("Edit existing product"); // new jlabel welcome message
        editLabel.setForeground(Color.cyan); // welcome message color
        Dimension size = editLabel.getPreferredSize();
        editLabel.setBounds(150, 10, size.width, size.height); // welcome message position in frame14

        JLabel productName14 = new JLabel("Name :"); // new jlabel welcome message
        productName14.setForeground(Color.cyan); // welcome message color
        productName14.setBounds(122, 50, size.width, size.height); // welcome message position in frame14

        JTextField productNameText14 = new JTextField();
        productNameText14.setBounds(202, 50, size.width, 20); // welcome message position in frame14

        JLabel price14 = new JLabel("Price :"); // new jlabel welcome message
        price14.setForeground(Color.cyan); // welcome message color
        price14.setBounds(122, 80, size.width, size.height); // welcome message position in frame14

        JTextField priceText14 = new JTextField();
        priceText14.setBounds(202, 80, size.width, 20); // welcome message position in frame14

        JLabel qty14 = new JLabel("Quantity :"); // new jlabel welcome message
        qty14.setForeground(Color.cyan); // welcome message color
        qty14.setBounds(122, 110, size.width, size.height); // welcome message position in frame14

        JTextField qtyText14 = new JTextField();
        qtyText14.setBounds(202, 110, size.width, 20); // welcome message position in frame14

        //String[] options={"Buyer","Seller"};
        JLabel description14 = new JLabel("Description :"); // new jlabel welcome message
        description14.setForeground(Color.cyan); // welcome message color
        description14.setBounds(122, 140, size.width, size.height); // welcome message position in frame14

        JTextField descriptionText14 = new JTextField();
        descriptionText14.setBounds(202, 140, size.width, 20); // welcome message position in frame14


        JButton ok14 = new JButton("Ok");// new jlabel login button
        ok14.setBounds(210, 180, 125, 30); // login button position in frame14

        panel14.setLayout(null);
        panel14.add(editLabel);
        panel14.add(productName14);
        panel14.add(productNameText14);
        panel14.add(price14);
        panel14.add(priceText14);
        panel14.add(qty14);
        panel14.add(qtyText14);
        panel14.add(ok14);
        panel14.add(description14);
        panel14.add(descriptionText14);
        //panel14.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel14.setBackground(Color.darkGray);
        frame14.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame14.add(panel14);
        frame14.setSize(400, 250);
        frame14.setLocationRelativeTo(null);
        frame14.setVisible(true);


        ok14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame14.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String pName = productNameText14.getText();
                String pPrice = priceText14.getText();
                String pQty = qtyText14.getText();
                String pDescription = descriptionText14.getText();
                try {
                    System.out.println("Server Connected");
                    writer.println("14");
                    writer.println(pName);
                    writer.println(pPrice);
                    writer.println(pQty);
                    writer.println(pDescription);
                    writer.flush();
                    marketplaceClient.Fifteen(reader, writer,ois,oos);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //writer.println("8");
                //marketplaceServer.Thirteen();
            }
        });


    }

    public void Fifteen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame15 = new JFrame("Online Marketplace"); // creating new frame15
        JPanel panel15 = new JPanel(); // creating new panel15
        frame15.getContentPane();

        JLabel updatedText = new JLabel("Product Updated"); // new jlabel welcome message
        updatedText.setForeground(Color.cyan); // welcome message color
        Dimension size = updatedText.getPreferredSize();
        updatedText.setBounds(150, 50, size.width, size.height); // welcome message position in frame15


        JButton ok15 = new JButton("Ok");// new jlabel login button
        ok15.setBounds(250, 120, 100, 30); // login button position in frame15

        panel15.setLayout(null);
        panel15.add(updatedText);
        panel15.add(ok15);
        //panel15.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel15.setBackground(Color.darkGray);
        frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame15.add(panel15);
        frame15.setSize(400, 250);
        frame15.setLocationRelativeTo(null);
        frame15.setVisible(true);

        ok15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame15.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }

    public void Sixteen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame16 = new JFrame("Online Marketplace"); // creating new frame16
        JPanel panel16 = new JPanel(); // creating new panel16
        frame16.getContentPane();

        JLabel deletedText = new JLabel("Product deleted"); // new jlabel welcome message
        deletedText.setForeground(Color.cyan); // welcome message color
        Dimension size = deletedText.getPreferredSize();
        deletedText.setBounds(150, 50, size.width, size.height); // welcome message position in frame16


        JButton ok16 = new JButton("Ok");// new jlabel login button
        ok16.setBounds(250, 120, 100, 30); // login button position in frame16

        panel16.setLayout(null);
        panel16.add(deletedText);
        panel16.add(ok16);
        //panel16.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel16.setBackground(Color.darkGray);
        frame16.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame16.add(panel16);
        frame16.setSize(400, 250);
        frame16.setLocationRelativeTo(null);
        frame16.setVisible(true);

        ok16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame16.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Six(reader, writer,ois,oos);
            }
        });
    }

    public void Seventeen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame17 = new JFrame("Online Marketplace"); // creating new frame17
        JPanel panel17 = new JPanel(); // creating new panel17
        frame17.getContentPane();

        JLabel errorText17 = new JLabel("Please check that all fields are valid"); // new jlabel welcome message
        errorText17.setForeground(Color.cyan); // welcome message color
        Dimension size = errorText17.getPreferredSize();
        errorText17.setBounds(100, 50, size.width, size.height); // welcome message position in frame17


        JButton ok17 = new JButton("Ok");// new jlabel login button
        ok17.setBounds(250, 120, 100, 30); // login button position in frame17

        panel17.setLayout(null);
        panel17.add(errorText17);
        panel17.add(ok17);
        //panel17.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel17.setBackground(Color.darkGray);
        frame17.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame17.add(panel17);
        frame17.setSize(400, 250);
        frame17.setLocationRelativeTo(null);
        frame17.setVisible(true);

        ok17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame17.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Eight(reader, writer,ois,oos);
            }
        });
    }

    public void Eighteen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame18 = new JFrame("Online Marketplace"); // creating new frame18
        JPanel panel18 = new JPanel(); // creating new panel18
        frame18.getContentPane();

        JLabel errorText18 = new JLabel("Please check that all fields are valid"); // new jlabel welcome message
        errorText18.setForeground(Color.cyan); // welcome message color
        Dimension size = errorText18.getPreferredSize();
        errorText18.setBounds(100, 50, size.width, size.height); // welcome message position in frame18


        JButton ok18 = new JButton("Ok");// new jlabel login button
        ok18.setBounds(250, 120, 100, 30); // login button position in frame18

        panel18.setLayout(null);
        panel18.add(errorText18);
        panel18.add(ok18);
        //panel18.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel18.setBackground(Color.darkGray);
        frame18.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame18.add(panel18);
        frame18.setSize(400, 250);
        frame18.setLocationRelativeTo(null);
        frame18.setVisible(true);

        ok18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame18.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Nine(reader, writer,ois,oos);
            }
        });
    }

    public void Nineteen(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame19 = new JFrame("Online Marketplace"); // creating new frame19
        JPanel panel19 = new JPanel(); // creating new panel19
        frame19.getContentPane();

        JLabel errorText18 = new JLabel("Please check that all fields are valid"); // new jlabel welcome message
        errorText18.setForeground(Color.cyan); // welcome message color
        Dimension size = errorText18.getPreferredSize();
        errorText18.setBounds(100, 50, size.width, size.height); // welcome message position in frame19


        JButton ok19 = new JButton("Ok");// new jlabel login button
        ok19.setBounds(250, 120, 100, 30); // login button position in frame19

        panel19.setLayout(null);
        panel19.add(errorText18);
        panel19.add(ok19);
        //panel19.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel19.setBackground(Color.darkGray);
        frame19.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame19.add(panel19);
        frame19.setSize(400, 250);
        frame19.setLocationRelativeTo(null);
        frame19.setVisible(true);

        ok19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame19.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Ten(reader, writer,ois,oos);
            }
        });
    }

    public void TwentyTwo(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame22 = new JFrame("Online Marketplace"); // creating new frame22
        JPanel panel22 = new JPanel(); // creating new panel22
        frame22.getContentPane();

        JLabel menuLabel22 = new JLabel("Search Options"); // new jlabel welcome message
        menuLabel22.setForeground(Color.cyan); // welcome message color
        Dimension size = menuLabel22.getPreferredSize();
        menuLabel22.setBounds(155, 10, size.width, size.height); // welcome message position in frame22


        JButton showEntireStoreList = new JButton("Show entire store list");// new jlabel login button
        size = showEntireStoreList.getPreferredSize();
        showEntireStoreList.setBounds(20, 80, size.width, 60); // login button position in frame22

        JButton searchWithKeyword = new JButton("Search with keyword");// new jlabel login button
        size = searchWithKeyword.getPreferredSize();
        searchWithKeyword.setBounds(210, 80, size.width, 60); // login button position in frame22


        panel22.setLayout(null);
        panel22.add(menuLabel22);
        panel22.add(showEntireStoreList);
        panel22.add(searchWithKeyword);
        //panel22.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel22.setBackground(Color.darkGray);
        frame22.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame22.add(panel22);
        frame22.setSize(400, 250);
        frame22.setLocationRelativeTo(null);
        frame22.setVisible(true);

        searchWithKeyword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame22.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.TwentyThree(reader, writer,ois,oos);
            }
        });

        showEntireStoreList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame22.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                //ArrayList<String> storeList=ois.readObject(ArrayList);
                try {
                    writer.println("22");
                    writer.flush();
                    ArrayList<String> storeList= (ArrayList<String>) ois.readObject();
                    marketplaceClient.TwentyFive(reader, writer,ois,oos,storeList);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
                //marketplaceClient.TwentyFive(reader, writer,ois,oos);
            }
        });
    }

    public void TwentyThree(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame23 = new JFrame("Online Marketplace"); // creating new frame23
        JPanel panel9 = new JPanel(); // creating new panel9
        frame23.getContentPane();

        JLabel searchProduct23 = new JLabel("Search Product"); // new jlabel welcome message
        searchProduct23.setForeground(Color.cyan); // welcome message color
        Dimension size = searchProduct23.getPreferredSize();
        searchProduct23.setBounds(160, 10, size.width, size.height); // welcome message position in frame23

        JLabel searchLabel23 = new JLabel("Product Name to search :"); // new jlabel welcome message
        searchLabel23.setForeground(Color.cyan); // welcome message color
        searchLabel23.setBounds(50, 80, 200, size.height); // welcome message position in frame23

        JTextField searchNameText23 = new JTextField();
        searchNameText23.setBounds(202, 78, 100, 20); // welcome message position in frame23


        JButton ok23 = new JButton("Ok");// new jlabel login button
        ok23.setBounds(250, 150, 100, 30); // login button position in frame23

        JButton back23 = new JButton("Back");// new jlabel login button
        back23.setBounds(50, 150, 100, 30); // login button position in frame23

        panel9.setLayout(null);
        panel9.add(searchProduct23);
        panel9.add(searchLabel23);
        panel9.add(searchNameText23);
        panel9.add(ok23);
        panel9.add(back23);
        //panel9.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel9.setBackground(Color.darkGray);
        frame23.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame23.add(panel9);
        frame23.setSize(400, 250);
        frame23.setLocationRelativeTo(null);
        frame23.setVisible(true);

        ok23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame23.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                String pName=searchNameText23.getText();
                try {
                    writer.println("23");
                    writer.println(pName);
                    writer.flush();
                    String result=reader.readLine();
                    if (result.equals("success")){
                        String p_Name=reader.readLine();
                        String p_Price=reader.readLine();
                        marketplaceClient.TwentySeven(reader,writer,ois,oos,p_Name,p_Price);
                    }
                    else {
                        marketplaceClient.TwentyFour(reader,writer,ois,oos);
                    }
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }

    public void TwentyFour(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame24 = new JFrame("Online Marketplace"); // creating new frame24
        JPanel panel24 = new JPanel(); // creating new panel24
        frame24.getContentPane();

        JLabel errorText24 = new JLabel("No Product with matching name, please try again"); // new jlabel welcome message
        errorText24.setForeground(Color.cyan); // welcome message color
        Dimension size = errorText24.getPreferredSize();
        errorText24.setBounds(50, 50, size.width, size.height); // welcome message position in frame24


        JButton ok24 = new JButton("Ok");// new jlabel login button
        ok24.setBounds(250, 120, 100, 30); // login button position in frame24

        panel24.setLayout(null);
        panel24.add(errorText24);
        panel24.add(ok24);
        //panel24.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel24.setBackground(Color.darkGray);
        frame24.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame24.add(panel24);
        frame24.setSize(400, 250);
        frame24.setLocationRelativeTo(null);
        frame24.setVisible(true);

        ok24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame24.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.TwentyThree(reader, writer,ois,oos);
            }
        });
    }

    public void TwentyFive(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos,ArrayList<String> storeList) {
        JFrame frame25 = new JFrame("Online Marketplace"); // creating new frame25
        JPanel panel25 = new JPanel(); // creating new panel25
        frame25.getContentPane();

        JLabel storeLabel25 = new JLabel("Store List :"); // new jlabel welcome message
        storeLabel25.setForeground(Color.cyan); // welcome message color
        Dimension size = storeLabel25.getPreferredSize();
        storeLabel25.setBounds(120, 50, size.width, size.height); // welcome message position in frame25

        JComboBox<String> options25 = new JComboBox();
        //ArrayList<String> arrayList=new ArrayList<>();
        //arrayList.add("Buyer");
        options25.addItem("Buyer");
        options25.addItem("Seller");
        for (int i=0;i<storeList.size();i++){
            options25.addItem(storeList.get(i));
        }
        //options25.addItem(arrayList.get(0));
        size = options25.getPreferredSize();
        options25.setBounds(200, 45, size.width, 30);


        JButton ok25 = new JButton("Ok");// new jlabel login button
        ok25.setBounds(250, 120, 100, 30); // login button position in frame25

        panel25.setLayout(null);
        panel25.add(storeLabel25);
        panel25.add(options25);
        panel25.add(ok25);
        //panel25.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel25.setBackground(Color.darkGray);
        frame25.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame25.add(panel25);
        frame25.setSize(400, 250);
        frame25.setLocationRelativeTo(null);
        frame25.setVisible(true);

        ok25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame25.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                try {
                    writer.println("25");
                    writer.println(storeList.get(options25.getSelectedIndex()));
                    writer.flush();
                    ArrayList<String> productList= (ArrayList<String>) ois.readObject();
                    marketplaceClient.TwentySix(reader, writer,ois,oos,productList);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }

    public void TwentySix(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos,ArrayList<String> productList) {
        JFrame frame26 = new JFrame("Online Marketplace"); // creating new frame26
        JPanel panel26 = new JPanel(); // creating new panel26
        frame26.getContentPane();

        JLabel productLabel26 = new JLabel("Product List :"); // new jlabel welcome message
        productLabel26.setForeground(Color.cyan); // welcome message color
        Dimension size = productLabel26.getPreferredSize();
        productLabel26.setBounds(120, 50, size.width, size.height); // welcome message position in frame26

        JComboBox<String> options26 = new JComboBox();
        //ArrayList<String> arrayList=new ArrayList<>();
        //arrayList.add("Buyer");
        options26.addItem("Buyer");
        options26.addItem("Seller");
        for (int i=0;i<productList.size();i++){
            options26.addItem(productList.get(i));
        }
        //options26.addItem(arrayList.get(0));
        size = options26.getPreferredSize();
        options26.setBounds(210, 45, size.width, 30);


        JButton ok26 = new JButton("Ok");// new jlabel login button
        ok26.setBounds(250, 120, 100, 30); // login button position in frame26

        panel26.setLayout(null);
        panel26.add(productLabel26);
        panel26.add(options26);
        panel26.add(ok26);
        //panel26.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel26.setBackground(Color.darkGray);
        frame26.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame26.add(panel26);
        frame26.setSize(400, 250);
        frame26.setLocationRelativeTo(null);
        frame26.setVisible(true);

        ok26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame26.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                try {
                    writer.println("26");
                    writer.println(productList.get(options26.getSelectedIndex()));
                    writer.flush();
                    String pName=reader.readLine();
                    String pPrice=reader.readLine();
                    marketplaceClient.TwentySeven(reader, writer,ois,oos,pName,pPrice);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }

    public void TwentySeven(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos,String pName,String pPrice) {
        JFrame frame27 = new JFrame("Online Marketplace"); // creating new frame27
        JPanel panel27 = new JPanel(); // creating new panel27
        frame27.getContentPane();

        JLabel productDetails = new JLabel("Product details"); // new jlabel welcome message
        productDetails.setForeground(Color.cyan); // welcome message color
        Dimension size = productDetails.getPreferredSize();
        productDetails.setBounds(150, 10, size.width, size.height); // welcome message position in frame27

        JLabel productName = new JLabel("Product name :"); // new jlabel welcome message
        productName.setForeground(Color.cyan); // welcome message color
        productName.setBounds(50, 80, 200, size.height); // welcome message position in frame27

        JLabel name = new JLabel(pName); // new jlabel welcome message
        name.setForeground(Color.cyan); // welcome message color
        name.setBounds(202, 80, 200, size.height); // welcome message position in frame27

        JLabel productPrice = new JLabel("Product price :"); // new jlabel welcome message
        productPrice.setForeground(Color.cyan); // welcome message color
        productPrice.setBounds(50, 100, 200, size.height); // welcome message position in frame27

        JLabel price = new JLabel(pPrice); // new jlabel welcome message
        price.setForeground(Color.cyan); // welcome message color
        size = price.getPreferredSize();
        price.setBounds(202, 100, size.width, size.height); // welcome message position in frame27


        JButton ok27 = new JButton("Add product");// new jlabel login button
        ok27.setBounds(250, 150, 120, 30); // login button position in frame27

        JButton back27 = new JButton("Start Over");// new jlabel login button
        back27.setBounds(50, 150, 100, 30); // login button position in frame27

        panel27.setLayout(null);
        panel27.add(productDetails);
        panel27.add(productName);
        panel27.add(productPrice);
        panel27.add(price);
        panel27.add(name);
        panel27.add(ok27);
        panel27.add(back27);
        //panel27.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel27.setBackground(Color.darkGray);
        frame27.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame27.add(panel27);
        frame27.setSize(400, 250);
        frame27.setLocationRelativeTo(null);
        frame27.setVisible(true);

        back27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame27.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });

        ok27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame27.dispose();
                MarketplaceClient marketplaceClient=new MarketplaceClient();
                marketplaceClient.Seven(reader,writer,ois,oos);
            }
        });
    }

    public void TwentyEight(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame28 = new JFrame("Online Marketplace"); // creating new frame28
        JPanel panel28 = new JPanel(); // creating new panel28
        frame28.getContentPane();

        JLabel statisticsInfo = new JLabel("Statistics info"); // new jlabel welcome message
        statisticsInfo.setForeground(Color.cyan); // welcome message color
        Dimension size = statisticsInfo.getPreferredSize();
        statisticsInfo.setBounds(150, 10, size.width, size.height); // welcome message position in frame28


        JButton ok28 = new JButton("Ok");// new jlabel login button
        ok28.setBounds(250, 150, 100, 30); // login button position in frame28

        panel28.setLayout(null);
        panel28.add(statisticsInfo);
        panel28.add(ok28);
        //panel28.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel28.setBackground(Color.darkGray);
        frame28.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame28.add(panel28);
        frame28.setSize(400, 250);
        frame28.setLocationRelativeTo(null);
        frame28.setVisible(true);

        ok28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame28.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });
    }

    public void TwentyNine(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame29 = new JFrame("Online Marketplace"); // creating new frame29
        JPanel panel29 = new JPanel(); // creating new panel29
        frame29.getContentPane();

        JLabel purchaseInfo = new JLabel("Purchase history"); // new jlabel welcome message
        purchaseInfo.setForeground(Color.cyan); // welcome message color
        Dimension size = purchaseInfo.getPreferredSize();
        purchaseInfo.setBounds(150, 10, size.width, size.height); // welcome message position in frame29


        JButton ok29 = new JButton("Ok");// new jlabel login button
        ok29.setBounds(250, 150, 100, 30); // login button position in frame29

        panel29.setLayout(null);
        panel29.add(purchaseInfo);
        panel29.add(ok29);
        //panel29.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel29.setBackground(Color.darkGray);
        frame29.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame29.add(panel29);
        frame29.setSize(400, 250);
        frame29.setLocationRelativeTo(null);
        frame29.setVisible(true);

        ok29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame29.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });
    }

    public void ThirtyOne(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame31 = new JFrame("Online Marketplace"); // creating new frame31
        JPanel panel31 = new JPanel(); // creating new panel31
        frame31.getContentPane();

        JLabel cartOptions = new JLabel("Cart Options"); // new jlabel welcome message
        cartOptions.setForeground(Color.cyan); // welcome message color
        Dimension size = cartOptions.getPreferredSize();
        cartOptions.setBounds(170, 10, size.width, size.height); // welcome message position in frame31


        JButton viewCart31 = new JButton("View Cart");// new jlabel login button
        viewCart31.setBounds(50, 70, 100, 60); // login button position in frame31

        JButton buyCart31 = new JButton("Buy Cart");// new jlabel login button
        buyCart31.setBounds(160, 70, 100, 60); // login button position in frame31

        JButton clearCart31 = new JButton("Clear Cart");// new jlabel login button
        clearCart31.setBounds(270, 70, 100, 60); // login button position in frame31


        panel31.setLayout(null);
        panel31.add(cartOptions);
        panel31.add(viewCart31);
        panel31.add(buyCart31);
        panel31.add(clearCart31);
        //panel31.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel31.setBackground(Color.darkGray);
        frame31.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame31.add(panel31);
        frame31.setSize(400, 250);
        frame31.setLocationRelativeTo(null);
        frame31.setVisible(true);
        MarketplaceClient marketplaceClient = new MarketplaceClient();

        viewCart31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame31.dispose();
                //marketplaceClient.ThirtyThree(reader, writer,ois,oos);
                try {
                    writer.println("31");
                    writer.flush();
                    ArrayList<String> viewCart= (ArrayList<String>) ois.readObject();
                    marketplaceClient.ThirtyThree(reader, writer,ois,oos,viewCart);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        buyCart31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame31.dispose();
                marketplaceClient.ThirtyTwo(reader, writer,ois,oos);
            }
        });

        clearCart31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame31.dispose();
                marketplaceClient.ThirtyFour(reader, writer,ois,oos);
            }
        });
    }

    public void ThirtyTwo(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame32 = new JFrame("Online Marketplace"); // creating new frame32
        JPanel panel32 = new JPanel(); // creating new panel32
        frame32.getContentPane();

        JLabel buyCart32 = new JLabel("Buy Cart"); // new jlabel welcome message
        buyCart32.setForeground(Color.cyan); // welcome message color
        Dimension size = buyCart32.getPreferredSize();
        buyCart32.setBounds(170, 10, size.width, size.height); // welcome message position in frame32

        JLabel buyAllProductsInCart32 = new JLabel("Buy all products in cart"); // new jlabel welcome message
        buyAllProductsInCart32.setForeground(Color.cyan); // welcome message color
        buyAllProductsInCart32.setBounds(120, 80, 200, size.height); // welcome message position in frame32


        JButton ok32 = new JButton("Ok");// new jlabel login button
        ok32.setBounds(250, 150, 100, 30); // login button position in frame9

        JButton back32 = new JButton("Back");// new jlabel login button
        back32.setBounds(50, 150, 100, 30); // login button position in frame9

        panel32.setLayout(null);
        panel32.add(buyCart32);
        panel32.add(buyAllProductsInCart32);
        panel32.add(ok32);
        panel32.add(back32);
        //panel32.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel32.setBackground(Color.darkGray);
        frame32.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame32.add(panel32);
        frame32.setSize(400, 250);
        frame32.setLocationRelativeTo(null);
        frame32.setVisible(true);

        ok32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame32.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                //marketplaceClient.Seven(reader, writer,ois,oos);
                try {
                    writer.println("32");
                    writer.flush();
                    marketplaceClient.Seven(reader, writer,ois,oos);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        back32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame32.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });
    }

    public void ThirtyThree(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos,ArrayList<String> viewCart) {
        JFrame frame33 = new JFrame("Online Marketplace"); // creating new frame33
        JPanel panel33 = new JPanel(); // creating new panel33
        frame33.getContentPane();

        JLabel viewCart33 = new JLabel("View Cart :"); // new jlabel welcome message
        viewCart33.setForeground(Color.cyan); // welcome message color
        Dimension size = viewCart33.getPreferredSize();
        viewCart33.setBounds(120, 50, size.width, size.height); // welcome message position in frame33

        JComboBox<String> options33 = new JComboBox();
        //ArrayList<String> arrayList=new ArrayList<>();
        //arrayList.add("Buyer");
        options33.addItem("Buyer");
        options33.addItem("Seller");
        //options33.addItem(arrayList.get(0));
        for (int i=0;i<viewCart.size();i++){
            options33.addItem(viewCart.get(i));
        }
        size = options33.getPreferredSize();
        options33.setBounds(200, 45, size.width, 30);


        JButton ok33 = new JButton("Ok");// new jlabel login button
        ok33.setBounds(250, 120, 100, 30); // login button position in frame33

        JButton back33 = new JButton("Back");// new jlabel login button
        back33.setBounds(50, 120, 100, 30); // login button position in frame9

        panel33.setLayout(null);
        panel33.add(viewCart33);
        panel33.add(options33);
        panel33.add(ok33);
        panel33.add(back33);
        //panel33.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel33.setBackground(Color.darkGray);
        frame33.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame33.add(panel33);
        frame33.setSize(400, 250);
        frame33.setLocationRelativeTo(null);
        frame33.setVisible(true);

        ok33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame33.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });

        back33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame33.dispose();
                MarketplaceClient marketplaceClient=new MarketplaceClient();
                marketplaceClient.Seven(reader,writer,ois,oos);
            }
        });
    }

    public void ThirtyFour(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame34 = new JFrame("Online Marketplace"); // creating new frame34
        JPanel panel34 = new JPanel(); // creating new panel34
        frame34.getContentPane();

        JLabel clearCart34 = new JLabel("Are you sure you want to clear the cart ?"); // new jlabel welcome message
        clearCart34.setForeground(Color.cyan); // welcome message color
        Dimension size = clearCart34.getPreferredSize();
        clearCart34.setBounds(80, 50, size.width, size.height); // welcome message position in frame34


        JButton ok34 = new JButton("Yes");// new jlabel login button
        ok34.setBounds(250, 120, 100, 30); // login button position in frame34

        JButton back34 = new JButton("No");// new jlabel login button
        back34.setBounds(50, 120, 100, 30); // login button position in frame9

        panel34.setLayout(null);
        panel34.add(clearCart34);
        panel34.add(ok34);
        panel34.add(back34);
        //panel34.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel34.setBackground(Color.darkGray);
        frame34.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame34.add(panel34);
        frame34.setSize(400, 250);
        frame34.setLocationRelativeTo(null);
        frame34.setVisible(true);

        ok34.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame34.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                //marketplaceClient.ThirtyFive(reader, writer,ois,oos);
                try {
                    writer.println("34");
                    writer.flush();
                    marketplaceClient.ThirtyFive(reader, writer,ois,oos);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        back34.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame34.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });
    }

    public void ThirtyFive(BufferedReader reader, PrintWriter writer,ObjectInputStream ois,ObjectOutputStream oos) {
        JFrame frame35 = new JFrame("Online Marketplace"); // creating new frame35
        JPanel panel35 = new JPanel(); // creating new panel35
        frame35.getContentPane();

        JLabel cartCleared35 = new JLabel("Cart Cleared"); // new jlabel welcome message
        cartCleared35.setForeground(Color.cyan); // welcome message color
        Dimension size = cartCleared35.getPreferredSize();
        cartCleared35.setBounds(150, 50, size.width, size.height); // welcome message position in frame35


        JButton ok35 = new JButton("Ok");// new jlabel login button
        ok35.setBounds(250, 120, 100, 30); // login button position in frame35

        panel35.setLayout(null);
        panel35.add(cartCleared35);
        panel35.add(ok35);
        //panel35.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel35.setBackground(Color.darkGray);
        frame35.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame35.add(panel35);
        frame35.setSize(400, 250);
        frame35.setLocationRelativeTo(null);
        frame35.setVisible(true);

        ok35.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame35.dispose();
                MarketplaceClient marketplaceClient = new MarketplaceClient();
                marketplaceClient.Seven(reader, writer,ois,oos);
            }
        });
    }
}
