package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.Reservation;
import pl.michal.wrona.tennisapp.model.User;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;

public class ReservationsWindow extends JFrame {

    private JTable reservationsTable;
    private JButton returnButton;
    private JScrollPane scrollPanel;
    private JPanel reservationsPanel;

    public ReservationsWindow(WindowUtils windowUtils, MainService mainService) {
        List<Reservation> reservations = mainService.getUserReservations(mainService.getActiveUser());
        reservationsPanel = new JPanel();
        scrollPanel = new JScrollPane();

        int width = 1000;
        int height = reservations.size() * 100;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        String[] columns = {"Nr kortu", "Rodzaj nawierzchni", "Data rezerwacji", "Godzina rozpoczęcia", "Godzina zakończenia"};
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");
        String[][] data = new String[reservations.size()][columns.length];
        for (int i = 0; i < reservations.size(); i++) {
            data[i][0] = String.valueOf(reservations.get(i).getCourt().getId());
            data[i][1] = String.valueOf(reservations.get(i).getCourt().getSurface());
            data[i][2] = reservations.get(i).getFrom().format((formatterDate));
            data[i][3] = reservations.get(i).getFrom().format((formatterHour));
            data[i][4] = reservations.get(i).getTo().format((formatterHour));
        }


        reservationsTable = new JTable(data, columns) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        reservationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel model = reservationsTable.getColumnModel();
        model.getColumn(0).setWidth(100);
        reservationsTable.getSelectionModel();

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
        scrollPanel.setViewportView(reservationsTable);
        scrollPanel.getViewport().setPreferredSize(new Dimension(750, 50 * reservations.size()));
        reservationsPanel.add(scrollPanel);
        add(reservationsPanel);
        reservationsPanel.add(returnButton);
    }

}
