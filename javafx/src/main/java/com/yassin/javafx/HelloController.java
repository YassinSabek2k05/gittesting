package com.yassin.javafx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class HelloController {
    @FXML private TableView<ProjectItem> tableView;
    @FXML private TableColumn<ProjectItem, String> nameColumn;
    @FXML private TableColumn<ProjectItem, String> valueColumn;
    @FXML private TableColumn<ProjectItem, String> dateColumn;
    @FXML private TableColumn<ProjectItem, String> progressColumn;
    @FXML private Label statusBar;
    @FXML private Label connectionStatus;
    @FXML private Label timeLabel;

    private final ObservableList<ProjectItem> data = FXCollections.observableArrayList(
        new ProjectItem("E-Commerce Platform", "In Progress", "2024-08-27 14:30", "75%"),
        new ProjectItem("Mobile App Design", "Completed", "2024-08-26 16:45", "100%"),
        new ProjectItem("Database Migration", "In Progress", "2024-08-27 09:15", "45%"),
        new ProjectItem("API Development", "Planning", "2024-08-25 11:20", "25%"),
        new ProjectItem("UI/UX Research", "In Review", "2024-08-26 13:10", "90%"),
        new ProjectItem("Security Audit", "Pending", "2024-08-24 10:00", "10%")
    );

    private Timeline clockTimeline;

    @FXML
    public void initialize() {
        setupTableView();
        setupClock();
        statusBar.setText("Dashboard loaded successfully");
    }

    private void setupTableView() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("lastModified"));
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));
        
        // Add custom cell factories for styling
        valueColumn.setCellFactory(column -> new TableCell<ProjectItem, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    switch (item) {
                        case "Completed" -> setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
                        case "In Progress" -> setStyle("-fx-text-fill: #3498db; -fx-font-weight: bold;");
                        case "In Review" -> setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");
                        case "Pending" -> setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                        default -> setStyle("-fx-text-fill: #95a5a6; -fx-font-weight: bold;");
                    }
                }
            }
        });
        
        tableView.setItems(data);
    }

    private void setupClock() {
        clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
        updateTime();
    }

    private void updateTime() {
        timeLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    // Toolbar Actions
    @FXML
    protected void onNew() {
        Random random = new Random();
        String[] projectTypes = {"Web Application", "Mobile App", "Desktop Software", "API Service", "Data Analysis"};
        String[] statuses = {"Planning", "In Progress", "In Review"};
        
        String name = projectTypes[random.nextInt(projectTypes.length)] + " " + (data.size() + 1);
        String status = statuses[random.nextInt(statuses.length)];
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String progress = (random.nextInt(80) + 10) + "%";
        
        data.add(new ProjectItem(name, status, date, progress));
        statusBar.setText("New project added: " + name);
    }

    @FXML
    protected void onSave() {
        statusBar.setText("All projects saved successfully");
        // Simulate save operation
        Timeline saveAnimation = new Timeline(
            new KeyFrame(Duration.seconds(2), e -> statusBar.setText("Ready"))
        );
        saveAnimation.play();
    }

    @FXML
    protected void onRefresh() {
        tableView.refresh();
        statusBar.setText("Table refreshed at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    // Menu Actions
    @FXML protected void onNewProject() { onNew(); }
    @FXML protected void onOpenProject() { statusBar.setText("Open project dialog would appear"); }
    @FXML protected void onSaveAs() { statusBar.setText("Save As dialog would appear"); }
    @FXML protected void onExit() { System.exit(0); }
    
    @FXML protected void onUndo() { statusBar.setText("Undo action performed"); }
    @FXML protected void onRedo() { statusBar.setText("Redo action performed"); }
    @FXML protected void onCut() { statusBar.setText("Cut action performed"); }
    @FXML protected void onCopy() { statusBar.setText("Copy action performed"); }
    @FXML protected void onPaste() { statusBar.setText("Paste action performed"); }
    
    @FXML protected void onZoomIn() { statusBar.setText("Zoom In applied"); }
    @FXML protected void onZoomOut() { statusBar.setText("Zoom Out applied"); }
    
    @FXML protected void onDocumentation() { statusBar.setText("Opening documentation..."); }
    @FXML protected void onAbout() { 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JavaFX Modern Dashboard");
        alert.setContentText("A beautiful JavaFX application showcasing modern UI design principles.\n\nVersion: 1.0\nDeveloper: Yassin");
        alert.showAndWait();
    }

    // Button Actions
    @FXML protected void onGetStarted() { statusBar.setText("Welcome! Let's get started with your projects."); }
    @FXML protected void onLearnMore() { statusBar.setText("Visit our documentation for more information."); }
    @FXML protected void onShowCharts() { statusBar.setText("Charts view would be displayed here."); }
    @FXML protected void onSettings() { statusBar.setText("Settings panel would open here."); }

    // Data Model
    public static class ProjectItem {
        private final String name;
        private final String status;
        private final String lastModified;
        private final String progress;

        public ProjectItem(String name, String status, String lastModified, String progress) {
            this.name = name;
            this.status = status;
            this.lastModified = lastModified;
            this.progress = progress;
        }

        public String getName() { return name; }
        public String getStatus() { return status; }
        public String getLastModified() { return lastModified; }
        public String getProgress() { return progress; }
    }
}