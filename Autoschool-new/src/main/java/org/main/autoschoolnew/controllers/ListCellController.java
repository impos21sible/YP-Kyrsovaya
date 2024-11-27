package org.main.autoschoolnew.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.main.autoschoolnew.models.Student;

public class ListCellController {
    @FXML
    private HBox root;
    @FXML
    private Label LabelName;
    @FXML
    private Label LabelInstructor;
    @FXML
    private Label LabelCar;
    @FXML
    private Label LabelCategory;

    public void setData(Student student) {
        LabelName.setText(student.getFirstName() + " " + student.getLastName());
        LabelInstructor.setText("Инструктор: " + (student.getInstructor() != null ? student.getInstructor().getFullName() : "Не назначен"));
        LabelCar.setText("Автомобиль: " + student.getVehicle().getVehicleModel());
        LabelCategory.setText("Категория: " + student.getCategory().getName());
    }
}
