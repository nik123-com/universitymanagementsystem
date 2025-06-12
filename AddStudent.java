package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class AddStudent extends JFrame implements ActionListener {

    JTextField tfname, tffname, tfaddress, tfphone, tfemail, tfx, tfxii, tfaadhar;
    JLabel labelrollno;
    JDateChooser dcdob;
    JComboBox<String> cbcourse, cbbranch;
    JButton submit, cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddStudent() {

        getContentPane().setBackground(new Color(240, 248, 255));
        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);

        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(280, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        heading.setForeground(new Color(25, 25, 112));
        add(heading);

        // --- Labels and Fields ---
        String[] labels = {
            "Name", "Father's Name", "Roll Number", "Date of Birth",
            "Address", "Phone", "Email Id", "Class X (%)",
            "Class XII (%)", "Aadhar Number", "Course", "Branch"
        };

        Font labelFont = new Font("serif", Font.BOLD, 20);
        Color labelColor = new Color(0, 0, 128);

        // Row 1
        addLabel("Name", 50, 150, labelFont, labelColor);
        tfname = addTextField(200, 150);
        tfname.setToolTipText("Enter full name");

        addLabel("Father's Name", 400, 150, labelFont, labelColor);
        tffname = addTextField(600, 150);
        tffname.setToolTipText("Enter father's name");

        addLabel("Roll Number", 50, 200, labelFont, labelColor);
        labelrollno = new JLabel("1533" + first4);
        labelrollno.setBounds(200, 200, 200, 30);
        labelrollno.setFont(labelFont);
        labelrollno.setForeground(Color.BLUE);
        add(labelrollno);

        addLabel("Date of Birth", 400, 200, labelFont, labelColor);
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        dcdob.setBackground(Color.WHITE);
        add(dcdob);

        addLabel("Address", 50, 250, labelFont, labelColor);
        tfaddress = addTextField(200, 250);
        tfaddress.setToolTipText("Enter full address");

        addLabel("Phone", 400, 250, labelFont, labelColor);
        tfphone = addTextField(600, 250);
        tfphone.setToolTipText("Enter 10-digit phone number");

        addLabel("Email Id", 50, 300, labelFont, labelColor);
        tfemail = addTextField(200, 300);
        tfemail.setToolTipText("Enter valid email");

        addLabel("Class X (%)", 400, 300, labelFont, labelColor);
        tfx = addTextField(600, 300);

        addLabel("Class XII (%)", 50, 350, labelFont, labelColor);
        tfxii = addTextField(200, 350);

        addLabel("Aadhar Number", 400, 350, labelFont, labelColor);
        tfaadhar = addTextField(600, 350);
        tfaadhar.setToolTipText("Enter 12-digit Aadhar number");

        addLabel("Course", 50, 400, labelFont, labelColor);
        String course[] = {"B.Tech", "BBA", "BCA", "Bsc", "Msc", "MBA", "MCA", "MCom", "MA", "BA"};
        cbcourse = new JComboBox<>(course);
        cbcourse.setBounds(200, 400, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);

        addLabel("Branch", 400, 400, labelFont, labelColor);
        String branch[] = {"Computer Science", "Electronics", "Mechanical", "Civil", "IT", "Sales", "Management"};
        cbbranch = new JComboBox<>(branch);
        cbbranch.setBounds(600, 400, 150, 30);
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);

        // --- Buttons ---
        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        styleButton(submit);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        styleButton(cancel);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String rollno = labelrollno.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String x = tfx.getText();
            String xii = tfxii.getText();
            String aadhar = tfaadhar.getText();
            String course = (String) cbcourse.getSelectedItem();
            String branch = (String) cbbranch.getSelectedItem();

            if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || address.isEmpty() || phone.isEmpty()
                    || email.isEmpty() || x.isEmpty() || xii.isEmpty() || aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
                return;
            }

            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Phone must be 10 digits.");
                return;
            }

            if (!aadhar.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "Aadhar must be 12 digits.");
                return;
            }

            if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
                JOptionPane.showMessageDialog(null, "Enter a valid Email ID.");
                return;
            }

            try {
                String query = "INSERT INTO student VALUES('" + name + "', '" + fname + "', '" + rollno + "', '" + dob + "', '" + address + "', '" + phone + "', '" + email + "', '" + x + "', '" + xii + "', '" + aadhar + "', '" + course + "', '" + branch + "')";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Student Details Inserted Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    private void addLabel(String text, int x, int y, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(font);
        label.setForeground(color);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 150, 30);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(tf);
        return tf;
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(25, 25, 112));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn.setFocusPainted(false);
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
