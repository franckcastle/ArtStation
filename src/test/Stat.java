package test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entities.Workshop;
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


import services.Reservation_WorkshopServices;
import services.WorkshopServices;
import utils.MyDb;

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
        MyDb cnx = MyDb.getInstance();
        Reservation_WorkshopServices rws = new Reservation_WorkshopServices();
        WorkshopServices ws = new WorkshopServices();

        // Récupérer les catégories de workshops
        List<String> categories = ws.getAllCategories();

        // Pour chaque catégorie, récupérer les workshops et leur nombre de réservations
        for (String category : categories) {
            List<Workshop> workshops = ws.getWorkshopsByCategory(category);
            int maxReservations = 0;
            Workshop mostPopularWorkshop = null;
            for (Workshop workshop : workshops) {
                int reservations = rws.getNombreReservations(workshop.getId());
                if (reservations > maxReservations) {
                    maxReservations = reservations;
                    mostPopularWorkshop = workshop;
                }
            }
            if (mostPopularWorkshop != null) {
                dataset.addValue(maxReservations, category, mostPopularWorkshop.getCategorie());
            }
        }
        return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createBarChart3D(" Categories ", // chart title
                " ", // domain axis label
                "  Le nombre de réservations ", // range axis label
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

