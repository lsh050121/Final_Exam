package StudyGroupMatching;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 메인 프레임의 등록 버튼 클릭 시 실행되는 창을 생성하는 클래스입니다.
 *
 * @author Lee SangHyeok    (lsh050121@naver.com)
 *
 * @created 2024-12-23
 *
 * @changelog
 * <ul>
 *     <li>2024-12-23 : 최초 생성</li>
 * </ul>
 *
 */
public class RegisterDialog extends JFrame{
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

        registrationDialog.setLocationRelativeTo(null);
        registrationDialog.setResizable(false);
        registrationDialog.setVisible(true);


    }
}