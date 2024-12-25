package StudyGroupMatching;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static StudyGroupMatching.ReadFile.FILE_PATH;

/**
 * CJU 스터디그룹 매칭 시스템의 메인 UI 클래스입니다.
 * 스터디 그룹 목록을 보여주고, 등록, 검색, 및 초기화 버튼을 제공합니다.
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
    private JButton registerButton, searchButton, resetButton;
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

        // 검색 초기화 버튼
        JButton reseJButton = createResetButton();
    }

    /**
     * 메인프레임을 생성하는 메소드입니다.
     * 크기, 종료 동작, 레이아웃, 위치, 크기 조정 가능 여부 등을 설정합니다.
     * 프레임을 화면 중앙에 배치하고 표시합니다.
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
     * @return 생성된 JLabel 객체를 반환합니다.
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
     * 지정된 파일에서 스터디 그룹 데이터를 읽어와 리스트에 추가합니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-20
     * @lastModified 2024-12-22
     *
     * @return 생성된 JPanel 객체 , 스터디그룹리스트 패널을 반환합니다
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
            ReadFile.populateStudyList(FILE_PATH, studyListPanel);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "파일을 읽는 중 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mainFrame.add(listScrollPane);
        return studyListPanel;
    }

    /**
     * studyListPanel에 접근할수 있는 메소드입니다.
     * 현재 존재하는 스터디 그룹 리스트 패널을 반환합니다
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-23
     *
     * @changelog
     * <ul>
     *     <li>2024-12-23 : 최초생성</li>
     * </ul>
     * @return 현재 스터디 그룹 리스트를 포함하고 있는 JPanel 객체를 반환합니다.
     */
    public static JPanel getStudyListPanel() {
        return studyListPanel;
    }

    /**
     * 등록버튼을 생성하는 메소드입니다.
     * 버튼을 클릭 했을 때 등록 다이얼로그가 열리도록 액션 리스너를 추가합니다.
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
        registerButton.setBounds(150, 350, 100, 30);
        mainFrame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterDialog.openRegistrationDialog(mainFrame);
            }
        });
        return registerButton;
    }

    /**
     * 검색 버튼을 생성하는 메소드입니다.
     * 버튼 클릭 시 검색 창이 열리도록 액션 리스너를 추가합니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @return 생성된 JButton 객체 == 검색 버튼을 반환합니다
     *
     * @created 2024-12-24
     *
     * @changelog
     * <ul>
     *     <li>2024-12-24 : 최초생성</li>
     * </ul>
     */
    private JButton createSearchButton() {
        searchButton = new JButton("검색");
        searchButton.setBounds(260, 350, 100, 30);
        mainFrame.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchDialog.openSearchDialog(mainFrame, studyListPanel);
            }
        });

        return searchButton;

    }

    /**
     * 검색 초기화 버튼을 생성하는 메소드입니다.
     * 버튼 클릭시 스터티 그룹 리스트를 초기화하고 파일에서 다시 데이터를 읽어오는 작업을 수행합니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-24
     *
     * @changelog
     * <ul>
     *     <li>2024-12-24 : 최초 생성</li>
     * </ul>
     *
     * @return 생성된 JButton 객체 == 검색 초기화 버튼을 반환합니다.
     */
    private JButton createResetButton() {
        JButton resetButton = new JButton("검색 초기화");
        resetButton.setBounds(370, 350, 100, 30);
        mainFrame.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // studyListPanel 초기화 후 전체 리스트 다시 로드
                studyListPanel.removeAll();
                try {
                    ReadFile.populateStudyList(FILE_PATH, studyListPanel);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, "리스트를 초기화하는 중 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                studyListPanel.revalidate();
                studyListPanel.repaint();
            }
        });

        return resetButton;
    }
}