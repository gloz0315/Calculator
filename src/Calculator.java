import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    public JTextField inputSpace; // 화면의 경우 이걸로 구현할 것
    public Calculator(){
        setLayout(null);

        inputSpace = new JTextField(); // 빈 공간의 JTextField를 생성하고 버튼으로만 입력을 받을 것
        inputSpace.setEditable(false); // 그래서 수정 불가능하게 만들어줌
        inputSpace.setBackground(Color.WHITE); // 배경색
        inputSpace.setHorizontalAlignment(JTextField.RIGHT); // 정렬 위치를 JTextField 오른쪽에 둠
        inputSpace.setFont(new Font("Arial", Font.BOLD, 50)); // 폰트
        inputSpace.setBounds(8,10,270,70); // x = 8, y = 10 위치에 270x70의 크기를 의미

        //버튼들을 담을 패널을 만들어주기
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4,10,10)); // 격자 형태로 배치해주는 GridLayout을 사용
        // 위에 의미는 가로칸수 4, 세로칸수 4, 좌우간격 10, 상하간격 10을 의미한다.
        buttonPanel.setBounds(8,90,270,235); // 위치와 크기를 정함

        String button_names[] = {"C", "÷", "×" , "=", "7", "8", "9", "+", "4" , "5" , "6", "-", "1", "2", "3", "0"};
        // 계산기 자판기에 들어갈 번호들을 배열을 통해 만들어줌
        JButton buttons[] = new JButton[button_names.length]; // 버튼들의 배열들을 만들어주기

        for(int i = 0; i < button_names.length; i++){
            buttons[i] = new JButton(button_names[i]);        // 각각 for문을 통해 넣어주고
            buttons[i].setFont(new Font("Arial", Font.BOLD,20));  // 글씨도 바꿔주고
            if(button_names[i] == "C"){
                buttons[i].setBackground(Color.RED);
            }else if((i >=4 && i <=6) || (i >= 8 && i <= 10) || (i >=12 && i <= 15)){
                buttons[i].setBackground(Color.BLACK);
            }else{
                buttons[i].setBackground(Color.GRAY);
            }
            buttons[i].setForeground(Color.WHITE); // 글자색은 흰색으로 설정
            buttons[i].setBorderPainted(false); // 테두리를 없애줌

            buttonPanel.add(buttons[i]); // 패널에 버튼을 넣어주고
        }

        add(inputSpace); // 이대로 만든 inputSpace를 추가함
        add(buttonPanel); // 만든 buttonPanel을 추가함

        setTitle("계산기");
        setVisible(true);
        setSize(300,370);
        setLocationRelativeTo(null);  // 처음 화면을 띄울때, null값으로 가운데에 띄우며
        setResizable(false); // 사이즈 조절 불가능하게 만듬
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램 종료하게끔 만듬
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
