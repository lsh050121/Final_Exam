package StudyGroupMatching;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MainFrame extends JFrame {
    private JFrame mainFrame;
    private JLabel title;
    private JButton registerButton;
    private JPanel studyListPanel;


    public MainFrame() {
        //메인프레임
        JFrame mainFrame = createFrame();

        // 타이틀
        JLabel title = createLabel();

        // 스터디그룹리스트
        JPanel studyListPanel = createStudyListPanel();

        // 등록버튼
        JButton registerButton = createRegisterButton();
    }

    private JFrame createFrame() {
        mainFrame = new JFrame("CJU StudyGroup");
        mainFrame.setSize(500, 450);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        return mainFrame;
    }

    private JLabel createLabel() {
        JLabel title = new JLabel("CJU StudyGroup List");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.DARK_GRAY);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(135, 10, 220, 70);
        mainFrame.add(title);
        return title;
    }

    private JPanel createStudyListPanel() {
        studyListPanel = new JPanel();
        studyListPanel.setLayout(new BoxLayout(studyListPanel, BoxLayout.Y_AXIS));
        JScrollPane listScrollPane = new JScrollPane(studyListPanel);
        listScrollPane.setBounds(50, 80, 400, 250);

        // 파일에서 읽어와 리스트 추가
        try {
            ReadFile.populateStudyList("src/StudyGroupMatching/StudyGroup_List.txt", studyListPanel);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "파일을 읽는 중 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mainFrame.add(listScrollPane);
        return studyListPanel;
    }

    private JButton createRegisterButton() {
        registerButton = new JButton("등록");
        registerButton.setBounds(200, 350, 100, 30);
        mainFrame.add(registerButton);
        return registerButton;
    }
}