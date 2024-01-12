import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BusReservationSystem extends JFrame implements ActionListener {
    JLabel busNameLabel, seatsLabel, nameLabel,passangerLabel, phoneLabel, sourceLabel, destinationLabel, timeLabel, msg;
    // JTextField nameTextField, phoneTextField;
    JComboBox sourceComboBox, destinationComboBox, timeComboBox;
    JComboBox<Integer> passengerComboBox;
    JRadioButton acRadioButton, nonAcRadioButton;
    JButton bookButton, cancelButton;
    public BusReservationSystem() {
        setTitle("Bus Reservation System");
        setSize(500, 400);
        setLayout(null);

        busNameLabel = new JLabel("Sahyadri Bus Service");
        busNameLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        busNameLabel.setBounds(150, 10, 200, 30);
        add(busNameLabel);

        passangerLabel=new JLabel("Number Of Passengers:");
        passangerLabel.setBounds(50, 100, 150, 20);
        add(passangerLabel);
        passengerComboBox = new JComboBox<Integer>();
        passengerComboBox.addItem(1);
        passengerComboBox.addItem(2);
        passengerComboBox.addItem(3);
        passengerComboBox.addItem(4);
        passengerComboBox.addItem(5);
        passengerComboBox.addItem(6);
        passengerComboBox.addItem(7);
        passengerComboBox.addItem(8);
        passengerComboBox.addItem(9);
        passengerComboBox.addItem(10);
        passengerComboBox.setBounds(250, 100, 100, 20);
        add(passengerComboBox);

        sourceLabel = new JLabel("From:");
        sourceLabel.setBounds(50, 130, 100, 20);
        add(sourceLabel);

        String[] sourceList = {"Mangalore", "Bangalore", "Trivandrum", "Mumbai", "Hyderabad"};
        sourceComboBox = new JComboBox(sourceList);
        sourceComboBox.setBounds(250, 130, 100, 20);
        add(sourceComboBox);
        destinationLabel = new JLabel("To:");
        destinationLabel.setBounds(50, 160, 100, 20);
        add(destinationLabel);

        String[] destinationList = {"Bangalore", "Mangalore", "Trivandrum", "Mumbai", "Hyderabad"};
        destinationComboBox = new JComboBox(destinationList);
        destinationComboBox.setBounds(250, 160, 100, 20);
        add(destinationComboBox);

        timeLabel = new JLabel("Time:");
        timeLabel.setBounds(50, 190, 100, 20);
        add(timeLabel);

        String[] timeList = {"9:00AM", "11:00AM", "1:00PM", "3:00PM", "5:00PM", "8:00PM", "11:30PM"};
        timeComboBox = new JComboBox(timeList);
        timeComboBox.setBounds(250, 190, 100, 20);
        add(timeComboBox);
        seatsLabel = new JLabel("Seats:");
        seatsLabel.setBounds(50, 220, 100, 20);
        add(seatsLabel);

        acRadioButton = new JRadioButton("AC");
        acRadioButton.setBounds(250, 220, 50, 20);
        add(acRadioButton);

        nonAcRadioButton = new JRadioButton("Non-AC");
        nonAcRadioButton.setBounds(300, 220, 100, 20);
        add(nonAcRadioButton);

        ButtonGroup group = new ButtonGroup();
        group.add(acRadioButton);
        group.add(nonAcRadioButton);

        bookButton = new JButton("Book");
        bookButton.setBounds(100, 280, 80, 30);
        bookButton.addActionListener(this);
        add(bookButton);
        bookButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 280, 80, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);
        

        msg=new JLabel("");
        msg.setBounds(20,400,250,20);
        add(msg);

        setVisible(true);
    } 
    public void actionPerformed(ActionEvent e) {
        msg.setText("Booking successfull");
        if (e.getSource() == bookButton) {
            
            int numPassengers = (Integer) passengerComboBox.getSelectedItem();
            String source = (String) sourceComboBox.getSelectedItem();
            String destination = (String) destinationComboBox.getSelectedItem();
            String time = (String) timeComboBox.getSelectedItem();
            String busType = acRadioButton.isSelected() ? "AC" : "Non-AC";
        
            int fare = bookSeat(source, destination, time, busType, numPassengers);
            int distance = getDistance(source,time,busType, destination);
            if (fare > 0) {
                JOptionPane.showMessageDialog(this, "Booking successful.\nPer Head:₹"+distance+"\n\nTotal Fare: ₹ " + fare);
            } else {
                // Booking failed.
                JOptionPane.showMessageDialog(this, "Sorry, booking failed. Please try again.");
            }
        }
        else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
        
    }
    public int bookSeat(String source, String destination, String time, String busType, int numPassengers) {
        int distance = getDistance(source,time,busType, destination);
        int fare = calculateFare(distance,numPassengers);
        return fare;
    }
    public int getDistance(String source,String time,String busType, String destination) {
        if((source=="Mangalore"&& destination=="Bangalore") ||(source=="Bangalore"&& destination=="Mangalore"))
        {
            if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
            return 950;
            else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
            return 850;
            else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
            return 900;
            else
            return 650;
        }
        else if((source=="Mangalore"&& destination=="Trivandrum") ||(source=="Trivandrum"&& destination=="Mangalore"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 1450;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 1300;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 1400;
                else
                return 1200;
            }
        }

        else if((source=="Mangalore"&& destination=="Mumbai") ||(source=="Mumbai"&& destination=="Mangalore"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 1550;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 1370;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 1400;
                else
                return 1250;
            }
        }
        
        else if((source=="Mangalore"&& destination=="Hyderabad") ||(source=="Hyderabad"&& destination=="Mangalore"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 1370;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 1200;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 1250;
                else
                return 1190;
            }
        }
        if((source=="Bangalore"&& destination=="Trivandrum") ||(source=="Trivandrum"&& destination=="Bangalore"))
        {
            if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
            return 1040;
            else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
            return 900;
            else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
            return 980;
            else
            return 700;
        }
        
        else if((source=="Bangalore"&& destination=="Mumbai") ||(source=="Mumbai"&& destination=="Bangalore"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 1720;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 1520;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 1600;
                else
                return 1390;
            }
        }
        
        else if((source=="Bangalore"&& destination=="Hyderabad") ||(source=="Hyderabad"&& destination=="Bangalore"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 950;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 750;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 800;
                else
                return 675;
            }
        }
        
        else if((source=="Trivandrum"&& destination=="Mumbai") ||(source=="Mumbai"&& destination=="Trivandrum"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 1845;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 1600;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 1750;
                else
                return 1525;
            }
        }
        else if((source=="Trivandrum"&& destination=="Hyderabad") ||(source=="Hyderabad"&& destination=="Trivandrum"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 2940;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 2550;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 2700;
                else
                return 2200;
            }
        }
        else if((source=="Mumbai"&& destination=="Hyderabad") ||(source=="Hyderabad"&& destination=="Mumbai"))
        {
            {
                if((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="AC")
                return 850;
                else if ((time=="3:00PM"||time=="5:00PM"||time=="8:00PM"||time=="11:30PM")&& busType=="Non-AC")
                return 670;
                else if ((time=="9:00AM"||time=="11:00AM"||time=="1:00PM")&& busType=="AC")
                return 700;
                else
                return 585;
            }
        }
        else
        return 0;
    }
    private int calculateFare(int distance,int numPassengers) {
        return distance * numPassengers;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        new BusReservationSystem();
        }
        });
        }
        }
