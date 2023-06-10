package Application;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.CipherManager;
import Util.DataGenerator;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

public class HC128Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_Key;
	private JTextField txt_iv;
	private JTextField txt_Image;
	private File imageSelectedFile;
	private File imageEncryptedFile;
	private final JSeparator separator = new JSeparator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HC128Main frame = new HC128Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public HC128Main() throws IOException {
		setResizable(false);
		setTitle("Algoritmo HC-128");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(172, 251, 231));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_Key = new JTextField();
		txt_Key.setBounds(96, 77, 262, 20);
		contentPane.add(txt_Key);
		txt_Key.setColumns(10);
		
		Button btn_GenerateKey = new Button("Generar");
		btn_GenerateKey.setBackground(new Color(0, 128, 128));
		btn_GenerateKey.setForeground(new Color(255, 255, 255));
		btn_GenerateKey.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_GenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_Key.setText(DataGenerator.generateData());
			}
		});
		btn_GenerateKey.setBounds(377, 77, 118, 23);
		contentPane.add(btn_GenerateKey);
		
		JLabel lbl_Key = new JLabel("Key:");
		lbl_Key.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Key.setBounds(26, 77, 75, 14);
		contentPane.add(lbl_Key);
		
		JLabel lbl_iv = new JLabel("IV:");
		lbl_iv.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_iv.setBounds(26+600, 77, 60, 14);
		contentPane.add(lbl_iv);
		
		txt_iv = new JTextField();
		txt_iv.setBounds(96+600, 77, 262, 20);
		contentPane.add(txt_iv);
		txt_iv.setColumns(10);
		
		Button btn_GenerateIv = new Button("Generar");
		btn_GenerateIv.setBackground(new Color(0, 128, 128));
		btn_GenerateIv.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_GenerateIv.setForeground(new Color(255, 255, 255));
		btn_GenerateIv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_iv.setText(DataGenerator.generateData());
			}
		});
		btn_GenerateIv.setBounds(377+600, 77, 118, 23);
		contentPane.add(btn_GenerateIv);
		
		JLabel lbl_Image = new JLabel("Imagen:");
		lbl_Image.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Image.setBounds(26+330, 156-10, 60, 14);
		contentPane.add(lbl_Image);
		
		txt_Image = new JTextField();
		txt_Image.setBounds(96+330, 153-10, 262, 20);
		contentPane.add(txt_Image);
		txt_Image.setColumns(10);
		
		JLabel lbl_OriginalImage = new JLabel("");
		lbl_OriginalImage.setBounds(56, 306-10, 174, 172);
		contentPane.add(lbl_OriginalImage);

		JLabel lbl_UnlamImage = new JLabel("");
		lbl_UnlamImage.setBounds(26, 11, 43, 42);
		File unlamIcon = new File ("unlam.png");
		BufferedImage unlamSelected = ImageIO.read(unlamIcon);
		ImageIcon unlamLabel = new ImageIcon(unlamSelected.getScaledInstance(lbl_UnlamImage.getWidth(), lbl_UnlamImage.getHeight(), Image.SCALE_DEFAULT));
		lbl_UnlamImage.setIcon(unlamLabel);
		contentPane.add(lbl_UnlamImage);
		
		JLabel lbl_Original = new JLabel("Imagen original");
		lbl_Original.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Original.setBounds(85, 270-10, 112, 14);
		contentPane.add(lbl_Original);
		
		JLabel lbl_EncryptedImage = new JLabel("");
		lbl_EncryptedImage.setBounds(282+180, 295-10, 174, 172);
		contentPane.add(lbl_EncryptedImage);
		
		JLabel lbl_Encrypted = new JLabel("Imagen cifrada");
		lbl_Encrypted.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Encrypted.setBounds(319+180, 270-10, 118, 14);
		contentPane.add(lbl_Encrypted);
		
		JLabel lbl_DecryptedImage = new JLabel("");
		lbl_DecryptedImage.setBounds(282+585, 295-10, 174, 172);
		contentPane.add(lbl_DecryptedImage);
		
		JLabel lbl_Decrypted = new JLabel("Imagen descifrada");
		lbl_Decrypted.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_Decrypted.setBounds(319+585, 270-10, 118, 14);
		contentPane.add(lbl_Decrypted);
		
		Button btn_SelectImage = new Button("Seleccionar");
		btn_SelectImage.setBackground(new Color(0, 128, 128));
		btn_SelectImage.setForeground(new Color(255, 255, 255));
		btn_SelectImage.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_SelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				int option = jf.showOpenDialog(txt_Image);
				if (option == JFileChooser.APPROVE_OPTION) 
				{
					imageSelectedFile = jf.getSelectedFile();
					if (!imageSelectedFile.getName().endsWith(".bmp"))
					{
						JOptionPane.showMessageDialog(null, "La extension de la imagen debe ser \".bmp\"", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					txt_Image.setText(imageSelectedFile.getName());
					BufferedImage imageSelected;
					try 
					{
						imageSelected = ImageIO.read(imageSelectedFile);
						ImageIcon imageLabel = new ImageIcon(imageSelected.getScaledInstance(lbl_OriginalImage.getWidth(), lbl_OriginalImage.getHeight(), Image.SCALE_DEFAULT));
						lbl_OriginalImage.setIcon(imageLabel);
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, "Error cargando la imagen, por favor intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
						txt_Image.setText(null);
					}
					
				}
				
			}
		});
		btn_SelectImage.setBounds(377+330, 150-10, 118, 23);
		contentPane.add(btn_SelectImage);
		
		Button btn_Encrypt = new Button("Cifrar");
		btn_Encrypt.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_Encrypt.setForeground( new Color(255, 255, 255) );
		btn_Encrypt.setBackground(new Color(0, 128, 128));
		btn_Encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encrypt(lbl_OriginalImage, lbl_EncryptedImage);
			}
		});
		btn_Encrypt.setBounds(130+120, 191+120, 180, 23);
		contentPane.add(btn_Encrypt);
		
		Button btn_Encrypt_Intercept = new Button("Cifrar interceptando 1bit");
		btn_Encrypt_Intercept.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_Encrypt_Intercept.setForeground( new Color(255, 255, 255) );
		btn_Encrypt_Intercept.setBackground(new Color(0, 128, 128));
		btn_Encrypt_Intercept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encrypt(lbl_OriginalImage, lbl_EncryptedImage, true);
			}
		});
		btn_Encrypt_Intercept.setBounds(130+120, 191+120+50, 180, 23);
		contentPane.add(btn_Encrypt_Intercept);
		
		Button btn_Decrypt_Intercept = new Button("Descifrar interceptando 1bit");
		btn_Decrypt_Intercept.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_Decrypt_Intercept.setForeground( new Color(255, 255, 255) );
		btn_Decrypt_Intercept.setBackground(new Color(0, 128, 128));
		btn_Decrypt_Intercept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decrypt(lbl_EncryptedImage, lbl_DecryptedImage, true);
			}
		});
		btn_Decrypt_Intercept.setBounds(130+540, 191+120+50, 180, 23);
		contentPane.add(btn_Decrypt_Intercept);
		
		
		separator.setBounds(0, 239-50, 1200, 31);
		contentPane.add(separator);
		
		Button btn_Decrypt = new Button("Descifrar");
		btn_Decrypt.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_Decrypt.setForeground( new Color(255, 255, 255) );
		btn_Decrypt.setBackground(new Color(0, 128, 128));
		btn_Decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decrypt(lbl_EncryptedImage, lbl_DecryptedImage);
			}
		});
		btn_Decrypt.setBounds(130+540, 191+120, 180, 23);
		contentPane.add(btn_Decrypt);
		
		JLabel lblCriptografa = new JLabel(" Criptograf\u00EDa");
		lblCriptografa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCriptografa.setBounds(79, 11, 106, 20);
		contentPane.add(lblCriptografa);
		
		JLabel lblAlgoritmoHc = new JLabel(" Algoritmo HC-128");
		lblAlgoritmoHc.setBounds(79, 30, 134, 14);
		contentPane.add(lblAlgoritmoHc);
	}
	
	public void encrypt(JLabel lbl_OriginalImage, JLabel lbl_EncryptedImage) {
		encrypt(lbl_OriginalImage, lbl_EncryptedImage, false);
	}
	
	public void encrypt(JLabel lbl_OriginalImage, JLabel lbl_EncryptedImage, boolean interceptText) {
		if(txt_Key.getText().length() == 16) 
		{
			if(txt_iv.getText().length() == 16) 
			{
				if (imageSelectedFile != null) 
				{
					try 
					{
						String key = txt_Key.getText();
						if(interceptText) {
							char[] chars = key.toCharArray();
							int len = chars.length;
							Random rand = new Random();
							int indexRand = rand.nextInt(0, len);
							chars[indexRand] = (char) (chars[indexRand] ^ 1 ); //cambio un bit en la pos 1
							key = new String(chars);
							System.out.println("llave cambiada a: " + key);
						}
						
						CipherManager cm = new CipherManager();
						cm.initializeData(key, txt_iv.getText());
						File encryptedFile = cm.encrypt(imageSelectedFile);
						BufferedImage imageEncrypted;
						imageEncrypted = ImageIO.read(encryptedFile);
						ImageIcon imageLabel = new ImageIcon(imageEncrypted.getScaledInstance(lbl_OriginalImage.getWidth(), lbl_OriginalImage.getHeight(), Image.SCALE_DEFAULT));
						lbl_EncryptedImage.setIcon(imageLabel);
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null, "Error encriptando la imagen, por favor intentelo de nuevo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Por favor seleccione una imagen", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "El tama�o del iv debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "El tama�o de la key debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void decrypt(JLabel lbl_EncryptedImage, JLabel lbl_DecryptedImage) {
		decrypt(lbl_EncryptedImage, lbl_DecryptedImage, false);
	}
	
	public void decrypt(JLabel lbl_EncryptedImage, JLabel lbl_DecryptedImage, boolean interceptText) {
		if(txt_Key.getText().length() == 16) 
		{
			if(txt_iv.getText().length() == 16) 
			{
				if (imageSelectedFile != null) 
				{
					try 
					{
						String key = txt_Key.getText();
						if(interceptText) {
							char[] chars = key.toCharArray();
							int len = chars.length;
							Random rand = new Random();
							int indexRand = rand.nextInt(0, len);
							chars[indexRand] = (char) (chars[indexRand] ^ 1 ); //cambio un bit en la pos 1
							key = new String(chars);
							System.out.println("llave cambiada a: " + key);
						}
						
						imageEncryptedFile = new File("Imagenes\\temp.bmp");
						CipherManager cm = new CipherManager();
						cm.initializeData(key, txt_iv.getText());
						File encryptedFile = cm.encrypt(imageEncryptedFile);
						BufferedImage imageEncrypted;
						imageEncrypted = ImageIO.read(encryptedFile);
						ImageIcon imageLabel = new ImageIcon(imageEncrypted.getScaledInstance(lbl_EncryptedImage.getWidth(), lbl_EncryptedImage.getHeight(), Image.SCALE_DEFAULT));
						lbl_DecryptedImage.setIcon(imageLabel);
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null, "Error encriptando la imagen, por favor intentelo de nuevo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Por favor seleccione una imagen", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "El tama�o del iv debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "El tama�o de la key debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
}
