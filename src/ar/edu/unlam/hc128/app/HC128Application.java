package ar.edu.unlam.hc128.app;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.border.EmptyBorder;

import ar.edu.unlam.hc128.cipher.CipherManager;
import ar.edu.unlam.hc128.utils.RandomDataGenerator;
import ar.edu.unlam.hc128.visual.NuevoJPanel;
import ar.edu.unlam.hc128.visual.StyledButtonUI;

import javax.imageio.ImageIO;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

public class HC128Application extends JFrame {
    private static final long serialVersionUID = 1L;
    private NuevoJPanel contentPane;
    private JTextField textBoxKey;
    private JTextField textBoxIV;
    private JTextField textImage;
    private File imageSelectedFile;
    private File imageEncryptedFile;
    private final JSeparator separator = new JSeparator();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HC128Application frame = new HC128Application();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HC128Application() {
        setResizable(false);
        setTitle("Algoritmo HC-128");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 777);
        contentPane = new NuevoJPanel();
    //    contentPane.setBackground(new Color(172, 251, 231)); 
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textBoxKey = new JTextField();
        textBoxKey.setBounds(96, 77, 262, 20);
        contentPane.add(textBoxKey);
        textBoxKey.setColumns(10);

        JButton buttonGenerateKey = new JButton("Generar");
        buttonGenerateKey.setBackground(new Color(0, 128, 128));
        buttonGenerateKey.setForeground(new Color(255, 255, 255));
        buttonGenerateKey.setFont(new Font("Dialog", Font.BOLD, 12));
        buttonGenerateKey.setUI(new StyledButtonUI());
        buttonGenerateKey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textBoxKey.setText(RandomDataGenerator.generate());
            }
        });
        buttonGenerateKey.setBounds(377, 77, 118, 23);
        contentPane.add(buttonGenerateKey);

        JLabel labelKey = new JLabel("Key:");
        labelKey.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelKey.setBounds(26, 77, 75, 14);
        labelKey.setForeground(Color.WHITE);
        contentPane.add(labelKey);

        JLabel labelIV = new JLabel("IV:");
        labelIV.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelIV.setBounds(26, 111, 60, 14);
        labelIV.setForeground(Color.WHITE);
        contentPane.add(labelIV);

        textBoxIV = new JTextField();
        textBoxIV.setBounds(96, 108, 262, 20);
        contentPane.add(textBoxIV);
        textBoxIV.setColumns(10);

        JButton buttonGenerateIV = new JButton("Generar");
        buttonGenerateIV.setBackground(new Color(0, 128, 128));
        buttonGenerateIV.setFont(new Font("Dialog", Font.BOLD, 12));
        buttonGenerateIV.setForeground(new Color(255, 255, 255));
        buttonGenerateIV.setUI(new StyledButtonUI());
        buttonGenerateIV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textBoxIV.setText(RandomDataGenerator.generate());
            }
        });
        buttonGenerateIV.setBounds(377, 106, 118, 23);
        contentPane.add(buttonGenerateIV);

        JLabel labelImage = new JLabel("Imagen:");
        labelImage.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelImage.setBounds(26, 142, 60, 14);
        labelImage.setForeground(Color.WHITE);
        contentPane.add(labelImage);

        textImage = new JTextField();
        textImage.setBounds(96, 139, 262, 20);
        contentPane.add(textImage);
        textImage.setColumns(10);

        JLabel labelOriginalImage = new JLabel("");
        labelOriginalImage.setBounds(175, 291, 174, 172);
        contentPane.add(labelOriginalImage);

        BufferedImage myPicture;
        JLabel labelUnlamImage;
        try {
            myPicture = ImageIO.read(new File("unlam.png"));
            labelUnlamImage = new JLabel(new ImageIcon(myPicture.getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        } catch (IOException e1) {
            labelUnlamImage = new JLabel("");
        }
		
        labelUnlamImage.setBounds(1120, 11, 43, 42);
        contentPane.add(labelUnlamImage);
        
        JLabel labelOriginal = new JLabel("Imagen original");
        labelOriginal.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelOriginal.setBounds(217, 249, 112, 14);
        labelOriginal.setForeground(Color.WHITE);
        contentPane.add(labelOriginal);

        separator.setBounds(10, 179, 1200, 14);
        contentPane.add(separator);

        JLabel labelEncryptedImage = new JLabel("");
        labelEncryptedImage.setBounds(788, 291, 174, 172);
        contentPane.add(labelEncryptedImage);

        JLabel labelEncrypted = new JLabel("Imagen cifrada");
        labelEncrypted.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelEncrypted.setBounds(825, 249, 118, 14);
        labelEncrypted.setForeground(Color.WHITE);
        contentPane.add(labelEncrypted);

        JButton buttonSelectImage = new JButton("Seleccionar");
        buttonSelectImage.setBackground(new Color(0, 128, 128));
        buttonSelectImage.setForeground(new Color(255, 255, 255));
        buttonSelectImage.setFont(new Font("Dialog", Font.BOLD, 12));
        buttonSelectImage.setUI(new StyledButtonUI());
        buttonSelectImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                int option = jf.showOpenDialog(textImage);
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    imageSelectedFile = jf.getSelectedFile();
                    if (!imageSelectedFile.getName().endsWith(".bmp"))
                    {
                        JOptionPane.showMessageDialog(null, "La extension de la imagen debe ser \".bmp\"", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    textImage.setText(imageSelectedFile.getName());
                    BufferedImage imageSelected;
                    try
                    {
                        imageSelected = ImageIO.read(imageSelectedFile);
                        ImageIcon imageLabel = new ImageIcon(imageSelected.getScaledInstance(labelOriginalImage.getWidth(), labelOriginalImage.getHeight(), Image.SCALE_DEFAULT));
                        labelOriginalImage.setIcon(imageLabel);
                    }
                    catch (Exception e1)
                    {
                        JOptionPane.showMessageDialog(null, "Error cargando la imagen, por favor intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
                        textImage.setText(null);
                    }

                }

            }
        });
        buttonSelectImage.setBounds(377, 137, 118, 23);
        contentPane.add(buttonSelectImage);

        JLabel lblCriptografa = new JLabel(" Criptograf\u00EDa");
        lblCriptografa.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCriptografa.setBounds(20, 10, 106, 20);
        lblCriptografa.setForeground(Color.WHITE);
        contentPane.add(lblCriptografa);

        JLabel lblAlgoritmoHc = new JLabel(" Algoritmo HC-128");
        lblAlgoritmoHc.setBounds(20, 29, 134, 14);
        lblAlgoritmoHc.setForeground(Color.WHITE);
        contentPane.add(lblAlgoritmoHc);
        
        JLabel lblTitleFirstSection = new JLabel("Inicializaci\u00F3n");
        lblTitleFirstSection.setForeground(new Color(255, 255, 255));
        lblTitleFirstSection.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleFirstSection.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitleFirstSection.setBounds(499, 23, 118, 20);
        contentPane.add(lblTitleFirstSection);
        
        JLabel lblTitleSecondSection = new JLabel("Cifrado");
        lblTitleSecondSection.setForeground(new Color(255, 255, 255));
        lblTitleSecondSection.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleSecondSection.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitleSecondSection.setBounds(499, 208, 118, 20);
        contentPane.add(lblTitleSecondSection);
        
        JLabel lblImagenCifrada = new JLabel("Imagen cifrada");
        lblImagenCifrada.setForeground(Color.WHITE);
        lblImagenCifrada.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblImagenCifrada.setBounds(217, 485, 112, 14);
        contentPane.add(lblImagenCifrada);
        
        JLabel lblImagenDescifrada = new JLabel("Imagen descifrada");
        lblImagenDescifrada.setForeground(Color.WHITE);
        lblImagenDescifrada.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblImagenDescifrada.setBounds(825, 491, 118, 14);
        contentPane.add(lblImagenDescifrada);
        
        JLabel labelEncryptedImage2 = new JLabel("");
        labelEncryptedImage2.setForeground(Color.BLACK);
        labelEncryptedImage2.setBackground(Color.GRAY);
        labelEncryptedImage2.setBounds(175, 527, 174, 172);
        contentPane.add(labelEncryptedImage2);   
        
        JLabel labelDecryptedImage = new JLabel("");
        labelDecryptedImage.setBounds(788, 533, 174, 172);
        contentPane.add(labelDecryptedImage);
        
        JButton btnDecrypt = new JButton("Descifrar");
        btnDecrypt.setFont(new Font("Dialog", Font.BOLD, 12));
        btnDecrypt.setForeground(new Color(255, 255, 255));
        btnDecrypt.setBackground(new Color(0, 128, 128));
        btnDecrypt.setUI(new StyledButtonUI());
        btnDecrypt.setBounds(469, 587, 180, 23);
        btnDecrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrypt(labelEncryptedImage2, labelDecryptedImage);
            }
        });
        contentPane.add(btnDecrypt);
        

        JButton buttonEncrypt = new JButton("Cifrar");
        buttonEncrypt.setFont(new Font("Dialog", Font.BOLD, 12));
        buttonEncrypt.setForeground( new Color(255, 255, 255) );
        buttonEncrypt.setBackground(new Color(0, 128, 128));
        buttonEncrypt.setUI(new StyledButtonUI());
        buttonEncrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encrypt(labelOriginalImage, labelEncryptedImage, labelEncryptedImage2);
            }
        });
        buttonEncrypt.setBounds(469, 348, 180, 23);
        contentPane.add(buttonEncrypt);
        
        
        JButton histogramaImagenOrigin = new JButton("Histograma Original");
        histogramaImagenOrigin.setBackground(new Color(0, 128, 128));
        histogramaImagenOrigin.setForeground(new Color(255, 255, 255));
        histogramaImagenOrigin.setFont(new Font("Dialog", Font.BOLD, 12));
        histogramaImagenOrigin.setUI(new StyledButtonUI());
        histogramaImagenOrigin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	if(imageSelectedFile != null) {
                		new HistogramaFrame(imageSelectedFile, "Histograma Original");
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Por favor seleccione una imagen", "Error", JOptionPane.ERROR_MESSAGE);
                	}
					
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        histogramaImagenOrigin.setBounds(469, 300, 180, 23);
        contentPane.add(histogramaImagenOrigin);
        
        
        JButton histogramaImagenCifrada = new JButton("Histograma Cifrada");
        histogramaImagenCifrada.setBackground(new Color(0, 128, 128));
        histogramaImagenCifrada.setForeground(new Color(255, 255, 255));
        histogramaImagenCifrada.setFont(new Font("Dialog", Font.BOLD, 12));
        histogramaImagenCifrada.setUI(new StyledButtonUI());
        histogramaImagenCifrada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	if(imageEncryptedFile != null) {
                		new HistogramaFrame(imageEncryptedFile, "Histograma Cifrada");
                	} else {
                		JOptionPane.showMessageDialog(null, "Por favor primero cifre una imagen", "Error", JOptionPane.ERROR_MESSAGE);
                	}
                	
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        histogramaImagenCifrada.setBounds(469, 539, 180, 23);
        contentPane.add(histogramaImagenCifrada);

        
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
    }

    public void encrypt(JLabel labelOriginalImage, JLabel labelEncryptedImage, JLabel labelEncryptedImage2) {
        if(textBoxKey.getText().length() == 16)
        {
            if(textBoxIV.getText().length() == 16)
            {
                if (imageSelectedFile != null)
                {
                    try
                    {
                        String key = textBoxKey.getText();
                        String IV = textBoxIV.getText();
                        imageEncryptedFile = getEncryptedImage(key, IV, imageSelectedFile);
                        BufferedImage imageEncrypted = ImageIO.read(imageEncryptedFile);
                        ImageIcon encryptedImageIcon = new ImageIcon(imageEncrypted.getScaledInstance(labelOriginalImage.getWidth(), labelOriginalImage.getHeight(), Image.SCALE_DEFAULT));
                        labelEncryptedImage.setIcon(encryptedImageIcon);
                        labelEncryptedImage2.setIcon(encryptedImageIcon);
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
                JOptionPane.showMessageDialog(null, "El tamaño del iv debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El tamaño de la key debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void decrypt(JLabel lbl_EncryptedImage, JLabel lbl_DecryptedImage) {
        if(textBoxKey.getText().length() == 16)
        {
            if(textBoxIV.getText().length() == 16)
            {
                if (imageEncryptedFile != null)
                {
                    try
                    {
                        String key = textBoxKey.getText();
                        String IV = textBoxIV.getText();
                        File decryptedFile = getDecryptedImage(key, IV, imageEncryptedFile);
                        BufferedImage imageDecrypted = ImageIO.read(decryptedFile);
                        ImageIcon imageLabel = new ImageIcon(imageDecrypted.getScaledInstance(lbl_EncryptedImage.getWidth(), lbl_EncryptedImage.getHeight(), Image.SCALE_DEFAULT));
                        lbl_DecryptedImage.setIcon(imageLabel);
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error descenriptando la imagen, por favor intentelo de nuevo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Por favor encripte una imagen primero", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El tamaño del iv debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El tamaño de la key debe ser de 16 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public File getEncryptedImage(String key, String IV, File originalImage) throws IOException {
    	return applyHC128ToImage(key, IV, originalImage, "encrypted.bmp");
    }
    
    public File getDecryptedImage(String key, String IV, File encryptedImage) throws IOException {
    	return applyHC128ToImage(key, IV, encryptedImage, "decrypted.bmp");
    }
    
    private File applyHC128ToImage(String key, String IV, File image, String outputImageName) throws IOException {
		CipherManager cm = new CipherManager();
		cm.initializeData(key, IV);
		return cm.applyHC128(image, outputImageName);
    }   

    private void crearJPanelHistograma(File Image) throws IOException {
    	System.out.println("ABRE NUEVA VENTANA");
    }
    	
}
