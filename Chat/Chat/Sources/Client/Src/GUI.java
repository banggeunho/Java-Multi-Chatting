package Chat.Sources.Client.Src;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.List;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel;



public class GUI extends JFrame implements ActionListener {

	static String menu_list[][];
	private String[] column_list= {"번 호", "메 뉴", "장 소", "비 용", "식사가능시간"};
	private JPanel contentPane;
	private JTextField a_time;
	private JTextField cur_loc;
	private JTextField des_loc;
	JLabel lblNewLabel_1 = new JLabel("Bob List");
	List list;
	JTextPane txtpnDetail;
	int cur_index;
	int des_index;
	int str_index;

	private JTable table = new JTable();
	private final JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public GUI() {
		table.setAutoCreateRowSorter(true);
		table.setFont(new Font("배달의민족 주아", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			/* When client press the Jtable element, Print path at txtpnDetail(TextArea) */
			public void mousePressed(MouseEvent e) {
							JTable target = (JTable) e.getSource();
					    	str_index = Integer.parseInt(menu_list[target.getSelectedRow()][5]);
					    	StringBuilder rs = new StringBuilder();
					    	int time = floydwarshall.distance(cur_index, str_index) + floydwarshall.distance(str_index, des_index);
					    	rs.append("<Path>  총 "+time+"분 소요\n");
							floydwarshall.printPath(cur_index, str_index);
							if(str_index != cur_index)
							rs.append(floydwarshall.getPath() +"["+floydwarshall.distance(cur_index, str_index)+"분]\n");
							floydwarshall.printPath(str_index, des_index);
							if(str_index != des_index)
							rs.append(floydwarshall.getPath() +"["+floydwarshall.distance(str_index, des_index)+"분]\n");
					    	txtpnDetail.setText(rs.toString());
			}
		});
		/*Setting main frame*/
		setTitle("Abob Project v5");		
		setBounds(100, 100, 620, 342);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(85, 107, 47));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*Left Panel*/
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBackground(new Color(85, 107, 47));
		panel.setBounds(12, 10, 188, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*Insert field in left panel : time available time*/
		a_time = new JTextField();
		a_time.setText("30");
		a_time.setBounds(22, 86, 128, 21);
		panel.add(a_time);
		a_time.setColumns(10);
		
		/*Insert field in left panel : Current Location*/
		cur_loc = new JTextField();
		cur_loc.setText("IT\uB300\uD559");
		cur_loc.setColumns(10);
		cur_loc.setBounds(22, 133, 128, 21);
		panel.add(cur_loc);
		
		/*Insert field in left panel : Destination Location*/
		des_loc = new JTextField();
		des_loc.setText("\uAC00\uCC9C\uAD00");
		des_loc.setColumns(10);
		des_loc.setBounds(22, 178, 128, 21);
		panel.add(des_loc);
		
		/*Button in left panel : SHOW BOB*/
		JButton btnNewButton = new JButton("Show Bob");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		btnNewButton.setBorder(UIManager.getBorder("CheckBox.border"));
		btnNewButton.setBackground(new Color(128, 128, 0));
		
		/*Click Event of "SHOW BOB" Button*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_list = new String[40][6];
				String temp;
				locationToIndex lti = new locationToIndex();
				DB jdbc = new DB();
				int cnt=0;
				txtpnDetail.setText("Path..");				
				int[] c_r_time = new int[5];
				int[] r_d_time = new int[5];
				int[] m_time = new int[5]; 
				temp = lti.Convert(cur_loc.getText(), des_loc.getText());		
				cur_index = Integer.parseInt(temp.substring(0, temp.indexOf(",")));
				des_index = Integer.parseInt(temp.substring(temp.indexOf(",")+1));
				for(int i=0;i<5;i++) { //Building which has restaurant is odd index(1, 3, 5, 7, 9)
					c_r_time[i] = floydwarshall.distance(cur_index, i*2+1);
					r_d_time[i] = floydwarshall.distance( i*2+1, des_index);
					m_time[i] = c_r_time[i] + r_d_time[i];
					try {
						if(jdbc.DB(Integer.parseInt(a_time.getText()), m_time[i], i*2+1)) {
							cnt++;
							System.out.println(cnt);
						}	
					}
					catch(NumberFormatException | NullPointerException e1) {
						JOptionPane.showMessageDialog(null,"숫자를 입력해 주세요","알림", JOptionPane.PLAIN_MESSAGE);
						cnt=-1;
						break;
					}
				}
				/*If the number of data(instance) which get from database more than 1.*/
				if(cnt>0) {
					DefaultTableModel dtm = new DefaultTableModel();
					/* Renewal Jtable model that print the menu */
					for(int i=0; i<column_list.length;i++ )
					{
						dtm.addColumn(column_list[i]);
					}
					for(int i=0;i<menu_list.length;i++)
					{
						if(menu_list[i][0]!=null)
						 dtm.addRow(menu_list[i]);
						else break;
					}
					table.setModel(dtm);
					/* Print the number of printed menu */
					lblNewLabel_1.setText("Bob List: " + DB.menu_cnt+"개");
					DB.menu_cnt=0;
				}
				else if(cnt!=-1){
					JOptionPane.showMessageDialog(null,"식사시간이 부족합니다.","알림",JOptionPane.PLAIN_MESSAGE);
					}
				}
		});
		btnNewButton.setBounds(12, 218, 164, 44);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Free Time (\uBD84)");
		lblNewLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 13));
		lblNewLabel.setBounds(12, 68, 76, 15);
		panel.add(lblNewLabel);
		
		JLabel lblCurrentLocation = new JLabel("Current Location");
		lblCurrentLocation.setFont(new Font("배달의민족 주아", Font.PLAIN, 13));
		lblCurrentLocation.setBounds(12, 113, 109, 15);
		panel.add(lblCurrentLocation);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("배달의민족 주아", Font.PLAIN, 13));
		lblDestination.setBounds(12, 160, 96, 15);
		panel.add(lblDestination);
		
		JLabel lblproject = new JLabel("\uC544\uBC25Project");
		lblproject.setBackground(new Color(85, 107, 47));
		lblproject.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		lblproject.setHorizontalTextPosition(SwingConstants.CENTER);
		lblproject.setHorizontalAlignment(SwingConstants.CENTER);
		lblproject.setBounds(12, 10, 164, 48);
		panel.add(lblproject);
		
		/* Right panel*/
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBackground(new Color(85, 107, 47));
		panel_1.setBounds(212, 10, 381, 283);
		contentPane.add(panel_1);
	
				
		lblNewLabel_1.setBounds(10, 10, 347, 23);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));		
		
		/*TextArea that print walking path in Right panel*/
		txtpnDetail = new JTextPane();
		txtpnDetail.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		txtpnDetail.setFont(new Font("배달의민족 주아", Font.PLAIN, 14));
		txtpnDetail.setForeground(new Color(0, 0, 0));
		txtpnDetail.setBackground(new Color(85, 107, 47));
		txtpnDetail.setText("<Path>");
		txtpnDetail.setBounds(10, 201, 361, 72);
		panel_1.add(txtpnDetail);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		scrollPane.setBounds(10, 41, 359, 151);
		panel_1.add(scrollPane);
		panel_1.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
