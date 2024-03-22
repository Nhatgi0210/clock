package clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class clock extends JFrame implements Runnable{
	String timeZone;
	JPanel timepanel;
	JLabel timelabel;
	JComboBox<String> selectTimeZone;
	JButton ok;
	public clock(String timeZone) {
		this.timeZone = timeZone;
		init();
	}
	public void init() {
		setSize(250,200);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		timelabel = new JLabel("");
		timelabel.setFont(new Font("tahoma",Font.BOLD,16));
		timepanel = new JPanel();
		//timepanel.setBackground(Color.blue);
		timepanel.setBounds(0, 40, 235, 30);
		timepanel.add(timelabel);
		add(timepanel);
		
		String[] a = new String[27];
		for(int i = -12; i <0; i++) {
			a[i+12] = "GMT"+i;
		}
		for(int i = 0; i <= 14; i++) {
			a[i+12] = "GMT+"+i;
		}
		selectTimeZone = new JComboBox<String>(a);
		selectTimeZone.setBounds(20,100,100,30);
		selectTimeZone.setSelectedItem(timeZone);
		add(selectTimeZone);
		
		ok = new JButton("OK");
		ok.setBounds(125,100,100,30);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					new clock((String) selectTimeZone.getSelectedItem());
					selectTimeZone.setSelectedItem(timeZone);
			}
		});
		add(ok);
		
		Thread t1 = new Thread(this);
		t1.start();
		
		setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
			timelabel.setText(dateFormat.format(new Date()));
		}
	}
}
