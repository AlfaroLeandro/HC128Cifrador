package ar.edu.unlam.hc128.app;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import ar.edu.unlam.hc128.cipher.CipherManager;
import ar.edu.unlam.hc128.utils.RandomDataGenerator;
import ar.edu.unlam.hc128.visual.NuevoJPanel;
import ar.edu.unlam.hc128.visual.StyledButtonUI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.*;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
        setBounds(100, 100, 1200, 600);
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
        labelOriginalImage.setBounds(56, 319, 174, 172);
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
        labelOriginal.setBounds(96, 283, 112, 14);
        labelOriginal.setForeground(Color.WHITE);
        contentPane.add(labelOriginal);

        separator.setBounds(10, 179, 1200, 14);
        contentPane.add(separator);

        JLabel labelEncryptedImage = new JLabel("");
        labelEncryptedImage.setBounds(462, 308, 174, 172);
        contentPane.add(labelEncryptedImage);

        JLabel labelEncrypted = new JLabel("Imagen cifrada");
        labelEncrypted.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelEncrypted.setBounds(499, 283, 118, 14);
        labelEncrypted.setForeground(Color.WHITE);
        contentPane.add(labelEncrypted);

        JLabel labelDecryptedImage = new JLabel("");
        labelDecryptedImage.setBounds(867, 308, 174, 172);
        contentPane.add(labelDecryptedImage);

        JLabel labelDecrypted = new JLabel("Imagen descifrada");
        labelDecrypted.setFont(new Font("Tahoma", Font.BOLD, 11));
        labelDecrypted.setBounds(904, 283, 118, 14);
        labelDecrypted.setForeground(Color.WHITE);
        contentPane.add(labelDecrypted);

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

        JButton buttonEncrypt = new JButton("Cifrar");
        buttonEncrypt.setFont(new Font("Dialog", Font.BOLD, 12));
        buttonEncrypt.setForeground( new Color(255, 255, 255) );
        buttonEncrypt.setBackground(new Color(0, 128, 128));
        buttonEncrypt.setUI(new StyledButtonUI());
        buttonEncrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encrypt(labelOriginalImage, labelEncryptedImage);
            }
        });
        buttonEncrypt.setBounds(250, 334, 180, 23);
        contentPane.add(buttonEncrypt);



        JButton buttonDecrypt = new JButton("Descifrar");
        buttonDecrypt.setFont(new Font("Dialog", Font.BOLD, 12));
        buttonDecrypt.setForeground( new Color(255, 255, 255) );
        buttonDecrypt.setBackground(new Color(0, 128, 128));
        buttonDecrypt.setUI(new StyledButtonUI());
        buttonDecrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decrypt(labelEncryptedImage, labelDecryptedImage);
            }
        });
        buttonDecrypt.setBounds(670, 334, 180, 23);
        contentPane.add(buttonDecrypt);

        JLabel lblCriptografa = new JLabel(" Criptograf\u00EDa");
        lblCriptografa.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCriptografa.setBounds(20, 10, 106, 20);
        lblCriptografa.setForeground(Color.WHITE);
        contentPane.add(lblCriptografa);

        JLabel lblAlgoritmoHc = new JLabel(" Algoritmo HC-128");
        lblAlgoritmoHc.setBounds(20, 29, 134, 14);
        lblAlgoritmoHc.setForeground(Color.WHITE);
        contentPane.add(lblAlgoritmoHc);
        
        JLabel lblNewLabel = new JLabel("Inicializaci\u00F3n");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(499, 23, 118, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblImagen = new JLabel("Cifrado");
        lblImagen.setForeground(new Color(255, 255, 255));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblImagen.setBounds(499, 208, 118, 20);
        contentPane.add(lblImagen);
    }

    public void encrypt(JLabel labelOriginalImage, JLabel labelEncryptedImage) {
        if(textBoxKey.getText().length() == 16)
        {
            if(textBoxIV.getText().length() == 16)
            {
                if (imageSelectedFile != null)
                {
                    try
                    {
                        String key = textBoxKey.getText();
                        CipherManager cm = new CipherManager();
                        cm.initializeData(key, textBoxIV.getText());
                        File encryptedFile = cm.encrypt(imageSelectedFile);
                        BufferedImage imageEncrypted;
                        imageEncrypted = ImageIO.read(encryptedFile);
                        ImageIcon imageLabel = new ImageIcon(imageEncrypted.getScaledInstance(labelOriginalImage.getWidth(), labelOriginalImage.getHeight(), Image.SCALE_DEFAULT));
                        labelEncryptedImage.setIcon(imageLabel);
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
        if(textBoxKey.getText().length() == 16)
        {
            if(textBoxIV.getText().length() == 16)
            {
                if (imageSelectedFile != null)
                {
                    try
                    {
                        String key = textBoxKey.getText();
                        imageEncryptedFile = new File("Imagenes\\temp.bmp");
                        CipherManager cm = new CipherManager();
                        cm.initializeData(key, textBoxIV.getText());
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
