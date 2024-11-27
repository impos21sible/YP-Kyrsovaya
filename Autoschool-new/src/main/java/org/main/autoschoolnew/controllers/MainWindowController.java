package org.main.autoschoolnew.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import org.main.autoschoolnew.models.Student;
import org.main.autoschoolnew.models.Instructor;
import org.main.autoschoolnew.service.StudentService;
import org.main.autoschoolnew.service.InstructorService;
import org.main.autoschoolnew.util.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    private final InstructorService instructorService = new InstructorService();
    private final StudentService studentService = new StudentService();

    @FXML
    private Button BtnBack;
    @FXML
    private ListView<Student> ListViewStudents;
    @FXML
    private ComboBox<Instructor> CmboBoxInstructor;
    @FXML
    private ComboBox<String> ComboboxSort;
    @FXML
    private Label LabelUser;
    @FXML
    private Label LabelInfo;

    @FXML
    void BtnBackAction(javafx.event.ActionEvent event) {
        // Действия по возвращению в главное меню
    }

    @FXML
    void CmboBoxInstructorAction(javafx.event.ActionEvent event) {
        Instructor instructor = CmboBoxInstructor.getValue();
        if (instructor != null) {
            loadStudents(instructor);
        } else {
            loadStudents(null); // Загружаем всех студентов, если не выбран инструктор
        }
    }

    @FXML
    void ComboboxSortAction(javafx.event.ActionEvent event) {
        // Реализация сортировки студентов (например, по имени или категории)
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelUser.setText("Добро пожаловать, " + Manager.currentInstructor.getFirstName());
        List<Instructor> instructorList = instructorService.findAll();
        CmboBoxInstructor.setItems(FXCollections.observableArrayList(instructorList));
        loadStudents(null); // Загружаем всех студентов при инициализации
    }

    public void loadStudents(Instructor instructor) {
        ListViewStudents.getItems().clear();
        List<Student> students = studentService.findAll();
        if (instructor != null) {
            students = students.stream()
                    .filter(student -> student.getInstructor().equals(instructor))
                    .collect(Collectors.toList());
        }

        ListViewStudents.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/student-cell.fxml"));
                    try {
                        HBox root = loader.load();
                        ListCellController controller = loader.getController();
                        controller.setData(student);
                        setGraphic(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ListViewStudents.getItems().addAll(students);
    }
}