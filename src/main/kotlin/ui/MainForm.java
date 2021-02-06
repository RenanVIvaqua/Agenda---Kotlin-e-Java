package ui;

import business.ContactBusiness;
import entity.ContactEntity;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonNovaContato;
    private JButton buttonRemover;
    private JTable tableContatos;
    private JLabel labelContactCount;
    private ContactBusiness mContactBusiness;
    private String mNome = "";
    private String mPhone = "";

    public MainForm(){

        configuracaoTela();

        mContactBusiness = new ContactBusiness();

        setListeners();
        loadContacts();
    }

    private void configuracaoTela(){
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2 );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void loadContacts(){
        List<ContactEntity>contactEntityList = mContactBusiness.getList();

        String[] columNames = {"Nome","Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0],columNames);

        for(ContactEntity  i: contactEntityList){
            Object[] object = new Object[2];

            object[0] = i.getName();
            object[1] = i.getPhone();

            model.addRow(object);
        }
        tableContatos.clearSelection();
        tableContatos.setModel(model);

        labelContactCount.setText(mContactBusiness.getContactCount());
    }

    private void setListeners(){

        buttonNovaContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //abre o novo formulario
                new ContactForm();
                //Fecha o formulario atual
                dispose();
            }
        });

        buttonRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mContactBusiness.delete(mNome, mPhone);
                    loadContacts();
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
                }
                finally {
                    mNome = "";
                    mPhone = "";
                }
            }
        });

        tableContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){

                    if(tableContatos.getSelectedRow() != -1) {
                        mNome = tableContatos.getValueAt(tableContatos.getSelectedRow(), 0).toString();
                        mPhone = tableContatos.getValueAt(tableContatos.getSelectedRow(), 1).toString();
                    }
                }
            }
        });

    }
}
