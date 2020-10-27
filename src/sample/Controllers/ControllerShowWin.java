package sample.Controllers;

import com.sun.webkit.Timer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectionUtil;
import sample.ModelTableCourses;
import sample.ModelTableLessons;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerShowWin implements Initializable {

    Connection conn;

    public ControllerShowWin () {
        conn = ConnectionUtil.connDB();
    }

    @FXML
    private TableView<ModelTableCourses> tableCourses;

    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesId;

    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesTitle;

    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesLength;

    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesDescription;

    @FXML
    private TableView<ModelTableLessons> tableLessons;

    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsId;

    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsTeacher;

    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsCourse;

    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsRoom;

    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsLessonDate;

    ObservableList<ModelTableCourses> observableList = FXCollections.observableArrayList();

    ObservableList<ModelTableLessons> observableList1 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Courses
        String sql = "SELECT * FROM courses";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                observableList.add(new ModelTableCourses(resultSet.getString("id"), resultSet.getString("title"),
                        resultSet.getString("length"), resultSet.getString("description")));

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        tableCoursesId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCoursesTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableCoursesLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        tableCoursesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableCourses.setItems(observableList);

        //Lessons

        String sql1 = "SELECT * FROM lessons";

        try {
            PreparedStatement statement = conn.prepareStatement(sql1);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                observableList1.add(new ModelTableLessons(resultSet.getString("id"), resultSet.getString("teacher"),
                        resultSet.getString("course"), resultSet.getString("room"), resultSet.getString("lesson_date")));
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        tableLessonsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableLessonsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tableLessonsCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        tableLessonsRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        tableLessonsLessonDate.setCellValueFactory(new PropertyValueFactory<>("lessonDate"));

        tableLessons.setItems(observableList1);

    }
}
