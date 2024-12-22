package StudyGroupMatching;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame{
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
        JPanel studyListPanel = creatStudyListPanel();

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

    private JPanel creatStudyListPanel() {
        studyListPanel = new JPanel();
        studyListPanel.setLayout(new BoxLayout(studyListPanel, BoxLayout.Y_AXIS));
        JScrollPane listScrollPane = new JScrollPane(studyListPanel);
        listScrollPane.setBounds(50, 80, 400, 250);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        listPanel.setMaximumSize(new Dimension(400, 40));

        JLabel info = new JLabel ("자바프로그래밍 / ( 월요일 ) /  1 / 3명 ");
        info.setPreferredSize(new Dimension(250, 30));
        listPanel.add(info);

        JButton joinButton = new JButton("참가");
        joinButton.setPreferredSize(new Dimension(80, 30));
        listPanel.add(joinButton);

        studyListPanel.add(listPanel);

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