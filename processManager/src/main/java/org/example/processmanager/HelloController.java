package org.example.processmanager;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.processmanager.pojo.Service;
import org.example.processmanager.utils.ProcessManager;
import org.example.processmanager.utils.ServiceManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.example.processmanager.utils.ServiceManager.serviceList;
/**
 * @author cyl
 */
public class HelloController implements Initializable {

    @FXML
    TableColumn<Service,String> nameColumn;
    @FXML
    TableColumn<Service,String> statusColumn;

    @FXML
    TableView<Service> serviceTable;

    @FXML
    public void startService(ActionEvent actionEvent) throws InterruptedException {
        Service item = serviceTable.getSelectionModel().getSelectedItem();
        ProcessManager.start(item.name);
        Thread.sleep(1000);
        refreshServices(actionEvent);
    }

    @FXML
    public void stopService(ActionEvent actionEvent) throws InterruptedException {
        Service item = serviceTable.getSelectionModel().getSelectedItem();
        ProcessManager.stop(item.name);
        Thread.sleep(1000);
        refreshServices(actionEvent);
    }

    @FXML
    public void refreshServices(ActionEvent actionEvent) {
        serviceTable.getItems().clear();
        ServiceManager.clearList();
        ServiceManager.getAny();
        ObservableList<Service> list= FXCollections.observableArrayList(serviceList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Service,String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Service,String>("status"));
        serviceTable.setItems(list);
    }

    static {
        ServiceManager.getAny();
    }
    ObservableList<Service> list= FXCollections.observableArrayList(serviceList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Service,String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Service,String>("status"));
        serviceTable.setItems(list);
    }
}