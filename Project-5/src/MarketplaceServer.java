import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketplaceServer {
    public static void main(String[] args) {
        //User user;
        MarketplaceServer marketplaceServer=new MarketplaceServer();

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
                options.setBounds(205,135,size.width,30);


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
                        frame.dispose();
                        marketplaceServer.Three();
                    }
                });
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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
                        marketplaceServer.Six();
                    }
                });
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
    }


    public void Three(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }


    public void Five(){
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
    }

    public void Six(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Eight();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Nine();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Ten();
            }
        });

        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Eleven();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Twelve();
            }
        });
    }

    public void Seven(){
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
    }

    public void Eight(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Thirteen();
            }
        });


        back8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame8.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }

    public void Nine(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Fourteen();
            }
        });

        back9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame9.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }

    public void Ten(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Sixteen();
            }
        });

        back10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame10.dispose();
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }

    public void Eleven(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }

    public void Twelve(){
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

        ok12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame12.dispose();
            }
        });
    }

    public void Thirteen(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }


    public void Fourteen(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Fifteen();
            }
        });
    }

    public void Fifteen(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }

    public void Sixteen(){
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
                MarketplaceServer marketplaceServer=new MarketplaceServer();
                marketplaceServer.Six();
            }
        });
    }


}
