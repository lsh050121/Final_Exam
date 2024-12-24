package StudyGroupMatching;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * 파일을 읽어오는 클래스입니다.
 *
 * @author Lee SangHyeok    (lsh050121@naver.com)
 *
 * @created 2024-12-22
 *
 * @changelog
 * <ul>
 *     <li>2024-12-22 : 최초생성</li>
 *     <li>2024-12-23 : writeToFile 메소드 생성</li>
 * </ul>
 */
public class ReadFile {
    public static void populateStudyList(String filePath, JPanel studyListPanel) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get(filePath);
        ArrayList<String> studyGroups = new ArrayList<>(java.nio.file.Files.readAllLines(path));

        for (String infoText : studyGroups) {
            addStudyGroup(infoText, studyListPanel);
        }
    }

    public static void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(content);
            writer.newLine();
        }
    }

    public static void addStudyGroup(String infoText, JPanel studyListPanel) {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        listPanel.setMaximumSize(new Dimension(400, 40));

        JLabel info = new JLabel(infoText);
        info.setPreferredSize(new Dimension(250, 30));
        listPanel.add(info);

        JButton joinButton = new JButton("참가");
        joinButton.setPreferredSize(new Dimension(80, 30));
        listPanel.add(joinButton);

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = ReadFile.joinStudyGroup(infoText, studyListPanel);
                if (success) {
                    JOptionPane.showMessageDialog(null, "참가가 완료되었습니다!", "참가 성공", JOptionPane.INFORMATION_MESSAGE);

                    // studyListPanel 업데이트
                    studyListPanel.removeAll();
                    try {
                        ReadFile.populateStudyList("src/StudyGroupMatching/StudyGroup_List.txt", studyListPanel);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    studyListPanel.revalidate();
                    studyListPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "최대 인원에 도달하여 참가할 수 없습니다.", "참가 실패", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        studyListPanel.add(listPanel);
        studyListPanel.revalidate();
        studyListPanel.repaint();
    }

    public static boolean joinStudyGroup(String groupInfo, JPanel studyListPanel) {
        Path filePath = Paths.get("src/StudyGroupMatching/StudyGroup_List.txt");

        try {
            List<String> allGroups = Files.readAllLines(filePath);
            List<String> updatedGroups = new ArrayList<>();
            boolean updated = false;

            for (String group : allGroups) {
                if (group.equals(groupInfo)) {
                    String[] parts = group.split(" / ");
                    int currentMembers = Integer.parseInt(parts[2].trim());
                    int maxMembers = Integer.parseInt(parts[3].replace("명", "").trim());


                    if (currentMembers >= maxMembers) {
                        return false;
                    }

                    currentMembers++;
                    group = parts[0] + " / " + parts[1] + " / " + currentMembers + " / " + maxMembers + "명";
                    updated = true;
                }
                updatedGroups.add(group);
            }

            if (updated) {
                Files.write(filePath, updatedGroups);
            }

            return updated;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}