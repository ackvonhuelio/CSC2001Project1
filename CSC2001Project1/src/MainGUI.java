import javax.swing.*;
import java.awt.*;

import java.util.function.Consumer;

public class MainGUI extends JFrame {
    private JTextField idField;
    private JTextField titleField;
    private JTextField mentorField;
    private JTextField departmentField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField locationField;
    private JTextField maxField;
    private JTextArea outputArea;
    // TODO: Create instance variable with type linked list
    private MyLinkedList sessions;

    public MainGUI() {
        // TODO: Create a new LinkList
        this.sessions = new MyLinkedList();

        setTitle("Employee Mentorship and Inclusion Manager");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createGUI();
        setVisible(true);
    }
    // DO NOT CHANGE THIS METHOD!
    // This method creates your GUI of the layout
    private void createGUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8,2,5,5));
        idField = new JTextField();
        titleField = new JTextField();
        mentorField = new JTextField();
        departmentField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        locationField = new JTextField();
        maxField = new JTextField();
        inputPanel.add(new JLabel("Session ID"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Title"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Mentor"));
        inputPanel.add(mentorField);
        inputPanel.add(new JLabel("Department"));
        inputPanel.add(departmentField);
        inputPanel.add(new JLabel("Date"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Time"));
        inputPanel.add(timeField);
        inputPanel.add(new JLabel("Location"));
        inputPanel.add(locationField);
        inputPanel.add(new JLabel("Max Participants"));
        inputPanel.add(maxField);
        add(inputPanel, BorderLayout.NORTH);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add / Update");
        JButton displayButton = new JButton("Display");
        JButton searchButton = new JButton("Search");
        JButton removeButton = new JButton("Remove");
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");
        // add Buttons
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions:
        // Clicking each button in the layout will invoke its corresponding functionality.
        addButton.addActionListener(e -> addSession());
        displayButton.addActionListener(e -> displaySessions());
        searchButton.addActionListener(e -> searchSession());
        removeButton.addActionListener(e -> removeSession());
        registerButton.addActionListener(e -> registerParticipant());
        exitButton.addActionListener(e -> System.exit(0));
    }
    // DO NOT CHANGE THIS METHOD!
    // It clears all fields in the layout
    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        mentorField.setText("");
        departmentField.setText("");
        dateField.setText("");
        timeField.setText("");
        locationField.setText("");
        maxField.setText("");
        // Put the cursor back in the first field
        idField.requestFocus();
    }
    // Add a Session in the correct location first, last, or after based on sessionID
    private void addSession() {
        try {
            int id = Integer.parseInt(idField.getText());
            int max = 99;
            if(!maxField.getText().isEmpty()) {
                try {
                    max = Integer.parseInt(maxField.getText());
                } catch (Exception e) {
                    max = -1;
                }
            }
            if(max <= 0){
                outputArea.setText("Invalid max participant input");
            }else {
                /* TODO: Create Session object and based on id call:
                   - addFirst, or
                   - addLast, or
                   - insertAfter
                */
                /*
                 * TODO make duplicate IDS update
                 * */



                Session cur = sessions.grabById(id);
                if (cur != null) {
                    tryUpdateInput(titleField.getText(), cur::setTopic);
                    tryUpdateInput(mentorField.getText(), cur::setMentor);
                    tryUpdateInput(departmentField.getText(), cur::setDepartment);
                    tryUpdateInput(dateField.getText(), cur::setDate);
                    tryUpdateInput(timeField.getText(), cur::setTime);
                    tryUpdateInput(locationField.getText(), cur::setLocation);
                    if(!maxField.getText().isEmpty()){
                        cur.setMaxParticipants(max);
                    }
                    outputArea.setText("Session Updated Successfully\n\n" + cur.toString());
                } else {
                    Session s = new Session(id, getInput(0), getInput(1), getInput(2), getInput(3), getInput(4), getInput(5), max);
                    sessions = sessions.handleAdd(s);
                    outputArea.setText("Session Added Successfully\n\n" + s.toString());
                }



                // Clear the input fields
                clearFields();
            }
        }
        catch(Exception e) {
            outputArea.setText("Invalid session ID input");
        }
    }

    // Helper functions to make addSession smoother
    private void tryUpdateInput(String value, Consumer<String> setter) {
        if (!value.isEmpty()) {
            setter.accept(value);
        }
    }
    private String getInput(int index){
        String[] things = {
                titleField.getText(),
                mentorField.getText(),
                departmentField.getText(),
                dateField.getText(),
                timeField.getText(),
                locationField.getText()
        };
        String[] defaults = {
                "UNKNOWN",
                "UNKNOWN",
                "UNKNOWN",
                "TBD",
                "TBD",
                "TBD",
        };
        if(things[index].isEmpty()){
            return defaults[index];
        }
        return things[index];
    }
    
    // Display the information in the outputArea in GUI
    private void displaySessions() {
        /* TODO: Print the sessions information in the
                  outputArea
        */
//        Session s = new Session(((int)(Math.random()*100)), "topic", "mentor", "dept.", "date", "time", "location", 7);
//        sessions.handleAdd(s);
        outputArea.setText(sessions.toString());

    }

    // Search based on sessionID or mentor if the fields are not empty
    private void searchSession() {
        /* TODO: 1) Search by sessionID, if field is empty,
         print Session not found, in outputArea.
        2) Search by mentor if the Mentor field is empty,
         print No session found for mentor (mentor name) in outputArea
        3) If both sessionID and mentor are empty,
        print Please enter a Session ID or Mentor name in outputArea
        */

        String mentor = mentorField.getText();
        boolean hasId = false;
        int id = 0;

        if(!idField.getText().isEmpty()) {
            try {
                id = Integer.parseInt(idField.getText());
                hasId = true;
            } catch (Exception e) {
                outputArea.setText("Invalid ID input");
            }
        }

        if(!hasId && mentor.isEmpty()){
            outputArea.setText("Please enter a Session ID or Mentor name");
        }else{
            if(hasId && mentor.isEmpty()){
                outputArea.setText(sessions.searchIByID(id));
            }else if(hasId){
                outputArea.setText(sessions.search(mentor, id, hasId));
            }else{
                outputArea.setText(sessions.searchByMentor(mentor));
            }

        }
        
    }
    
    // delete the session
    private void removeSession() {
    	/* TODO: if session exists remove the session and print Session removed,
    	otherwise print Session not found in outputArea
    	*/

        try {
            int id = Integer.parseInt(idField.getText());
            Session session = sessions.grabById(id);
            if(session == null) {
                outputArea.setText("Could not find session "+id);
            }else{
                outputArea.setText("Session "+id+" removed");
                sessions = sessions.remove(session);
            }



            clearFields();
        }
        catch(Exception e) {
            outputArea.setText("Invalid session ID input");
        }
        
    }

    // registerParticipants call the method in the LinkedList
    private void registerParticipant() {
        /* TODO: It must call the registerParticipant() method of the LinkedList,
        if result is True: print in outputArea, "Participant registered"
        otherwise print, "Registration failed"
        */
        try {
            int id = Integer.parseInt(idField.getText());

            if(sessions.grabById(id) != null) {
                String str = "";
                boolean registered = sessions.registerParticipant(id);
                if(registered){
                    str += "Participant registered";
                }else{
                    str += "Registration failed (Session is full)";
                }
                str += "\n\n";
                str += sessions.search("", id, true);
                outputArea.setText(str);
            }else{
                outputArea.setText("Session " + id + " Not Found\n");
            }
        }
        catch(Exception e) {
            outputArea.setText("Invalid session ID input");
        }
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
