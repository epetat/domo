package fr.nom.petat.domo.chart;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import fr.nom.petat.domo.service.TemperatureService;

public class JFreeChartTemperature {
	public static Logger logger = Logger.getLogger(JFreeChartTemperature.class);

    public static OutputStream createCombinedChart(Date pDateDebut, Date pDateFin, OutputStream pOutputStream) throws IOException {
		logger.debug("Début createCombinedChart");
    	TemperatureService temperatureService = new TemperatureService();
    	XYDataset dataset = new TimeSeriesCollection();
    	try {
    	dataset = temperatureService.getTemperatures(pDateDebut, pDateFin);
    	} catch(Exception e){}
    	
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Températures",           // chart title
            "Date",                   // x axis label
            "température °C",         // y axis label
            dataset,                  // data
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//            final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy HH:mm"));

        ChartUtilities.writeChartAsPNG(pOutputStream, chart, 750, 400);
        
		logger.debug("Fin createCombinedChart");
        return pOutputStream;
    }
}
