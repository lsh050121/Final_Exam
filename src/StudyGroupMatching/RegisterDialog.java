package StudyGroupMatching;

import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 메인 프레임의 등록 버튼 클릭 시 실행되는 창을 생성하는 클래스입니다.
 *
 * @author Lee SangHyeok    (lsh050121@naver.com)
 *
 * @created 2024-12-23
 * @lastModified 2024-12-24
 *
 * @changelog
 * <ul>
 *     <li>2024-12-23 : 최초 생성</li>
 *     <li>2024-12-23 : 등록 버튼 액션리스너 추가</li>
 *     <li>2024-12-24 : 인원 수 오류메세지 추가</li>
 *     <li>2024-12-24 : 오류메시지 발생시 효과음 발생 메소드 추가</li>
 *     <li>2024-12-24 : 주제 유효성 검사 추가</li>
 * </ul>
 *
 */
public class RegisterDialog extends JFrame{
    /**
     * 등록 화면을 생성하는 메소드입니다.
     * 사용자가 스터디 그룹을 등록할 수 있는 다이얼로그를 생성하고,
     * 주제, 요일, 현재인원, 최대 입력을 입력받아 추가합니다.
     * 등록 버튼을 클릭하면 입력값을 검증한 후, 파일에 저장하고 리스트를 갱신합니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-23
     * @lastModified 2024-12-24
     *
     * @changelog
     * <ul>
     *     <li>2024-12-23 : 최초생성</li>
     *     <li>2024-12-23 : 등록 버튼 액션리스너 추가</li>
     *     <li>2024-12-24 : 인원 수 오류메세지 추가</li>
     *     <li>2024-12-24 : 주제 유효성 검사 추가</li>
     * </ul>
     * @param parent 부모 JFrame 객체. 다이얼로그는 이 부모 JFrame 위에 표시됩니다.
     */
    public static void openRegistrationDialog(JFrame parent) {
        JDialog registrationDialog = new JDialog(parent, "스터디 등록", true);
        registrationDialog.setSize(300, 300);
        registrationDialog.setLayout(null);

        JLabel subjectLabel = new JLabel("주제");
        subjectLabel.setBounds(20, 20, 80, 20);
        registrationDialog.add(subjectLabel);

        JTextField subjectField = new JTextField();
        subjectField.setBounds(100, 20, 150, 20);
        registrationDialog.add(subjectField);

        JLabel dayLabel = new JLabel("요일");
        dayLabel.setBounds(20, 60, 80, 20);
        registrationDialog.add(dayLabel);

        String[] days = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
        JComboBox<String> dayComboBox = new JComboBox<>(days);
        dayComboBox.setBounds(100, 60, 150, 20);
        registrationDialog.add(dayComboBox);

        JLabel currentLabel = new JLabel("현 인원 ");
        currentLabel.setBounds(20, 100, 80, 20);
        registrationDialog.add(currentLabel);

        Integer[] currentMembers = {1, 2, 3, 4, 5, 6, 7};
        JComboBox<Integer> currentMembersComboBox = new JComboBox<>(currentMembers);
        currentMembersComboBox.setBounds(100, 100, 150, 20);
        registrationDialog.add(currentMembersComboBox);

        JLabel totalLabel = new JLabel("최대 인원");
        totalLabel.setBounds(20, 140, 80, 20);
        registrationDialog.add(totalLabel);

        Integer[] totalMembers = {2, 3, 4, 5, 6, 7, 8};
        JComboBox<Integer> totalMembersComboBox = new JComboBox<>(totalMembers);
        totalMembersComboBox.setBounds(100, 140, 150, 20);
        registrationDialog.add(totalMembersComboBox);

        JButton addButton = new JButton("등록");
        addButton.setBounds(100, 200, 100, 30);
        registrationDialog.add(addButton);

        //등록 버튼 액션
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText();
                String day = (String) dayComboBox.getSelectedItem();
                int currentMembers = (int) currentMembersComboBox.getSelectedItem();
                int totalMembers = (int) totalMembersComboBox.getSelectedItem();

                // 주제 비어있는지 검사
                if (subject.isEmpty()) {
                    playErrorSound();
                    JOptionPane.showMessageDialog(registrationDialog, "주제를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 현재인원이 총 인원보다 크지 않게 검사
                if(currentMembers >= totalMembers) {
                    playErrorSound();
                    JOptionPane.showMessageDialog(registrationDialog,"현 인원이 최대 인원보다 크거나 같을 수 없습니다. 다시 입력해주세요.",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                String newStudyGroup = String.format("%s / ( %s ) / %d / %d명", subject, day, currentMembers, totalMembers);

                try {
                    ReadFile.writeToFile("src/StudyGroupMatching/StudyGroup_List.txt", newStudyGroup);
                    ReadFile.addStudyGroup(newStudyGroup, MainFrame.getStudyListPanel());
                } catch (IOException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(registrationDialog, "파일 저장 중 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                registrationDialog.dispose();
            }
        });

        registrationDialog.setLocationRelativeTo(null);
        registrationDialog.setResizable(false);
        registrationDialog.setVisible(true);


    }

    /**
     * 오류메세지 발생시 효과음을 생성하는 메소드입니다
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
    private static void playErrorSound() {
        Toolkit.getDefaultToolkit().beep();
    }
}