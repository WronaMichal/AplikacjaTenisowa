package pl.michal.wrona.tennisapp.frames;

import pl.michal.wrona.tennisapp.model.SurfaceCourt;
import pl.michal.wrona.tennisapp.service.MainService;
import pl.michal.wrona.tennisapp.utils.WindowUtils;

import javax.swing.*;

public class AddingCourtWindow extends JFrame {
    public static String courtSurface;
    private JButton addingButton;
    private JButton mainPanelButton;
    private JPanel addingCourtPanel;
    private JLabel courtLabel;
    private JLabel errorLabel;
    private JComboBox<String> courtComboBox;

    public AddingCourtWindow(WindowUtils windowUtils, MainService mainService)  {
        addingCourtPanel = new JPanel();
        courtLabel.setBounds(300, 110, 130, 25);


        courtComboBox = new JComboBox<>();
        courtComboBox.addItem(" ");
        for (SurfaceCourt s : SurfaceCourt.values()) {
            courtComboBox.addItem(s.toString());
        }

    }
}
