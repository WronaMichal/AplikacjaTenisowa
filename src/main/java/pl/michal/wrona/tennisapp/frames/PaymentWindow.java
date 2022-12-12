package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;

public class PaymentWindow extends JFrame {
    private JTable paymentTable;
    private JButton returnButton;
    private JScrollPane scrollPanel;
    private JPanel paymentPanel;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public PaymentWindow(WindowUtils windowUtils, MainService mainService) {
        List<Reservation> reservations = mainService.getUserReservations(mainService.getActiveUser());
        paymentPanel = new JPanel();
        scrollPanel = new JScrollPane();

        int width = 1000;
        int height = reservations.size() * 100;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        String[] columns = {"Nr kortu", "Rodzaj nawierzchni", "Płatność"};
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");
        String[][] data = new String[reservations.size()][columns.length];
        for (int i = 0; i < reservations.size(); i++) {
            data[i][0] = String.valueOf(reservations.get(i).getCourt().getId());
            data[i][1] = String.valueOf(reservations.get(i).getCourt().getSurface());
            data[i][2] = String.valueOf(Duration.between(reservations.get(i).getFrom(),reservations.get(i).getTo()).toHours()
                    *reservations.get(i).getCourt().getPricePerHour());
        }


        paymentTable = new JTable(data, columns) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        paymentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel model = paymentTable.getColumnModel();
        model.getColumn(0).setWidth(100);
        paymentTable.getSelectionModel();

        returnButton = new JButton("Cofnij");
        returnButton.setBounds(150, 200, 200, 70);
        returnButton.addActionListener(
                e -> {
                    dispose();
                    JFrame MainPanelFrame = new MainPanelWindow(windowUtils, mainService);
                    MainPanelFrame.setVisible(true);
                    MainPanelFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
        );
        scrollPanel.setViewportView(paymentTable);
        scrollPanel.getViewport().setPreferredSize(new Dimension(750, 50 * reservations.size()));
        paymentPanel.add(scrollPanel);
        add(paymentPanel);
        paymentPanel.add(returnButton);
    }

}

