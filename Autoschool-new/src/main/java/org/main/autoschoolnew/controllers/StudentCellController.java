package org.main.autoschoolnew.controllers;  // Убедитесь, что пакет называется правильно

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.main.autoschoolnew.models.Student;

public class StudentCellController {

    @FXML
    private ImageView ImageViewPhoto;
    @FXML
    private Label LabelName;
    @FXML
    private Label LabelInstructor;
    @FXML
    private Label LabelCategory;
    @FXML
    private Label LabelStatus;

    // Метод для установки данных в элементы интерфейса
    public void setData(Student student) {
        // Устанавливаем имя студента
        LabelName.setText(student.getFirstName() + " " + student.getLastName());

        // Устанавливаем информацию об инструкторе (если применимо)
        if (student.getInstructor() != null) {
            LabelInstructor.setText("Инструктор: " + student.getInstructor().getFullName());
        } else {
            LabelInstructor.setText("Инструктор: Не назначен");
        }

        // Устанавливаем категорию
        LabelCategory.setText("Категория: " + student.getCategory().getName());

        // Устанавливаем статус студента
        LabelStatus.setText("Статус: " + student.getStatus());  // Предполагается, что в модели Student есть метод getStatus()

        // Устанавливаем изображение (если оно есть)
        // Пример: ImageViewPhoto.setImage(new Image("path/to/photo.jpg"));
    }
}
