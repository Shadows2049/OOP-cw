package comp1721.cwk1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.io.FileNotFoundException;


public class CovidChart extends Application {                           //function to draw chart
    @Override
    public void start(Stage stage) {

        NumberAxis xAxis = new NumberAxis(0, 76, 1);    //define xAxis
        xAxis.setLabel("Day of Year");

        NumberAxis yAxis = new NumberAxis   (0, 850, 50);  //define yAxis
        yAxis.setLabel("Active Cases");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("source from ../datafiles/202.csv");

        CovidDataset newCs = new CovidDataset();
        try{newCs.readDailyCases("D:/coursework/cwk1/datafiles/202.csv");}         //read data from a file(this is for testing)
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        int i;
        int days;
        for(i=0;i< newCs.size();++i){
            //days = newCs.caseR[i].getDate();
            series.getData().add(new XYChart.Data(i, newCs.caseR[i].totalCases()));         //get data
            linechart.getData().add(series);
        }

        Scene scene = new Scene(linechart, 800, 600);
        stage.setTitle("Line Chart");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){               //main function to draw chart


        launch(args);
    }
}