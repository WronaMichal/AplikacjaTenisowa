package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.Court;
import pl.michal.wrona.tennisapp.repository.CourtsRepository;
import pl.michal.wrona.tennisapp.repository.ReservationsFileRepository;
import pl.michal.wrona.tennisapp.repository.ReservationsRepository;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;

public class AvailableSpots extends JFrame {
    private JTable courtsTable;
    private JButton selectCourtButton;
    private JButton returnButton;
    private JScrollPane scrollPanel;
    private JPanel courtPanel;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String genre;
    private Court selectedCourt;

    public AvailableSpots(WindowUtils windowUtils, MainService mainService, LocalDateTime dateFrom, LocalDateTime dateTo, String courtType) {
        List<Court> courtsByCriteria =
                mainService.getCourtsByCriteria(dateFrom, dateTo, courtType);
        // filtruje po rezerwacjach w systemie aby zobaczyć czy rezerwacja jaka chce user istnieje na naszej liście
        courtPanel = new JPanel();
        scrollPanel = new JScrollPane();
        selectCourtButton = new JButton("Wybierz kort");
        selectCourtButton.setEnabled(false);
        selectCourtButton.addActionListener(e -> {
                    mainService.saveReservation(mainService.addReservation(dateFrom, dateTo, mainService.getActiveUser(), selectedCourt));
                    JFrame selectCourtFrame = new MainPanelWindow(windowUtils, mainService);
                    selectCourtFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    selectCourtFrame.setVisible(true);
                    dispose();
                }
        );


        int width = 1000;
        int height = courtsByCriteria.size() * 100;
        setBounds(windowUtils.getX(width), windowUtils.getY(height), width, height);
        String[] columns = {"Nr kortu", "Rodzaj nawierzchni", "Data rezerwacji", "Godzina rozpoczęcia", "Godzina zakończenia", "Cena za godzinę", "Cena końcowa"};
        Duration durationTime = Duration.between(dateFrom, dateTo);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");
        String[][] data = new String[courtsByCriteria.size()][columns.length];
        for (int i = 0; i < courtsByCriteria.size(); i++) {
            data[i][0] = String.valueOf(courtsByCriteria.get(i).getId());
            data[i][1] = String.valueOf(courtsByCriteria.get(i).getSurface());
            data[i][2] = dateFrom.format(formatterDate);
            data[i][3] = dateFrom.format(formatterHour);
            data[i][4] = dateTo.format(formatterHour);
            data[i][5] = String.valueOf(courtsByCriteria.get(i).getPricePerHour());
            data[i][6] = String.valueOf(courtsByCriteria.get(i).getPricePerHour() * durationTime.toHours());
        }


        courtsTable = new JTable(data, columns) {
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        courtsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnModel model = courtsTable.getColumnModel();
        model.getColumn(0).setWidth(100);
        courtsTable.getSelectionModel().addListSelectionListener(event -> {
            if (courtsTable.getSelectedRow() > -1) {
                selectCourtButton.setEnabled(true);
                selectedCourt = courtsByCriteria.get(courtsTable.getSelectedRow());
            }
        });

        returnButton = new JButton("Cofnij");
        returnButton.setBounds(150, 200, 200, 70);
        returnButton.addActionListener(
                e -> {
                    dispose();
                    JFrame loginFrame = new ReservationsSpecificationWindow(windowUtils, mainService);
                    loginFrame.setVisible(true);
                    loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
        );
        scrollPanel.setViewportView(courtsTable);
        scrollPanel.getViewport().setPreferredSize(new Dimension(750, 50 * courtsByCriteria.size()));
        courtPanel.add(scrollPanel);
        courtPanel.add(selectCourtButton);
        courtPanel.add(returnButton);
        add(courtPanel);
        setBounds(500, 200, 800, 400);
        setTitle("Dostępne terminy");
    }


}
