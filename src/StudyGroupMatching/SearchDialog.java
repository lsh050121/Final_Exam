package StudyGroupMatching;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 메인 프레임의 검색 버튼 클릭시 시 실행되는 창을 생성하는 클래스입니다.
 *
 * @author Lee SangHyeok    (lsh050121@naver.com)
 *
 * @created 2024-12-24
 *
 * @changelog
 * <ul>
 *     <li>2024-12-24 : 최초생성</li>
 * </ul>
 */
public class SearchDialog {
    /**
     * 검색창을 생성하는 메소드입니다.
     *
     * @authro Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-24
     *
     * @changelog
     * <ul>
     *     <li>2024-12-24 : 최초생성</li>
     * </ul>
     * @param parent JFrame 객체인 호출한 창, 부모창입니다.
     * @param studyListPanel 결과값을 mainFrame의 studyListPanel에 반환합니다
     */
    public static void openSearchDialog(JFrame parent, JPanel studyListPanel){
        JDialog searchDialog = new JDialog(parent, "요일별 스터디 검색", true);
        searchDialog.setSize(300, 150);
        searchDialog.setLayout(null);

        JLabel dayLabel = new JLabel("요일 선택");
        dayLabel.setBounds(20, 20, 80, 20);
        searchDialog.add(dayLabel);

        String[] days = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
        JComboBox dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds(100, 20, 150, 20);
        searchDialog.add(dayComboBox);

        JButton searchButton = new JButton("검색");
        searchButton.setBounds(100, 60, 100, 30);
        searchDialog.add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String selectedDay = (String) dayComboBox.getSelectedItem();
                List<String> filteredGroups = filterStudyGroupsByDay(selectedDay);

                studyListPanel.removeAll();
                for (String group: filteredGroups) {
                    ReadFile.addStudyGroup(group, studyListPanel);
                }
                studyListPanel.revalidate();
                studyListPanel.repaint();
                searchDialog.dispose();
            }
        });

        searchDialog.setLocationRelativeTo(null);
        searchDialog.setResizable(false);
        searchDialog.setVisible(true);
    }

    /**
     * 주어진 요일에 해당하는 스터디 그룹들을 필터링하여 반환하는 메소드입니다.
     *
     * <p>이 메소드는 "src/StudyGroupMatching/StudyGroup_List.txt" 파일을 읽어
     * 각 스터디 그룹 정보에서 주어진 요일이 포함된 그룹만 필터링하여 리스트로 반환합니다.
     * 만약 파일을 읽는 중 오류가 발생하면 오류 메시지를 포함한 리스트를 반환합니다.</p>
     *
     * @param day 필터링할 요일. 예를 들어, "월요일", "화요일" 등 요일 문자열을 입력합니다.
     * @return 주어진 요일에 해당하는 스터디 그룹들의 리스트.
     *         파일을 읽을 수 없을 경우, "오류: 파일을 읽을 수 없습니다."라는 메시지를 포함한 리스트를 반환합니다.
     */
    private static List<String> filterStudyGroupsByDay(String day) {
        try {
            List<String> allGroups = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("src/StudyGroupMatching/StudyGroup_List.txt"));
            return allGroups.stream()
                    .filter(group -> group.contains("( " + day + " )")) // 요일로 필터링
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("오류: 파일을 읽을 수 없습니다.");
        }
    }
}
