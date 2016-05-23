import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

class Frame extends JFrame {
    //private JLabel display = new JLabel();
    private JTextArea textArea = new JTextArea(20, 80);
    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 900;
    private JPanel ActionsPossibleToMake;
    private JLabel userNote = new JLabel("Opcje Użytkownika");
    private JLabel adminNote = new JLabel("Opcje Administratora");
    private JButton action1a = new JButton("Dostępne płyty");  //1 - opcje usera 2 - opcje admina
    private JButton action1b = new JButton("Oddaj płytę");
    private JButton action1c = new JButton("Wypożycz płytę");
    private JButton action2a = new JButton("Kup płytę");
    private JButton action2b = new JButton("Sprzedaj płytę");
    private JButton action2c = new JButton("Dodaj użytkownika");
    private JButton action1d = new JButton("Baza Usera");
    private JButton action2d = new JButton("Pełne baza");
    private JButton refresh = new JButton("Odśwież");

    private JButton button;
    private static int check;
    private JTextField textowepole;
    private JPasswordField pw;
    public static final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));
    public Session session = HibernateUtil.getSessionFactory().openSession();

    private static User loggedUser = null;

    private User getTempUser() {
        List user = showAllUser();
        return (User) user.get(1);
    }

    public Frame() {

        //JScrollPane suwak = new JScrollPane();
        //add(suwak, BorderLayout.CENTER);

        add(refresh, BorderLayout.SOUTH);
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame.this.setPrivilenge();
            }
        });

        // TWORZE WYBOR USERA  PANEL #2 PRAWY
        JPanel AccessMode = new JPanel();
        AccessMode.setLayout(new GridLayout(3, 1));
        Border etcheddown = BorderFactory.createEtchedBorder();
        Border titleWest = BorderFactory.createTitledBorder(etcheddown, "WYBOR USERA");
        AccessMode.setBorder(titleWest);
        ButtonGroup group = new ButtonGroup(); // tworze 2 radiobuttony

        JRadioButton userButton = new JRadioButton("User", false);
        JRadioButton unsignedUserButton = new JRadioButton("Niezalogowany", true);
        group.add(unsignedUserButton);
        group.add(userButton);


        Font f = new Font("Arial", Font.BOLD, 20);
        textArea.setFont(f);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText("ZAGADNIENIA ZARZĄDZANIA\n" +
                "PROJEKT: WYPOŻYCZALNIA PLYT\n" +
                "ADAM GIŻA\n");

        add(scrollPane, BorderLayout.CENTER);

        Label note = new Label("ZALOGUJ SIĘ JAKO");
        AccessMode.add(note);
        AccessMode.add(unsignedUserButton);
        AccessMode.add(userButton);


        add(AccessMode, BorderLayout.WEST);
        setVisible(true);        //pack();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // TWORZE WYBOR USERA  PANEL #5 LEWY
        ActionsPossibleToMake = new JPanel();
        Border titleEAST = BorderFactory.createTitledBorder(etcheddown, "WYBOR AKCJI");
        ActionsPossibleToMake.setBorder(titleEAST);
        insert(new Component[]{userNote, adminNote, action1a, action2a, action1b, action2b, action1c, action2c, action1d, action2d});

        if (check == 0)
            setDefaultPrivilenge();
        if (check == 1)
            setAdminPrivilenge();
        if (check == 2)
            setUserPrivilenge();

        // dodanie akcji
        action1a.addActionListener(button1a);
        action1b.addActionListener(button1b);
        action1c.addActionListener(button1c);
        action1d.addActionListener(button1d);
        action2a.addActionListener(button2a);
        action2b.addActionListener(button2b);
        action2c.addActionListener(button2c);
        action2d.addActionListener(button2d);


        userButton.addActionListener(listener2);
        unsignedUserButton.addActionListener(listener3);
        setVisible(true);        //pack();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        pack();
    }

    public Frame(String b) {
        button = new JButton("ZALOGUJ");               // BUTTON logowania
        textowepole = new JTextField("admin", 20);    // POLE TEKSTOWE
        pw = new JPasswordField("admin", 2);     // POLE HASLO

        //PANEL #1 GORNY
        JPanel LoginPanel = new JPanel();

        // dodaje obramowanie
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Panel logowania");
        LoginPanel.setBorder(titled);
        LoginPanel.setLayout(new GridLayout(3, 2));

        //dodanie przyciskow do panelu logowania
        LoginPanel.add(new JLabel("Nazwa użytkownika", JLabel.RIGHT));
        LoginPanel.add(textowepole);
        LoginPanel.add(new JLabel("Haslo", JLabel.RIGHT));
        LoginPanel.add(pw);
        LoginPanel.add(new JLabel("Zalogować?", JLabel.RIGHT));
        LoginPanel.add(button);
        add(LoginPanel, BorderLayout.NORTH);
        button.addActionListener(listenerlogowania);


        // setPrivilenge();
        setVisible(true);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        pack();
    }


    void insert(Component[] a) {
        ActionsPossibleToMake.setLayout(new GridLayout(5, 2));
        for (Object i : a) {
            ActionsPossibleToMake.add((Component) i);
        }
        add(ActionsPossibleToMake, BorderLayout.EAST);
    }


    // WAZNE INFO //

    ActionListener button1a = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Dostepne plyty", "Dostepne plyty", JOptionPane.INFORMATION_MESSAGE);
            //textArea.setText("POKAZ PLYTY");
            List<CD> cds = avalibleCD();
            String formatedString = cds.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim();
            textArea.setText(formatedString);
        }

    };

    ActionListener button1b = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Oddaj plyte", "NIEUDANE", JOptionPane.ERROR_MESSAGE);
            // textArea.setText("ODDAJ PLYTY");
            withdrawCD();

        }
    };

    ActionListener button1c = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // JOptionPane.showMessageDialog(null, "Wypozycz plyte", "NIEUDANE", JOptionPane.ERROR_MESSAGE);
            //textArea.setText("POZYCZ PLYTY");
            borrowCD();
        }
    };

    ActionListener button1d = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            //textArea.setText("POKAZ HISTORIE");
            List<Order> orders = OrderAll();
            String formatedString = orders.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .replace("User=   ", "")
                    .replace("Tytuł=  ", "");
            // .trim();
            textArea.setText(formatedString);
        }
    };

    ActionListener button2a = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            buyCD();
        }
    };

    ActionListener button2b = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sellCD();
        }
    };

    ActionListener button2c = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addNewUser();
        }
    };

    ActionListener button2d = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("Pokaz pelna baze plyt i userow");
            List<User> sers = showAllUser();
            List<CD> cds = showAllCd();
            List<Order> orders = showAllOrder();
            String formatedString1 = sers.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "");  //remove the left bracket
            //.trim();
            String formatedString2 = cds.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "");  //remove the left bracket
            //.trim();
            String formatedString3 = orders.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .replace("User=   ", "")
                    .replace("Tytuł=  ", "");
            //.trim();
            textArea.setText(formatedString1 + '\n' + formatedString2 + '\n' + formatedString3 + '\n');
        }
    };


    // PO WAZNYM INFO //

    ActionListener listener2 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {    // USER

            EventQueue.invokeLater(new Runnable() {
                                       public void run() {
                                           JFrame frame2 = new Frame("Log");
                                           frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                           frame2.setVisible(true);
                                           frame2.addWindowListener(new WindowAdapter() {
                                               @Override
                                               public void windowClosed(WindowEvent e) {  // uzywam dispose
                                                   super.windowClosing(e);
                                                   setPrivilenge();
                                               }
                                           });
                                       }
                                   }
            );
        }
    };

    ActionListener listener3 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            check = 0;
            setDefaultPrivilenge();
        }
    };
//if (textowepole.getText().toLowerCase().equals(users.get(0).getUsername())

    ActionListener listenerlogowania = new ActionListener() {
        public void actionPerformed(ActionEvent e) { //ADMIN
            List<User> users = showAllUser();
            String username = textowepole.getText().toLowerCase();
            String password = new String(pw.getPassword()).toLowerCase();
            List<User> usersMatching = session.createCriteria(User.class)
                    .add(Restrictions.and(
                            Restrictions.eq("username", username),
                            Restrictions.eq("password", password))
                    ).list();

            System.err.println(usersMatching);
            if (usersMatching.size() == 0) {
                JOptionPane.showMessageDialog(null, "NIEUDANE", "NIEUDANE", JOptionPane.ERROR_MESSAGE);
                check = 0;
                System.out.println("Check: " + check);
                loggedUser = null;
            } else {
                loggedUser = usersMatching.get(0);
                if (loggedUser.getId() == 1) {
                    JOptionPane.showMessageDialog(null, "WITAJ ADMINISTRATORZE", "UDANE LOGOWANIE", JOptionPane.INFORMATION_MESSAGE);
                    check = 1;
                    System.out.println("Check: " + check);
                    //user = users.get(0);
                    Frame.this.setAdminPrivilenge();
                } else {
                    JOptionPane.showMessageDialog(null, "WITAJ USERZE", "UDANE LOGOWANIE", JOptionPane.INFORMATION_MESSAGE);
                    Frame.this.getDefaultCloseOperation();
                    check = 2;
                    System.out.println("Check: " + check);
                    Frame.this.setUserPrivilenge();
                }
            }


            // if (textowepole.getText().toLowerCase().equals(users.get(0).getUsername()) && new String(pw.getPassword()).toLowerCase().equals(users.get(0).getPassword())) {

/*
            } else if (textowepole.getText().toLowerCase().equals("user") && new String(pw.getPassword()).toLowerCase().equals("user")) {
                JOptionPane.showMessageDialog(null, "WITAJ USERZE", "UDANE LOGOWANIE", JOptionPane.INFORMATION_MESSAGE);
                Frame.this.getDefaultCloseOperation();
                check = 2;
                System.out.println("Check: " + check);
                Frame.this.setUserPrivilenge();
            } else {
                JOptionPane.showMessageDialog(null, "NIEUDANE", "NIEUDANE", JOptionPane.ERROR_MESSAGE);
                check = 0;
                System.out.println("Check: " + check);
            }
            */
            Frame.this.dispose();
        }
    };

    void setAdminPrivilenge() {
        System.out.println("PRIVILENGE ADMIN Check: " + check);
        this.action1b.setEnabled(true);
        this.action1c.setEnabled(true);
        this.action1d.setEnabled(true);
        this.action2a.setVisible(true);
        this.action2b.setVisible(true);
        this.action2c.setVisible(true);
        this.action2d.setVisible(true);
        this.adminNote.setVisible(true);
    }

    void setUserPrivilenge() {
        System.out.println("PRIVILENGE USER Check: " + check);
        this.action1b.setEnabled(true);
        this.action1c.setEnabled(true);
        this.action1d.setEnabled(true);
        this.action2a.setVisible(false);
        this.action2b.setVisible(false);
        this.action2c.setVisible(false);
        this.action2d.setVisible(false);
        this.adminNote.setVisible(false);
    }

    void setDefaultPrivilenge() {
        System.out.println("PRIVILENGE UNSIGNED Check: " + check);
        this.action1b.setEnabled(false);
        this.action1c.setEnabled(false);
        this.action1d.setEnabled(false);
        this.action2a.setVisible(false);
        this.action2b.setVisible(false);
        this.action2c.setVisible(false);
        this.action2d.setVisible(false);
        this.adminNote.setVisible(false);
    }

    private void setPrivilenge() {
        if (check == 0)
            setDefaultPrivilenge();
        if (check == 1)
            setAdminPrivilenge();
        if (check == 2)
            setUserPrivilenge();
    }

    @SuppressWarnings("unchecked")
    private List<User> showAllUser() {
        List<User> sers = (List<User>) session.createQuery("from User").list();
        return sers;
    }

    private List<CD> avalibleCD() {    // perfect (1)
        List<CD> cds = (List<CD>) session.createCriteria(CD.class)
                .add(Restrictions.and(
                        Restrictions.eq("status", "AVALIBLE")
                )).list();
        return cds;
    }

    private List<CD> showAllCd() {
        List<CD> cds = (List<CD>) session.createQuery("from CD").list();
        return cds;
    }

    private List<Order> showAllOrder() {
        List<Order> orders = (List<Order>) session.createQuery("from Order").list();
        return orders;
    }

    private List<Order> OrderAll() {
        List<Order> orders = (List<Order>) session.createCriteria(Order.class)
                .add(Restrictions.and(
                        Restrictions.eq("user.id", loggedUser.getId())
                )).list();
        return orders;
    }

    private void buyCD() {   // perfect (5)
        List<CD> cds = (List<CD>) session.createQuery("from CD").list();
        CD cd = new CD();
        String nazwisko, tytul;
        nazwisko = JOptionPane.showInputDialog("Podaj nazwisko:");
        tytul = JOptionPane.showInputDialog("Podaj tytul:");
        //System.out.println(nazwisko);
        //System.out.println(tytul);

        if ((nazwisko == null) || (tytul == null) || (nazwisko.equals("")) || (tytul.equals(""))) {
            JOptionPane.showMessageDialog(this, "Cancelled");
        } else {
            JOptionPane.showMessageDialog(this, "add");
            cd.setAuthor(nazwisko);
            cd.setTitle(tytul);
            cd.setStatus("AVALIBLE");
            cd.setPrice(13);
            session.beginTransaction();
            Integer id = (Integer) session.save(cd);
            session.getTransaction().commit();
        }
    }

    private void sellCD() {   // perfect (6)
        List<CD> cds = (List<CD>) session.createQuery("from CD").list();
        ListIterator<CD> it = cds.listIterator();

        String tytul;
        CD position = null;

        tytul = JOptionPane.showInputDialog("Podaj tytul:");


        while (it.hasNext()) {
            CD e = it.next();
            if (e.getTitle().equals(tytul)) {
                position = e;
                JOptionPane.showMessageDialog(this, "Remove");
            }
        }
        if (tytul == null || tytul.equals(""))
            JOptionPane.showMessageDialog(this, "Canceled");
        else if (position == null) {
            JOptionPane.showMessageDialog(this, "No such an element in database");
        } else {
            session.beginTransaction();
            session.delete(position);
            session.getTransaction().commit();
        }
    }

    private void borrowCD() {    // DOPISAC KLUCZE OBCE
        List<CD> cds = (List<CD>) session.createQuery("from CD").list();
        ListIterator<CD> it = cds.listIterator();

        CD position = null;
        String tytul;
        tytul = JOptionPane.showInputDialog("Podaj tytul pozycji ktora chcesz wypozyczyc:");

        while (it.hasNext()) {
            CD e = it.next();
            if (e.getTitle().equals(tytul)) {
                position = e;
            }
        }
        if (tytul == null || tytul.equals(""))
            JOptionPane.showMessageDialog(this, "Canceled");
        else if (position == null) {
            JOptionPane.showMessageDialog(this, "No such an element in database");
        } else if (position != null && position.getStatus().equals("UNAVALIBLE"))
            JOptionPane.showMessageDialog(this, "Position unavalible");
        else {
            Order order = new Order();
            order.setCd(position);
            order.setUser(loggedUser);
            position.setStatus("UNAVALIBLE");
            session.beginTransaction();
            session.update(position);
            session.save(order);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Borrow");
        }
    }


    private void withdrawCD() {
        List<CD> cds = (List<CD>) session.createQuery("from CD").list();
        ListIterator<CD> it = cds.listIterator();

        CD position = null;
        String tytul;
        tytul = JOptionPane.showInputDialog("Podaj tytul pozycji ktora chcesz oddać:");

        while (it.hasNext()) {
            CD e = it.next();
            if (e.getTitle().equals(tytul)) {
                position = e;
            }
        }
        if (tytul == null || tytul.equals(""))
            JOptionPane.showMessageDialog(this, "Canceled");
        else if (position == null) {
            JOptionPane.showMessageDialog(this, "No such an element in database");
        } else if (position != null && position.getStatus().equals("AVALIBLE"))
            JOptionPane.showMessageDialog(this, "Position not borrowed");
        else {
            position.setStatus("AVALIBLE");
            session.beginTransaction();
            session.update(position);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "No longer Borrow");
        }
    }


    private void addNewUser() {  // perfect (7)
        User user = new User();
        String nazwisko, haslo;

        nazwisko = JOptionPane.showInputDialog("Podaj nazwisko:");
        haslo = JOptionPane.showInputDialog("Podaj haslo:");

        if ((nazwisko == null) || (haslo == null) || (nazwisko.equals("")) || (haslo.equals("")))
            JOptionPane.showMessageDialog(this, "Cancelled");
        else {
            JOptionPane.showMessageDialog(this, "add");
            user.setUsername(nazwisko);
            user.setPassword(haslo);
            session.beginTransaction();
            Integer id = (Integer) session.save(user);
            session.getTransaction().commit();
        }
    }
}


