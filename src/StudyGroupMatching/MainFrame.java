package StudyGroupMatching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * CJU 스터디그룹 매칭 시스템 메인프레임 클래스입니다.
 *
 * @author Lee SangHyeok    (lsh050121@naver.com)
 *
 * @created 2024-12-20
 * @lastModified 2024-12-23
 *
 * @changelog
 * <ul>
 *     <li>2024-12-20 : 최초 생성</li>
 *     <li>2024-12-20 : 등록버튼 생성</li>
 *     <li>2024-12-22 : ReadFile 클래스 생성</li>
 *     <li>2024-12-23 : getStudyListPanel 메소드 생성</li>
 * </ul>
 */
public class MainFrame extends JFrame {
    private JFrame mainFrame;
    private JLabel title;
    private JButton registerButton, searchButton;
    private static JPanel studyListPanel;


    public MainFrame() {
        //메인프레임
        JFrame mainFrame = createFrame();

        // 타이틀
        JLabel title = createLabel();

        // 스터디그룹리스트
        JPanel studyListPanel = createStudyListPanel();

        // 등록버튼
        JButton registerButton = createRegisterButton();

        // 검색 버튼
        JButton searchButton = createSearchButton();
    }

    /**
     * 메인프레임을 생성하는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @return 메인프레임을 반환합니다.
     *
     * @created 2024-12-20
     *
     * @changelog
     * <ul>
     *     <li>2024-12-20 : 최초생성</li>
     * </ul>
     */
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

    /**
     * 상단 제목을 보여주는 레이블 생성 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-20
     *
     * @return 제목 레이블을 반환합니다
     *
     * @changelog
     * <ul>
     *     <li>2024-12-20 : 최초생성</li>
     * </ul>
     */
    private JLabel createLabel() {
        JLabel title = new JLabel("CJU StudyGroup List");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.DARK_GRAY);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(135, 10, 220, 70);
        mainFrame.add(title);
        return title;
    }

    /**
     * 스터디그룹 리스트 목록을 보여주는 패널 생성 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-20
     * @lastModified 2024-12-22
     *
     * @return 스터디그룹리스트 패널을 반환합니다
     *
     * @changelog
     * <ul>
     *     <li>2024-12-20 : 최초생성</li>
     *     <li>2024-12-22 : ReadFile 클래스 사용</li>
     * </ul>
     */
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

    /**
     * studyListPanel에 접근할수 있는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-23
     *
     * @changelog
     * <ul>
     *     <li>2024-12-23 : 최초생성</li>
     * </ul>
     * @return studyListPanel 을 반환합니다.
     */
    public static JPanel getStudyListPanel() {
        return studyListPanel;
    }

    /**
     * 등록버튼을 생성하는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-20
     * @lastModified 2024-12-23
     *
     * @return 등록버튼을 반환합니다
     *
     * @changelog
     * <ul>
     *     <li>2024-12-20 : 최초생성</li>
     *     <li>2024-12-23 : 등록 버튼 클릭 시 등록 창 추가</li>
     * </ul>
     */
    private JButton createRegisterButton() {
        registerButton = new JButton("등록");
        registerButton.setBounds(200, 350, 100, 30);
        mainFrame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterDialog.openRegistrationDialog(mainFrame);
            }
        });
        return registerButton;
    }

    private JButton createSearchButton() {
        searchButton = new JButton("검색");
        searchButton.setBounds(310, 350, 100, 30); // 등록 버튼 옆에 위치
        mainFrame.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchDialog.openSearchDialog(mainFrame, studyListPanel);
            }
        });

        return searchButton;

    }
}