
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Student {
    private String name;
    private String studentId;
    private String grade;

    public Student(String name, String studentId, String grade) {
        this.name = name;
        this.studentId = studentId;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return name + " - ID: " + studentId + ", Grade: " + grade;
    }
}

class StudentFrame extends JFrame implements ActionListener {
    private DefaultListModel<String> listModel;
    private JList<String> studentList;
    private java.util.List<Student> students;

    JPanel mainPanel, inputPanel, buttonPanel;
    JTextField nameField, idField, gradeField;
    JButton addButton, deleteButton, updateButton;

    public StudentFrame() {
        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        students = new java.util.ArrayList<>();

        nameField = new JTextField();
        idField = new JTextField();
        gradeField = new JTextField();

        inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        addButton = new JButton("Add Student");
        deleteButton = new JButton("Delete Student");
        updateButton = new JButton("Update Student");

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(studentList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String studentId = idField.getText();
            String grade = gradeField.getText();

            Student student = new Student(name, studentId, grade);
            students.add(student);
            listModel.addElement(student.toString());

            JOptionPane.showMessageDialog(null, "Student added successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            idField.setText("");
            gradeField.setText("");
        }

        if (e.getSource() == deleteButton) {
            int selectedIndex = studentList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
                students.remove(selectedIndex);
                JOptionPane.showMessageDialog(null, "Student deleted successfully", "Deleted",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == updateButton) {
            int selectedIndex = studentList.getSelectedIndex();
            if (selectedIndex != -1) {
                String newName = nameField.getText();
                String newStudentId = idField.getText();
                String newGrade = gradeField.getText();

                // Update the student object
                Student student = students.get(selectedIndex);
                student.setName(newName);
                student.setStudentId(newStudentId);
                student.setGrade(newGrade);

                // Update the list model
                listModel.set(selectedIndex, student.toString());

                JOptionPane.showMessageDialog(null, "Student updated successfully", "Updated",
                        JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                idField.setText("");
                gradeField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a student to update", "No Selection",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

public class StudentManagement extends JFrame {
    public static void main(String[] args) {
        StudentFrame frame = new StudentFrame();
        frame.setVisible(true);
        frame.setTitle("Student Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
