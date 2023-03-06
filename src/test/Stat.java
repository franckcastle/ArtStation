package test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


import services.WorkshopServices;
import utils.MyDB;

public class Stat extends ApplicationFrame {

    public Stat(final String title) throws IOException, SQLException {
        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() throws IOException, SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        MyDB cnx = MyDB.getInstance();

        WorkshopServices Rec = new WorkshopServices();
        List<Integer> list = Rec.getCountByCategory();
        dataset.addValue(list.get(0), "Musique", " ");
        dataset.addValue(list.get(1), "Theatre", " ");
        dataset.addValue(list.get(2), "Dessin", " ");
        dataset.addValue(list.get(0), "f", " ");
        // dataset.addValue(19, "Taux de couverture Incar", " ");
        return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createBarChart3D(" Categories ", // chart title
                " ", // domain axis label
                "  Le nombre de workshops ", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                true // urls
        );

        final CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));

        return chart;
    }

    public static void main(final String[] args) throws IOException, SQLException {
        final Stat demo = new Stat("Statistiques sur les workshops par catégorie");
        demo.pack();
        demo.setVisible(true);
    }
}

