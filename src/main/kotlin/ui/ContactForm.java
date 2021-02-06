package ui;
import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm  extends JFrame {

    private JPanel contactPanel;
    private JTextField textName;
    private JTextField textphone;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private ContactBusiness mContactBusiness;

    public ContactForm(){
        configuracaoTela();

        mContactBusiness = new ContactBusiness();
        setListeners();

    }

    private void configuracaoTela(){
        setContentPane(contactPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2 );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setListeners(){
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    mContactBusiness.save(textName.getText(), textphone.getText());
                    new MainForm();
                    dispose();
                }catch (Exception exception){

                    JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });
    }

    private void createUIComponents() {
        try{
            javax.swing.text.MaskFormatter formataIntervalo = new javax.swing.text.MaskFormatter("###########");
            textphone = new javax.swing.JFormattedTextField(formataIntervalo);
        }catch(Exception e){
        }
    }
}
