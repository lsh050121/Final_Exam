package StudyGroupMatching;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReadFile {
    public static void populateStudyList(String filePath, JPanel studyListPanel) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get(filePath);
        ArrayList<String> studyGroups = new ArrayList<>(java.nio.file.Files.readAllLines(path));

        for (String infoText : studyGroups) {
            addStudyGroup(infoText, studyListPanel);
        }
    }

    private static void addStudyGroup(String infoText, JPanel studyListPanel) {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        listPanel.setMaximumSize(new Dimension(400, 40));

        JLabel info = new JLabel(infoText);
        info.setPreferredSize(new Dimension(250, 30));
        listPanel.add(info);

        JButton joinButton = new JButton("참가");
        joinButton.setPreferredSize(new Dimension(80, 30));
        listPanel.add(joinButton);

        studyListPanel.add(listPanel);
        studyListPanel.revalidate();
        studyListPanel.repaint();
    }
}