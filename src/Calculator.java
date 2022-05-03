import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator extends JFrame {

    private JTextField inputSpace; // 화면의 경우 이걸로 구현할 것
    private ArrayList<String> equation = new ArrayList<String>();

    private String num = "";

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

            buttons[i].addActionListener(new PadActionListener()); // 버튼을 눌렀을때 행동하게끔 추가한다.

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

    // ActionListener를 상속시켜주고 actionPerformed(ActionEvent e) 메소드를 이벤트 처리리
   class PadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String operation = e.getActionCommand(); // 어떤 버튼이 눌렸는지 operation 객체에 받아온다.
            if(operation.equals("C")){
                // 만약 "C"의 버튼이 눌러졌을 경우에 계산식 내용을 지워줄 것이다.
                inputSpace.setText("");
            }else if(operation.equals("=")){
                // 만약 "="의 버튼이 눌러졌을 경우에 전체 내용을 출력한다.
                String result = Double.toString(calculate(inputSpace.getText()));
                inputSpace.setText("" + result); // 앞서 만든 메소드를 이용해 계산을 해주고 계산식 화면에 값을 띄어준다.
                num = "";
            }else{
                // 나머지 버튼을 눌렀을 경우 계산식에 추가되도록 한다.
                inputSpace.setText(inputSpace.getText() + e.getActionCommand());
            }
        }
    }

    // 계산 기능을 구현하기 위해 ArrayList에 숫자와 연산 기호를 하나씩 구분해준다.
    private void fullTextParsing(String inputText){
        equation.clear();

        for(int i = 0; i < inputText.length(); i++){
            char ch = inputText.charAt(i);

            if(ch == '-' | ch == '+' | ch == '×' | ch == '÷'){
                equation.add(num);
                num = "";                    // num을 초기화하고 연산기호를 ArrayList에 추가해준다.
                equation.add(ch + "");
            }else{
                num = num+ ch;              // 나머지인 경우 그냥 추가해준다.
            }
        }

        // 반복문이 끝나고 최종적으로 num도 ArrayList에 추가해준다.
        equation.add(num);
    }

    public double calculate(String inputText){
        fullTextParsing(inputText); // 계산기 기능을 만듬

        double prev = 0;
        double currnet = 0;
        String mode = ""; // 연산 기호에 대한 처리를 해주는 변수

        for(String s : equation){
            if(s.equals("+")){
                mode = "add";
            }else if(s.equals("-")){
                mode = "sub";
            }else if(s.equals("×")){
                mode = "mul";
            }else if(s.equals("÷")){
                mode = "div";
            }else{
                // 나머지 숫자인 경우 문자열을 Double로 형변환 해줌
                currnet = Double.parseDouble(s);


                // prev에 계속 계산값이 갱신되어 저장될 것이다.
                if(mode == "add"){
                    prev += currnet;
                }else if(mode == "sub"){
                    prev -= currnet;
                }else if(mode == "mul"){
                    prev *= currnet;
                }else if(mode == "div"){
                    prev /= currnet;
                }else{
                    prev = currnet;
                }
            }
        }

        //마지막에 prev 값을 반환해주면 된다.
        return prev;
    }
    public static void main(String[] args) {
        new Calculator();
    }
}
