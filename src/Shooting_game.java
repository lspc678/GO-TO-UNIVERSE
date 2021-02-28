import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.Random;
import java.awt.GridLayout;

public class Shooting_game 
{
	// member variable
	private JFrame frame;
	private static JPanel panel_title_screen = new JPanel();
	private static JPanel panel_game_screen = new JPanel();
	private static JLabel Label_stage = new JLabel("1"); // print stage
	private static JPanel panel_main_screen = new JPanel();
	private static JPanel panel_ending_credit = new JPanel();
	private static int stage = 1; // default = 1
	private static JLabel Label_lifes = new JLabel("3"); // print lifes
	private static int lifes = 99; // default = 3
	private static int score = 0; // default = 0
	private static JLabel Label_score = new JLabel("0"); // print score
	private static JLabel Label_final_score = new JLabel("0");
	private static JLabel Label_final_life = new JLabel("0");
	private static int bomb = 2;
	private static JPanel panel_itemshop = new JPanel();
	private static JLabel Label_text_power_up = new JLabel("ÆÄ¿ö¾÷(ÇöÀç ÆÄ¿ö: 1)");
	private static JLabel Label_current_score = new JLabel("0");
	private static JLabel Label_massage = new JLabel("¾ÆÀÌÅÛ ¼¥¿¡ ¿À½Å°ÍÀ» È¯¿µÇÕ´Ï´Ù");
	private static JLabel Label_text_bomb_1 = new JLabel("ÆøÅº(ÇöÀç ¼ÒÁö°³¼ö: 1)");
	private static JLabel Label_text_life = new JLabel("¶óÀÌÇÁ(ÇöÀç ¶óÀÌÇÁ: 1)");
	private static JLabel Label_loading = new JLabel("LOADING...");
	// show 1st bomb ~ 5th bomb
	private static JLabel Label_bomb1 = new JLabel("\u25CF");
	private static JLabel Label_bomb2 = new JLabel("\u25CF");
	private static JLabel Label_bomb3 = new JLabel("\u25CF");
	private static JLabel Label_bomb4 = new JLabel("\u25CF");
	private static JLabel Label_bomb5 = new JLabel("\u25CF");
	private static JLabel Label_text_gameover = new JLabel("GAME OVER");
	
	// player
	private static JLabel Label_player = new JLabel("\u25C0\u25A0\u25A0\u25B6");
	private static int power = 0; // default = 0
	private static int player_damage = 1; // default = 1
	private static boolean player_died = false;
	private static boolean invincibility = false; // ¹«Àû
	private static int frame_invincibility = 0;
	private static final int invincibility_time = 500;
	private static boolean used_bomb = false;
	private static int bomb_fire_count = 0;
	private static Bullet power_up_item = new Bullet();
	private static Bullet barrier_item = new Bullet();
	private static boolean drop_power = false;
	private static boolean drop_barrier = false;
	private static int shield_barrier = 0;
	private static boolean shopping = false; // default = false
	private static boolean side_attack = false; // default = false
	private static Point player = Label_player.getLocation();
	
	// bullet
	private static final int max_player_bullet = 255;
	private static final int max_bomb_bullet = 350;
	private static Bullet[] bullet_player = new Bullet[max_player_bullet];
	private static Bullet[] bullet_player_2 = new Bullet[max_player_bullet]; // power >= 10
	private static Bullet[] bullet_player_2_left = new Bullet[max_player_bullet]; // power >= 15
	private static Bullet[] bullet_player_2_right = new Bullet[max_player_bullet]; // power >= 15
	private static Bullet[] bullet_bomb = new Bullet[max_bomb_bullet];
	//private static JLabel[] bullet_player = new JLabel[max_player_bullet];
	
	private JTextField textField;
	private static boolean start = false;
	
	// set fire speed
	private static int fire_speed = 20;
	
	// enemy
	private static final int max_enemy_bullet = 50;
	private static Bullet[] bullet_enemy = new Bullet[max_enemy_bullet];
	private static Bullet[] bullet_enemy_2 = new Bullet[max_enemy_bullet];
	private static Bullet[] bullet_enemy_3 = new Bullet[max_enemy_bullet];
	private static final int enemy_1_count = 50;
	//private static final int enemy_1_width = 100;
	//private static final int enemy_1_height = 30;
	//private static final int enemy_1_fire_speed = 200;
	private static final int enemy_2_count = 20;
	private static final int enemy_3_count = 10;
	//private static final int enemy_2_width = 200;
	//private static final int enemy_2_height = 30;
	//private static final int enemy_2_fire_speed = 300;
	//private static final int enemy_1st_middle_boss_width = 200;
	//private static final int enemy_1st_middle_boss_height = 170;
	//private static final int enemy_1st_middle_boss_fire_speed = 150;
	//private static JLabel[] Label_enemy_1 = new JLabel[enemy_1]; // 1st stage enemy(HP: 1)
	
	// enemy(boss)
	private static JLabel Label_text_hp = new JLabel("HP: ");
	private static JLabel Label_hp = new JLabel("200");
	private static JLabel Label_1stage_boss = new JLabel("\u25A0");
	
	// cheat code
	private static final String max_power = "GOD OF FIGHTER";
	private static final String max_fire_speed = "FIRESTORM";
	
	// cheat code flag
	private static boolean flag_max_power = false;
	private static boolean flag_max_fire_speed = false;
	
	// frame count
	private static long frame_count_start = System.currentTimeMillis();
	private static long frame_count_current = System.currentTimeMillis();
	private static int frame_count = 0; // control player bullet speed
	private static int enemy_attack_frame_count = 0; // control enemy bullet speed
	private static int enemy_2_attack_frame_count = 0;
	
	private static Point bp3 = new Point();
	private static Point bp4 = new Point();
	private static Point bp5 = new Point();
	private static Point bp6 = new Point();
	
	//private static Point bp3 = bullet_player[0].label.getLocation();
	//private static Point bp4 = bullet_player_2[0].label.getLocation();
	//private static Point bp5 = bullet_player_2_left[0].label.getLocation();
	//private static Point bp6 = bullet_player_2_right[0].label.getLocation();
	
	// event count
	private static int event_count = 0;
	
	// Launch the application.
	public static void main(String[] args) 
	{		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					Shooting_game window = new Shooting_game();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		while(true) // °ÔÀÓ ½ÃÀÛ ¹öÆ°À» ´©¸¦ ¶§ ±îÁö ´ë±â
		{
			wait(50);
			
			if(start == true)
			{
				break;
			}
		}
		
		// test code
		
		stage = 4; // default = 1
		lifes = 99; // default = 3
		score = 1000000; // default = 0
		power = 14; // default = 0
		player_damage = 2; // default = 1
		shopping = true; // default = false
		
		Label_bomb3.setVisible(false); // 3th bomb disable
		Label_bomb4.setVisible(false); // 4th bomb disable
		Label_bomb5.setVisible(false); // 5th bomb disable
		
		// set player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i] = new Bullet();
			bullet_player[i].label = new JLabel("¡Ü");
			bullet_player[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_player[i].label.setBounds(0, 0, 30, 30);
			bullet_player[i].posX = 0;
			bullet_player[i].posY = 0;
			bullet_player[i].move_x = 0;
			bullet_player[i].move_y = -5;
			bullet_player[i].label.setLocation(bullet_player[i].posX, bullet_player[i].posY); // original position(not used)
			panel_game_screen.add(bullet_player[i].label);
			bullet_player[i].label.setVisible(false);
		}
		
		// set player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player_2[i] = new Bullet();
			bullet_player_2[i].label = new JLabel("¡Ý");
			bullet_player_2[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_player_2[i].label.setBounds(0, 0, 30, 30);
			bullet_player_2[i].posX = 0;
			bullet_player_2[i].posY = 0;
			bullet_player_2[i].move_x = 0;
			bullet_player_2[i].move_y = -5;
			bullet_player_2[i].label.setLocation(bullet_player_2[i].posX, bullet_player_2[i].posY); // original position(not used)
			panel_game_screen.add(bullet_player_2[i].label);
			bullet_player_2[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player_2_left[i] = new Bullet();
			bullet_player_2_left[i].label = new JLabel("¡Ý");
			bullet_player_2_left[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_player_2_left[i].label.setBounds(0, 0, 30, 30);
			bullet_player_2_left[i].posX = 0;
			bullet_player_2_left[i].posY = 0;
			bullet_player_2_left[i].move_x = -2;
			bullet_player_2_left[i].move_y = -5;
			bullet_player_2_left[i].label.setLocation(bullet_player_2_left[i].posX, bullet_player_2_left[i].posY); // original position(not used)
			panel_game_screen.add(bullet_player_2_left[i].label);
			bullet_player_2_left[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player_2_right[i] = new Bullet();
			bullet_player_2_right[i].label = new JLabel("¡Ý");
			bullet_player_2_right[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_player_2_right[i].label.setBounds(0, 0, 30, 30);
			bullet_player_2_right[i].posX = 0;
			bullet_player_2_right[i].posY = 0;
			bullet_player_2_right[i].move_x = 2;
			bullet_player_2_right[i].move_y = -5;
			bullet_player_2_right[i].label.setLocation(bullet_player_2_right[i].posX, bullet_player_2_right[i].posY); // original position(not used)
			panel_game_screen.add(bullet_player_2_right[i].label);
			bullet_player_2_right[i].label.setVisible(false);
		}
		
		// set player bomb bullet
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i] = new Bullet();
			bullet_bomb[i].label = new JLabel("¡Ü");
			bullet_bomb[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_bomb[i].label.setBounds(0, 0, 30, 30);
			bullet_bomb[i].posX = 0;
			bullet_bomb[i].posY = 0;
			bullet_bomb[i].move_x = 0;
			bullet_bomb[i].move_y = -10;
			bullet_bomb[i].label.setLocation(bullet_bomb[i].posX, bullet_bomb[i].posY); // original position(not used)
			panel_game_screen.add(bullet_bomb[i].label);
			bullet_bomb[i].label.setVisible(false);
		}
		
		bp3 = bullet_player[0].label.getLocation();
		bp4 = bullet_player_2[0].label.getLocation();
		bp5 = bullet_player_2_left[0].label.getLocation();
		bp6 = bullet_player_2_right[0].label.getLocation();
		
		bomb_fire_count = 0;
		
		// set power up item
		power_up_item.move_x = 0;
		power_up_item.move_y = 2;
		power_up_item.posX = 0;
		power_up_item.posY = 0;
		power_up_item.label = new JLabel("<P>");
		power_up_item.label.setFont(new Font("Agency FB", Font.BOLD, 30));
		power_up_item.label.setHorizontalAlignment(SwingConstants.CENTER);
		power_up_item.label.setBounds(0, 0, 40, 35);
		panel_game_screen.add(power_up_item.label);
		power_up_item.label.setVisible(false);
		
		// set shield barrier item
		barrier_item.move_x = 0;
		barrier_item.move_y = 2;
		barrier_item.posX = 0;
		barrier_item.posY = 0;
		barrier_item.label = new JLabel("<S>");
		barrier_item.label.setFont(new Font("Agency FB", Font.BOLD, 30));
		barrier_item.label.setHorizontalAlignment(SwingConstants.CENTER);
		barrier_item.label.setBounds(0, 0, 40, 35);
		panel_game_screen.add(barrier_item.label);
		barrier_item.label.setVisible(false);
			
		// set enemy bullet
		if(flag_max_fire_speed == true) // cheat code 1
		{
			fire_speed = 150;
		}
		
		if(flag_max_power == true) // cheat code 2
		{
			player_damage = 3;
		}
		
		Label_stage.setText(Integer.toString(stage));
		Label_lifes.setText(Integer.toString(lifes));
		
		Label_loading.setFont(new Font("Agency FB", Font.BOLD, 99));
		Label_loading.setHorizontalAlignment(SwingConstants.CENTER);
		Label_loading.setBounds(365, 348, 533, 161);
		panel_game_screen.add(Label_loading);
		
		JLabel Label_ending_1 = new JLabel("µåµð¾î ±â³ª±ä ¿ìÁÖÀüÀïÀÌ ³¡³µ´Ù.");
		Label_ending_1.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 40));
		Label_ending_1.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ending_1.setBounds(0, 40, 1264, 99);
		panel_ending_credit.add(Label_ending_1);
		
		JLabel Label_ending_2 = new JLabel("±×·¸´Ù. Áö±¸ÀÎÀÌ ½Â¸®ÇÑ °ÍÀÌ´Ù!");
		Label_ending_2.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ending_2.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 40));
		Label_ending_2.setBounds(0, 135, 1264, 99);
		panel_ending_credit.add(Label_ending_2);
		
		JLabel Label_ending_3 = new JLabel("ÇÏÁö¸¸ ¾ÕÀ¸·Î ¾ðÁ¨°¡ ´Ù½ÃÇÑ¹ø ¿ìÁÖÀüÀïÀÌ ¹ú¾îÁú Áöµµ ¸ð¸¥´Ù.");
		Label_ending_3.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ending_3.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 40));
		Label_ending_3.setBounds(0, 230, 1264, 99);
		panel_ending_credit.add(Label_ending_3);
		
		JLabel Label_ending_4 = new JLabel("±×³¯À» ´ëºñÇÏ¿© ¿ì¸® GO TO UNIVERSE ºÎ´ë´Â Áö±¸ÀÇ ÆòÈ­¸¦ ÁöÅ°±â À§ÇØ");
		Label_ending_4.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ending_4.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 40));
		Label_ending_4.setBounds(0, 325, 1264, 99);
		panel_ending_credit.add(Label_ending_4);
		
		JLabel Label_ending_5 = new JLabel("³ë·ÂÇÒ °ÍÀÌ´Ù.");
		Label_ending_5.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ending_5.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 40));
		Label_ending_5.setBounds(0, 420, 1264, 99);
		panel_ending_credit.add(Label_ending_5);
		
		JLabel Label_ending_6 = new JLabel("´õ ÀÌ»ó À§ÇùÀÌ ¾ø´Â ±×³¯±îÁö GO TO UNIVERSEÀÇ È°µ¿Àº °è¼ÓµÈ´Ù!!");
		Label_ending_6.setHorizontalAlignment(SwingConstants.CENTER);
		Label_ending_6.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 40));
		Label_ending_6.setBounds(0, 515, 1264, 99);
		panel_ending_credit.add(Label_ending_6);
		
		JLabel Label_text_final_score = new JLabel("ÃÖÁ¾Á¡¼ö:  ");
		Label_text_final_score.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_text_final_score.setBounds(110, 701, 258, 83);
		panel_ending_credit.add(Label_text_final_score);
		
		JLabel Label_text_final_life = new JLabel("¶óÀÌÇÁ: ");
		Label_text_final_life.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_text_final_life.setBounds(110, 803, 228, 83);
		panel_ending_credit.add(Label_text_final_life);
		
		Label_final_score.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_final_score.setBounds(350, 701, 344, 83);
		panel_ending_credit.add(Label_final_score);
		
		Label_final_life.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_final_life.setBounds(350, 803, 477, 83);
		panel_ending_credit.add(Label_final_life);
		
		JButton btnNewButton = new JButton("Å¸ÀÌÆ² È­¸éÀ¸·Î µ¹¾Æ°¡±â");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_ending_credit.setVisible(false);
				panel_title_screen.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 34));
		btnNewButton.setBounds(770, 701, 457, 185);
		panel_ending_credit.add(btnNewButton);
		Label_loading.setVisible(false);
		
		// game loop
		/*
		stage1();
		
		if(lifes > 0)
		{
			stage2();
		}
		
		if(lifes > 0)
		{
			stage3();
		}
		
		if(lifes > 0)
		{
			panel_main_screen.setVisible(false);
			panel_itemshop.setVisible(true);
			String str = "ÆÄ¿ö¾÷(ÇöÀç ÆÄ¿ö: " + Integer.toString(power) + ")";
			String str2 = "ÆøÅº(ÇöÀç ¼ÒÁö°³¼ö: " + Integer.toString(bomb) + ")";
			String str3 = "¶óÀÌÇÁ(ÇöÀç ¶óÀÌÇÁ: " + Integer.toString(lifes) + ")";
			Label_text_power_up.setText(str);
			Label_text_bomb_1.setText(str2);
			Label_text_life.setText(str3);
			Label_current_score.setText(Integer.toString(score));
			while(shopping == true)
			{
				wait(5);
			}
			stage4();
		}*/
		
		if(stage == 1)
		{
			stage1();
		}
		else if(stage == 2)
		{
			stage2();
		}
		else if(stage == 3)
		{
			stage3();
		}
		else if(stage == 4)
		{
			panel_main_screen.setVisible(false);
			panel_itemshop.setVisible(true);
			String str = "ÆÄ¿ö¾÷(ÇöÀç ÆÄ¿ö: " + Integer.toString(power) + ")";
			String str2 = "ÆøÅº(ÇöÀç ¼ÒÁö°³¼ö: " + Integer.toString(bomb) + ")";
			String str3 = "¶óÀÌÇÁ(ÇöÀç ¶óÀÌÇÁ: " + Integer.toString(lifes) + ")";
			Label_text_power_up.setText(str);
			Label_text_bomb_1.setText(str2);
			Label_text_life.setText(str3);
			Label_current_score.setText(Integer.toString(score));
			while(shopping == true)
			{
				wait(5);
			}
			stage4();
		}
	} // end of public static void main(String[] args) 

	// Create the application.
	public Shooting_game() 
	{
		initialize();
	}

	// Initialize the contents of the frame.
	private void initialize() 
	{
		// set frame
		frame = new JFrame("GO TO UNIVERSE");
		frame.setSize(1280, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		panel_main_screen.setVisible(false);
		
		JPanel panel_secret_code = new JPanel();
		JPanel panel_credit = new JPanel();
		panel_credit.setVisible(false);
		
		panel_title_screen.setVisible(true);
		
		// set panel_title_screen
		panel_title_screen.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(panel_title_screen);
		panel_title_screen.setLayout(null);
		
		JButton Button_credit = new JButton("\uD06C\uB808\uB527");
		Button_credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel_title_screen.setVisible(false);
				panel_credit.setVisible(true);
			}
		});
		Button_credit.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Button_credit.setBounds(694, 552, 501, 92);
		panel_title_screen.add(Button_credit);
		
		JLabel Label_title = new JLabel("GO TO UNIVERSE");
		Label_title.setFont(new Font("Agency FB", Label_title.getFont().getStyle(), 140));
		Label_title.setHorizontalAlignment(SwingConstants.CENTER);
		Label_title.setBounds(0, 27, 1264, 262);
		panel_title_screen.add(Label_title);
		
		JPanel panel_print_key_direction = new JPanel();
		panel_print_key_direction.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_print_key_direction.setBounds(10, 304, 643, 670);
		panel_title_screen.add(panel_print_key_direction);
		panel_print_key_direction.setLayout(null);
		
		JLabel Label_up_direction = new JLabel("\u25B2");
		Label_up_direction.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 50));
		Label_up_direction.setBounds(130, 150, 72, 106);
		panel_print_key_direction.add(Label_up_direction);
		
		JLabel Label_left_right_direction = new JLabel("\u25C0   \u25B6 : \uC774\uB3D9");
		Label_left_right_direction.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 50));
		Label_left_right_direction.setBounds(74, 260, 489, 87);
		panel_print_key_direction.add(Label_left_right_direction);
		
		JLabel Label_text_key_direction = new JLabel("<\uC870\uC791\uD0A4 \uC124\uBA85>");
		Label_text_key_direction.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_key_direction.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_text_key_direction.setBounds(0, 0, 643, 151);
		panel_print_key_direction.add(Label_text_key_direction);
		
		JLabel Label_down_direction = new JLabel("\u25BC");
		Label_down_direction.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 50));
		Label_down_direction.setBounds(130, 350, 72, 106);
		panel_print_key_direction.add(Label_down_direction);
		
		JLabel Label_press_A = new JLabel("\uD0C4\uB9C9\uC740 \uC790\uB3D9\uC73C\uB85C \uBC1C\uC0AC\uB429\uB2C8\uB2E4");
		Label_press_A.setHorizontalAlignment(SwingConstants.CENTER);
		Label_press_A.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 50));
		Label_press_A.setBounds(28, 450, 572, 87);
		panel_print_key_direction.add(Label_press_A);
		
		JLabel Label_press_S = new JLabel("S: \uD3ED\uD0C4");
		Label_press_S.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.PLAIN, 50));
		Label_press_S.setBounds(74, 550, 489, 87);
		panel_print_key_direction.add(Label_press_S);
		
		JButton Button_game_start = new JButton("\uAC8C\uC784 \uC2DC\uC791");
		Button_game_start.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Button_game_start.setBounds(694, 359, 501, 92);
		panel_title_screen.add(Button_game_start);
		
		JButton Button_secret_code = new JButton("\uC2DC\uD06C\uB9BF \uCF54\uB4DC");
		Button_secret_code.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Button_secret_code.setBounds(694, 769, 501, 92);
		panel_title_screen.add(Button_secret_code);
		Button_secret_code.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel_title_screen.setVisible(false);
				panel_secret_code.setVisible(true);
			}
		});
		
		Button_game_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel_title_screen.setVisible(false);
				panel_main_screen.setVisible(true);
				panel_ending_credit.setVisible(false);
				start = true;
			}
		});
		
		// set panel_main_screen(game screen)
		panel_main_screen.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(panel_main_screen);
		panel_main_screen.setLayout(null);
		
		//JPanel panel_game_screen = new JPanel();
		panel_game_screen.setBounds(0, 0, 1264, 985);
		panel_main_screen.add(panel_game_screen);
		panel_game_screen.setLayout(null);
		
		JLabel Label_text_stage = new JLabel("STAGE");
		Label_text_stage.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_stage.setBounds(20, 910, 106, 52);
		panel_game_screen.add(Label_text_stage);
		
		Label_stage.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_stage.setBounds(127, 910, 49, 52);
		panel_game_screen.add(Label_stage);
		
		JLabel Label_text_lifes = new JLabel("LIFE(S)");
		Label_text_lifes.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_lifes.setBounds(300, 908, 106, 52);
		panel_game_screen.add(Label_text_lifes);
		
		Label_lifes.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_lifes.setBounds(410, 908, 85, 52);
		panel_game_screen.add(Label_lifes);
		
		JLabel Label_text_bomb = new JLabel("BOMB");
		Label_text_bomb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_bomb.setBounds(550, 910, 96, 52);
		panel_game_screen.add(Label_text_bomb);
		
		Label_bomb1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_bomb1.setBounds(650, 908, 37, 52);
		panel_game_screen.add(Label_bomb1);
		
		Label_bomb2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_bomb2.setBounds(680, 908, 37, 52);
		panel_game_screen.add(Label_bomb2);
		
		Label_bomb3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_bomb3.setBounds(710, 908, 37, 52);
		panel_game_screen.add(Label_bomb3);
		
		Label_bomb4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_bomb4.setBounds(740, 908, 37, 52);
		panel_game_screen.add(Label_bomb4);
		
		Label_bomb5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_bomb5.setBounds(770, 908, 37, 52);
		panel_game_screen.add(Label_bomb5);
		
		JLabel Label_text_score = new JLabel("SCORE");
		Label_text_score.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_score.setBounds(902, 910, 106, 52);
		panel_game_screen.add(Label_text_score);
		
		Label_score.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_score.setBounds(1011, 910, 241, 52);
		panel_game_screen.add(Label_score);
		
		Label_player.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		Label_player.setBounds(590, 860, 85, 35);
		
		panel_game_screen.add(Label_player);
		Label_1stage_boss.setFont(Label_1stage_boss.getFont().deriveFont(200f));
		Label_1stage_boss.setBounds(539, 57, 190, 150);
		panel_game_screen.add(Label_1stage_boss);
		
		Label_text_hp.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_hp.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_hp.setBounds(550, 10, 96, 35);
		Label_text_hp.setVisible(false);
		panel_game_screen.add(Label_text_hp);
		
		Label_hp.setHorizontalAlignment(SwingConstants.CENTER);
		Label_hp.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_hp.setBounds(629, 10, 69, 35);
		Label_hp.setVisible(false);
		panel_game_screen.add(Label_hp);
		
		Label_text_gameover.setFont(new Font("Agency FB", Font.BOLD, 99));
		Label_text_gameover.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_gameover.setBounds(365, 348, 533, 161);
		panel_game_screen.add(Label_text_gameover);
		Label_text_gameover.setVisible(false);
		
		Label_1stage_boss.setVisible(false);
		
		panel_ending_credit.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(panel_ending_credit);
		panel_ending_credit.setLayout(null);
		
		panel_itemshop.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(panel_itemshop);
		panel_itemshop.setLayout(null);
		
		JLabel Label_text_itemshop = new JLabel("ITEM SHOP");
		Label_text_itemshop.setFont(new Font("Agency FB", Font.BOLD, 99));
		Label_text_itemshop.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_itemshop.setBounds(0, 20, 1264, 148);
		panel_itemshop.add(Label_text_itemshop);
		
		JPanel panel_items = new JPanel();
		panel_items.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_items.setBounds(73, 180, 1092, 575);
		panel_itemshop.add(panel_items);
		panel_items.setLayout(new GridLayout(5, 3, 3, 3));
		
		JLabel Label_text_item_name = new JLabel("\uC544\uC774\uD15C");
		Label_text_item_name.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_text_item_name.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(Label_text_item_name);
		
		JLabel Label_text_price = new JLabel("\uAC00\uACA9");
		Label_text_price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_text_price.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(Label_text_price);
		
		JLabel Label_text_buy = new JLabel("\uAD6C\uB9E4");
		Label_text_buy.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		Label_text_buy.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(Label_text_buy);
		
		Label_text_power_up.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_power_up.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		panel_items.add(Label_text_power_up);
		
		JLabel Label_text_power_up_item_price = new JLabel("100,000");
		Label_text_power_up_item_price.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_power_up_item_price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		panel_items.add(Label_text_power_up_item_price);
		
		JButton Button_BUY_1 = new JButton("BUY");
		Button_BUY_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(score >= 100000) // have enough score
				{
					if(power >= 15)
					{
						Label_massage.setText("ÇöÀç ÆÄ¿ö°¡ 14ÀÌÇÏÀÏ ¶§¸¸ ±¸¸ÅÇÒ ¼ö ÀÖ½À´Ï´Ù");
					}
					else
					{
						score -= 100000;
						power++;
						Label_massage.setText("ÆÄ¿ö¾÷ ¾ÆÀÌÅÛÀ» ±¸¸ÅÇß½À´Ï´Ù");
						Label_current_score.setText(Integer.toString(score));
						String str = "ÆÄ¿ö¾÷(ÇöÀç ÆÄ¿ö: " + Integer.toString(power) + ")";
						Label_text_power_up.setText(str);
						
						if(power == 15)
						{
							side_attack = true;
						}
					}
				}
				else
				{
					Label_massage.setText("Á¡¼ö°¡ ºÎÁ·ÇÕ´Ï´Ù");
				}
			}
		});
		Button_BUY_1.setFont(new Font("Arial", Font.BOLD, 50));
		panel_items.add(Button_BUY_1);
		
		Label_text_bomb_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_bomb_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(Label_text_bomb_1);
		
		JLabel Label_text_bomb_item_price = new JLabel("30,000");
		Label_text_bomb_item_price.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_bomb_item_price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		panel_items.add(Label_text_bomb_item_price);
		
		JButton Button_BUY_2 = new JButton("BUY");
		Button_BUY_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(score >= 30000) // have enough score
				{
					if(bomb >= 5)
					{
						Label_massage.setText("ÆøÅºÀº ÃÖ´ë 5°³±îÁö¸¸ ¼ÒÁö °¡´ÉÇÕ´Ï´Ù");
					}
					else
					{
						score -= 30000;
						Label_massage.setText("ÆøÅº ¾ÆÀÌÅÛÀ» ±¸¸ÅÇß½À´Ï´Ù");
						increase_bomb();
						Label_current_score.setText(Integer.toString(score));
						String str = "ÆøÅº(ÇöÀç ¼ÒÁö°³¼ö: " + Integer.toString(bomb) + ")";
						Label_text_bomb_1.setText(str);
					}
				}
				else
				{
					Label_massage.setText("Á¡¼ö°¡ ºÎÁ·ÇÕ´Ï´Ù");
				}
			}
		});
		Button_BUY_2.setFont(new Font("Arial", Font.BOLD, 50));
		panel_items.add(Button_BUY_2);
		
		Label_text_life.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_life.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		panel_items.add(Label_text_life);
		
		JLabel Label_text_life_item_price = new JLabel("200,000");
		Label_text_life_item_price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_life_item_price.setHorizontalAlignment(SwingConstants.CENTER);
		panel_items.add(Label_text_life_item_price);
		
		JButton Button_BUY_3 = new JButton("BUY");
		Button_BUY_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(score >= 200000) // have enough score
				{
					score -= 200000;
					Label_massage.setText("¶óÀÌÇÁ ¾ÆÀÌÅÛÀ» ±¸¸ÅÇß½À´Ï´Ù");
					lifes++;
					Label_current_score.setText(Integer.toString(score));
					String str = "¶óÀÌÇÁ(ÇöÀç ¶óÀÌÇÁ: " + Integer.toString(lifes) + ")";
					Label_text_life.setText(str);
				}
				else
				{
					Label_massage.setText("Á¡¼ö°¡ ºÎÁ·ÇÕ´Ï´Ù");
				}
			}
		});
		Button_BUY_3.setFont(new Font("Arial", Font.BOLD, 50));
		panel_items.add(Button_BUY_3);
		
		JLabel Label_text_shield = new JLabel("\uBC29\uC5B4\uB9C9(3\uD68C \uBC29\uC5B4)");
		Label_text_shield.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_shield.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		panel_items.add(Label_text_shield);
		
		JLabel Label_text_shield_item_price = new JLabel("100,000");
		Label_text_shield_item_price.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_shield_item_price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		panel_items.add(Label_text_shield_item_price);
		
		JButton Button_BUY_4 = new JButton("BUY");
		Button_BUY_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(score >= 100000) // have enough score
				{
					if(power >= 15)
					{
						if(shield_barrier < 3)
						{
							score -= 100000;
							Label_massage.setText("¹æ¾î¸· ¾ÆÀÌÅÛÀ» ±¸¸ÅÇß½À´Ï´Ù");
							Label_current_score.setText(Integer.toString(score));
							shield_barrier = 3;
						}
						else
						{
							Label_massage.setText("¹æ¾î¸·À» ÀÌ¹Ì °¡Áö°í ÀÖ½À´Ï´Ù");
						}
					}
					else
					{
						Label_massage.setText("¹æ¾î¸·Àº ÆÄ¿ö°¡ 15ÀÌ»óÀÏ °æ¿ì¿¡¸¸ ±¸¸Å°¡´ÉÇÕ´Ï´Ù");
					}
				}
				else
				{
					Label_massage.setText("Á¡¼ö°¡ ºÎÁ·ÇÕ´Ï´Ù");
				}
			}
		});
		Button_BUY_4.setFont(new Font("Arial", Font.BOLD, 50));
		panel_items.add(Button_BUY_4);
		
		JButton Button_go_to_next_stage = new JButton("\uC2A4\uD14C\uC774\uC9C0 \uC9C4\uD589 \u2192");
		Button_go_to_next_stage.addActionListener(new ActionListener() // exit
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_itemshop.setVisible(false);
				panel_main_screen.setVisible(true);
				shopping = false;
			}
		});
		Button_go_to_next_stage.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		Button_go_to_next_stage.setBounds(827, 765, 338, 177);
		panel_itemshop.add(Button_go_to_next_stage);
		
		Label_massage.setHorizontalAlignment(SwingConstants.LEFT);
		Label_massage.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_massage.setBounds(73, 858, 790, 84);
		panel_itemshop.add(Label_massage);
		
		JLabel Label_text_current_score = new JLabel("\uD604\uC7AC \uC810\uC218");
		Label_text_current_score.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_text_current_score.setHorizontalAlignment(SwingConstants.LEFT);
		Label_text_current_score.setBounds(73, 765, 140, 89);
		panel_itemshop.add(Label_text_current_score);
		
		Label_current_score.setFont(new Font("Arial", Font.BOLD, 30));
		Label_current_score.setBounds(220, 770, 221, 84);
		panel_itemshop.add(Label_current_score);
		panel_itemshop.setVisible(false);
		panel_secret_code.setVisible(false);
		
		panel_secret_code.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(panel_secret_code);
		panel_secret_code.setLayout(null);
		
		JButton Button_go_back_to_title_screen_1 = new JButton("\uD0C0\uC774\uD2C0 \uD654\uBA74\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
		Button_go_back_to_title_screen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel_secret_code.setVisible(false);
				panel_title_screen.setVisible(true);
			}
		});
		Button_go_back_to_title_screen_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		Button_go_back_to_title_screen_1.setBounds(380, 800, 502, 125);
		panel_secret_code.add(Button_go_back_to_title_screen_1);
		
		JLabel Label_text_secret_code = new JLabel("SECRET CODE");
		Label_text_secret_code.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_secret_code.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 99));
		Label_text_secret_code.setBounds(0, 37, 1264, 158);
		panel_secret_code.add(Label_text_secret_code);
		
		JLabel Label_text_input_password = new JLabel("\uC554\uD638\uB97C \uC785\uB825\uD558\uC138\uC694");
		Label_text_input_password.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		Label_text_input_password.setHorizontalAlignment(SwingConstants.CENTER);
		Label_text_input_password.setBounds(0, 220, 1264, 94);
		panel_secret_code.add(Label_text_input_password);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 30));
		textField.setBounds(468, 325, 332, 68);
		panel_secret_code.add(textField);
		textField.setColumns(10);
		
		JLabel Label_message = new JLabel("\uCF54\uB4DC\uAC00 \uC801\uC6A9\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		Label_message.setHorizontalAlignment(SwingConstants.CENTER);
		Label_message.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		Label_message.setBounds(468, 514, 332, 61);
		panel_secret_code.add(Label_message);
		Label_message.setVisible(false);
		
		JButton Button_ok = new JButton("\uD655\uC778");
		Button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String[] pass_codes = { max_power, max_fire_speed };
				for(int i = 0; i < 2; i++)
				{
					if(pass_codes[i].equals(textField.getText()))
					{
						Label_message.setText("ÄÚµå°¡ Àû¿ëµÇ¾ú½À´Ï´Ù");
						Label_message.setVisible(true);
						if(i == 0)
						{
							flag_max_power = true;
						}
						if(i == 1)
						{
							flag_max_fire_speed = true;
						}
						break;
					}
					else
					{
						Label_message.setText("ÆÐ½º¿öµå°¡ Æ²·È½À´Ï´Ù");
						Label_message.setVisible(true);
					}
				}
			}
		});
		
		Button_ok.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		Button_ok.setBounds(535, 424, 197, 51);
		panel_secret_code.add(Button_ok);
		
		// set panel_credit
		panel_credit.setBounds(0, 0, 1264, 985);
		frame.getContentPane().add(panel_credit);
		panel_credit.setLayout(null);
		panel_credit.setVisible(false);
		
		JLabel Label_developer = new JLabel("Developer: Lee Seung-Bin");
		Label_developer.setHorizontalAlignment(SwingConstants.CENTER);
		Label_developer.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 80));
		Label_developer.setBounds(30, 10, 1194, 250);
		panel_credit.add(Label_developer);
		
		JLabel Label_credit1 = new JLabel("<\uAC1C\uBC1C\uC790 \uD55C\uB9C8\uB514>");
		Label_credit1.setHorizontalAlignment(SwingConstants.CENTER);
		Label_credit1.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.BOLD, 60));
		Label_credit1.setBounds(30, 270, 1194, 146);
		panel_credit.add(Label_credit1);
		
		JLabel Label_credit2 = new JLabel("\uC800\uC758 2\uBC88\uC9F8 \uD504\uB85C\uC81D\uD2B8\uC785\uB2C8\uB2E4.");
		Label_credit2.setHorizontalAlignment(SwingConstants.CENTER);
		Label_credit2.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.BOLD, 40));
		Label_credit2.setBounds(30, 420, 1194, 125);
		panel_credit.add(Label_credit2);
		
		JLabel Label_credit3 = new JLabel("\uC7AC\uBBF8\uC788\uAC8C \uD50C\uB808\uC774 \uD574\uC8FC\uC168\uC73C\uBA74 \uC88B\uACA0\uC2B5\uB2C8\uB2E4.");
		Label_credit3.setHorizontalAlignment(SwingConstants.CENTER);
		Label_credit3.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.BOLD, 40));
		Label_credit3.setBounds(30, 555, 1194, 125);
		panel_credit.add(Label_credit3);
		
		JLabel Label_credit4 = new JLabel("\uAC10\uC0AC\uD569\uB2C8\uB2E4.");
		Label_credit4.setHorizontalAlignment(SwingConstants.CENTER);
		Label_credit4.setFont(new Font("ÈÞ¸ÕÆíÁöÃ¼", Font.BOLD, 40));
		Label_credit4.setBounds(30, 670, 1194, 125);
		panel_credit.add(Label_credit4);
		
		JButton Button_go_back_to_title_screen = new JButton("\uD0C0\uC774\uD2C0 \uD654\uBA74\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
		Button_go_back_to_title_screen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel_credit.setVisible(false);
				panel_title_screen.setVisible(true);
			}
		});
		Button_go_back_to_title_screen.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		Button_go_back_to_title_screen.setBounds(380, 800, 502, 125);
		panel_credit.add(Button_go_back_to_title_screen);
		
		class key implements KeyListener
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				Point p = Label_player.getLocation();
				if(e.getKeyCode() == 37 && p.x > 25) // move left
				{
					Label_player.setLocation(p.x - 30, p.y);
				}
				if(e.getKeyCode() == 39 && p.x < 1170) // move right
				{
					Label_player.setLocation(p.x + 30, p.y);
				}
				if(e.getKeyCode() == 38 && p.y > 25) // move up
				{
					Label_player.setLocation(p.x, p.y - 30);
				}
				if(e.getKeyCode() == 40 && p.y < 870) // move down
				{
					Label_player.setLocation(p.x, p.y + 30);
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				Point p = Label_player.getLocation();
				if(e.getKeyCode() == 37 && p.x > 25) // move left
				{
					Label_player.setLocation(p.x - 20, p.y);
				}
				if(e.getKeyCode() == 39 && p.x < 1170) // move right
				{
					Label_player.setLocation(p.x + 20, p.y);
				}
				if(e.getKeyCode() == 38 && p.y >= 600) // move up
				{
					Label_player.setLocation(p.x, p.y - 20);
				}
				if(e.getKeyCode() == 40 && p.y < 870) // move down
				{
					Label_player.setLocation(p.x, p.y + 20);
				}
				if(e.getKeyCode() == 83 || e.getKeyCode() == 115) // bomb
				{
					if(bomb > 0 && used_bomb == false)
					{
						used_bomb = true;
						decrease_bomb();
						use_bomb();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				//keyboard = KEYBOARD.NULL;
			}
		}
		
		frame.addKeyListener(new key());
        frame.setFocusable(true);
	} // end of private void initialize()

	private static void set_stage(int val)
	{
		Label_stage.setText(Integer.toString(val));
	}	
	
	private static void set_lifes()
	{
		Label_lifes.setText(Integer.toString(lifes));
	}
	
	private static boolean check_gameover()
	{
		if(lifes == 0)
		{
			Label_player.setVisible(false);
			for(int i = 0; i < max_player_bullet; i++)
			{
				bullet_player[i].label.setVisible(false);
			}
			Label_text_gameover.setVisible(true);
			wait(99999);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private static void increase_score(int val)
	{
		score += val;
		Label_score.setText(Integer.toString(score));
	}
	
	private static void decrease_bomb()
	{		
		switch(bomb)
		{
		case 5:
			Label_bomb5.setVisible(false);
			break;
		case 4:
			Label_bomb4.setVisible(false);
			break;
		case 3:
			Label_bomb3.setVisible(false);
			break;
		case 2:
			Label_bomb2.setVisible(false);
			break;
		case 1:
			Label_bomb1.setVisible(false);
			break;
		default:
			break;
		}
	}
	
	private static void increase_bomb()
	{				
		switch(bomb)
		{
		case 0:
			Label_bomb1.setVisible(true);
			break;
		case 1:
			Label_bomb2.setVisible(true);
			break;
		case 2:
			Label_bomb3.setVisible(true);
			break;
		case 3:
			Label_bomb4.setVisible(true);
			break;
		case 4:
			Label_bomb5.setVisible(true);
			break;
		default:
			increase_score(5000);
			bomb = 5;
			break;
		}
		
		if(bomb <= 4)
		{
			bomb++; // increase bomb
		}
	}
	
	private static void set_initial_bomb()
	{
		bomb = 2;
		Label_bomb1.setVisible(true);
		Label_bomb2.setVisible(true);
		Label_bomb3.setVisible(false);
		Label_bomb4.setVisible(false);
		Label_bomb5.setVisible(false);
	}
	
	private static void player_killed()
	{
		Label_player.setVisible(false); // killed
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setVisible(false);
		}
		
		lifes--;
		set_lifes();
		set_initial_bomb();
		invincibility = true;
		Label_player.setLocation(590, 860); // set player to initial location
		Label_player.setVisible(true);
	} // end of private static void player_killed()
	
	private static void player_decrease_shield_barrier()
	{
		if(shield_barrier > 0)
		{
			shield_barrier--;
		}
		invincibility = true;
	} // end of private static void player_decrease_shield_barrier()
	
	public static void wait(int time)
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	} // end of public void wait(int time)
	
	private static void player_bullet_move()
	{
		for(int i = 0; i < max_player_bullet; i++)
		{
			Point p1 = bullet_player[i].label.getLocation();
			if(p1.y > 0) // bullet will move to upward
			{
				bullet_player[i].label.setLocation(p1.x + bullet_player[i].move_x, p1.y + bullet_player[i].move_y);
			}
		}
		
		for(int i = 0; i < max_player_bullet; i++)
		{
			Point p1 = bullet_player[i].label.getLocation();
			if(p1.y <= 0)
			{
				bullet_player[i].label.setLocation(0, 0);
				bullet_player[i].label.setVisible(false);
			}
		}
		
		if(power >= 10)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2[i].label.getLocation();
				if(p1.y > 0) // bullet will move to upward
				{
					bullet_player_2[i].label.setLocation(p1.x + bullet_player_2[i].move_x, p1.y + bullet_player_2[i].move_y);
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2[i].label.getLocation();
				if(p1.y <= 0)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
				}
			}
		}
		
		if(power >= 15)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2_left[i].label.getLocation();
				if(p1.y > 0) // bullet will move to upward
				{
					bullet_player_2_left[i].label.setLocation(p1.x + bullet_player_2_left[i].move_x, p1.y + bullet_player_2_left[i].move_y);
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2_left[i].label.getLocation();
				if(p1.y <= 0)
				{
					bullet_player_2_left[i].label.setLocation(0, 0);
					bullet_player_2_left[i].label.setVisible(false);
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2_right[i].label.getLocation();
				if(p1.y > 0) // bullet will move to upward
				{
					bullet_player_2_right[i].label.setLocation(p1.x + bullet_player_2_right[i].move_x, p1.y + bullet_player_2_right[i].move_y);
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2_right[i].label.getLocation();
				if(p1.y <= 0)
				{
					bullet_player_2_right[i].label.setLocation(0, 0);
					bullet_player_2_right[i].label.setVisible(false);
				}
			}
		}
	} // end of private static void player_bullet_move()
	
	private static void player_fire_bullet()
	{
		// auto fire
		if(frame_count == fire_speed && player_died == false)
		{
			auto_fire();
			frame_count = 0;
		}
		
		player_bullet_move();
	} // end of private static void player_fire_bullet()
	
	private static void auto_fire()
	{
		Point p = Label_player.getLocation(); // get player position
		
		// auto fire
		if(power < 3)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_player[i].label.setLocation(p.x + 28, p.y - 30); // create bullet
					bullet_player[i].label.setVisible(true); 
					break;
				}
			}
		}
		else if(power < 5)
		{
			for(int i = 0; i < max_player_bullet; i += 2)
			{
				Point p1 = bullet_player[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_player[i].label.setLocation(p.x + 10, p.y - 30); // create bullet
					bullet_player[i + 1].label.setLocation(p.x + 45, p.y - 30); // create bullet
					bullet_player[i].label.setVisible(true); 
					bullet_player[i + 1].label.setVisible(true); 
					break;
				}
			}
		}
		else if (power < 10)
		{
			for(int i = 0; i < max_player_bullet; i += 3)
			{
				Point p1 = bullet_player[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_player[i].label.setLocation(p.x, p.y - 30); // create bullet
					bullet_player[i + 1].label.setLocation(p.x + 25, p.y - 30); // create bullet
					bullet_player[i + 2].label.setLocation(p.x + 50, p.y - 30); // create bullet
					bullet_player[i].label.setVisible(true); 
					bullet_player[i + 1].label.setVisible(true); 
					bullet_player[i + 2].label.setVisible(true);
					break;
				}
			}
		}
		
		else if(power < 15)
		{
			for(int i = 0; i < max_player_bullet; i += 3)
			{
				Point p1 = bullet_player_2[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_player_2[i].label.setLocation(p.x, p.y - 30); // create bullet
					bullet_player_2[i + 1].label.setLocation(p.x + 25, p.y - 30); // create bullet
					bullet_player_2[i + 2].label.setLocation(p.x + 50, p.y - 30); // create bullet
					bullet_player_2[i].label.setVisible(true); 
					bullet_player_2[i + 1].label.setVisible(true); 
					bullet_player_2[i + 2].label.setVisible(true);
					break;
				}
			}
		}
		
		else if(power < 25)
		{
			for(int i = 0; i < max_player_bullet; i += 3)
			{
				Point p1 = bullet_player_2[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_player_2[i].label.setLocation(p.x, p.y - 30); // create bullet
					bullet_player_2[i + 1].label.setLocation(p.x + 25, p.y - 30); // create bullet
					bullet_player_2[i + 2].label.setLocation(p.x + 50, p.y - 30); // create bullet
					bullet_player_2[i].label.setVisible(true); 
					bullet_player_2[i + 1].label.setVisible(true); 
					bullet_player_2[i + 2].label.setVisible(true);
					break;
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p1 = bullet_player_2_left[i].label.getLocation(); // get bullet_player position
				
				if(p1.y == 0) // not used bullet
				{
					bullet_player_2_left[i].label.setLocation(p.x - 25, p.y - 30); // create bullet
					bullet_player_2_left[i].label.setVisible(true); 
					break;
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				Point p2 = bullet_player_2_right[i].label.getLocation(); // get bullet_player position
				if(p2.y == 0) // not used bullet
				{
					bullet_player_2_right[i].label.setLocation(p.x + 75, p.y - 30); // create bullet
					bullet_player_2_right[i].label.setVisible(true); 
					break;
				}
			}			
		}
		
	} // end of private static void auto_fire()
	
	private static void set_player_invincibility_package()
	{
		if(invincibility == true)
		{
			frame_invincibility++;
			if(frame_invincibility >= invincibility_time)
			{
				invincibility = false;
				frame_invincibility = 0;
			}
		}
	} // end of set_player_invincibility_package()
	
	private static void check_player_invincibility()
	{
		if(invincibility == true)
		{
			Label_player.setForeground(Color.YELLOW);
		}
		else
		{
			Label_player.setForeground(Color.BLACK);
		}
	} // end of private static void check_player_invincibility()
	
	private static void enemy_bullet_move(Bullet[] bullet_enemy)
	{
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y < 900 && p1.y != 0) // bullet will move to downward
			{
				bullet_enemy[i].label.setLocation(p1.x + bullet_enemy[i].move_x, p1.y + bullet_enemy[i].move_y);
				
			}
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y >= 900)
			{
				bullet_enemy[i].label.setLocation(1200, 0);
				bullet_enemy[i].label.setVisible(false);
			}
		}
	} // end of private static void enemy_bullet_move(Bullet[] bullet_enemy)
	
	private static void enemy_bullet_move(Bullet[] bullet_enemy, int index)
	{
		for(int i = 0; i < index; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y < 900 && p1.y != 0) // bullet will move to downward
			{
				bullet_enemy[i].label.setLocation(p1.x + bullet_enemy[i].move_x, p1.y + bullet_enemy[i].move_y);
				
			}
		}
		
		for(int i = 0; i < index; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y >= 900 || p1.x >= 1200)
			{
				bullet_enemy[i].label.setLocation(1200, 0);
				bullet_enemy[i].label.setVisible(false);
			}
		}
	} // end of private static void enemy_bullet_move(Bullet[] bullet_enemy)
	
	private static void enemy_bullet_move(Bullet_Radian[] bullet_enemy)
	{
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y < 900 && p1.y != 0) // bullet will move to downward
			{
				bullet_enemy[i].move();
			}
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y >= 900 || p1.y <= 0)
			{
				bullet_enemy[i].label.setLocation(1200, 0);
				bullet_enemy[i].label.setVisible(false);
			}
		}
	} // end of private static void enemy_bullet_move(Bullet_Radian[] bullet_enemy)
	
	private static void enemy_bullet_move(Bullet_Radian[] bullet_enemy, int index)
	{
		for(int i = 0; i < index; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y < 900 && p1.y != 0) // bullet will move to downward
			{
				bullet_enemy[i].move();
			}
		}
		
		for(int i = 0; i < index; i++)
		{
			Point p1 = bullet_enemy[i].label.getLocation();
			if(p1.y >= 900 || p1.y <= 0)
			{
				bullet_enemy[i].label.setLocation(1200, 0);
				bullet_enemy[i].label.setVisible(false);
			}
		}
	} // end of private static void enemy_bullet_move(Bullet_Radian[] bullet_enemy, int index)
	
	private static void use_bomb()
	{		
		for(int i = 0; i < 25; i++)
		{
			for(int j = 0; j < max_bomb_bullet; j++)
			{
				Point p = bullet_bomb[j].label.getLocation(); // get bullet_player position
				if(p.y == 0) // not used bullet
				{
					bullet_bomb[j].label.setLocation(10 + i * 50, 900); // create bullet
					bullet_bomb[j].label.setVisible(true);
					break;
				}
			}
		}
	} // end of private static void use_bomb()
	
	private static void use_bomb_package()
	{
		if(bomb > 0 && used_bomb == true)
		{
			invincibility = true;
			if(bomb_fire_count % 100 == 0)
			{
				use_bomb();
			}
			
			for(int i = 0; i < max_bomb_bullet; i++)
			{
				Point p = bullet_bomb[i].label.getLocation();
				if(p.y > 0) // bullet will move to upward
				{
					bullet_bomb[i].label.setLocation(p.x, p.y - 10);
				}
			}
			
			for(int i = 0; i < max_bomb_bullet; i++)
			{
				Point p = bullet_bomb[i].label.getLocation();
				if(p.y <= 0)
				{
					bullet_bomb[i].label.setLocation(0, 0);
					bullet_bomb[i].label.setVisible(false);
				}
			}
			bomb_fire_count++;
			
			if(bomb_fire_count >= 600)
			{
				bomb--;
				used_bomb = false;
				bomb_fire_count = 0;
				invincibility = false;
				
				for(int i = 0; i < max_bomb_bullet; i++)
				{
					bullet_bomb[i].label.setLocation(0, 0);
					bullet_bomb[i].label.setVisible(false);
				}
			}
		}
	} // end of private static void use_bomb_package()
	
	private static void power_moving()
	{
		int x = power_up_item.posX;
		int y = power_up_item.posY + 2;
		power_up_item.label.setLocation(x, y);
		power_up_item.posY = y;
	} // end of private static void power_moving()
	
	private static void barrier_item_moving()
	{
		int x = barrier_item.posX;
		int y = barrier_item.posY + 2;
		barrier_item.label.setLocation(x, y);
		barrier_item.posY = y;
	} // end of private static void barrier_item_moving()
	
	private static void check_player_get_power()
	{
		player = Label_player.getLocation();
		// Point player = Label_player.getLocation();
		Point p = power_up_item.label.getLocation();
		if(player.x >= p.x - 70 && player.x <= p.x + 70 && player.y >= p.y - 50 && player.y <= p.y + 50)
		{
			System.out.println("POWER UP");
			power++;
			power_up_item.label.setLocation(0, 0);
			power_up_item.label.setVisible(false);
			power_up_item.posX = 0;
			power_up_item.posY = 0;
			
			if(power == 10) // damage up
			{
				player_damage = 2;
			}
			
			if(power == 15)
			{
				side_attack = true;
			}
		}
		
		if(p.y >= 1000)
		{
			power_up_item.label.setLocation(0, 0);
			power_up_item.label.setVisible(false);
			power_up_item.posX = 0;
			power_up_item.posY = 0;
			//drop_power = false;
		}
	} // end of private static void check_player_get_power()
	
	private static void check_player_get_barrier()
	{
		Point player = Label_player.getLocation();
		Point p = barrier_item.label.getLocation();
		if(player.x >= p.x - 70 && player.x <= p.x + 70 && player.y >= p.y - 50 && player.y <= p.y + 50)
		{
			System.out.println("SHIELD UP");
			if(shield_barrier < 3)
			{
				shield_barrier++;
			}
			
			else
			{
				score += 10000;
			}
			
			barrier_item.label.setLocation(0, 0);
			barrier_item.label.setVisible(false);
			barrier_item.posX = 0;
			barrier_item.posY = 0;
		}
		
		if(p.y >= 1000)
		{
			barrier_item.label.setLocation(0, 0);
			barrier_item.label.setVisible(false);
			barrier_item.posX = 0;
			barrier_item.posY = 0;
		}
	} // end of private static void check_player_get_power()
	
	private static boolean attack_enemy(Enemy[] enemy, int enemy_attack_frame_count, final int enemy_fire_speed, Bullet[] bullet_enemy, int start_index, int end_index, int x, int y)
	{
		if(enemy_attack_frame_count == enemy_fire_speed)
		{						
			for(int i = start_index; i < end_index; i++)
			{
				if(enemy[i].survive == false)
				{
					continue;
				}
				
				Point p = enemy[i].label.getLocation(); // get enemy position
				
				for(int j = 0; j < max_enemy_bullet; j++)
				{
					Point p1 = bullet_enemy[j].label.getLocation(); // get bullet_player position
					if(p1.y == 0) // not used bullet
					{
						bullet_enemy[j].label.setLocation(p.x + x, p.y + y); // create bullet
						bullet_enemy[j].label.setVisible(true); 
						break;
					}
				}
			}
			return true;
		}
		return false;
	} // end of private static void attack_enemy(Enemy[] enemy, int enemy_attack_frame_count, final int enemy_fire_speed, Bullet[] bullet_enemy, int start_index, int end_index, int x, int y)
	
	private static boolean attack_enemy(Enemy enemy, int enemy_attack_frame_count, final int enemy_fire_speed, Bullet[] bullet_enemy, int x, int y)
	{
		if(enemy_attack_frame_count == enemy_fire_speed)
		{						
			if(enemy.survive == false)
			{
				return false;
			}
			
			Point p = enemy.label.getLocation(); // get enemy position
			
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				Point p1 = bullet_enemy[j].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_enemy[j].label.setLocation(p.x + x, p.y + y); // create bullet
					bullet_enemy[j].label.setVisible(true); 
					break;
				}
			}
			return true;
		}
		return false;
	} // end of private static boolean attack_enemy(Enemy enemy, int enemy_attack_frame_count, final int enemy_fire_speed, Bullet[] bullet_enemy, int x, int y)
	
	private static void attack_enemy(Enemy enemy, Bullet[] bullet_enemy, int x, int y)
	{
		if(enemy.survive == true)
		{
			Point p = enemy.label.getLocation(); // get enemy position	
			for(int i = 0; i < max_enemy_bullet; i++)
			{
				Point p1 = bullet_enemy[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_enemy[i].label.setLocation(p.x + x, p.y + y); // create bullet
					bullet_enemy[i].label.setVisible(true); 
					break;
				}
			}
		}
	} // end of private static boolean attack_enemy(Enemy enemy, final int enemy_fire_speed, Bullet[] bullet_enemy, int x, int y)
	
	private static void attack_enemy(Enemy enemy, Bullet[] bullet_enemy, int x, int y, int index)
	{
		if(enemy.survive == true)
		{
			Point p = enemy.label.getLocation(); // get enemy position	
			for(int i = 0; i < index; i++)
			{
				Point p1 = bullet_enemy[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_enemy[i].label.setLocation(p.x + x, p.y + y); // create bullet
					bullet_enemy[i].label.setVisible(true); 
					break;
				}
			}
		}
	} // end of private static boolean attack_enemy(Enemy enemy, final int enemy_fire_speed, Bullet[] bullet_enemy, int x, int y, int index)
	
	private static void attack_enemy(Enemy enemy, Bullet_Radian[] bullet_enemy, int x, int y)
	{
		if(enemy.survive == true)
		{
			Point p = enemy.label.getLocation(); // get enemy position	
			for(int i = 0; i < max_enemy_bullet; i++)
			{
				Point p1 = bullet_enemy[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_enemy[i].x = (float)(p.x + x);
					bullet_enemy[i].y = (float)(p.y + y);
					bullet_enemy[i].label.setLocation(p.x + x, p.y + y); // create bullet
					bullet_enemy[i].label.setVisible(true); 
					break;
				}
			}
		}
	} // end of private static boolean attack_enemy(Enemy enemy, final int enemy_fire_speed, Bullet[] bullet_enemy, int x, int y)
	
	private static void attack_enemy(Enemy enemy, Bullet_Radian[] bullet_enemy, int x, int y, int index)
	{
		if(enemy.survive == true)
		{
			Point p = enemy.label.getLocation(); // get enemy position	
			for(int i = 0; i < index; i++)
			{
				Point p1 = bullet_enemy[i].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_enemy[i].x = (float)(p.x + x);
					bullet_enemy[i].y = (float)(p.y + y);
					bullet_enemy[i].label.setLocation(p.x + x, p.y + y); // create bullet
					bullet_enemy[i].label.setVisible(true); 
					break;
				}
			}
		}
	} // end of private static void attack_enemy(Enemy enemy, Bullet_Radian[] bullet_enemy, int x, int y, int index)
	
	private static void attack_enemy(Bullet_Radian[] src_bullet_enemy, Bullet_Radian[] bullet_enemy, int x, int y, int index)
	{
		for(int i = 0; i < index; i++)
		{
			Point p = src_bullet_enemy[i].label.getLocation(); // get enemy position
			for(int j = 0; j < max_enemy_bullet; j++)
			{	
				Point p1 = bullet_enemy[j].label.getLocation(); // get bullet_player position
				if(p1.y == 0) // not used bullet
				{
					bullet_enemy[j].x = (float)(p.x + x);
					bullet_enemy[j].y = (float)(p.y + y);
					bullet_enemy[j].label.setLocation(p.x + x, p.y + y); // create bullet
					bullet_enemy[j].label.setVisible(true); 
					break;
				}
			}
		}
	} // end of private static boolean attack_enemy(Bullet_Radian[] src_bullet_enemy, Bullet_Radian[] bullet_enemy, int x, int y)
	
	private static int check_enemy_damaged(Enemy enemy)
	{		
		Point p2 = enemy.label.getLocation();
		int damage = 0;
		
		if(used_bomb == true)
		{
			Point bp1 = bullet_bomb[0].label.getLocation();
			for(int i = 0; i < max_bomb_bullet; i++)
			{
				bp1 = bullet_bomb[i].label.getLocation();
				if(bp1.x >= p2.x - 10 && bp1.x <= p2.x + enemy.width && bp1.y < p2.y + enemy.height && bp1.y > p2.y - 20)
			    {
					return player_damage;
				}
			}
		}
		
		if(power < 3)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				bp3 = bullet_player[i].label.getLocation();								
				if(bp3.x >= p2.x - 10 && bp3.x <= p2.x + enemy.width && bp3.y < p2.y + enemy.height && bp3.y > p2.y - 20)
				{
					bullet_player[i].label.setLocation(0, 0);
					bullet_player[i].label.setVisible(false);
					return 1;
				}
			}
			return 0;
		}
		else if(power < 5)
		{
			for(int i = 0; i < max_player_bullet; i += 2)
			{
				bp3 = bullet_player[i].label.getLocation();				
				
				if(bp3.x >= p2.x - 10 && bp3.x <= p2.x + enemy.width && bp3.y < p2.y + enemy.height && bp3.y > p2.y - 20)
				{
					bullet_player[i].label.setLocation(0, 0);
					bullet_player[i].label.setVisible(false);
					damage++;
					break;
				}
			}
			
			for(int i = 1; i < max_player_bullet; i += 2)
			{
				bp3 = bullet_player[i].label.getLocation();				
								
				if(bp3.x >= p2.x - 10 && bp3.x <= p2.x + enemy.width && bp3.y < p2.y + enemy.height && bp3.y > p2.y - 20)
				{
					bullet_player[i].label.setLocation(0, 0);
					bullet_player[i].label.setVisible(false);
					damage++;
					break;
				}
			}
		} // end of else if(power < 5)
		else if (power < 10)
		{
			for(int i = 0; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage++;
					break;
				}
			}
			
			for(int i = 1; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage++;
					break;
				}
			}
			
			for(int i = 2; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage++;
					break;
				}
			}
		} // end of else if(power < 10)
		else if (power < 15)
		{
			for(int i = 0; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
			
			for(int i = 1; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
			
			for(int i = 2; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
		} // end of else if(power < 15)
		else if (power < 25)
		{
			for(int i = 0; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
								
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
			
			for(int i = 1; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
				
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
			
			for(int i = 2; i < max_player_bullet; i += 3)
			{
				bp4 = bullet_player_2[i].label.getLocation();				
				
				if(bp4.x >= p2.x - 10 && bp4.x <= p2.x + enemy.width && bp4.y < p2.y + enemy.height && bp4.y > p2.y - 20)
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				bp5 = bullet_player_2_left[i].label.getLocation();				
				
				if(bp5.x >= p2.x - 10 && bp5.x <= p2.x + enemy.width && bp5.y < p2.y + enemy.height && bp5.y > p2.y - 20)
				{
					bullet_player_2_left[i].label.setLocation(0, 0);
					bullet_player_2_left[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
			
			for(int i = 0; i < max_player_bullet; i++)
			{
				bp6 = bullet_player_2_right[i].label.getLocation();				
				
				if(bp6.x >= p2.x - 10 && bp6.x <= p2.x + enemy.width && bp6.y < p2.y + enemy.height && bp6.y > p2.y - 20)
				{
					bullet_player_2_right[i].label.setLocation(0, 0);
					bullet_player_2_right[i].label.setVisible(false);
					damage += 2;
					break;
				}
			}
		} // end of else if(power < 15)
		return damage;
	} // end of check_enemy_damaged(Enemy enemy)
	
	/*
	private static boolean check_enemy_damaged(Enemy enemy)
	{
		if(used_bomb == true)
		{
			for(int i = 0; i < max_bomb_bullet; i++)
			{
				Point p1 = bullet_bomb[i].label.getLocation();
				Point p2 = enemy.label.getLocation();
				if(p1.x >= p2.x - 10 && p1.x <= p2.x + enemy.width && p1.y < p2.y + enemy.height && p1.y > p2.y - 20)
			    {
				//	bullet_bomb[i].label.setLocation(0, 0);
				//	bullet_bomb[i].label.setVisible(false);
					return true;
				}
			}
		}
		
		for(int i = 0; i < max_player_bullet; i++)
		{
			Point p1 = null;
			if(power < 10)
			{
				p1 = bullet_player[i].label.getLocation();
			}
			else 
			{
				p1 = bullet_player_2[i].label.getLocation();
			}
			
			Point p2 = enemy.label.getLocation();
			
			if(p1.x >= p2.x - 10 && p1.x <= p2.x + enemy.width && p1.y < p2.y + enemy.height && p1.y > p2.y - 20)
			{
				if(power < 10)
				{
					bullet_player[i].label.setLocation(0, 0);
					bullet_player[i].label.setVisible(false);
				}
				else
				{
					bullet_player_2[i].label.setLocation(0, 0);
					bullet_player_2[i].label.setVisible(false);
				}
				return true;
			}
		}
		return false;
	} // end of check_enemy_damaged(Enemy enemy)
	
	
	private static boolean check_enemy_damaged_left(Enemy enemy) // power >= 15
	{
		for(int i = 0; i < max_player_bullet; i++)
		{
			Point p1 = bullet_player_2_left[i].label.getLocation();
			Point p2 = enemy.label.getLocation();
			
			if(p1.x >= p2.x - 10 && p1.x <= p2.x + enemy.width && p1.y < p2.y + enemy.height && p1.y > p2.y - 20)
			{
				if(power < 10)
				{
					bullet_player_2_left[i].label.setLocation(0, 0);
					bullet_player_2_left[i].label.setVisible(false);
					
				}
				else
				{
					bullet_player_2_left[i].label.setLocation(0, 0);
					bullet_player_2_left[i].label.setVisible(false);
				}
				return true;
			}
		}
		return false;
	} // end of private static boolean check_enemy_damaged_left(Enemy enemy)
	
	private static boolean check_enemy_damaged_right(Enemy enemy) // power >= 15
	{
		for(int i = 0; i < max_player_bullet; i++)
		{
			Point p1 = bullet_player_2_right[i].label.getLocation();
			Point p2 = enemy.label.getLocation();
			
			if(p1.x >= p2.x - 10 && p1.x <= p2.x + enemy.width && p1.y < p2.y + enemy.height && p1.y > p2.y - 20)
			{
				if(power < 10)
				{
					bullet_player_2_right[i].label.setLocation(0, 0);
					bullet_player_2_right[i].label.setVisible(false);
					
				}
				else
				{
					bullet_player_2_right[i].label.setLocation(0, 0);
					bullet_player_2_right[i].label.setVisible(false);
				}
				return true;
			}
		}
		return false;
	} // end of private static boolean check_enemy_damaged_left(Enemy enemy)
	*/
	
	private static boolean check_enemy_boss_damaged()
	{
		boolean check_damaged = false;
		for(int i = 0; i < max_player_bullet; i++)
		{
			Point p_bullet = bullet_player[i].label.getLocation();
			Point p_boss = Label_1stage_boss.getLocation();
			if(p_bullet.x >= p_boss.x && p_bullet.x <= p_boss.x + 178 && p_bullet.y <= p_boss.y && p_bullet.y >= p_boss.y - 140)
			{
				check_damaged = true;
				break;
			}
		}
		
		if(check_damaged == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	} // end of private static boolean check_enemy_boss_damaged()
	
	private static void check_enemy_damaged_package(Enemy[] enemy, int start_index, int end_index)
	{
		for(int i = start_index; i < end_index; i++)
		{
			int damage = check_enemy_damaged(enemy[i]);
			enemy[i].decrease_hp(damage);
			if(enemy[i].killed() == true)
			{
				if(enemy[i].survive == true)
				{
					if(used_bomb == false)
					{
						score += (stage * enemy[i].total_hp * 100);
					}
					else
					{
						score += (stage * enemy[i].total_hp * 10);
					}
				}
				enemy[i].survive = false;
				enemy[i].disapear();
			}
		}
		/*
		for(int i = start_index; i < end_index; i++)
		{			
			if(check_enemy_damaged(enemy[i]) > 0)
			{
				int damage = check_enemy_damaged(enemy[i]);
				enemy[i].decrease_hp(damage);
				if(enemy[i].killed() == true)
				{
					if(enemy[i].survive == true)
					{
						if(used_bomb == false)
						{
							score += (stage * enemy[i].total_hp * 100);
						}
						else
						{
							score += (stage * enemy[i].total_hp * 10);
						}
					}
					enemy[i].survive = false;
					enemy[i].disapear();
				}
			}
		}*/
	} // end of private static void check_enemy_damaged_package(Enemy[] enemy, int start_index, int end_index)
	/*
	private static void check_enemy_damaged_package(Enemy[] enemy, int start_index, int end_index)
	{
		for(int i = start_index; i < end_index; i++)
		{			
			if(check_enemy_damaged(enemy[i]))
			{
				enemy[i].decrease_hp(player_damage);
				if(enemy[i].killed() == true)
				{
					if(enemy[i].survive == true)
					{
						if(used_bomb == false)
						{
							score += (stage * enemy[i].total_hp * 100);
						}
						else
						{
							score += (stage * enemy[i].total_hp * 10);
						}
					}
					enemy[i].survive = false;
					enemy[i].disapear();
				}
			}
		}
	} // end of private static void check_enemy_damaged_package(Enemy[] enemy, int start_index, int end_index)
	*/
	
	private static void check_enemy_damaged_package(Enemy enemy)
	{
		int damage = check_enemy_damaged(enemy);
		enemy.decrease_hp(damage);
		if(enemy.killed() == true)
		{
			if(enemy.survive == true)
			{
				if(used_bomb == false)
				{
					score += (stage * enemy.total_hp * 100);
				}
				else
				{
					score += (stage * enemy.total_hp * 10);
				}
			}
			enemy.survive = false;
			enemy.disapear();
		}
		/*
		if(check_enemy_damaged(enemy) > 0)
		{
			
		}*/
	} // end of private static void check_enemy_damaged_package(Enemy enemy)
	
	/*
	private static void check_enemy_damaged_package_2(Enemy[] enemy, int start_index, int end_index) // power >= 15
	{
		for(int i = start_index; i < end_index; i++)
		{			
			if(check_enemy_damaged(enemy[i]))
			{
				enemy[i].decrease_hp(player_damage);
				if(enemy[i].killed() == true)
				{
					if(enemy[i].survive == true)
					{
						if(used_bomb == false)
						{
							score += (stage * enemy[i].total_hp * 100);
						}
						else
						{
							score += (stage * enemy[i].total_hp * 10);
						}
					}
					enemy[i].survive = false;
					enemy[i].disapear();
				}
			}
			
			if(check_enemy_damaged_left(enemy[i]))
			{
				enemy[i].decrease_hp(player_damage);
				if(enemy[i].killed() == true)
				{
					if(enemy[i].survive == true)
					{
						if(used_bomb == false)
						{
							score += (stage * enemy[i].total_hp * 100);
						}
						else
						{
							score += (stage * enemy[i].total_hp * 10);
						}
					}
					enemy[i].survive = false;
					enemy[i].disapear();
				}
			}
			
			if(check_enemy_damaged_right(enemy[i]))
			{
				enemy[i].decrease_hp(player_damage);
				if(enemy[i].killed() == true)
				{
					if(enemy[i].survive == true)
					{
						if(used_bomb == false)
						{
							score += (stage * enemy[i].total_hp * 100);
						}
						else
						{
							score += (stage * enemy[i].total_hp * 10);
						}
					}
					enemy[i].survive = false;
					enemy[i].disapear();
				}
			}
		}
	} // end of private static void check_enemy_damaged_package(Enemy[] enemy, int start_index, int end_index)
	
	
	private static void check_enemy_damaged_package_2(Enemy enemy) // power >= 15
	{
		if(check_enemy_damaged(enemy))
		{
			enemy.decrease_hp(player_damage);
			if(enemy.killed() == true)
			{
				if(enemy.survive == true)
				{
					if(used_bomb == false)
					{
						score += (stage * enemy.total_hp * 100);
					}
					else
					{
						score += (stage * enemy.total_hp * 10);
					}
				}
				enemy.survive = false;
				enemy.disapear();
			}
		}
		
		if(check_enemy_damaged_left(enemy))
		{
			enemy.decrease_hp(player_damage);
			if(enemy.killed() == true)
			{
				if(enemy.survive == true)
				{
					if(used_bomb == false)
					{
						score += (stage * enemy.total_hp * 100);
					}
					else
					{
						score += (stage * enemy.total_hp * 10);
					}
				}
				enemy.survive = false;
				enemy.disapear();
			}
		}
		
		if(check_enemy_damaged_right(enemy))
		{
			enemy.decrease_hp(player_damage);
			if(enemy.killed() == true)
			{
				if(enemy.survive == true)
				{
					if(used_bomb == false)
					{
						score += (stage * enemy.total_hp * 100);
					}
					else
					{
						score += (stage * enemy.total_hp * 10);
					}
				}
				enemy.survive = false;
				enemy.disapear();
			}
		}
	} // end of private static void check_enemy_damaged_package_2(Enemy enemy)
	
	private static void check_enemy_damaged_package(Enemy enemy)
	{
		if(check_enemy_damaged(enemy))
		{
			enemy.decrease_hp(player_damage);
			if(enemy.killed() == true)
			{
				if(enemy.survive == true)
				{
					if(enemy.survive == true)
					{
						if(used_bomb == false)
						{
							score += (stage * enemy.total_hp * 100);
						}
						else
						{
							score += (stage * enemy.total_hp * 10);
						}
					}
				}
				enemy.survive = false;
				enemy.disapear();
			}
		}
	} // end of private static void check_enemy_damaged_package(Enemy enemy, int start_index, int end_index)
	*/
	
	private static void erase_enemy_bullet_package(Bullet[][] bullet_enemy, int end_index)
	{
		for(int i = 0; i < end_index; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_enemy[i][j].label.setLocation(0, 0);
				bullet_enemy[i][j].label.setVisible(false);
			}
		}
	} // end of private static void erase_enemy_bullet_package(Bullet[][] bullet_enemy, int end_index)
	
	private static void erase_enemy_bullet_package(Bullet[] bullet_enemy)
	{
		for(int j = 0; j < max_enemy_bullet; j++)
		{
			bullet_enemy[j].label.setLocation(0, 0);
			bullet_enemy[j].label.setVisible(false);
		}
	} // end of private static void erase_enemy_bullet_package(Bullet[] bullet_enemy)
	
	private static void erase_enemy_bullet_package(Bullet[] bullet_enemy, int index)
	{
		for(int j = 0; j < index; j++)
		{
			bullet_enemy[j].label.setLocation(0, 0);
			bullet_enemy[j].label.setVisible(false);
		}
	} // end of private static void erase_enemy_bullet_package(Bullet[] bullet_enemy, int end_index)
	
	private static void erase_enemy_bullet_package(Bullet_Radian[][] bullet_enemy, int end_index)
	{
		for(int i = 0; i < end_index; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_enemy[i][j].label.setLocation(0, 0);
				bullet_enemy[i][j].label.setVisible(false);
			}
		}
	} // end of private static void erase_enemy_bullet_package(Bullet_Radian[][] bullet_enemy, int end_index)
	
	private static void erase_enemy_bullet_package(Bullet_Radian[][] bullet_enemy, int end_index, int bullet_end_index)
	{
		for(int i = 0; i < end_index; i++)
		{
			for(int j = 0; j < bullet_end_index; j++)
			{
				bullet_enemy[i][j].label.setLocation(0, 0);
				bullet_enemy[i][j].label.setVisible(false);
			}
		}
	} // end of private static void erase_enemy_bullet_package(Bullet_Radian[][] bullet_enemy, int end_index, int bullet_end_index)
	
	private static void erase_player_bullet_package()
	{
		if(power < 10)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				bullet_player[i].label.setLocation(0, 0);
				bullet_player[i].label.setVisible(false);
			}
		}
		else if(power < 15)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				bullet_player_2[i].label.setLocation(0, 0);
				bullet_player_2[i].label.setVisible(false);
			}
		}
		else if(power < 25)
		{
			for(int i = 0; i < max_player_bullet; i++)
			{
				bullet_player_2[i].label.setLocation(0, 0);
				bullet_player_2[i].label.setVisible(false);
				bullet_player_2_left[i].label.setVisible(false);
				bullet_player_2_left[i].label.setLocation(0, 0);
				bullet_player_2_right[i].label.setVisible(false);
				bullet_player_2_right[i].label.setLocation(0, 0);
			}
		}
	}
	
	private static boolean check_player_damaged(Bullet[] enemy_bullet)
	{
		if(invincibility == true)
		{
			return false;
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			Point p1 = Label_player.getLocation();
			Point p2 = enemy_bullet[i].label.getLocation();
			// if(p2.x >= p1.x - 10 && p2.x < p1.x + enemy_bullet[i].label.getWidth() + 30 && p2.y < p1.y + enemy_bullet[i].label.getHeight() && p2.y > p1.y)
			if(p2.x >= p1.x && p2.x < p1.x + enemy_bullet[i].label.getWidth() + 10 && p2.y < p1.y + enemy_bullet[i].label.getHeight() && p2.y > p1.y)
			{
				return true;
			}
		}
		return false;
	} // end of private static boolean check_player_damaged(Bullet[] enemy_bullet)
	
	private static boolean check_player_damaged(Bullet_Radian[] enemy_bullet)
	{
		if(invincibility == true)
		{
			return false;
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			Point p1 = Label_player.getLocation();
			Point p2 = enemy_bullet[i].label.getLocation();
			// if(p2.x >= p1.x - 10 && p2.x < p1.x + enemy_bullet[i].label.getWidth() + 30 && p2.y < p1.y + enemy_bullet[i].label.getHeight() && p2.y > p1.y)
			if(p2.x >= p1.x && p2.x < p1.x + enemy_bullet[i].label.getWidth() + 10 && p2.y < p1.y + enemy_bullet[i].label.getHeight() && p2.y > p1.y)
			{
				return true;
			}
		}
		return false;
	} // end of private static boolean check_player_damaged(Bullet_Radian[] enemy_bullet)
	
	private static boolean check_player_damaged(Bullet_Radian[] enemy_bullet, int index)
	{
		if(invincibility == true)
		{
			return false;
		}
		
		for(int i = 0; i < index; i++)
		{
			Point p1 = Label_player.getLocation();
			Point p2 = enemy_bullet[i].label.getLocation();
			//if(p2.x >= p1.x - 10 && p2.x < p1.x + 70 && p2.y < p1.y + 30 && p2.y > p1.y)
			if(p2.x >= p1.x - 10 && p2.x < p1.x + enemy_bullet[i].label.getWidth() + 30 && p2.y < p1.y + enemy_bullet[i].label.getHeight() && p2.y > p1.y)
			{
				return true;
			}
		}
		return false;
	} // end of private static boolean check_player_damaged(Bullet_Radian[] enemy_bullet)
	
	private static void check_player_damaged_package(Bullet[] bullet_enemy)
	{
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			if(check_player_damaged(bullet_enemy))
			{
				if(shield_barrier == 0)
				{
					player_killed();
					bullet_enemy[i].label.setLocation(1200, 0);
					bullet_enemy[i].label.setVisible(false);
					break;
				}
				else
				{
					bullet_enemy[i].label.setLocation(1200, 0);
					bullet_enemy[i].label.setVisible(false);
					player_decrease_shield_barrier();
					break;
				}
			}
		}
	} // end of private static void check_player_damaged_package(Bullet[] bullet_enemy)
	
	private static void check_player_damaged_package(Bullet[] bullet_enemy, int index)
	{
		for(int i = 0; i < index; i++)
		{
			if(check_player_damaged(bullet_enemy))
			{
				if(shield_barrier == 0)
				{
					player_killed();
					bullet_enemy[i].label.setLocation(1200, 0);
					bullet_enemy[i].label.setVisible(false);
					break;
				}
				else
				{
					bullet_enemy[i].label.setLocation(1200, 0);
					bullet_enemy[i].label.setVisible(false);
					player_decrease_shield_barrier();
					break;
				}
			}
		}
	} // end of private static void check_player_damaged_package(Bullet[] bullet_enemy, int index)
	
	private static void check_player_damaged_package(Bullet_Radian[] bullet_enemy)
	{
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			if(check_player_damaged(bullet_enemy))
			{
				System.out.println(shield_barrier);
				if(shield_barrier == 0)
				{
					player_killed();
					bullet_enemy[i].label.setLocation(1200, 0);
					bullet_enemy[i].label.setVisible(false);
					break;
				}
				else
				{
					bullet_enemy[i].label.setLocation(1200, 0);
					bullet_enemy[i].label.setVisible(false);
					player_decrease_shield_barrier();
					break;
				}
			}
		}
	} // end of private static void check_player_damaged_package(Bullet_Radian[] bullet_enemy)
	
	private static void check_player_damaged_package(Bullet_Radian[] bullet_enemy, int index)
	{
		for(int i = 0; i < index; i++)
		{
			if(check_player_damaged(bullet_enemy, index))
			{
				player_killed();
				bullet_enemy[i].label.setLocation(1200, 0);
				bullet_enemy[i].label.setVisible(false);
				break;
			}
		}
	} // end of private static void check_player_damaged_package(Bullet_Radian[] bullet_enemy, int index)
	
	private static void set_player_color_by_shield_barrier()
	{
		if(invincibility == false)
		{
			switch(shield_barrier)
			{
			case 3:
				Label_player.setForeground(Color.BLUE);
				break;
			case 2:
				Label_player.setForeground(Color.ORANGE);
				break;
			case 1:
				Label_player.setForeground(Color.GREEN);
				break;
			default:
				Label_player.setForeground(Color.BLACK);
				break;
			}
		}
	} // end of private static void set_player_color_by_shield_barrier()
	
	// build stage1
	private static void stage1()
	{
		Label_loading.setVisible(true);
		
		// set enemy bullet
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			bullet_enemy[i] = new Bullet();
			bullet_enemy[i].label = new JLabel("¡Ü");
			bullet_enemy[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_enemy[i].label.setForeground(Color.BLUE);
			bullet_enemy[i].posX = 1200;
			bullet_enemy[i].posY = 0;
			bullet_enemy[i].move_x = 0;
			bullet_enemy[i].move_y = 3;
			bullet_enemy[i].label.setBounds(0, 0, 30, 30);
			bullet_enemy[i].label.setLocation(bullet_enemy[i].posX, bullet_enemy[i].posY); // original position(not used)
			panel_game_screen.add(bullet_enemy[i].label);
			bullet_enemy[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			bullet_enemy_2[i] = new Bullet();
			bullet_enemy_2[i].label = new JLabel("¡Ü");
			bullet_enemy_2[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_enemy_2[i].label.setForeground(Color.ORANGE);
			bullet_enemy_2[i].posX = 1200;
			bullet_enemy_2[i].posY = 0;
			bullet_enemy_2[i].move_x = 0;
			bullet_enemy_2[i].move_y = 3;
			bullet_enemy_2[i].label.setBounds(0, 0, 30, 30);
			bullet_enemy_2[i].label.setLocation(bullet_enemy_2[i].posX, bullet_enemy_2[i].posY); // original position(not used)
			panel_game_screen.add(bullet_enemy_2[i].label);
			bullet_enemy_2[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			bullet_enemy_3[i] = new Bullet();
			bullet_enemy_3[i].label = new JLabel("¡Ü");
			bullet_enemy_3[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_enemy_3[i].label.setForeground(Color.GREEN);
			bullet_enemy_3[i].posX = 1200;
			bullet_enemy_3[i].posY = 0;
			bullet_enemy_3[i].move_x = 0;
			bullet_enemy_3[i].move_y = 3;
			bullet_enemy_3[i].label.setBounds(0, 0, 30, 30);
			bullet_enemy_3[i].label.setLocation(bullet_enemy_3[i].posX, bullet_enemy_3[i].posY); // original position(not used)
			panel_game_screen.add(bullet_enemy_3[i].label);
			bullet_enemy_3[i].label.setVisible(false);
		}
		
		Bullet[][] bullet_1st_middle_boss_enemy = new Bullet[5][max_enemy_bullet];
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_1st_middle_boss_enemy[i][j] = new Bullet();
				bullet_1st_middle_boss_enemy[i][j].label = new JLabel("¡Ü");
				bullet_1st_middle_boss_enemy[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_1st_middle_boss_enemy[i][j].label.setForeground(Color.BLUE);
				bullet_1st_middle_boss_enemy[i][j].posX = 1200;
				bullet_1st_middle_boss_enemy[i][j].posY = 0;
				if(i == 0)
				{
					bullet_1st_middle_boss_enemy[i][j].move_x = -3;
				}
				else if(i == 1)
				{
					bullet_1st_middle_boss_enemy[i][j].move_x = -2;
				}
				else if(i == 2)
				{
					bullet_1st_middle_boss_enemy[i][j].move_x = 0;
				}
				else if(i == 3)
				{
					bullet_1st_middle_boss_enemy[i][j].move_x = 2;
				}
				else if(i == 4)
				{
					bullet_1st_middle_boss_enemy[i][j].move_x = 3;
				}
				bullet_1st_middle_boss_enemy[i][j].move_y = 3;
				bullet_1st_middle_boss_enemy[i][j].label.setBounds(0, 0, 30, 30);
				bullet_1st_middle_boss_enemy[i][j].label.setLocation(bullet_1st_middle_boss_enemy[i][j].posX, bullet_1st_middle_boss_enemy[i][j].posY); // original position(not used)
				panel_game_screen.add(bullet_1st_middle_boss_enemy[i][j].label);
				bullet_1st_middle_boss_enemy[i][j].label.setVisible(false);
			}
		}
		
		Bullet[][] bullet_1st_stage_boss_enemy = new Bullet[11][max_enemy_bullet];
		
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_1st_stage_boss_enemy[i][j] = new Bullet();
				bullet_1st_stage_boss_enemy[i][j].label = new JLabel("¡Ü");
				bullet_1st_stage_boss_enemy[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_1st_stage_boss_enemy[i][j].label.setForeground(Color.RED);
				bullet_1st_stage_boss_enemy[i][j].posX = 1200;
				bullet_1st_stage_boss_enemy[i][j].posY = 0;
				bullet_1st_stage_boss_enemy[i][j].move_x = i - 5;
				bullet_1st_stage_boss_enemy[i][j].move_y = 4;
				bullet_1st_stage_boss_enemy[i][j].label.setBounds(0, 0, 30, 30);
				bullet_1st_stage_boss_enemy[i][j].label.setLocation(bullet_1st_stage_boss_enemy[i][j].posX, bullet_1st_stage_boss_enemy[i][j].posY); // original position(not used)
				panel_game_screen.add(bullet_1st_stage_boss_enemy[i][j].label);
				bullet_1st_stage_boss_enemy[i][j].label.setVisible(false);
			}
		}
		
		// set enemy(1st stage)
		Enemy[] enemy_1_group = new Enemy[enemy_1_count];		
		for(int i = 0; i < enemy_1_count; i++)
		{
			enemy_1_group[i] = new Enemy();
			enemy_1_group[i].total_hp = 1;
			enemy_1_group[i].hp = 1;
			enemy_1_group[i].posX = 0;
			enemy_1_group[i].posY = 0;
			enemy_1_group[i].width = 80;
			enemy_1_group[i].height = 24;
			enemy_1_group[i].attack_interval = 300;
			enemy_1_group[i].attack_frame = 0;
			enemy_1_group[i].survive = true;
			enemy_1_group[i].label = new JLabel(new ImageIcon("./image/enemy_1_picture.png"));
			panel_game_screen.add(enemy_1_group[i].label);
			enemy_1_group[i].label.setBounds(0, 0, 100, 30);
			enemy_1_group[i].label.setVisible(false);
		}
		
		Enemy[] enemy_2_group = new Enemy[enemy_2_count];
		for(int i = 0; i < enemy_2_count; i++)
		{
			enemy_2_group[i] = new Enemy();
			enemy_2_group[i].total_hp = 10;
			enemy_2_group[i].hp = 10;
			enemy_2_group[i].posX = 0;
			enemy_2_group[i].posY = 0;
			enemy_2_group[i].width = 140;
			enemy_2_group[i].height = 27;
			enemy_2_group[i].attack_interval = 300;
			enemy_2_group[i].attack_frame = 0;
			enemy_2_group[i].survive = true;
			enemy_2_group[i].label = new JLabel(new ImageIcon("./image/enemy_2_picture.png"));
			panel_game_screen.add(enemy_2_group[i].label);
			enemy_2_group[i].label.setBounds(0, 0, 200, 30);
			enemy_2_group[i].label.setVisible(false);
		}
		
		//enemy_2_group[0].getPower = true;
		
		Enemy[] enemy_3_group = new Enemy[enemy_3_count];
		for(int i = 0; i < enemy_3_count; i++)
		{
			enemy_3_group[i] = new Enemy();
			enemy_3_group[i].total_hp = 5;
			enemy_3_group[i].hp = 5;
			enemy_3_group[i].posX = 0;
			enemy_3_group[i].posY = 0;
			enemy_3_group[i].width = 80;
			enemy_3_group[i].height = 48;
			enemy_3_group[i].attack_interval = 300;
			enemy_3_group[i].attack_frame = 0;
			enemy_3_group[i].survive = true;
			enemy_3_group[i].label = new JLabel(new ImageIcon("./image/enemy_3_picture.png"));
			panel_game_screen.add(enemy_3_group[i].label);
			enemy_3_group[i].label.setBounds(0, 0, 100, 60);
			enemy_3_group[i].label.setVisible(false);
		}
		
		Enemy enemy_1st_middle_boss = new Enemy();
		enemy_1st_middle_boss.total_hp = 100;
		enemy_1st_middle_boss.hp = 100;
		enemy_1st_middle_boss.posX = 0;
		enemy_1st_middle_boss.posY = 0;
		enemy_1st_middle_boss.width = 140;
		enemy_1st_middle_boss.height = 160;
		enemy_1st_middle_boss.attack_interval = 150;
		enemy_1st_middle_boss.attack_frame = 0;
		enemy_1st_middle_boss.survive = true;
		enemy_1st_middle_boss.label = new JLabel(new ImageIcon("./image/enemy_1st_middle_boss.png"));
		panel_game_screen.add(enemy_1st_middle_boss.label);
		enemy_1st_middle_boss.label.setBounds(0, 0, 200, 170);
		enemy_1st_middle_boss.label.setVisible(false);
		
		Enemy enemy_1st_stage_boss = new Enemy();
		enemy_1st_stage_boss.total_hp = 200;
		enemy_1st_stage_boss.hp = 200;
		enemy_1st_stage_boss.posX = 0;
		enemy_1st_stage_boss.posY = 0;
		enemy_1st_stage_boss.width = 200;
		enemy_1st_stage_boss.height = 144;
		enemy_1st_stage_boss.attack_interval = 150;
		enemy_1st_stage_boss.attack_frame = 0;
		enemy_1st_stage_boss.survive = true;
		enemy_1st_stage_boss.label = new JLabel(new ImageIcon("./image/enemy_1st_stage_boss.png"));
		panel_game_screen.add(enemy_1st_stage_boss.label);
		enemy_1st_stage_boss.label.setBounds(0, 0, 300, 170);
		enemy_1st_stage_boss.label.setVisible(false);
		
		Point power_generate_location = null;
		
		Label_loading.setVisible(false);

		// ==================================================================
		frame_count_start = System.currentTimeMillis();
		frame_count_current = System.currentTimeMillis();
		
		int start_index = 0;
		int end_index = 0;
		
		while(frame_count_current - frame_count_start < 1000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
		}
		
		// ==================================================================
		// enemy occur
		enemy_1_group[0].label.setLocation(200, 50);
		enemy_1_group[1].label.setLocation(900, 50);
		
		start_index = 0;
		end_index = 2;
		
		for(int i = 0; i < 2; i++)
		{
			enemy_1_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 3000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = 0; i < end_index; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], bullet_enemy, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 250 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(bullet_enemy);
			check_enemy_damaged_package(enemy_1_group, start_index, end_index);
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy);
			set_player_invincibility_package();
			check_gameover();
		}
		
		// ==================================================================
		// enemy occur
		enemy_1_group[2].label.setLocation(300, 30);
		enemy_1_group[3].label.setLocation(450, 30);
		enemy_1_group[4].label.setLocation(600, 30);
		enemy_1_group[5].label.setLocation(750, 30);
		
		end_index = 6;
		
		for(int i = 2; i < 6; i++)
		{
			//enemy_1_group[i].survive = true;
			enemy_1_group[i].label.setVisible(true);
		}
		
		enemy_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 7000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			for(int i = 0; i < end_index; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], bullet_enemy, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 250 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(bullet_enemy);
			check_enemy_damaged_package(enemy_1_group, start_index, end_index);
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy);
			set_player_invincibility_package();
			check_gameover();
		}
		
		// ==================================================================
		// enemy occur
		enemy_2_group[0].label.setLocation(100, 30);
		enemy_2_group[1].label.setLocation(900, 30);
		
		power_generate_location = enemy_2_group[0].label.getLocation();
		
		int start_index2 = 0;
		int end_index2 = 2;
		
		for(int i = 0; i < 2; i++)
		{
			enemy_2_group[i].survive = true;
			enemy_2_group[i].label.setVisible(true);
		}
		
		enemy_attack_frame_count = 0;
		enemy_2_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 15000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			enemy_2_attack_frame_count++;
			
			power_generate_location = enemy_2_group[0].label.getLocation();
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			for(int i = 0; i < end_index; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], bullet_enemy, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < end_index2; i++)
			{
				if(enemy_2_group[i].attack())
				{
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 30);
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 70);
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 130);
					Random random_attack_enemy_interval = new Random();
					enemy_2_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_2_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(bullet_enemy);
			enemy_bullet_move(bullet_enemy_2);
			check_enemy_damaged_package(enemy_1_group, start_index, end_index);
			check_enemy_damaged_package(enemy_2_group, start_index2, end_index2);
			
			// power generate
			if(enemy_2_group[0].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				System.out.println(power_up_item.posY);
				power_up_item.posX = power_generate_location.x + 50;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy);
			check_player_damaged_package(bullet_enemy_2);
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd event
		
		// ==================================================================
		// enemy occur
		enemy_2_group[2].label.setLocation(200, 30);
		enemy_2_group[3].label.setLocation(800, 30);
		
		end_index2 = 4;
		
		enemy_1_group[6].label.setLocation(350, 50);
		enemy_1_group[7].label.setLocation(550, 50);
		enemy_1_group[8].label.setLocation(750, 50);
		
		end_index = 9;
		
		for(int i = 2; i < 4; i++)
		{
			enemy_2_group[i].survive = true;
			enemy_2_group[i].label.setVisible(true);
		}
		
		for(int i = 6; i < 9; i++)
		{
			enemy_1_group[i].survive = true;
			enemy_1_group[i].label.setVisible(true);
		}
		
		enemy_attack_frame_count = 0;
		enemy_2_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 20000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			enemy_2_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			for(int i = 0; i < end_index; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], bullet_enemy, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < end_index2; i++)
			{
				if(enemy_2_group[i].attack())
				{
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 30);
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 70);
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 130);
					Random random_attack_enemy_interval = new Random();
					enemy_2_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_2_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(bullet_enemy);
			enemy_bullet_move(bullet_enemy_2);
			check_enemy_damaged_package(enemy_1_group, start_index, end_index);
			check_enemy_damaged_package(enemy_2_group, start_index2, end_index2);
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy);
			check_player_damaged_package(bullet_enemy_2);
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy occur
		for(int i = 9; i <= 13; i++)
		{
			enemy_1_group[i].label.setLocation(200 + 50 * (i - 9), 50 * (i - 8));
		}
		
		for(int i = 14; i < 19; i++)
		{
			enemy_1_group[i].label.setLocation(900 - 50 * (i - 14), 50 * (i - 13));
		}
		
		end_index = 19;
		
		// enemy disapear
		for(int i = 0; i < 9; i++)
		{
			enemy_1_group[i].survive = false;
			enemy_1_group[i].label.setVisible(false);
		}
		
		start_index = 9;
		
		for(int i = 9; i < 19; i++)
		{
			enemy_1_group[i].survive = true;
			enemy_1_group[i].label.setVisible(true);
		}
		
		enemy_attack_frame_count = 0;
		enemy_2_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 35000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			enemy_2_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			for(int i = start_index; i < end_index; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], bullet_enemy, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index2; i < end_index2; i++)
			{
				if(enemy_2_group[i].attack())
				{
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 30);
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 70);
					attack_enemy(enemy_2_group[i], bullet_enemy_2, 100, 130);
					Random random_attack_enemy_interval = new Random();
					enemy_2_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_2_group[i].attack_frame++;
				}
			}
			enemy_bullet_move(bullet_enemy);
			enemy_bullet_move(bullet_enemy_2);
			check_enemy_damaged_package(enemy_1_group, start_index, end_index);
			check_enemy_damaged_package(enemy_2_group, start_index2, end_index2);
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy);
			check_player_damaged_package(bullet_enemy_2);
			set_player_invincibility_package();
			check_gameover();
		} // end of 5th event
		
		// ==================================================================
		// enemy disapear
		for(int i = 9; i < 19; i++)
		{
			enemy_1_group[i].survive = false;
			enemy_1_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < end_index2; i++)
		{
			enemy_2_group[i].survive = false;
			enemy_2_group[i].label.setVisible(false);
		}
		
		// bullet remove
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			bullet_enemy[i].label.setLocation(1200, 0);
			bullet_enemy_2[i].label.setLocation(1200, 0);
			bullet_enemy[i].label.setVisible(false);
			bullet_enemy_2[i].label.setVisible(false);
		}
		
		// 1st stage middle boss occur
		enemy_1st_middle_boss.label.setLocation(500, 50);
		enemy_1st_middle_boss.survive = true;
		enemy_1st_middle_boss.label.setVisible(true);
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_1st_middle_boss.hp));
		Label_hp.setVisible(true);
		
		enemy_attack_frame_count = 0;
		
		Random random = new Random();
		int attack_pattern = -1;
		int frame_move_pattern = 0;
		int move_pattern = -1;
		
		while(enemy_1st_middle_boss.survive != false && frame_count_current - frame_count_start < 90000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			frame_move_pattern++;
			// set enemy moving pattern
			if(frame_move_pattern >= 100)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					enemy_1st_middle_boss.move(20, 0);
					break;
				default:
					enemy_1st_middle_boss.move(-20, 0);
					break;
				}
				frame_move_pattern = 0;
			}
			
			power_generate_location = enemy_1st_middle_boss.label.getLocation();
			
			if(enemy_1st_middle_boss.attack())
			{
				attack_pattern = random.nextInt(3);
				if(attack_pattern == 0)
				{
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 50, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 90, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 130, 150);
				}
				else if(attack_pattern == 1)
				{
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 90, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 90, 200);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 90, 250);
				}
				else
				{
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[0], 50, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[1], 70, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[2], 90, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[3], 110, 150);
					attack_enemy(enemy_1st_middle_boss, bullet_1st_middle_boss_enemy[4], 130, 150);
				}
			}
			else
			{
				enemy_1st_middle_boss.attack_frame++;
			}
			
			enemy_bullet_move(bullet_enemy);
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(bullet_1st_middle_boss_enemy[i]);
			}
			
			check_enemy_damaged_package(enemy_1st_middle_boss);
			Label_hp.setText(Integer.toString(enemy_1st_middle_boss.hp));
			Label_score.setText(Integer.toString(score));
			
			check_player_damaged_package(bullet_enemy);
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(bullet_1st_middle_boss_enemy[i]);
			}
			
			set_player_invincibility_package();
			
			check_gameover();
		} // end of 1st middle boss occur
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_1st_middle_boss_enemy[i][j].label.setLocation(1200 ,0);
				bullet_1st_middle_boss_enemy[i][j].label.setVisible(false);
			}
		}
		
		Label_hp.setVisible(false);
		Label_text_hp.setVisible(false);
		
		// power generate		
		if(enemy_1st_middle_boss.survive == false)
		{
			power_up_item.posX = power_generate_location.x + 100;
			power_up_item.posY = power_generate_location.y;
			power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
			power_up_item.label.setVisible(true);
			drop_power = true;
		}
		else
		{
			enemy_1st_middle_boss.survive = false;
		}
		
		// ==================================================================
		// rest time
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 4000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		
		// reset frame count
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		// enemy occur
		int start_index3 = 0;
		int end_index3 = 2;
		
		enemy_3_group[0].label.setLocation(300, 80);
		enemy_3_group[1].label.setLocation(800, 80);
		
		for(int i = start_index3; i < end_index3; i++)
		{
			enemy_3_group[i].survive = true;
			enemy_3_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 10000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			for(int i = start_index3; i < end_index3; i++)
			{
				if(enemy_3_group[i].attack())
				{
					attack_enemy(enemy_3_group[i], bullet_enemy_3, 20, 50);
					attack_enemy(enemy_3_group[i], bullet_enemy_3, 60, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_3_group[i].attack_interval = 250 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_3_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(bullet_enemy_3);			
			check_enemy_damaged_package(enemy_3_group, start_index3, end_index3);
			
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy_3);
			set_player_invincibility_package();
			
			check_gameover();
		} // end of 7th event
		
		// ==================================================================
		enemy_3_group[2].label.setLocation(300, 50);
		enemy_3_group[3].label.setLocation(600, 50);
		enemy_3_group[4].label.setLocation(900, 50);
		end_index3 = 5;
		
		for(int i = 2; i < end_index3; i++)
		{
			enemy_3_group[i].survive = true;
			enemy_3_group[i].label.setVisible(true);
		}
		
		power_generate_location = enemy_3_group[3].label.getLocation();
		
		start_index = 0;
		end_index = 6;
		for(int i = start_index; i < 3; i++)
		{
			enemy_1_group[i].label.setLocation(100 + 100 * i, 150);
		}
		
		for(int i = 3; i < end_index; i++)
		{
			enemy_1_group[i].label.setLocation(1100 - 100 * (i - 3), 150);
		}
		
		for(int i = start_index; i < end_index; i++)
		{
			enemy_1_group[i].survive = true;
			enemy_1_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 25000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			for(int i = start_index; i < end_index; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], bullet_enemy, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 250 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index3; i < end_index3; i++)
			{
				if(enemy_3_group[i].attack())
				{
					attack_enemy(enemy_3_group[i], bullet_enemy_3, 20, 50);
					attack_enemy(enemy_3_group[i], bullet_enemy_3, 60, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_3_group[i].attack_interval = 250 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_3_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(bullet_enemy);
			enemy_bullet_move(bullet_enemy_3);
			
			check_enemy_damaged_package(enemy_1_group, start_index, end_index);
			check_enemy_damaged_package(enemy_3_group, start_index3, end_index3);
			// power generate
			if(enemy_3_group[3].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 50;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(bullet_enemy);
			check_player_damaged_package(bullet_enemy_3);
			set_player_invincibility_package();
			check_gameover();
		} // end of 8th event
		
		// enemy disapear
		for(int i = start_index; i < end_index; i++)
		{
			enemy_1_group[i].survive = false;
			enemy_1_group[i].label.setVisible(false);
		}
		
		
		for(int i = start_index3; i < end_index3; i++)
		{
			enemy_3_group[i].survive = false;
			enemy_3_group[i].label.setVisible(false);
		}
		
		// bullet remove
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			bullet_enemy[i].label.setLocation(1200, 0);
			bullet_enemy[i].label.setVisible(false);
			bullet_enemy_3[i].label.setLocation(1200, 0);
			bullet_enemy_3[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
		// ==================================================================
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 4000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
		// ==================================================================		
		// boss occur
		enemy_1st_stage_boss.label.setLocation(480, 0);
		enemy_1st_stage_boss.survive = true;
		enemy_1st_stage_boss.label.setVisible(true);
		
		frame_move_pattern = 0;
		move_pattern = -1;
		
		for(int frame = 0; frame < 50; frame++)
		{
			enemy_1st_stage_boss.move(0, 1);
			wait(20);
		}
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_1st_stage_boss.hp));
		Label_hp.setVisible(true);
		
		while(enemy_1st_stage_boss.survive != false)
		{
			frame_move_pattern++;
			wait(5);
			frame_count++;
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// set enemy moving pattern
			if(frame_move_pattern >= 100)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					enemy_1st_stage_boss.move(30, 0);
					break;
				default:
					enemy_1st_stage_boss.move(-30, 0);
					break;
				}
				frame_move_pattern = 0;
			}
			
			power_generate_location.x = enemy_1st_stage_boss.label.getX();
			power_generate_location.y = enemy_1st_stage_boss.label.getY();
			
			if(enemy_1st_stage_boss.attack())
			{
				attack_pattern = random.nextInt(3);
				if(attack_pattern == 0)
				{
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 50, 150);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 100, 150);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 150, 150);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 200, 150);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 250, 150);
				}
				else if(attack_pattern == 1)
				{
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 150, 100);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 150, 150);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 150, 200);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 150, 250);
					attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[5], 150, 300);
				}
				else
				{
					for(int i = 0; i < 11; i++)
					{
						attack_enemy(enemy_1st_stage_boss, bullet_1st_stage_boss_enemy[i], i * 20, 150);
					}
				}
			}
			else
			{
				enemy_1st_stage_boss.attack_frame++;
			}
			
			for(int i = 0; i < 11; i++)
			{
				enemy_bullet_move(bullet_1st_stage_boss_enemy[i]);
			}
			
			check_enemy_damaged_package(enemy_1st_stage_boss);
			Label_hp.setText(Integer.toString(enemy_1st_stage_boss.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 11; i++)
			{
				check_player_damaged_package(bullet_1st_stage_boss_enemy[i]);
			}
			
			set_player_invincibility_package();
			
			check_gameover();
		}
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
		// disapear bullet bomb
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i].label.setLocation(1200 ,0);
			bullet_bomb[i].label.setVisible(false);
		}
		
		// disapear boss bullet
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_1st_stage_boss_enemy[i][j].label.setLocation(1200 ,0);
				bullet_1st_stage_boss_enemy[i][j].label.setVisible(false);
			}
		}
		
		Label_hp.setVisible(false);
		Label_text_hp.setVisible(false);
		
		// power generate
		power_up_item.posX = power_generate_location.x + 80;
		power_up_item.posY = power_generate_location.y;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		// ==================================================================
		// rest time
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			check_player_invincibility();
			use_bomb_package();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
        Point p = Label_player.getLocation();
		while(p.y >= -20)
		{
			wait(20);
			Label_player.setLocation(p.x, p.y - 20);
			p = Label_player.getLocation();
		}
		
		stage++;
		Label_stage.setText(Integer.toString(stage));
		
		//stage2();
	} // end of stage1	
	
	//build stage2
	private static void stage2()
	{
		Label_loading.setVisible(true);
		
		Bullet[] enemy_bullet_1 = new Bullet[max_enemy_bullet];
		Bullet[] enemy_bullet_2 = new Bullet[max_enemy_bullet];
		Bullet[] enemy_bullet_3 = new Bullet[max_enemy_bullet];
		Bullet[][] enemy_bullet_4 = new Bullet[5][max_enemy_bullet];
		Bullet[][] enemy_bullet_5 = new Bullet[9][max_enemy_bullet];
		Bullet[][] enemy_bullet_6 = new Bullet[4][max_enemy_bullet];
		Bullet[][] bullet_2nd_middle_boss = new Bullet[11][max_enemy_bullet];
		Bullet[][] bullet_2nd_stage_boss = new Bullet[15][max_enemy_bullet];
		
		// set enemy bullet
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			enemy_bullet_1[i] = new Bullet();
			enemy_bullet_1[i].label = new JLabel("¡Ü");
			enemy_bullet_1[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			enemy_bullet_1[i].label.setForeground(Color.BLUE);
			enemy_bullet_1[i].posX = 1200;
			enemy_bullet_1[i].posY = 0;
			enemy_bullet_1[i].move_x = 0;
			enemy_bullet_1[i].move_y = 4;
			enemy_bullet_1[i].label.setBounds(0, 0, 30, 30);
			enemy_bullet_1[i].label.setLocation(enemy_bullet_1[i].posX, enemy_bullet_1[i].posY); // original position(not used)
			panel_game_screen.add(enemy_bullet_1[i].label);
			enemy_bullet_1[i].label.setVisible(false);
			
			enemy_bullet_2[i] = new Bullet();
			enemy_bullet_2[i].label = new JLabel("¡Ü");
			enemy_bullet_2[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			enemy_bullet_2[i].label.setForeground(Color.ORANGE);
			enemy_bullet_2[i].posX = 1200;
			enemy_bullet_2[i].posY = 0;
			enemy_bullet_2[i].move_x = 0;
			enemy_bullet_2[i].move_y = 4;
			enemy_bullet_2[i].label.setBounds(0, 0, 30, 30);
			enemy_bullet_2[i].label.setLocation(enemy_bullet_2[i].posX, enemy_bullet_2[i].posY); // original position(not used)
			panel_game_screen.add(enemy_bullet_2[i].label);
			enemy_bullet_2[i].label.setVisible(false);
			
			enemy_bullet_3[i] = new Bullet();
			enemy_bullet_3[i].label = new JLabel("¡Ü");
			enemy_bullet_3[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			enemy_bullet_3[i].label.setForeground(Color.GREEN);
			enemy_bullet_3[i].posX = 1200;
			enemy_bullet_3[i].posY = 0;
			enemy_bullet_3[i].move_x = 0;
			enemy_bullet_3[i].move_y = 4;
			enemy_bullet_3[i].label.setBounds(0, 0, 30, 30);
			enemy_bullet_3[i].label.setLocation(enemy_bullet_3[i].posX, enemy_bullet_3[i].posY); // original position(not used)
			panel_game_screen.add(enemy_bullet_3[i].label);
			enemy_bullet_3[i].label.setVisible(false);
		}	
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_4[i][j] = new Bullet();
				enemy_bullet_4[i][j].label = new JLabel("¡Ü");
				enemy_bullet_4[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_4[i][j].label.setForeground(Color.GREEN);
				enemy_bullet_4[i][j].posX = 1200;
				enemy_bullet_4[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_4[i][j].move_x = -4;
					enemy_bullet_4[i][j].move_y = 3;
					break;
				case 1:
					enemy_bullet_4[i][j].move_x = -2;
					enemy_bullet_4[i][j].move_y = 4;
					break;
				case 2:
					enemy_bullet_4[i][j].move_x = 0;
					enemy_bullet_4[i][j].move_y = 4;
					break;
				case 3:
					enemy_bullet_4[i][j].move_x = 2;
					enemy_bullet_4[i][j].move_y = 4;
					break;
				default:
					enemy_bullet_4[i][j].move_x = 4;
					enemy_bullet_4[i][j].move_y = 3;
					break;
				}
				
				enemy_bullet_4[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_4[i][j].label.setLocation(enemy_bullet_4[i][j].posX, enemy_bullet_4[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_4[i][j].label);
				enemy_bullet_4[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_5[i][j] = new Bullet();
				enemy_bullet_5[i][j].label = new JLabel("¡Ü");
				enemy_bullet_5[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_5[i][j].label.setForeground(Color.GRAY);
				enemy_bullet_5[i][j].posX = 1200;
				enemy_bullet_5[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_5[i][j].move_x = -4;
					enemy_bullet_5[i][j].move_y = 3;
					break;
				case 1:
					enemy_bullet_5[i][j].move_x = -3;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				case 2:
					enemy_bullet_5[i][j].move_x = -2;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				case 3:
					enemy_bullet_5[i][j].move_x = -1;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				case 4:
					enemy_bullet_5[i][j].move_x = 0;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				case 5:
					enemy_bullet_5[i][j].move_x = 1;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				case 6:
					enemy_bullet_5[i][j].move_x = 2;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				case 7:
					enemy_bullet_5[i][j].move_x = 3;
					enemy_bullet_5[i][j].move_y = 2;
					break;
				default:
					enemy_bullet_5[i][j].move_x = 4;
					enemy_bullet_5[i][j].move_y = 3;
					break;
				}
				
				enemy_bullet_5[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_5[i][j].label.setLocation(enemy_bullet_5[i][j].posX, enemy_bullet_5[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_5[i][j].label);
				enemy_bullet_5[i][j].label.setVisible(false);
			}
		}
		
		float[] DARK_CYAN = Color.RGBtoHSB(0, 108, 146, null);
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_6[i][j] = new Bullet();
				enemy_bullet_6[i][j].label = new JLabel("¡Ü");
				enemy_bullet_6[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_6[i][j].label.setForeground(Color.getHSBColor(DARK_CYAN[0], DARK_CYAN[1], DARK_CYAN[2]));
				enemy_bullet_6[i][j].posX = 1200;
				enemy_bullet_6[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_6[i][j].move_x = -2;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				case 1:
					enemy_bullet_6[i][j].move_x = -1;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				case 2:
					enemy_bullet_6[i][j].move_x = 1;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				default:
					enemy_bullet_6[i][j].move_x = 2;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				}
				
				enemy_bullet_6[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_6[i][j].label.setLocation(enemy_bullet_6[i][j].posX, enemy_bullet_6[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_6[i][j].label);
				enemy_bullet_6[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_2nd_middle_boss[i][j] = new Bullet();
				bullet_2nd_middle_boss[i][j].label = new JLabel("¡Ü");
				bullet_2nd_middle_boss[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_2nd_middle_boss[i][j].label.setForeground(Color.MAGENTA);
				bullet_2nd_middle_boss[i][j].posX = 1200;
				bullet_2nd_middle_boss[i][j].posY = 0;
				bullet_2nd_middle_boss[i][j].move_x = -5 + i;
				
				if(i == 0)
				{
					bullet_2nd_middle_boss[i][j].move_y = 2;
				}
				else if(i <= 3)
				{
					bullet_2nd_middle_boss[i][j].move_y = 3;
				}
				else if(i <= 6)
				{
					bullet_2nd_middle_boss[i][j].move_y = 4;
				}
				else if(i <= 9)
				{
					bullet_2nd_middle_boss[i][j].move_y = 3;
				}
				else
				{
					bullet_2nd_middle_boss[i][j].move_y = 2;
				}
				
				bullet_2nd_middle_boss[i][j].label.setBounds(0, 0, 30, 30);
				bullet_2nd_middle_boss[i][j].label.setLocation(bullet_2nd_middle_boss[i][j].posX, bullet_2nd_middle_boss[i][j].posY); // original position(not used)
				panel_game_screen.add(bullet_2nd_middle_boss[i][j].label);
				bullet_2nd_middle_boss[i][j].label.setVisible(false);
			}
		}
		
		float[] BROWN = Color.RGBtoHSB(153, 102, 51, null);
		
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_2nd_stage_boss[i][j] = new Bullet();
				bullet_2nd_stage_boss[i][j].label = new JLabel("¡Ü");
				bullet_2nd_stage_boss[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_2nd_stage_boss[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				bullet_2nd_stage_boss[i][j].posX = 1200;
				bullet_2nd_stage_boss[i][j].posY = 0;
				
				if(i >= 0 && i <= 2)
				{
					bullet_2nd_stage_boss[i][j].move_x = -5;
				}
				else if(i <= 4)
				{
					bullet_2nd_stage_boss[i][j].move_x = -4;
				}
				else if(i <= 9)
				{
					bullet_2nd_stage_boss[i][j].move_x = -7 + i;
				}
				else if(i <= 11)
				{
					bullet_2nd_stage_boss[i][j].move_x = 4;
				}
				else
				{
					bullet_2nd_stage_boss[i][j].move_x = 5;
				}
				
				if(i >= 0 && i <= 3)
				{
					bullet_2nd_stage_boss[i][j].move_y = 2;
				}
				else if(i <= 5)
				{
					bullet_2nd_stage_boss[i][j].move_y = 3;
				}
				else if(i <= 9)
				{
					bullet_2nd_stage_boss[i][j].move_y = 4;
				}
				else if(i <= 11)
				{
					bullet_2nd_stage_boss[i][j].move_y = 3;
				}
				else
				{
					bullet_2nd_stage_boss[i][j].move_y = 2;
				}
				
				bullet_2nd_stage_boss[i][j].label.setBounds(0, 0, 30, 30);
				bullet_2nd_stage_boss[i][j].label.setLocation(bullet_2nd_stage_boss[i][j].posX, bullet_2nd_stage_boss[i][j].posY); // original position(not used)
				panel_game_screen.add(bullet_2nd_stage_boss[i][j].label);
				bullet_2nd_stage_boss[i][j].label.setVisible(false);
			}
		}
		
		// set enemy(2nd stage)
		final int enemy_1_group_count = 20;
		final int enemy_2_group_count = 20;
		final int enemy_3_group_count = 20;
		final int enemy_4_group_count = 5;
		Enemy[] enemy_1_group = new Enemy[enemy_1_group_count];
		Enemy[] enemy_2_group = new Enemy[enemy_2_group_count];
		Enemy[] enemy_3_group = new Enemy[enemy_3_group_count];
		Enemy[] enemy_4_group = new Enemy[enemy_4_group_count];
		Enemy enemy_5 = new Enemy();
		final int enemy_6_group_count = 20;
		Enemy[] enemy_6_group = new Enemy[enemy_6_group_count];
		Enemy enemy_2nd_middle_boss = new Enemy();
		Enemy enemy_2nd_stage_boss = new Enemy();
		
		for(int i = 0; i < enemy_1_group_count; i++)
		{
			enemy_1_group[i] = new Enemy();
			enemy_1_group[i].total_hp = 3;
			enemy_1_group[i].hp = 3;
			enemy_1_group[i].posX = 0;
			enemy_1_group[i].posY = 0;
			enemy_1_group[i].width = 80;
			enemy_1_group[i].height = 24;
			enemy_1_group[i].attack_interval = 200;
			enemy_1_group[i].attack_frame = 0;
			enemy_1_group[i].survive = true;
			enemy_1_group[i].label = new JLabel(new ImageIcon("./image/enemy_1_picture.png"));
			panel_game_screen.add(enemy_1_group[i].label);
			enemy_1_group[i].label.setBounds(0, 0, 100, 30);
			enemy_1_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_2_group_count; i++)
		{
			enemy_2_group[i] = new Enemy();
			enemy_2_group[i].total_hp = 12;
			enemy_2_group[i].hp = 12;
			enemy_2_group[i].posX = 0;
			enemy_2_group[i].posY = 0;
			enemy_2_group[i].width = 140;
			enemy_2_group[i].height = 27;
			enemy_2_group[i].attack_interval = 200;
			enemy_2_group[i].attack_frame = 0;
			enemy_2_group[i].survive = true;
			enemy_2_group[i].label = new JLabel(new ImageIcon("./image/enemy_2_picture.png"));
			panel_game_screen.add(enemy_2_group[i].label);
			enemy_2_group[i].label.setBounds(0, 0, 200, 30);
			enemy_2_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_3_group_count; i++)
		{
			enemy_3_group[i] = new Enemy();
			enemy_3_group[i].total_hp = 5;
			enemy_3_group[i].hp = 5;
			enemy_3_group[i].posX = 0;
			enemy_3_group[i].posY = 0;
			enemy_3_group[i].width = 80;
			enemy_3_group[i].height = 48;
			enemy_3_group[i].attack_interval = 200;
			enemy_3_group[i].attack_frame = 0;
			enemy_3_group[i].survive = true;
			enemy_3_group[i].label = new JLabel(new ImageIcon("./image/enemy_3_picture.png"));
			panel_game_screen.add(enemy_3_group[i].label);
			enemy_3_group[i].label.setBounds(0, 0, 100, 50);
			enemy_3_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_4_group_count; i++)
		{
			enemy_4_group[i] = new Enemy();
			enemy_4_group[i].total_hp = 40;
			enemy_4_group[i].hp = 40;
			enemy_4_group[i].posX = 0;
			enemy_4_group[i].posY = 0;
			enemy_4_group[i].width = 150;
			enemy_4_group[i].height = 95;
			enemy_4_group[i].attack_interval = 200;
			enemy_4_group[i].attack_frame = 0;
			enemy_4_group[i].survive = true;
			enemy_4_group[i].label = new JLabel(new ImageIcon("./image/enemy_4_picture.png"));
			panel_game_screen.add(enemy_4_group[i].label);
			enemy_4_group[i].label.setBounds(0, 0, 170, 100);
			enemy_4_group[i].label.setVisible(false);
		}
		
		enemy_5.total_hp = 150;
		enemy_5.hp = 150;
		enemy_5.posX = 0;
		enemy_5.posY = 0;
		enemy_5.width = 350;
		enemy_5.height = 118;
		enemy_5.attack_interval = 150;
		enemy_5.attack_frame = 0;
		enemy_5.survive = true;
		enemy_5.label = new JLabel(new ImageIcon("./image/enemy_5_picture.png"));
		panel_game_screen.add(enemy_5.label);
		enemy_5.label.setBounds(0, 0, 400, 130);
		enemy_5.label.setVisible(false);
		
		for(int i = 0; i < enemy_6_group_count; i++)
		{
			enemy_6_group[i] = new Enemy();
			enemy_6_group[i].total_hp = 6;
			enemy_6_group[i].hp = 6;
			enemy_6_group[i].posX = 0;
			enemy_6_group[i].posY = 0;
			enemy_6_group[i].width = 80;
			enemy_6_group[i].height = 29;
			enemy_6_group[i].attack_interval = 200;
			enemy_6_group[i].attack_frame = 0;
			enemy_6_group[i].survive = true;
			enemy_6_group[i].label = new JLabel(new ImageIcon("./image/enemy_6_picture.png"));
			panel_game_screen.add(enemy_6_group[i].label);
			enemy_6_group[i].label.setBounds(0, 0, 100, 40);
			enemy_6_group[i].label.setVisible(false);
		}
		
		enemy_2nd_middle_boss.total_hp = 450;
		enemy_2nd_middle_boss.hp = 450;
		enemy_2nd_middle_boss.posX = 0;
		enemy_2nd_middle_boss.posY = 0;
		enemy_2nd_middle_boss.width = 350;
		enemy_2nd_middle_boss.height = 119;
		enemy_2nd_middle_boss.attack_interval = 150;
		enemy_2nd_middle_boss.attack_frame = 0;
		enemy_2nd_middle_boss.survive = true;
		enemy_2nd_middle_boss.label = new JLabel(new ImageIcon("./image/enemy_2nd_middle_boss.png"));
		panel_game_screen.add(enemy_2nd_middle_boss.label);
		enemy_2nd_middle_boss.label.setBounds(0, 0, 400, 130);
		enemy_2nd_middle_boss.label.setVisible(false);
		
		enemy_2nd_stage_boss.total_hp = 600;
		enemy_2nd_stage_boss.hp = 600;
		enemy_2nd_stage_boss.posX = 0;
		enemy_2nd_stage_boss.posY = 0;
		enemy_2nd_stage_boss.width = 340;
		enemy_2nd_stage_boss.height = 119;
		enemy_2nd_stage_boss.attack_interval = 140;
		enemy_2nd_stage_boss.attack_frame = 0;
		enemy_2nd_stage_boss.survive = true;
		enemy_2nd_stage_boss.label = new JLabel(new ImageIcon("./image/enemy_2nd_stage_boss.png"));
		panel_game_screen.add(enemy_2nd_stage_boss.label);
		enemy_2nd_stage_boss.label.setBounds(0, 0, 400, 200);
		enemy_2nd_stage_boss.label.setVisible(false);
		
		Point power_generate_location = null;
		
		Label_loading.setVisible(false);
		
		// 2nd stage start
		//
		//
		//
		// ==================================================================
		
		Label_player.setLocation(590, 860);
		frame_count_start = System.currentTimeMillis();
		frame_count_current = System.currentTimeMillis();
		
		int start_index1 = 0;
		int end_index1 = 0;
		
		while(frame_count_current - frame_count_start < 2000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
		}
		
		// ==================================================================
		// enemy occur
		enemy_1_group[0].label.setLocation(200, 50);
		enemy_1_group[1].label.setLocation(330, 50);
		enemy_1_group[2].label.setLocation(460, 50);
		
		end_index1 = 3;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 0; i < end_index1; i++)
		{
			enemy_1_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 8000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index1; i < end_index1; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], enemy_bullet_1, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(enemy_bullet_1);
			check_enemy_damaged_package(enemy_1_group, start_index1, end_index1);
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(enemy_bullet_1);
			set_player_invincibility_package();
			check_gameover();
		} // end of 2nd stage 1st event
		
		// ==================================================================
		// enemy occur
		enemy_3_group[0].label.setLocation(500, 80);
		enemy_3_group[1].label.setLocation(650, 80);
		enemy_3_group[2].label.setLocation(800, 80);
		enemy_3_group[3].label.setLocation(950, 80);
		enemy_3_group[4].label.setLocation(1100, 80);
		
		int start_index3 = 0;
		int end_index3 = 5;
		
		for(int i = 0; i < end_index3; i++)
		{
			enemy_3_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 20000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index1; i < end_index1; i++)
			{
				if(enemy_1_group[i].attack())
				{
					attack_enemy(enemy_1_group[i], enemy_bullet_1, 40, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_1_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_1_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index3; i < end_index3; i++)
			{
				if(enemy_3_group[i].attack())
				{
					attack_enemy(enemy_3_group[i], enemy_bullet_3, 20, 50);
					attack_enemy(enemy_3_group[i], enemy_bullet_3, 60, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_3_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_3_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(enemy_bullet_1);
			enemy_bullet_move(enemy_bullet_3);
			check_enemy_damaged_package(enemy_1_group, start_index1, end_index1);
			check_enemy_damaged_package(enemy_3_group, start_index3, end_index3);
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(enemy_bullet_1);
			check_player_damaged_package(enemy_bullet_3);
			set_player_invincibility_package();
			check_gameover();
		} // end of 2nd stage 2nd event
		
		// ==================================================================
		// enemy disappear
		for(int i = start_index1; i < end_index1; i++)
		{
			enemy_1_group[i].survive = false;
			enemy_1_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			enemy_bullet_1[i].label.setLocation(0, 0);
			enemy_bullet_1[i].label.setVisible(false);
		}
		
		// enemy occur
		enemy_2_group[0].label.setLocation(50, 50);
		enemy_2_group[1].label.setLocation(1050, 50);
		
		int start_index2 = 0;
		int end_index2 = 2;
		
		for(int i = start_index2; i < end_index2; i++)
		{
			enemy_2_group[i].label.setVisible(true);
		}
		
		enemy_3_group[5].label.setLocation(200, 80);
		enemy_3_group[6].label.setLocation(400, 80);
		enemy_3_group[7].label.setLocation(600, 80);
		enemy_3_group[8].label.setLocation(800, 80);
		
		end_index3 = 9;
		
		for(int i = 5; i < end_index3; i++)
		{
			enemy_3_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 35000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index2; i < end_index2; i++)
			{
				if(enemy_2_group[i].attack())
				{
					attack_enemy(enemy_2_group[i], enemy_bullet_2, 100, 30);
					attack_enemy(enemy_2_group[i], enemy_bullet_2, 100, 70);
					attack_enemy(enemy_2_group[i], enemy_bullet_2, 100, 130);
					Random random_attack_enemy_interval = new Random();
					enemy_2_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_2_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index3; i < end_index3; i++)
			{
				if(enemy_3_group[i].attack())
				{
					attack_enemy(enemy_3_group[i], enemy_bullet_3, 20, 50);
					attack_enemy(enemy_3_group[i], enemy_bullet_3, 60, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_3_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_3_group[i].attack_frame++;
				}
			}
			
			enemy_bullet_move(enemy_bullet_2);
			enemy_bullet_move(enemy_bullet_3);
			
			power_generate_location = enemy_2_group[0].label.getLocation();
			
			// power generate
			if(enemy_2_group[0].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 120;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			check_enemy_damaged_package(enemy_2_group, start_index1, end_index1);
			check_enemy_damaged_package(enemy_3_group, start_index3, end_index3);
			
			Label_score.setText(Integer.toString(score));
			check_player_damaged_package(enemy_bullet_2);
			check_player_damaged_package(enemy_bullet_3);
			set_player_invincibility_package();
			check_gameover();
		} // end of 2nd stage 3rd event
		
		// ==================================================================
		// rest time
		
		// enemy disapear
		for(int i = start_index2; i < end_index2; i++)
		{
			enemy_2_group[i].survive = false;
			enemy_2_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			enemy_bullet_2[i].label.setLocation(0, 0);
			enemy_bullet_2[i].label.setVisible(false);
		}
		
		for(int i = start_index3; i < end_index3; i++)
		{
			enemy_3_group[i].survive = false;
			enemy_3_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < max_enemy_bullet; i++)
		{
			enemy_bullet_3[i].label.setLocation(0, 0);
			enemy_bullet_3[i].label.setVisible(false);
		}
		
		while(frame_count_current - frame_count_start < 40000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy occur
		enemy_4_group[0].label.setLocation(200, 50);
		enemy_4_group[1].label.setLocation(800, 50);
		
		int start_index4 = 0;
		int end_index4 = 2;
		
		for(int i = start_index4; i < end_index4; i++)
		{
			enemy_4_group[i].label.setVisible(true);
		}
		
		int frame_enemy_4_move_pattern = 0;
		
		while((enemy_4_group[0].survive == true || enemy_4_group[1].survive == true) && frame_count_current - frame_count_start < 60000)
		{			
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			frame_enemy_4_move_pattern++;
			// set enemy moving pattern
			if(frame_enemy_4_move_pattern >= 100)
			{
				Random random_enemy_4_move_pattern = new Random();
				frame_enemy_4_move_pattern = random_enemy_4_move_pattern.nextInt(4);
				switch(frame_enemy_4_move_pattern)
				{
				case 0:
					enemy_4_group[0].move(-20, 0);
					break;
				case 1:
					enemy_4_group[0].move(20, 0);
					break;
				case 2:
					enemy_4_group[1].move(-20, 0);
					break;
				default:
					enemy_4_group[1].move(-20, 0);
					break;
				}
				frame_enemy_4_move_pattern = 0;
			}
			
			// enemy attack			
			for(int i = start_index4; i < end_index4; i++)
			{
				if(enemy_4_group[i].attack())
				{
					attack_enemy(enemy_4_group[i], enemy_bullet_4[0], 20, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[1], 40, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[2], 60, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[3], 80, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[4], 100, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_4_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);
				}
				else
				{
					enemy_4_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_4[i]);
			}
			
			// power generate
			if(enemy_4_group[1].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 150;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			power_generate_location = enemy_4_group[1].label.getLocation();			
			check_enemy_damaged_package(enemy_4_group, start_index4, end_index4);
			
			Label_score.setText(Integer.toString(score));
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_4[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 2nd stage 4th event
		
		// ==================================================================
		// rest time
		// enemy disapear
		for(int i = start_index4; i < end_index4; i++)
		{
			enemy_4_group[i].label.setLocation(0, 0);
			enemy_4_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_4[i][j].label.setLocation(0, 0);
				enemy_bullet_4[i][j].label.setVisible(false);
			}
		}
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 4000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy occur
		enemy_5.label.setLocation(400, 40);
		enemy_5.label.setVisible(true);
		
		int frame_enemy_5_move_pattern = 0;
		
		enemy_4_group[2].label.setLocation(100, 50);
		enemy_4_group[3].label.setLocation(900, 50);
		
	    start_index4 = 2;
		end_index4 = 4;
		
		for(int i = start_index4; i < end_index4; i++)
		{
			enemy_4_group[i].label.setVisible(true);
		}
		
		frame_enemy_4_move_pattern = 0;
		
		while(enemy_5.survive == true)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			frame_enemy_4_move_pattern++;
			frame_enemy_5_move_pattern++;
			// set enemy moving pattern
			if(frame_enemy_4_move_pattern >= 100)
			{
				Random random_enemy_4_move_pattern = new Random();
				frame_enemy_4_move_pattern = random_enemy_4_move_pattern.nextInt(4);
				switch(frame_enemy_4_move_pattern)
				{
				case 0:
					enemy_4_group[0].move(0, -10);
					break;
				case 1:
					if(enemy_4_group[0].label.getY() < 150)
					{
						enemy_4_group[0].move(0, 10);
					}
					break;
				case 2:
					enemy_4_group[1].move(0, -10);
					break;
				default:
					if(enemy_4_group[1].label.getY() < 150)
					{
						enemy_4_group[1].move(0, 10);
					}
					break;
				}
				frame_enemy_4_move_pattern = 0;
			}
			
			if(frame_enemy_5_move_pattern >= 200)
			{
				Random random_enemy_5_move_pattern = new Random();
				frame_enemy_5_move_pattern = random_enemy_5_move_pattern.nextInt(2);
				switch(frame_enemy_5_move_pattern)
				{
				case 0:
					enemy_5.move(-5, 0);
					break;
				default:
					enemy_5.move(5, 0);
					break;
				}
				frame_enemy_5_move_pattern = 0;
			}
			
			// enemy attack			
			for(int i = start_index4; i < end_index4; i++)
			{
				if(enemy_4_group[i].attack())
				{
					attack_enemy(enemy_4_group[i], enemy_bullet_4[0], 20, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[1], 40, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[2], 60, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[3], 80, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[4], 100, 50);
					Random random_attack_enemy_interval = new Random();
					enemy_4_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);
				}
				else
				{
					enemy_4_group[i].attack_frame++;
				}
			}
			
			if(enemy_5.attack())
			{
				for(int i = 0; i < 9; i++)
				{
					attack_enemy(enemy_5, enemy_bullet_5[i], 35 * i, 100);
				}
				Random random_attack_enemy_interval = new Random();
				enemy_5.attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);
			}
			else
			{
				enemy_5.attack_frame++;
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_4[i]);
			}
			
			for(int i = 0; i < 9; i++)
			{
				enemy_bullet_move(enemy_bullet_5[i]);
			}
			
			// power generate			
			power_generate_location = enemy_5.label.getLocation();
			check_enemy_damaged_package(enemy_4_group, start_index4, end_index4);
			check_enemy_damaged_package(enemy_5);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_4[i]);
			}
			
			for(int i = 0; i < 9; i++)
			{
				check_player_damaged_package(enemy_bullet_5[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 2nd stage 5th event
		
		// rest time
		// enemy disapear
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		for(int i = start_index4; i < end_index4; i++)
		{
			enemy_4_group[i].survive = false;
			enemy_4_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_4[i][j].label.setLocation(0, 0);
				enemy_bullet_4[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_5[i][j].label.setLocation(0, 0);
				enemy_bullet_5[i][j].label.setVisible(false);
			}
		}
		
		power_up_item.posX = power_generate_location.x + 200;
		power_up_item.posY = power_generate_location.y;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// ==================================================================
		// reset frame count
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		// enemy occur
		int start_index6 = 0;
		int end_index6 = 4;
		
		enemy_6_group[0].label.setLocation(100, 30);
		enemy_6_group[1].label.setLocation(250, 30);
		enemy_6_group[2].label.setLocation(800, 30);
		enemy_6_group[3].label.setLocation(950, 30);
		
		for(int i = start_index6; i < end_index6; i++)
		{
			enemy_6_group[i].label.setVisible(true);
		}
		
		int[] frame_enemy_6_move = {0, 0, 0, 0};
		int[] random_frame_enemy_6_move = {100, 120, 120, 100};
		
		while(frame_count_current - frame_count_start < 15000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// set enemy moving pattern
			for(int i = 0; i < end_index6; i++)
			{
				frame_enemy_6_move[i]++;
			}
			
			for(int i = 0; i < end_index6; i++)
			{
				if(frame_enemy_6_move[i] >= random_frame_enemy_6_move[i])
				{
					Random random_frame_enemy_6_move_choose = new Random();
					random_frame_enemy_6_move[i] = 100 + 10 * random_frame_enemy_6_move_choose.nextInt(4);
					
					Random random_enemy_6_move_pattern = new Random();
					int enemy_6_move_pattern = random_enemy_6_move_pattern.nextInt(4);
					
					switch(enemy_6_move_pattern)
					{
					case 0:
						enemy_6_group[i].move(-15, 0);
						break;
					case 1:
						enemy_6_group[i].move(15, 0);
						break;
					case 2:
						enemy_6_group[i].move(0, -15);
						break;
					default:
						if(enemy_6_group[i].label.getY() < 150)
						{
							enemy_6_group[i].move(0, 15);
						}
						break;
					}
					frame_enemy_6_move[i] = 0;
				}
				else
				{
					frame_enemy_6_move[i]++;
				}
			}
			
			// enemy attack
			for(int i = start_index6; i < end_index6; i++)
			{
				if(enemy_6_group[i].attack())
				{
					attack_enemy(enemy_6_group[i], enemy_bullet_6[0], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[1], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[2], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[3], 50, 40);
					Random random_attack_enemy_interval = new Random();
					enemy_6_group[i].attack_interval = 200 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_6_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_6[i]);
			}
			
			power_moving();
			check_player_get_power();
			
			check_enemy_damaged_package(enemy_6_group, start_index6, end_index6);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_6[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 2nd stage 6th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// rest time
		// enemy disapear
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		for(int i = start_index6; i < end_index6; i++)
		{
			enemy_6_group[i].survive = false;
			enemy_6_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_6[i][j].label.setLocation(0, 0);
				enemy_bullet_6[i][j].label.setVisible(false);
			}
		}
		
		// ===============================================================
		// 2nd stage middle boss occur
		enemy_2nd_middle_boss.label.setLocation(430, 50);
		enemy_2nd_middle_boss.survive = true;
		enemy_2nd_middle_boss.label.setVisible(true);
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_2nd_middle_boss.hp));
		Label_hp.setVisible(true);
		
		enemy_attack_frame_count = 0;
		
		Random random = new Random();
		int attack_pattern = -1;
		int frame_move_pattern = 0;
		int move_pattern = -1;
		
		while(enemy_2nd_middle_boss.survive != false && frame_count_current - frame_count_start < 90000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			frame_move_pattern++;
			// set enemy moving pattern
			if(frame_move_pattern >= 100)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					enemy_2nd_middle_boss.move(30, 0);
					break;
				default:
					enemy_2nd_middle_boss.move(-30, 0);
					break;
				}
				frame_move_pattern = 0;
			}
			
			power_generate_location = enemy_2nd_middle_boss.label.getLocation();
			
			if(enemy_2nd_middle_boss.attack())
			{
				attack_pattern = random.nextInt(3);
				if(attack_pattern == 0)
				{
					for(int i = 0; i < 11; i++)
					{
						attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[i], 160, 60);
					}
				}
				else if(attack_pattern == 1)
				{
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], -20, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], 70, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], 160, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], 250, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], 340, 60);
				}
				else
				{
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[3], 20, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[4], 20, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], 20, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[6], 20, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[7], 20, 60);
					
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[3], 300, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[4], 300, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[5], 300, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[6], 300, 60);
					attack_enemy(enemy_2nd_middle_boss, bullet_2nd_middle_boss[7], 300, 60);
				}
			}
			else
			{
				enemy_2nd_middle_boss.attack_frame++;
			}
			
			for(int i = 0; i < 11; i++)
			{
				enemy_bullet_move(bullet_2nd_middle_boss[i]);
			}
			
			check_enemy_damaged_package(enemy_2nd_middle_boss);
			Label_hp.setText(Integer.toString(enemy_2nd_middle_boss.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 11; i++)
			{
				check_player_damaged_package(bullet_2nd_middle_boss[i]);
			}
			
			set_player_invincibility_package();
			
			check_gameover();
		} // end of 2nd middle boss occur
		
		// ==================================================================
		// rest time
		
		Label_text_hp.setVisible(false);
		Label_hp.setVisible(false);
		
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_2nd_middle_boss[i][j].label.setLocation(0, 0);
				bullet_2nd_middle_boss[i][j].label.setVisible(false);
			}
		}
		
		// power generate		
		if(enemy_2nd_middle_boss.survive == false)
		{
			power_up_item.posX = power_generate_location.x + 160;
			power_up_item.posY = power_generate_location.y;
			power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
			power_up_item.label.setVisible(true);
			drop_power = true;
		}
		else
		{
			enemy_2nd_middle_boss.survive = false;
		}
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// ==================================================================
		// boss occur
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
		enemy_2nd_stage_boss.label.setLocation(430, 0);
		enemy_2nd_stage_boss.survive = true;
		enemy_2nd_stage_boss.label.setVisible(true);
		
		int special_pattern_count = 0;
		
		for(int frame = 0; frame < 50; frame++)
		{
			enemy_2nd_stage_boss.move(0, 1);
			wait(20);
		}
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_2nd_stage_boss.hp));
		Label_hp.setVisible(true);
		
		frame_move_pattern = 0;
		
		while(enemy_2nd_stage_boss.survive != false)
		{
			frame_move_pattern++;
			wait(5);
			frame_count++;
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// set enemy moving pattern
			if(frame_move_pattern >= 100)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					enemy_2nd_stage_boss.move(30, 0);
					break;
				default:
					enemy_2nd_stage_boss.move(-30, 0);
					break;
				}
				frame_move_pattern = 0;
			}
			
			power_generate_location = enemy_2nd_stage_boss.label.getLocation();
			
			if(enemy_2nd_stage_boss.attack())
			{
				attack_pattern = random.nextInt(4);
				if(attack_pattern == 0)
				{
					for(int i = 0; i < 15; i++)
					{
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[i], 160, 60);
					}
				}
				else if(attack_pattern == 1)
				{
					for(int i = 0; i < 5; i++)
					{
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[7], 0, 60 + 40 * i);
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[7], 100, 60 + 40 * i);
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[7], 140, 60 + 40 * i);
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[7], 220, 60 + 40 * i);
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[7], 320, 60 + 40 * i);
					}
				}
				else if(attack_pattern == 2)
				{
					for(int i = 3; i <= 11; i++)
					{
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[i], 0, 60);
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[i], 320, 60);
					}
				}
				else
				{
					for(int i = 3; i <= 11; i += 2)
					{
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[i], 100, 60);
						attack_enemy(enemy_2nd_stage_boss, bullet_2nd_stage_boss[i], 220, 60);
					}
					if(special_pattern_count < 3)
					{
						enemy_2nd_stage_boss.attack_interval = 100;
						special_pattern_count++;
					}
					else
					{
						enemy_2nd_stage_boss.attack_interval = 140;
						special_pattern_count = 0;
					}
				}
			}
			else
			{
				enemy_2nd_stage_boss.attack_frame++;
			}
			
			for(int i = 0; i < 15; i++)
			{
				enemy_bullet_move(bullet_2nd_stage_boss[i]);
			}
			
			check_enemy_damaged_package(enemy_2nd_stage_boss);
			Label_hp.setText(Integer.toString(enemy_2nd_stage_boss.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 15; i++)
			{
				check_player_damaged_package(bullet_2nd_stage_boss[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
		// disapear bullet bomb
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i].label.setLocation(1200 ,0);
			bullet_bomb[i].label.setVisible(false);
		}
		
		// disapear boss bullet
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_2nd_stage_boss[i][j].label.setLocation(1200 ,0);
				bullet_2nd_stage_boss[i][j].label.setVisible(false);
			}
		}
		
		Label_hp.setVisible(false);
		Label_text_hp.setVisible(false);
		
		// power generate
		power_up_item.posX = power_generate_location.x + 160;
		power_up_item.posY = power_generate_location.y;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		// ==================================================================
		// rest time
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
		}
		
        Point p = Label_player.getLocation();
		while(p.y >= -20)
		{
			wait(20);
			Label_player.setLocation(p.x, p.y - 20);
			p = Label_player.getLocation();
		}
		
		stage++;
		Label_stage.setText(Integer.toString(stage));
		
		//stage3();		
	} // end of stage2	
	
	//build stage3
	private static void stage3()
	{
		Label_loading.setVisible(true);
		
		// set enemy bullet
		Bullet[][] enemy_bullet_6 = new Bullet[4][max_enemy_bullet];
		Bullet[][] enemy_bullet_7 = new Bullet[4][max_enemy_bullet];
		Bullet[][] enemy_bullet_8 = new Bullet[3][max_enemy_bullet];
		Bullet[][] enemy_bullet_9_1 = new Bullet[7][max_enemy_bullet];
		Bullet[][] enemy_bullet_9_2 = new Bullet[5][max_enemy_bullet];
		Bullet_Radian[][] bullet_3rd_middle_boss_1 = new Bullet_Radian[13][max_enemy_bullet];
		//Bullet[][] bullet_3rd_middle_boss_1 = new Bullet[13][max_enemy_bullet];
		Bullet[][] bullet_3rd_middle_boss_2 = new Bullet[7][max_enemy_bullet];
		Bullet[][] enemy_bullet_10 = new Bullet[5][max_enemy_bullet];
		//Bullet[][] bullet_3rd_stage_boss_1_1 = new Bullet[6][max_enemy_bullet];
		//Bullet[][] bullet_3rd_stage_boss_1_2 = new Bullet[13][max_enemy_bullet];
		Bullet_Radian[][] bullet_3rd_stage_boss_1_1 = new Bullet_Radian[6][max_enemy_bullet];
		Bullet_Radian[][] bullet_3rd_stage_boss_1_2 = new Bullet_Radian[11][max_enemy_bullet];
		Bullet_Radian[][] bullet_3rd_stage_boss_2_1 = new Bullet_Radian[6][max_enemy_bullet];
		Bullet_Radian[][] bullet_3rd_stage_boss_2_2 = new Bullet_Radian[13][max_enemy_bullet];
		Bullet_Radian[][] bullet_3rd_stage_boss_2_3 = new Bullet_Radian[2][1];
		Bullet_Radian[][] bullet_3rd_stage_boss_2_4 = new Bullet_Radian[16][max_enemy_bullet];
		
		float[] DARK_CYAN = Color.RGBtoHSB(0, 108, 146, null);
		float[] BROWN = Color.RGBtoHSB(153, 102, 51, null);
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_6[i][j] = new Bullet();
				enemy_bullet_6[i][j].label = new JLabel("¡Ü");
				enemy_bullet_6[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_6[i][j].label.setForeground(Color.getHSBColor(DARK_CYAN[0], DARK_CYAN[1], DARK_CYAN[2]));
				enemy_bullet_6[i][j].posX = 1200;
				enemy_bullet_6[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_6[i][j].move_x = -2;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				case 1:
					enemy_bullet_6[i][j].move_x = -1;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				case 2:
					enemy_bullet_6[i][j].move_x = 1;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				default:
					enemy_bullet_6[i][j].move_x = 2;
					enemy_bullet_6[i][j].move_y = 4;
					break;
				}
				
				enemy_bullet_6[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_6[i][j].label.setLocation(enemy_bullet_6[i][j].posX, enemy_bullet_6[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_6[i][j].label);
				enemy_bullet_6[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_7[i][j] = new Bullet();
				enemy_bullet_7[i][j].label = new JLabel("¡Ü");
				enemy_bullet_7[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_7[i][j].label.setForeground(Color.GREEN);
				enemy_bullet_7[i][j].posX = 1200;
				enemy_bullet_7[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_7[i][j].move_x = -2;
					break;
				case 1:
					enemy_bullet_7[i][j].move_x = -1;
					break;
				case 2:
					enemy_bullet_7[i][j].move_x = 1;
					break;
				default:
					enemy_bullet_7[i][j].move_x = 2;
					break;
				}
				
				enemy_bullet_7[i][j].move_y = 3;
				enemy_bullet_7[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_7[i][j].label.setLocation(enemy_bullet_7[i][j].posX, enemy_bullet_7[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_7[i][j].label);
				enemy_bullet_7[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_8[i][j] = new Bullet();
				enemy_bullet_8[i][j].label = new JLabel("¡Ü");
				enemy_bullet_8[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				enemy_bullet_8[i][j].label.setForeground(Color.BLUE);
				enemy_bullet_8[i][j].posX = 1200;
				enemy_bullet_8[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_8[i][j].move_x = -2;
					break;
				case 1:
					enemy_bullet_8[i][j].move_x = 0;
					break;
				default:
					enemy_bullet_8[i][j].move_x = 2;
					break;
				}
				
				enemy_bullet_8[i][j].move_y = 3;
				enemy_bullet_8[i][j].label.setBounds(0, 0, 40, 40);
				enemy_bullet_8[i][j].label.setLocation(enemy_bullet_8[i][j].posX, enemy_bullet_8[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_8[i][j].label);
				enemy_bullet_8[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_9_1[i][j] = new Bullet();
				enemy_bullet_9_1[i][j].label = new JLabel("¡Ü");
				enemy_bullet_9_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_9_1[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				enemy_bullet_9_1[i][j].posX = 1200;
				enemy_bullet_9_1[i][j].posY = 0;
				enemy_bullet_9_1[i][j].move_x = -3 + i;
				
				if(i == 0 || i == 6)
				{
					enemy_bullet_9_1[i][j].move_y = 3;
				}
				else if(i == 3)
				{
					enemy_bullet_9_1[i][j].move_y = 4;
				}
				else
				{
					enemy_bullet_9_1[i][j].move_y = 5;
				}
		
				enemy_bullet_9_1[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_9_1[i][j].label.setLocation(enemy_bullet_9_1[i][j].posX, enemy_bullet_9_1[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_9_1[i][j].label);
				enemy_bullet_9_1[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_9_2[i][j] = new Bullet();
				enemy_bullet_9_2[i][j].label = new JLabel("¡Ü");
				enemy_bullet_9_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				enemy_bullet_9_2[i][j].label.setForeground(Color.BLUE);
				enemy_bullet_9_2[i][j].posX = 1200;
				enemy_bullet_9_2[i][j].posY = 0;
				enemy_bullet_9_2[i][j].move_x = -2 + i;
				enemy_bullet_9_2[i][j].move_y = 4;
				enemy_bullet_9_2[i][j].label.setBounds(0, 0, 40, 40);
				enemy_bullet_9_2[i][j].label.setLocation(enemy_bullet_9_2[i][j].posX, enemy_bullet_9_2[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_9_2[i][j].label);
				enemy_bullet_9_2[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd middle boss using radian
		for(int i = 0; i < 13; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_middle_boss_1[i][j] = new Bullet_Radian(30.0f + 10.0f * i);
				bullet_3rd_middle_boss_1[i][j].label = new JLabel("¡Ü");
				bullet_3rd_middle_boss_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_3rd_middle_boss_1[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				bullet_3rd_middle_boss_1[i][j].speed = 3;
				bullet_3rd_middle_boss_1[i][j].label.setBounds(0, 0, 30, 30);
				bullet_3rd_middle_boss_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_middle_boss_1[i][j].label);
				bullet_3rd_middle_boss_1[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd middle boss bullet 2
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_middle_boss_2[i][j] = new Bullet();
				bullet_3rd_middle_boss_2[i][j].label = new JLabel("¡Ü");
				bullet_3rd_middle_boss_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
				bullet_3rd_middle_boss_2[i][j].label.setForeground(Color.getHSBColor(DARK_CYAN[0], DARK_CYAN[1], DARK_CYAN[2]));
				bullet_3rd_middle_boss_2[i][j].posX = 1200;
				bullet_3rd_middle_boss_2[i][j].posY = 0;
				bullet_3rd_middle_boss_2[i][j].move_x = -3 + i;
				bullet_3rd_middle_boss_2[i][j].move_y = 5;
				bullet_3rd_middle_boss_2[i][j].label.setBounds(0, 0, 50, 50);
				bullet_3rd_middle_boss_2[i][j].label.setLocation(bullet_3rd_middle_boss_2[i][j].posX, bullet_3rd_middle_boss_2[i][j].posY); // original position(not used)
				panel_game_screen.add(bullet_3rd_middle_boss_2[i][j].label);
				bullet_3rd_middle_boss_2[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_10[i][j] = new Bullet();
				enemy_bullet_10[i][j].label = new JLabel("¡Ü");
				enemy_bullet_10[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_10[i][j].label.setForeground(Color.ORANGE);
				enemy_bullet_10[i][j].posX = 1200;
				enemy_bullet_10[i][j].posY = 0;
				enemy_bullet_10[i][j].move_x = -2 + i;				
				enemy_bullet_10[i][j].move_y = 2;
				enemy_bullet_10[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_10[i][j].label.setLocation(enemy_bullet_10[i][j].posX, enemy_bullet_10[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_10[i][j].label);
				enemy_bullet_10[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd boss_1 bullet 1 using radian
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_stage_boss_1_1[i][j] = new Bullet_Radian(45.0f + 18.0f * i);
				bullet_3rd_stage_boss_1_1[i][j].label = new JLabel("¡Ü");
				bullet_3rd_stage_boss_1_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_3rd_stage_boss_1_1[i][j].label.setForeground(Color.DARK_GRAY);
				bullet_3rd_stage_boss_1_1[i][j].speed = 4;
				bullet_3rd_stage_boss_1_1[i][j].label.setBounds(0, 0, 30, 30);
				bullet_3rd_stage_boss_1_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_stage_boss_1_1[i][j].label);
				bullet_3rd_stage_boss_1_1[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd boss_1 bullet 2 using radian
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_stage_boss_1_2[i][j] = new Bullet_Radian(30.0f + 12.0f * i);
				bullet_3rd_stage_boss_1_2[i][j].label = new JLabel("¡Ü");
				bullet_3rd_stage_boss_1_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_3rd_stage_boss_1_2[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				bullet_3rd_stage_boss_1_2[i][j].speed = 3;
				bullet_3rd_stage_boss_1_2[i][j].label.setBounds(0, 0, 30, 30);
				bullet_3rd_stage_boss_1_2[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_stage_boss_1_2[i][j].label);
				bullet_3rd_stage_boss_1_2[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd boss_1 bullet 1 using radian
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_stage_boss_2_1[i][j] = new Bullet_Radian(45.0f + 18.0f * i);
				bullet_3rd_stage_boss_2_1[i][j].label = new JLabel("¡Ü");
				bullet_3rd_stage_boss_2_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_3rd_stage_boss_2_1[i][j].label.setForeground(Color.DARK_GRAY);
				bullet_3rd_stage_boss_2_1[i][j].speed = 5;
				bullet_3rd_stage_boss_2_1[i][j].label.setBounds(0, 0, 30, 30);
				bullet_3rd_stage_boss_2_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_stage_boss_2_1[i][j].label);
				bullet_3rd_stage_boss_2_1[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd boss_2 bullet 2 using radian
		for(int i = 0; i < 13; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_stage_boss_2_2[i][j] = new Bullet_Radian(30.0f + 10.0f * i);
				bullet_3rd_stage_boss_2_2[i][j].label = new JLabel("¡Ü");
				bullet_3rd_stage_boss_2_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_3rd_stage_boss_2_2[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				bullet_3rd_stage_boss_2_2[i][j].speed = 3;
				bullet_3rd_stage_boss_2_2[i][j].label.setBounds(0, 0, 30, 30);
				bullet_3rd_stage_boss_2_2[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_stage_boss_2_2[i][j].label);
				bullet_3rd_stage_boss_2_2[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd boss_2 bullet 3 using radian
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 1; j++)
			{
				bullet_3rd_stage_boss_2_3[i][j] = new Bullet_Radian(45.0f + 90.0f * i);
				bullet_3rd_stage_boss_2_3[i][j].label = new JLabel("¡Ü");
				bullet_3rd_stage_boss_2_3[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
				bullet_3rd_stage_boss_2_3[i][j].label.setForeground(Color.MAGENTA);
				bullet_3rd_stage_boss_2_3[i][j].speed = 3;
				bullet_3rd_stage_boss_2_3[i][j].label.setBounds(0, 0, 50, 50);
				bullet_3rd_stage_boss_2_3[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_stage_boss_2_3[i][j].label);
				bullet_3rd_stage_boss_2_3[i][j].label.setVisible(false);
			}
		}
		
		// set 3rd boss_2 bullet 4 using radian
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_3rd_stage_boss_2_4[i][j] = new Bullet_Radian(22.5f * i);
				bullet_3rd_stage_boss_2_4[i][j].label = new JLabel("¡Ü");
				bullet_3rd_stage_boss_2_4[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_3rd_stage_boss_2_4[i][j].label.setForeground(Color.RED);
				bullet_3rd_stage_boss_2_4[i][j].speed = 4;
				bullet_3rd_stage_boss_2_4[i][j].label.setBounds(0, 0, 40, 40);
				bullet_3rd_stage_boss_2_4[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_3rd_stage_boss_2_4[i][j].label);
				bullet_3rd_stage_boss_2_4[i][j].label.setVisible(false);
			}
		}
		
		// set enemy(3rd stage)
		final int enemy_6_group_count = 20;
		final int enemy_7_group_count = 20;
		final int enemy_8_group_count = 20;
		final int enemy_9_group_count = 10;
		final int enemy_10_group_count = 30;
		Enemy[] enemy_6_group = new Enemy[enemy_6_group_count];
		Enemy[] enemy_7_group = new Enemy[enemy_7_group_count];
		Enemy[] enemy_8_group = new Enemy[enemy_8_group_count];
		Enemy[] enemy_9_group = new Enemy[enemy_9_group_count];
		Enemy enemy_3rd_middle_boss = new Enemy();
		Enemy[] enemy_10_group = new Enemy[enemy_10_group_count];
		Enemy enemy_3rd_stage_boss_1 = new Enemy();
		Enemy enemy_3rd_stage_boss_2 = new Enemy();
		
		for(int i = 0; i < enemy_6_group_count; i++)
		{
			enemy_6_group[i] = new Enemy();
			enemy_6_group[i].total_hp = 10;
			enemy_6_group[i].hp = 10;
			enemy_6_group[i].posX = 0;
			enemy_6_group[i].posY = 0;
			enemy_6_group[i].width = 80;
			enemy_6_group[i].height = 29;
			enemy_6_group[i].attack_interval = 180;
			enemy_6_group[i].attack_frame = 0;
			enemy_6_group[i].survive = true;
			enemy_6_group[i].label = new JLabel(new ImageIcon("./image/enemy_6_picture.png"));
			panel_game_screen.add(enemy_6_group[i].label);
			enemy_6_group[i].label.setBounds(0, 0, 100, 40);
			enemy_6_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_7_group_count; i++)
		{
			enemy_7_group[i] = new Enemy();
			enemy_7_group[i].total_hp = 20;
			enemy_7_group[i].hp = 20;
			enemy_7_group[i].posX = 0;
			enemy_7_group[i].posY = 0;
			enemy_7_group[i].width = 210;
			enemy_7_group[i].height = 74;
			enemy_7_group[i].attack_interval = 150;
			enemy_7_group[i].attack_frame = 0;
			enemy_7_group[i].survive = true;
			enemy_7_group[i].label = new JLabel(new ImageIcon("./image/enemy_7_picture.png"));
			panel_game_screen.add(enemy_7_group[i].label);
			enemy_7_group[i].label.setBounds(0, 0, 250, 80);
			enemy_7_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_8_group_count; i++)
		{
			enemy_8_group[i] = new Enemy();
			enemy_8_group[i].total_hp = 24;
			enemy_8_group[i].hp = 24;
			enemy_8_group[i].posX = 0;
			enemy_8_group[i].posY = 0;
			enemy_8_group[i].width = 125;
			enemy_8_group[i].height = 143;
			enemy_8_group[i].attack_interval = 170;
			enemy_8_group[i].attack_frame = 0;
			enemy_8_group[i].survive = true;
			enemy_8_group[i].label = new JLabel(new ImageIcon("./image/enemy_8_picture.png"));
			panel_game_screen.add(enemy_8_group[i].label);
			enemy_8_group[i].label.setBounds(0, 0, 150, 150);
			enemy_8_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_9_group_count; i++)
		{
			enemy_9_group[i] = new Enemy();
			enemy_9_group[i].total_hp = 80;
			enemy_9_group[i].hp = 80;
			enemy_9_group[i].posX = 0;
			enemy_9_group[i].posY = 0;
			enemy_9_group[i].width = 220;
			enemy_9_group[i].height = 185;
			enemy_9_group[i].attack_interval = 150;
			enemy_9_group[i].attack_frame = 0;
			enemy_9_group[i].survive = true;
			enemy_9_group[i].label = new JLabel(new ImageIcon("./image/enemy_9_picture.png"));
			panel_game_screen.add(enemy_9_group[i].label);
			enemy_9_group[i].label.setBounds(0, 0, 250, 230);
			enemy_9_group[i].label.setVisible(false);
		}
		
		enemy_3rd_middle_boss.total_hp = 800;
		enemy_3rd_middle_boss.hp = 800;
		enemy_3rd_middle_boss.posX = 0;
		enemy_3rd_middle_boss.posY = 0;
		enemy_3rd_middle_boss.width = 300;
		enemy_3rd_middle_boss.height = 252;
		enemy_3rd_middle_boss.attack_interval = 130;
		enemy_3rd_middle_boss.attack_frame = 0;
		enemy_3rd_middle_boss.survive = true;
		enemy_3rd_middle_boss.label = new JLabel(new ImageIcon("./image/enemy_3rd_middle_boss.png"));
		panel_game_screen.add(enemy_3rd_middle_boss.label);
		enemy_3rd_middle_boss.label.setBounds(0, 0, 400, 350);
		enemy_3rd_middle_boss.label.setVisible(false);
		
		for(int i = 0; i < enemy_10_group_count; i++)
		{
			enemy_10_group[i] = new Enemy();
			enemy_10_group[i].total_hp = 21;
			enemy_10_group[i].hp = 21;
			enemy_10_group[i].posX = 0;
			enemy_10_group[i].posY = 0;
			enemy_10_group[i].width = 130;
			enemy_10_group[i].height = 49;
			enemy_10_group[i].attack_interval = 150;
			enemy_10_group[i].attack_frame = 0;
			enemy_10_group[i].survive = true;
			enemy_10_group[i].label = new JLabel(new ImageIcon("./image/enemy_10_picture.png"));
			panel_game_screen.add(enemy_10_group[i].label);
			enemy_10_group[i].label.setBounds(0, 0, 150, 80);
			enemy_10_group[i].label.setVisible(false);
		}
		
		enemy_3rd_stage_boss_1.total_hp = 1000;
		enemy_3rd_stage_boss_1.hp = 1000;
		enemy_3rd_stage_boss_1.posX = 0;
		enemy_3rd_stage_boss_1.posY = 0;
		enemy_3rd_stage_boss_1.width = 215;
		enemy_3rd_stage_boss_1.height = 298;
		enemy_3rd_stage_boss_1.attack_interval = 130;
		enemy_3rd_stage_boss_1.attack_frame = 0;
		enemy_3rd_stage_boss_1.survive = true;
		enemy_3rd_stage_boss_1.label = new JLabel(new ImageIcon("./image/enemy_3rd_stage_boss_1.png"));
		panel_game_screen.add(enemy_3rd_stage_boss_1.label);
		enemy_3rd_stage_boss_1.label.setBounds(0, 0, 250, 400);
		enemy_3rd_stage_boss_1.label.setVisible(false);
		
		enemy_3rd_stage_boss_2.total_hp = 1500;
		enemy_3rd_stage_boss_2.hp = 1500;
		enemy_3rd_stage_boss_2.posX = 0;
		enemy_3rd_stage_boss_2.posY = 0;
		enemy_3rd_stage_boss_2.width = 255;
		enemy_3rd_stage_boss_2.height = 185;
		enemy_3rd_stage_boss_2.attack_interval = 130;
		enemy_3rd_stage_boss_2.attack_frame = 0;
		enemy_3rd_stage_boss_2.survive = true;
		enemy_3rd_stage_boss_2.label = new JLabel(new ImageIcon("./image/enemy_3rd_stage_boss_2.png"));
		panel_game_screen.add(enemy_3rd_stage_boss_2.label);
		enemy_3rd_stage_boss_2.label.setBounds(0, 0, 280, 300);
		enemy_3rd_stage_boss_2.label.setVisible(false);
		
		Point power_generate_location = null;
		
		Label_loading.setVisible(false);
		
		// 3rd stage start
		//
		//
		//
		// ==================================================================
		
		Label_player.setLocation(590, 860);
		frame_count_start = System.currentTimeMillis();
		frame_count_current = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 2000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
		}
		
		// ==================================================================
		// enemy occur
		enemy_7_group[0].label.setLocation(100, 200);
		enemy_7_group[0].attack_interval = 100;
		enemy_7_group[1].label.setLocation(250, 120);
		enemy_7_group[1].attack_interval = 120;
		enemy_7_group[2].label.setLocation(850, 120);
		enemy_7_group[2].attack_interval = 120;
		enemy_7_group[3].label.setLocation(1000, 200);
		enemy_7_group[3].attack_interval = 100;
		
		int start_index7 = 0;
		int end_index7 = 4;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 0; i < end_index7; i++)
		{
			enemy_7_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 10000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index7; i < end_index7; i++)
			{
				if(enemy_7_group[i].attack())
				{
					for(int j = 0; j <= 1; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 77, 65);
					}	
					for(int j = 2; j <= 3; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 119, 65);
					}
					Random random_attack_enemy_interval = new Random();
					enemy_7_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_7_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_7[i]);
			}
			
			check_enemy_damaged_package(enemy_7_group, start_index7, end_index7);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_7[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 1st event
		
		// ==================================================================
		// enemy occur
		enemy_7_group[4].label.setLocation(400, 40);
		enemy_7_group[5].label.setLocation(700, 40);
		end_index7 = 6;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 4; i < end_index7; i++)
		{
			enemy_7_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 15000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index7; i < end_index7; i++)
			{
				if(enemy_7_group[i].attack())
				{
					for(int j = 0; j <= 1; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 77, 65);
					}	
					for(int j = 2; j <= 3; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 119, 65);
					}
					Random random_attack_enemy_interval = new Random();
					enemy_7_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_7_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_7[i]);
			}
			
			check_enemy_damaged_package(enemy_7_group, start_index7, end_index7);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_7[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 2nd event
		
		// ==================================================================
		// enemy occur
		enemy_8_group[0].label.setLocation(200, 40);
		enemy_8_group[1].label.setLocation(900, 40);
		
		power_generate_location = enemy_8_group[1].label.getLocation();
		
		int start_index8 = 0;
		int end_index8 = 2;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 0; i < end_index8; i++)
		{
			enemy_8_group[i].label.setVisible(true);
		}
		
		while(frame_count_current - frame_count_start < 25000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index7; i < end_index7; i++)
			{
				if(enemy_7_group[i].attack())
				{
					for(int j = 0; j <= 1; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 77, 65);
					}	
					for(int j = 2; j <= 3; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 119, 65);
					}
					Random random_attack_enemy_interval = new Random();
					enemy_7_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_7_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index8; i < end_index8; i++)
			{
				if(enemy_8_group[i].attack())
				{
					for(int j = 0; j < 3; j++)
					{
						attack_enemy(enemy_8_group[i], enemy_bullet_8[j], 59, 143);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_8_group[i].attack_interval = 170 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_8_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_7[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				enemy_bullet_move(enemy_bullet_8[i]);
			}
			
			// power generate
			if(enemy_8_group[1].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 59;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			check_enemy_damaged_package(enemy_7_group, start_index7, end_index7);
			check_enemy_damaged_package(enemy_8_group, start_index8, end_index8);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_7[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				check_player_damaged_package(enemy_bullet_8[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 3rd event
		
		// ==================================================================
		// enemy disappear
		for(int i = 0; i < 4; i++)
		{
			enemy_7_group[i].survive = false;
			enemy_7_group[i].label.setVisible(false);
		}
		
		start_index7 = 4;
		
		// enemy bullet disappear
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_7[i][j].label.setLocation(0, 0);
				enemy_bullet_7[i][j].label.setVisible(false);
			}
		}
		
		// enemy occur
		int start_index6 = 0;
		int end_index6 = 6;
		
		for(int i = 0; i <= 3; i++)
		{
			enemy_6_group[i].label.setLocation(100 * (i + 1), 300 - (50 * i));
		}
		
		for(int i = 4; i < end_index6; i++)
		{
			enemy_6_group[i].label.setLocation(1000 - 100 * (i - 4), 300 - (50 * (i - 4)));
		}
		
		enemy_6_group[0].attack_interval = 100;
		enemy_6_group[1].attack_interval = 300;
		enemy_6_group[2].attack_interval = 500;
		enemy_6_group[3].attack_interval = 500;
		enemy_6_group[4].attack_interval = 300;
		enemy_6_group[5].attack_interval = 100;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 0; i < end_index6; i++)
		{
			enemy_6_group[i].label.setVisible(true);
		}
		
		int[] frame_enemy_6_move = {0, 0, 0, 0, 0, 0};
		int[] random_frame_enemy_6_move = {100, 120, 140, 140, 120, 100};
		
		while(frame_count_current - frame_count_start < 35000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			// set enemy moving pattern
			for(int i = 0; i < end_index6; i++)
			{
				frame_enemy_6_move[i]++;
			}
			
			for(int i = 0; i < end_index6; i++)
			{
				if(frame_enemy_6_move[i] >= random_frame_enemy_6_move[i])
				{
					Random random_frame_enemy_6_move_choose = new Random();
					random_frame_enemy_6_move[i] = 100 + 10 * random_frame_enemy_6_move_choose.nextInt(4);
					
					Random random_enemy_6_move_pattern = new Random();
					int enemy_6_move_pattern = random_enemy_6_move_pattern.nextInt(4);
					
					switch(enemy_6_move_pattern)
					{
					case 0:
						enemy_6_group[i].move(-20, 0);
						break;
					case 1:
						enemy_6_group[i].move(20, 0);
						break;
					case 2:
						enemy_6_group[i].move(0, -20);
						break;
					default:
						if(enemy_6_group[i].label.getY() < 400)
						{
							enemy_6_group[i].move(0, 20);
						}
						break;
					}
					frame_enemy_6_move[i] = 0;
				}
				else
				{
					frame_enemy_6_move[i]++;
				}
			}
			
			// enemy attack
			for(int i = start_index6; i < end_index6; i++)
			{
				if(enemy_6_group[i].attack())
				{
					attack_enemy(enemy_6_group[i], enemy_bullet_6[0], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[1], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[2], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[3], 50, 40);
					Random random_attack_enemy_interval = new Random();
					enemy_6_group[i].attack_interval = 150 + 30 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_6_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index7; i < end_index7; i++)
			{
				if(enemy_7_group[i].attack())
				{
					for(int j = 0; j <= 1; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 77, 65);
					}	
					for(int j = 2; j <= 3; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 119, 65);
					}
					Random random_attack_enemy_interval = new Random();
					enemy_7_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_7_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index8; i < end_index8; i++)
			{
				if(enemy_8_group[i].attack())
				{
					for(int j = 0; j < 3; j++)
					{
						attack_enemy(enemy_8_group[i], enemy_bullet_8[j], 59, 143);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_8_group[i].attack_interval = 170 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_8_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_6[i]);
				enemy_bullet_move(enemy_bullet_7[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				enemy_bullet_move(enemy_bullet_8[i]);
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			check_enemy_damaged_package(enemy_6_group, start_index6, end_index6);
			check_enemy_damaged_package(enemy_7_group, start_index7, end_index7);
			check_enemy_damaged_package(enemy_8_group, start_index8, end_index8);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_6[i]);
				check_player_damaged_package(enemy_bullet_7[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				check_player_damaged_package(enemy_bullet_8[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 4th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		
		// enemy disappear
		for(int i = 0; i < end_index7; i++)
		{
			enemy_7_group[i].survive = false;
			enemy_7_group[i].label.setVisible(false);
		}
		
		start_index7 = 6;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_7, 4);
		
		// enemy occur
		int start_index9 = 0;
		int end_index9 = 1;
		
		enemy_9_group[0].label.setLocation(700, 100);
		power_generate_location = enemy_9_group[0].label.getLocation();
		
		int enemy_9_attack_pattern = 0;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 0; i < end_index9; i++)
		{
			enemy_9_group[i].label.setVisible(true);
		}
		
		//while(frame_count_current - frame_count_start < 10000)
		while(frame_count_current - frame_count_start < 45000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			// set enemy moving pattern
			for(int i = 0; i < end_index6; i++)
			{
				frame_enemy_6_move[i]++;
			}
			
			for(int i = 0; i < end_index6; i++)
			{
				if(frame_enemy_6_move[i] >= random_frame_enemy_6_move[i])
				{
					Random random_frame_enemy_6_move_choose = new Random();
					random_frame_enemy_6_move[i] = 100 + 10 * random_frame_enemy_6_move_choose.nextInt(4);
					
					Random random_enemy_6_move_pattern = new Random();
					int enemy_6_move_pattern = random_enemy_6_move_pattern.nextInt(4);
					
					switch(enemy_6_move_pattern)
					{
					case 0:
						enemy_6_group[i].move(-20, 0);
						break;
					case 1:
						enemy_6_group[i].move(20, 0);
						break;
					case 2:
						enemy_6_group[i].move(0, -20);
						break;
					default:
						if(enemy_6_group[i].label.getY() < 400)
						{
							enemy_6_group[i].move(0, 20);
						}
						break;
					}
					frame_enemy_6_move[i] = 0;
				}
				else
				{
					frame_enemy_6_move[i]++;
				}
			}
			
			// enemy attack
			for(int i = start_index6; i < end_index6; i++)
			{
				if(enemy_6_group[i].attack())
				{
					attack_enemy(enemy_6_group[i], enemy_bullet_6[0], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[1], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[2], 50, 40);
					attack_enemy(enemy_6_group[i], enemy_bullet_6[3], 50, 40);
					Random random_attack_enemy_interval = new Random();
					enemy_6_group[i].attack_interval = 150 + 30 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_6_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index8; i < end_index8; i++)
			{
				if(enemy_8_group[i].attack())
				{
					for(int j = 0; j < 3; j++)
					{
						attack_enemy(enemy_8_group[i], enemy_bullet_8[j], 59, 143);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_8_group[i].attack_interval = 170 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_8_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index9; i < end_index9; i++)
			{
				if(enemy_9_group[i].attack())
				{
					enemy_9_attack_pattern++;
					if(enemy_9_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < 5; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_2[j], 102, 89);
						}
					}
					else
					{
						for(int j = 0; j < 7; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 36, 86);
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 163, 86);
						}
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_9_group[i].attack_interval = 170 + 20 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_9_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_6[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				enemy_bullet_move(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < 7; i++)
			{
				enemy_bullet_move(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_9_2[i]);
			}
			
			// power generate
			if(enemy_9_group[0].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 102;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			check_enemy_damaged_package(enemy_6_group, start_index6, end_index6);
			check_enemy_damaged_package(enemy_8_group, start_index8, end_index8);
			check_enemy_damaged_package(enemy_9_group, start_index9, end_index9);			
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_6[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				check_player_damaged_package(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < 7; i++)
			{
				check_player_damaged_package(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_9_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 5th event
		
		// ==================================================================		
		// enemy disappear
		for(int i = 0; i < end_index6; i++)
		{
			enemy_6_group[i].survive = false;
			enemy_6_group[i].label.setVisible(false);
		}
		
		start_index6 = 6;
		
		for(int i = 0; i < end_index8; i++)
		{
			enemy_8_group[i].survive = false;
			enemy_8_group[i].label.setVisible(false);
		}
		
		start_index8 = 2;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_6, 4);
		erase_enemy_bullet_package(enemy_bullet_8, 3);
		
		// enemy occur
		enemy_7_group[6].label.setLocation(100, 40);
		enemy_7_group[7].label.setLocation(300, 100);
		
		end_index7 = 8;
		
		enemy_8_group[2].label.setLocation(400, 200);
		enemy_8_group[3].label.setLocation(600, 200);
		end_index8 = 4;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = start_index7; i < end_index7; i++)
		{
			enemy_7_group[i].label.setVisible(true);
		}
		
		for(int i = start_index8; i < end_index8; i++)
		{
			enemy_8_group[i].label.setVisible(true);
		}		
		
		//while(frame_count_current - frame_count_start < 20000)
		while(frame_count_current - frame_count_start < 60000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack	
			for(int i = start_index7; i < end_index7; i++)
			{
				if(enemy_7_group[i].attack())
				{
					for(int j = 0; j <= 1; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 77, 65);
					}	
					for(int j = 2; j <= 3; j++)
					{
						attack_enemy(enemy_7_group[i], enemy_bullet_7[j], 119, 65);
					}
					Random random_attack_enemy_interval = new Random();
					enemy_7_group[i].attack_interval = 150 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_7_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index8; i < end_index8; i++)
			{
				if(enemy_8_group[i].attack())
				{
					for(int j = 0; j < 3; j++)
					{
						attack_enemy(enemy_8_group[i], enemy_bullet_8[j], 59, 143);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_8_group[i].attack_interval = 170 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_8_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index9; i < end_index9; i++)
			{
				if(enemy_9_group[i].attack())
				{
					enemy_9_attack_pattern++;
					if(enemy_9_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < 5; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_2[j], 102, 89);
						}
					}
					else
					{
						for(int j = 0; j < 7; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 36, 86);
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 163, 86);
						}
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_9_group[i].attack_interval = 170 + 20 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_9_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 4; i++)
			{
				enemy_bullet_move(enemy_bullet_7[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				enemy_bullet_move(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < 7; i++)
			{
				enemy_bullet_move(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_9_2[i]);
			}
					
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			check_enemy_damaged_package(enemy_7_group, start_index7, end_index7);
			check_enemy_damaged_package(enemy_8_group, start_index8, end_index8);
			check_enemy_damaged_package(enemy_9_group, start_index9, end_index9);			
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 4; i++)
			{
				check_player_damaged_package(enemy_bullet_7[i]);
			}
			
			for(int i = 0; i < 3; i++)
			{
				check_player_damaged_package(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < 7; i++)
			{
				check_player_damaged_package(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_9_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 6th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================	
		// rest time
		// enemy disapear
		for(int i = start_index7; i < end_index7; i++)
		{
			enemy_7_group[i].survive = false;
			enemy_7_group[i].label.setVisible(false);
		}
		
		start_index7 = 8;
		
		for(int i = start_index8; i < end_index8; i++)
		{
			enemy_8_group[i].survive = false;
			enemy_8_group[i].label.setVisible(false);
		}
		
		start_index8 = 4;
		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].survive = false;
			enemy_9_group[i].label.setVisible(false);
		}
		
		start_index9 = 1;
		
		erase_enemy_bullet_package(enemy_bullet_7, 4);
		erase_enemy_bullet_package(enemy_bullet_8, 3);
		erase_enemy_bullet_package(enemy_bullet_9_1, 7);
		erase_enemy_bullet_package(enemy_bullet_9_2, 5);
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 4000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// ===============================================================
		// 3rd stage middle boss occur
		enemy_3rd_middle_boss.label.setLocation(400, 30);
		enemy_3rd_middle_boss.survive = true;
		enemy_3rd_middle_boss.label.setVisible(true);
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_3rd_middle_boss.hp));
		Label_hp.setVisible(true);
		
		enemy_attack_frame_count = 0;
		
		Random random = new Random();
		int attack_pattern = -1;
		int frame_move_pattern = 0;
		int move_pattern = -1;
		
		while(enemy_3rd_middle_boss.survive != false && frame_count_current - frame_count_start < 90000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			power_generate_location = enemy_3rd_middle_boss.label.getLocation();
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			frame_move_pattern++;
			// set enemy moving pattern
			if(frame_move_pattern >= 70)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					if(enemy_3rd_middle_boss.label.getX() < 600)
					{
						enemy_3rd_middle_boss.move(30, 0);
					}
					else
					{
						enemy_3rd_middle_boss.move(-30, 0);
					}
					break;
				default:
					if(enemy_3rd_middle_boss.label.getX() > 300)
					{
						enemy_3rd_middle_boss.move(-30, 0);
					}
					else
					{
						enemy_3rd_middle_boss.move(30, 0);
					}
					break;
				}
				frame_move_pattern = 0;
			}
			
			if(enemy_3rd_middle_boss.attack())
			{
				attack_pattern = random.nextInt(5);
				if(attack_pattern == 0)
				{
					for(int i = 0; i < 7; i++)
					{
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 78, 87);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 122, 110);
					}
				}
				else if(attack_pattern == 1)
				{
					for(int i = 8; i < 13; i++)
					{
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 206, 87);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 164, 110);
					}
				}
				else if(attack_pattern == 2)
				{
					for(int i = 0; i < 13; i += 2)
					{
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 78, 87);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 122, 110);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 206, 87);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 164, 110);
					}
				}
				else if(attack_pattern == 3)
				{
					for(int i = 0; i < 7; i++)
					{
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_2[i], 143, 110);
					}
				}
				else
				{
					for(int i = 0; i < 13; i += 3)
					{
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 120, 41);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 78, 87);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 34, 125);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 162, 41);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 206, 87);
						attack_enemy(enemy_3rd_middle_boss, bullet_3rd_middle_boss_1[i], 250, 125);
					}
				}
			}
			else
			{
				enemy_3rd_middle_boss.attack_frame++;
			}
			
			for(int i = 0; i < 13; i++)
			{
				enemy_bullet_move(bullet_3rd_middle_boss_1[i]);
			}
			
			for(int i = 0; i < 7; i++)
			{
				enemy_bullet_move(bullet_3rd_middle_boss_2[i]);
			}
			
			check_enemy_damaged_package(enemy_3rd_middle_boss);
			Label_hp.setText(Integer.toString(enemy_3rd_middle_boss.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 13; i++)
			{
				check_player_damaged_package(bullet_3rd_middle_boss_1[i]);
			}
			
			for(int i = 0; i < 7; i++)
			{
				check_player_damaged_package(bullet_3rd_middle_boss_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd middle boss occur
		
		// ==================================================================
		// rest time
		
		Label_text_hp.setVisible(false);
		Label_hp.setVisible(false);
		
		erase_enemy_bullet_package(bullet_3rd_middle_boss_1, 13);
		erase_enemy_bullet_package(bullet_3rd_middle_boss_2, 7);
		
		// power generate		
		if(enemy_3rd_middle_boss.survive == false)
		{
			power_up_item.posX = power_generate_location.x + 143;
			power_up_item.posY = power_generate_location.y;
			power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
			power_up_item.label.setVisible(true);
			drop_power = true;
		}
		else
		{
			enemy_3rd_middle_boss.survive = false;
		}
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy occur
		frame_count_start = System.currentTimeMillis();
		frame_count_current = System.currentTimeMillis();
		
		enemy_10_group[0].label.setLocation(100, 0);
		enemy_10_group[1].label.setLocation(200, -50);
		enemy_10_group[1].attack_interval = 300;
		enemy_10_group[2].label.setLocation(300, -100);
		enemy_10_group[2].attack_interval = 500;
		enemy_10_group[3].label.setLocation(700, -100);
		enemy_10_group[3].attack_interval = 500;
		enemy_10_group[4].label.setLocation(800, -50);
		enemy_10_group[4].attack_interval = 300;
		enemy_10_group[5].label.setLocation(900, 0);		
		
		int start_index10 = 0;
		int end_index10 = 6;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 0; i < end_index10; i++)
		{
			enemy_10_group[i].label.setVisible(true);
		}
		
		int frame_enemy_move_downward = 0;
		
		while(frame_count_current - frame_count_start < 10000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			// enemy move downward
			if(frame_enemy_move_downward >= 50)
			{
				for(int i = start_index10; i < end_index10; i++)
				{
					if(enemy_10_group[i].label.getY() >= 0)
					{
						enemy_10_group[i].move(0,  3);
					}
					else
					{
						Point p = enemy_10_group[i].label.getLocation();
						enemy_10_group[i].label.setLocation(p.x, p.y + 3);
					}
					
					if(enemy_10_group[i].label.getY() >= 600)
					{
						enemy_10_group[i].survive = false;
						enemy_10_group[i].label.setLocation(0, 0);
						enemy_10_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index10; i < end_index10; i++)
			{
				if(enemy_10_group[i].attack() && enemy_10_group[i].label.getY() > -30)
				{
					for(int j = 0; j < 5; j++)
					{
						attack_enemy(enemy_10_group[i], enemy_bullet_10[j], 57, 45);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_10_group[i].attack_interval = 200 + 50 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_10_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_10[i]);
			}
			
			check_enemy_damaged_package(enemy_10_group, start_index10, end_index10);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_10[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 7th event
		
		// ==================================================================
		// enemy occur
		
		enemy_10_group[6].label.setLocation(100, -50);
		enemy_10_group[6].attack_interval = 400;
		enemy_10_group[7].label.setLocation(300, -50);
		enemy_10_group[7].attack_interval = 300;
		enemy_10_group[8].label.setLocation(500, -50);
		enemy_10_group[8].attack_interval = 200;
		enemy_10_group[9].label.setLocation(700, -50);
		enemy_10_group[9].attack_interval = 300;
		enemy_10_group[10].label.setLocation(900, -50);
		enemy_10_group[10].attack_interval = 400;
		
		end_index10 = 11;
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = 6; i < end_index10; i++)
		{
			enemy_10_group[i].label.setVisible(true);
		}
		
		frame_enemy_move_downward = 0;
		
		while(frame_count_current - frame_count_start < 25000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			// enemy move downward
			if(frame_enemy_move_downward >= 50)
			{
				for(int i = start_index10; i < end_index10; i++)
				{
					if(enemy_10_group[i].label.getY() >= 0)
					{
						enemy_10_group[i].move(0,  3);
					}
					else
					{
						Point p = enemy_10_group[i].label.getLocation();
						enemy_10_group[i].label.setLocation(p.x, p.y + 3);
					}
					
					if(enemy_10_group[i].label.getY() >= 600)
					{
						enemy_10_group[i].survive = false;
						enemy_10_group[i].label.setLocation(0, 0);
						enemy_10_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index10; i < end_index10; i++)
			{
				if(enemy_10_group[i].attack() && enemy_10_group[i].label.getY() > -30)
				{
					for(int j = 0; j < 5; j++)
					{
						attack_enemy(enemy_10_group[i], enemy_bullet_10[j], 57, 45);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_10_group[i].attack_interval = 200 + 50 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_10_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_10[i]);
			}
			
			check_enemy_damaged_package(enemy_10_group, start_index10, end_index10);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_10[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 8th event
		
		// ==================================================================		
		// enemy occur		
		enemy_9_group[1].label.setLocation(300, -50);
		enemy_9_group[2].label.setLocation(700, -50);
		
	    end_index9 = 3;
		
		enemy_9_attack_pattern = 0;
		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].label.setVisible(true);
		}
		
		frame_enemy_move_downward = 0;
		
		while(frame_count_current - frame_count_start < 40000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			// enemy move downward
			if(frame_enemy_move_downward >= 50)
			{
				for(int i = start_index9; i < end_index9; i++)
				{
					if(enemy_9_group[i].label.getY() >= 0)
					{
						enemy_9_group[i].move(0,  4);
					}
					else
					{
						Point p = enemy_9_group[i].label.getLocation();
						enemy_9_group[i].label.setLocation(p.x, p.y + 4);
					}
					
					if(enemy_9_group[i].label.getY() >= 600)
					{
						enemy_9_group[i].survive = false;
						enemy_9_group[i].label.setLocation(0, 0);
						enemy_9_group[i].label.setVisible(false);
					}
				}
				
				for(int i = start_index10; i < end_index10; i++)
				{
					if(enemy_10_group[i].label.getY() >= 0)
					{
						enemy_10_group[i].move(0,  3);
					}
					else
					{
						Point p = enemy_10_group[i].label.getLocation();
						enemy_10_group[i].label.setLocation(p.x, p.y + 3);
					}
					
					if(enemy_10_group[i].label.getY() >= 600)
					{
						enemy_10_group[i].survive = false;
						enemy_10_group[i].label.setLocation(0, 0);
						enemy_10_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack
			for(int i = start_index9; i < end_index9; i++)
			{
				if(enemy_9_group[i].attack())
				{
					enemy_9_attack_pattern++;
					if(enemy_9_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < 5; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_2[j], 102, 89);
						}
					}
					else
					{
						for(int j = 0; j < 7; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 36, 86);
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 163, 86);
						}
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_9_group[i].attack_interval = 170 + 20 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_9_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index10; i < end_index10; i++)
			{
				if(enemy_10_group[i].attack() && enemy_10_group[i].label.getY() > 0)
				{
					for(int j = 0; j < 5; j++)
					{
						attack_enemy(enemy_10_group[i], enemy_bullet_10[j], 57, 45);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_10_group[i].attack_interval = 200 + 50 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_10_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 7; i++)
			{
				enemy_bullet_move(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_9_2[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_10[i]);
			}
			
			check_enemy_damaged_package(enemy_9_group, start_index9, end_index9);
			check_enemy_damaged_package(enemy_10_group, start_index10, end_index10);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 7; i++)
			{
				check_player_damaged_package(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_9_2[i]);
			}
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_10[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 9th event
		
		// ==================================================================
		// enemy disappear		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].survive = false;
			enemy_9_group[i].label.setVisible(false);
		}
		
		start_index9 = end_index9;
		
		for(int i = start_index10; i < end_index10; i++)
		{
			enemy_10_group[i].survive = false;
			enemy_10_group[i].label.setVisible(false);
		}
		
		start_index10 = end_index10;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_9_1, 7);
		erase_enemy_bullet_package(enemy_bullet_9_2, 5);
		erase_enemy_bullet_package(enemy_bullet_10, 5);
		
	    // enemy occur
		end_index10 = 21;
		
		for(int i = start_index10; i <= 15; i++)
		{
			enemy_10_group[i].label.setLocation(200, 70 - 70 * (i - 11));
		}
		
		for(int i = 16; i < end_index10; i++)
		{
			enemy_10_group[i].label.setLocation(700, 70 - 70 * (i - 16));
		}
		
		for(int i = start_index10; i < end_index10; i++)
		{
			enemy_10_group[i].attack_interval = 200;
			enemy_10_group[i].label.setVisible(true);
		}
		
		// power occur
		power_up_item.posX = 800;
		power_up_item.posY = -50;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		frame_enemy_move_downward = 0;
		
		while(frame_count_current - frame_count_start < 70000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			// enemy move downward
			if(frame_enemy_move_downward >= 50)
			{			
				Point p_power = power_up_item.label.getLocation();
				power_up_item.label.setLocation(p_power.x, p_power.y + 10);				
				
				for(int i = start_index10; i < end_index10; i++)
				{
					if(enemy_10_group[i].label.getY() >= 0)
					{
						enemy_10_group[i].move(0,  3);
					}
					else
					{
						Point p = enemy_10_group[i].label.getLocation();
						enemy_10_group[i].label.setLocation(p.x, p.y + 3);
					}
					
					if(enemy_10_group[i].label.getY() >= 600)
					{
						enemy_10_group[i].survive = false;
						enemy_10_group[i].label.setLocation(0, 0);
						enemy_10_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			// enemy attack			
			for(int i = start_index10; i < end_index10; i++)
			{
				if(enemy_10_group[i].attack() && enemy_10_group[i].label.getY() > -30)
				{
					for(int j = 0; j < 5; j++)
					{
						attack_enemy(enemy_10_group[i], enemy_bullet_10[j], 57, 45);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_10_group[i].attack_interval = 200 + 50 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_10_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < 5; i++)
			{
				enemy_bullet_move(enemy_bullet_10[i]);
			}
			
			check_enemy_damaged_package(enemy_10_group, start_index10, end_index10);
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 5; i++)
			{
				check_player_damaged_package(enemy_bullet_10[i]);
			}
			
			check_player_get_power();			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage 10th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// rest time
		// enemy disappear		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].survive = false;
			enemy_9_group[i].label.setVisible(false);
		}
		
		start_index9 = end_index9;
		
		for(int i = start_index10; i < end_index10; i++)
		{
			enemy_10_group[i].survive = false;
			enemy_10_group[i].label.setVisible(false);
		}
		
		start_index10 = end_index10;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_9_1, 7);
		erase_enemy_bullet_package(enemy_bullet_9_2, 5);
		erase_enemy_bullet_package(enemy_bullet_10, 5);
		
		// test code
		/*
		int[] frame_enemy_6_move = {0, 0, 0, 0, 0, 0};
		int[] random_frame_enemy_6_move = {100, 120, 140, 140, 120, 100};
		
		int start_index6 = 0;
		int end_index6 = 6;
		
		int start_index7 = 0;
		int end_index7 = 6;
		
		int start_index8 = 0;
		int end_index8 = 2;
		
		int start_index9 = 1;
		int end_index9 = 1;
		
		int enemy_9_attack_pattern = 0;
		
		int frame_move_pattern = 0;
		int move_pattern = -1;
		int attack_pattern = -1;
		
		Random random = new Random();*/
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// ==================================================================
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
			bullet_player_2[i].label.setLocation(0, 0);
			bullet_player_2[i].label.setVisible(false);
		}
		
		// 3rd stage boss occur
		enemy_3rd_stage_boss_1.label.setLocation(500, 0);
		enemy_3rd_stage_boss_1.survive = true;
		enemy_3rd_stage_boss_1.label.setVisible(true);
		
		Point enemy_3rd_stage_boss_position = enemy_3rd_stage_boss_1.label.getLocation();
		
		boolean attack_1_pattern = true;
		int attack_3_pattern_count = 0;
		int attack_4_pattern_count = 0;
		int attack_5_pattern_count = 0;
		
		for(int frame = 0; frame < 50; frame++)
		{
			enemy_3rd_stage_boss_1.move(0, 1);
			wait(20);
		}
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_3rd_stage_boss_1.hp));
		Label_hp.setVisible(true);
		
		frame_move_pattern = 0;
		
		while(enemy_3rd_stage_boss_1.survive != false)
		{
			frame_move_pattern++;
			wait(5);
			frame_count++;
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			enemy_3rd_stage_boss_position = enemy_3rd_stage_boss_1.label.getLocation();
			
			// set enemy moving pattern
			if(frame_move_pattern >= 70)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					if(enemy_3rd_stage_boss_1.label.getX() < 700)
					{
						enemy_3rd_stage_boss_1.move(30, 0);
					}
					else
					{
						enemy_3rd_stage_boss_1.move(-30, 0);
					}
					break;
				default:
					if(enemy_3rd_stage_boss_1.label.getX() > 100)
					{
						enemy_3rd_stage_boss_1.move(-30, 0);
					}
					else
					{
						enemy_3rd_stage_boss_1.move(30, 0);
					}
					break;
				}
				frame_move_pattern = 0;
			}
			
			if(enemy_3rd_stage_boss_1.attack())
			{
				// decide attack_pattern
				if(attack_1_pattern == false)
				{
					attack_pattern = 0;
				}
				else if(attack_3_pattern_count != 0)
				{
					attack_pattern = 2;
				}
				else if(attack_4_pattern_count != 0)
				{
					attack_pattern = 3;
				}
				else if(attack_5_pattern_count != 0)
				{
					attack_pattern = 4;
				}
				else
				{
					attack_pattern = random.nextInt(5);
				}
				
				if(attack_pattern == 0)
				{
					if(attack_1_pattern == true)
					{
						enemy_3rd_stage_boss_1.attack_interval = 20;
						for(int i = 0; i < 6; i++)
						{
							attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_1[i], 33, 177);
						}
						attack_1_pattern = false;
					}
					else
					{
						for(int i = 0; i < 6; i++)
						{
							attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_1[i], 162, 177);
						}
						attack_1_pattern = true;
						enemy_3rd_stage_boss_1.attack_interval = 130;
					}
				}
				else if(attack_pattern == 1)
				{
					for(int i = 0; i < 6; i++)
					{
						attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_1[i], 33, 221);
						attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_1[i], 162, 221);
					}
				}
				else if(attack_pattern == 2)
				{
					enemy_3rd_stage_boss_1.attack_interval = 70;
					for(int i = 0; i < 11; i++)
					{
						attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[i], 98, 289);
					}
					
					if(attack_3_pattern_count < 3)
					{
						attack_3_pattern_count++;
					}
					else
					{
						attack_3_pattern_count = 0;
						enemy_3rd_stage_boss_1.attack_interval = 130;
					}
				}
				else if(attack_pattern == 3)
				{
					enemy_3rd_stage_boss_1.attack_interval = 20;
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[attack_4_pattern_count], 11, 65);
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[attack_4_pattern_count], 183, 65);
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[attack_4_pattern_count], 11, 154);
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[attack_4_pattern_count], 183, 154);
					if(attack_4_pattern_count < 10)
					{
						attack_4_pattern_count++;
					}
					else
					{
						attack_4_pattern_count = 0;
						enemy_3rd_stage_boss_1.attack_interval = 130;
					}
				}
				else
				{
					enemy_3rd_stage_boss_1.attack_interval = 20;
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[10 - attack_5_pattern_count], 11, 65);
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[10 - attack_5_pattern_count], 183, 65);
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[10 - attack_5_pattern_count], 11, 154);
					attack_enemy(enemy_3rd_stage_boss_1, bullet_3rd_stage_boss_1_2[10 - attack_5_pattern_count], 183, 154);
					if(attack_5_pattern_count < 10)
					{
						attack_5_pattern_count++;
					}
					else
					{
						attack_5_pattern_count = 0;
						enemy_3rd_stage_boss_1.attack_interval = 130;
					}
				}
			}
			else
			{
				enemy_3rd_stage_boss_1.attack_frame++;
			}
			
			for(int i = 0; i < 6; i++)
			{
				enemy_bullet_move(bullet_3rd_stage_boss_1_1[i]);
			}
			
			for(int i = 0; i < 11; i++)
			{
				enemy_bullet_move(bullet_3rd_stage_boss_1_2[i]);
			}
			
			check_enemy_damaged_package(enemy_3rd_stage_boss_1);
			Label_hp.setText(Integer.toString(enemy_3rd_stage_boss_1.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 6; i++)
			{
				check_player_damaged_package(bullet_3rd_stage_boss_1_1[i]);
			}
			
			for(int i = 0; i < 11; i++)
			{
				check_player_damaged_package(bullet_3rd_stage_boss_1_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage boss_1 occur 
		
		// ==================================================================
		erase_enemy_bullet_package(bullet_3rd_stage_boss_1_1, 6);
		erase_enemy_bullet_package(bullet_3rd_stage_boss_1_2, 11);
		
		Label_hp.setVisible(false);
		Label_text_hp.setVisible(false);
		
		// power generate
		power_up_item.posX = enemy_3rd_stage_boss_position.x + 98;
		power_up_item.posY = enemy_3rd_stage_boss_position.y;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		// ==================================================================
		// rest time
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 5000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			check_player_invincibility();
			use_bomb_package();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		// disappear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
			bullet_player_2[i].label.setLocation(0, 0);
			bullet_player_2[i].label.setVisible(false);
		}
		
		// disappear bullet bomb
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i].label.setLocation(1200 ,0);
			bullet_bomb[i].label.setVisible(false);
		}

		enemy_3rd_stage_boss_1.label.setVisible(true);
		
		while(enemy_3rd_stage_boss_position.y >= -500)
		{
			wait(20);
			enemy_3rd_stage_boss_1.label.setLocation(enemy_3rd_stage_boss_position.x, enemy_3rd_stage_boss_position.y - 3);
			enemy_3rd_stage_boss_position = enemy_3rd_stage_boss_1.label.getLocation();
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		enemy_3rd_stage_boss_1.label.setVisible(false);
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// 3rd stage boss 2 occur
		enemy_3rd_stage_boss_2.label.setLocation(450, -30);
		enemy_3rd_stage_boss_2.survive = true;
		enemy_3rd_stage_boss_2.label.setVisible(true);
		
		attack_1_pattern = true;
		attack_3_pattern_count = 0;
		attack_4_pattern_count = 0;
		attack_5_pattern_count = 0;
		int frame_special_attack = 0;
		boolean special_pattern = false;
		Point special_bullet_1 = bullet_3rd_stage_boss_2_3[0][0].label.getLocation();
		Point special_bullet_2 = bullet_3rd_stage_boss_2_3[1][0].label.getLocation();
		
		for(int frame = 0; frame < 50; frame++)
		{
			enemy_3rd_stage_boss_2.move(0, 1);
			wait(20);
		}
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_3rd_stage_boss_2.hp));
		Label_hp.setVisible(true);
		
		frame_move_pattern = 0;
		
		while(enemy_3rd_stage_boss_2.survive != false)
		{
			frame_move_pattern++;
			wait(5);
			frame_count++;
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			
			enemy_3rd_stage_boss_position = enemy_3rd_stage_boss_2.label.getLocation();
			
			// set enemy moving pattern
			if(frame_move_pattern >= 70)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					if(enemy_3rd_stage_boss_2.label.getX() < 700)
					{
						enemy_3rd_stage_boss_2.move(30, 0);
					}
					else
					{
						enemy_3rd_stage_boss_2.move(-30, 0);
					}
					break;
				default:
					if(enemy_3rd_stage_boss_2.label.getX() > 300)
					{
						enemy_3rd_stage_boss_2.move(-30, 0);
					}
					else
					{
						enemy_3rd_stage_boss_2.move(30, 0);
					}
					break;
				}
				frame_move_pattern = 0;
			}
			
			if(enemy_3rd_stage_boss_2.attack())
			{
				// decide attack_pattern
				if(special_pattern == true)
				{
					attack_pattern = 5;
				}
			    else if(attack_1_pattern == false)
				{
					attack_pattern = 0;
				}
				else if(attack_3_pattern_count != 0)
				{
					attack_pattern = 2;
				}
				else if(attack_4_pattern_count != 0)
				{
					attack_pattern = 3;
				}
				else if(attack_5_pattern_count != 0)
				{
					attack_pattern = 4;
				}
				else
				{
					attack_pattern = random.nextInt(5);
				}
				
				if(frame_special_attack >= 25)
				{
					if(special_pattern == false)
					{
						special_pattern = true;
						for(int i = 0; i < 2; i++)
						{
							attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_3[i], 122, 70);
						}
					}
					frame_special_attack = -1;
				}
				else if(attack_pattern == 0)
				{
					if(attack_1_pattern == true)
					{
						enemy_3rd_stage_boss_2.attack_interval = 15;
						for(int i = 0; i < 6; i++)
						{
							attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 35, 110);
						}
						attack_1_pattern = false;
					}
					else
					{
						for(int i = 0; i < 6; i++)
						{
							attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 207, 110);
						}
						attack_1_pattern = true;
						enemy_3rd_stage_boss_2.attack_interval = 130;
					}
				}
				else if(attack_pattern == 1)
				{
					for(int i = 0; i < 6; i++)
					{
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 35, 80);
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 35, 110);
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 35, 140);
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 207, 80);
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 207, 110);
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_1[i], 207, 140);
					}
				}
				else if(attack_pattern == 2)
				{
					enemy_3rd_stage_boss_2.attack_interval = 60;
					for(int i = 0; i < 13; i++)
					{
						attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[i], 122, 70);
					}
					
					if(attack_3_pattern_count < 6)
					{
						attack_3_pattern_count++;
					}
					else
					{
						attack_3_pattern_count = 0;
						enemy_3rd_stage_boss_2.attack_interval = 130;
					}
				}
				else if(attack_pattern == 3)
				{
					enemy_3rd_stage_boss_2.attack_interval = 15;
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[attack_4_pattern_count], 11, 110);
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[attack_4_pattern_count], 11, 150);
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[attack_4_pattern_count], 228, 110);
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[attack_4_pattern_count], 228, 150);
					if(attack_4_pattern_count < 12)
					{
						attack_4_pattern_count++;
					}
					else
					{
						attack_4_pattern_count = 0;
						enemy_3rd_stage_boss_2.attack_interval = 130;
					}
				}
				else if(attack_pattern == 4)
				{
					enemy_3rd_stage_boss_2.attack_interval = 15;
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[12 - attack_5_pattern_count], 11, 110);
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[12 - attack_5_pattern_count], 11, 150);
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[12 - attack_5_pattern_count], 228, 110);
					attack_enemy(enemy_3rd_stage_boss_2, bullet_3rd_stage_boss_2_2[12 - attack_5_pattern_count], 228, 150);
					if(attack_5_pattern_count < 12)
					{
						attack_5_pattern_count++;
					}
					else
					{
						attack_5_pattern_count = 0;
						enemy_3rd_stage_boss_2.attack_interval = 130;
					}
				}
				else
				{
					// empty
				}
				special_bullet_1 = bullet_3rd_stage_boss_2_3[0][0].label.getLocation();
				special_bullet_2 = bullet_3rd_stage_boss_2_3[1][0].label.getLocation();
				if(special_bullet_1.y >= 300 || special_bullet_2.y >= 300)
				{
					for(int i = 0; i < 16; i++)
					{
						attack_enemy(bullet_3rd_stage_boss_2_3[0], bullet_3rd_stage_boss_2_4[i], 20, 20, 1);
						attack_enemy(bullet_3rd_stage_boss_2_3[1], bullet_3rd_stage_boss_2_4[i], 20, 20, 1);
					}
					erase_enemy_bullet_package(bullet_3rd_stage_boss_2_3, 2, 1);
					special_pattern = false;
				}
				frame_special_attack++;
			}
			else
			{
				enemy_3rd_stage_boss_2.attack_frame++;
			}
			
			for(int i = 0; i < 6; i++)
			{
				enemy_bullet_move(bullet_3rd_stage_boss_2_1[i]);
			}
			
			for(int i = 0; i < 13; i++)
			{
				enemy_bullet_move(bullet_3rd_stage_boss_2_2[i]);
			}
			
			for(int i = 0; i < 2; i++)
			{
				enemy_bullet_move(bullet_3rd_stage_boss_2_3[i], 1);
			}
			
			for(int i = 0; i < 16; i++)
			{
				enemy_bullet_move(bullet_3rd_stage_boss_2_4[i]);
			}
			
			check_enemy_damaged_package(enemy_3rd_stage_boss_2);
			Label_hp.setText(Integer.toString(enemy_3rd_stage_boss_2.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < 6; i++)
			{
				check_player_damaged_package(bullet_3rd_stage_boss_2_1[i]);
			}
			
			for(int i = 0; i < 11; i++)
			{
				check_player_damaged_package(bullet_3rd_stage_boss_2_2[i]);
			}
			
			for(int i = 0; i < 2; i++)
			{
				check_player_damaged_package(bullet_3rd_stage_boss_2_3[i], 1);
			}
			
			for(int i = 0; i < 16; i++)
			{
				check_player_damaged_package(bullet_3rd_stage_boss_2_4[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 3rd stage boss_2 occur 
		
		// ==================================================================
		erase_enemy_bullet_package(bullet_3rd_stage_boss_2_1, 6);
		erase_enemy_bullet_package(bullet_3rd_stage_boss_2_2, 13);
		erase_enemy_bullet_package(bullet_3rd_stage_boss_2_3, 2, 1);
		erase_enemy_bullet_package(bullet_3rd_stage_boss_2_4, 16);
		
		Label_hp.setVisible(false);
		Label_text_hp.setVisible(false);
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
			bullet_player_2[i].label.setLocation(0, 0);
			bullet_player_2[i].label.setVisible(false);
		}
		
		// disapear bullet bomb
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i].label.setLocation(1200 ,0);
			bullet_bomb[i].label.setVisible(false);
		}
		
		// power generate
		power_up_item.posX = enemy_3rd_stage_boss_position.x + 121;
		power_up_item.posY = enemy_3rd_stage_boss_position.y;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		// ==================================================================
		// rest time
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 6000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			check_player_invincibility();
			use_bomb_package();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		// disapear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
			bullet_player_2[i].label.setLocation(0, 0);
			bullet_player_2[i].label.setVisible(false);
		}
		
        Point p = Label_player.getLocation();
		while(p.y >= -20)
		{
			wait(20);
			Label_player.setLocation(p.x, p.y - 20);
			p = Label_player.getLocation();
		}
		
		stage++;
		Label_stage.setText(Integer.toString(stage));
		
		drop_power = false;
		power_up_item.posY = 0;
		shopping = true;
	} // end of stage3
	
	//build stage4
	private static void stage4()
	{		
		Label_lifes.setText(Integer.toString(lifes));
		Label_score.setText(Integer.toString(score));
		Label_stage.setText(Integer.toString(stage));
		Label_player.setLocation(590, 860);
		
		set_player_color_by_shield_barrier();
		
		Point p = Label_player.getLocation();
		while(p.y >= -20)
		{
			wait(20);
			Label_player.setLocation(p.x, p.y - 20);
			p = Label_player.getLocation();
		}
		
		Label_player.setLocation(590, 860);
		Label_loading.setVisible(true);
		
		JPanel Panel_laser_1 = new JPanel();
		JPanel Panel_laser_2 = new JPanel();
		Panel_laser_1.setBounds(946, 260, 15, 850);
		Panel_laser_2.setBounds(300, 260, 15, 850);
		panel_game_screen.add(Panel_laser_1);
		panel_game_screen.add(Panel_laser_2);
		Panel_laser_1.setVisible(false);
		Panel_laser_2.setVisible(false);
		
		// bullet direction setting
		final int enemy_bullet_4_direction = 5;
		final int enemy_bullet_8_direction = 3;
		final int enemy_bullet_9_1_direction = 7;
		final int enemy_bullet_9_2_direction = 5;
		final int enemy_bullet_10_direction = 5;
		final int enemy_bullet_11_direction = 15;
		final int enemy_bullet_12_left_direction = 3;
		final int enemy_bullet_12_right_direction = 3;
		final int enemy_bullet_12_down_direction = 3;
		final int enemy_bullet_13_direction = 9;
		final int enemy_bullet_14_1_direction = 5;
		final int enemy_bullet_14_2_direction = 4;
		final int enemy_bullet_4th_middle_boss_1_direction = 5;
		final int enemy_bullet_4th_middle_boss_2_direction = 5;
		final int enemy_bullet_4th_middle_boss_3_direction = 4;
		final int enemy_bullet_4th_middle_boss_4_direction = 4;
		final int enemy_bullet_4th_middle_boss_5_direction = 24;
		final int enemy_bullet_4th_middle_boss_6_direction = 24;
		final int enemy_bullet_4th_boss_1_1_direction = 13;
		final int enemy_bullet_4th_boss_1_2_direction = 9;
		final int enemy_bullet_4th_boss_1_3_direction = 1;
		final int enemy_bullet_4th_boss_2_1_direction = 24;
		final int enemy_bullet_4th_boss_2_2_direction = 24;
		final int enemy_bullet_4th_boss_2_3_direction = 24;
		final int enemy_bullet_4th_boss_2_4_direction = 20;
		final int enemy_bullet_4th_boss_2_5_direction = 2;
		final int enemy_bullet_4th_boss_2_6_direction = 15;
		final int enemy_bullet_4th_boss_2_7_direction = 1; // entire attack
		final int enemy_bullet_4th_boss_2_8_direction = 1; // entire attack
		
		// bullet color setting
		float[] BROWN = Color.RGBtoHSB(153, 102, 51, null);
		float[] WHITE_GRAY = Color.RGBtoHSB(191, 191, 191, null);
		float[] DARK_CYAN = Color.RGBtoHSB(0, 108, 146, null);
		
		Bullet[][] enemy_bullet_4 = new Bullet[enemy_bullet_4_direction][max_enemy_bullet];
		Bullet[][] enemy_bullet_8 = new Bullet[enemy_bullet_8_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_9_1 = new Bullet_Radian[enemy_bullet_9_1_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_9_2 = new Bullet_Radian[enemy_bullet_9_2_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_10 = new Bullet_Radian[enemy_bullet_10_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_11 = new Bullet_Radian[enemy_bullet_11_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_12_left = new Bullet_Radian[enemy_bullet_12_left_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_12_right = new Bullet_Radian[enemy_bullet_12_right_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_12_down = new Bullet_Radian[enemy_bullet_12_down_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_13 = new Bullet_Radian[enemy_bullet_13_direction][max_enemy_bullet];
		Bullet_Radian[][] enemy_bullet_14_1 = new Bullet_Radian[enemy_bullet_14_1_direction][max_enemy_bullet];
		Bullet[][] enemy_bullet_14_2 = new Bullet[enemy_bullet_14_2_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_middle_boss_1 = new Bullet_Radian[enemy_bullet_4th_middle_boss_1_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_middle_boss_2 = new Bullet_Radian[enemy_bullet_4th_middle_boss_2_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_middle_boss_3 = new Bullet_Radian[enemy_bullet_4th_middle_boss_3_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_middle_boss_4 = new Bullet_Radian[enemy_bullet_4th_middle_boss_4_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_middle_boss_5 = new Bullet_Radian[enemy_bullet_4th_middle_boss_5_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_middle_boss_6 = new Bullet_Radian[enemy_bullet_4th_middle_boss_6_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_1_1 = new Bullet_Radian[enemy_bullet_4th_boss_1_1_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_1_2 = new Bullet_Radian[enemy_bullet_4th_boss_1_2_direction][max_enemy_bullet];
		Bullet[] bullet_4th_boss_1_3 = new Bullet[max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_2_1 = new Bullet_Radian[enemy_bullet_4th_boss_2_1_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_2_2 = new Bullet_Radian[enemy_bullet_4th_boss_2_2_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_2_3 = new Bullet_Radian[enemy_bullet_4th_boss_2_3_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_2_4 = new Bullet_Radian[enemy_bullet_4th_boss_2_4_direction][max_enemy_bullet];
		Bullet_Radian[][] bullet_4th_boss_2_5 = new Bullet_Radian[enemy_bullet_4th_boss_2_5_direction][1];
		Bullet_Radian[][] bullet_4th_boss_2_6 = new Bullet_Radian[enemy_bullet_4th_boss_2_6_direction][max_enemy_bullet];
		Bullet[] bullet_4th_boss_2_7 = new Bullet[200];
		Bullet[] bullet_4th_boss_2_8 = new Bullet[200];
		
		// set enemy_bullet
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_4[i][j] = new Bullet();
				enemy_bullet_4[i][j].label = new JLabel("¡Ü");
				enemy_bullet_4[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_4[i][j].label.setForeground(Color.GREEN);
				enemy_bullet_4[i][j].posX = 1200;
				enemy_bullet_4[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_4[i][j].move_x = -4;
					enemy_bullet_4[i][j].move_y = 3;
					break;
				case 1:
					enemy_bullet_4[i][j].move_x = -2;
					enemy_bullet_4[i][j].move_y = 4;
					break;
				case 2:
					enemy_bullet_4[i][j].move_x = 0;
					enemy_bullet_4[i][j].move_y = 4;
					break;
				case 3:
					enemy_bullet_4[i][j].move_x = 2;
					enemy_bullet_4[i][j].move_y = 4;
					break;
				default:
					enemy_bullet_4[i][j].move_x = 4;
					enemy_bullet_4[i][j].move_y = 3;
					break;
				}
				
				enemy_bullet_4[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_4[i][j].label.setLocation(enemy_bullet_4[i][j].posX, enemy_bullet_4[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_4[i][j].label);
				enemy_bullet_4[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_8[i][j] = new Bullet();
				enemy_bullet_8[i][j].label = new JLabel("¡Ü");
				enemy_bullet_8[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				enemy_bullet_8[i][j].label.setForeground(Color.BLUE);
				enemy_bullet_8[i][j].posX = 1200;
				enemy_bullet_8[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_8[i][j].move_x = -2;
					break;
				case 1:
					enemy_bullet_8[i][j].move_x = 0;
					break;
				default:
					enemy_bullet_8[i][j].move_x = 2;
					break;
				}
				
				enemy_bullet_8[i][j].move_y = 5;
				enemy_bullet_8[i][j].label.setBounds(0, 0, 40, 40);
				enemy_bullet_8[i][j].label.setLocation(enemy_bullet_8[i][j].posX, enemy_bullet_8[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_8[i][j].label);
				enemy_bullet_8[i][j].label.setVisible(false);
			}
		}
		
		// set enemy_bullet using radian
		for(int i = 0; i < enemy_bullet_9_1_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_9_1[i][j] = new Bullet_Radian(30.0f + 20.0f * i);
				enemy_bullet_9_1[i][j].label = new JLabel("¡Ü");
				enemy_bullet_9_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_9_1[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				enemy_bullet_9_1[i][j].speed = 4;
				enemy_bullet_9_1[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_9_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_9_1[i][j].label);
				enemy_bullet_9_1[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_9_2_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_9_2[i][j] = new Bullet_Radian(30.0f + 30.0f * i);
				enemy_bullet_9_2[i][j].label = new JLabel("¡Ü");
				enemy_bullet_9_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				enemy_bullet_9_2[i][j].label.setForeground(Color.BLUE);
				enemy_bullet_9_2[i][j].speed = 5;
				enemy_bullet_9_2[i][j].label.setBounds(0, 0, 40, 40);
				enemy_bullet_9_2[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_9_2[i][j].label);
				enemy_bullet_9_2[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_10_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_10[i][j] = new Bullet_Radian(45.0f + 22.5f * i);
				enemy_bullet_10[i][j].label = new JLabel("¡Ü");
				enemy_bullet_10[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_10[i][j].label.setForeground(Color.GREEN);
				enemy_bullet_10[i][j].speed = 4;
				enemy_bullet_10[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_10[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_10[i][j].label);
				enemy_bullet_10[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_11_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_11[i][j] = new Bullet_Radian(20.0f + 10.0f * i);
				enemy_bullet_11[i][j].label = new JLabel("¡Ü");
				enemy_bullet_11[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_11[i][j].label.setForeground(Color.ORANGE);
				enemy_bullet_11[i][j].speed = 3;
				enemy_bullet_11[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_11[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_11[i][j].label);
				enemy_bullet_11[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_12_left_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_12_left[i][j] = new Bullet_Radian(22.5f + 22.5f * i);
				enemy_bullet_12_left[i][j].label = new JLabel("¡Ü");
				enemy_bullet_12_left[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_12_left[i][j].label.setForeground(Color.getHSBColor(WHITE_GRAY[0], WHITE_GRAY[1], WHITE_GRAY[2]));
				enemy_bullet_12_left[i][j].speed = 2;
				enemy_bullet_12_left[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_12_left[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_12_left[i][j].label);
				enemy_bullet_12_left[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_12_right_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_12_right[i][j] = new Bullet_Radian(112.5f + 22.5f * i);
				enemy_bullet_12_right[i][j].label = new JLabel("¡Ü");
				enemy_bullet_12_right[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_12_right[i][j].label.setForeground(Color.getHSBColor(WHITE_GRAY[0], WHITE_GRAY[1], WHITE_GRAY[2]));
				enemy_bullet_12_right[i][j].speed = 2;
				enemy_bullet_12_right[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_12_right[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_12_right[i][j].label);
				enemy_bullet_12_right[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_12_down_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_12_down[i][j] = new Bullet_Radian(67.5f + 22.5f * i);
				enemy_bullet_12_down[i][j].label = new JLabel("¡Ü");
				enemy_bullet_12_down[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_12_down[i][j].label.setForeground(Color.getHSBColor(WHITE_GRAY[0], WHITE_GRAY[1], WHITE_GRAY[2]));
				enemy_bullet_12_down[i][j].speed = 2;
				enemy_bullet_12_down[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_12_down[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_12_down[i][j].label);
				enemy_bullet_12_down[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_13_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_13[i][j] = new Bullet_Radian(18.0f + 18.0f * i);
				enemy_bullet_13[i][j].label = new JLabel("¡Ü");
				enemy_bullet_13[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				enemy_bullet_13[i][j].label.setForeground(Color.RED);
				enemy_bullet_13[i][j].speed = 7;
				enemy_bullet_13[i][j].label.setBounds(0, 0, 40, 40);
				enemy_bullet_13[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_13[i][j].label);
				enemy_bullet_13[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_14_1_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_14_1[i][j] = new Bullet_Radian(45.0f + 22.5f * i);
				enemy_bullet_14_1[i][j].label = new JLabel("¡Ü");
				enemy_bullet_14_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_14_1[i][j].label.setForeground(Color.getHSBColor(BROWN[0], BROWN[1], BROWN[2]));
				enemy_bullet_14_1[i][j].speed = 4;
				enemy_bullet_14_1[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_14_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(enemy_bullet_14_1[i][j].label);
				enemy_bullet_14_1[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_14_2_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				enemy_bullet_14_2[i][j] = new Bullet();
				enemy_bullet_14_2[i][j].label = new JLabel("¡Ü");
				enemy_bullet_14_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				enemy_bullet_14_2[i][j].label.setForeground(Color.ORANGE);
				enemy_bullet_14_2[i][j].posX = 1200;
				enemy_bullet_14_2[i][j].posY = 0;
				
				switch(i)
				{
				case 0:
					enemy_bullet_14_2[i][j].move_x = -2;
					enemy_bullet_14_2[i][j].move_y = 5;
					break;
				case 1:
					enemy_bullet_14_2[i][j].move_x = -1;
					enemy_bullet_14_2[i][j].move_y = 5;
					break;
				case 2:
					enemy_bullet_14_2[i][j].move_x = 1;
					enemy_bullet_14_2[i][j].move_y = 5;
					break;
				default:
					enemy_bullet_14_2[i][j].move_x = 2;
					enemy_bullet_14_2[i][j].move_y = 5;
					break;
				}
				
				enemy_bullet_14_2[i][j].label.setBounds(0, 0, 30, 30);
				enemy_bullet_14_2[i][j].label.setLocation(enemy_bullet_14_2[i][j].posX, enemy_bullet_14_2[i][j].posY); // original position(not used)
				panel_game_screen.add(enemy_bullet_14_2[i][j].label);
				enemy_bullet_14_2[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_middle_boss_1_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_middle_boss_1[i][j] = new Bullet_Radian(22.5f + 11.25f * i);
				bullet_4th_middle_boss_1[i][j].label = new JLabel("¡Ü");
				bullet_4th_middle_boss_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_middle_boss_1[i][j].label.setForeground(Color.PINK);
				bullet_4th_middle_boss_1[i][j].speed = 8;
				bullet_4th_middle_boss_1[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_middle_boss_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_middle_boss_1[i][j].label);
				bullet_4th_middle_boss_1[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_middle_boss_2_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_middle_boss_2[i][j] = new Bullet_Radian(112.5f + 11.25f * i);
				bullet_4th_middle_boss_2[i][j].label = new JLabel("¡Ü");
				bullet_4th_middle_boss_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_middle_boss_2[i][j].label.setForeground(Color.PINK);
				bullet_4th_middle_boss_2[i][j].speed = 8;
				bullet_4th_middle_boss_2[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_middle_boss_2[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_middle_boss_2[i][j].label);
				bullet_4th_middle_boss_2[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_middle_boss_3_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_middle_boss_3[i][j] = new Bullet_Radian(20.0f + 30.0f * i);
				bullet_4th_middle_boss_3[i][j].label = new JLabel("¡Ü");
				bullet_4th_middle_boss_3[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_middle_boss_3[i][j].label.setForeground(Color.MAGENTA);
				bullet_4th_middle_boss_3[i][j].speed = 8;
				bullet_4th_middle_boss_3[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_middle_boss_3[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_middle_boss_3[i][j].label);
				bullet_4th_middle_boss_3[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_middle_boss_4_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_middle_boss_4[i][j] = new Bullet_Radian(70.0f + 30.0f * i);
				bullet_4th_middle_boss_4[i][j].label = new JLabel("¡Ü");
				bullet_4th_middle_boss_4[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_middle_boss_4[i][j].label.setForeground(Color.MAGENTA);
				bullet_4th_middle_boss_4[i][j].speed = 8;
				bullet_4th_middle_boss_4[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_middle_boss_4[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_middle_boss_4[i][j].label);
				bullet_4th_middle_boss_4[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_middle_boss_5_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_middle_boss_5[i][j] = new Bullet_Radian(15.0f * i);
				bullet_4th_middle_boss_5[i][j].label = new JLabel("¡Ü");
				bullet_4th_middle_boss_5[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_4th_middle_boss_5[i][j].label.setForeground(Color.ORANGE);
				bullet_4th_middle_boss_5[i][j].speed = 3;
				bullet_4th_middle_boss_5[i][j].label.setBounds(0, 0, 30, 30);
				bullet_4th_middle_boss_5[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_middle_boss_5[i][j].label);
				bullet_4th_middle_boss_5[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_middle_boss_6_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_middle_boss_6[i][j] = new Bullet_Radian(7.5f + 15.0f * i);
				bullet_4th_middle_boss_6[i][j].label = new JLabel("¡Ü");
				bullet_4th_middle_boss_6[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_4th_middle_boss_6[i][j].label.setForeground(Color.ORANGE);
				bullet_4th_middle_boss_6[i][j].speed = 3;
				bullet_4th_middle_boss_6[i][j].label.setBounds(0, 0, 30, 30);
				bullet_4th_middle_boss_6[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_middle_boss_6[i][j].label);
				bullet_4th_middle_boss_6[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_1_1_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_1_1[i][j] = new Bullet_Radian(30.0f + 10.0f * i);
				bullet_4th_boss_1_1[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_1_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_4th_boss_1_1[i][j].label.setForeground(Color.GREEN);
				bullet_4th_boss_1_1[i][j].speed = 3;
				bullet_4th_boss_1_1[i][j].label.setBounds(0, 0, 30, 30);
				bullet_4th_boss_1_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_1_1[i][j].label);
				bullet_4th_boss_1_1[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_1_2_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_1_2[i][j] = new Bullet_Radian(18.0f + 18.0f * i);
				bullet_4th_boss_1_2[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_1_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_boss_1_2[i][j].label.setForeground(Color.ORANGE);
				bullet_4th_boss_1_2[i][j].speed = 6;
				bullet_4th_boss_1_2[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_boss_1_2[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_1_2[i][j].label);
				bullet_4th_boss_1_2[i][j].label.setVisible(false);
			}
		}
		
		for(int j = 0; j < max_enemy_bullet; j++)
		{
			bullet_4th_boss_1_3[j] = new Bullet();
			bullet_4th_boss_1_3[j].label = new JLabel("¡Ü");
			bullet_4th_boss_1_3[j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_4th_boss_1_3[j].label.setForeground(Color.MAGENTA);
			bullet_4th_boss_1_3[j].move_y = 5;
			bullet_4th_boss_1_3[j].label.setBounds(0, 0, 30, 30);
			bullet_4th_boss_1_3[j].label.setLocation(1200, 0); // original position(not used)
			panel_game_screen.add(bullet_4th_boss_1_3[j].label);
			bullet_4th_boss_1_3[j].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_2_1_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_2_1[i][j] = new Bullet_Radian(7.5f * i);
				bullet_4th_boss_2_1[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_2_1[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_4th_boss_2_1[i][j].label.setForeground(Color.PINK);
				bullet_4th_boss_2_1[i][j].speed = 4;
				bullet_4th_boss_2_1[i][j].label.setBounds(0, 0, 30, 30);
				bullet_4th_boss_2_1[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_2_1[i][j].label);
				bullet_4th_boss_2_1[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_2_2_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_2_2[i][j] = new Bullet_Radian(2.5f + 7.5f * i);
				bullet_4th_boss_2_2[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_2_2[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_4th_boss_2_2[i][j].label.setForeground(Color.PINK);
				bullet_4th_boss_2_2[i][j].speed = 4;
				bullet_4th_boss_2_2[i][j].label.setBounds(0, 0, 30, 30);
				bullet_4th_boss_2_2[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_2_2[i][j].label);
				bullet_4th_boss_2_2[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_2_3_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_2_3[i][j] = new Bullet_Radian(5.0f + 7.5f * i);
				bullet_4th_boss_2_3[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_2_3[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				bullet_4th_boss_2_3[i][j].label.setForeground(Color.PINK);
				bullet_4th_boss_2_3[i][j].speed = 4;
				bullet_4th_boss_2_3[i][j].label.setBounds(0, 0, 30, 30);
				bullet_4th_boss_2_3[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_2_3[i][j].label);
				bullet_4th_boss_2_3[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_2_4_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_2_4[i][j] = new Bullet_Radian(20.0f * i);
				bullet_4th_boss_2_4[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_2_4[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_boss_2_4[i][j].label.setForeground(Color.getHSBColor(DARK_CYAN[0], DARK_CYAN[1], DARK_CYAN[2]));
				bullet_4th_boss_2_4[i][j].speed = 8;
				bullet_4th_boss_2_4[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_boss_2_4[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_2_4[i][j].label);
				bullet_4th_boss_2_4[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_2_5_direction; i++)
		{
			for(int j = 0; j < 1; j++)
			{
				bullet_4th_boss_2_5[i][j] = new Bullet_Radian(45.0f + 90.0f * i);
				bullet_4th_boss_2_5[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_2_5[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
				bullet_4th_boss_2_5[i][j].label.setForeground(Color.MAGENTA);
				bullet_4th_boss_2_5[i][j].speed = 3;
				bullet_4th_boss_2_5[i][j].label.setBounds(0, 0, 50, 50);
				bullet_4th_boss_2_5[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_2_5[i][j].label);
				bullet_4th_boss_2_5[i][j].label.setVisible(false);
			}
		}
		
		for(int i = 0; i < enemy_bullet_4th_boss_2_6_direction; i++)
		{
			for(int j = 0; j < max_enemy_bullet; j++)
			{
				bullet_4th_boss_2_6[i][j] = new Bullet_Radian(12.0f * i);
				bullet_4th_boss_2_6[i][j].label = new JLabel("¡Ü");
				bullet_4th_boss_2_6[i][j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				bullet_4th_boss_2_6[i][j].label.setForeground(Color.RED);
				bullet_4th_boss_2_6[i][j].speed = 7;
				bullet_4th_boss_2_6[i][j].label.setBounds(0, 0, 40, 40);
				bullet_4th_boss_2_6[i][j].label.setLocation(1200, 0); // original position(not used)
				panel_game_screen.add(bullet_4th_boss_2_6[i][j].label);
				bullet_4th_boss_2_6[i][j].label.setVisible(false);
			}
		}
		
		for(int j = 0; j < 200; j++)
		{
			bullet_4th_boss_2_7[j] = new Bullet();
			bullet_4th_boss_2_7[j].label = new JLabel("¡Ü");
			bullet_4th_boss_2_7[j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_4th_boss_2_7[j].label.setForeground(Color.getHSBColor(WHITE_GRAY[0], WHITE_GRAY[1], WHITE_GRAY[2]));
			bullet_4th_boss_2_7[j].move_x = 0;
			bullet_4th_boss_2_7[j].move_y = 5;
			bullet_4th_boss_2_7[j].label.setBounds(0, 0, 30, 30);
			bullet_4th_boss_2_7[j].label.setLocation(1200, 0); // original position(not used)
			panel_game_screen.add(bullet_4th_boss_2_7[j].label);
			bullet_4th_boss_2_7[j].label.setVisible(false);
			
			bullet_4th_boss_2_8[j] = new Bullet();
			bullet_4th_boss_2_8[j].label = new JLabel("¡Ü");
			bullet_4th_boss_2_8[j].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			bullet_4th_boss_2_8[j].label.setForeground(Color.getHSBColor(WHITE_GRAY[0], WHITE_GRAY[1], WHITE_GRAY[2]));
			bullet_4th_boss_2_8[j].move_x = 4;
			bullet_4th_boss_2_8[j].move_y = 0;
			bullet_4th_boss_2_8[j].label.setBounds(0, 0, 30, 30);
			bullet_4th_boss_2_8[j].label.setLocation(1200, 0); // original position(not used)
			panel_game_screen.add(bullet_4th_boss_2_8[j].label);
			bullet_4th_boss_2_8[j].label.setVisible(false);
		}
		
		// set enemy(4th stage)
		final int enemy_4_group_count = 5;
		final int enemy_8_group_count = 10;
		final int enemy_9_group_count = 20;
		final int enemy_10_group_count = 20;
		final int enemy_11_group_count = 20;
		final int enemy_12_left_group_count = 10;
		final int enemy_12_right_group_count = 10;
		final int enemy_13_group_count = 2;
		final int enemy_14_group_count = 2;
		final int intruder_1_group_count = 10;
		final int intruder_2_group_count = 10;
		Enemy[] enemy_4_group = new Enemy[enemy_4_group_count];
		Enemy[] enemy_8_group = new Enemy[enemy_8_group_count];
		Enemy[] enemy_9_group = new Enemy[enemy_9_group_count];
		Enemy[] enemy_10_group = new Enemy[enemy_10_group_count];
		Enemy[] enemy_11_group = new Enemy[enemy_11_group_count];
		Enemy[] enemy_12_left_group = new Enemy[enemy_12_left_group_count];
		Enemy[] enemy_12_right_group = new Enemy[enemy_12_right_group_count];
		Enemy[] enemy_13_group = new Enemy[enemy_13_group_count];
		Enemy[] enemy_14_group = new Enemy[enemy_14_group_count];
		Enemy enemy_4th_middle_boss = new Enemy();
		Enemy enemy_4th_stage_boss_1 = new Enemy();
		Enemy enemy_4th_stage_boss_2 = new Enemy();
		Enemy[] intruder_1_group = new Enemy[intruder_1_group_count];
		Enemy[] intruder_2_group = new Enemy[intruder_2_group_count];
		
		for(int i = 0; i < enemy_4_group_count; i++)
		{
			enemy_4_group[i] = new Enemy();
			enemy_4_group[i].total_hp = 60;
			enemy_4_group[i].hp = 60;
			enemy_4_group[i].posX = 0;
			enemy_4_group[i].posY = 0;
			enemy_4_group[i].width = 150;
			enemy_4_group[i].height = 95;
			enemy_4_group[i].attack_interval = 120;
			enemy_4_group[i].attack_frame = 0;
			enemy_4_group[i].survive = true;
			enemy_4_group[i].label = new JLabel(new ImageIcon("./image/enemy_4_picture.png"));
			panel_game_screen.add(enemy_4_group[i].label);
			enemy_4_group[i].label.setBounds(0, 0, 170, 100);
			enemy_4_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_8_group_count; i++)
		{
			enemy_8_group[i] = new Enemy();
			enemy_8_group[i].total_hp = 24;
			enemy_8_group[i].hp = 24;
			enemy_8_group[i].posX = 0;
			enemy_8_group[i].posY = 0;
			enemy_8_group[i].width = 125;
			enemy_8_group[i].height = 143;
			enemy_8_group[i].attack_interval = 120;
			enemy_8_group[i].attack_frame = 0;
			enemy_8_group[i].survive = true;
			enemy_8_group[i].label = new JLabel(new ImageIcon("./image/enemy_8_picture.png"));
			panel_game_screen.add(enemy_8_group[i].label);
			enemy_8_group[i].label.setBounds(0, 0, 150, 150);
			enemy_8_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_9_group_count; i++)
		{
			enemy_9_group[i] = new Enemy();
			enemy_9_group[i].total_hp = 100;
			enemy_9_group[i].hp = 100;
			enemy_9_group[i].posX = 0;
			enemy_9_group[i].posY = 0;
			enemy_9_group[i].width = 220;
			enemy_9_group[i].height = 185;
			enemy_9_group[i].attack_interval = 150;
			enemy_9_group[i].attack_frame = 0;
			enemy_9_group[i].survive = true;
			enemy_9_group[i].label = new JLabel(new ImageIcon("./image/enemy_9_picture.png"));
			panel_game_screen.add(enemy_9_group[i].label);
			enemy_9_group[i].label.setBounds(0, 0, 250, 230);
			enemy_9_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_10_group_count; i++)
		{
			enemy_10_group[i] = new Enemy();
			enemy_10_group[i].total_hp = 24;
			enemy_10_group[i].hp = 24;
			enemy_10_group[i].posX = 0;
			enemy_10_group[i].posY = 0;
			enemy_10_group[i].width = 130;
			enemy_10_group[i].height = 49;
			enemy_10_group[i].attack_interval = 200;
			enemy_10_group[i].attack_frame = 0;
			enemy_10_group[i].survive = true;
			enemy_10_group[i].label = new JLabel(new ImageIcon("./image/enemy_10_picture.png"));
			panel_game_screen.add(enemy_10_group[i].label);
			enemy_10_group[i].label.setBounds(0, 0, 150, 80);
			enemy_10_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_11_group_count; i++)
		{
			enemy_11_group[i] = new Enemy();
			enemy_11_group[i].total_hp = 18;
			enemy_11_group[i].hp = 18;
			enemy_11_group[i].posX = 0;
			enemy_11_group[i].posY = 0;
			enemy_11_group[i].width = 130;
			enemy_11_group[i].height = 97;
			enemy_11_group[i].attack_interval = 100;
			enemy_11_group[i].attack_frame = 0;
			enemy_11_group[i].survive = true;
			enemy_11_group[i].label = new JLabel(new ImageIcon("./image/enemy_11_picture.png"));
			panel_game_screen.add(enemy_11_group[i].label);
			enemy_11_group[i].label.setBounds(0, 0, 160, 110);
			enemy_11_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_12_left_group_count; i++)
		{
			enemy_12_left_group[i] = new Enemy();
			enemy_12_left_group[i].total_hp = 18;
			enemy_12_left_group[i].hp = 18;
			enemy_12_left_group[i].posX = 0;
			enemy_12_left_group[i].posY = 0;
			enemy_12_left_group[i].width = 170;
			enemy_12_left_group[i].height = 168;
			enemy_12_left_group[i].attack_interval = 80;
			enemy_12_left_group[i].attack_frame = 0;
			enemy_12_left_group[i].survive = true;
			enemy_12_left_group[i].label = new JLabel(new ImageIcon("./image/enemy_12_picture.png"));
			panel_game_screen.add(enemy_12_left_group[i].label);
			enemy_12_left_group[i].label.setBounds(0, 0, 180, 200);
			enemy_12_left_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_12_right_group_count; i++)
		{
			enemy_12_right_group[i] = new Enemy();
			enemy_12_right_group[i].total_hp = 18;
			enemy_12_right_group[i].hp = 18;
			enemy_12_right_group[i].posX = 0;
			enemy_12_right_group[i].posY = 0;
			enemy_12_right_group[i].width = 170;
			enemy_12_right_group[i].height = 168;
			enemy_12_right_group[i].attack_interval = 80;
			enemy_12_right_group[i].attack_frame = 0;
			enemy_12_right_group[i].survive = true;
			enemy_12_right_group[i].label = new JLabel(new ImageIcon("./image/enemy_12_picture.png"));
			panel_game_screen.add(enemy_12_right_group[i].label);
			enemy_12_right_group[i].label.setBounds(0, 0, 180, 200);
			enemy_12_right_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_13_group_count; i++)
		{
			enemy_13_group[i] = new Enemy();
			enemy_13_group[i].total_hp = 120;
			enemy_13_group[i].hp = 120;
			enemy_13_group[i].posX = 0;
			enemy_13_group[i].posY = 0;
			enemy_13_group[i].width = 125;
			enemy_13_group[i].height = 51;
			enemy_13_group[i].attack_interval = 100;
			enemy_13_group[i].attack_frame = 0;
			enemy_13_group[i].survive = true;
			enemy_13_group[i].label = new JLabel(new ImageIcon("./image/enemy_13_picture.png"));
			panel_game_screen.add(enemy_13_group[i].label);
			enemy_13_group[i].label.setBounds(0, 0, 140, 80);
			enemy_13_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < enemy_14_group_count; i++)
		{
			enemy_14_group[i] = new Enemy();
			enemy_14_group[i].total_hp = 60;
			enemy_14_group[i].hp = 60;
			enemy_14_group[i].posX = 0;
			enemy_14_group[i].posY = 0;
			enemy_14_group[i].width = 210;
			enemy_14_group[i].height = 118;
			enemy_14_group[i].attack_interval = 100;
			enemy_14_group[i].attack_frame = 0;
			enemy_14_group[i].survive = true;
			enemy_14_group[i].label = new JLabel(new ImageIcon("./image/enemy_14_picture.png"));
			panel_game_screen.add(enemy_14_group[i].label);
			enemy_14_group[i].label.setBounds(0, 0, 230, 150);
			enemy_14_group[i].label.setVisible(false);
		}
		
		enemy_4th_middle_boss.total_hp = 2000;
		enemy_4th_middle_boss.hp = 2000;
		//enemy_4th_middle_boss.hp = 2000;
		enemy_4th_middle_boss.posX = 0;
		enemy_4th_middle_boss.posY = 0;
		enemy_4th_middle_boss.width = 340;
		enemy_4th_middle_boss.height = 209;
		enemy_4th_middle_boss.attack_interval = 50;
		enemy_4th_middle_boss.attack_frame = 0;
		enemy_4th_middle_boss.survive = true;
		enemy_4th_middle_boss.label = new JLabel(new ImageIcon("./image/enemy_4th_middle_boss.png"));
		panel_game_screen.add(enemy_4th_middle_boss.label);
		enemy_4th_middle_boss.label.setBounds(0, 0, 360, 300);
		enemy_4th_middle_boss.label.setVisible(false);
		
		enemy_4th_stage_boss_1.total_hp = 2500;
		enemy_4th_stage_boss_1.hp = 2500;
		//enemy_4th_stage_boss_1.hp = 2500;
		enemy_4th_stage_boss_1.posX = 0;
		enemy_4th_stage_boss_1.posY = 0;
		enemy_4th_stage_boss_1.width = 235;
		enemy_4th_stage_boss_1.height = 185;
		enemy_4th_stage_boss_1.attack_interval = 80;
		enemy_4th_stage_boss_1.attack_frame = 0;
		enemy_4th_stage_boss_1.survive = true;
		enemy_4th_stage_boss_1.label = new JLabel(new ImageIcon("./image/enemy_4th_stage_boss_1.png"));
		panel_game_screen.add(enemy_4th_stage_boss_1.label);
		enemy_4th_stage_boss_1.label.setBounds(0, 0, 250, 200);
		enemy_4th_stage_boss_1.label.setVisible(false);
		
		enemy_4th_stage_boss_2.total_hp = 5000;
		//enemy_4th_stage_boss_2.hp = 5000;
		enemy_4th_stage_boss_2.hp = 5000;
		enemy_4th_stage_boss_2.posX = 0;
		enemy_4th_stage_boss_2.posY = 0;
		enemy_4th_stage_boss_2.width = 320;
		enemy_4th_stage_boss_2.height = 182;
		enemy_4th_stage_boss_2.attack_interval = 80;
		enemy_4th_stage_boss_2.attack_frame = 0;
		enemy_4th_stage_boss_2.survive = true;
		enemy_4th_stage_boss_2.label = new JLabel(new ImageIcon("./image/enemy_4th_stage_boss_2.png"));
		panel_game_screen.add(enemy_4th_stage_boss_2.label);
		enemy_4th_stage_boss_2.label.setBounds(0, 0, 340, 200);
		enemy_4th_stage_boss_2.label.setVisible(false);
		
		for(int i = 0; i < intruder_1_group_count; i++)
		{
			intruder_1_group[i] = new Enemy();
			intruder_1_group[i].total_hp = 0;
			intruder_1_group[i].hp = 99999999;
			intruder_1_group[i].posX = 0;
			intruder_1_group[i].posY = 0;
			intruder_1_group[i].width = 0;
			intruder_1_group[i].height = 0;
			intruder_1_group[i].attack_interval = 40;
			intruder_1_group[i].attack_frame = 0;
			intruder_1_group[i].survive = true;
			intruder_1_group[i].label = new JLabel("¡Ú");
			intruder_1_group[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
			panel_game_screen.add(intruder_1_group[i].label);
			intruder_1_group[i].label.setBounds(0, 0, 40, 40);
			intruder_1_group[i].label.setVisible(false);
		}
		
		for(int i = 0; i < intruder_2_group_count; i++)
		{
			intruder_2_group[i] = new Enemy();
			intruder_2_group[i].total_hp = 0;
			intruder_2_group[i].hp = 99999999;
			intruder_2_group[i].posX = 0;
			intruder_2_group[i].posY = 0;
			intruder_2_group[i].width = 0;
			intruder_2_group[i].height = 0;
			intruder_2_group[i].attack_interval = 40;
			intruder_2_group[i].attack_frame = 0;
			intruder_2_group[i].survive = true;
			intruder_2_group[i].label = new JLabel("¡Ú");
			intruder_2_group[i].label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
			panel_game_screen.add(intruder_2_group[i].label);
			intruder_2_group[i].label.setBounds(0, 0, 40, 40);
			intruder_2_group[i].label.setVisible(false);
		}
		
		Point power_generate_location = null;
		Point barrier_generate_location = null;
		
		drop_power = false;
		power_up_item.posY = 0;
		
		drop_barrier = false;
		barrier_item.posY = 0;
		
		Label_loading.setVisible(false);
		
		// 4th stage start
		//
		//
		//
		// ==================================================================
		
		frame_count_start = System.currentTimeMillis();
		frame_count_current = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 3000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			set_player_color_by_shield_barrier();
		}
		
		// ==================================================================
		// enemy occur
		
		enemy_10_group[0].label.setLocation(100, -50);
		enemy_10_group[1].label.setLocation(150, -100);
		enemy_10_group[2].label.setLocation(200, -150);
		enemy_10_group[3].label.setLocation(700, -150);
		enemy_10_group[4].label.setLocation(750, -100);
		enemy_10_group[5].label.setLocation(800, -50);
		
		enemy_10_group[0].attack_interval = 200;
		enemy_10_group[1].attack_interval = 230;		
		enemy_10_group[2].attack_interval = 260;		
		enemy_10_group[3].attack_interval = 260;
		enemy_10_group[4].attack_interval = 230;
		enemy_10_group[5].attack_interval = 200;
		
		enemy_10_group[6].label.setLocation(250, -200);
		enemy_10_group[7].label.setLocation(400, -200);
		enemy_10_group[8].label.setLocation(550, -200);
		enemy_10_group[9].label.setLocation(700, -200);
		enemy_10_group[10].label.setLocation(850, -200);
		
		enemy_10_group[6].attack_interval = 200;
		enemy_10_group[7].attack_interval = 230;		
		enemy_10_group[8].attack_interval = 260;		
		enemy_10_group[9].attack_interval = 230;
		enemy_10_group[10].attack_interval = 200;
		
		enemy_10_group[11].label.setLocation(100, -300);
		enemy_10_group[12].label.setLocation(250, -300);
		enemy_10_group[13].label.setLocation(400, -300);
		enemy_10_group[14].label.setLocation(550, -300);
		enemy_10_group[15].label.setLocation(700, -300);
		enemy_10_group[16].label.setLocation(850, -300);
		
		enemy_10_group[11].attack_interval = 200;
		enemy_10_group[12].attack_interval = 230;		
		enemy_10_group[13].attack_interval = 260;		
		enemy_10_group[14].attack_interval = 260;
		enemy_10_group[15].attack_interval = 200;
		enemy_10_group[16].attack_interval = 200;
		
		enemy_11_group[0].label.setLocation(200, -450);
		enemy_11_group[1].label.setLocation(600, -450);
		
		int start_index10 = 0;
		int end_index10 = 17;
		
		int start_index11 = 0;
		int end_index11 = 2;
		boolean enemy_11_attack_pattern = true;
		
		int frame_enemy_move_downward = 0;
		
		for(int i = 0; i < end_index10; i++)
		{
			enemy_10_group[i].label.setVisible(true);
		}
		
		for(int i = 0; i < end_index11; i++)
		{
			enemy_11_group[i].label.setVisible(true);
		}
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 30000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move_downward >= 20)
			{
				for(int i = start_index10; i < end_index10; i++)
				{
					if(enemy_10_group[i].label.getY() >= 0)
					{
						enemy_10_group[i].move(0,  5);
					}
					else
					{
						Point p1 = enemy_10_group[i].label.getLocation();
						enemy_10_group[i].label.setLocation(p1.x, p1.y + 5);
					}
					
					if(enemy_10_group[i].label.getY() >= 600)
					{
						enemy_10_group[i].survive = false;
						enemy_10_group[i].label.setLocation(0, 0);
						enemy_10_group[i].label.setVisible(false);
					}
				}
				
				for(int i = start_index11; i < end_index11; i++)
				{
					if(enemy_11_group[i].label.getY() >= 0)
					{
						enemy_11_group[i].move(0,  5);
					}
					else
					{
						Point p1 = enemy_11_group[i].label.getLocation();
						enemy_11_group[i].label.setLocation(p1.x, p1.y + 5);
					}
					
					if(enemy_11_group[i].label.getY() >= 600)
					{
						enemy_11_group[i].survive = false;
						enemy_11_group[i].label.setLocation(0, 0);
						enemy_11_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			// enemy attack
			for(int i = start_index10; i < end_index10; i++)
			{
				if(enemy_10_group[i].attack())
				{
					for(int j = 0; j < enemy_bullet_10_direction; j++)
					{
						attack_enemy(enemy_10_group[i], enemy_bullet_10[j], 57, 45);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_10_group[i].attack_interval = 200 + 20 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_10_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index11; i < end_index11; i++)
			{
				if(enemy_11_group[i].attack())
				{
					if(enemy_11_attack_pattern == true)
					{
						for(int j = 0; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = false;
					}
					else
					{
						for(int j = 1; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = true;
					}
				}
				else
				{
					enemy_11_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move
			for(int i = 0; i < enemy_bullet_10_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_10[i]);
			}
			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_11[i]);
			}
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_10_group, start_index10, end_index10);
			check_enemy_damaged_package(enemy_11_group, start_index11, end_index11);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_10_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_10[i]);
			}
			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_11[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 1st event
		
		// ==================================================================
		// enemy occur
		
		start_index10 = end_index10;
		start_index11 = end_index11;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_10, enemy_bullet_10_direction);
		erase_enemy_bullet_package(enemy_bullet_11, enemy_bullet_11_direction);
		
		enemy_9_group[0].label.setLocation(100, -50);
		enemy_9_group[1].label.setLocation(300, -200);
		enemy_9_group[2].label.setLocation(500, -350);
		enemy_9_group[3].label.setLocation(700, -500);
		enemy_9_group[4].label.setLocation(900, -650);
		
		int start_index9 = 0;
		int end_index9 = 5;
		int enemy_9_attack_pattern = 0;
		
		frame_enemy_move_downward = 0;
		
		for(int i = 0; i < end_index9; i++)
		{
			enemy_9_group[i].label.setVisible(true);
		}
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 60000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			power_generate_location = enemy_9_group[2].label.getLocation();
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move_downward >= 20)
			{
				for(int i = start_index9; i < end_index9; i++)
				{
					if(enemy_9_group[i].label.getY() >= 0)
					{
						enemy_9_group[i].move(0,  5);
					}
					else
					{
						Point p1 = enemy_9_group[i].label.getLocation();
						enemy_9_group[i].label.setLocation(p1.x, p1.y + 5);
					}
					
					if(enemy_9_group[i].label.getY() >= 600)
					{
						enemy_9_group[i].survive = false;
						enemy_9_group[i].label.setLocation(0, 0);
						enemy_9_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			// enemy attack
			for(int i = start_index9; i < end_index9; i++)
			{
				if(enemy_9_group[i].attack())
				{
					enemy_9_attack_pattern++;
					if(enemy_9_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < 5; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_2[j], 102, 89);
						}
					}
					else
					{
						for(int j = 0; j < 7; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 36, 86);
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 163, 86);
						}
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_9_group[i].attack_interval = 100 + 20 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_9_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move
			for(int i = 0; i < enemy_bullet_9_1_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_9_2_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_9_2[i]);
			}
			
			// power generate
			if(enemy_9_group[2].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = 600;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_9_group, start_index9, end_index9);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_9_1_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_9_2_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_9_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 2nd event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy disappear		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].survive = false;
			enemy_9_group[i].label.setVisible(false);
		}
		
		start_index9 = end_index9;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_9_1, enemy_bullet_9_1_direction);
		erase_enemy_bullet_package(enemy_bullet_9_2, enemy_bullet_9_2_direction);
		
		// enemy occur
		enemy_11_group[2].label.setLocation(1300, 400);
		enemy_11_group[3].label.setLocation(1200, 200);
		enemy_11_group[4].label.setLocation(200, -100);
		enemy_11_group[5].label.setLocation(500, -300);
		
		end_index11 = 6;
		
		frame_enemy_move_downward = 0;
		
		for(int i = start_index11; i < end_index11; i++)
		{
			enemy_11_group[i].label.setVisible(true);
		}
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 90000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move_downward >= 20)
			{
				for(int i = start_index11; i < 4; i++)
				{
					if(enemy_11_group[i].label.getX() >= 1170)
					{
						enemy_11_group[i].move(-5,  0);
					}
					else
					{
						Point p1 = enemy_11_group[i].label.getLocation();
						enemy_11_group[i].label.setLocation(p1.x - 5, p1.y);
					}
					
					if(enemy_11_group[i].label.getY() <= 100)
					{
						enemy_11_group[i].survive = false;
						enemy_11_group[i].label.setLocation(0, 0);
						enemy_11_group[i].label.setVisible(false);
					}
				}
		
				for(int i = 4; i < end_index11; i++)
				{
					if(enemy_11_group[i].label.getY() >= 0)
					{
						enemy_11_group[i].move(0,  5);
					}
					else
					{
						Point p1 = enemy_11_group[i].label.getLocation();
						enemy_11_group[i].label.setLocation(p1.x, p1.y + 5);
					}
					
					if(enemy_11_group[i].label.getY() >= 600)
					{
						enemy_11_group[i].survive = false;
						enemy_11_group[i].label.setLocation(0, 0);
						enemy_11_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			// enemy attack			
			for(int i = start_index11; i < end_index11; i++)
			{
				if(enemy_11_group[i].attack())
				{
					if(enemy_11_attack_pattern == true)
					{
						for(int j = 0; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = false;
					}
					else
					{
						for(int j = 1; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = true;
					}
				}
				else
				{
					enemy_11_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_11[i]);
			}
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_11_group, start_index11, end_index11);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_11[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 3rd event
		
		// ==================================================================
		// enemy disappear		
		for(int i = start_index11; i < end_index11; i++)
		{
			enemy_11_group[i].survive = false;
			enemy_11_group[i].label.setVisible(false);
		}
		
		start_index11 = end_index11;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_11, enemy_bullet_11_direction);
		erase_enemy_bullet_package(enemy_bullet_11, enemy_bullet_11_direction);
		
		// enemy occur		
		enemy_12_left_group[0].label.setLocation(-100, 50);
		enemy_12_left_group[1].label.setLocation(-100, 350);
		enemy_12_right_group[0].label.setLocation(1400, 50);
		enemy_12_right_group[1].label.setLocation(1400, 350);
		
		int start_index12_left = 0;
		int end_index12_left = 2;
		
		int start_index12_right = 0;
		int end_index12_right = 2;
		
		int frame_enemy_move = 0;
		int enemy_12_attack_pattern = 0;
		
		for(int i = 0; i < end_index12_left; i++)
		{
			enemy_12_left_group[i].label.setVisible(true);
		}
		
		for(int i = 0; i < end_index12_right; i++)
		{
			enemy_12_right_group[i].label.setVisible(true);
		}
		
		power_generate_location = enemy_12_right_group[0].label.getLocation();
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		//while(frame_count_current - frame_count_start < 10000)
		while(frame_count_current - frame_count_start < 120000)
		{	
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move
			if(frame_enemy_move >= 20)
			{
				for(int i = 0; i < end_index12_left; i++)
				{
					if(enemy_12_left_group[i].label.getX() >= 0)
					{
						enemy_12_left_group[i].move(5,  0);
					}
					else
					{
						Point p1 = enemy_12_left_group[i].label.getLocation();
						enemy_12_left_group[i].label.setLocation(p1.x + 5, p1.y);
					}
				}
				
				for(int i = 0; i < end_index12_right; i++)
				{
					if(enemy_12_right_group[i].label.getX() >= 1170)
					{
						enemy_12_right_group[i].move(-5,  0);
					}
					else
					{
						Point p1 = enemy_12_right_group[i].label.getLocation();
						enemy_12_right_group[i].label.setLocation(p1.x - 5, p1.y);
					}
				}
				frame_enemy_move = 0;
			}
			else
			{
				frame_enemy_move++;
			}
			
			// enemy attack			
			for(int i = 0; i < end_index12_left; i++)
			{
				if(enemy_12_left_group[i].attack())
				{
					if(enemy_12_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < enemy_bullet_12_left_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_left[j], 77, 84);
						}
					}
					else if(enemy_12_attack_pattern % 3 == 1)
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_down[j], 77, 84);
						}
					}
					else
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_right[j], 77, 84);
						}
					}
				}
				else
				{
					enemy_12_left_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < end_index12_right; i++)
			{
				if(enemy_12_right_group[i].attack())
				{
					if(enemy_12_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < enemy_bullet_12_right_direction; j++)
						{
							attack_enemy(enemy_12_right_group[i], enemy_bullet_12_right[j], 77, 84);
						}	
					}
					else if(enemy_12_attack_pattern % 3 == 1)
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_down[j], 77, 84);
						}
					}
					else
					{
						for(int j = 0; j < enemy_bullet_12_left_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_left[j], 77, 84);
						}
					}
					enemy_12_attack_pattern++;
				}
				else
				{
					enemy_12_right_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move			
			for(int i = 0; i < enemy_bullet_12_left_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_left[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_right_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_right[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_down_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_down[i]);
			}
			
			// power generate
			if(enemy_12_right_group[0].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 77;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			power_generate_location = enemy_12_right_group[0].label.getLocation();
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_12_left_group, start_index12_left, end_index12_left);
			check_enemy_damaged_package(enemy_12_right_group, start_index12_right, end_index12_right);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_12_left_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_left[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_right_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_right[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_down_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_down[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 4th event
		
		// enemy occur
		enemy_11_group[6].label.setLocation(100, -100);
		enemy_11_group[7].label.setLocation(300, -100);
		enemy_11_group[8].label.setLocation(500, -100);
		enemy_11_group[9].label.setLocation(700, -100);
		enemy_11_group[10].label.setLocation(900, -100);
		
		end_index11 = 11;
		frame_enemy_move = 0;
		enemy_11_attack_pattern = true;
		
		// test code 
		//int start_index11 = 5;
		//int end_index11 = 11;
		//boolean enemy_11_attack_pattern = true;
		
		for(int i = start_index11; i < end_index11; i++)
		{
			enemy_11_group[i].label.setVisible(true);
		}
		
		barrier_generate_location = enemy_11_group[9].label.getLocation();
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		//while(frame_count_current - frame_count_start < 23000)
		while(frame_count_current - frame_count_start < 150000)
		{	
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move
			if(frame_enemy_move >= 20)
			{
				for(int i = 0; i < end_index12_left; i++)
				{
					if(enemy_12_left_group[i].label.getX() >= 0)
					{
						enemy_12_left_group[i].move(5,  0);
					}
					else
					{
						Point p1 = enemy_12_left_group[i].label.getLocation();
						enemy_12_left_group[i].label.setLocation(p1.x + 5, p1.y);
					}
				}
				
				for(int i = 0; i < end_index12_right; i++)
				{
					if(enemy_12_right_group[i].label.getX() >= 1170)
					{
						enemy_12_right_group[i].move(-5,  0);
					}
					else
					{
						Point p1 = enemy_12_right_group[i].label.getLocation();
						enemy_12_right_group[i].label.setLocation(p1.x - 5, p1.y);
					}
				}
				
				for(int i = start_index11; i < end_index11; i++)
				{
					if(enemy_11_group[i].label.getY() >= 0)
					{
						enemy_11_group[i].move(0,  10);
					}
					else
					{
						Point p1 = enemy_11_group[i].label.getLocation();
						enemy_11_group[i].label.setLocation(p1.x, p1.y + 10);
					}
					
					if(enemy_11_group[i].label.getY() >= 600)
					{
						enemy_11_group[i].survive = false;
						enemy_11_group[i].label.setLocation(0, 0);
						enemy_11_group[i].label.setVisible(false);
					}
				}				
				frame_enemy_move = 0;
			}
			else
			{
				frame_enemy_move++;
			}
			
			// enemy attack			
			for(int i = 0; i < end_index12_left; i++)
			{
				if(enemy_12_left_group[i].attack())
				{
					if(enemy_12_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < enemy_bullet_12_left_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_left[j], 77, 84);
						}
					}
					else if(enemy_12_attack_pattern % 3 == 1)
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_down[j], 77, 84);
						}
					}
					else
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_right[j], 77, 84);
						}
					}
				}
				else
				{
					enemy_12_left_group[i].attack_frame++;
				}
			}
			
			for(int i = 0; i < end_index12_right; i++)
			{
				if(enemy_12_right_group[i].attack())
				{
					if(enemy_12_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < enemy_bullet_12_right_direction; j++)
						{
							attack_enemy(enemy_12_right_group[i], enemy_bullet_12_right[j], 77, 84);
						}	
					}
					else if(enemy_12_attack_pattern % 3 == 1)
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_down[j], 77, 84);
						}
					}
					else
					{
						for(int j = 0; j < enemy_bullet_12_left_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_left[j], 77, 84);
						}
					}
					enemy_12_attack_pattern++;
				}
				else
				{
					enemy_12_right_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index11; i < end_index11; i++)
			{
				if(enemy_11_group[i].attack())
				{
					if(enemy_11_attack_pattern == true)
					{
						for(int j = 0; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = false;
					}
					else
					{
						for(int j = 1; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = true;
					}
				}
				else
				{
					enemy_11_group[i].attack_frame++;
				}
			}
			
			
			// enemy bullet move	
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_11[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_left_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_left[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_right_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_right[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_down_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_down[i]);
			}
			
			// barrier item generate
			if(enemy_11_group[9].survive == false && barrier_item.posY == 0 && drop_barrier == false)
			{
				barrier_item.posX = barrier_generate_location.x + 57;
				barrier_item.posY = barrier_generate_location.y;
				barrier_item.label.setLocation(barrier_item.posX, barrier_item.posY);
				barrier_item.label.setVisible(true);
				drop_barrier = true;
			}
			
			if(drop_barrier == true)
			{
				barrier_item_moving();
				check_player_get_barrier();
			}
			
			barrier_generate_location = enemy_11_group[9].label.getLocation();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_11_group, start_index11, end_index11);
			check_enemy_damaged_package(enemy_12_left_group, start_index12_left, end_index12_left);
			check_enemy_damaged_package(enemy_12_right_group, start_index12_right, end_index12_right);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_11[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_left_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_left[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_right_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_right[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_down_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_down[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 5th event
		
		// ========================================================================
		// rest time
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 2000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// enemy disappear		
		for(int i = start_index11; i < end_index11; i++)
		{
			enemy_11_group[i].survive = false;
			enemy_11_group[i].label.setVisible(false);
		}
		
		start_index11 = end_index11;
		
		for(int i = start_index12_left; i < end_index12_left; i++)
		{
			enemy_12_left_group[i].survive = false;
			enemy_12_left_group[i].label.setVisible(false);
		}
		
		start_index12_left = end_index12_left;
		
		for(int i = start_index12_right; i < end_index12_right; i++)
		{
			enemy_12_right_group[i].survive = false;
			enemy_12_right_group[i].label.setVisible(false);
		}
		
		start_index12_right = end_index12_right;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_11, enemy_bullet_11_direction);
		erase_enemy_bullet_package(enemy_bullet_12_left, enemy_bullet_12_left_direction);
		erase_enemy_bullet_package(enemy_bullet_12_right, enemy_bullet_12_right_direction);
		
		// disappear player bullet
		for(int i = 0; i < max_player_bullet; i++)
		{
			bullet_player[i].label.setLocation(0, 0);
			bullet_player[i].label.setVisible(false);
			bullet_player_2[i].label.setLocation(0, 0);
			bullet_player_2[i].label.setVisible(false);
		}
		
		// ========================================================================
		
		frame_count_start = System.currentTimeMillis();
		frame_count_current = System.currentTimeMillis();
		
		// 4th stage middle boss occur
		enemy_4th_middle_boss.label.setLocation(400, 30);
		enemy_4th_middle_boss.survive = true;
		enemy_4th_middle_boss.label.setVisible(true);
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_4th_middle_boss.hp));
		Label_hp.setVisible(true);
		
		enemy_attack_frame_count = 0;
		
		int enemy_4th_middle_boss_attack_pattern = 0;
		int middle_boss_attack_pattern_count = 0;
		int frame_move_pattern = 0;
		Random random = new Random();
		int move_pattern = -1;
		
		power_generate_location = enemy_4th_middle_boss.label.getLocation();
		
		while(enemy_4th_middle_boss.survive == true && frame_count_current - frame_count_start < 90000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			frame_move_pattern++;
			// set enemy moving pattern
			if(frame_move_pattern >= 50)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					if(enemy_4th_middle_boss.label.getX() < 600)
					{
						enemy_4th_middle_boss.move(30, 0);
					}
					else
					{
						enemy_4th_middle_boss.move(-30, 0);
					}
					break;
				default:
					if(enemy_4th_middle_boss.label.getX() > 300)
					{
						enemy_4th_middle_boss.move(-30, 0);
					}
					else
					{
						enemy_4th_middle_boss.move(30, 0);
					}
					break;
				}
				frame_move_pattern = 0;
			}
			
			power_generate_location = enemy_4th_middle_boss.label.getLocation();
			
			if(enemy_4th_middle_boss.attack())
			{
				if(enemy_4th_middle_boss_attack_pattern % 5 == 0)
				{
					if(middle_boss_attack_pattern_count % 2 == 0)
					{
						for(int i = 0; i < enemy_bullet_4th_middle_boss_1_direction; i += 2)
						{
							attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_1[i], 57, 107);
						}
					}
					else
					{
						for(int i = 1; i < enemy_bullet_4th_middle_boss_1_direction; i += 2)
						{
							attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_2[i], 57, 107);
						}
					}
					middle_boss_attack_pattern_count++;
				} // end of if(enemy_4th_middle_boss_attack_pattern % 5 == 0)
				else if(enemy_4th_middle_boss_attack_pattern % 5 == 1)
				{
					if(middle_boss_attack_pattern_count % 2 == 0)
					{
						for(int i = 0; i < enemy_bullet_4th_middle_boss_2_direction; i += 2)
						{
							attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_2[i], 273, 107);
						}
					}
					else
					{
						for(int i = 1; i < enemy_bullet_4th_middle_boss_2_direction; i += 2)
						{
							attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_1[i], 273, 107);
						}
					}
					middle_boss_attack_pattern_count++;
				} // end of else if(enemy_4th_middle_boss_attack_pattern % 5 == 1)
				else if(enemy_4th_middle_boss_attack_pattern % 5 == 2)
				{
					for(int i = 0; i < enemy_bullet_4th_middle_boss_3_direction; i++)
					{
						attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_3[i], 57, 107);
					}
					
					for(int i = 0; i < enemy_bullet_4th_middle_boss_4_direction; i++)
					{
						attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_4[i], 273, 107);
					}
				} // end of else if(enemy_4th_middle_boss_attack_pattern % 5 == 2)
				else if(enemy_4th_middle_boss_attack_pattern % 5 == 3)
				{
					for(int i = 0; i < enemy_bullet_4th_middle_boss_5_direction; i++)
					{
						attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_5[i], 57, 107);
					}
					
					for(int i = 0; i < enemy_bullet_4th_middle_boss_5_direction; i++)
					{
						attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_5[i], 273, 107);
					}
				} // end of else if(enemy_4th_middle_boss_attack_pattern % 5 == 3)
				else
				{
					for(int i = 0; i < enemy_bullet_4th_middle_boss_6_direction; i++)
					{
						attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_6[i], 57, 107);
					}
					
					for(int i = 0; i < enemy_bullet_4th_middle_boss_6_direction; i++)
					{
						attack_enemy(enemy_4th_middle_boss, bullet_4th_middle_boss_6[i], 273, 107);
					}
				}
				enemy_4th_middle_boss_attack_pattern++;
			}
			else
			{
				enemy_4th_middle_boss.attack_frame++;
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_1_direction; i++)
			{
				enemy_bullet_move(bullet_4th_middle_boss_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_2_direction; i++)
			{
				enemy_bullet_move(bullet_4th_middle_boss_2[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_3_direction; i++)
			{
				enemy_bullet_move(bullet_4th_middle_boss_3[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_4_direction; i++)
			{
				enemy_bullet_move(bullet_4th_middle_boss_4[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_5_direction; i++)
			{
				enemy_bullet_move(bullet_4th_middle_boss_5[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_6_direction; i++)
			{
				enemy_bullet_move(bullet_4th_middle_boss_6[i]);
			}
			
			check_enemy_damaged_package(enemy_4th_middle_boss);
			
			Label_hp.setText(Integer.toString(enemy_4th_middle_boss.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_1_direction; i++)
			{
				check_player_damaged_package(bullet_4th_middle_boss_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_2_direction; i++)
			{
				check_player_damaged_package(bullet_4th_middle_boss_2[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_3_direction; i++)
			{
				check_player_damaged_package(bullet_4th_middle_boss_3[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_4_direction; i++)
			{
				check_player_damaged_package(bullet_4th_middle_boss_4[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_5_direction; i++)
			{
				check_player_damaged_package(bullet_4th_middle_boss_5[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_middle_boss_6_direction; i++)
			{
				check_player_damaged_package(bullet_4th_middle_boss_6[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
			
		} // end of 4th middle boss occur
		
		// ==================================================================	
		// enemy disapear
		
		Label_text_hp.setVisible(false);
		Label_hp.setVisible(false);
		
		erase_enemy_bullet_package(bullet_4th_middle_boss_1, enemy_bullet_4th_middle_boss_1_direction);
		erase_enemy_bullet_package(bullet_4th_middle_boss_2, enemy_bullet_4th_middle_boss_2_direction);
		erase_enemy_bullet_package(bullet_4th_middle_boss_3, enemy_bullet_4th_middle_boss_3_direction);
		erase_enemy_bullet_package(bullet_4th_middle_boss_4, enemy_bullet_4th_middle_boss_4_direction);
		erase_enemy_bullet_package(bullet_4th_middle_boss_5, enemy_bullet_4th_middle_boss_5_direction);
		erase_enemy_bullet_package(bullet_4th_middle_boss_6, enemy_bullet_4th_middle_boss_6_direction);
		
		// power generate		
		if(enemy_4th_middle_boss.survive == false)
		{
			power_up_item.posX = power_generate_location.x + 165;
			power_up_item.posY = power_generate_location.y;
			power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
			power_up_item.label.setVisible(true);
			drop_power = true;
		}
		else
		{
			enemy_4th_middle_boss.survive = false;
		}
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		// rest time
		while(frame_count_current - frame_count_start < 3000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy occur
		enemy_8_group[0].label.setLocation(200, -50);
		enemy_8_group[1].label.setLocation(500, -50);
		enemy_8_group[2].label.setLocation(800, -50);
		
		int start_index8 = 0;
		int end_index8 = 3;
		
		enemy_9_group[5].label.setLocation(300, -200);
		enemy_9_group[6].label.setLocation(700, -200);
		
		end_index9 = 7;
		enemy_9_attack_pattern = 0;
		// test code
		//int start_index9 = 5;
		//int end_index9 = 7;
		//int enemy_9_attack_pattern = 0;
		
		frame_enemy_move_downward = 0;
		//int frame_enemy_move_downward = 0;
		
		for(int i = start_index8; i < end_index8; i++)
		{
			enemy_8_group[i].label.setVisible(true);
		}
		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].label.setVisible(true);
		}
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 15000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move_downward >= 20)
			{
				for(int i = start_index8; i < end_index8; i++)
				{
					if(enemy_8_group[i].label.getY() >= 0)
					{
						enemy_8_group[i].move(0,  6);
					}
					else
					{
						Point p1 = enemy_8_group[i].label.getLocation();
						enemy_8_group[i].label.setLocation(p1.x, p1.y + 6);
					}
					
					if(enemy_8_group[i].label.getY() >= 600)
					{
						enemy_8_group[i].survive = false;
						enemy_8_group[i].label.setLocation(0, 0);
						enemy_8_group[i].label.setVisible(false);
					}
				}
		
				for(int i = start_index9; i < end_index9; i++)
				{
					if(enemy_9_group[i].label.getY() >= 0)
					{
						enemy_9_group[i].move(0,  6);
					}
					else
					{
						Point p1 = enemy_9_group[i].label.getLocation();
						enemy_9_group[i].label.setLocation(p1.x, p1.y + 6);
					}
					
					if(enemy_9_group[i].label.getY() >= 600)
					{
						enemy_9_group[i].survive = false;
						enemy_9_group[i].label.setLocation(0, 0);
						enemy_9_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move_downward = 0;
			}
			else
			{
				frame_enemy_move_downward++;
			}
			
			// enemy attack			
			for(int i = start_index8; i < end_index8; i++)
			{
				if(enemy_8_group[i].attack())
				{
					for(int j = 0; j < 3; j++)
					{
						attack_enemy(enemy_8_group[i], enemy_bullet_8[j], 59, 143);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_8_group[i].attack_interval = 170 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_8_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index9; i < end_index9; i++)
			{
				if(enemy_9_group[i].attack())
				{
					enemy_9_attack_pattern++;
					if(enemy_9_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < 5; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_2[j], 102, 89);
						}
					}
					else
					{
						for(int j = 0; j < 7; j++)
						{
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 36, 86);
							attack_enemy(enemy_9_group[i], enemy_bullet_9_1[j], 163, 86);
						}
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_9_group[i].attack_interval = 100 + 20 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_9_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move			
			for(int i = 0; i < enemy_bullet_8_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < enemy_bullet_9_1_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_9_2_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_9_2[i]);
			}
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_8_group, start_index8, end_index8);
			check_enemy_damaged_package(enemy_9_group, start_index9, end_index9);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_8_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < enemy_bullet_9_1_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_9_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_9_2_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_9_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 5th event
		
		// ==================================================================
		// enemy disappear		
		for(int i = start_index8; i < end_index8; i++)
		{
			enemy_8_group[i].survive = false;
			enemy_8_group[i].label.setVisible(false);
		}
		
		start_index8 = end_index8;
		
		for(int i = start_index9; i < end_index9; i++)
		{
			enemy_9_group[i].survive = false;
			enemy_9_group[i].label.setVisible(false);
		}
		
		start_index9 = end_index9;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_8, enemy_bullet_8_direction);
		erase_enemy_bullet_package(enemy_bullet_9_1, enemy_bullet_9_1_direction);
		erase_enemy_bullet_package(enemy_bullet_9_2, enemy_bullet_9_2_direction);
		
		// enemy occur	
		enemy_4_group[0].label.setLocation(200, 100);
		enemy_4_group[1].label.setLocation(800, 100);
		
		int start_index4 = 0;
		int end_index4 = 2;
		
		enemy_8_group[3].label.setLocation(-200, 300);
		enemy_8_group[4].label.setLocation(1300, 300);
		end_index8 = 5;
		
		// test code
		//int start_index10 = 17;
		//int end_index10 = 20;
		//int frame_enemy_move = 0;
		
		start_index10 = 17;
		end_index10 = 20;
		frame_enemy_move = 0;
		
		enemy_10_group[17].label.setLocation(-100, -150);
		enemy_10_group[18].label.setLocation(1100, -150);
		enemy_10_group[19].label.setLocation(500, -50);
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		for(int i = start_index4; i < end_index4; i++)
		{
			enemy_4_group[i].label.setVisible(true);
		}
		
		for(int i = start_index8; i < end_index8; i++)
		{
			enemy_8_group[i].label.setVisible(true);
		}
		
		for(int i = start_index10; i < end_index10; i++)
		{
			enemy_10_group[i].label.setVisible(true);
		}
		
		power_generate_location = enemy_10_group[18].label.getLocation();
		
		while(frame_count_current - frame_count_start < 30000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move >= 20)
			{
				for(int i = start_index4; i < end_index4; i++)
				{
					if(enemy_4_group[i].label.getY() >= 0)
					{
						enemy_4_group[i].move(0,  6);
					}
					else
					{
						Point p1 = enemy_4_group[i].label.getLocation();
						enemy_4_group[i].label.setLocation(p1.x, p1.y + 6);
					}
					
					if(enemy_4_group[i].label.getY() >= 600)
					{
						enemy_4_group[i].survive = false;
						enemy_4_group[i].label.setLocation(0, 0);
						enemy_4_group[i].label.setVisible(false);
					}
				}
		
				for(int i = start_index10; i < end_index10; i++)
				{
					if(enemy_10_group[i].label.getY() >= 0)
					{
						enemy_10_group[i].move(0,  6);
					}
					else
					{
						Point p1 = enemy_10_group[i].label.getLocation();
						enemy_10_group[i].label.setLocation(p1.x, p1.y + 6);
					}
					
					if(enemy_10_group[i].label.getY() >= 600)
					{
						enemy_10_group[i].survive = false;
						enemy_10_group[i].label.setLocation(0, 0);
						enemy_10_group[i].label.setVisible(false);
					}
				}
			}
			else
			{
				frame_enemy_move++;
			}
			
			// enemy move side
			if(frame_enemy_move >= 20)
			{
				if(enemy_8_group[3].label.getX() >= 0)
				{
					enemy_8_group[3].move(5,  0);
				}
				else
				{
					Point p1 = enemy_8_group[3].label.getLocation();
					enemy_8_group[3].label.setLocation(p1.x + 5, p1.y);
				}
				
				if(enemy_8_group[4].label.getX() <= 1170)
				{
					enemy_8_group[4].move(-5,  0);
				}
				else
				{
					Point p1 = enemy_8_group[4].label.getLocation();
					enemy_8_group[4].label.setLocation(p1.x - 5, p1.y);
				}
				
				frame_enemy_move = 0;
			}
			else
			{
				frame_enemy_move++;
			}
			
			// enemy attack			
			for(int i = start_index4; i < end_index4; i++)
			{
				if(enemy_4_group[i].attack())
				{
					attack_enemy(enemy_4_group[i], enemy_bullet_4[0], 20, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[1], 40, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[2], 60, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[3], 80, 50);
					attack_enemy(enemy_4_group[i], enemy_bullet_4[4], 100, 50);
				}
				else
				{
					enemy_4_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index8; i < end_index8; i++)
			{
				if(enemy_8_group[i].attack())
				{
					for(int j = 0; j < 3; j++)
					{
						attack_enemy(enemy_8_group[i], enemy_bullet_8[j], 59, 143);
					}	
					Random random_attack_enemy_interval = new Random();
					enemy_8_group[i].attack_interval = 170 + 10 * random_attack_enemy_interval.nextInt(5);					
				}
				else
				{
					enemy_8_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index10; i < end_index10; i++)
			{
				if(enemy_10_group[i].attack())
				{
					for(int j = 0; j < enemy_bullet_10_direction; j++)
					{
						attack_enemy(enemy_10_group[i], enemy_bullet_10[j], 57, 45);
					}	
				}
				else
				{
					enemy_10_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move			
			for(int i = 0; i < enemy_bullet_4_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_4[i]);
			}
			
			for(int i = 0; i < enemy_bullet_8_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < enemy_bullet_10_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_10[i]);
			}
			
			// power generate		
			if(enemy_10_group[18].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 59;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			power_generate_location = enemy_10_group[18].label.getLocation();
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_4_group, start_index4, end_index4);
			check_enemy_damaged_package(enemy_8_group, start_index8, end_index8);
			check_enemy_damaged_package(enemy_10_group, start_index10, end_index10);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_4_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_4[i]);
			}
			
			for(int i = 0; i < enemy_bullet_8_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_8[i]);
			}
			
			for(int i = 0; i < enemy_bullet_10_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_10[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 6th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ==================================================================
		// enemy disappear		
		for(int i = start_index4; i < end_index4; i++)
		{
			enemy_4_group[i].survive = false;
			enemy_4_group[i].label.setVisible(false);
		}
		
		start_index4 = end_index4;
		
		for(int i = start_index8; i < end_index8; i++)
		{
			enemy_8_group[i].survive = false;
			enemy_8_group[i].label.setVisible(false);
		}
		
		start_index8 = end_index8;
		
		for(int i = start_index10; i < end_index10; i++)
		{
			enemy_10_group[i].survive = false;
			enemy_10_group[i].label.setVisible(false);
		}
		
		start_index10 = end_index10;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_4, enemy_bullet_4_direction);
		erase_enemy_bullet_package(enemy_bullet_8, enemy_bullet_8_direction);
		erase_enemy_bullet_package(enemy_bullet_10, enemy_bullet_10_direction);
		
		// enemy occur
		enemy_11_group[11].label.setLocation(700, -100);
		enemy_11_group[12].label.setLocation(700, -150);
		enemy_11_group[13].label.setLocation(700, -200);
		enemy_11_group[14].label.setLocation(700, -250);
		enemy_11_group[15].label.setLocation(700, -300);
		
		enemy_12_left_group[2].label.setLocation(100, -50);
		enemy_12_left_group[3].label.setLocation(300, -300);
		
		enemy_13_group[0].label.setLocation(500, 150);
		
		power_generate_location = enemy_13_group[0].label.getLocation();
		
		// test code
		//int start_index11 = 11;
		//int end_index11 = 16;
		end_index11 = 16;
		
		int start_index13 = 0;
		int end_index13 = 1;
		
		// test code
		//int start_index12_left = 2;
		//int end_index12_left = 4;
		end_index12_left = 4;
		//boolean enemy_11_attack_pattern = true;
		//int enemy_12_attack_pattern = 0;
		int enemy_13_attack_pattern = 0;
		enemy_12_attack_pattern = 0;
		
		frame_enemy_move = 0;
		
		for(int i = start_index11; i < end_index11; i++)
		{
			enemy_11_group[i].label.setVisible(true);
		}
		
		for(int i = start_index12_left; i < end_index12_left; i++)
		{
			enemy_12_left_group[i].label.setVisible(true);
		}
		
		for(int i = start_index13; i < end_index13; i++)
		{
			enemy_13_group[i].label.setVisible(true);
		}
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		while(frame_count_current - frame_count_start < 50000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move >= 20)
			{
				for(int i = start_index11; i < end_index11; i++)
				{
					if(enemy_11_group[i].label.getY() >= 0)
					{
						enemy_11_group[i].move(0,  6);
					}
					else
					{
						Point p1 = enemy_11_group[i].label.getLocation();
						enemy_11_group[i].label.setLocation(p1.x, p1.y + 6);
					}
					
					if(enemy_11_group[i].label.getY() >= 600)
					{
						enemy_11_group[i].survive = false;
						enemy_11_group[i].label.setLocation(0, 0);
						enemy_11_group[i].label.setVisible(false);
					}
				}
		
				for(int i = start_index12_left; i < end_index12_left; i++)
				{
					if(enemy_12_left_group[i].label.getY() >= 0)
					{
						enemy_12_left_group[i].move(0,  6);
					}
					else
					{
						Point p1 = enemy_12_left_group[i].label.getLocation();
						enemy_12_left_group[i].label.setLocation(p1.x, p1.y + 6);
					}
					
					if(enemy_12_left_group[i].label.getY() >= 600)
					{
						enemy_12_left_group[i].survive = false;
						enemy_12_left_group[i].label.setLocation(0, 0);
						enemy_12_left_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move = 0;
			}
			else
			{
				frame_enemy_move++;
			}
			
			// enemy attack	
			for(int i = start_index11; i < end_index11; i++)
			{
				if(enemy_11_group[i].attack())
				{
					if(enemy_11_attack_pattern == true)
					{
						for(int j = 0; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = false;
					}
					else
					{
						for(int j = 1; j < enemy_bullet_11_direction; j += 2)
						{
							attack_enemy(enemy_11_group[i], enemy_bullet_11[j], 57, 89);
						}	
						enemy_11_attack_pattern = true;
					}
				}
				else
				{
					enemy_11_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index12_left; i < end_index12_left; i++)
			{
				if(enemy_12_left_group[i].attack())
				{
					if(enemy_12_attack_pattern % 3 == 0)
					{
						for(int j = 0; j < enemy_bullet_12_left_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_left[j], 77, 84);
						}
					}
					else if(enemy_12_attack_pattern % 3 == 1)
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_down[j], 77, 84);
						}
					}
					else
					{
						for(int j = 0; j < enemy_bullet_12_down_direction; j++)
						{
							attack_enemy(enemy_12_left_group[i], enemy_bullet_12_right[j], 77, 84);
						}
					}
				}
				else
				{
					enemy_12_left_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index13; i < end_index13; i++)
			{
				if(enemy_13_group[i].attack())
				{
					if(enemy_13_attack_pattern % 12 >= 0 && enemy_13_attack_pattern % 12 <= 2)
					{
						enemy_13_group[i].attack_interval = 20;
						for(int j = 6; j < enemy_bullet_13_direction; j++)
						{
							attack_enemy(enemy_13_group[i], enemy_bullet_13[j], 56, 44);
						}	
					}
					else if(enemy_13_attack_pattern % 12 == 3 || enemy_13_attack_pattern % 12 == 7 || enemy_13_attack_pattern % 12 == 11)
					{
						enemy_13_group[i].attack_interval = 50;
					}
					else if (enemy_13_attack_pattern % 12 >= 4 && enemy_13_attack_pattern % 12 <= 6)
					{
						enemy_13_group[i].attack_interval = 20;
						for(int j = 3; j < 6; j++)
						{
							attack_enemy(enemy_13_group[i], enemy_bullet_13[j], 56, 44);
						}
					}
					else
					{
						enemy_13_group[i].attack_interval = 20;
						for(int j = 0; j < 3; j++)
						{
							attack_enemy(enemy_13_group[i], enemy_bullet_13[j], 56, 44);
						}
					}
					enemy_13_attack_pattern++;
				}
				else
				{
					enemy_13_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_11[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_left_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_12_left[i]);
			}
			
			for(int i = 0; i < enemy_bullet_13_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_13[i]);
			}
			
			// power generate		
			if(enemy_13_group[0].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 56;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			power_generate_location = enemy_13_group[0].label.getLocation();
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_11_group, start_index11, end_index11);
			check_enemy_damaged_package(enemy_12_left_group, start_index12_left, end_index12_left);
			check_enemy_damaged_package(enemy_13_group, start_index13, end_index13);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_11_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_11[i]);
			}
			
			for(int i = 0; i < enemy_bullet_12_left_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_12_left[i]);
			}
			
			for(int i = 0; i < enemy_bullet_13_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_13[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 7th event
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// enemy disappear		
		for(int i = start_index11; i < end_index11; i++)
		{
			enemy_11_group[i].survive = false;
			enemy_11_group[i].label.setVisible(false);
		}
		
		start_index11 = end_index11;
		
		for(int i = start_index12_left; i < end_index12_left; i++)
		{
			enemy_12_left_group[i].survive = false;
			enemy_12_left_group[i].label.setVisible(false);
		}
		
		start_index12_left = end_index12_left;
		
		for(int i = start_index13; i < end_index13; i++)
		{
			enemy_13_group[i].survive = false;
			enemy_13_group[i].label.setVisible(false);
		}
		
		start_index13 = end_index13;
		
		// ==================================================================
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_11, enemy_bullet_11_direction);
		erase_enemy_bullet_package(enemy_bullet_12_left, enemy_bullet_12_left_direction);
		erase_enemy_bullet_package(enemy_bullet_13, enemy_bullet_13_direction);
		
		// enemy occur
		enemy_13_group[1].label.setLocation(500, -50);
		
		end_index13 = 2;
		
		enemy_14_group[0].label.setLocation(200, -50);
		enemy_14_group[1].label.setLocation(700, -50);
		
		int start_index14 = 0;
		int end_index14 = 2;
		
		power_generate_location = enemy_14_group[1].label.getLocation();
		
		frame_enemy_move = 0;
		
		for(int i = start_index13; i < end_index13; i++)
		{
			enemy_13_group[i].label.setVisible(true);
		}
		
		for(int i = start_index14; i < end_index14; i++)
		{
			enemy_14_group[i].label.setVisible(true);
		}
		
		frame_count = 0;
		enemy_attack_frame_count = 0;
		
		enemy_13_attack_pattern = 0;
		int enemy_14_attack_pattern = 0;
		
		while(frame_count_current - frame_count_start < 70000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			// enemy move downward
			if(frame_enemy_move >= 20)
			{
				for(int i = start_index13; i < end_index13; i++)
				{
					if(enemy_13_group[i].label.getY() >= 0)
					{
						enemy_13_group[i].move(2,  5);
					}
					else
					{
						Point p1 = enemy_13_group[i].label.getLocation();
						enemy_13_group[i].label.setLocation(p1.x + 2, p1.y + 5);
					}
					
					if(enemy_13_group[i].label.getY() >= 600)
					{
						enemy_13_group[i].survive = false;
						enemy_13_group[i].label.setLocation(0, 0);
						enemy_13_group[i].label.setVisible(false);
					}
				}
				
				if(enemy_14_group[0].label.getY() >= 0)
				{
					enemy_14_group[0].move(2,  5);
				}
				else
				{
					Point p1 = enemy_14_group[0].label.getLocation();
					enemy_14_group[0].label.setLocation(p1.x + 2, p1.y + 5);
				}
				
				if(enemy_14_group[1].label.getY() >= 0)
				{
					enemy_14_group[1].move(-2,  5);
				}
				else
				{
					Point p1 = enemy_14_group[1].label.getLocation();
					enemy_14_group[1].label.setLocation(p1.x - 2, p1.y + 5);
				}
		
				for(int i = start_index14; i < end_index14; i++)
				{					
					if(enemy_14_group[i].label.getY() >= 600)
					{
						enemy_14_group[i].survive = false;
						enemy_14_group[i].label.setLocation(0, 0);
						enemy_14_group[i].label.setVisible(false);
					}
				}
				frame_enemy_move = 0;
			}
			else
			{
				frame_enemy_move++;
			}
			
			// enemy attack				
			for(int i = start_index13; i < end_index13; i++)
			{
				if(enemy_13_group[i].attack())
				{
					if(enemy_13_attack_pattern % 12 >= 0 && enemy_13_attack_pattern % 12 <= 2)
					{
						enemy_13_group[i].attack_interval = 20;
						for(int j = 6; j < enemy_bullet_13_direction; j++)
						{
							attack_enemy(enemy_13_group[i], enemy_bullet_13[j], 56, 44);
						}	
					}
					else if(enemy_13_attack_pattern % 12 == 3 || enemy_13_attack_pattern % 12 == 7 || enemy_13_attack_pattern % 12 == 11)
					{
						enemy_13_group[i].attack_interval = 50;
					}
					else if (enemy_13_attack_pattern % 12 >= 4 && enemy_13_attack_pattern % 12 <= 6)
					{
						enemy_13_group[i].attack_interval = 20;
						for(int j = 3; j < 6; j++)
						{
							attack_enemy(enemy_13_group[i], enemy_bullet_13[j], 56, 44);
						}
					}
					else
					{
						enemy_13_group[i].attack_interval = 20;
						for(int j = 0; j < 3; j++)
						{
							attack_enemy(enemy_13_group[i], enemy_bullet_13[j], 56, 44);
						}
					}
					enemy_13_attack_pattern++;
				}
				else
				{
					enemy_13_group[i].attack_frame++;
				}
			}
			
			for(int i = start_index14; i < end_index14; i++)
			{
				if(enemy_14_group[i].attack())
				{
					if(enemy_14_attack_pattern % 8 >= 0 && enemy_14_attack_pattern % 8 <= 3)
					{
						enemy_14_group[i].attack_interval = 20;
						for(int j = 0; j < enemy_bullet_14_1_direction; j++)
						{
							attack_enemy(enemy_14_group[i], enemy_bullet_14_1[j], 100, 65);
						}	
					}
					else if(enemy_14_attack_pattern % 8 == 4 || enemy_14_attack_pattern % 8 == 7)
					{
						enemy_14_group[i].attack_interval = 70;
					}
					else
					{
						enemy_14_group[i].attack_interval = 20;
						for(int j = 0; j < enemy_bullet_14_2_direction; j++)
						{
							attack_enemy(enemy_14_group[i], enemy_bullet_14_2[j], 100, 65);
						}
					}
					enemy_14_attack_pattern++;
				}
				else
				{
					enemy_14_group[i].attack_frame++;
				}
			}
			
			// enemy bullet move						
			for(int i = 0; i < enemy_bullet_13_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_13[i]);
			}
			
			for(int i = 0; i < enemy_bullet_14_1_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_14_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_14_2_direction; i++)
			{
				enemy_bullet_move(enemy_bullet_14_2[i]);
			}
			
			// power generate		
			if(enemy_14_group[1].survive == false && power_up_item.posY == 0 && drop_power == false)
			{
				power_up_item.posX = power_generate_location.x + 100;
				power_up_item.posY = power_generate_location.y;
				power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
				power_up_item.label.setVisible(true);
				drop_power = true;
			}
			
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
			
			power_generate_location = enemy_14_group[1].label.getLocation();
			
			// check enemy damaged
			check_enemy_damaged_package(enemy_13_group, start_index13, end_index13);
			check_enemy_damaged_package(enemy_14_group, start_index14, end_index14);
			
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_13_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_13[i]);
			}
			
			for(int i = 0; i < enemy_bullet_14_1_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_14_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_14_2_direction; i++)
			{
				check_player_damaged_package(enemy_bullet_14_2[i]);
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 8th event
		
		// ========================================================================
		// rest time
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		// enemy disappear		
		for(int i = start_index13; i < end_index13; i++)
		{
			enemy_13_group[i].survive = false;
			enemy_13_group[i].label.setVisible(false);
		}
		
		start_index13 = end_index13;
		
		for(int i = start_index14; i < end_index14; i++)
		{
			enemy_14_group[i].survive = false;
			enemy_14_group[i].label.setVisible(false);
		}
		
		start_index14 = end_index14;
		
		// enemy bullet disappear
		erase_enemy_bullet_package(enemy_bullet_13, enemy_bullet_13_direction);
		erase_enemy_bullet_package(enemy_bullet_14_1, enemy_bullet_14_1_direction);
		erase_enemy_bullet_package(enemy_bullet_14_2, enemy_bullet_14_2_direction);
		
		while(frame_count_current - frame_count_start < 3000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// disapear player bullet
		erase_player_bullet_package();
		
		drop_power = false;
		power_up_item.posY = 0;
		
		// ===============================================================
		// 4th stage 1st boss occur		
		enemy_4th_stage_boss_1.label.setLocation(450, 0);
		enemy_4th_stage_boss_1.survive = true;
		enemy_4th_stage_boss_1.label.setVisible(true);
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_4th_stage_boss_1.hp));
		Label_hp.setVisible(true);
		
		enemy_attack_frame_count = 0;
		
		for(int frame = 0; frame < 50; frame++)
		{
			enemy_4th_stage_boss_1.move(0, 1);
			wait(20);
		}
		
		int enemy_4th_boss_1_attack_pattern = 0;
		// test code
		//int frame_move_pattern = 0;
		//int move_pattern = -1;
		//Random random = new Random();
		frame_move_pattern = 0;
		move_pattern = -1;
		boolean enemy_4th_boss_1_special_attack = false;
		int enemy_4th_boss_1_special_attack_count = 0;
		
		power_generate_location = enemy_4th_stage_boss_1.label.getLocation();
		Point boss = enemy_4th_stage_boss_1.label.getLocation();
		
		while(enemy_4th_stage_boss_1.survive == true)
		{
			//frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			frame_move_pattern++;
			// set enemy moving pattern
			if(frame_move_pattern >= 50)
			{
				move_pattern = random.nextInt(2);
				switch(move_pattern)
				{
				case 0:
					if(enemy_4th_stage_boss_1.label.getX() < 600)
					{
						enemy_4th_stage_boss_1.move(30, 0);
					}
					else
					{
						enemy_4th_stage_boss_1.move(-30, 0);
					}
					break;
				default:
					if(enemy_4th_stage_boss_1.label.getX() > 300)
					{
						enemy_4th_stage_boss_1.move(-30, 0);
					}
					else
					{
						enemy_4th_stage_boss_1.move(30, 0);
					}
					break;
				}
				frame_move_pattern = 0;
			}
			
			power_generate_location = enemy_4th_stage_boss_1.label.getLocation();
			boss = enemy_4th_stage_boss_1.label.getLocation();
			
			if(enemy_4th_stage_boss_1.attack())
			{
				if(enemy_4th_boss_1_special_attack == true)
				{
					enemy_4th_boss_1_attack_pattern = 2;
				}
				else
				{
					enemy_4th_boss_1_attack_pattern = random.nextInt(3);
				}
				
				if(enemy_4th_boss_1_attack_pattern == 0)
				{
					for(int i = 0; i < enemy_bullet_4th_boss_1_1_direction; i++)
					{
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_1[i], 15, 154);
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_1[i], 207, 154);
					}
				} // end of if(enemy_4th_boss_1_attack_pattern == 0)
				else if(enemy_4th_boss_1_attack_pattern == 1)
				{
					for(int i = 0; i < enemy_bullet_4th_boss_1_2_direction; i++)
					{
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_2[i], 15, 154);
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_2[i], 207, 154);
					}
				} // end of else if(enemy_4th_boss_1_attack_pattern == 1)
				else
				{
					if(enemy_4th_boss_1_special_attack == false)
					{
						enemy_4th_stage_boss_1.attack_interval = 15;
						enemy_4th_boss_1_special_attack = true;
					}
					
					if(enemy_4th_boss_1_special_attack_count >= 4)
					{
						enemy_4th_boss_1_special_attack_count = 0;
						enemy_4th_boss_1_special_attack = false;
						enemy_4th_stage_boss_1.attack_interval = 80;
					}
					else
					{
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_3, 5, 174);
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_3, 100, 174);
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_3, 120, 174);
						attack_enemy(enemy_4th_stage_boss_1, bullet_4th_boss_1_3, 217, 174);
						enemy_4th_boss_1_special_attack_count++;
					}
				} // end of else if(enemy_4th_middle_boss_attack_pattern % 5 == 2)
			}
			else
			{
				enemy_4th_stage_boss_1.attack_frame++;
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_1_1_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_1_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_1_2_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_1_2[i]);
			}
			
			enemy_bullet_move(bullet_4th_boss_1_3);
			
			check_enemy_damaged_package(enemy_4th_stage_boss_1);
			
			Label_hp.setText(Integer.toString(enemy_4th_stage_boss_1.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_4th_boss_1_1_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_1_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_1_2_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_1_2[i]);
			}
			
			check_player_damaged_package(bullet_4th_boss_1_3);
			
			set_player_invincibility_package();
			check_gameover();
			
		} // end of 4th stage 1st boss occur
		
		power_up_item.posX = power_generate_location.x + 110;
		power_up_item.posY = power_generate_location.y;
		power_up_item.label.setLocation(power_up_item.posX, power_up_item.posY);
		power_up_item.label.setVisible(true);
		drop_power = true;
		
		// ========================================================================
		// rest time
		enemy_4th_stage_boss_1.label.setLocation(boss);
		enemy_4th_stage_boss_1.label.setVisible(true);
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		// disappear bullet bomb
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i].label.setLocation(1200 ,0);
			bullet_bomb[i].label.setVisible(false);
		}
		
		// enemy bullet disappear
		erase_enemy_bullet_package(bullet_4th_boss_1_1, enemy_bullet_4th_boss_1_1_direction);
		erase_enemy_bullet_package(bullet_4th_boss_1_2, enemy_bullet_4th_boss_1_2_direction);
		erase_enemy_bullet_package(bullet_4th_boss_1_3);
		
		while(frame_count_current - frame_count_start < 3000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
			
			power_moving();
			check_player_get_power();
		}
		
		// disapear player bullet
		erase_player_bullet_package();
		
		// 4th stage 1st boss disappear
		
		enemy_4th_stage_boss_1.label.setLocation(boss);
		
		while(boss.y >= -500)
		{
			wait(20);
			enemy_4th_stage_boss_1.label.setLocation(boss.x, boss.y - 2);
			boss = enemy_4th_stage_boss_1.label.getLocation();
			if(drop_power == true)
			{
				power_moving();
				check_player_get_power();
			}
		}
		
		enemy_4th_stage_boss_1.label.setVisible(false);
		
		drop_power = false;
		power_up_item.posY = 0;
		
		erase_player_bullet_package();
		
		// ===============================================================
		// 4th stage 2nd boss occur		
		enemy_4th_stage_boss_2.label.setLocation(450, 0);
		enemy_4th_stage_boss_2.survive = true;
		enemy_4th_stage_boss_2.label.setVisible(true);
		
		for(int i = 0; i < intruder_1_group_count; i++)
		{
			intruder_1_group[i].label.setLocation(50 + 120 * i, 10);
		}
		
		for(int i = 0; i < intruder_2_group_count; i++)
		{
			intruder_2_group[i].label.setLocation(30, 30 + 85 * i);
		}
		
		Label_text_hp.setVisible(true);
		Label_hp.setText(Integer.toString(enemy_4th_stage_boss_2.hp));
		Label_hp.setVisible(true);
		
		enemy_attack_frame_count = 0;
		
		for(int frame = 0; frame < 50; frame++)
		{
			enemy_4th_stage_boss_2.move(0, 1);
			wait(20);
		}
		
		int enemy_4th_boss_2_attack_pattern = 0;
		
		frame_move_pattern = 0;
		move_pattern = 0;
		
		//boolean enemy_4th_boss_2_special_attack = false;
		int frame_enemy_4th_boss_2_special_attack_count = 0;
		int enemy_4th_boss_2_special_attack_2_count = 0;
		int enemy_4th_boss_2_special_attack_3_count = 0;
		int frame_enemy_4th_boss_2_special_attack_2_count = 0;
		
		Point special_bullet_1 = bullet_4th_boss_2_5[0][0].label.getLocation();
		Point special_bullet_2 = bullet_4th_boss_2_5[1][0].label.getLocation();
		
		Point boss_2 = enemy_4th_stage_boss_2.label.getLocation();
		
		while(enemy_4th_stage_boss_2.survive == true)
		{
			wait(5);
			frame_count++;
			enemy_attack_frame_count++;
			
			player_fire_bullet();
			use_bomb_package();
			check_player_invincibility();
			set_player_color_by_shield_barrier();
			
			frame_move_pattern++;
			frame_enemy_4th_boss_2_special_attack_count++; // laser attack
			frame_enemy_4th_boss_2_special_attack_2_count++; // entire attack
			
			// set enemy moving pattern
			if(frame_move_pattern >= 30 && frame_enemy_4th_boss_2_special_attack_count < 1200)
			{
				if(move_pattern % 2 == 0)
				{
					enemy_4th_stage_boss_2.move(30, 0);
					if(enemy_4th_stage_boss_2.label.getX() > 700)
					{
						move_pattern++;
					}
				}
				else
				{
					enemy_4th_stage_boss_2.move(-30, 0);
					if(enemy_4th_stage_boss_2.label.getX() < 50)
					{
						move_pattern++;
					}
				}
				frame_move_pattern = 0;
			}
			
			if(frame_enemy_4th_boss_2_special_attack_count >= 1000)
			{
				Panel_laser_1.setLocation(enemy_4th_stage_boss_2.label.getX() + 25, enemy_4th_stage_boss_2.label.getY() + 50);
				Panel_laser_2.setLocation(enemy_4th_stage_boss_2.label.getX() + 305, enemy_4th_stage_boss_2.label.getY() + 50);
				Panel_laser_1.setBackground(Color.GREEN);
				Panel_laser_2.setBackground(Color.GREEN);
				Panel_laser_1.setVisible(true);
				Panel_laser_2.setVisible(true);
			}
			
			if(frame_enemy_4th_boss_2_special_attack_count >= 1200)
			{
				Panel_laser_1.setBackground(Color.RED);
				Panel_laser_2.setBackground(Color.RED);
			}
			
			if(frame_enemy_4th_boss_2_special_attack_count >= 1600)
			{
				Panel_laser_1.setVisible(false);
				Panel_laser_2.setVisible(false);
				frame_enemy_4th_boss_2_special_attack_count = 0;
			}
			
			if(frame_enemy_4th_boss_2_special_attack_2_count >= 2000 && frame_enemy_4th_boss_2_special_attack_2_count <= 2300) // entire attack
			{
				for(int i = 0; i < intruder_1_group_count; i++)
				{
					intruder_1_group[i].label.setVisible(true);
				}
				
				for(int i = 0; i < intruder_2_group_count; i++)
				{
					intruder_2_group[i].label.setVisible(true);
				}
				
				for(int i = 0; i < intruder_1_group_count; i++)
				{
					if(intruder_1_group[i].attack())
					{
						attack_enemy(intruder_1_group[i], bullet_4th_boss_2_7, 0, 20, 200);
					}
					else
					{
						intruder_1_group[i].attack_frame++;
					}
				}
				
				for(int i = 0; i < intruder_2_group_count; i++)
				{
					if(intruder_2_group[i].attack())
					{
						attack_enemy(intruder_2_group[i], bullet_4th_boss_2_8, 20, 0, 200);
					}
					else
					{
						intruder_2_group[i].attack_frame++;
					}
				}
			}
			else if(frame_enemy_4th_boss_2_special_attack_2_count > 2300)
			{
				frame_enemy_4th_boss_2_special_attack_2_count = 0;
				
				for(int i = 0; i < intruder_1_group_count; i++)
				{
					intruder_1_group[i].label.setVisible(false);
				}
				
				for(int i = 0; i < intruder_2_group_count; i++)
				{
					intruder_2_group[i].label.setVisible(false);
				}
			}
			
			boss = enemy_4th_stage_boss_2.label.getLocation();
			
			if(enemy_4th_stage_boss_2.attack())
			{				
				if(enemy_4th_boss_2_attack_pattern % 10 >= 0 && enemy_4th_boss_2_attack_pattern % 10 <= 5)
				{
					enemy_4th_stage_boss_2.attack_interval = 20;
					for(int i = 0; i < enemy_bullet_4th_boss_2_1_direction; i++)
					{
						attack_enemy(enemy_4th_stage_boss_2, bullet_4th_boss_2_1[i], 151, 93);
					}
				} // end of if(enemy_4th_boss_2_attack_pattern % 9 >= 0 && enemy_4th_boss_2_attack_pattern % 9 <= 5)
				else if(enemy_4th_boss_2_attack_pattern % 10 == 6 || enemy_4th_boss_2_attack_pattern % 10 == 8)
				{
					enemy_4th_stage_boss_2.attack_interval = 80;
				}
				else if(enemy_4th_boss_2_attack_pattern % 10 == 7)
				{
					for(int i = 0; i < enemy_bullet_4th_boss_2_4_direction; i++)
					{
						attack_enemy(enemy_4th_stage_boss_2, bullet_4th_boss_2_4[i], 151, 25);
					}
				}
				else
				{
					attack_enemy(enemy_4th_stage_boss_2, bullet_4th_boss_2_5[0], 118, 92, 1);
					attack_enemy(enemy_4th_stage_boss_2, bullet_4th_boss_2_5[1], 182, 92, 1);
				}
				
				special_bullet_1 = bullet_4th_boss_2_5[0][0].label.getLocation();
				special_bullet_2 = bullet_4th_boss_2_5[1][0].label.getLocation();
				if(special_bullet_1.y >= 300 || special_bullet_2.y >= 300)
				{
					for(int i = 0; i < enemy_bullet_4th_boss_2_6_direction; i++)
					{
						attack_enemy(bullet_4th_boss_2_5[0], bullet_4th_boss_2_6[i], 20, 20, 1);
						attack_enemy(bullet_4th_boss_2_5[1], bullet_4th_boss_2_6[i], 20, 20, 1);
					}
					erase_enemy_bullet_package(bullet_4th_boss_2_5, 2, 1);
				}
				enemy_4th_boss_2_attack_pattern++;
			}
			else
			{
				enemy_4th_stage_boss_2.attack_frame++;
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_1_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_2_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_2_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_2_2[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_3_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_2_3[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_4_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_2_4[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_5_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_2_5[i], 1);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_6_direction; i++)
			{
				enemy_bullet_move(bullet_4th_boss_2_6[i]);
			}
			
			enemy_bullet_move(bullet_4th_boss_2_7, 200);
			enemy_bullet_move(bullet_4th_boss_2_8, 200);
			
			check_enemy_damaged_package(enemy_4th_stage_boss_2);
			
			Label_hp.setText(Integer.toString(enemy_4th_stage_boss_2.hp));
			Label_score.setText(Integer.toString(score));
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_1_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_2_1[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_2_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_2_2[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_3_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_2_3[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_4_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_2_4[i]);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_5_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_2_5[i], 1);
			}
			
			for(int i = 0; i < enemy_bullet_4th_boss_2_6_direction; i++)
			{
				check_player_damaged_package(bullet_4th_boss_2_6[i]);
			}
			
			check_player_damaged_package(bullet_4th_boss_2_7, 200);
			check_player_damaged_package(bullet_4th_boss_2_8, 200);
			
			if(frame_enemy_4th_boss_2_special_attack_count >= 1200 && frame_enemy_4th_boss_2_special_attack_count <= 1600)
			{
				if(Label_player.getX() >= Panel_laser_1.getX() && Label_player.getX() <= Panel_laser_1.getX() + 15)
				{
					if(shield_barrier == 0 && invincibility == false)
					{
						player_killed();
					}
					else
					{
						player_decrease_shield_barrier();
					}
				}
				
				if(Label_player.getX() + 85 >= Panel_laser_2.getX() && Label_player.getX() + 85 <= Panel_laser_2.getX() + 15)
				{
					if(shield_barrier == 0 && invincibility == false)
					{
						player_killed();
					}
					else
					{
						player_decrease_shield_barrier();
					}
				}
			}
			
			set_player_invincibility_package();
			check_gameover();
		} // end of 4th stage 2nd boss occur		
		erase_enemy_bullet_package(bullet_4th_boss_2_1, enemy_bullet_4th_boss_2_1_direction);
		erase_enemy_bullet_package(bullet_4th_boss_2_2, enemy_bullet_4th_boss_2_2_direction);
		erase_enemy_bullet_package(bullet_4th_boss_2_3, enemy_bullet_4th_boss_2_3_direction);
		erase_enemy_bullet_package(bullet_4th_boss_2_4, enemy_bullet_4th_boss_2_4_direction);
		erase_enemy_bullet_package(bullet_4th_boss_2_5, enemy_bullet_4th_boss_2_5_direction, 1);
		erase_enemy_bullet_package(bullet_4th_boss_2_6, enemy_bullet_4th_boss_2_6_direction);
		erase_enemy_bullet_package(bullet_4th_boss_2_7, 200);
		erase_enemy_bullet_package(bullet_4th_boss_2_8, 200);
		
		Label_hp.setVisible(false);
		Label_text_hp.setVisible(false);
		
		frame_count_current = System.currentTimeMillis();
		frame_count_start = System.currentTimeMillis();
		
		while(frame_count_current - frame_count_start < 2000)
		{
			frame_count_current = System.currentTimeMillis();
			wait(5);
			frame_count++;
			
			player_fire_bullet();
			check_player_invincibility();
			use_bomb_package();
			
			Label_score.setText(Integer.toString(score));
			set_player_invincibility_package();
		}
		
		// disappear player bullet
		erase_player_bullet_package();
		
		// disapear bullet bomb
		for(int i = 0; i < max_bomb_bullet; i++)
		{
			bullet_bomb[i].label.setLocation(1200 ,0);
			bullet_bomb[i].label.setVisible(false);
		}
		
		Point p_player = Label_player.getLocation();
		while(p_player.y >= -20)
		{
			wait(20);
			Label_player.setLocation(p_player.x, p_player.y - 20);
			p_player = Label_player.getLocation();
		}
		
		Label_final_score.setText(Integer.toString(score));
		Label_final_life.setText(Integer.toString(lifes));
		
		panel_main_screen.setVisible(false);
		panel_ending_credit.setVisible(true);
	}
} // end of public class Shooting_game 


class Enemy
{
	// private variables
	int total_hp;
	int hp; // current hp
	int posX;
	int posY;
	int width;
	int height;
	int attack_interval;
	int attack_frame;
	boolean survive = false;
	boolean getPower = false;
	JLabel label;
	
	// public member
	void decrease_hp(int damage)
	{
		if(survive == true)
		{
			hp -= damage;
			if(hp < 0)
			{
				hp = 0;
			}
		}
	}
	
	boolean attack()
	{
		if(attack_frame >= attack_interval)
		{
			attack_frame = 0;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	boolean killed()
	{
		if(hp <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	void disapear()
	{
		label.setLocation(0, 0);
		survive = false;
		label.setVisible(false);
	}
	
	void move(int x, int y)
	{
		Point p = label.getLocation();
		if(p.x + x <= 0)
		{
			p.x = 50;
		}
		else if(p.x + x >= 1200)
		{
			p.x = 1200;
		}
		else
		{
			p.x += x;
		}
		
		if(p.y + y <= 0)
		{
			p.y = 50;
		}
		else if(p.y + y >= 600)
		{
			p.y = 600;
		}
		else
		{
			p.y += y;
		}
		
		label.setLocation(p);
	}
} // end of class Enemy


class Bullet
{
	int posX;
	int posY;
	int move_x;
	int move_y;
	JLabel label;
	
	void move()
	{
		posX += move_x;
		posY += move_y;
	}
	
	void disapear()
	{
		if(posX >= 1200 || posX <= 0 || posY >= 900 || posY <= 0)
		{
			label.setLocation(0, 0);
			label.setVisible(false);
		}
	}
}

class Bullet_Radian // Bullet using radian
{
	float x;
	float y;
	int speed;
	float angle;
	float angleInRadians;
	JLabel label;
	
	Bullet_Radian(float angle)
	{
		this.x = 1200.0f;
		this.y = 0.0f;
		this.angle = angle;
		angleInRadians = (float)(angle * Math.PI / 180);
	}
	
	void set_AngleInRadians(float angle)
	{
		this.angle = angle;
		angleInRadians = (float)(angle * Math.PI / 180);
	}
	
	void move()
	{
		x = (float) (x + speed * Math.cos(angleInRadians));
        y = (float) (y + speed * Math.sin(angleInRadians));
        label.setLocation((int)x, (int)y);
	}
	
	void disapear()
	{
		if(x >= 1200.0f || x <= 0.0f || y >= 900.0f || y <= 0.0f)
		{
			label.setLocation(0, 0);
			label.setVisible(false);
		}
	}
}
