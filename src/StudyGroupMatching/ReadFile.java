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
import java.util.HashSet;
import java.util.Set;

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
 *     <li>2024-12-24 : populateStudyList 메소드 수정</li>
 * </ul>
 */
public class ReadFile {
    /**
     * 주어진 파일에서 스터디 그룹 정보를 읽어와 `studyListPanel`에 표시하는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-22
     * @lastModified 2024-12-24
     *
     * @changelog
     * <ul>
     *     <li>2024-12-22 : 최초생성</li>
     *     <li>2024-12-24 : 학번을 제외한 정보만 표시되게 변경</li>
     * </ul>
     * @param filePath 스터디 그룹 정보가 저장된 파일의 경로
     * @param studyListPanel 스터디 그룹 목록을 표시하는 JPanel 객체
     * @throws IOException 파일 읽기 중 발생할 수 있는 입출력 오류
     */
    static final String FILE_PATH = "src/StudyGroupMatching/StudyGroup_List.txt";

    public static void populateStudyList(String filePath, JPanel studyListPanel) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get(filePath);
        List<String> studyGroups = java.nio.file.Files.readAllLines(path);

        for (String infoText : studyGroups) {
            // 학번을 제외한 정보만 표시
            String[] parts = infoText.split(" / ");
            String groupInfoWithoutStudentNumber = parts[0] + " / " + parts[1] + " / " + parts[2] + " / " + parts[3];

            addStudyGroup(groupInfoWithoutStudentNumber, studyListPanel);
        }
    }

    /**
     * 주어진 파일에 내용을 추가하는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-23
     *
     * @changelog
     * <ul>
     *     <li>2024-12-23 : 최초생성</li>
     * </ul>
     *
     * @param filePath 파일의 경로입니다.
     * @param content 파일에 추가할 내용입니다.
     * @throws IOException 파일 쓰기 중 발생할 수 있는 입출력 오류
     */
    public static void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(content);
            writer.newLine();
        }
    }

    /**
     * 스터디 그룹 정보를 'studyListPanel'에 추가하여 화면에 표시해주는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-23
     * @lastModified 2024-12-25
     *
     * @changelog
     * <ul>
     *     <li>2024-12-23 : 최초생성</li>
     *     <li>2024-12-24 : 학번을 제외한 정보만 표시되게 변경</li>
     *     <li>2024-12-25 : 학번 입력시 숫자만 입력받을 수 있게 변경</li>
     * </ul>
     *
     * @param infoText 스터디 그룹에 대한 정보 문자열입니다.
     * @param studyListPanel 스터디 그룹 목록을 표시할 JPanel 객체입니다.
     */
    public static void addStudyGroup(String infoText, JPanel studyListPanel) {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        listPanel.setMaximumSize(new Dimension(400, 40));

        // 학번을 제외한 정보만 화면에 표시
        String[] parts = infoText.split(" / ");
        String groupInfoWithoutStudentNumber = parts[0] + " / " + parts[1] + " / " + parts[2] + " / " + parts[3];

        JLabel info = new JLabel(groupInfoWithoutStudentNumber);
        info.setPreferredSize(new Dimension(250, 30));
        listPanel.add(info);

        JButton joinButton = new JButton("참가");
        joinButton.setPreferredSize(new Dimension(80, 30));
        listPanel.add(joinButton);

        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 학번 입력 받기
                String studentNumber = JOptionPane.showInputDialog(null, "학번을 입력하세요:", "참가", JOptionPane.PLAIN_MESSAGE);

                // 입력값이 비어 있거나 숫자가 아닌 경우 처리
                if (studentNumber == null || studentNumber.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "학번을 입력하지 않았습니다.", "참가 실패", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!studentNumber.matches("\\d+")) { // 숫자가 아닌 경우
                    JOptionPane.showMessageDialog(null, "학번은 숫자만 입력할 수 있습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 참가 처리 로직
                boolean success = ReadFile.joinStudyGroup(infoText, studentNumber, studyListPanel);
                if (success) {
                    JOptionPane.showMessageDialog(null, "참가가 완료되었습니다!", "참가 성공", JOptionPane.INFORMATION_MESSAGE);

                    // studyListPanel 업데이트
                    studyListPanel.removeAll();
                    try {
                        ReadFile.populateStudyList(FILE_PATH, studyListPanel);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    studyListPanel.revalidate();
                    studyListPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "이미 참가한 학번이거나 최대 인원에 도달하여 참가할 수 없습니다.", "참가 실패", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        studyListPanel.add(listPanel);
        studyListPanel.revalidate();
        studyListPanel.repaint();
    }

    /**
     * 사용자가 입력한 학번을 바탕으로 스터디 그룹에 참가를 처리하는 메소드입니다.
     *
     * @author Lee SangHyeok    (lsh050121@naver.com)
     *
     * @created 2024-12-24
     * @lastModified 2024-12-24
     *
     * @changelog
     * <ul>
     *     <li>2024-12-24 : 최초생성</li>
     *     <li>2024-12-24 : 학번 입력 및 중복 방지 구현</li></>
     * </ul>
     *
     * @param groupInfo
     * @param studentNumber
     * @param studyListPanel
     * @return
     */
    public static boolean joinStudyGroup(String groupInfo, String studentNumber, JPanel studyListPanel) {
        Path filePath = Paths.get(FILE_PATH);

        try {
            List<String> allGroups = Files.readAllLines(filePath);
            List<String> updatedGroups = new ArrayList<>();
            boolean updated = false;

            for (String group : allGroups) {
                if (group.startsWith(groupInfo)) { // 해당 스터디 그룹을 찾음
                    String[] parts = group.split(" / ");
                    int currentMembers = Integer.parseInt(parts[2].trim());
                    int maxMembers = Integer.parseInt(parts[3].replace("명", "").trim());
                    String existingStudentNumbers = parts.length > 4 ? parts[4] : "";

                    // 기존 학번을 Set에 저장하여 중복을 제거
                    Set<String> studentNumbers = new HashSet<>();
                    if (!existingStudentNumbers.isEmpty()) {
                        String[] existingNumbers = existingStudentNumbers.split(", ");
                        for (String num : existingNumbers) {
                            studentNumbers.add(num);
                        }
                    }

                    // 학번 중복 체크
                    if (studentNumbers.contains(studentNumber)) {
                        return false; // 이미 참가한 학번이라 참가 불가
                    }

                    // 최대 인원을 초과하는 경우
                    if (currentMembers >= maxMembers) {
                        return false; // 참가 실패
                    }

                    // 인원수 증가
                    currentMembers++;
                    // 학번 추가
                    studentNumbers.add(studentNumber);

                    // 학번을 다시 문자열로 변환
                    String newStudentNumbers = String.join(", ", studentNumbers);

                    group = parts[0] + " / " + parts[1] + " / " + currentMembers + " / " + maxMembers + "명 / " + newStudentNumbers;
                    updated = true;
                }
                updatedGroups.add(group);
            }

            // 파일에 업데이트된 내용 저장
            if (updated) {
                Files.write(filePath, updatedGroups);
            }

            return updated; // 참가 성공 여부 반환
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 오류 발생 시 참가 실패
        }
    }
}